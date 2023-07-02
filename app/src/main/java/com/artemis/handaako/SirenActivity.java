package com.artemis.handaako;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;

public class SirenActivity extends AppCompatActivity {

    private int clickCounter = 0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siren);

        CardView sirenDismissButton = findViewById(R.id.siren_dismiss_button);
        sirenDismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCounter++;
                if (clickCounter >= 5) {
                    stopSiren();
                    finish();
                }
            }
        });

        LottieAnimationView sirenAnimation = findViewById(R.id.siren_animation);
        sirenAnimation.setMinAndMaxProgress(0f, 1.0f);
        sirenAnimation.playAnimation();

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (audioManager != null) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
        }

        mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.siren_sound);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        // Hide the navigation bar to prevent the user from using navigation buttons
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    private void stopSiren() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        // Do nothing to prevent dismissing the screen with the back button
    }
}