package antoinepetetin.fr.pocandroidlibrary

import android.os.Bundle
import android.widget.Toast
import antoinepetetin.fr.easylogin.EasyLoginActivity
import antoinepetetin.fr.easylogin.EasyLoginException
import antoinepetetin.fr.easylogin.R
import antoinepetetin.fr.easylogin.user.EasyUser
import kotlinx.android.synthetic.main.easyloginactivity_layout.*
import java.util.*

class ConnectionActivity : EasyLoginActivity()   {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.easyloginactivity_layout)
    }

    override fun onLoginSuccess(user: EasyUser) {
        super.onLoginSuccess(user)
        finish()

    }

    override fun onLoginFailure(e: EasyLoginException) {
        super.onLoginFailure(e)
        Toast.makeText(this,e.message, Toast.LENGTH_LONG).show()
    }

    override fun onLogout() {
        super.onLogout()
        Toast.makeText(this, "Logged out !", Toast.LENGTH_LONG).show()
    }

    override fun doCustomLogin(): EasyUser? {

        /*
        val email = loginLayout.getEmail() //Get the text from our custom layout :) amazing !!!
        val password = loginLayout.getPassword() //Get the text from our custom layout :) amazing !!!

        return loginUser(email, password)
        */
        return null
    }

    //My private function that check if user is connected, like API Call :)
    private fun loginUser(email: String, password: String): EasyUser?{
        val random = Random()
        var result: EasyUser? = null
        if(random.nextBoolean())
        {
            val user = EasyUser()
            user.userId = "1"
            user.email = email
            user.firstName = "Antoine"
            user.lastName = "Petetin"

            result = user
        }else
            result = null

        return result
    }
}