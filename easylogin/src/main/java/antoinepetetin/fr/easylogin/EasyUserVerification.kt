package antoinepetetin.fr.easylogin

import android.util.Patterns

abstract class EasyUserVerification {
    companion object {
        fun checkEmail(email: String?): Boolean{
            return email.orEmpty().isNotEmpty() &&
                    Patterns.EMAIL_ADDRESS.matcher(email!!.trim()).matches();
        }

    }
}