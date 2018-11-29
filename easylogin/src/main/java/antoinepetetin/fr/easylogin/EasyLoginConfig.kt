package antoinepetetin.fr.easylogin

import android.app.Activity
import android.content.pm.PackageManager
import com.google.android.gms.common.api.GoogleApiClient


class EasyLoginConfig {
    private var facebookAppId: String? = null
    private var googleTokenId: String? = null
    private var facebookPermissions: ArrayList<String>? = null
    private var googleApiClient: GoogleApiClient? = null
    private val activity: Activity
    private val callback: EasyLoginCallbacks


    constructor(activity: Activity, callback: EasyLoginCallbacks) {
        this.activity = activity
        this.callback = callback
    }

    fun getActivity(): Activity {
        return activity
    }

    fun getCallback(): EasyLoginCallbacks {
        return callback
    }

    fun getGoogleApiClient(): GoogleApiClient? {
        return googleApiClient
    }

    fun setGoogleApiClient(googleApiClient: GoogleApiClient) {
        this.googleApiClient = googleApiClient
    }

    //Read facebook app id from manifest ;)
    fun getFacebookAppId(): String? {
        val ai = activity.packageManager.getApplicationInfo(activity.packageName, PackageManager.GET_META_DATA)
        val myApiKey = ai.metaData.getString("facebook_app_id")
        if(!myApiKey.orEmpty().isEmpty())
            facebookAppId = myApiKey
        return facebookAppId
    }

    fun getFacebookPermissions(): ArrayList<String>? {
        return facebookPermissions
    }

    fun setFacebookPermissions(facebookPermissions: ArrayList<String>) {
        this.facebookPermissions = facebookPermissions
    }

    companion object {
        fun getDefaultFacebookPermissions(): ArrayList<String> {
            val defaultPermissions = ArrayList<String>()
            defaultPermissions.add("public_profile")
            defaultPermissions.add("email")
            defaultPermissions.add("user_birthday")
            return defaultPermissions
        }
    }

}