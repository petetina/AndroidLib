package antoinepetetin.fr.easylogin.loginProcess

import android.content.Context
import android.content.Intent
import android.util.Log
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.user.EasyUser
import antoinepetetin.fr.easylogin.user.EasyUserProperty
import antoinepetetin.fr.easylogin.user.UserSessionManager

class CustomLogin(config: EasyLoginConfig, var requiredFields: Array<EasyUserProperty>? = null) : LoginProcess(config), EasyLoginInterface {

    override fun login() {

        if(isUserConnected()){
            throwUserAlreadyConnectedFailure(LoginType.CustomLogin)
        }else {
            val user = config.getCallback().doCustomLogin()
            if (user != null) {

                //Check if user is valid
                if (userIsValid(user)) {
                    // Save the user
                    UserSessionManager.setUserSession(config.getActivity(), user)
                    config.getCallback().onLoginSuccess(user)
                } else
                    config.getCallback().onLoginFailure(EasyLoginException("User is not valid", LoginType.CustomLogin))
            } else {
                config.getCallback().onLoginFailure(EasyLoginException("Custom login failed", LoginType.CustomLogin))
            }
        }
    }

    //TODO return an array of int to know what fields cause problem !!!!
    private fun userIsValid(user: EasyUser): Boolean {
        var isValid = user.email != null || user.username != null

        requiredFields?.let {


            if (isValid) {

                it.map {

                    when (it) {
                        EasyUserProperty.EMAIL -> {
                            val emailOk = EasyUserVerification.checkEmail(user.email)
                            //Get email view and display/hide error
                            var email = (config.getActivity() as ConnectedActivity).dialog!!.loginLayout!!.emailField

                            if(emailOk)
                                email!!.error = null
                            else
                                email!!.error = "Invalid email"

                            isValid = isValid.and(emailOk)
                        }
                        EasyUserProperty.USERNAME -> {
                            isValid = isValid.and(!user.username.isNullOrBlank())
                        }
                    }
                }
            }

        }
        return isValid
    }

    override fun signup() {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }
}