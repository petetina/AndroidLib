package antoinepetetin.fr.easylogin.component

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.user.UserSessionManager
import com.facebook.login.widget.LoginButton

class FacebookSignInButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var view: View? = null

    init {


        view = LayoutInflater.from(context)
            .inflate(R.layout.facebook_signin_button, this, true)

        var easyLogin = EasyLogin(context as Activity,context as EasyLoginCallbacks).buildFacebookLogin()

        //Register the button to our library
        (context as EasyLoginActivity).registerSignInComponent(view!!.findViewById<LoginButton>(R.id.facebook_login_button), LoginType.Facebook)

        if(!UserSessionManager.isUserConnected(context))
            easyLogin.registerCallback(view!!.findViewById<LoginButton>(R.id.facebook_login_button))
        else
            easyLogin.throwUserAlreadyConnectedFailure(LoginType.Facebook)


        orientation = VERTICAL

    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        view!!.findViewById<Button>(R.id.facebook_login_button).callOnClick()
    }
}