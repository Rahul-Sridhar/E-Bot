package com.example.sairahul5223.e_bot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Music_player1 extends Activity {

    private String str2, str6, str7, music_indicator="1";
    private int index=0;
    private ArrayList<String> playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this, "segoeuil.ttf", true);
        setContentView(R.layout.activity_music_player1);
        DisplayMetrics dm=new DisplayMetrics();
        str2=getIntent().getStringExtra("emotion");
        str6=getIntent().getStringExtra("chatting_code");
        str7=getIntent().getStringExtra("chat_response");
        index=getIntent().getIntExtra("index", 0);
        playlist= getIntent().getStringArrayListExtra("playlist");
        TextView text=findViewById(R.id.music_played);
        text.setText(playlist.get(index));
        ImageView iv1=findViewById(R.id.music_previous);
        iv1.clearColorFilter();
        ImageView iv2=findViewById(R.id.music_stop);
        iv2.clearColorFilter();
        ImageView iv3=findViewById(R.id.music_play);
        iv3.clearColorFilter();
        ImageView iv4=findViewById(R.id.music_pause);
        iv4.clearColorFilter();
        ImageView iv5=findViewById(R.id.music_next);
        iv5.clearColorFilter();
        new httpAsyncTask1().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.2));
    }

    public class httpAsyncTask1 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                Log.v("AsyncHTTP", "Get returned: " + playlist.get(index));
                final URL addr1 = new URL("http://172.17.59.97/song_play?nameofsong="+playlist.get(index));
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                String str7=br6.readLine();
                Log.v("AsyncHTTP", "Get returned: " + str7);
                con6.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(java.io.IOException ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

        }

    }

    public class httpAsyncTask2 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/song_stop");
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                String str7=br6.readLine();
                Log.v("AsyncHTTP", "Get returned: " + str7);
                con6.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(java.io.IOException ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            TextView text=findViewById(R.id.music_played);
            text.setText(playlist.get(index));
            String user_name=getIntent().getStringExtra("user_name");
            String internet_connection1=getIntent().getStringExtra("internet_status");
            String language_code=getIntent().getStringExtra("language_code");
            playlist=getIntent().getStringArrayListExtra("playlist");
            index=getIntent().getIntExtra("index", 0);
            Intent intent= new Intent(getBaseContext(), Main4Activity.class);
            intent.putExtra("user_name", user_name);
            intent.putExtra("internet_status", internet_connection1);
            intent.putExtra("language_code", language_code);
            intent.putExtra("playlist", playlist);
            intent.putExtra("index", index);
            intent.putExtra("emotion", str2);
            intent.putExtra("chat_response", str7);
            intent.putExtra("chatting_code", str6);
            intent.putExtra("music_indicator", music_indicator);
            startActivity(intent);
        }

    }

    public class httpAsyncTask3 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/song_pause");
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                String str7=br6.readLine();
                Log.v("AsyncHTTP", "Get returned: " + str7);
                con6.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(java.io.IOException ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

        }

    }

    public class httpAsyncTask4 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                Log.v("AsyncHTTP", "Get returned: " + playlist.get(index));
                final URL addr1 = new URL("http://172.17.59.97/song_prev_next?nameofsong="+playlist.get(index));
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                String str7=br6.readLine();
                Log.v("AsyncHTTP", "Get returned: " + str7);
                con6.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(java.io.IOException ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

        }

    }

    public void prev(View v){
        ImageView iv1=findViewById(R.id.music_previous);
        iv1.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        ImageView iv2=findViewById(R.id.music_stop);
        iv2.clearColorFilter();
        ImageView iv3=findViewById(R.id.music_play);
        iv3.clearColorFilter();
        ImageView iv4=findViewById(R.id.music_pause);
        iv4.clearColorFilter();
        ImageView iv5=findViewById(R.id.music_next);
        iv5.clearColorFilter();
        int len=playlist.size()-1;
        if (index == 0) {
            index=len;
        }else{
            index=index-1;
        }
        TextView text=findViewById(R.id.music_played);
        text.setText(playlist.get(index));
        new httpAsyncTask4().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void stop(View v){
        ImageView iv1=findViewById(R.id.music_previous);
        iv1.clearColorFilter();
        ImageView iv2=findViewById(R.id.music_stop);
        iv2.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        ImageView iv3=findViewById(R.id.music_play);
        iv3.clearColorFilter();
        ImageView iv4=findViewById(R.id.music_pause);
        iv4.clearColorFilter();
        ImageView iv5=findViewById(R.id.music_next);
        iv5.clearColorFilter();
        new httpAsyncTask2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        iv2.clearColorFilter();
    }

    public void play(View v){
        ImageView iv1=findViewById(R.id.music_previous);
        iv1.clearColorFilter();
        ImageView iv2=findViewById(R.id.music_stop);
        iv2.clearColorFilter();
        ImageView iv3=findViewById(R.id.music_play);
        iv3.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        ImageView iv4=findViewById(R.id.music_pause);
        iv4.clearColorFilter();
        ImageView iv5=findViewById(R.id.music_next);
        iv5.clearColorFilter();
        new httpAsyncTask1().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void pause(View v){
        ImageView iv1=findViewById(R.id.music_previous);
        iv1.clearColorFilter();
        ImageView iv2=findViewById(R.id.music_stop);
        iv2.clearColorFilter();
        ImageView iv3=findViewById(R.id.music_play);
        iv3.clearColorFilter();
        ImageView iv4=findViewById(R.id.music_pause);
        iv4.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        ImageView iv5=findViewById(R.id.music_next);
        iv5.clearColorFilter();
        new httpAsyncTask3().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void next(View v){
        ImageView iv1=findViewById(R.id.music_previous);
        iv1.clearColorFilter();
        ImageView iv2=findViewById(R.id.music_stop);
        iv2.clearColorFilter();
        ImageView iv3=findViewById(R.id.music_play);
        iv3.clearColorFilter();
        ImageView iv4=findViewById(R.id.music_pause);
        iv4.clearColorFilter();
        ImageView iv5=findViewById(R.id.music_next);
        iv5.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        int len=playlist.size()-1;
        if(index+1>len){
            index=0;
        }else{
            index=index+1;
        }
        TextView text=findViewById(R.id.music_played);
        text.setText(playlist.get(index));
        new httpAsyncTask4().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
