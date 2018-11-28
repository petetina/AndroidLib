package antoinepetetin.fr.easylogin

import android.app.Activity
import android.content.Context
import android.content.Intent
import antoinepetetin.fr.easylogin.user.UserSessionManager

class ConnectedIntent(var context: Context, var connectionActivity: Class<out EasyLoginActivity>, var connectedActivityToStart: Class<out Activity>) {

    fun start(){
        if(UserSessionManager.isUserConnected(context)) {
            var intent = Intent(context, connectedActivityToStart)
            context.startActivity(intent)
        }else{
            var intent = Intent(context, connectionActivity)
            context.startActivity(intent)
        }

    }
}