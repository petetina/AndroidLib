package antoinepetetin.fr.easylogin.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import antoinepetetin.fr.easylogin.R

class FacebookSignInButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.facebook_signin_button, this, true)
        orientation = VERTICAL
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        
    }
}