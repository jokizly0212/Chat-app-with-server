package com.example.dell.dmchat

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class TabTopChatterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance = true
        val view = inflater.inflate(R.layout.fragment_top_chatter, null)
        return view
    }
}