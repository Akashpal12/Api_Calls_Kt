package com.school.apicallskt.retrofit_kotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.school.apicallskt.R
import com.school.apicallskt.retrofit_kotlin.activity.SinglePostKtActivity
import com.school.apicallskt.retrofit_kotlin.model.PostKtModel

class PostKtAdapter(val context: Context, val postList: List<PostKtModel>) :
    RecyclerView.Adapter<PostKtAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_list_items, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model = postList[position]
        holder.userId.text = model.userId.toString()
        holder.id.text = model.id.toString()
        holder.body.text = model.body
        holder.title.text = model.title
        holder.card_item.setOnClickListener({
            val intent=Intent(context,SinglePostKtActivity::class.java)
            intent.putExtra("id",model.id.toString())
            context.startActivity(intent)
        })

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userId: TextView = itemView.findViewById(R.id.userId)
        var id: TextView = itemView.findViewById(R.id.Id)
        var body: TextView = itemView.findViewById(R.id.body)
        var title: TextView = itemView.findViewById(R.id.tittle)
        var card_item: MaterialCardView = itemView.findViewById(R.id.card_item)

    }
}