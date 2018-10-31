package com.example.dell.dmchat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import android.content.Intent

//author : Phat Doan
//This is the login activity for setting the user name and connect this client to the server through the socket

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onClickLoginBtn()
    }

    private fun onClickLoginBtn() { //Handles the login button also get the name from the EditText
        loginBtn.setOnClickListener {
            val userName = username.text
            UserName.userName = "$userName"
            userLogin(userName)
        }
    }

    private fun userLogin(userName: Editable) { //Connect to the chat server through socket
        val handler = Handler()
        val thread = Thread(Runnable {
            run{
                try {
                    ClientSocket.connectServer()
                    val clientSocket = ClientSocket.clientSocket
                    val printWriter = PrintWriter(clientSocket.getOutputStream(), true)
                    printWriter.println(":user $userName") //command for setting the name and send it to the server, user now can enter the chat room
                    val bufferedReader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                    val message = bufferedReader.readLine()
                    printWriter.println(":login login $userName")// while logging in, it also notifies to everyone in the chat room that new user has joined
                    handler.post {
                        run {
                            if(message == "Connect successfully") { //Check whether the user is connected to the chat server or not
                                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                                val intent = Intent(this, ChatActivity::class.java ) //moving to the chat Activity if user is connected to the chat server
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "something wrong", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
        thread.start()
    }

}
