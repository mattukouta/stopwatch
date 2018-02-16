package sample.kouta.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {

    val handler = Handler()
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeText = findViewById(R.id.timeText) as TextView
        val startButton = findViewById(R.id.start) as Button
        val stopButton = findViewById(R.id.stop) as Button
        val resetButton = findViewById(R.id.reset) as Button


        val runnable = object : Runnable {
            override fun run() {
                timeValue++
                timeToText(timeValue)?.let {
                    timeText.text = it
                }
                handler.postDelayed(this, 100)
            }
        }
        startButton.setOnClickListener {
            handler.post(runnable)
        }
        stopButton.setOnClickListener {
            handler.removeCallbacks(runnable)
        }
        resetButton.setOnClickListener {
            handler.removeCallbacks(runnable)
            timeValue = 0
            timeToText(timeValue)?.let {
                timeText.text = it
            }
        }
    }


    fun timeToText(time: Int = 0): String? {
        return if (time < 0) {
            null
        } else if (time == 0) {
            "00:00:00:0"
        } else {
            val h = time / 10 / 3600
            val m = time / 10 % 3600 /60
            val s = time / 10 % 60
            val ms= time % 10
            "%1$02d:%2$02d:%3$02d:%4$01d".format(h, m, s, ms)
        }
    }
}
