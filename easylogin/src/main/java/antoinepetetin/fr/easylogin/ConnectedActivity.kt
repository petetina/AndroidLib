package antoinepetetin.fr.easylogin

import android.os.Bundle
import android.widget.Toast
import antoinepetetin.fr.easylogin.dialog.LoginDialog
import antoinepetetin.fr.easylogin.user.EasyUser
import antoinepetetin.fr.easylogin.user.UserSessionManager

open class ConnectedActivity: EasyLoginActivity(), ConnectedActivityInterface{
    var dialog: LoginDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //If user is not connected, show dialog
        if(!UserSessionManager.isUserConnected(applicationContext)){
            showDialog()
        }else
            doOnCreate(UserSessionManager.getCurrentUser(applicationContext)!!)

    }

    private fun showDialog(){
        if(dialog == null){
            dialog = LoginDialog(this)
        }
        dialog!!.show()
    }

    //Do all stuff here when user is connected
    override fun doOnCreate(user: EasyUser){

    }

    override fun onLoginSuccess(user: EasyUser) {
        super.onLoginSuccess(user)
        dialog!!.dismiss()
        doOnCreate(user)
        Toast.makeText(this, "User " + user.firstName + " " + user.lastName, Toast.LENGTH_LONG).show()
    }

    override fun onLoginFailure(e: EasyLoginException) {
        super.onLoginFailure(e)
        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        if(e.isCancelled)
            finish()
    }

    override fun onLogout() {
        finish()
    }
}