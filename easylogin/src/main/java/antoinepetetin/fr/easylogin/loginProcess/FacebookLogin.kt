package antoinepetetin.fr.easylogin.loginProcess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.user.EasyFacebookUser
import antoinepetetin.fr.easylogin.user.UserSessionManager
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.json.JSONException
import org.json.JSONObject


internal class FacebookLogin(config: EasyLoginConfig): LoginProcess(config), EasyLoginInterface {

    private val callbackManager:CallbackManager
    private var button: LoginButton? = null

    init{
        //Facebook login callback
        callbackManager = CallbackManager.Factory.create()
    }

    override fun login() {
    }

    fun registerCallback(button: LoginButton){
        if(isUserConnected()){
            throwUserAlreadyConnectedFailure(LoginType.Facebook)
        }else {
            button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {

                    val request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken()
                    ) { `object`, response ->
                        try {

                            val facebookUser = populateFacebookUser(`object`, loginResult.accessToken)
                            // Save the user
                            UserSessionManager.setUserSession(config.getActivity(), facebookUser)
                            button.unregisterCallback(callbackManager)
                            config.getCallback().onLoginSuccess(facebookUser!!)

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    val parameters = Bundle()
                    parameters.putString(
                        "fields",
                        "id,first_name,last_name,email,gender,birthday"
                    ) // id,first_name,last_name,email,gender,birthday,cover,picture.type(large)
                    request.parameters = parameters

                    if(isUserConnected())
                        throwUserAlreadyConnectedFailure(LoginType.Facebook)
                    else
                        request.executeAsync()

                }

                override fun onCancel() {
                    Log.d("Facebook Login", "User cancelled the login process")
                    config.getCallback()
                        .onLoginFailure(EasyLoginException("User cancelled the login request", LoginType.Facebook))
                }

                override fun onError(e: FacebookException) {
                    config.getCallback().onLoginFailure(EasyLoginException(e.message!!, e, LoginType.Facebook))
                }
            })
        }
    }

    override fun signup() {

    }

    override fun logout(context: Context):Boolean {
        try
        {
            val preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            LoginManager.getInstance().logOut()
            editor.remove(Constants.USER_TYPE)
            editor.remove(Constants.USER_SESSION)
            editor.apply()
            return true
        }
        catch (e:Exception) {
            Log.e("FacebookLogin", e.message)
            return false
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun populateFacebookUser(`object`: JSONObject, accessToken: AccessToken): EasyFacebookUser? {
        var facebookUser: EasyFacebookUser? = EasyFacebookUser()
        facebookUser?.gender = -1
        facebookUser?.accessToken  = accessToken
        try {
            if (`object`.has(Constants.FacebookFields.EMAIL))
                facebookUser?.email = `object`.getString(Constants.FacebookFields.EMAIL)
            if (`object`.has(Constants.FacebookFields.BIRTHDAY))
                facebookUser?.birthday = `object`.getString(Constants.FacebookFields.BIRTHDAY)
            if (`object`.has(Constants.FacebookFields.GENDER)) {
                try {
                    val gender = Constants.Gender.valueOf(`object`.getString(Constants.FacebookFields.GENDER))
                    when (gender) {
                        Constants.Gender.male -> facebookUser?.gender = 0
                        Constants.Gender.female -> facebookUser?.gender = 1
                    }
                } catch (e: Exception) {
                    //if gender is not in the enum it is already set to unspecified value (-1)
                    Log.e("UserUtil", e.message)
                }

            }
            if (`object`.has(Constants.FacebookFields.LINK))
                facebookUser?.profileLink = `object`.getString(Constants.FacebookFields.LINK)
            if (`object`.has(Constants.FacebookFields.ID))
                facebookUser?.userId = `object`.getString(Constants.FacebookFields.ID)
            if (`object`.has(Constants.FacebookFields.NAME))
                facebookUser?.profileName = `object`.getString(Constants.FacebookFields.NAME)
            if (`object`.has(Constants.FacebookFields.FIRST_NAME))
                facebookUser?.firstName = `object`.getString(Constants.FacebookFields.FIRST_NAME)
            if (`object`.has(Constants.FacebookFields.MIDDLE_NAME))
                facebookUser?.middleName = `object`.getString(Constants.FacebookFields.MIDDLE_NAME)
            if (`object`.has(Constants.FacebookFields.LAST_NAME))
                facebookUser?.lastName = `object`.getString(Constants.FacebookFields.LAST_NAME)
        } catch (e: JSONException) {
            Log.e("UserUtil", e.message)
            facebookUser = null
        }

        return facebookUser
    }

    override fun registerSignInButton(button: View) {
        this.button = button as LoginButton
    }

    override fun unbind() {
        this.button?.let {
            it.unregisterCallback(callbackManager)
        }
    }
}