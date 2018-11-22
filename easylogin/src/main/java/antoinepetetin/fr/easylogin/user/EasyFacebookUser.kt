package antoinepetetin.fr.easylogin.user

import com.facebook.AccessToken


class EasyFacebookUser : EasyUser() {
    var profileName: String? = null
    var accessToken: AccessToken? = null
}