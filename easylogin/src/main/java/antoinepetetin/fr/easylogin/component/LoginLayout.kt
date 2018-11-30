package antoinepetetin.fr.easylogin.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import antoinepetetin.fr.easylogin.R
import antoinepetetin.fr.easylogin.user.UserSessionManager
import kotlinx.android.synthetic.main.login_layout.view.*
import java.util.*


class LoginLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    private var view: View? = null
    var emailField : EditText? = null
    var passwordField : EditText? = null

    init {

        view = LayoutInflater.from(context)
            .inflate(R.layout.login_layout, this, true)

        if(UserSessionManager.isUserConnected(context))
            linearLayoutButtons.addView(SignOutButton(context))
        else {
            val map = getAttributes(attrs)

            //Then add components to LoginLayout if attribut is present in map
            for (mapData in map.entries) {
                when (mapData.value) {
                    "facebook" -> {
                        linearLayoutButtons.addView(FacebookSignInButton(context))
                    }
                    "google" -> {
                        linearLayoutButtons.addView(GoogleSignInButton(context))
                    }
                    "custom" -> {
                        val view = EmailPasswordView(context)
                        emailField = view.email
                        passwordField = view.password
                        linearLayoutButtons.addView(view)
                    }
                }
                Log.e("Custom attr", "Attributes : " + mapData.key + "Value : " + mapData.value)
            }
        }



        orientation = VERTICAL

    }

    fun getEmail(): String{
        if(emailField == null)
            return ""
        else
            return emailField!!.text.toString()
    }

    fun getPassword(): String{
        if(passwordField == null)
            return ""
        else
            return passwordField!!.text.toString()
    }

    private fun getAttributes(attrs: AttributeSet? = null): TreeMap<Int,String>{
        //Declare an ascending sorted map
        var map = TreeMap<Int, String>()

        //For each attributes, we fill a tree map
        val array = context.obtainStyledAttributes(attrs, R.styleable.LoginLayout)
        val N = array.indexCount
        Log.e("count", N.toString())
        for (i in 0 until N) {
            val attr = array.getIndex(i)
            when (attr) {
                R.styleable.LoginLayout_facebook -> {
                    val position = array.getInt(attr,-1)
                    if(position >= 0)
                        map.put(position,"facebook")
                }

                R.styleable.LoginLayout_google -> {
                    val position = array.getInt(attr,-1)
                    if(position >= 0)
                        map.put(position,"google")
                }

                R.styleable.LoginLayout_custom -> {
                    val position = array.getInt(attr,-1)
                    if(position >= 0)
                        map.put(position,"custom")
                }
            }
        }
        array.recycle()
        return map
    }

    fun addFacebookButton(){
        linearLayoutButtons.addView(FacebookSignInButton(context))
    }

    fun addGoogleButton(){
        linearLayoutButtons.addView(GoogleSignInButton(context))
    }

    fun addCustomLogin(){
        val view = EmailPasswordView(context)
        emailField = view.email
        passwordField = view.password
        linearLayoutButtons.addView(view)
    }


}