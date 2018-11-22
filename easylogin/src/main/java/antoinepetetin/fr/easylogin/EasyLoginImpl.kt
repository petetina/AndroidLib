package antoinepetetin.fr.easylogin

import android.app.Activity
import antoinepetetin.fr.easylogin.loginProcess.CustomLogin
import antoinepetetin.fr.easylogin.loginProcess.FacebookLogin
import antoinepetetin.fr.easylogin.loginProcess.GoogleLogin
import antoinepetetin.fr.easylogin.user.EasyUserProperty

class EasyLoginImpl {

    var config: EasyLoginConfig? = null

    constructor(activity: Activity, callback: EasyLoginCallbacks){
        config = EasyLoginConfig(activity, callback)
    }


    fun build(loginType: LoginType): EasyLogin {

        when (loginType) {
            LoginType.Facebook -> return FacebookLogin(config!!)
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

}