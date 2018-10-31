package com.example.dell.dmchat

//author : Phat Doan
//This is the singleton for storing the username in the app and username is gotten from the server

object UserName {
    var userName = ""
    var userNameFromServer = ""
    var userNameLogin = ""
    var userTimeLoggedIn = ""
    val activeUserList = ArrayList<Users>()
    private val avatar = R.drawable.stranger
    fun userType() : Int { //get the type of the message and send it to the Message class to sort the type of the message
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