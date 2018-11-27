package antoinepetetin.fr.easylogin.loginProcess

import android.content.Context
import android.content.Intent
import antoinepetetin.fr.easylogin.EasyLoginCallbacks
import antoinepetetin.fr.easylogin.EasyLoginConfig
import antoinepetetin.fr.easylogin.EasyLoginException
import antoinepetetin.fr.easylogin.EasyLoginInterface
import antoinepetetin.fr.easylogin.user.UserSessionManager

internal abstract class LoginProcess(var config: EasyLoginConfig) : EasyLoginInterface() {

    override fun login() {
        if(UserSessionManager.isUserConnected(config.getActivity().applicationContext))
        {
            (config.getActivity() as EasyLoginCallbacks).onLoginFailure(EasyLoginException("User is already connected"))
            // stop login process if user is already connected !
            return
        }
    }

    abstract override fun signup()

    abstract override fun logout(context: Context): Boolean

    abstract override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}