package io.github.metalcupcake5.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var timeElapsed = 0L
    private val KEY_BASE = "base"
    private val KEY_RUNNING = "running"
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        running = savedInstanceState?.getBoolean(KEY_RUNNING) ?: false
        if (running){
            chronometer_main_timer.base = savedInstanceState?.getLong(KEY_BASE) ?: 0L
            chronometer_main_timer.start()
        }
        //timeElapsed = savedInstanceState?.getLong(KEY_TIME_ELAPSED) ?: 0L

        var pausedTime = 0L


        button_main_start.setOnClickListener {
            if (running) {
                Toast.makeText(this, "The stopwatch is already running!", Toast.LENGTH_SHORT).show()
            } else {
                if (pausedTime > 0) chronometer_main_timer.base += SystemClock.elapsedRealtime() - pausedTime else chronometer_main_timer.base = SystemClock.elapsedRealtime()
                chronometer_main_timer.start()
                running = true
            }
        }

        button_main_pause.setOnClickListener {
            if (!running) {
                Toast.makeText(this, "The stopwatch is already paused!", Toast.LENGTH_SHORT).show()
            } else {
                chronometer_main_timer.stop()
                pausedTime = SystemClock.elapsedRealtime()
                running = false
            }

        }

        button_main_reset.setOnClickListener {
            pausedTime = 0L
            chronometer_main_timer.base = SystemClock.elapsedRealtime()
            chronometer_main_timer.stop()
            running = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(running){
            outState.putLong(KEY_BASE, chronometer_main_timer.base)
            outState.putBoolean(KEY_RUNNING, true)
        } else {
            outState.putLong(KEY_BASE, chronometer_main_timer.base)
            outState.putBoolean(KEY_RUNNING, false)
        }
    }

    override fun onPause() {
        super.onPause()

    }

}