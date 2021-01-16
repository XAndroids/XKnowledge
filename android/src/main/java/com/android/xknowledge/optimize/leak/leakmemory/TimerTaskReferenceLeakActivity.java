package com.android.xknowledge.optimize.leak.leakmemory;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

public class TimerTaskReferenceLeakActivity extends TitleActivity {

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timertaskreference_leak);

        startTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                final int secondsRemaining = (int) (millisUntilFinished / 1000);
                //update UI
            }

            @Override
            public void onFinish() {
                //handle onFinish
            }
        };
        countDownTimer.start();
    }

    public void cancelTimer() {
        if (countDownTimer != null) countDownTimer.cancel();
    }

    /*
     * Fix 1: Cancel Timer when
     * activity might be completed
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }
}