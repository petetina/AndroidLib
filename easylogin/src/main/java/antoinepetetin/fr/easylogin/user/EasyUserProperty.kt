package antoinepetetin.fr.easylogin.user

enum class EasyUserProperty(var s: kotlin.String) {
    USERNAME("username"),
    EMAIL("email");

    fun getField(): String{
        return s
    }
}