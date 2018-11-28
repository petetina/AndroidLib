package antoinepetetin.fr.pocandroidlibrary

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import antoinepetetin.fr.easylogin.EasyLoginActivity
import antoinepetetin.fr.easylogin.user.EasyUser
import antoinepetetin.fr.pocandroidlibrary.databinding.ActivityUserConnectedBinding

class UserConnectedActivity : EasyLoginActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_connected)

        /*val user: EasyUser = intent.getParcelableExtra("user") as EasyUser;

        val binding: ActivityUserConnectedBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_connected)

        binding.setVariable(BR.user, user)
        binding.executePendingBindings()
*/
    }

    override fun onLogout() {
        super.onLogout()
        finish()
    }
}
