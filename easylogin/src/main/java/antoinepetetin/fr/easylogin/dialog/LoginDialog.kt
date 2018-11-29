package antoinepetetin.fr.easylogin.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.component.LoginLayout
import kotlinx.android.synthetic.main.easyloginactivity_layout.*


class LoginDialog(var activity: EasyLoginActivity): Dialog(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
    private var easyLogin: EasyLogin? = null
    var dialog: Dialog? = null
    var loginLayout: LoginLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        //Init our library with activity and callback
        easyLogin = EasyLogin(activity,activity)

        setContentView(R.layout.easyloginactivity_layout)
        loginLayout = LoginLayout(activity)
        loginLayout!!.addCustomLogin()
        loginLayout!!.addFacebookButton()
        loginLayout!!.addGoogleButton()

        easyLoginActivityLinearLayout.addView(loginLayout)

    }

    fun getEmail():String{
        if(loginLayout != null){
            return loginLayout!!.getEmail()
        }else
            return ""
    }

    fun getPassword():String{
        if(loginLayout != null){
            return loginLayout!!.getPassword()
        }else
            return ""
    }

    override fun onBackPressed() {
        activity.onLoginFailure(EasyLoginException("Login cancelled", LoginType.CustomLogin, isCancelled = true))
    }
}