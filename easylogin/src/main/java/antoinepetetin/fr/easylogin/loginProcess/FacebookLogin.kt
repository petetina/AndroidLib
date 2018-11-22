package antoinepetetin.fr.easylogin.loginProcess

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.R
import antoinepetetin.fr.easylogin.user.EasyFacebookUser
import antoinepetetin.fr.easylogin.user.UserSessionManager
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import org.json.JSONException
import org.json.JSONObject


class FacebookLogin: EasyLogin() {

    private val callbackManager:CallbackManager
    init{
        //Facebook login callback
        callbackManager = CallbackManager.Factory.create()
    }

    override fun login(config: EasyLoginConfig) {
        val activity = config.getActivity()
        val callback = config.getCallback()
        val progress = ProgressDialog.show(activity, "", activity.getString(R.string.logging_holder), true)
        var permissions = config.getFacebookPermissions()
        if (permissions == null)
        {
            permissions = EasyLoginConfig.getDefaultFacebookPermissions()
        }
        LoginManager.getInstance().logInWithReadPermissions(activity, permissions)
        LoginManager.getInstance().registerCallback(callbackManager, object:FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult:LoginResult) {
                progress.setMessage(activity.getString(R.string.getting_data))
                val request = GraphRequest.newMeRequest(loginResult.accessToken
                ) { jsonObject, _ ->
                    progress.dismiss()
                    val facebookUser = populateFacebookUser(jsonObject, loginResult.accessToken)
                    // Save the user
                    UserSessionManager.setUserSession(activity, facebookUser)
                    callback.onLoginSuccess(facebookUser!!)
                }
                request.executeAsync()
            }

            override fun onCancel() {
                progress.dismiss()
                Log.d("Facebook Login", "User cancelled the login process")
                callback.onLoginFailure(EasyLoginException("User cancelled the login request", LoginType.Facebook))
            }

            override fun onError(e:FacebookException) {
                progress.dismiss()
                callback.onLoginFailure(EasyLoginException(e.message!!, e, LoginType.Facebook))
            }
        })
    }

    override fun signup(config:EasyLoginConfig) {

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

    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent, config:EasyLoginConfig) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun populateFacebookUser(`object`: JSONObject, accessToken: AccessToken): EasyFacebookUser? {
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
}