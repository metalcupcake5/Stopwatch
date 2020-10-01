package io.github.metalcupcake5.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pausedTime = 0L

        button_main_start.setOnClickListener {
            if (pausedTime > 0) chronometer_main_timer.base += SystemClock.elapsedRealtime() - pausedTime else chronometer_main_timer.base = SystemClock.elapsedRealtime()
            chronometer_main_timer.start()
        }

        button_main_pause.setOnClickListener {
            chronometer_main_timer.stop()
            pausedTime = SystemClock.elapsedRealtime()
        }

        button_main_reset.setOnClickListener {
            pausedTime = 0L
            chronometer_main_timer.base = SystemClock.elapsedRealtime()
            chronometer_main_timer.stop()
        }
    }


}