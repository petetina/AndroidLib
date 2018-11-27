package antoinepetetin.fr.easylogin.component

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import antoinepetetin.fr.easylogin.EasyLoginCallbacks
import antoinepetetin.fr.easylogin.EasyLoginImpl
import antoinepetetin.fr.easylogin.LoginType
import antoinepetetin.fr.easylogin.R

class GoogleSignInButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private var view: View? = null

    init {
        view = LayoutInflater.from(context)
            .inflate(R.layout.google_signin_button, this, true)

        view!!.findViewById<Button>(R.id.google_login_button).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Call login automatically
                EasyLoginImpl(context as Activity,context as EasyLoginCallbacks).build(LoginType.Google).login()
            }
        })

        orientation = VERTICAL
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        view!!.findViewById<Button>(R.id.google_login_button).callOnClick()
    }
}