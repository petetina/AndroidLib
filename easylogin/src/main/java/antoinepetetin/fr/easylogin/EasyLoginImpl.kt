package antoinepetetin.fr.easylogin

import android.app.Activity
import android.content.Intent
import antoinepetetin.fr.easylogin.loginProcess.CustomLogin
import antoinepetetin.fr.easylogin.loginProcess.FacebookLogin
import antoinepetetin.fr.easylogin.loginProcess.GoogleLogin
import antoinepetetin.fr.easylogin.user.EasyUserProperty

class EasyLoginImpl {

    var config: EasyLoginConfig? = null
    companion object {
        var facebookInstance: FacebookLogin? = null
    }

    constructor(activity: Activity, callback: EasyLoginCallbacks){
        config = EasyLoginConfig(activity, callback)
    }


    fun build(loginType: LoginType): EasyLogin {

        when (loginType) {
            LoginType.Facebook -> {
                facebookInstance = FacebookLogin(config!!)
                return facebookInstance!!
            }
            LoginType.Google -> return GoogleLogin(config!!)
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

}