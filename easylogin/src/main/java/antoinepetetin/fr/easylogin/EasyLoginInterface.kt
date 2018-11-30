package antoinepetetin.fr.easylogin

import android.content.Context
import android.content.Intent


/**
 * A login screen that offers login via email/password.
 */
interface EasyLoginInterface{

    fun login()

    fun signup()

    fun logout(context: Context): Boolean

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}
