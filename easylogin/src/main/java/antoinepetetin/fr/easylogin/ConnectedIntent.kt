package antoinepetetin.fr.easylogin

import android.app.Activity
import android.content.Intent
import antoinepetetin.fr.easylogin.user.UserSessionManager

class ConnectedIntent(var context: Activity, var connectionActivity: Class<out EasyLoginActivity>, var connectedActivityToStart: Class<out Activity>) {

    fun start(){
        if(UserSessionManager.isUserConnected(context)) {
            var intent = Intent(context, connectedActivityToStart)
            context.startActivity(intent)
        }else{
            var intent = Intent(context, connectionActivity)
            context.startActivityForResult(intent, EasyLoginActivity.EASY_LOGIN_REQUEST_CODE)
        }

    }
}