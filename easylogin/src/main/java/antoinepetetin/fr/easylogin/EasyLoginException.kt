package antoinepetetin.fr.easylogin

class EasyLoginException : Exception {
    private var loginType: LoginType? = null
    var isCancelled = false

    constructor(message: String, loginType: LoginType) : super(message) {
        this.loginType = loginType
    }

    constructor(message: String, loginType: LoginType, isCancelled: Boolean) : super(message) {
        this.loginType = loginType
        this.isCancelled = isCancelled
    }

    constructor(message: String, cause: Throwable, loginType: LoginType) : super(message, cause) {
        this.loginType = loginType
    }
}