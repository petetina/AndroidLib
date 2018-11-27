package antoinepetetin.fr.easylogin

internal abstract class Constants {

    companion object {

        val LAYOUT_ID = "login_layout_id"
        val FATAL = 1001
        val LOGIN_THEME = "login_page_theme"
        private val LOGIN_LISTENER = "login_listener_instance"
        val GOOGLE_PROGRESS_DIALOG_MESSAGE = "Getting User Info…"

        val APPLOGO = "studios.codelight.applogo"
        val USER = "studios.codelight.user"
        val CUSTOMLOGINFLAG = "studios.codelight.custom_login_flag"
        val FACEBOOKFLAG = "studios.codelight.facebook_flag"
        val FACEBOOKPERMISSIONS = "studios.codelight.facebook_permissions"
        //public static final String TWITTERFLAG = "studios.codelight.twitter_flag";
        val GOOGLEFLAG = "studios.codelight.google_flag"
        val FACEBOOKID = "studios.codelight.facebook_id"
        val CUSTOMUSERFLAG = "studios.codelight.custom_user"
        val CUSTOMLOGINTYPE = "studios.codelight.custom_login_type"

        val USER_TYPE = "user_type"

        val GOOGLE_LOGIN_REQUEST = 322
        val CUSTOM_LOGIN_REQUEST = 323
        val CUSTOM_SIGNUP_REQUEST = 324
        val LOGIN_REQUEST = 5

        val USER_SESSION = "user_session_key"
        val USER_PREFS = "codelight_studios_user_prefs"
        val DEFAULT_SESSION_VALUE = "No logged in user"
    }
    class FacebookFields {
        companion object {
            val EMAIL = "email"
            val ID = "id"
            val BIRTHDAY = "birthday"
            val GENDER = "gender"
            val FIRST_NAME = "first_name"
            val MIDDLE_NAME = "middle_name"
            val LAST_NAME = "last_name"
            val NAME = "name"
            val LINK = "link"
        }
    }

    enum class Gender {
        male, female
    }

}