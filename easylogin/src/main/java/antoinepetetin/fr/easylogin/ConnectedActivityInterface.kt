package antoinepetetin.fr.easylogin

import antoinepetetin.fr.easylogin.user.EasyUser

interface ConnectedActivityInterface{
    fun doOnCreate(user: EasyUser)
}