package antoinepetetin.fr.easylogin.loginProcess

import android.content.Context
import android.content.Intent
import android.util.Log
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.user.UserSessionManager
import com.facebook.login.LoginManager

internal abstract class LoginProcess(var config: EasyLoginConfig) {

    fun isUserConnected(): Boolean{
        return UserSessionManager.isUserConnected(config.getActivity().applicationContext)
    }

    fun throwUserAlreadyConnectedFailure(loginType: LoginType){
        config.getCallback().onLoginFailure(EasyLoginException("User is already connected", loginType))
    }

    open fun logout(context: Context):Boolean{
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
}