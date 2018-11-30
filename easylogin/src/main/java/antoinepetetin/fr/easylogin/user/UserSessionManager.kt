package antoinepetetin.fr.easylogin.user

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import antoinepetetin.fr.easylogin.Constants
import antoinepetetin.fr.easylogin.LoginType
import com.google.gson.Gson


object UserSessionManager {

    fun isUserConnected(context: Context): Boolean {
        val preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE)
        val sessionUser = preferences.getString(Constants.USER_SESSION, Constants.DEFAULT_SESSION_VALUE)
        return sessionUser != Constants.DEFAULT_SESSION_VALUE
    }

    /**
     * This static method can be called to get the logged in user.
     * It reads from the shared preferences and builds a EasyUser object and returns it.
     * If no user is logged in it returns null
     */
    fun getCurrentUser(context: Context): EasyUser? {
        var easyUser: EasyUser? = null
        val preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE)
        val gson = Gson()
        val sessionUser = preferences.getString(Constants.USER_SESSION, Constants.DEFAULT_SESSION_VALUE)
        val user_type = preferences.getString(Constants.USER_TYPE, Constants.CUSTOMUSERFLAG)
        if (sessionUser != Constants.DEFAULT_SESSION_VALUE) {
            try {
                when (user_type) {
                    Constants.FACEBOOKFLAG -> easyUser =
                            gson.fromJson<EasyFacebookUser>(sessionUser, EasyFacebookUser::class.java!!)
                    Constants.GOOGLEFLAG -> easyUser =
                            gson.fromJson<EasyGoogleUser>(sessionUser, EasyGoogleUser::class.java!!)
                    else -> easyUser = gson.fromJson<EasyUser>(sessionUser, EasyUser::class.java!!)
                }
            } catch (e: Exception) {
                Log.e("GSON", e.message)
            }

        }
        return easyUser
    }

    /*
     * This method return the connection type if user is connected, else null
     */

    fun getTypeConnection(context: Context): LoginType? {
        val preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE)
        val user_type = preferences.getString(Constants.USER_TYPE, Constants.CUSTOMUSERFLAG)
        when (user_type) {
            Constants.FACEBOOKFLAG -> return LoginType.Facebook
            Constants.GOOGLEFLAG -> return LoginType.Google
            Constants.CUSTOMUSERFLAG -> return LoginType.CustomLogin
            else -> return null
        }
    }

    /**
     * This method sets the session object for the current logged in user.
     * This is called from inside the EasyLoginActivity to save the
     * current logged in user to the shared preferences.
     */
    internal fun setUserSession(context: Context, EasyUser: EasyUser?): Boolean {
        val preferences: SharedPreferences
        val editor: SharedPreferences.Editor
        if (EasyUser != null) {
            try {
                preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE)
                editor = preferences.edit()

                if (EasyUser is EasyFacebookUser) {
                    editor.putString(Constants.USER_TYPE, Constants.FACEBOOKFLAG)
                } else if (EasyUser is EasyGoogleUser) {
                    editor.putString(Constants.USER_TYPE, Constants.GOOGLEFLAG)
                } else {
                    editor.putString(Constants.USER_TYPE, Constants.CUSTOMUSERFLAG)
                }

                val gson = Gson()
                val sessionUser = gson.toJson(EasyUser)
                Log.d("GSON", sessionUser)
                editor.putString(Constants.USER_SESSION, sessionUser)
                editor.apply()
                return true
            } catch (e: Exception) {
                Log.e("Session Error", e.message)
                return false
            }

        } else {
            Log.e("Session Error", "User is null")
            return false
        }
    }
}