package antoinepetetin.fr.easylogin.loginProcess

import android.content.Context
import android.content.Intent
import android.util.Log
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.user.EasyUser
import antoinepetetin.fr.easylogin.user.EasyUserProperty
import antoinepetetin.fr.easylogin.user.UserSessionManager


class CustomLogin(var requiredFields: Array<EasyUserProperty>? = null) : EasyLogin() {

    override fun login(config: EasyLoginConfig) {
        val user = config.getCallback().doCustomLogin()
        if (user != null) {

            //Check if user is valid
            if(userIsValid(user)) {
                // Save the user
                UserSessionManager.setUserSession(config.getActivity(), user)
                config.getCallback().onLoginSuccess(user)
            }else
                config.getCallback().onLoginFailure(EasyLoginException("Custom login : user is not valid", LoginType.CustomLogin))
        } else {
            config.getCallback().onLoginFailure(EasyLoginException("Custom login failed", LoginType.CustomLogin))
        }
    }

    private fun userIsValid(user: EasyUser): Boolean {
        var isValid = true

        requiredFields?.let {
            isValid = user.email != null || user.username != null

            if (isValid) {

                it.map {

                    when (it) {
                        EasyUserProperty.EMAIL -> {
                            isValid = isValid.and(EasyUserVerification.checkEmail(user.email))
                        }
                        EasyUserProperty.USERNAME -> {
                            isValid = isValid.and(!user.username.isNullOrBlank())
                        }
                    }
                }
            }
            return isValid
        }
    }

    override fun signup(config: EasyLoginConfig) {
        val user = config.getCallback().doCustomSignup()
        if (user != null) {
            // Save the user
            UserSessionManager.setUserSession(config.getActivity(), user)
            config.getCallback().onLoginSuccess(user)
        } else {
            config.getCallback().onLoginFailure(EasyLoginException("Custom signup failed", LoginType.CustomLogin))
        }
    }

    override fun logout(context: Context): Boolean {
        try {
            val preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.remove(Constants.USER_TYPE)
            editor.remove(Constants.USER_SESSION)
            editor.apply()
            return true
        } catch (e: Exception) {
            Log.e("CustomLogin", e.message)
            return false
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent, config: EasyLoginConfig) {

    }
}