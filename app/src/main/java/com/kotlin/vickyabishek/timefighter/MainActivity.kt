package com.kotlin.vickyabishek.timefighter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //Todo check what is internal and lateinit
    internal lateinit var tapMeButton: Button
    internal lateinit var timeLeftTextView: TextView
    internal lateinit var yourScoreTextView: TextView
    internal var score: Int = 0
    internal var gameStarted: Boolean = false

    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 60000
    internal val countDownDuration: Long = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tap_me)
        timeLeftTextView = findViewById(R.id.score)
        yourScoreTextView = findViewById(R.id.score)

        resetScoresAndValues()

        tapMeButton.setOnClickListener { view ->
            incrementUserScore()
        }
    }

    fun incrementUserScore() {
        if( !gameStarted ) {
            countDownTimer.start()
            gameStarted = true
        }

        ++score;
        var newScore = getString(R.string.your_score, score.toString());
        yourScoreTextView.setText(newScore);
    }

    fun resetScoresAndValues() {
        score = 0
        yourScoreTextView.text = getString(R.string.your_score, score.toString())
        val initialTimeLeft: Long = initialCountDown/1000
        timeLeftTextView.text = getString(R.string.time_left, initialTimeLeft.toString())

        countDownTimer = object: CountDownTimer( initialCountDown, countDownDuration ) {
            override fun onFinish() {

            }

            override fun onTick(millisFinished: Long) {
                val timeLeft = millisFinished / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())
            }


        }

        gameStarted = false
    }
}

//
//Todo list for creating the app
//1. Create visual elements - Buttons, Text labels for Counts, Timer
//2. Create timer logic -
// On click of the button, the timer should start
// and should regularly update it and
// after 60 secs it should stop automatically
//3. Pop up in alert box the score



