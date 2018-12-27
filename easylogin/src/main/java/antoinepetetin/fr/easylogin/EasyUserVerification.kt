package antoinepetetin.fr.easylogin

import android.util.Patterns
import java.util.regex.Pattern

abstract class EasyUserVerification {
    companion object {
        val EMAIL_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"

        fun checkEmail(email: String?): Boolean{
            Patterns.EMAIL_ADDRESS
            return !email.isNullOrBlank() &&
                    Pattern.compile(EMAIL_PATTERN).matcher(email).matches()
        }

    }
}