package com.example.dell.dmchat


object UserName {
    var userName = ""
    var userNameFromServer = ""
    var userNameLogin = ""
    var userTimeLoggedIn = ""
    val activeUserList = ArrayList<Users>()
    private val avatar = R.drawable.stranger
    fun userType() : Int {
        var result: Int
        if(userNameFromServer == "login") {
            activeUserList.add(Users(avatar, userNameLogin, userTimeLoggedIn))
            result = 2
        } else if(userName != userNameFromServer && userNameFromServer != "login"){
            result = 1
        } else {
            result = 0
        }
        return result
    }
}