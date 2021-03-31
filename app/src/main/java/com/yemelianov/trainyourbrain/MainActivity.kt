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

    var startBtn: Button? = null
    var resultTV: TextView? = null
    var pointsTV: TextView? = null
    var sumTV: TextView? = null
    var timerTV: TextView? = null
    var playAgainBtn: Button? = null
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
    lateinit var savedPref: SharedPreferences
    var bestScoreInt: Int = 0
    var settingsBtn: ImageButton? = null
    var time = 10
    var x = 0


    fun playAgain(view: View?) {
        score = 0
        questions = 0
        timerTV!!.text = getString(R.string._30s)
        pointsTV!!.text = getString(R.string._0_0)
        resultTV!!.text = getString(R.string.answering)
        playAgainBtn!!.visibility = View.INVISIBLE
        button3!!.isEnabled = true
        button4!!.isEnabled = true
        button5!!.isEnabled = true
        button6!!.isEnabled = true
        button3!!.isInvisible = false
        button4!!.isInvisible = false
        button5!!.isInvisible = false
        button6!!.isInvisible = false
        sumTV!!.isInvisible = false
        pointsTV!!.isInvisible = false

        val intent = intent
        time = intent.getIntExtra("time", 10)

        generateQuestion()
        object : CountDownTimer(time.toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTV!!.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                playAgainBtn!!.visibility = View.VISIBLE
                timerTV!!.text = getString(R.string.sec_finish)
                resultTV!!.text = getString(R.string.your_score, score)
                bestScore = findViewById(R.id.bestScore)
                button3!!.isEnabled = false
                button4!!.isEnabled = false
                button5!!.isEnabled = false
                button6!!.isEnabled = false
                button3!!.isInvisible = true
                button4!!.isInvisible = true
                button5!!.isInvisible = true
                button6!!.isInvisible = true
                sumTV!!.isInvisible = true
                pointsTV!!.isInvisible = true
                settingsBtn!!.isInvisible = false
                if (score > bestScoreInt) {
                    bestScoreInt = score
                }
                bestScore!!.text = getString(R.string.best_score, bestScoreInt)
            }
        }.start()
    }

    fun generateQuestion() {
        val intent = intent
        x = intent.getIntExtra("x", 21)
        val rand = Random()
        val a = rand.nextInt(x)
        val b = rand.nextInt(x)
        sumTV!!.text = getString(R.string.welcome_messages, a, b)
        locationOfCorrectAnswers = rand.nextInt(4)
        answers.clear()
        for (i in 0..3) {
            var incorrectAnswer: Int
            if (i == locationOfCorrectAnswers) {
                answers.add(a + b)
            } else {
                incorrectAnswer = rand.nextInt(x + 20)
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(x + 20)
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
        startBtn!!.visibility = View.INVISIBLE
        consLayout!!.visibility = View.VISIBLE
        playAgain(findViewById(R.id.playAgainButton))
        settingsBtn!!.isInvisible = true
    }

    fun answer1(view: View?) {
        if (locationOfCorrectAnswers == 0) {
            score++
            resultTV!!.text = getString(R.string.display)
        } else {
            resultTV!!.text = getString(R.string.play)
        }
        questions++
        pointsTV!!.text = getString(R.string.point, score, questions)
        generateQuestion()
    }

    fun answer2(view: View?) {
        if (locationOfCorrectAnswers == 1) {
            score++
            resultTV!!.text = getString(R.string.display)
        } else {
            resultTV!!.text = getString(R.string.play)
        }
        questions++
        pointsTV!!.text = getString(R.string.point, score, questions)
        generateQuestion()
    }

    fun answer3(view: View?) {
        if (locationOfCorrectAnswers == 2) {
            score++
            resultTV!!.text = getString(R.string.display)
        } else {
            resultTV!!.text = getString(R.string.play)
        }
        questions++
        pointsTV!!.text = getString(R.string.point, score, questions)
        generateQuestion()
    }

    fun answer4(view: View?) {
        if (locationOfCorrectAnswers == 3) {
            score++
            resultTV!!.text = getString(R.string.display)
        } else {
            resultTV!!.text = getString(R.string.play)
        }
        questions++
        pointsTV!!.text = getString(R.string.point, score, questions)
        generateQuestion()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn = findViewById(R.id.startButton)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        resultTV = findViewById(R.id.resultTextView)
        pointsTV = findViewById(R.id.pointsTextView)
        sumTV = findViewById(R.id.sumTextView)
        timerTV = findViewById(R.id.timerTextView)
        playAgainBtn = findViewById(R.id.playAgainButton)
        consLayout = findViewById(R.id.consLayout)

        settingsBtn = findViewById(R.id.settingsBtn)
        settingsBtn!!.setOnClickListener {
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

        if (savedPref.contains("Best_Score")) {
            bestScoreInt = savedPref.getInt("Best_Score", 0)

        }
    }
}