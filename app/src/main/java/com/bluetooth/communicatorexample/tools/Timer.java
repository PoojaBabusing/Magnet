

package com.bluetooth.communicatorexample.tools;

import android.os.Handler;
import android.os.Looper;

import com.bluetooth.communicator.tools.CustomCountDownTimer;


public class Timer {
    private final long hourMillis=3600000;
    private final long minuteMillis=60000;
    private final long secondMillis=1000;
    private CustomCountDownTimer timer;
    private long duration;
    private Handler mainHandler;

    public Timer(long durationMillis, final DateCallback dateCallback){
        mainHandler = new Handler(Looper.getMainLooper());
        duration=durationMillis;
        timer= new CustomCountDownTimer(durationMillis,1000) {
            @Override
            public void onTick(long millisUntilEnd) {
                int[] date=convertIntoDate(millisUntilEnd);
                dateCallback.onTick(date[0],date[1],date[2]);
            }

            @Override
            public void onFinish() {
                dateCallback.onEnd();
            }
        };
    }

    public void start(){
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                timer.start();
            }
        });
    }

    private int[] convertIntoDate(long millis){
        int hours=0;
        int minutes=0;
        int seconds=0;
        if(millis>hourMillis){
            long rest=millis%hourMillis;
            hours= (int) ((millis-rest)/hourMillis);
            millis=rest;
        }
        if(millis>minuteMillis){
            long rest=millis%minuteMillis;
            minutes= (int) ((millis-rest)/minuteMillis);
            millis=rest;
        }
        if(millis>secondMillis){
            long rest=millis%secondMillis;
            seconds= (int) ((millis-rest)/secondMillis);
        }

        return new int[]{hours,minutes,seconds};
    }

    public interface DateCallback {
        void onTick(int hoursUntilEnd, int minutesUntilEnd, int secondsUntilEnd);
        void onEnd();
    }
}
