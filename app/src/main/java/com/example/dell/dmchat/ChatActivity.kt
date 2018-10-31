package com.example.dell.dmchat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.dell.dmchat.R.id.tabItem
import com.example.dell.dmchat.R.id.tabs
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.fragment_active_user.*
import kotlinx.android.synthetic.main.fragment_chat.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

//author: Phat Doan

//Chat Activity when user has logged in, this activity contains two fragments

class ChatActivity : AppCompatActivity() {

    private var listMsg = ArrayList<Message>()
    private val listUserActive = UserName.activeUserList
    private val adapter = CustomAdapter(this@ChatActivity, listMsg)
    private val activeUserAdapter = ActiveUserAdapter(this@ChatActivity, listUserActive)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setupViewPager(container)
        tabs.setupWithViewPager(container)
        tabs.getTabAt(0)?.setIcon(R.drawable.ic_chat_black_24dp)
        tabs.getTabAt(1)?.setIcon(R.drawable.people)
        container.offscreenPageLimit = 2
        onShowMessage()
    }

    private fun setupViewPager (viewPager: ViewPager) {
        val fragmentAdapter = SectionsPageAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(TabChatFragment())
        fragmentAdapter.addFragment(TabActiveUserFragment())
        viewPager.adapter = fragmentAdapter
    }
    fun onClickSendBtn(message: String) {
        onSendMessage(message)
    }

    private fun onUpdateMessage(message:String) {//handles the message from the server, updating the UI by using CustomAdapter
        var messageData = message.split("⟷") //split the message into neccessary data for showing on the UI
        val msgUser = messageData[0]
        val msgContent = messageData[1]
        val msgTime = messageData[2]
        val msgAvatar = R.drawable.stranger
        UserName.userNameFromServer = msgUser
        UserName.userNameLogin = msgContent
        UserName.userTimeLoggedIn = "Logged In at $msgTime"
        listMsg.add(Message(msgUser, msgContent, msgTime, msgAvatar))
        listMessage.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun onShowMessage() { //Connect to the server through socket, this function is used for reading the message from the server
        val handler = Handler()
        val thread = Thread(Runnable {
            run {
                while(ClientSocket.isConnect) {
                    try {
                        val clientSocket = ClientSocket.clientSocket
                        val bufferedReader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
                        val message = bufferedReader.readLine()
                        handler.post {
                            onUpdateMessage(message)
                            onShowActiveUser()
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        })
        thread.start()
    }

    private fun onSendMessage(message:String) {//connect to server through socket, this function is used for sending the message when user click the send button
        val thread = Thread(Runnable {
            run {
                try {
                    val clientSocket = ClientSocket.clientSocket
                    val printWriter = PrintWriter(clientSocket.getOutputStream(), true)
                    printWriter.println(message)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
        thread.start()
    }


    private fun onShowActiveUser() { //update the list active user and using the activeUserAdapter for updating the UI
        listActiveUser.adapter = activeUserAdapter
        activeUserAdapter.notifyDataSetChanged()
    }

    override fun onBackPressed() { //When user click back button, close the app
        finish()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}
