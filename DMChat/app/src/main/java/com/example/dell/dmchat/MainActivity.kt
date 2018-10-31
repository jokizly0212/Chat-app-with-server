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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onClickLoginBtn()
    }

    private fun onClickLoginBtn() {
        loginBtn.setOnClickListener {
            val userName = username.text
            UserName.userName = "$userName"
            userLogin(userName)
        }
    }

    private fun userLogin(userName: Editable) {
        val handler = Handler()
        val thread = Thread(Runnable {
            run{
                try {
                    ClientSocket.connectServer()
                    val clientSocket = ClientSocket.clientSocket
                    val printWriter = PrintWriter(clientSocket.getOutputStream(), true)
                    printWriter.println(":user $userName")
                    val bufferedReader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                    val message = bufferedReader.readLine()
                    printWriter.println(":login login $userName")
                    handler.post {
                        run {
                            if(message == "Connect successfully") {
                                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                                val intent = Intent(this, ChatActivity::class.java )
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
