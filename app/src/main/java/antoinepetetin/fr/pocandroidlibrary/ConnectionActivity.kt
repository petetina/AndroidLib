package antoinepetetin.fr.pocandroidlibrary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import antoinepetetin.fr.easylogin.EasyLoginActivity
import antoinepetetin.fr.easylogin.EasyLoginException
import antoinepetetin.fr.easylogin.user.EasyUser
import kotlinx.android.synthetic.main.activity_connection.*


class ConnectionActivity : EasyLoginActivity()   {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection)

    }

    override fun onLoginSuccess(user: EasyUser) {
        super.onLoginSuccess(user)
        finish()

    }

    override fun onLoginFailure(e: EasyLoginException) {
        super.onLoginFailure(e)
        Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()
    }

    override fun doCustomLogin(): EasyUser? {

        Log.e("doCustomLogin","Running")
        //For example create an user in local DB
        //And then create an EasyUser with info
        var user = EasyUser()
        user.userId = "1" //you can get id from the user in DB
        user.email = emailPasswordView.email!!.text.toString() //Get the text from our custom layout :) amazing !!!

        return user
    }
}