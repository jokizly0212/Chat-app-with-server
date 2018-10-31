package com.example.dell.dmchat

import java.net.Socket

object ClientSocket {
    val ipAddress = "192.168.0.101"
    val port = 30001
    var isConnect = false
    lateinit var clientSocket: Socket
    fun connectServer() {
        clientSocket = Socket(ipAddress, port)
        isConnect = true
    }
}