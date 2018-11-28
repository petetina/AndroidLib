package antoinepetetin.fr.easylogin.component

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import antoinepetetin.fr.easylogin.*
import com.facebook.login.widget.LoginButton
import com.google.android.gms.common.SignInButton

class GoogleSignInButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var view: View? = null

    init {
        view = LayoutInflater.from(context)
            .inflate(R.layout.google_signin_button, this, true)

        var easyLogin = EasyLogin(context as Activity,context as EasyLoginCallbacks).build(LoginType.Google)

        //Register the button to our library
        (context as EasyLoginActivity).registerSignInComponent(view!!.findViewById<SignInButton>(R.id.google_login_button), LoginType.Google)

        view!!.findViewById<SignInButton>(R.id.google_login_button).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Call login automatically
                EasyLogin(context as Activity,context as EasyLoginCallbacks).build(LoginType.Google).login()
            }
        })

        orientation = VERTICAL
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        view!!.findViewById<SignInButton>(R.id.google_login_button).callOnClick()
    }
}