package antoinepetetin.fr.pocandroidlibrary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import antoinepetetin.fr.easylogin.EasyLoginActivity
import antoinepetetin.fr.easylogin.EasyLoginException
import antoinepetetin.fr.easylogin.user.EasyUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : EasyLoginActivity()   {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onLoginSuccess(user: EasyUser) {
        super.onLoginSuccess(user)
        Log.e("user",user.toString())
        Toast.makeText(this,"Well done :) You are connected !", Toast.LENGTH_LONG).show()

        var intent = Intent(this,UserConnectedActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)

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