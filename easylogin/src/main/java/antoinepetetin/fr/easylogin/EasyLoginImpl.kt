package antoinepetetin.fr.easylogin

import antoinepetetin.fr.easylogin.loginProcess.CustomLogin
import antoinepetetin.fr.easylogin.loginProcess.FacebookLogin
import antoinepetetin.fr.easylogin.loginProcess.GoogleLogin
import antoinepetetin.fr.easylogin.user.EasyUserProperty

abstract class EasyLoginImpl {
    companion object {
        fun build(loginType: LoginType): EasyLogin {
            when (loginType) {
                LoginType.Facebook -> return FacebookLogin()
                LoginType.Google -> return GoogleLogin()
                else ->
                    // To avoid null pointers
                    return CustomLogin()
            }
        }

        fun buildCustomLogin(requiredFields: Array<EasyUserProperty>?): EasyLogin{
            return CustomLogin(requiredFields)
        }

        fun buildCustomLogin(): EasyLogin{
            return CustomLogin()
        }
    }
}