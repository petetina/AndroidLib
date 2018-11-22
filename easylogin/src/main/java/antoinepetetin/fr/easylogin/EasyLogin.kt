package antoinepetetin.fr.easylogin

import android.content.Context
import android.content.Intent



/**
 * A login screen that offers login via email/password.
 */
abstract class EasyLogin{
    abstract fun login(config: EasyLoginConfig)

    abstract fun signup(config: EasyLoginConfig)

    abstract fun logout(context: Context): Boolean

    abstract fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent, config: EasyLoginConfig)
}
