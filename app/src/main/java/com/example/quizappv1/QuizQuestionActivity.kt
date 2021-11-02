package com.example.quizappv1

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceGroup
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var progresBar: ProgressBar
    lateinit var textBar : TextView
    lateinit var questionText: TextView
    lateinit var ivImage: ImageView
    lateinit var optionOne: TextView
    lateinit var optionTwo: TextView
    lateinit var optionThree: TextView
    lateinit var optionFour: TextView
    lateinit var btnSubmit : Button
//    lateinit var correct: TextView

    private var mCurrentPosition: Int = 1

    private var mQuestionList: ArrayList<Question> ? = null

    private var mSelectedOptionPosition: Int = 0

    private var mCorrectAnswers: Int =0

    private var mUserName:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionList = Constants.getQuestion()
        setQuestions()

        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)

        btnSubmit.setOnClickListener(this)

    }

    private fun setQuestions()
    {
        btnSubmit = findViewById(R.id.btn_submit)
        val question = mQuestionList!!.get(mCurrentPosition-1)

        defaultOptionView()
        if(mCurrentPosition == mQuestionList!!.size)
        {
            btnSubmit.text = "FINISH"
        } else {
            btnSubmit.text = "SUBMIT"
        }


        progresBar = findViewById(R.id.progressBar)
        progresBar.progress = mCurrentPosition

        textBar = findViewById(R.id.tv_progress)
        textBar.text = "$mCurrentPosition" + "/" + progresBar.max

        questionText = findViewById(R.id.tv_question)

        questionText.text = question!!.question

        ivImage = findViewById(R.id.iv_image)
        ivImage.setImageResource(question.image)


        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)

        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo
        optionThree.text = question.optionThree
        optionFour.text = question.optionFour
    }

    private fun defaultOptionView()
    {

        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)


        val options = ArrayList<TextView>()
        options.add(0,optionOne)
        options.add(1,optionTwo)
        options.add(2,optionThree)
        options.add(3,optionFour)

        for(option in options)
        {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }

    }

    override fun onClick(v: View?) {
        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)
        when(v?.id)
        {
            R.id.tv_option_one -> {
                selectedOptionView(optionOne,1)
            }

            R.id.tv_option_two -> {
                selectedOptionView(optionTwo,2)
            }

            R.id.tv_option_three -> {
                selectedOptionView(optionThree,3)
            }

            R.id.tv_option_four -> {
                selectedOptionView(optionFour,4)
            }

            R.id.btn_submit -> {
                if(mSelectedOptionPosition ==0) {
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestions()
                        } else -> {
                            Toast.makeText(this, "You have Successfully Completed the Quiz", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                            startActivity(intent)
                        finish()
                        }

                    }
                }
                else {
                    val question= mQuestionList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer !== mSelectedOptionPosition)
                    {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question!!.correctAnswer, R.drawable.correct_option_border_bg)


                    if(mCurrentPosition == mQuestionList!!.size)
                    {
                        btnSubmit.text = "FINISH"
                    } else {
                        btnSubmit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }

        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber:Int)
    {
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selection_option_border_bg)
    }

    private fun answerView(answer:Int, drawableView:Int)
    {
        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)
        when(answer)
        {
            1-> {
                optionOne.background = ContextCompat.getDrawable(this,drawableView)
            }
            2-> {
                optionTwo.background = ContextCompat.getDrawable(this,drawableView)
            }
            3-> {
                optionThree.background = ContextCompat.getDrawable(this,drawableView)
            }

            4-> {
                optionFour.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }
}