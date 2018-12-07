package com.coinbkt.medmyth;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class SoundService extends Service {
    private static final String LOGCAT = null;
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.d(LOGCAT, "Service Started!");

        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setLooping(true); //set looping
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        mediaPlayer.start();
        Log.d(LOGCAT, "Media Player started!");
        if(!mediaPlayer.isLooping()){
            Log.d(LOGCAT, "Problem in Playing Audio");
        }

        return Service.START_NOT_STICKY;
    }

    public void onStop(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void onPause(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        stopSelf();
        super.onDestroy();
    }

}
