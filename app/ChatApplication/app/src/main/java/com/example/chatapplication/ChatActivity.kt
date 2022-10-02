package com.example.chatapplication


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var galleryButton: ImageView
    private lateinit var sendButton: ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>

    private lateinit var ImageUri: Uri

    private lateinit var database: DatabaseReference

    var receiverRoom: String? = null
    var senderRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        database=FirebaseDatabase.getInstance().getReference()

        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid

        supportActionBar?.title = name


        messageList = ArrayList()
        messageAdapter = MessageAdapter(this,messageList)

        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageBox = findViewById(R.id.messageBox)
        galleryButton=findViewById(R.id.galleryButton)
        sendButton = findViewById(R.id.sentButton)


        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter

        database.child("chats").child(senderRoom!!).child("messages").addValueEventListener(object:ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                messageList.clear()
                for(postSnapshot in snapshot.children)
                {
                    val message = postSnapshot.getValue(Message::class.java)
                    messageList.add(message!!)
                    chatRecyclerView.scrollToPosition(messageAdapter.itemCount -1)
//                    Log.d("jayant", message.message.toString())
                }
                messageAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        galleryButton.setOnClickListener {
            chooseImage()
        }

        sendButton.setOnClickListener {

            val message = messageBox.text.toString()
            val messageObject = Message(message,senderUid)

            database.child("chats").child(senderRoom!!).child("messages").push().setValue(messageObject).addOnSuccessListener {
                database.child("chats").child(receiverRoom!!).child("messages").push().setValue(messageObject)
            }
            messageBox.setText("")

        }
    }
    private fun chooseImage()
    {
        val intent = Intent()
        intent.type="images/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==100 && resultCode == RESULT_OK)
        {
            ImageUri = data?.data!!
            val intent = Intent(this,PreviewImg::class.java)
            intent.putExtra("Image",ImageUri)
            startActivity(intent)
        }
    }
}