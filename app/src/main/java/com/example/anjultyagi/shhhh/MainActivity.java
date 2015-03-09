package com.example.anjultyagi.shhhh;

import android.media.AudioManager;
import android.media.Image;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {
    private AudioManager mAudioManager;
    private boolean mPhoneIsSilent;
    private Vibrator vib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        checkIfPhoneIsSilent();
        setGUI();
    }
    private void checkIfPhoneIsSilent(){
        int RingerMode = mAudioManager.getRingerMode();
        if(RingerMode == AudioManager.RINGER_MODE_VIBRATE){
            mPhoneIsSilent = true;
        }
        else {
            mPhoneIsSilent = false;
        }
    }
    private void setGUI(){
        ImageView img = (ImageView)findViewById(R.id.imageView);
        Button but = (Button)findViewById(R.id.button);
        if(mPhoneIsSilent){
            img.setImageResource(R.drawable.silent_on);
            but.setText(R.string.silent_off);
        }
        else{
            img.setImageResource(R.drawable.silent_off);
            but.setText(R.string.silent_on);
        }
    }
    private void vibrate(){
        vib.vibrate(500);
    }
    public void onButtonClick(View v){
        if(mPhoneIsSilent){
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            mPhoneIsSilent = false;
        }
        else{
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            vibrate();
            mPhoneIsSilent=true;
        }
        setGUI();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onResume(){
        super.onResume();
        checkIfPhoneIsSilent();
        setGUI();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
