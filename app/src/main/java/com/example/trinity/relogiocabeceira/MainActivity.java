package com.example.trinity.relogiocabeceira;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        startBedsideClock();
    }

    private void startBedsideClock() {

        final Calendar calendar = Calendar.getInstance();

        this.mRunnable = new Runnable() {
            @Override
            public void run() {

                calendar.setTimeInMillis(System.currentTimeMillis());

                String hourMinutesFormat = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                String secondsFormat = String.format("%02d", calendar.get(Calendar.SECOND));

                mViewHolder.mTextHourMinute.setText(hourMinutesFormat);
                //mViewHolder.mTextSeconds.setText(String.valueOf(secondsFormat));

                long now = SystemClock.uptimeMillis();
                long next = now + (1000 - (now % 1000));

                mHandler.postAtTime(mRunnable, next);
            }
        };
        this.mRunnable.run();
    }

    private void init() {
        this.mViewHolder.mTextHourMinute = findViewById(R.id.text_hour_minute);
        this.mViewHolder.mTextSeconds = findViewById(R.id.text_seconds);
        this.mViewHolder.mCheckBaterry = findViewById(R.id.check_battery);
    }

    private static class ViewHolder {
        TextView mTextHourMinute;
        TextView mTextSeconds;
        CheckBox mCheckBaterry;
    }
}
