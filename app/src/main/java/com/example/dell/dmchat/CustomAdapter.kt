package com.example.dell.dmchat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
//author : Phat Doan
//Custom adapter for the chat room
class CustomAdapter (var context : Context, var arrayMessage : ArrayList<Message>) : BaseAdapter() {

    private val sendMessage = 0
    private val receiveMessage = 1
    private val userLogin = 2

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowMessage: View?
        val type = getItemViewType(position)
            val layoutInflater = LayoutInflater.from(context)
            if(type == sendMessage) {
                rowMessage = layoutInflater.inflate(R.layout.item_message_sent, null)
            } else if (type == receiveMessage) {
                rowMessage = layoutInflater.inflate(R.layout.item_message_received, null)
            } else {
                rowMessage = layoutInflater.inflate(R.layout.item_user_login, null)
            }

        val name = rowMessage.findViewById<TextView>(R.id.text_message_name)
        val message = rowMessage.findViewById<TextView>(R.id.text_message_body)
        val time = rowMessage.findViewById<TextView>(R.id.text_message_time)
        val image = rowMessage.findViewById<ImageView>(R.id.image_message_profile)

        val messageData : Message = getItem(position) as Message

        if(type == sendMessage) {
            message.text = messageData.message
            time.text = messageData.time
        } else if (type == receiveMessage) {
            name.text = messageData.name
            message.text = messageData.message
            time.text = messageData.time
            image.setImageResource(messageData.avatar)
        } else {
            message.text = messageData.message
            image.setImageResource(messageData.avatar)
        }

        return rowMessage

    }

    override fun getViewTypeCount(): Int {
        return 3
    }

    override fun getItemViewType(position: Int): Int {
        var result: Int
        if(arrayMessage.get(position).getUserType() == 0) {
            result = sendMessage
        } else if(arrayMessage.get(position).getUserType() == 1) {
            result = receiveMessage
        } else {
            result = userLogin
        }
        return result
    }

    override fun getItem(position: Int): Any {
        return arrayMessage.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getCount(): Int {
        return arrayMessage.size
    }
}