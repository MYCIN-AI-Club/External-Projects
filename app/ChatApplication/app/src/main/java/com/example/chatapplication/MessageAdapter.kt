package com.example.chatapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context:Context, val messageList: ArrayList<Message>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val itemReceive = 1
    val itemSent= 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        if(viewType == 1)
        {
            val view: View = LayoutInflater.from(context).inflate(R.layout.recieve,parent,false)
            return ReceiveViewHolder(view)
        }
        else
        {
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]
//        val currentImage =messageList[position]
        if(holder.javaClass == SentViewHolder::class.java){
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text=currentMessage.message
//            holder.sentImage.setImageURI(currentImage.Image)
        }
        else{
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text=currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        val currentImage =messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId))
        {
            return itemSent
        }
        else
        {
            return itemReceive
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }


    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val sentMessage =itemView.findViewById<TextView>(R.id.txt_sent_message)
        //val sentImage=itemView.findViewById<ImageView>(R.id.img_sent_message)
    }

    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val receiveMessage =itemView.findViewById<TextView>(R.id.txt_receive_message)
        //val receiveImagae=itemView.findViewById<ImageView>(R.id.img_receive_message)

    }

}