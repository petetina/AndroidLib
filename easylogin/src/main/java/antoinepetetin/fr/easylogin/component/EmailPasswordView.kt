package antoinepetetin.fr.easylogin.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import antoinepetetin.fr.easylogin.R
import android.content.ComponentName
import android.support.v4.content.ContextCompat.getSystemService
import android.app.ActivityManager
import android.util.Log
import antoinepetetin.fr.easylogin.EasyLoginActivity


class EmailPasswordView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private var view: View? = null
    var email: EditText? = null
        get() {
            view?.let {
                return it.findViewById(R.id.email)
            }
        }
    var password: EditText? = null
        get() {
            view?.let {
                return it.findViewById(R.id.password)
            }
        }

    init {

        view = LayoutInflater.from(context)
            .inflate(R.layout.email_password_login_layout, this, true)

        orientation = VERTICAL

    }


}