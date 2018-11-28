package antoinepetetin.fr.easylogin

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import antoinepetetin.fr.easylogin.loginProcess.CustomLogin
import antoinepetetin.fr.easylogin.loginProcess.FacebookLogin
import antoinepetetin.fr.easylogin.loginProcess.GoogleLogin
import antoinepetetin.fr.easylogin.user.EasyUserProperty
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class EasyLogin {

    private var config: EasyLoginConfig? = null

    companion object {
        internal var facebookInstance: FacebookLogin? = null
        internal var googleInstance: GoogleLogin? = null
        internal var customInstance: CustomLogin? = null

        fun isFacebookRequest(requestCode: Int): Boolean{
            return FacebookSdk.isFacebookRequestCode(requestCode)
        }

        fun isGoogleRequest(requestCode: Int): Boolean{
            return requestCode == Constants.GOOGLE_LOGIN_REQUEST
        }

    }

    internal constructor(activity: Activity, callback: EasyLoginCallbacks){
        config = EasyLoginConfig(activity, callback)
        var facebookId = activity
            .getPackageManager()
            .getApplicationInfo(activity.baseContext.getPackageName(), PackageManager.GET_META_DATA)
            .metaData
            .getString("facebook_app_id")

        if(facebookId == null)
        {
            throw java.lang.Exception("Please ensure that facebook_app_id is present in our manifest")
        }else{
            facebookId = facebookId.replace("string/","")
        }

        //Initialize facebook sdk
        FacebookSdk.setApplicationId(facebookId);
        FacebookSdk.sdkInitialize(activity.applicationContext);
        AppEventsLogger.activateApp(activity.application);
    }


    fun build(loginType: LoginType): EasyLoginInterface {

        when (loginType) {
            LoginType.Facebook -> {
                facebookInstance = FacebookLogin(config!!)
                return facebookInstance!!
            }
            LoginType.Google -> {
                googleInstance = GoogleLogin(config!!)
                return googleInstance!!
            }
            else ->{
                // To avoid null pointers
                customInstance = CustomLogin(config!!)
                return customInstance!!
            }

        }
    }

    fun buildCustomLogin(requiredFields: Array<EasyUserProperty>?): EasyLoginInterface{
        customInstance = CustomLogin(config!!, requiredFields)
        return customInstance!!
    }

    fun buildCustomLogin(): EasyLoginInterface{
        customInstance = CustomLogin(config!!)
        return customInstance!!
    }

    internal fun buildFacebookLogin(): FacebookLogin{
        facebookInstance = FacebookLogin(config!!)
        return facebookInstance!!
    }

    //Must call every disconnect authentification method (Facebook, Google, and Custom)
    fun logout(){
        facebookInstance?.let {
            it.logout(config!!.getActivity())
        }
        googleInstance?.let {
            it.logout(config!!.getActivity())
        }
        customInstance?.let {
            it.logout(config!!.getActivity())
        }
        config!!.getCallback().onLogout()
    }

    fun onFacebookResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookInstance?.let {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun onGoogleResult(requestCode: Int, resultCode: Int, data: Intent?) {
        googleInstance?.let {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }

}