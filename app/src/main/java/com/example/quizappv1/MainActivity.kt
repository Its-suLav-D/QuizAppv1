package com.example.quizappv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.content.Intent
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var startBtn: Button
    lateinit var name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

//
//        btn_start.setOnClickListener {
//            if(et_name.text.toString().isEmpty())
//            {
//                Toast.makeText(this,"Please Enter your Name", Toast.LENGTH_SHORT).show()
//            } else {
//                val intent = Intent(this,QuizQuestionActivity::class.java)
//                startActivity(intent)
//            }
//        }

        startBtn = findViewById(R.id.btn_start)

        name = findViewById(R.id.et_name)


        startBtn.setOnClickListener{
            if(name.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, name.text.toString())
                startActivity(intent)
                finish()
            }
        }

    }
}