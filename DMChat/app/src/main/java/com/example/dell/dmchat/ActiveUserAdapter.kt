package com.example.dell.dmchat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class ActiveUserAdapter (var context : Context, var arrayUser : ArrayList<Users>) : BaseAdapter() {
    class ViewHolder(row: View) {
        var avatar : ImageView
        var name: TextView
        var time: TextView

        init {
            avatar = row.findViewById(R.id.image_message_profile) as ImageView
            name = row.findViewById(R.id.text_message_name) as TextView
            time = row.findViewById(R.id.text_message_time) as TextView
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if (convertView == null) {
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.user_active_layout, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        var user: Users = getItem(position) as Users
        viewHolder.avatar.setImageResource(user.avatar)
        viewHolder.name.text = user.userName
        viewHolder.time.text = user.timeLoggedIn
        return view as View
    }

    override fun getItem(position: Int): Any {
        return arrayUser.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayUser.size
    }
}