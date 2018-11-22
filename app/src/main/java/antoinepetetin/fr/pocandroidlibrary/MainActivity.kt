package antoinepetetin.fr.pocandroidlibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import antoinepetetin.fr.easylogin.EasyLoginCallbacks
import antoinepetetin.fr.easylogin.EasyLoginException
import antoinepetetin.fr.easylogin.EasyLoginImpl
import antoinepetetin.fr.easylogin.user.EasyUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), EasyLoginCallbacks{

    var easyLogin: EasyLoginImpl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init our library with activity and callback
        easyLogin = EasyLoginImpl(this,this)
    }

    //Cette méthode est déclenchée en cas de succès du login
    override fun onLoginSuccess(user: EasyUser) {
        Log.e("onLoginSuccess",user.toString())
    }

    //Cette méthode est déclenchée en cas de succès d'échec d'authentification
    override fun onLoginFailure(e: EasyLoginException) {
        Log.e("onLoginFailure",e.message)
    }

    //Cette méthode sert à décrire de quoi est composé notre EasyUser
    //A notre charge de créer un easyuser en fonction de nos champs
    override fun doCustomLogin(): EasyUser? {

        Log.e("doCustomLogin","Running")
        //For example create an user in local DB
        //And then create an EasyUser with info
        var user = EasyUser()
        user.userId = "1" //you can get id from the user in DB
        user.email = emailPasswordView.email!!.text.toString() //Get the text from our custom layout :) amazing !!!

        return user
    }

    override fun doCustomSignup(): EasyUser {
        TODO("not implemented")
    }
}