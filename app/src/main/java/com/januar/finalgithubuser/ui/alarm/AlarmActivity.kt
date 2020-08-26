package com.januar.finalgithubuser.ui.alarm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.januar.finalgithubuser.R
import com.januar.finalgithubuser.alarm.AlarmReceiver
import kotlinx.android.synthetic.main.activity_setting.*

class AlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val ar = AlarmReceiver()

        btn_start_alarm.setOnClickListener {
            ar.setAlarmRepeat(this, "09:00")
            Toast.makeText(this, resources.getString(R.string.alarm_active), Toast.LENGTH_SHORT).show()
        }
        btn_stop_alarm.setOnClickListener {
            ar.setAlarmCancel(this)
            Toast.makeText(this, resources.getString(R.string.alarm_cancelled), Toast.LENGTH_SHORT).show()
        }
    }
}