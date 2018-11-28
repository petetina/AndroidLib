package antoinepetetin.fr.pocandroidlibrary

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import antoinepetetin.fr.easylogin.ConnectedIntent
import antoinepetetin.fr.easylogin.user.UserSessionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paymentButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.paymentButton -> {
                var intent = ConnectedIntent(this, ConnectionActivity::class.java, UserConnectedActivity::class.java)
                intent.start()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 555){
            Toast.makeText(this,UserSessionManager.getCurrentUser(this).toString(), Toast.LENGTH_LONG).show()

        }
    }


}
