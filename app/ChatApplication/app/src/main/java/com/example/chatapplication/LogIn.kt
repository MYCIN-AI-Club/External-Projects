package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private  lateinit var edtEmail: EditText
    private  lateinit var edtPassword: EditText
    private  lateinit var btnLogin: Button
    private  lateinit var btnSignUp: Button

    private  lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        supportActionBar?.hide()
        auth= FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        edtEmail=findViewById(R.id.email)
        edtPassword=findViewById(R.id.password)
        btnLogin=findViewById(R.id.btnLogin)
        btnSignUp=findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email=edtEmail.text.toString()
            val password=edtPassword.text.toString()

            login(email,password)
        }


    }

    private fun login(email: String,password: String)
    {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val intent=Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
    }
}