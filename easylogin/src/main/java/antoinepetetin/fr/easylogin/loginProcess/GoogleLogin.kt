package antoinepetetin.fr.easylogin.loginProcess

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import antoinepetetin.fr.easylogin.*
import antoinepetetin.fr.easylogin.user.EasyGoogleUser
import antoinepetetin.fr.easylogin.user.UserSessionManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient


internal class GoogleLogin(config: EasyLoginConfig) : LoginProcess(config), EasyLoginInterface {

    private var button: SignInButton? = null

    override fun login() {

        if (isUserConnected()) {
            throwUserAlreadyConnectedFailure(LoginType.Google)
        } else {
            var apiClient = config.getGoogleApiClient()
            val activity = config.getActivity()

            if (apiClient == null) {

                //try to read google_token_id from developer's manifest project
                var googleTokenId = activity
                    .packageManager
                    .getApplicationInfo(activity.packageName, PackageManager.GET_META_DATA)
                    .metaData.getString("google_token_id")

                //Create google sigin options builder
                var gsoBuilder = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()

                //if token id is not null, pass it to the builder
                if (googleTokenId !== null)
                    gsoBuilder.requestIdToken(googleTokenId)

                //finally, create the apiClient from builder
                apiClient = GoogleApiClient.Builder(activity)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gsoBuilder.build())
                    .build()
            }

            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient)
            activity.startActivityForResult(signInIntent, Constants.GOOGLE_LOGIN_REQUEST)
        }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // The Task returned from this call is always completed, no need to attach
        // a listener.
        val completedTask = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            val googleUser = populateGoogleUser(account!!)

            // Save the user
            UserSessionManager.setUserSession(config.getActivity(), googleUser)
            //TODO unbind facebook callback !!
            config.getCallback().onLoginSuccess(googleUser)

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("Google onActivityResult", "signInResult:failed code=" + e.statusCode + " " + e.message)
            Log.e("GOOGLE SIGN IN", "" + requestCode)

            if(e.statusCode !== GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
                // Signed out, show unauthenticated UI.
                config.getCallback().onLoginFailure(EasyLoginException("Google login failed", LoginType.Google))
            }
        }

    }

    private fun populateGoogleUser(account: GoogleSignInAccount): EasyGoogleUser {
        //Create a new google user
        val googleUser = EasyGoogleUser()
        //populate the user
        googleUser.displayName = account.displayName
        googleUser.firstName = account.givenName
        googleUser.lastName = account.familyName
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

    override fun registerSignInButton(button: View) {
        this.button = button as SignInButton
    }

    override fun unbind() {

    }
}