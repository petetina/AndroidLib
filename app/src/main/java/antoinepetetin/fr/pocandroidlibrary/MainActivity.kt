package antoinepetetin.fr.pocandroidlibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.user.EasyUser
import kotlinx.android.synthetic.main.activity_main.*


/* Without library
class MainActivity : AppCompatActivity() {
    val mockData: List<out FakeDataModel> = listOf(
        FakeDataModel("azerty"),
        FakeDataModel("plop"),
        FakeDataModel("hello")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(mockData, R.layout.layout_item)
    }
}
*/

/* test custom adapter
// With our custom library
class MainActivity : AppCompatActivity() {

    val mockData: List<out FakeDataModel> = listOf(
        FakeDataModel("azerty"),
        FakeDataModel("plop"),
        FakeDataModel("hello")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(mockData, R.layout.layout_item)
    }
}
        */

class MainActivity : AppCompatActivity(), EasyLoginCallbacks, View.OnClickListener{

    var config: EasyLoginConfig? = null
    var easyLogin: EasyLogin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init config
        config = EasyLoginConfig(this /* Context */, this /* SmartLoginCallbacks */)

        //handlers
        signInButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            signInButton.id -> {
                easyLogin = EasyLoginImpl.buildCustomLogin()
                easyLogin?.let {
                    it.login(config!!)
                }
            }
            R.id.facebook_login_button -> {
                // TODO
            }
            R.id.google_login_button -> {
                // TODO
            }
        }
    }


    override fun onLoginSuccess(user: EasyUser) {
        Log.e("onLoginSuccess",user.toString())
    }

    override fun onLoginFailure(e: EasyLoginException) {
        Log.e("onLoginFailure",e.message)
    }

    override fun doCustomLogin(): EasyUser? {

        Log.e("doCustomLogin","Running")
        //For example create an user in local DB
        //And then create an EasyUser with info
        var user = EasyUser()
        user.userId = "1" //you can get id from the user in DB
        user.email = email.text.toString()

        //Check user
        if(EasyUserVerification.checkEmail(user.email))
            return user
        else
            return null
    }

    override fun doCustomSignup(): EasyUser {
        TODO("not implemented")
    }
}