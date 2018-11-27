package antoinepetetin.fr.pocandroidlibrary

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import antoinepetetin.fr.easylogin.user.EasyUser
import antoinepetetin.fr.pocandroidlibrary.databinding.ActivityUserConnectedBinding

class UserConnectedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_connected)

        val user = getIntent().getSerializableExtra("user") as EasyUser;

        val binding: ActivityUserConnectedBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_connected)

        binding.setVariable(BR.user, user)
        binding.executePendingBindings()

    }
}
