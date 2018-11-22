package antoinepetetin.fr.easylogin.user


open class EasyUser {

    var userId: String? = null
        set(value) {field = formatString(value)}
    var username: String? = null
        set(value) { field = formatString((value))}
    var firstName: String? = null
        set(value) {field = formatString(value)}
    var middleName: String? = null
        set(value) {field = formatString(value)}
    var lastName: String? = null
        set(value) {field = formatString(value)}
    var email: String? = null
        set(value) {field = formatString(value)}
    var birthday: String? = null
        set(value) {field = formatString(value)}
    var gender: Int = 0
    var profileLink: String? = null
        set(value){field = formatString(value)}

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