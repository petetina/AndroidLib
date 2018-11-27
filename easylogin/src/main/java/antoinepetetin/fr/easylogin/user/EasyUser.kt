package antoinepetetin.fr.easylogin.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
open class EasyUser(var userId: String? = null,
                    var username: String? = null,
                    var firstName: String? = null,
                    var middleName: String? = null,
                    var lastName: String? = null,
                    var email: String? = null,
                    var birthday: String? = null,
                    var gender: Int = 0,
                    var profileLink: String? = null
                    ): Parcelable {

    override fun toString(): String {
        return "SmartUser{" +
                "userId='" + userId + '\''.toString() +
                ", username='" + username + '\''.toString() +
                ", firstName='" + firstName + '\''.toString() +
                ", middleName='" + middleName + '\''.toString() +
                ", lastName='" + lastName + '\''.toString() +
                ", email='" + email + '\''.toString() +
                ", birthday='" + birthday + '\''.toString() +
                ", gender=" + gender +
                ", profileLink='" + profileLink + '\''.toString() +
                '}'.toString()
    }

    private fun formatString(text: String?): String?{
        if(text == null)
            return null
        else
            return text.orEmpty()
    }

}
/*
@Parcelize
open class EasyUser(var userId: String?): Parcelable {


    var userId: String? = null
        get
        set(varue) {field = formatString(varue)}
    var username: String? = null
        get
        set(varue) { field = formatString((varue))}
    var firstName: String? = null
        get
        set(varue) {field = formatString(varue)}
    var middleName: String? = null
        get
        set(varue) {field = formatString(varue)}
    var lastName: String? = null
        get
        set(varue) {field = formatString(varue)}
    var email: String? = null
        get
        set(varue) {field = formatString(varue)}
    var birthday: String? = null
        get
        set(varue) {field = formatString(varue)}
    var gender: Int = 0
        get
        set
    var profileLink: String? = null
        get
        set(varue){field = formatString(varue)}

    override fun toString(): String {
        return "SmartUser{" +
                "userId='" + userId + '\''.toString() +
                ", username='" + username + '\''.toString() +
                ", firstName='" + firstName + '\''.toString() +
                ", middleName='" + middleName + '\''.toString() +
                ", lastName='" + lastName + '\''.toString() +
                ", email='" + email + '\''.toString() +
                ", birthday='" + birthday + '\''.toString() +
                ", gender=" + gender +
                ", profileLink='" + profileLink + '\''.toString() +
                '}'.toString()
    }

    private fun formatString(text: String?): String?{
        if(text == null)
            return null
        else
            return text.orEmpty()
    }

}
        */