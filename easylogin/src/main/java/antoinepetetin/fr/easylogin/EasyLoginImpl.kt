package antoinepetetin.fr.easylogin

import android.app.Activity
import android.content.Intent
import antoinepetetin.fr.easylogin.loginProcess.CustomLogin
import antoinepetetin.fr.easylogin.loginProcess.FacebookLogin
import antoinepetetin.fr.easylogin.loginProcess.GoogleLogin
import antoinepetetin.fr.easylogin.user.EasyUserProperty
import com.facebook.FacebookSdk

class EasyLoginImpl {

    private var isConnected = false
    private var config: EasyLoginConfig? = null

    companion object {
        private var facebookInstance: FacebookLogin? = null
        private var googleInstance: GoogleLogin? = null

        fun isFacebookRequest(requestCode: Int): Boolean{
            return FacebookSdk.isFacebookRequestCode(requestCode)
        }

        fun isGoogleRequest(requestCode: Int): Boolean{
            return requestCode == Constants.GOOGLE_LOGIN_REQUEST
        }

    }

    internal constructor(activity: Activity, callback: EasyLoginCallbacks){
        config = EasyLoginConfig(activity, callback)
    }


    fun build(loginType: LoginType): EasyLogin {

        when (loginType) {
            LoginType.Facebook -> {
                facebookInstance = FacebookLogin(config!!)
                return facebookInstance!!
            }
            LoginType.Google -> {
                googleInstance = GoogleLogin(config!!)
                return googleInstance!!
            }
            else ->
                // To avoid null pointers
                return CustomLogin(config!!)
        }
    }

    fun buildCustomLogin(requiredFields: Array<EasyUserProperty>?): EasyLogin{
        return CustomLogin(config!!, requiredFields)
    }

    fun buildCustomLogin(): EasyLogin{
        return CustomLogin(config!!)
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