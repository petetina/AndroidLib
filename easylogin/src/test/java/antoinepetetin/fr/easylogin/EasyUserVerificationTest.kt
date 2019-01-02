package antoinepetetin.fr.easylogin

import org.junit.Assert
import org.junit.Test

/**
 * Check if user verification works
 */
class EasyUserVerificationTest {


    @Test
    fun check_valid_email() {
        Assert.assertTrue(EasyUserVerification.checkEmail("a.b@gmail.com"))
    }

    @Test
    fun check_wrong_email_should_failed() {
        Assert.assertFalse(EasyUserVerification.checkEmail("@gmail.com"))
    }


}