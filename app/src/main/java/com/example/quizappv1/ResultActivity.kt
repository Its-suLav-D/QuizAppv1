package com.example.quizappv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var userName : TextView
        lateinit var finalScore: TextView
        lateinit var finishBtn: Button

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        userName = findViewById(R.id.tv_name)
        finalScore = findViewById(R.id.tv_score)

        val username = intent.getStringExtra(Constants.USER_NAME)
        userName.text = username

        val correct = intent.getStringExtra(Constants.CORRECT_ANSWERS)


        val total = intent.getStringExtra(Constants.TOTAL_QUESTIONS)

        finalScore.text="Your score is $correct out of $total"

        finishBtn = findViewById(R.id.btn_finish)

        finishBtn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}