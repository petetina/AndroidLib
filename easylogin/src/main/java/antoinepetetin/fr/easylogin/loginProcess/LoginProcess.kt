package antoinepetetin.fr.easylogin.loginProcess

import android.content.Context
import android.util.Log
import antoinepetetin.fr.easylogin.Constants
import antoinepetetin.fr.easylogin.EasyLoginConfig
import antoinepetetin.fr.easylogin.EasyLoginException
import antoinepetetin.fr.easylogin.LoginType
import antoinepetetin.fr.easylogin.user.UserSessionManager
import com.facebook.login.LoginManager

abstract class LoginProcess(var config: EasyLoginConfig) {

    fun isUserConnected(): Boolean{
        return UserSessionManager.isUserConnected(config.getActivity().applicationContext)
    }

    fun throwUserAlreadyConnectedFailure(loginType: LoginType){
        config.getCallback().onLoginFailure(EasyLoginException("User is already connected", loginType))
    }

    open fun logout(context: Context):Boolean{
        try
        {
            UserSessionManager.logout(context)
            LoginManager.getInstance().logOut()
            return true
        }
        catch (e:Exception) {
            Log.e("FacebookLogin", e.message)
            return false
        }
    }
}