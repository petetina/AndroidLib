package antoinepetetin.fr.easylogin

class EasyLoginException : Exception {
    private var loginType: LoginType? = null

    constructor(message: String) : super(message) {
    }

    constructor(message: String, loginType: LoginType) : super(message) {
        this.loginType = loginType
    }

    constructor(message: String, cause: Throwable, loginType: LoginType) : super(message, cause) {
        this.loginType = loginType
    }
}