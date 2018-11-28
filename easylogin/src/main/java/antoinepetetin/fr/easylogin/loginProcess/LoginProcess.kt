package antoinepetetin.fr.easylogin.loginProcess

import android.content.Context
import android.content.Intent
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.user.UserSessionManager

internal abstract class LoginProcess(var config: EasyLoginConfig) {

    fun isUserConnected(): Boolean{
        return UserSessionManager.isUserConnected(config.getActivity().applicationContext)
    }

    fun throwUserAlreadyConnectedFailure(loginType: LoginType){
        config.getCallback().onLoginFailure(EasyLoginException("User is already connected", loginType))
    }
}