package antoinepetetin.fr.easylogin.user

import com.facebook.AccessToken


internal class EasyFacebookUser : EasyUser() {
    var profileName: String? = null
    var accessToken: AccessToken? = null
}