package antoinepetetin.fr.easylogin.component

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import antoinepetetin.fr.easylogin.EasyLoginCallbacks
import antoinepetetin.fr.easylogin.EasyLogin
import antoinepetetin.fr.easylogin.R
import antoinepetetin.fr.easylogin.user.EasyUserProperty

class SignInButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private var view: View? = null

    init {
        view = LayoutInflater.from(context)
            .inflate(R.layout.signin_button, this, true)
            //.findViewById<Button>(R.id.signInButton).

        view!!.findViewById<Button>(R.id.mySignInButton).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Call login automatically
                EasyLogin(context as Activity,context as EasyLoginCallbacks).buildCustomLogin(arrayOf(EasyUserProperty.EMAIL)).login()
            }
        })

        orientation = VERTICAL
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        view!!.findViewById<Button>(R.id.mySignInButton).callOnClick()

    }
}