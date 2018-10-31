package com.example.dell.dmchat

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import kotlinx.android.synthetic.main.fragment_chat.view.*

class TabChatFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chat, null)
        val sendButton = view.findViewById<ImageButton>(R.id.sendBtn)
        val message = view.findViewById<EditText>(R.id.inputMessage)
        sendButton.setOnClickListener {
            val chatActivity = ChatActivity()
            chatActivity.onClickSendBtn(message.text.toString())
            view.inputMessage.text = null
        }
        return view
    }
}