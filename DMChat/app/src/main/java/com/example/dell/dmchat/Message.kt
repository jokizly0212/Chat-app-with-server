package com.example.dell.dmchat

data class Message (var name: String, var message: String, var time: String, var avatar: Int) {
    val type = UserName.userType()
    fun getUserType() : Int {
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