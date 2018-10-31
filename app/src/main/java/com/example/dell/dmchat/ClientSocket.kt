package com.example.dell.dmchat

import java.net.Socket
//author : Phat Doan
//This singleton is used for create the connection between client and chat server
object ClientSocket {
    val ipAddress = "192.168.0.101"
    val port = 30001
    var isConnect = false
    lateinit var clientSocket: Socket //store the socket in this singleton after user logged in
    fun connectServer() {
        clientSocket = Socket(ipAddress, port)
        isConnect = true
    }
}