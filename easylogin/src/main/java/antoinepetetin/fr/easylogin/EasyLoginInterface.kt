package antoinepetetin.fr.easylogin

import android.content.Context
import android.content.Intent
import android.view.View


/**
 * A login screen that offers login via email/password.
 */
interface EasyLoginInterface{

    fun registerSignInButton(button: View)

    fun unbind()

    fun login()

    fun signup()

    fun logout(context: Context): Boolean

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}
