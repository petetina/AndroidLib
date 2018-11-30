package antoinepetetin.fr.easylogin

import antoinepetetin.fr.easylogin.user.EasyUser

interface EasyLoginCallbacks {

    fun onLoginSuccess(user: EasyUser)

    fun onLoginFailure(e: EasyLoginException)

    fun onLogout()

    fun doCustomLogin(): EasyUser?

    fun doCustomSignup(): EasyUser?
}