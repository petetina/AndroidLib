package antoinepetetin.fr.easylogin.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal class EasyGoogleUser : EasyUser(), Parcelable {

    var displayName: String? = null
    var photoUrl: String? = null
    var idToken: String? = null
}