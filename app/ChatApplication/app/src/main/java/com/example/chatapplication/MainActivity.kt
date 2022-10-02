package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth= FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance().getReference()

        userList = ArrayList()
        adapter = UserAdapter(this,userList)

        userRecyclerView=findViewById(R.id.userRecyclerView)

        userRecyclerView.layoutManager =LinearLayoutManager(this)
        userRecyclerView.adapter=adapter

        database.child("user").addValueEventListener(object:ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for(postSnapshot in snapshot.children)
                {
                    val currentUser=postSnapshot.getValue(User::class.java)
                    if(auth.currentUser?.uid!=currentUser?.uid)
                    {
                        userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout)
        {
            auth.signOut()
            val intent= Intent(this,LogIn::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}