package com.yemelianov.trainyourbrain

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import java.util.*


class MainActivity : AppCompatActivity() {
    var startButton: Button? = null
    var resultTextView: TextView? = null
    var pointsTextView: TextView? = null
    var sumTextView: TextView? = null
    var timerTextView: TextView? = null
    var playAgainButton: Button? = null
    var button3: Button? = null
    var button4: Button? = null
    var button5: Button? = null
    var button6: Button? = null
    var answers = ArrayList<Int>()
    var locationOfCorrectAnswers = 0
    var score = 0
    var questions = 0
    var consLayout: ConstraintLayout? = null
    var bestScore: TextView? = null
    private lateinit var savedPref: SharedPreferences
    var bestScoreInt: Int = 0


    fun playAgain(view: View?) {
        score = 0
        questions = 0
        timerTextView!!.text = getString(R.string._30s)
        pointsTextView!!.text = getString(R.string._0_0)
        resultTextView!!.text = getString(R.string.answering)
        playAgainButton!!.visibility = View.INVISIBLE
        button3!!.isEnabled = true
        button4!!.isEnabled = true
        button5!!.isEnabled = true
        button6!!.isEnabled = true
        button3!!.isInvisible = false
        button4!!.isInvisible = false
        button5!!.isInvisible = false
        button6!!.isInvisible = false
        sumTextView!!.isInvisible = false
        pointsTextView!!.isInvisible = false

        generateQuestion()
        object : CountDownTimer(15900, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView!!.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                playAgainButton!!.visibility = View.VISIBLE
                timerTextView!!.text = getString(R.string.sec_finish)
                resultTextView!!.text = getString(R.string.your_score, score)
                bestScore = findViewById(R.id.bestScore)
                button3!!.isEnabled = false
                button4!!.isEnabled = false
                button5!!.isEnabled = false
                button6!!.isEnabled = false
                button3!!.isInvisible = true
                button4!!.isInvisible = true
                button5!!.isInvisible = true
                button6!!.isInvisible = true
                sumTextView!!.isInvisible = true
                pointsTextView!!.isInvisible = true

                if (score > bestScoreInt) {
                    bestScoreInt = score
                }
                bestScore!!.text = getString(R.string.best_score, bestScoreInt)

            }

        }.start()

    }

    fun generateQuestion() {
        val rand = Random()
        val a = rand.nextInt(21)
        val b = rand.nextInt(21)
        sumTextView!!.text = getString(R.string.welcome_messages, a, b)
        locationOfCorrectAnswers = rand.nextInt(4)
        answers.clear()
        for (i in 0..3) {
            var incorrectAnswer: Int
            if (i == locationOfCorrectAnswers) {
                answers.add(a + b)
            } else {
                incorrectAnswer = rand.nextInt(41)
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41)
                }
                answers.add(incorrectAnswer)
            }
        }
        button3!!.text = answers[0].toString()
        button4!!.text = answers[1].toString()
        button5!!.text = answers[2].toString()
        button6!!.text = answers[3].toString()
    }

    fun startGame(view: View?) {
        startButton!!.visibility = View.INVISIBLE
        consLayout!!.visibility = View.VISIBLE
        playAgain(findViewById(R.id.playAgainButton))
    }

    fun answer1(view: View?) {
        if (locationOfCorrectAnswers == 0) {
            score++
            resultTextView!!.text = getString(R.string.display)
        } else {
            resultTextView!!.text = getString(R.string.play)
        }
        questions++
        pointsTextView!!.text = getString(R.string.point, score, questions)
        generateQuestion()
    }

    fun answer2(view: View?) {
        if (locationOfCorrectAnswers == 1) {
            score++
            resultTextView!!.text = getString(R.string.display)
        } else {
            resultTextView!!.text = getString(R.string.play)
        }
        questions++
        pointsTextView!!.text = getString(R.string.point, score, questions)
        generateQuestion()
    }

    fun answer3(view: View?) {
        if (locationOfCorrectAnswers == 2) {
            score++
            resultTextView!!.text = getString(R.string.display)
        } else {
            resultTextView!!.text = getString(R.string.play)
        }
        questions++
        pointsTextView!!.text = getString(R.string.point, score, questions)
        generateQuestion()
    }

    fun answer4(view: View?) {
        if (locationOfCorrectAnswers == 3) {
            score++
            resultTextView!!.text = getString(R.string.display)
        } else {
            resultTextView!!.text = getString(R.string.play)
        }
        questions++
        pointsTextView!!.text = getString(R.string.point, score, questions)
        generateQuestion()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton = findViewById(R.id.startButton)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        resultTextView = findViewById(R.id.resultTextView)
        pointsTextView = findViewById(R.id.pointsTextView)
        sumTextView = findViewById(R.id.sumTextView)
        timerTextView = findViewById(R.id.timerTextView)
        playAgainButton = findViewById(R.id.playAgainButton)
        consLayout = findViewById(R.id.consLayout)



        val settingsBtn = findViewById<ImageButton>(R.id.settingsBtn)
        settingsBtn.setOnClickListener {
            intent = Intent(this, Settings::class.java)
            finish()
            startActivity(intent)
        }
        savedPref = getSharedPreferences("settings", MODE_PRIVATE)

        val editor = savedPref.edit()



    }

    override fun onPause() {
        super.onPause()

        val editor = savedPref.edit()
        editor.putInt("Best_Score", bestScoreInt).apply()
    }

    override fun onResume() {
        super.onResume()

        if(savedPref.contains("Best_Score")){
            bestScoreInt = savedPref.getInt("Best_Score", 0)

        }
    }


}