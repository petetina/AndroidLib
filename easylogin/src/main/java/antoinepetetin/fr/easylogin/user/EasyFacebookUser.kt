package antoinepetetin.fr.easylogin.user

import android.os.Parcelable
import com.facebook.AccessToken
import kotlinx.android.parcel.Parcelize

@Parcelize
internal open class EasyFacebookUser(var profileName: String? = null,
                                     var accessToken: AccessToken? = null) : EasyUser(), Parcelable {

    /*
constructor(userId: String? = null,
            username: String? = null,
            firstName: String? = null,
            middleName: String? = null,
            lastName: String? = null,
            email: String? = null,
            birthday: String? = null,
            gender: Int = 0,
            profileLink: String? = null):this()//userId, username, firstName, middleName, lastName, email,birthday, gender, profileLink)
*/
}