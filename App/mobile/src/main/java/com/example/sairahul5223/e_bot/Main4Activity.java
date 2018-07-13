package com.example.sairahul5223.e_bot;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Main4Activity extends Activity {

    private List<Item> itemList = new ArrayList<>();
    private List<Item> removed_songs;
    private ArrayList<String> playlist;
    private DialogMultipleChoiceAdapter adapter;
    private float f;
    private String str1, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, language_code, file_name, music_indicator="0";
    private int a=0, b=0, c=0, d=0, e=0, g=0;
    private ImageView imgView;
    private InputStream is;
    Button b41, b42, b43, b44, b45, b46, b47, b48, b49, b410, b411, b412, b413, b414;
    private int index=0;
    Uri uri;
    private static final int REQUEST_CALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*new Main4Activity.httpAsyncTask41().execute();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this, "segoeuil.ttf", true);
        playlist=getIntent().getExtras().getStringArrayList("playlist");
        language_code=getIntent().getStringExtra("language_code");
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        setTitle(resources.getString(R.string.main_activity_toolbar_title));
        imgView = (ImageView) findViewById(R.id.imageView);
        String user_name41=getIntent().getStringExtra("user_name");
        TextView myText41=findViewById(R.id.text41);
        myText41.setText(String.format(resources.getString(R.string.translation18), user_name41));
        str10=getIntent().getStringExtra("help_number");
        Log.v("AsyncHTTPCheck", "Get returned: " + str10);
        music_indicator=getIntent().getStringExtra("music_indicator");
        // Change layout of activity to be displayed initially based if this activity is shown after activity 3 or on pressing stop button on music player
        if(music_indicator.equals("0")==true) {
            new Main4Activity.httpAsyncTask42().execute();
            //Log.v("HTTP", "Get returned: " + String.valueOf(f/1000));
            //TextView myText42=findViewById(R.id.text42);
            //myText42.setText("I am "+String.valueOf(f/1000)+"m away");
            while (true) {

                new Main4Activity.httpAsyncTask41().execute();
                Log.v("HTTP12", "Get returned: " + String.valueOf(f / 1000));
                if ((int) f < 275 == true) {
                    break;
                }
            }
            new Main4Activity.httpAsyncTask411().execute();
            updateViews();
        }else{
            str2=getIntent().getStringExtra("emotion");
            str6=getIntent().getStringExtra("chatting_code");
            str7=getIntent().getStringExtra("chat_response");
            TextView t43=findViewById(R.id.text43);
            translator(R.id.text43, R.string.translation19, "t");
            t43.setVisibility(View.VISIBLE);
            LinearLayout l42=findViewById(R.id.layout42);
            l42.setVisibility(View.VISIBLE);
            translator(R.id.button43, R.string.translation20, "b");
            translator(R.id.button44, R.string.translation21, "b");
            translator(R.id.button45, R.string.translation22, "b");
            Button b45=findViewById(R.id.button45);
            b45.setTextColor(Color.BLACK);
            b45.setBackgroundColor(Color.GRAY);
            LinearLayout l414=findViewById(R.id.layout414);
            l414.setVisibility(View.VISIBLE);
            LinearLayout l41=findViewById(R.id.layout41);
            l41.setVisibility(View.VISIBLE);
            Button b47=findViewById(R.id.button47);
            b47.setVisibility(View.VISIBLE);
            Button b48=findViewById(R.id.button48);
            b48.setVisibility(View.VISIBLE);
            if(str6.charAt(1)=='1'){
                b47.setTextColor(Color.BLACK);
                b47.setBackgroundColor(Color.GRAY);
            }else{
                b48.setTextColor(Color.BLACK);
                b48.setBackgroundColor(Color.GRAY);
            }
            if(str2.equals("You seem happy!")==true){
                translator(R.id.text44, R.string.translation23, "t");
                translator(R.id.text45, R.string.translation24, "t");
                translator(R.id.button47, R.string.translation53, "b");
                translator(R.id.button48, R.string.translation54, "b");
                a=R.string.translation23;
                b=R.string.translation24;
                c=R.string.translation53;
                d=R.string.translation54;
            }else if(str2.equals("Uh oh, you seem angry!")==true){
                translator(R.id.text44, R.string.translation25, "t");
                translator(R.id.text45, R.string.translation26, "t");
                translator(R.id.button47, R.string.translation55, "b");
                translator(R.id.button48, R.string.translation56, "b");
                a=R.string.translation25;
                b=R.string.translation26;
                c=R.string.translation55;
                d=R.string.translation56;
            }else if(str2.equals("You seem surprised!")==true){
                translator(R.id.text44, R.string.translation27, "t");
                translator(R.id.text45, R.string.translation28, "t");
                translator(R.id.button47, R.string.translation57, "b");
                translator(R.id.button48, R.string.translation58, "b");
                a=R.string.translation27;
                b=R.string.translation28;
                c=R.string.translation57;
                d=R.string.translation58;
            }else if(str2.equals("You seem disgusted!")==true){
                translator(R.id.text44, R.string.translation31, "t");
                translator(R.id.text45, R.string.translation32, "t");
                translator(R.id.button47, R.string.translation59, "b");
                translator(R.id.button48, R.string.translation60, "b");
                a=R.string.translation31;
                b=R.string.translation32;
                c=R.string.translation59;
                d=R.string.translation60;
            }else if(str2.equals("You seem scared!")==true){
                translator(R.id.text44, R.string.translation29, "t");
                translator(R.id.text45, R.string.translation30, "t");
                translator(R.id.button47, R.string.translation61, "b");
                translator(R.id.button48, R.string.translation62, "b");
                a=R.string.translation29;
                b=R.string.translation30;
                c=R.string.translation61;
                d=R.string.translation62;
            }else if(str2.equals("You seem sad!")==true){
                translator(R.id.text44, R.string.translation35, "t");
                translator(R.id.text45, R.string.translation36, "t");
                translator(R.id.button47, R.string.translation63, "b");
                translator(R.id.button48, R.string.translation64, "b");
                a=R.string.translation35;
                b=R.string.translation36;
                c=R.string.translation63;
                d=R.string.translation64;
            }else if(str2.equals("You seem to show no emotions!")==true){
                translator(R.id.text44, R.string.translation33, "t");
                translator(R.id.text45, R.string.translation34, "t");
                translator(R.id.button47, R.string.translation65, "b");
                translator(R.id.button48, R.string.translation66, "b");
                a=R.string.translation33;
                b=R.string.translation34;
                c=R.string.translation65;
                d=R.string.translation66;
            }else{
                translator(R.id.text44, R.string.translation37, "t");
                translator(R.id.text45, R.string.translation38, "t");
                a=R.string.translation37;
                b=R.string.translation38;
            }
            LinearLayout l43=findViewById(R.id.layout43);
            l43.setVisibility(View.VISIBLE);
            new Main4Activity.httpAsyncTask45().execute();
            TextView t411=findViewById(R.id.text411);
            if(str7.equals(translator_return1(R.string.translation45))==true){
                translator(R.id.text411, R.string.translation45, "t");
                g=R.string.translation45;
            }else if(str7.equals(translator_return1(R.string.translation46))==true){
                translator(R.id.text411, R.string.translation46, "t");
                g=R.string.translation46;
            }else if(str7.equals(translator_return1(R.string.translation41))==true){
                translator(R.id.text411, R.string.translation41, "t");
                g=R.string.translation41;
            }else if(str7.equals(translator_return1(R.string.translation42))==true){
                translator(R.id.text411, R.string.translation42, "t");
                g=R.string.translation42;
            }else if(str7.equals(translator_return1(R.string.translation52))==true){
                translator(R.id.text411, R.string.translation52, "t");
                g=R.string.translation52;
            }else if(str7.equals(translator_return1(R.string.translation51))==true){
                translator(R.id.text411, R.string.translation51, "t");
                g=R.string.translation51;
            }else if(str7.equals(translator_return1(R.string.translation43))==true){
                translator(R.id.text411, R.string.translation43, "t");
                g=R.string.translation43;
            }else if(str7.equals(translator_return1(R.string.translation44))==true){
                translator(R.id.text411, R.string.translation44, "t");
                g=R.string.translation44;
            }else if(str7.equals(translator_return1(R.string.translation39))==true){
                translator(R.id.text411, R.string.translation39, "t");
                g=R.string.translation39;
            }else if(str7.equals(translator_return1(R.string.translation40))==true){
                translator(R.id.text411, R.string.translation40, "t");
                g=R.string.translation40;
            }else if(str7.equals(translator_return1(R.string.translation49))==true){
                translator(R.id.text411, R.string.translation49, "t");
                g=R.string.translation49;
            }else if(str7.equals(translator_return1(R.string.translation50))==true){
                translator(R.id.text411, R.string.translation50, "t");
                g=R.string.translation50;
            }else if(str7.equals(translator_return1(R.string.translation47))==true){
                translator(R.id.text411, R.string.translation47, "t");
                g=R.string.translation47;
            }else if(str7.equals(translator_return1(R.string.translation48))==true){
                translator(R.id.text411, R.string.translation48, "t");
                g=R.string.translation48;
            }
            t411.setVisibility(View.VISIBLE);
            TextView t415=findViewById(R.id.text415);
            translator(R.id.text415, R.string.translation67, "t");
            t415.setVisibility(View.VISIBLE);
            LinearLayout l411=findViewById(R.id.layout411);
            l411.setVisibility(View.VISIBLE);
            translator(R.id.button411, R.string.translation68, "t");
            translator(R.id.button413, R.string.translation69, "t");
            Button b413=findViewById(R.id.button413);
            b413.setTextColor(Color.BLACK);
            b413.setBackgroundColor(Color.GRAY);
            LinearLayout l415=findViewById(R.id.layout415);
            l415.setVisibility(View.VISIBLE);
            TextView t412=findViewById(R.id.text412);
            t412.setVisibility(View.VISIBLE);
            translator(R.id.text412, R.string.translation71, "t");
            LinearLayout l412=findViewById(R.id.layout412);
            l412.setVisibility(View.VISIBLE);
            translator(R.id.button414, R.string.translation12, "t");
            translator(R.id.button416, R.string.translation13, "t");
            Button b414=findViewById(R.id.button414);
            b414.setTextColor(Color.BLACK);
            b414.setBackgroundColor(Color.GRAY);
            translator(R.id.text413, R.string.translation75, "t");
            TextView t413=findViewById(R.id.text413);
            t413.setVisibility(View.VISIBLE);
            LinearLayout l413=findViewById(R.id.layout413);
            l413.setVisibility(View.VISIBLE);
            translator(R.id.button417, R.string.translation12, "t");
            translator(R.id.button419, R.string.translation13, "t");
        }
    }

    // This function checks for distance between robot and the user
    public class httpAsyncTask41 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //long startTime = System.currentTimeMillis();
                final URL addr = new URL("http://172.17.59.97/distance");
                final HttpURLConnection con = (HttpURLConnection) addr.openConnection();
                InputStream is = con.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                f=Float.parseFloat(br.readLine());
                Log.v("HTTP22", "Get returned: " + String.valueOf(f/1000));
                con.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }
    }

    // This function is executed after robot stops on reaching close enough to the user
    public class httpAsyncTask411 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {

                final URL addr11 = new URL("http://172.17.59.97/distancee");
                final HttpURLConnection con11 = (HttpURLConnection) addr11.openConnection();
                InputStream is11 = con11.getInputStream();
                InputStreamReader isr11 = new InputStreamReader(is11);
                BufferedReader br11 = new BufferedReader(isr11);
                f=Float.parseFloat(br11.readLine());
                Log.v("HTTP", "Get returned: " + String.valueOf(f/1000));
                con11.disconnect();
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            TextView myText43 = findViewById(R.id.text43);
            myText43.setVisibility(View.VISIBLE);
            LinearLayout myText48 = findViewById(R.id.layout42);
            myText48.setVisibility(View.VISIBLE);

        }

    }

    // This function moves the robot towards the user
    public class httpAsyncTask42 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //long startTime = System.currentTimeMillis();
                final URL addr2 = new URL("http://172.17.59.97/move_bot");
                final HttpURLConnection con2 = (HttpURLConnection) addr2.openConnection();
                InputStream is2 = con2.getInputStream();
                InputStreamReader isr2 = new InputStreamReader(is2);
                BufferedReader br2 = new BufferedReader(isr2);
                String str5=br2.readLine();
                Log.v("HTTP", "Get returned: " + str5);
                con2.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }
    }

    // This function displays the start of conversation with user (based on their emotion)
    public class httpAsyncTask43 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //long startTime = System.currentTimeMillis();
                final URL addr2 = new URL("http://172.17.59.97/process");
                final HttpURLConnection con2 = (HttpURLConnection) addr2.openConnection();
                InputStream is2 = con2.getInputStream();
                InputStreamReader isr2 = new InputStreamReader(is2);
                BufferedReader br2 = new BufferedReader(isr2);
                str2=br2.readLine();
                str3=br2.readLine();
                Log.v("HTTP", "Get returned: " + str2+" "+str3);
                con2.disconnect();
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
            TextView myText43 = findViewById(R.id.text43);
            myText43.setVisibility(View.VISIBLE);
            LinearLayout myText48 = findViewById(R.id.layout42);
            myText48.setVisibility(View.VISIBLE);
            Log.v("HTTP2", "Get returned: " + String.valueOf(f/1000));
            LinearLayout myText11 = findViewById(R.id.layout414);
            myText11.setVisibility(View.VISIBLE);
            LinearLayout myText10 = findViewById(R.id.layout41);
            myText10.setVisibility(View.VISIBLE);
            Log.v("HTTP2", str2+" "+str3);
            b44=findViewById(R.id.button47);
            b44.setVisibility(View.VISIBLE);
            b45=findViewById(R.id.button48);
            b45.setVisibility(View.VISIBLE);
            if(str2.equals("You seem happy!")==true){
                str5="H";
                translator(R.id.text44, R.string.translation23, "t");
                translator(R.id.text45, R.string.translation24, "t");
                translator(R.id.button47, R.string.translation53, "b");
                translator(R.id.button48, R.string.translation54, "b");
                a=R.string.translation23;
                b=R.string.translation24;
                c=R.string.translation53;
                d=R.string.translation54;
            }else if(str2.equals("Uh oh, you seem angry!")==true){
                str5="A";
                translator(R.id.text44, R.string.translation25, "t");
                translator(R.id.text45, R.string.translation26, "t");
                translator(R.id.button47, R.string.translation55, "b");
                translator(R.id.button48, R.string.translation56, "b");
                a=R.string.translation25;
                b=R.string.translation26;
                c=R.string.translation55;
                d=R.string.translation56;
            }else if(str2.equals("You seem surprised!")==true){
                str5="Su";
                translator(R.id.text44, R.string.translation27, "t");
                translator(R.id.text45, R.string.translation28, "t");
                translator(R.id.button47, R.string.translation57, "b");
                translator(R.id.button48, R.string.translation58, "b");
                a=R.string.translation27;
                b=R.string.translation28;
                c=R.string.translation57;
                d=R.string.translation58;
            }else if(str2.equals("You seem disgusted!")==true){
                str5="D";
                translator(R.id.text44, R.string.translation31, "t");
                translator(R.id.text45, R.string.translation32, "t");
                translator(R.id.button47, R.string.translation59, "b");
                translator(R.id.button48, R.string.translation60, "b");
                a=R.string.translation31;
                b=R.string.translation32;
                c=R.string.translation59;
                d=R.string.translation60;
            }else if(str2.equals("You seem scared!")==true){
                str5="Sc";
                translator(R.id.text44, R.string.translation29, "t");
                translator(R.id.text45, R.string.translation30, "t");
                translator(R.id.button47, R.string.translation61, "b");
                translator(R.id.button48, R.string.translation62, "b");
                a=R.string.translation29;
                b=R.string.translation30;
                c=R.string.translation61;
                d=R.string.translation62;
            }else if(str2.equals("You seem sad!")==true){
                str5="Sa";
                translator(R.id.text44, R.string.translation35, "t");
                translator(R.id.text45, R.string.translation36, "t");
                translator(R.id.button47, R.string.translation63, "b");
                translator(R.id.button48, R.string.translation64, "b");
                a=R.string.translation35;
                b=R.string.translation36;
                c=R.string.translation63;
                d=R.string.translation64;
            }else if(str2.equals("You seem to show no emotions!")==true){
                str5="N";
                translator(R.id.text44, R.string.translation33, "t");
                translator(R.id.text45, R.string.translation34, "t");
                translator(R.id.button47, R.string.translation65, "b");
                translator(R.id.button48, R.string.translation66, "b");
                a=R.string.translation33;
                b=R.string.translation34;
                c=R.string.translation65;
                d=R.string.translation66;
            }else{
                translator(R.id.text44, R.string.translation37, "t");
                translator(R.id.text45, R.string.translation38, "t");
                a=R.string.translation37;
                b=R.string.translation38;
            }
            LinearLayout l1=findViewById(R.id.layout43);
            l1.setVisibility(View.VISIBLE);
            Button myText49 = findViewById(R.id.button46);
            myText49.setVisibility(View.INVISIBLE);
            if(str2.equals("There seems to be a server error.")){
                Button myText46 = findViewById(R.id.button46);
                myText46.setVisibility(View.VISIBLE);
                b44=findViewById(R.id.button47);
                b44.setVisibility(View.INVISIBLE);
                b45=findViewById(R.id.button48);
                b45.setVisibility(View.INVISIBLE);
                if(str2.equals("I am sorry, I cannot see your face.") && str3.equals("May I try again?")){
                    myText46.setText(translator_return(R.string.translation81));
                    e=R.string.translation81;
                }
                else{
                    translator(R.id.button46, R.string.translation7, "b");
                    e=R.string.translation7;
                }

            }
        }

    }


    // This function instructs the robot to take a picture of the user
    public class httpAsyncTask44 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //long startTime = System.currentTimeMillis();
                final URL addr4 = new URL("http://172.17.59.97/photo");
                final HttpURLConnection con4 = (HttpURLConnection) addr4.openConnection();
                InputStream is4 = con4.getInputStream();
                InputStreamReader isr4 = new InputStreamReader(is4);
                BufferedReader br4 = new BufferedReader(isr4);
                String str6=br4.readLine();
                con4.disconnect();
                Log.v("HTTP3", "Get returned: " + "saved_photo");
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }
    }


    // This function gets the image from Raspberry Pi and displays it as Bitmap on the app
    public class httpAsyncTask45 extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                //long startTime = System.currentTimeMillis();
                final URL addr3 = new URL("http://172.17.59.97/image");
                final HttpURLConnection con3 = (HttpURLConnection) addr3.openConnection();
                Bitmap b = BitmapFactory.decodeStream(con3.getInputStream());
                Log.v("HTTP3", "Get returned: " + "image");
                con3.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
                return b;
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }


        @Override
        protected void onPostExecute(Bitmap b) {
            imgView.setImageBitmap(b);
            TextView myText11 = findViewById(R.id.text411);
            myText11.setVisibility(View.VISIBLE);
        }
    }

    // This function is executed at the end of conversation when the user enters 'No' when asked 'Can I go Now?' the second time
    public class httpAsyncTask46 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //long startTime = System.currentTimeMillis();
                final URL addr3 = new URL("http://172.17.59.97/back_away1");
                final HttpURLConnection con3 = (HttpURLConnection) addr3.openConnection();
                InputStream is3 = con3.getInputStream();
                InputStreamReader isr3 = new InputStreamReader(is3);
                BufferedReader br3 = new BufferedReader(isr3);
                String str5=br3.readLine();
                Log.v("HTTP3", "Get returned: " + str5);
                con3.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            TextView mytext=findViewById(R.id.text416);
            mytext.setVisibility(View.VISIBLE);
            translator(R.id.text416, R.string.translation77, "t");
        }
    }

    // This function moves the robot backwards
    public class httpAsyncTask461 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //long startTime = System.currentTimeMillis();
                final URL addr3 = new URL("http://172.17.59.97/back_away");
                final HttpURLConnection con3 = (HttpURLConnection) addr3.openConnection();
                InputStream is3 = con3.getInputStream();
                InputStreamReader isr3 = new InputStreamReader(is3);
                BufferedReader br3 = new BufferedReader(isr3);
                String str5=br3.readLine();
                Log.v("HTTP3", "Get returned: " + str5);
                con3.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }
    }

     // This function moves the robot backwards
    public class httpAsyncTask462 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //long startTime = System.currentTimeMillis();
                final URL addr3 = new URL("http://172.17.59.97/back_away");
                final HttpURLConnection con3 = (HttpURLConnection) addr3.openConnection();
                InputStream is3 = con3.getInputStream();
                InputStreamReader isr3 = new InputStreamReader(is3);
                BufferedReader br3 = new BufferedReader(isr3);
                String str5=br3.readLine();
                Log.v("HTTP3", "Get returned: " + str5);
                con3.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            TextView mytext=findViewById(R.id.text416);
            mytext.setVisibility(View.VISIBLE);
            translator(R.id.text416, R.string.translation70, "t");
        }
    }

    // This function moves the robot left by 30 degrees
    public class httpAsyncTask47 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //long startTime = System.currentTimeMillis();
                final URL addr4 = new URL("http://172.17.59.97/turn_left");
                final HttpURLConnection con4 = (HttpURLConnection) addr4.openConnection();
                InputStream is4 = con4.getInputStream();
                InputStreamReader isr4 = new InputStreamReader(is4);
                BufferedReader br10 = new BufferedReader(isr4);
                String str5=br10.readLine();
                Log.v("HTTP3", "Get returned: " + str5);
                con4.disconnect();
                //long stopTime = System.currentTimeMillis();
                //long elapsedTime = stopTime - startTime;
                //Log.v("AsyncHTTP", "Get returned: " + Long.toString(elapsedTime));
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }
    }

    // This function moves the robot right by 30 degrees
    public class httpAsyncTask48 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {

                final URL addr5 = new URL("http://172.17.59.97/turn_right");
                final HttpURLConnection con5 = (HttpURLConnection) addr5.openConnection();
                InputStream is5 = con5.getInputStream();
                InputStreamReader isr5 = new InputStreamReader(is5);
                BufferedReader br20 = new BufferedReader(isr5);
                String str5=br20.readLine();
                Log.v("HTTP3", "Get returned: " + str5);
                con5.disconnect();
            }catch(Exception ex) {
                //Log.e("HTTP ASYNC", ex);
            }

            return null;
        }
    }

    // This function displays the continuation of the conversation with the user when they select a button 
    public class httpAsyncTask49 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/chatting?code="+str6);
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                str7=br6.readLine();
                Log.v("AsyncHTTP", "Get returned: " + str6);
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
            LinearLayout l2=findViewById(R.id.layout415);
            l2.setVisibility(View.VISIBLE);
            TextView myText11 = findViewById(R.id.text411);
            myText11.setVisibility(View.VISIBLE);
            if(str7.equals(translator_return1(R.string.translation45))==true){
                translator(R.id.text411, R.string.translation45, "t");
                g=R.string.translation45;
            }else if(str7.equals(translator_return1(R.string.translation46))==true){
                translator(R.id.text411, R.string.translation46, "t");
                g=R.string.translation46;
            }else if(str7.equals(translator_return1(R.string.translation41))==true){
                translator(R.id.text411, R.string.translation41, "t");
                g=R.string.translation41;
            }else if(str7.equals(translator_return1(R.string.translation42))==true){
                translator(R.id.text411, R.string.translation42, "t");
                g=R.string.translation42;
            }else if(str7.equals(translator_return1(R.string.translation52))==true){
                translator(R.id.text411, R.string.translation52, "t");
                g=R.string.translation52;
            }else if(str7.equals(translator_return1(R.string.translation51))==true){
                translator(R.id.text411, R.string.translation51, "t");
                g=R.string.translation51;
            }else if(str7.equals(translator_return1(R.string.translation43))==true){
                translator(R.id.text411, R.string.translation43, "t");
                g=R.string.translation43;
            }else if(str7.equals(translator_return1(R.string.translation44))==true){
                translator(R.id.text411, R.string.translation44, "t");
                g=R.string.translation44;
            }else if(str7.equals(translator_return1(R.string.translation39))==true){
                translator(R.id.text411, R.string.translation39, "t");
                g=R.string.translation39;
            }else if(str7.equals(translator_return1(R.string.translation40))==true){
                translator(R.id.text411, R.string.translation40, "t");
                g=R.string.translation40;
            }else if(str7.equals(translator_return1(R.string.translation49))==true){
                translator(R.id.text411, R.string.translation49, "t");
                g=R.string.translation49;
            }else if(str7.equals(translator_return1(R.string.translation50))==true){
                translator(R.id.text411, R.string.translation50, "t");
                g=R.string.translation50;
            }else if(str7.equals(translator_return1(R.string.translation47))==true){
                translator(R.id.text411, R.string.translation47, "t");
                g=R.string.translation47;
            }else if(str7.equals(translator_return1(R.string.translation48))==true){
                translator(R.id.text411, R.string.translation48, "t");
                g=R.string.translation48;
            }

            TextView myText410 = findViewById(R.id.text415);
            myText410.setVisibility(View.VISIBLE);
            LinearLayout l1=findViewById(R.id.layout411);
            l1.setVisibility(View.VISIBLE);
        }

    }

    public class httpAsyncTask410 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/stay");
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                str7=br6.readLine();
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

    }

    
    // This function plays a song on the robot
    public class httpAsyncTask412 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/song_play");
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                str7=br6.readLine();
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

            b49=findViewById(R.id.button414);
            b49.setEnabled(true);
        }

    }

    // This function plays a song on the robot
    public class httpAsyncTask4121 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/song_play1");
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                str7=br6.readLine();
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
            b49=findViewById(R.id.button414);
            b49.setEnabled(true);
        }

    }

    // The robot asks the user if they would like to call somebody, when this function is executed
    public class httpAsyncTask413 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/call_ask");
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                str7=br6.readLine();
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
            TextView myText11 = findViewById(R.id.text413);
            myText11.setVisibility(View.VISIBLE);
            LinearLayout myText12 = findViewById(R.id.layout413);
            myText12.setVisibility(View.VISIBLE);
        }

    }

    // The robot asks the user if they would like to call somebody, when this function is executed
    public class httpAsyncTask4131 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/call_ask");
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                str7=br6.readLine();
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
            TextView myText11 = findViewById(R.id.text413);
            myText11.setVisibility(View.VISIBLE);
            LinearLayout myText12 = findViewById(R.id.layout413);
            myText12.setVisibility(View.VISIBLE);
            b410=findViewById(R.id.button416);
            b410.setEnabled(true);
        }

    }

    // The robot asks the user if they would like the robot to go, when this function is executed
    public class httpAsyncTask414 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/go_ask");
                final HttpURLConnection con6 = (HttpURLConnection) addr1.openConnection();
                con6.setConnectTimeout(10000);
                InputStream is6 = con6.getInputStream();
                InputStreamReader isr6 = new InputStreamReader(is6);
                BufferedReader br6 = new BufferedReader(isr6);
                str7=br6.readLine();
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
            TextView myText11 = findViewById(R.id.text417);
            myText11.setVisibility(View.VISIBLE);
            LinearLayout myText12 = findViewById(R.id.layout417);
            myText12.setVisibility(View.VISIBLE);
        }

    }

    
    // This function sets the language of communication for the robot
    public class httpAsyncTask415 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                final URL addr1 = new URL("http://172.17.59.97/sound_settings?language="+str11);
                final HttpURLConnection con = (HttpURLConnection) addr1.openConnection();
                con.setConnectTimeout(10000);
                InputStream is = con.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String str2=br.readLine();
                con.disconnect();
            }catch(java.io.IOException ex) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

        }

    }

    // This function sends the name of the song to be deleted as a parameter of the URL
    public class httpAsyncTask416 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                String s=strings[0];
                Log.v("HTTPDelete_Check3", "Get returned: " + s);
                final URL addr1 = new URL("http://172.17.59.97/delete_songs?song_to_be_deleted="+s);
                final HttpURLConnection con = (HttpURLConnection) addr1.openConnection();
                con.setConnectTimeout(10000);
                InputStream is = con.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String str2=br.readLine();
                con.disconnect();
            }catch(java.io.IOException ex) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

        }

    }

    // This function sends a song  from the Android Phone to the Raspberry Pi
    public class httpAsyncTask417 extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... strings) {

            try {
                String url="http://172.17.59.97/post_songs?title="+file_name;
                URL obj=new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add reuqest header
                con.setRequestMethod("POST");

                // Send post request
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                /*BufferedReader r = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = r.readLine()) != null) {
                    wr.writeBytes(line+'\n');
                }*/
                //wr.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + uri.toString() +"\"" + "\r\n");

                byte []buffer = new byte[4096];
                int read = 0;

                while ( (read = is.read(buffer)) != -1 ) {
                    wr.write(buffer, 0, read);
                }
                wr.flush();
                wr.close();

                int responseCode = con.getResponseCode();
                Log.v("HTTPDelete_Check3", "Get returned: " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result

            }catch(java.io.IOException ex) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            playlist.add(file_name);
        }
    }

    // This function is executed when 'Turn Left' button is selected  
    public void onButtonClickName41(View v){
        b41=findViewById(R.id.button43);
        b41.setBackgroundColor(Color.GRAY);
        b41.setTextColor(Color.BLACK);
        b41.setEnabled(false);
        b42=findViewById(R.id.button44);
        b42.setBackgroundColor(Color.BLACK);
        b42.setTextColor(Color.WHITE);
        b43=findViewById(R.id.button45);
        b43.setBackgroundColor(Color.BLACK);
        b43.setTextColor(Color.WHITE);
        b44=findViewById(R.id.button47);
        b44.setBackgroundColor(Color.BLACK);
        b44.setTextColor(Color.WHITE);
        b45=findViewById(R.id.button48);
        b45.setBackgroundColor(Color.BLACK);
        b45.setTextColor(Color.WHITE);
        b46=findViewById(R.id.button46);
        b46.setBackgroundColor(Color.WHITE);
        b46.setTextColor(Color.BLACK);
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.BLACK);
        b47.setTextColor(Color.WHITE);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.BLACK);
        b48.setTextColor(Color.WHITE);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView myText42 = findViewById(R.id.text411);
        myText42.setVisibility(View.INVISIBLE);
        myText42.setText("");
        TextView myText410 = findViewById(R.id.text415);
        myText410.setVisibility(View.INVISIBLE);
        LinearLayout l1=findViewById(R.id.layout411);
        l1.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        LinearLayout myText11 = findViewById(R.id.layout414);
        myText11.setVisibility(View.INVISIBLE);
        LinearLayout myText10 = findViewById(R.id.layout41);
        myText10.setVisibility(View.INVISIBLE);
        LinearLayout l2=findViewById(R.id.layout415);
        l2.setVisibility(View.INVISIBLE);
        LinearLayout myText41 = findViewById(R.id.layout412);
        myText41.setVisibility(View.INVISIBLE);
        TextView myText43 = findViewById(R.id.text412);
        myText43.setVisibility(View.INVISIBLE);
        new Main4Activity.httpAsyncTask47().execute();
        b41.setEnabled(true);
    }

    // This function is executed when 'Turn Right' button is selected
    public void onButtonClickName42(View v){
        b41=findViewById(R.id.button43);
        b41.setBackgroundColor(Color.BLACK);
        b41.setTextColor(Color.WHITE);
        b42=findViewById(R.id.button44);
        b42.setBackgroundColor(Color.GRAY);
        b42.setTextColor(Color.BLACK);
        b42.setEnabled(false);
        b43=findViewById(R.id.button45);
        b43.setBackgroundColor(Color.BLACK);
        b43.setTextColor(Color.WHITE);
        b44=findViewById(R.id.button47);
        b44.setBackgroundColor(Color.BLACK);
        b44.setTextColor(Color.WHITE);
        b45=findViewById(R.id.button48);
        b45.setBackgroundColor(Color.BLACK);
        b45.setTextColor(Color.WHITE);
        b46=findViewById(R.id.button46);
        b46.setBackgroundColor(Color.WHITE);
        b46.setTextColor(Color.BLACK);
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.BLACK);
        b47.setTextColor(Color.WHITE);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.BLACK);
        b48.setTextColor(Color.WHITE);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView myText42 = findViewById(R.id.text411);
        myText42.setVisibility(View.INVISIBLE);
        myText42.setText("");
        TextView myText410 = findViewById(R.id.text415);
        myText410.setVisibility(View.INVISIBLE);
        LinearLayout l1=findViewById(R.id.layout411);
        l1.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        LinearLayout myText11 = findViewById(R.id.layout414);
        myText11.setVisibility(View.INVISIBLE);
        LinearLayout myText10 = findViewById(R.id.layout41);
        myText10.setVisibility(View.INVISIBLE);
        LinearLayout l2=findViewById(R.id.layout415);
        l2.setVisibility(View.INVISIBLE);
        LinearLayout myText41 = findViewById(R.id.layout412);
        myText41.setVisibility(View.INVISIBLE);
        TextView myText43 = findViewById(R.id.text412);
        myText43.setVisibility(View.INVISIBLE);
        new Main4Activity.httpAsyncTask48().execute();
        b42.setEnabled(true);
    }

    // This function is executed when 'Start a conversation' is selected
    public void onButtonClickName43(View v){
        b41=findViewById(R.id.button43);
        b41.setBackgroundColor(Color.BLACK);
        b41.setTextColor(Color.WHITE);
        b42=findViewById(R.id.button44);
        b42.setBackgroundColor(Color.BLACK);
        b42.setTextColor(Color.WHITE);
        b43=findViewById(R.id.button45);
        b43.setBackgroundColor(Color.GRAY);
        b43.setTextColor(Color.BLACK);
        b43.setEnabled(false);
        b44=findViewById(R.id.button47);
        b44.setBackgroundColor(Color.BLACK);
        b44.setTextColor(Color.WHITE);
        b45=findViewById(R.id.button48);
        b45.setBackgroundColor(Color.BLACK);
        b45.setTextColor(Color.WHITE);
        b46=findViewById(R.id.button46);
        b46.setBackgroundColor(Color.WHITE);
        b46.setTextColor(Color.BLACK);
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.BLACK);
        b47.setTextColor(Color.WHITE);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.BLACK);
        b48.setTextColor(Color.WHITE);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView myText42 = findViewById(R.id.text411);
        myText42.setVisibility(View.INVISIBLE);
        myText42.setText("");
        TextView myText410 = findViewById(R.id.text415);
        myText410.setVisibility(View.INVISIBLE);
        LinearLayout l1=findViewById(R.id.layout411);
        l1.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        LinearLayout myText11 = findViewById(R.id.layout414);
        myText11.setVisibility(View.INVISIBLE);
        LinearLayout myText10 = findViewById(R.id.layout41);
        myText10.setVisibility(View.INVISIBLE);
        LinearLayout l2=findViewById(R.id.layout415);
        l2.setVisibility(View.INVISIBLE);
        LinearLayout myText41 = findViewById(R.id.layout412);
        myText41.setVisibility(View.INVISIBLE);
        TextView myText43 = findViewById(R.id.text412);
        myText43.setVisibility(View.INVISIBLE);
        new Main4Activity.httpAsyncTask44().execute();
        new Main4Activity.httpAsyncTask43().execute();
        new Main4Activity.httpAsyncTask45().execute();
        b43.setEnabled(true);
    }

    // This function is executed when the mic button is selected under 'Look at me. Lets have a chat'
    public void onButtonClickName44(View v){
        b41=findViewById(R.id.button43);
        b41.setBackgroundColor(Color.BLACK);
        b41.setTextColor(Color.WHITE);
        b42=findViewById(R.id.button44);
        b42.setBackgroundColor(Color.BLACK);
        b42.setTextColor(Color.WHITE);
        b43=findViewById(R.id.button45);
        b43.setBackgroundColor(Color.BLACK);
        b43.setTextColor(Color.WHITE);
        b44=findViewById(R.id.button47);
        b44.setBackgroundColor(Color.BLACK);
        b44.setTextColor(Color.WHITE);
        b45=findViewById(R.id.button48);
        b45.setBackgroundColor(Color.BLACK);
        b45.setTextColor(Color.WHITE);
        b46=findViewById(R.id.button46);
        b46.setBackgroundColor(Color.WHITE);
        b46.setTextColor(Color.BLACK);
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.BLACK);
        b47.setTextColor(Color.WHITE);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.BLACK);
        b48.setTextColor(Color.WHITE);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView myText42 = findViewById(R.id.text411);
        myText42.setVisibility(View.INVISIBLE);
        myText42.setText("");
        TextView myText410 = findViewById(R.id.text415);
        myText410.setVisibility(View.INVISIBLE);
        LinearLayout l1=findViewById(R.id.layout411);
        l1.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        LinearLayout myText11 = findViewById(R.id.layout414);
        myText11.setVisibility(View.INVISIBLE);
        LinearLayout myText10 = findViewById(R.id.layout41);
        myText10.setVisibility(View.INVISIBLE);
        LinearLayout l2=findViewById(R.id.layout415);
        l2.setVisibility(View.INVISIBLE);
        LinearLayout myText41 = findViewById(R.id.layout412);
        myText41.setVisibility(View.INVISIBLE);
        TextView myText43 = findViewById(R.id.text412);
        myText43.setVisibility(View.INVISIBLE);
        ImageView iv1=findViewById(R.id.speaker_button5);
        iv1.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        String user_name43=getIntent().getStringExtra("user_name");
        String internet_connection1=getIntent().getStringExtra("internet_status");
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra("user_name", user_name43);
        intent.putExtra("internet_status", internet_connection1);
        intent.putExtra("language_code", language_code);
        intent.putExtra("playlist", playlist);
        str8="A";
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, 10);
        }else{
            Toast.makeText(this, translator_return(R.string.translation78), Toast.LENGTH_SHORT).show();
        }

    }

    // This function is executed when 'Try Again' button is selected which appears when there exists a problem in connection between Raspberry Pi and Android Phone
    public void onButtonClickName45(View v){
        b44=findViewById(R.id.button47);
        b44.setBackgroundColor(Color.BLACK);
        b44.setTextColor(Color.WHITE);
        b45=findViewById(R.id.button48);
        b45.setBackgroundColor(Color.BLACK);
        b45.setTextColor(Color.WHITE);
        b46=findViewById(R.id.button46);
        b46.setBackgroundColor(Color.GRAY);
        b46.setTextColor(Color.BLACK);
        b46.setEnabled(false);
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.BLACK);
        b47.setTextColor(Color.WHITE);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.BLACK);
        b48.setTextColor(Color.WHITE);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView myText11 = findViewById(R.id.text411);
        myText11.setVisibility(View.INVISIBLE);
        TextView myText410 = findViewById(R.id.text415);
        myText410.setVisibility(View.INVISIBLE);
        LinearLayout l1=findViewById(R.id.layout411);
        l1.setVisibility(View.INVISIBLE);
        LinearLayout l2=findViewById(R.id.layout415);
        l2.setVisibility(View.INVISIBLE);
        TextView myText411 = findViewById(R.id.text412);
        myText411.setVisibility(View.INVISIBLE);
        LinearLayout l3=findViewById(R.id.layout412);
        l3.setVisibility(View.INVISIBLE);
        TextView myText412 = findViewById(R.id.text413);
        myText412.setVisibility(View.INVISIBLE);
        LinearLayout l4=findViewById(R.id.layout413);
        l4.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        new Main4Activity.httpAsyncTask44().execute();
        new Main4Activity.httpAsyncTask43().execute();
        new Main4Activity.httpAsyncTask45().execute();
        b46.setEnabled(true);
    }

    // This function is executed when the button on the left is selected right below the beginning of the conversation 
    public void onButtonClickName48(View v){
        b44=findViewById(R.id.button47);
        b44.setBackgroundColor(Color.GRAY);
        b44.setTextColor(Color.BLACK);
        b44.setEnabled(false);
        b45=findViewById(R.id.button48);
        b45.setBackgroundColor(Color.BLACK);
        b45.setTextColor(Color.WHITE);
        b46=findViewById(R.id.button46);
        b46.setBackgroundColor(Color.BLACK);
        b46.setTextColor(Color.WHITE);
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.BLACK);
        b47.setTextColor(Color.WHITE);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.BLACK);
        b48.setTextColor(Color.WHITE);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView myText11 = findViewById(R.id.text411);
        myText11.setVisibility(View.INVISIBLE);
        TextView myText410 = findViewById(R.id.text415);
        myText410.setVisibility(View.INVISIBLE);
        LinearLayout l1=findViewById(R.id.layout411);
        l1.setVisibility(View.INVISIBLE);
        LinearLayout l2=findViewById(R.id.layout415);
        l2.setVisibility(View.INVISIBLE);
        TextView myText411 = findViewById(R.id.text412);
        myText411.setVisibility(View.INVISIBLE);
        LinearLayout l3=findViewById(R.id.layout412);
        l3.setVisibility(View.INVISIBLE);
        TextView myText412 = findViewById(R.id.text413);
        myText412.setVisibility(View.INVISIBLE);
        LinearLayout l4=findViewById(R.id.layout413);
        l4.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        str6="";
        str6=str5+"1";
        new Main4Activity.httpAsyncTask49().execute();
        b44.setEnabled(true);
    }

    // This function is executed when the button on the left is selected right below the beginning of the conversation
    public void onButtonClickName49(View v){
        b44=findViewById(R.id.button47);
        b44.setBackgroundColor(Color.BLACK);
        b44.setTextColor(Color.WHITE);
        b45=findViewById(R.id.button48);
        b45.setBackgroundColor(Color.GRAY);
        b45.setTextColor(Color.BLACK);
        b45.setEnabled(false);
        b46=findViewById(R.id.button46);
        b46.setBackgroundColor(Color.BLACK);
        b46.setTextColor(Color.WHITE);
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.BLACK);
        b47.setTextColor(Color.WHITE);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.BLACK);
        b48.setTextColor(Color.WHITE);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView myText11 = findViewById(R.id.text411);
        myText11.setVisibility(View.INVISIBLE);
        TextView myText410 = findViewById(R.id.text415);
        myText410.setVisibility(View.INVISIBLE);
        LinearLayout l1=findViewById(R.id.layout411);
        l1.setVisibility(View.INVISIBLE);
        LinearLayout l2=findViewById(R.id.layout415);
        l2.setVisibility(View.INVISIBLE);
        TextView myText411 = findViewById(R.id.text412);
        myText411.setVisibility(View.INVISIBLE);
        LinearLayout l3=findViewById(R.id.layout412);
        l3.setVisibility(View.INVISIBLE);
        TextView myText412 = findViewById(R.id.text413);
        myText412.setVisibility(View.INVISIBLE);
        LinearLayout l4=findViewById(R.id.layout413);
        l4.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        str6="";
        str6=str5+"2";
        new Main4Activity.httpAsyncTask49().execute();
        b45.setEnabled(true);
    }

    // This function is executed when 'Yes' button is selected when 'Would you like me to stay?' is asked
    public void onButtonClickName411(View v){
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.GRAY);
        b47.setTextColor(Color.BLACK);
        b47.setEnabled(false);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.BLACK);
        b48.setTextColor(Color.WHITE);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        LinearLayout myText15 = findViewById(R.id.layout415);
        myText15.setVisibility(View.VISIBLE);
        TextView myText11 = findViewById(R.id.text412);
        myText11.setVisibility(View.VISIBLE);
        translator(R.id.text412, R.string.translation70, "t");
        LinearLayout myText12 = findViewById(R.id.layout412);
        myText12.setVisibility(View.INVISIBLE);
        TextView myText13 = findViewById(R.id.text413);
        myText13.setVisibility(View.INVISIBLE);
        LinearLayout myText14 = findViewById(R.id.layout413);
        myText14.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        new Main4Activity.httpAsyncTask461().execute();
        b47.setEnabled(true);
    }

    // This function is executed when mic button is selected when 'Would you like me to stay?' is asked 
    public void onButtonClickName412(View v){
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.BLACK);
        b47.setTextColor(Color.WHITE);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.BLACK);
        b48.setTextColor(Color.WHITE);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        ImageView iv41=findViewById(R.id.speaker_button412);
        iv41.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        String user_name43=getIntent().getStringExtra("user_name");
        String internet_connection1=getIntent().getStringExtra("internet_status");
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra("user_name", user_name43);
        intent.putExtra("internet_status", internet_connection1);
        intent.putExtra("language_code", language_code);
        intent.putExtra("playlist", playlist);
        intent.putExtra("help_number", str10);
        str8="B";
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, 10);
        }else{
            Toast.makeText(this, translator_return(R.string.translation78), Toast.LENGTH_SHORT).show();
        }

    }

    // This function is executed when 'No' button is selected when 'Would you like me to stay?' is asked 
    public void onButtonClickName413(View v){
        b47=findViewById(R.id.button411);
        b47.setBackgroundColor(Color.BLACK);
        b47.setTextColor(Color.WHITE);
        b48=findViewById(R.id.button413);
        b48.setBackgroundColor(Color.GRAY);
        b48.setTextColor(Color.BLACK);
        b48.setEnabled(false);
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        new Main4Activity.httpAsyncTask410().execute();
        LinearLayout myText15 = findViewById(R.id.layout415);
        myText15.setVisibility(View.VISIBLE);
        TextView myText11 = findViewById(R.id.text412);
        myText11.setVisibility(View.VISIBLE);
        translator(R.id.text412, R.string.translation71, "t");
        LinearLayout myText12 = findViewById(R.id.layout412);
        myText12.setVisibility(View.VISIBLE);
        TextView myText13 = findViewById(R.id.text413);
        myText13.setVisibility(View.INVISIBLE);
        LinearLayout myText14 = findViewById(R.id.layout413);
        myText14.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        b48.setEnabled(true);
    }

    // This function is executed when 'Yes' button is selected when 'Should I play a song for you?' is asked 
    public void onButtonClickName414(View v){
        str9="0";
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.GRAY);
        b49.setTextColor(Color.BLACK);
        b49.setEnabled(false);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView myText11 = findViewById(R.id.text413);
        myText11.setVisibility(View.INVISIBLE);
        LinearLayout myText12 = findViewById(R.id.layout413);
        myText12.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        /*final AlertDialog alertDialog=new AlertDialog.Builder(this).setTitle("")
                .setMessage(translator_return(R.string.translation72))
                .setPositiveButton(translator_return(R.string.translation73), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(translator_return(R.string.translation74), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
        TextView messageView = alertDialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str12="0";
                str9="1";
                new Main4Activity.httpAsyncTask412().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                if(str12.equals("1")){
                    alertDialog.dismiss();
                }
            }
        });
        Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Main4Activity.httpAsyncTask4121().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                new Main4Activity.httpAsyncTask413().execute();
                alertDialog.dismiss();
            }
        });
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        layoutParams.weight = 10;
        btnPositive.setLayoutParams(layoutParams);
        btnNegative.setLayoutParams(layoutParams);
        if(str9.equals("1")){
            new Main4Activity.httpAsyncTask4121().execute();
            new Main4Activity.httpAsyncTask413().execute();
        }*/
        String user_name43=getIntent().getStringExtra("user_name");
        String internet_connection1=getIntent().getStringExtra("internet_status");
        Intent intent= new Intent(getBaseContext(), Music_player1.class);
        intent.putExtra("user_name", user_name43);
        intent.putExtra("internet_status", internet_connection1);
        intent.putExtra("language_code", language_code);
        intent.putExtra("playlist", playlist);
        intent.putExtra("index", index);
        intent.putExtra("help_number", str10);
        intent.putExtra("emotion", str2);
        intent.putExtra("chat_response", str7);
        intent.putExtra("chatting_code", str6);
        startActivity(intent);
        new Main4Activity.httpAsyncTask413().execute();
        b49=findViewById(R.id.button414);
        b49.setEnabled(true);
    }

    // This function is executed when mic button is selected when 'Should I play a song for you?' is asked 
    public void onButtonClickName415(View v){
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.BLACK);
        b410.setTextColor(Color.WHITE);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        ImageView iv41=findViewById(R.id.speaker_button415);
        iv41.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        String user_name43=getIntent().getStringExtra("user_name");
        String internet_connection1=getIntent().getStringExtra("internet_status");
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra("user_name", user_name43);
        intent.putExtra("internet_status", internet_connection1);
        intent.putExtra("language_code", language_code);
        intent.putExtra("playlist", playlist);
        intent.putExtra("help_number", str10);
        str8="C";
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, 10);
        }else{
            Toast.makeText(this, translator_return(R.string.translation78), Toast.LENGTH_SHORT).show();
        }
    }

    // This function is executed when 'No' button is selected when 'Should I play a song for you?' is asked 
    public void onButtonClickName416(View v){
        b49=findViewById(R.id.button414);
        b49.setBackgroundColor(Color.BLACK);
        b49.setTextColor(Color.WHITE);
        b410=findViewById(R.id.button416);
        b410.setBackgroundColor(Color.GRAY);
        b410.setTextColor(Color.BLACK);
        b410.setEnabled(false);
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView myText11 = findViewById(R.id.text413);
        myText11.setVisibility(View.INVISIBLE);
        LinearLayout myText12 = findViewById(R.id.layout413);
        myText12.setVisibility(View.INVISIBLE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        new Main4Activity.httpAsyncTask4131().execute();
    }

    // This function is executed when 'Yes' button is selected when 'Should I call for help?' is asked 
    public void onButtonClickName417(View v){
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.GRAY);
        b411.setTextColor(Color.BLACK);
        b411.setEnabled(false);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext43=findViewById(R.id.text416);
        mytext43.setVisibility(View.INVISIBLE);
        makePhoneCall();
        new Main4Activity.httpAsyncTask414().execute();
        b411.setEnabled(true);
    }

    // This function is executed when mic button is selected when 'Should I call for help?' is asked 
    public void onButtonClickName418(View v){
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.BLACK);
        b412.setTextColor(Color.WHITE);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        ImageView iv41=findViewById(R.id.speaker_button418);
        iv41.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        String user_name43=getIntent().getStringExtra("user_name");
        String internet_connection1=getIntent().getStringExtra("internet_status");
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra("user_name", user_name43);
        intent.putExtra("internet_status", internet_connection1);
        intent.putExtra("language_code", language_code);
        intent.putExtra("playlist", playlist);
        intent.putExtra("help_number", str10);
        str8="D";
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, 10);
        }else{
            Toast.makeText(this, translator_return(R.string.translation78), Toast.LENGTH_SHORT).show();
        }
    }

    // This function is executed when 'No' button is selected when 'Should I call for help?' is asked 
    public void onButtonClickName419(View v){
        b411=findViewById(R.id.button417);
        b411.setBackgroundColor(Color.BLACK);
        b411.setTextColor(Color.WHITE);
        b412=findViewById(R.id.button419);
        b412.setBackgroundColor(Color.GRAY);
        b412.setTextColor(Color.BLACK);
        b412.setEnabled(false);
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView mytext=findViewById(R.id.text417);
        mytext.setVisibility(View.INVISIBLE);
        LinearLayout lay2=findViewById(R.id.layout417);
        lay2.setVisibility(View.INVISIBLE);
        TextView mytext41=findViewById(R.id.text416);
        mytext41.setVisibility(View.INVISIBLE);
        new Main4Activity.httpAsyncTask414().execute();
        b412.setEnabled(true);
    }

    // This function is executed when 'Yes' button is selected when 'Can I go Now?' is asked 
    public void onButtonClickName420(View v){
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.GRAY);
        b413.setTextColor(Color.BLACK);
        b413.setEnabled(false);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        TextView mytext42=findViewById(R.id.text416);
        mytext42.setVisibility(View.INVISIBLE);
        new Main4Activity.httpAsyncTask462().execute();
        b413.setEnabled(true);
    }

    // This function is executed when mic button is selected when 'Can I go Now?' is asked
    public void onButtonClickName421(View v){
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.BLACK);
        b414.setTextColor(Color.WHITE);
        ImageView iv41=findViewById(R.id.speaker_button421);
        iv41.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        String user_name43=getIntent().getStringExtra("user_name");
        String internet_connection1=getIntent().getStringExtra("internet_status");
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra("user_name", user_name43);
        intent.putExtra("internet_status", internet_connection1);
        intent.putExtra("language_code", language_code);
        intent.putExtra("playlist", playlist);
        intent.putExtra("help_number", str10);
        str8="E";
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, 10);
        }else{
            Toast.makeText(this, translator_return(R.string.translation79), Toast.LENGTH_SHORT).show();
        }
    }

    // This function is executed when 'No' button is selected when 'Can I go Now?' is asked
    public void onButtonClickName422(View v){
        b413=findViewById(R.id.button420);
        b413.setBackgroundColor(Color.BLACK);
        b413.setTextColor(Color.WHITE);
        b414=findViewById(R.id.button422);
        b414.setBackgroundColor(Color.GRAY);
        b414.setTextColor(Color.BLACK);
        b414.setEnabled(false);
        TextView mytext42=findViewById(R.id.text416);
        mytext42.setVisibility(View.INVISIBLE);
        new Main4Activity.httpAsyncTask46().execute();
        b414.setEnabled(true);
    }


    // Override the method for execution of back button twice
    Boolean doubleBackPressed=false;
    @Override
    public void onBackPressed() {
        if(doubleBackPressed) {
            Intent myIntent43= new Intent(getBaseContext(), MainActivity.class);
            myIntent43.putExtra("language_code", language_code);
            myIntent43.putExtra("playlist", playlist);
            myIntent43.putExtra("help_number", str10);
            startActivity(myIntent43);
        }else{
            doubleBackPressed=true;
            Toast.makeText(getApplicationContext(), translator_return(R.string.translation80), Toast.LENGTH_SHORT).show();
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackPressed=false;
                }
            }, 2000);
        }
    }

    // Override function which executes a set of statements based on voice input from the user
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case 10:
                if(resultCode==RESULT_OK && data!=null){
                    String user_name45=getIntent().getStringExtra("user_name");
                    String internet_connection3=getIntent().getStringExtra("internet_status");
                    ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.v("HTTP", "Get returned: " + result.get(0));
                    str4=result.get(0);
                    if(str8.equals("A")) {
                        if (str4.equals("turn left") == true || str4.equals("left") == true || str4.equals("go away") == true || str4.equals("转左") == true || str4.equals("zhuan zuo") == true || str4.equals("剩下") == true || str4.equals("sheng xia\n") == true || str4.equals("走开") == true || str4.equals("zou kai\n") == true) {
                            b41 = findViewById(R.id.button43);
                            b41.setBackgroundColor(Color.GRAY);
                            b41.setTextColor(Color.BLACK);
                            b41.setEnabled(false);
                            b42 = findViewById(R.id.button44);
                            b42.setBackgroundColor(Color.BLACK);
                            b42.setTextColor(Color.WHITE);
                            b43 = findViewById(R.id.button45);
                            b43.setBackgroundColor(Color.BLACK);
                            b43.setTextColor(Color.WHITE);
                            b44=findViewById(R.id.button47);
                            b44.setBackgroundColor(Color.BLACK);
                            b44.setTextColor(Color.WHITE);
                            b45=findViewById(R.id.button48);
                            b45.setBackgroundColor(Color.BLACK);
                            b45.setTextColor(Color.WHITE);
                            b46=findViewById(R.id.button46);
                            b46.setBackgroundColor(Color.WHITE);
                            b46.setTextColor(Color.BLACK);
                            b47=findViewById(R.id.button411);
                            b47.setBackgroundColor(Color.BLACK);
                            b47.setTextColor(Color.WHITE);
                            b48=findViewById(R.id.button413);
                            b48.setBackgroundColor(Color.BLACK);
                            b48.setTextColor(Color.WHITE);
                            b49=findViewById(R.id.button414);
                            b49.setBackgroundColor(Color.BLACK);
                            b49.setTextColor(Color.WHITE);
                            b410=findViewById(R.id.button416);
                            b410.setBackgroundColor(Color.BLACK);
                            b410.setTextColor(Color.WHITE);
                            b411=findViewById(R.id.button417);
                            b411.setBackgroundColor(Color.BLACK);
                            b411.setTextColor(Color.WHITE);
                            b412=findViewById(R.id.button419);
                            b412.setBackgroundColor(Color.BLACK);
                            b412.setTextColor(Color.WHITE);
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            LinearLayout myText11 = findViewById(R.id.layout414);
                            myText11.setVisibility(View.INVISIBLE);
                            LinearLayout myText10 = findViewById(R.id.layout41);
                            myText10.setVisibility(View.INVISIBLE);
                            Log.v("HTTP", "Get returned: " + str4);
                            new Main4Activity.httpAsyncTask47().execute();
                            ImageView iv1 = findViewById(R.id.speaker_button5);
                            iv1.clearColorFilter();
                            b41.setEnabled(true);
                        } else if (str4.equals("turn right") == true || str4.equals("right") == true || str4.equals("右转") == true || str4.equals("you zhuan") == true || str4.equals("对") == true || str4.equals("dui") == true) {
                            b41 = findViewById(R.id.button43);
                            b41.setBackgroundColor(Color.BLACK);
                            b41.setTextColor(Color.WHITE);
                            b42 = findViewById(R.id.button44);
                            b42.setBackgroundColor(Color.GRAY);
                            b42.setTextColor(Color.BLACK);
                            b42.setEnabled(false);
                            b43 = findViewById(R.id.button45);
                            b43.setBackgroundColor(Color.BLACK);
                            b43.setTextColor(Color.WHITE);
                            b44=findViewById(R.id.button47);
                            b44.setBackgroundColor(Color.BLACK);
                            b44.setTextColor(Color.WHITE);
                            b45=findViewById(R.id.button48);
                            b45.setBackgroundColor(Color.BLACK);
                            b45.setTextColor(Color.WHITE);
                            b46=findViewById(R.id.button46);
                            b46.setBackgroundColor(Color.WHITE);
                            b46.setTextColor(Color.BLACK);
                            b47=findViewById(R.id.button411);
                            b47.setBackgroundColor(Color.BLACK);
                            b47.setTextColor(Color.WHITE);
                            b48=findViewById(R.id.button413);
                            b48.setBackgroundColor(Color.BLACK);
                            b48.setTextColor(Color.WHITE);
                            b49=findViewById(R.id.button414);
                            b49.setBackgroundColor(Color.BLACK);
                            b49.setTextColor(Color.WHITE);
                            b410=findViewById(R.id.button416);
                            b410.setBackgroundColor(Color.BLACK);
                            b410.setTextColor(Color.WHITE);
                            b411=findViewById(R.id.button417);
                            b411.setBackgroundColor(Color.BLACK);
                            b411.setTextColor(Color.WHITE);
                            b412=findViewById(R.id.button419);
                            b412.setBackgroundColor(Color.BLACK);
                            b412.setTextColor(Color.WHITE);
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            LinearLayout myText11 = findViewById(R.id.layout414);
                            myText11.setVisibility(View.INVISIBLE);
                            LinearLayout myText10 = findViewById(R.id.layout41);
                            myText10.setVisibility(View.INVISIBLE);
                            new Main4Activity.httpAsyncTask48().execute();
                            ImageView iv1 = findViewById(R.id.speaker_button5);
                            iv1.clearColorFilter();
                            b42.setEnabled(true);
                        } else {
                            b41 = findViewById(R.id.button43);
                            b41.setBackgroundColor(Color.BLACK);
                            b41.setTextColor(Color.WHITE);
                            b42 = findViewById(R.id.button44);
                            b42.setBackgroundColor(Color.BLACK);
                            b42.setTextColor(Color.WHITE);
                            b43 = findViewById(R.id.button45);
                            b43.setBackgroundColor(Color.GRAY);
                            b43.setTextColor(Color.BLACK);
                            b43.setEnabled(false);
                            b44=findViewById(R.id.button47);
                            b44.setBackgroundColor(Color.BLACK);
                            b44.setTextColor(Color.WHITE);
                            b45=findViewById(R.id.button48);
                            b45.setBackgroundColor(Color.BLACK);
                            b45.setTextColor(Color.WHITE);
                            b46=findViewById(R.id.button46);
                            b46.setBackgroundColor(Color.WHITE);
                            b46.setTextColor(Color.BLACK);
                            b47=findViewById(R.id.button411);
                            b47.setBackgroundColor(Color.BLACK);
                            b47.setTextColor(Color.WHITE);
                            b48=findViewById(R.id.button413);
                            b48.setBackgroundColor(Color.BLACK);
                            b48.setTextColor(Color.WHITE);
                            b49=findViewById(R.id.button414);
                            b49.setBackgroundColor(Color.BLACK);
                            b49.setTextColor(Color.WHITE);
                            b410=findViewById(R.id.button416);
                            b410.setBackgroundColor(Color.BLACK);
                            b410.setTextColor(Color.WHITE);
                            b411=findViewById(R.id.button417);
                            b411.setBackgroundColor(Color.BLACK);
                            b411.setTextColor(Color.WHITE);
                            b412=findViewById(R.id.button419);
                            b412.setBackgroundColor(Color.BLACK);
                            b412.setTextColor(Color.WHITE);
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            LinearLayout myText11 = findViewById(R.id.layout414);
                            myText11.setVisibility(View.INVISIBLE);
                            LinearLayout myText10 = findViewById(R.id.layout41);
                            myText10.setVisibility(View.INVISIBLE);
                            new Main4Activity.httpAsyncTask44().execute();
                            new Main4Activity.httpAsyncTask43().execute();
                            new Main4Activity.httpAsyncTask45().execute();
                            ImageView iv1 = findViewById(R.id.speaker_button5);
                            iv1.clearColorFilter();
                            b43.setEnabled(true);
                        }
                    }else if(str8.equals("B")){
                        if (str4.equals("move back") == true || str4.equals("back") == true || str4.equals("back away") == true || str4.equals("go") == true || str4.equals("get lost") == true || str4.equals("退回") == true || str4.equals("tuihui") == true || str4.equals("背部") == true || str4.equals("beibu") == true || str4.equals("回去") == true || str4.equals("huiqu") == true || str4.equals("走") == true || str4.equals("zou") == true || str4.equals("走开") == true || str4.equals("zou kai") == true) {
                            b47=findViewById(R.id.button411);
                            b47.setBackgroundColor(Color.GRAY);
                            b47.setTextColor(Color.BLACK);
                            b47.setEnabled(false);
                            b48=findViewById(R.id.button413);
                            b48.setBackgroundColor(Color.BLACK);
                            b48.setTextColor(Color.WHITE);
                            b49=findViewById(R.id.button414);
                            b49.setBackgroundColor(Color.BLACK);
                            b49.setTextColor(Color.WHITE);
                            b410=findViewById(R.id.button416);
                            b410.setBackgroundColor(Color.BLACK);
                            b410.setTextColor(Color.WHITE);
                            b411=findViewById(R.id.button417);
                            b411.setBackgroundColor(Color.BLACK);
                            b411.setTextColor(Color.WHITE);
                            b412=findViewById(R.id.button419);
                            b412.setBackgroundColor(Color.BLACK);
                            b412.setTextColor(Color.WHITE);
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            LinearLayout myText15 = findViewById(R.id.layout415);
                            myText15.setVisibility(View.VISIBLE);
                            TextView myText11 = findViewById(R.id.text412);
                            myText11.setVisibility(View.VISIBLE);
                            translator(R.id.text412, R.string.translation70, "t");
                            LinearLayout myText12 = findViewById(R.id.layout412);
                            myText12.setVisibility(View.INVISIBLE);
                            TextView myText13 = findViewById(R.id.text413);
                            myText13.setVisibility(View.INVISIBLE);
                            LinearLayout myText14 = findViewById(R.id.layout413);
                            myText14.setVisibility(View.INVISIBLE);
                            new Main4Activity.httpAsyncTask461().execute();
                            ImageView iv1 = findViewById(R.id.speaker_button412);
                            iv1.clearColorFilter();
                            b47.setEnabled(true);
                        }else{
                            b47=findViewById(R.id.button411);
                            b47.setBackgroundColor(Color.BLACK);
                            b47.setTextColor(Color.WHITE);
                            b48=findViewById(R.id.button413);
                            b48.setBackgroundColor(Color.GRAY);
                            b48.setTextColor(Color.BLACK);
                            b48.setEnabled(false);
                            b49=findViewById(R.id.button414);
                            b49.setBackgroundColor(Color.BLACK);
                            b49.setTextColor(Color.WHITE);
                            b410=findViewById(R.id.button416);
                            b410.setBackgroundColor(Color.BLACK);
                            b410.setTextColor(Color.WHITE);
                            b411=findViewById(R.id.button417);
                            b411.setBackgroundColor(Color.BLACK);
                            b411.setTextColor(Color.WHITE);
                            b412=findViewById(R.id.button419);
                            b412.setBackgroundColor(Color.BLACK);
                            b412.setTextColor(Color.WHITE);
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            new Main4Activity.httpAsyncTask410().execute();
                            LinearLayout myText15 = findViewById(R.id.layout415);
                            myText15.setVisibility(View.VISIBLE);
                            TextView myText11 = findViewById(R.id.text412);
                            translator(R.id.text412, R.string.translation71, "t");
                            myText11.setVisibility(View.VISIBLE);
                            LinearLayout myText12 = findViewById(R.id.layout412);
                            myText12.setVisibility(View.VISIBLE);
                            TextView myText13 = findViewById(R.id.text413);
                            myText13.setVisibility(View.INVISIBLE);
                            LinearLayout myText14 = findViewById(R.id.layout413);
                            myText14.setVisibility(View.INVISIBLE);
                            ImageView iv1 = findViewById(R.id.speaker_button412);
                            iv1.clearColorFilter();
                            b48.setEnabled(true);
                        }
                    }else if(str8.equals("C")){
                        if (str4.equals("yes")==true || str4.equals("okay")==true || str4.equals("yeah")==true || str4.equals("yup")==true || str4.equals("yo")==true || str4.equals("是")==true || str4.equals("好的")==true || str4.equals("是啊")==true || str4.equals("对")==true || str4.equals("哟")==true || str4.equals("shi")==true || str4.equals("hao de")==true || str4.equals("shi a")==true || str4.equals("dui")==true || str4.equals("shia")==true) {
                            str9="0";
                            b49=findViewById(R.id.button414);
                            b49.setBackgroundColor(Color.GRAY);
                            b49.setTextColor(Color.BLACK);
                            b49.setEnabled(false);
                            b410=findViewById(R.id.button416);
                            b410.setBackgroundColor(Color.BLACK);
                            b410.setTextColor(Color.WHITE);
                            b411=findViewById(R.id.button417);
                            b411.setBackgroundColor(Color.BLACK);
                            b411.setTextColor(Color.WHITE);
                            b412=findViewById(R.id.button419);
                            b412.setBackgroundColor(Color.BLACK);
                            b412.setTextColor(Color.WHITE);
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            TextView myText11 = findViewById(R.id.text413);
                            myText11.setVisibility(View.INVISIBLE);
                            LinearLayout myText12 = findViewById(R.id.layout413);
                            myText12.setVisibility(View.INVISIBLE);
                            TextView mytext=findViewById(R.id.text417);
                            mytext.setVisibility(View.INVISIBLE);
                            LinearLayout lay2=findViewById(R.id.layout417);
                            lay2.setVisibility(View.INVISIBLE);
                            TextView mytext41=findViewById(R.id.text416);
                            mytext41.setVisibility(View.INVISIBLE);
                            /*Music_player1 adapter1=new Music_player1(this);
                            final AlertDialog alertDialog=new AlertDialog.Builder(this).setTitle("")
                                    .setAdapter(adapter1, null)
                                    .setMessage("")
                                    .setPositiveButton("", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .setNegativeButton("", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).show();
                            TextView messageView = alertDialog.findViewById(android.R.id.message);
                            messageView.setGravity(Gravity.CENTER);
                            Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            btnPositive.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String str12="0";
                                    str9="1";
                                    new Main4Activity.httpAsyncTask412().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                    if(str12.equals("1")){
                                        alertDialog.dismiss();
                                    }
                                }
                            });
                            Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                            btnNegative.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    new Main4Activity.httpAsyncTask4121().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                    new Main4Activity.httpAsyncTask413().execute();
                                    alertDialog.dismiss();
                                }
                            });
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
                            layoutParams.weight = 10;
                            btnPositive.setLayoutParams(layoutParams);
                            btnNegative.setLayoutParams(layoutParams);
                            if(str9.equals("1")){
                                new Main4Activity.httpAsyncTask4121().execute();
                                new Main4Activity.httpAsyncTask413().execute();
                            }*/
                            String user_name43=getIntent().getStringExtra("user_name");
                            String internet_connection1=getIntent().getStringExtra("internet_status");
                            Intent intent= new Intent(getBaseContext(), Music_player1.class);
                            intent.putExtra("user_name", user_name43);
                            intent.putExtra("internet_status", internet_connection1);
                            intent.putExtra("language_code", language_code);
                            intent.putExtra("playlist", playlist);
                            intent.putExtra("index", index);
                            intent.putExtra("help_number", str10);
                            intent.putExtra("emotion", str2);
                            intent.putExtra("chat_response", str7);
                            intent.putExtra("chatting_code", str6);
                            startActivity(intent);
                            new Main4Activity.httpAsyncTask413().execute();
                            b49=findViewById(R.id.button414);
                            b49.setEnabled(true);
                            ImageView iv1 = findViewById(R.id.speaker_button415);
                            iv1.clearColorFilter();
                        }else{
                            b49=findViewById(R.id.button414);
                            b49.setBackgroundColor(Color.BLACK);
                            b49.setTextColor(Color.WHITE);
                            b410=findViewById(R.id.button416);
                            b410.setBackgroundColor(Color.GRAY);
                            b410.setTextColor(Color.BLACK);
                            b410.setEnabled(false);
                            b411=findViewById(R.id.button417);
                            b411.setBackgroundColor(Color.BLACK);
                            b411.setTextColor(Color.WHITE);
                            b412=findViewById(R.id.button419);
                            b412.setBackgroundColor(Color.BLACK);
                            b412.setTextColor(Color.WHITE);
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            TextView myText11 = findViewById(R.id.text413);
                            myText11.setVisibility(View.INVISIBLE);
                            LinearLayout myText12 = findViewById(R.id.layout413);
                            myText12.setVisibility(View.INVISIBLE);
                            new Main4Activity.httpAsyncTask4131().execute();
                            ImageView iv1 = findViewById(R.id.speaker_button415);
                            iv1.clearColorFilter();
                            b410.setEnabled(true);
                        }
                    }else if(str8.equals("D")) {
                        if (str4.equals("yes")==true || str4.equals("okay")==true || str4.equals("yeah")==true || str4.equals("yup")==true || str4.equals("yo")==true || str4.equals("是")==true || str4.equals("好的")==true || str4.equals("是啊")==true || str4.equals("对")==true || str4.equals("哟")==true || str4.equals("shi")==true || str4.equals("hao de")==true || str4.equals("shi a")==true || str4.equals("dui")==true || str4.equals("shia")==true) {
                            b411 = findViewById(R.id.button417);
                            b411.setBackgroundColor(Color.GRAY);
                            b411.setTextColor(Color.BLACK);
                            b411.setEnabled(false);
                            b412 = findViewById(R.id.button419);
                            b412.setBackgroundColor(Color.BLACK);
                            b412.setTextColor(Color.WHITE);
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            makePhoneCall();
                            new Main4Activity.httpAsyncTask414().execute();
                            ImageView iv1 = findViewById(R.id.speaker_button418);
                            iv1.clearColorFilter();
                            b411.setEnabled(true);
                        } else {
                            b411 = findViewById(R.id.button417);
                            b411.setBackgroundColor(Color.BLACK);
                            b411.setTextColor(Color.WHITE);
                            b412 = findViewById(R.id.button419);
                            b412.setBackgroundColor(Color.GRAY);
                            b412.setTextColor(Color.BLACK);
                            b412.setEnabled(false);
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            new Main4Activity.httpAsyncTask414().execute();
                            ImageView iv1 = findViewById(R.id.speaker_button418);
                            iv1.clearColorFilter();
                            b412.setEnabled(true);
                        }
                    }else if(str8.equals("E")) {
                        if (str4.equals("yes")==true || str4.equals("okay")==true || str4.equals("yeah")==true || str4.equals("yup")==true || str4.equals("yo")==true || str4.equals("是")==true || str4.equals("好的")==true || str4.equals("是啊")==true || str4.equals("对")==true || str4.equals("哟")==true || str4.equals("shi")==true || str4.equals("hao de")==true || str4.equals("shi a")==true || str4.equals("dui")==true || str4.equals("shia")==true) {
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.GRAY);
                            b413.setTextColor(Color.BLACK);
                            b413.setEnabled(false);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.BLACK);
                            b414.setTextColor(Color.WHITE);
                            TextView mytext42=findViewById(R.id.text416);
                            mytext42.setVisibility(View.INVISIBLE);
                            new Main4Activity.httpAsyncTask462().execute();
                            ImageView iv1 = findViewById(R.id.speaker_button421);
                            iv1.clearColorFilter();
                            b413.setEnabled(true);
                        } else {
                            b413=findViewById(R.id.button420);
                            b413.setBackgroundColor(Color.BLACK);
                            b413.setTextColor(Color.WHITE);
                            b414=findViewById(R.id.button422);
                            b414.setBackgroundColor(Color.GRAY);
                            b414.setTextColor(Color.BLACK);
                            b414.setEnabled(false);
                            TextView mytext42=findViewById(R.id.text416);
                            mytext42.setVisibility(View.INVISIBLE);
                            new Main4Activity.httpAsyncTask46().execute();
                            ImageView iv1 = findViewById(R.id.speaker_button421);
                            iv1.clearColorFilter();
                            b414.setEnabled(true);
                        }
                    }
                }
                break;
                case 1:
                    if(resultCode == RESULT_OK){
                        //if(!DocumentsContract.isDocumentUri(this, data.getData()))
                        //    throw new RuntimeException("Not a documentsContract document");

                        try {
                            is = getContentResolver().openInputStream(data.getData());
                            uri=data.getData();
                            file_name=getFileName(uri);
                            new Main4Activity.httpAsyncTask417().execute();
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }
        }
    }

    private void makePhoneCall(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }else{
            String dial="tel:"+str10;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CALL){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else{
                Toast.makeText(this, translator_return(R.string.translation79), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // This function return the translated text
    private String translator_return(int b){
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        String str20=resources.getString(b);
        return str20;
    }

    // This function return the translated text in English
    private String translator_return1(int b){
        Context context = LocaleHelper.setLocale(this, "en");
        Resources resources = context.getResources();
        String str20=resources.getString(b);
        return str20;
    }

    private void helper1(){
        String user_name41=getIntent().getStringExtra("user_name");
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        TextView myText41=findViewById(R.id.text41);
        myText41.setText(String.format(resources.getString(R.string.translation18), user_name41));
    }

    // Converts the text to the given language and displays it
    private void translator(int a, int b, String help){
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        if(help.equals("t")){
            TextView textView10=findViewById(a);
            textView10.setText(resources.getString(b));
        }else if(help.equals("e")){
            EditText editText10=findViewById(a);
            editText10.setHint(resources.getString(b));
        }else if(help.equals("b")){
            Button button10=findViewById(a);
            button10.setText(resources.getString(b));
        }
    }

    // Displays the text based on the language set as soon as the activity appears on the app
    private void updateViews() {
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        TextView textView41, textView42, textView43, textView44, textView45, textView46, textView47, textView48;
        Button button41, button42, button43, button44, button45, button46, button47, button48, button49, button410, button411, button412, button413, button414, button415;
        textView41=findViewById(R.id.text415);
        textView41.setText(resources.getString(R.string.translation67));
        textView42=findViewById(R.id.text413);
        textView42.setText(resources.getString(R.string.translation75));
        textView43=findViewById(R.id.text43);
        textView43.setText(resources.getString(R.string.translation19));
        textView44=findViewById(R.id.text417);
        textView44.setText(resources.getString(R.string.translation76));
        textView48=findViewById(R.id.text412);
        textView48.setText(resources.getString(R.string.translation71));
        if(a!=0){
            textView45=findViewById(R.id.text44);
            textView45.setText(resources.getString(a));
        }
        if(b!=0){
            textView46=findViewById(R.id.text45);
            textView46.setText(resources.getString(b));
        }if(g!=0){
            textView47=findViewById(R.id.text411);
            textView47.setText(resources.getString(g));
        }
        button41=findViewById(R.id.button422);
        button41.setText(resources.getString(R.string.translation13));
        button42=findViewById(R.id.button420);
        button42.setText(resources.getString(R.string.translation12));
        button43=findViewById(R.id.button419);
        button43.setText(resources.getString(R.string.translation13));
        button44=findViewById(R.id.button417);
        button44.setText(resources.getString(R.string.translation12));
        button45=findViewById(R.id.button416);
        button45.setText(resources.getString(R.string.translation13));
        button46=findViewById(R.id.button414);
        button46.setText(resources.getString(R.string.translation12));
        button47=findViewById(R.id.button413);
        button47.setText(resources.getString(R.string.translation69));
        button48=findViewById(R.id.button411);
        button48.setText(resources.getString(R.string.translation68));
        button49=findViewById(R.id.button46);
        button49.setText(resources.getString(R.string.translation7));
        button410=findViewById(R.id.button45);
        button410.setText(resources.getString(R.string.translation22));
        button411=findViewById(R.id.button44);
        button411.setText(resources.getString(R.string.translation21));
        button412=findViewById(R.id.button43);
        button412.setText(resources.getString(R.string.translation20));
        if(c!=0){
            button413=findViewById(R.id.button47);
            button413.setText(resources.getString(c));
        }
        if(d!=0){
            button414=findViewById(R.id.button48);
            button414.setText(resources.getString(d));
        }if(e!=0){
            button415=findViewById(R.id.button46);
            button415.setText(resources.getString(e));
        }
        setTitle(resources.getString(R.string.main_activity_toolbar_title));
    }

    private void updates(){

    }

    // Override methods for the working of settings (Edit Language, Edit Playlist, Edit Help number) 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Build.VERSION.SDK_INT > 11) {
            invalidateOptionsMenu();
            menu.findItem(R.id.item1).setTitle(translator_return(R.string.translation82));
            menu.findItem(R.id.item2).setTitle(translator_return(R.string.translation85));
            menu.findItem(R.id.item3).setTitle(translator_return(R.string.translation86));
        }
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                final EditText taskEditText = new EditText(this);
                taskEditText.setText(str10);
                final AlertDialog alertDialog=new AlertDialog.Builder(this).setTitle("")
                        .setMessage(translator_return(R.string.translation82))
                        .setView(taskEditText)
                        .setPositiveButton(translator_return(R.string.translation84), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton(translator_return(R.string.translation83), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

                TextView messageView = alertDialog.findViewById(android.R.id.message);
                messageView.setGravity(Gravity.CENTER);
                Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        str10=taskEditText.getText().toString();
                        alertDialog.dismiss();
                    }
                });
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
                layoutParams.weight = 10;
                btnPositive.setLayoutParams(layoutParams);
                btnNegative.setLayoutParams(layoutParams);
                return true;
            case R.id.subitem21:
                Intent intent_upload = new Intent();
                intent_upload.setType("audio/*");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload,1);
                return true;
            case R.id.subitem22:
                if (itemList.isEmpty()) {
                    int num_of_songs=playlist.size();
                    for(int i=0; i<num_of_songs; i++)
                    {
                        itemList.add(new Item(playlist.get(i)));
                    }
                }

                adapter =
                        new DialogMultipleChoiceAdapter(this, itemList);

                final AlertDialog alertDialog2=new AlertDialog.Builder(this).setTitle("Remove Songs")
                        .setAdapter(adapter, null)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                Button btnPositive2 = alertDialog2.getButton(AlertDialog.BUTTON_POSITIVE);
                btnPositive2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog2.dismiss();
                    }
                });
                Button btnNegative2 = alertDialog2.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnNegative2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removed_songs=adapter.getCheckedItem();
                        if(removed_songs.get(0)!=null) {
                            int num_of_songs_removed = removed_songs.size();
                            for (int i = 0; i < num_of_songs_removed; i++) {
                                Log.v("HTTPDelete_Check2", "Get returned: " + removed_songs.get(i).getTitle());
                                new Main4Activity.httpAsyncTask416().execute(removed_songs.get(i).getTitle());
                                playlist.remove(removed_songs.get(i).getTitle());
                            }
                        }
                        Log.v("AsyncHTTP", "Get returned: " + playlist);
                        alertDialog2.dismiss();
                    }
                });
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) btnPositive2.getLayoutParams();
                layoutParams2.weight = 10;
                btnPositive2.setLayoutParams(layoutParams2);
                btnNegative2.setLayoutParams(layoutParams2);
                return true;
            case R.id.item3:
                final AlertDialog alertDialog1=new AlertDialog.Builder(this).setTitle("")
                        .setMessage(translator_return(R.string.translation86))
                        .setPositiveButton("中文", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("English", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

                TextView messageView1 = alertDialog1.findViewById(android.R.id.message);
                messageView1.setGravity(Gravity.CENTER);
                Button btnPositive1 = alertDialog1.getButton(AlertDialog.BUTTON_POSITIVE);
                btnPositive1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        str11="zh";
                        new Main4Activity.httpAsyncTask415().execute();
                        language_code="zh";
                        helper1();
                        updateViews();
                        alertDialog1.dismiss();
                    }
                });
                Button btnNegative1 = alertDialog1.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnNegative1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        str11="en";
                        new Main4Activity.httpAsyncTask415().execute();
                        language_code="en";
                        helper1();
                        updateViews();
                        alertDialog1.dismiss();
                    }
                });
                LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) btnPositive1.getLayoutParams();
                layoutParams1.weight = 10;
                btnPositive1.setLayoutParams(layoutParams1);
                btnNegative1.setLayoutParams(layoutParams1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // This function points to the name of the file on the Android Phone
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

}

