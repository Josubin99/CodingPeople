package com.example.codingpeople.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.codingpeople.MainActivity
import com.example.codingpeople.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()

        login_button.setOnClickListener {

            auth.signInWithEmailAndPassword(email_area.text.toString(), password_area.text.toString())
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){

                        val intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this,"로그인 성공",Toast.LENGTH_LONG).show()

                    }else{

                        Toast.makeText(this,"로그인에 실패하였습니다",Toast.LENGTH_LONG).show()

                    }
                }
        }

        join_button.setOnClickListener {

            val intent=Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

    }
}