package antoinepetetin.fr.easylogin

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import antoinepetetin.fr.easylogin.user.EasyFacebookUser
import antoinepetetin.fr.easylogin.user.EasyGoogleUser
import antoinepetetin.fr.easylogin.user.EasyUser
import antoinepetetin.fr.easylogin.user.UserSessionManager
import com.google.gson.Gson
import org.junit.*

import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class UserSessionManagerInstrumentedTest {

    private var mockCustomUser: EasyUser = EasyUser()
    private var mockFacebookUser: EasyFacebookUser = EasyFacebookUser()
    private var mockGoogleUser: EasyGoogleUser = EasyGoogleUser()

    private var gson : Gson = Gson()

    companion object {
        var context : Context? = null

        @AfterClass
        @JvmStatic
        fun logout(){
            //Clear shared preferences
            UserSessionManager.logout(context!!)
        }
    }

    @Before
    fun init(){
        //Set context
        context = InstrumentationRegistry.getTargetContext()
        //Reset user to null
        mockCustomUser = EasyUser()
        mockFacebookUser = EasyFacebookUser()
        mockGoogleUser = EasyGoogleUser()

        //Clear shared preferences
        UserSessionManager.logout(context!!)
    }

    //ifUserConnected method

    @Test
    fun userShouldNotBeConnected() {
        assertFalse(UserSessionManager.isUserConnected(context!!))
    }

    @Test
    fun customUserShouldBeConnected() {
        UserSessionManager.setUserSession(context!!,mockCustomUser)
        assertTrue(UserSessionManager.isUserConnected(context!!))
    }

    @Test
    fun facebookUserShouldBeConnected() {
        UserSessionManager.setUserSession(context!!,mockFacebookUser)
        assertTrue(UserSessionManager.isUserConnected(context!!))
    }

    @Test
    fun googleUserShouldBeConnected() {
        UserSessionManager.setUserSession(context!!,mockGoogleUser)
        assertTrue(UserSessionManager.isUserConnected(context!!))
    }

    //setUserSession and getUserSession

    @Test
    fun shouldCorrectlySaveAndGetCustomUser() {
        mockCustomUser.userId = "1"
        mockCustomUser.firstName = "Antonio"
        mockCustomUser.lastName = "De-Lisbonne"
        mockCustomUser.email = "a.b@gmail.com"
        mockCustomUser.gender = 0
        UserSessionManager.setUserSession(context!!,mockCustomUser)
        assertEquals(gson.toJson(mockCustomUser),gson.toJson(UserSessionManager.getCurrentUser(context!!)))
    }

    @Test
    fun shouldCorrectlySaveAndGetFacebookUser() {
        mockFacebookUser.userId = "1"
        mockFacebookUser.firstName = "Antonio"
        mockFacebookUser.lastName = "De-Lisbonne"
        mockFacebookUser.email = "a.b@gmail.com"
        mockFacebookUser.gender = 0
        UserSessionManager.setUserSession(context!!,mockFacebookUser)
        assertEquals(gson.toJson(mockFacebookUser),gson.toJson(UserSessionManager.getCurrentUser(context!!)))
    }

    @Test
    fun shouldCorrectlySaveAndGetGoogleUser() {
        mockGoogleUser.userId = "1"
        mockGoogleUser.firstName = "Antonio"
        mockGoogleUser.lastName = "De-Lisbonne"
        mockGoogleUser.email = "a.b@gmail.com"
        mockGoogleUser.gender = 0
        UserSessionManager.setUserSession(context!!,mockGoogleUser)
        assertEquals(gson.toJson(mockGoogleUser),gson.toJson(UserSessionManager.getCurrentUser(context!!)))
    }

    //getTypeConnection
    
    @Test
    fun shouldGetCustomUserType() {
        UserSessionManager.setUserSession(context!!,mockCustomUser)
        assertEquals(LoginType.CustomLogin,UserSessionManager.getTypeConnection(context!!))
    }

    @Test
    fun shouldGetFacebookUserType() {
        UserSessionManager.setUserSession(context!!,mockFacebookUser)
        assertEquals(LoginType.Facebook,UserSessionManager.getTypeConnection(context!!))
    }

    @Test
    fun shouldGetGoogleUserType() {
        UserSessionManager.setUserSession(context!!,mockGoogleUser)
        assertEquals(LoginType.Google,UserSessionManager.getTypeConnection(context!!))
    }

    //logout

    @Test
    fun shouldLogout(){
        UserSessionManager.setUserSession(context!!,mockCustomUser)
        assertTrue(UserSessionManager.isUserConnected(context!!))
        UserSessionManager.logout(context!!)
        assertFalse(UserSessionManager.isUserConnected(context!!))
    }
}