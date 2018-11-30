package antoinepetetin.fr.easylogin

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import antoinepetetin.fr.easylogin.user.EasyUser

open class EasyLoginActivity: AppCompatActivity(), EasyLoginCallbacks{
    private var easyLogin: EasyLogin? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Init our library with activity and callback
        easyLogin = EasyLogin(this,this)
    }

    //Cette méthode est déclenchée en cas de succès du login
    override fun onLoginSuccess(user: EasyUser){
    }

    //Cette méthode est déclenchée en cas de succès d'échec d'authentification
    override fun onLoginFailure(e: EasyLoginException) {
    }

    override fun onLogout() {
    }

    //Cette méthode sert à décrire de quoi est composé notre EasyUser
    //A notre charge de créer un easyuser en fonction de nos champs
    override fun doCustomLogin(): EasyUser? {
        return null
    }

    override fun doCustomSignup(): EasyUser {
        TODO("not implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(EasyLogin.isFacebookRequest(requestCode))
            easyLogin!!.onFacebookResult(requestCode, resultCode, data)
        else if(EasyLogin.isGoogleRequest(requestCode))
            easyLogin!!.onGoogleResult(requestCode, resultCode, data)

    }
}