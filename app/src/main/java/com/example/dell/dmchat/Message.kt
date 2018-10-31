package com.example.dell.dmchat

//author : Phat Doan
//This is the class for storing the data for the CustomAdapter
data class Message (var name: String, var message: String, var time: String, var avatar: Int) {
    val type = UserName.userType()
    fun getUserType() : Int { //Check the user type and send it to adapter to inflate the appropriate adapter
        var result: Int
        if(type == 0) {
            result = 0
        } else if (type == 1) {
            result = 1
        } else {
            result = 2
        }
        return result
    }
}