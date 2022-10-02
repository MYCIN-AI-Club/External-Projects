package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private  lateinit var name: EditText
    private  lateinit var edtEmail: EditText
    private  lateinit var edtPassword: EditText
    private  lateinit var btnSignUp: Button

    private  lateinit var auth: FirebaseAuth
    private  lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        auth= FirebaseAuth.getInstance()

        name=findViewById(R.id.userName)
        edtEmail=findViewById(R.id.email)
        edtPassword=findViewById(R.id.password)
        btnSignUp=findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val name=name.text.toString()
            val email= edtEmail.text.toString()
            val password=edtPassword.text.toString()

            signUp(name,email,password)
        }
    }
    private fun signUp(name: String,email: String, password: String)
    {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    addUserToDatabase(name,email,auth.currentUser?.uid!!)
                    val intent=Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(name:String,email:String,uid:String)
    {
        database= FirebaseDatabase.getInstance().getReference()

        database.child("user").child(uid).setValue(User(name, email, uid))
    }
}