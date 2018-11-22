package antoinepetetin.fr.easylogin.loginProcess

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.user.EasyGoogleUser
import antoinepetetin.fr.easylogin.user.UserSessionManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient


class GoogleLogin(var config: EasyLoginConfig) : EasyLogin() {

    override fun login() {
        var apiClient = config.getGoogleApiClient()
        val activity = config.getActivity()

        if (apiClient == null) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build()

            apiClient = GoogleApiClient.Builder(activity)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
        }

        val progress = ProgressDialog.show(activity, "", activity.getString(R.string.logging_holder), true)
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient)
        activity.startActivityForResult(signInIntent, Constants.GOOGLE_LOGIN_REQUEST)
        progress.dismiss()
    }

    override fun signup() {

    }

    override fun logout(context: Context): Boolean {
        try {
            val preferences = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.remove(Constants.USER_TYPE)
            editor.remove(Constants.USER_SESSION)
            editor.apply()
            return true
        } catch (e: Exception) {
            Log.e("GoogleLogin", e.message)
            return false
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent, config: EasyLoginConfig) {
        val progress =
            ProgressDialog.show(config.getActivity(), "", config.getActivity().getString(R.string.getting_data), true)
        val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
        Log.d("GOOGLE SIGN IN", "handleSignInResult:" + result.isSuccess)
        if (result.isSuccess) {
            // Signed in successfully, show authenticated UI.
            val acct = result.signInAccount
            val googleUser = populateGoogleUser(acct!!)
            // Save the user
            UserSessionManager.setUserSession(config.getActivity(), googleUser)
            config.getCallback().onLoginSuccess(googleUser)
            progress.dismiss()
        } else {
            Log.d("GOOGLE SIGN IN", "" + requestCode)
            // Signed out, show unauthenticated UI.
            progress.dismiss()
            config.getCallback().onLoginFailure(EasyLoginException("Google login failed", LoginType.Google))
        }
    }

    private fun populateGoogleUser(account: GoogleSignInAccount): EasyGoogleUser {
        //Create a new google user
        val googleUser = EasyGoogleUser()
        //populate the user
        googleUser.displayName = account.displayName
        if (account.photoUrl != null) {
            googleUser.photoUrl = account.photoUrl!!.toString()
        }
        googleUser.email = account.email
        googleUser.idToken = account.idToken
        googleUser.userId = account.id
        if (account.account != null) {
            googleUser.username = account.account!!.name
        }

        //return the populated google user
        return googleUser
    }
}