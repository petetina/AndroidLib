package antoinepetetin.fr.pocandroidlibrary

import android.databinding.DataBindingUtil
import android.os.Bundle
import antoinepetetin.fr.easylogin.ConnectedActivity
import antoinepetetin.fr.easylogin.user.EasyUser
import antoinepetetin.fr.pocandroidlibrary.databinding.ActivityUserConnectedBinding
import java.util.*

class UserConnectedActivity : ConnectedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //do nothing here, do all stuff on doOnCreate method !!

    }

    //Do all stuff here when user is connected
    override fun doOnCreate(user: EasyUser) {
        super.doOnCreate(user)

        val binding: ActivityUserConnectedBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_connected)

        binding.setVariable(BR.user, user)
        binding.executePendingBindings()

    }

    override fun onLogout() {
        super.onLogout()
        finish()
    }


    override fun doCustomLogin(): EasyUser? {
        val email = dialog!!.getEmail() //Get the text from our custom layout :) amazing !!!
        val password = dialog!!.getPassword() //Get the text from our custom layout :) amazing !!!

        return loginUser(email, password)
    }

    //My private function that check if user is connected, like API Call :)
    private fun loginUser(email: String, password: String): EasyUser?{
        val user = EasyUser()
        user.userId = "1"
        user.email = email
        user.firstName = "Antoine"
        user.lastName = "Petetin"

        return user
    }
}
