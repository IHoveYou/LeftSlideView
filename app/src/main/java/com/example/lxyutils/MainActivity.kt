package com.example.lxyutils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.leftslideview.LeftSlideUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
    }


    public fun test() {
        val list = ArrayList<String>();
        for (i in 1..100) {
            list.add(i.toString());
        }
        rvList.layoutManager = LinearLayoutManager(this)
        val adapter = MainAdapter(list, this)

        rvList.adapter = adapter

        tvRefresh.setOnClickListener() {
            adapter.notifyDataSetChanged()
        }
    }


    class MainAdapter(val list: ArrayList<String>, val context: Context) :
        RecyclerView.Adapter<MainViewHolder>() {
        val leftSlideUtils : LeftSlideUtils by lazy { LeftSlideUtils() }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_main, parent, false)
            val holder = MainViewHolder(itemView)
            leftSlideUtils.addView(itemView.leftSlideHorizontalScrollView)
            return holder
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.itemView.leftSlideHorizontalScrollView.initScrollow()
            holder.itemView.textView.text = list[position]
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }

    class MainViewHolder(itemView: View) : ViewHolder(itemView)
}