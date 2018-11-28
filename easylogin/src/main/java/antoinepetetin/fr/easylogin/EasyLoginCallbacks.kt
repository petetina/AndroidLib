package antoinepetetin.fr.easylogin

import antoinepetetin.fr.easylogin.user.EasyUser

internal interface EasyLoginCallbacks {

    fun unbindLoginComponents()

    fun onLoginSuccess(user: EasyUser)

    fun onLoginFailure(e: EasyLoginException)

    fun onLogout()

    fun doCustomLogin(): EasyUser?

    fun doCustomSignup(): EasyUser?
}