package com.example.sairahul5223.e_bot;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


import me.anwarshahriar.calligrapher.Calligrapher;

public class Main5Activity extends Activity {

    private String str1, language_code, help_number="911";
    private ArrayList<String> playlist = new ArrayList<>(Arrays.asList("Wavin Flag", "Your Song", "Spectre"));
    Button b51, b52;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Context context = LocaleHelper.setLocale(this, "en");
        Resources resources = context.getResources();
        setTitle(resources.getString(R.string.main_activity_toolbar_title));
        str1="";
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this, "segoeuil.ttf", true);
        checkPermission();
    }


    // This function sets the language of communication for the robot
    public class httpAsyncTask51 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                final URL addr1 = new URL("http://172.17.59.97/sound_settings?language="+str1);
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
            Log.v("HTTPDelete_Check3", "Get returned: " + "cool");
        }

    }

    // This is function is executed when 'English' button is selected
    public void onButtonClickName51(View v){
        b51=findViewById(R.id.button51);
        b51.setBackgroundColor(Color.GRAY);
        b51.setTextColor(Color.BLACK);
        b52=findViewById(R.id.button53);
        b52.setBackgroundColor(Color.BLACK);
        b52.setTextColor(Color.WHITE);
        b51.setEnabled(false);
        str1="en";
        language_code="en";
        updateViews();
        new httpAsyncTask51().execute();
        Intent myIntent= new Intent(getBaseContext(), MainActivity.class);
        myIntent.putExtra("language_code", language_code);
        myIntent.putExtra("playlist", playlist);
        myIntent.putExtra("help_number", help_number);
        startActivity(myIntent);
        b51.setEnabled(true);
    }

    // This is function is executed when mic button is selected
    public void onButtonClickName52(View v){
        b51=findViewById(R.id.button51);
        b51.setBackgroundColor(Color.BLACK);
        b51.setTextColor(Color.WHITE);
        b52=findViewById(R.id.button53);
        b52.setBackgroundColor(Color.BLACK);
        b52.setTextColor(Color.WHITE);
        ImageView iv41=findViewById(R.id.speaker_button52);
        iv41.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, 10);
        }else{
            Toast.makeText(this, "Your Device Does not Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    // This is function is executed when 'Chinese' button is selected
    public void onButtonClickName53(View v){
        b51=findViewById(R.id.button51);
        b51.setBackgroundColor(Color.BLACK);
        b51.setTextColor(Color.WHITE);
        b52=findViewById(R.id.button53);
        b52.setBackgroundColor(Color.GRAY);
        b52.setTextColor(Color.BLACK);
        b52.setEnabled(false);
        str1="zh-cn";
        language_code="zh";
        updateViews();
        new Main5Activity.httpAsyncTask51().execute();
        Intent myIntent= new Intent(getBaseContext(), MainActivity.class);
        myIntent.putExtra("language_code", language_code);
        myIntent.putExtra("playlist", playlist);
        myIntent.putExtra("help_number", help_number);
        startActivity(myIntent);
        b52.setEnabled(true);
    }

    // Override function which executes a set of statements based on voice input from the user
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10: {
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.v("HTTP", "Get returned: " + result.get(0));
                    String str2 = result.get(0);
                    if (str2.equals("english") == true || str2.equals("anglish") == true || str2.equals("yenglish") == true || str2.equals("en") == true || str2.equals("yanglish") == true || str2.equals("englis") == true) {
                        b51 = findViewById(R.id.button51);
                        b51.setBackgroundColor(Color.GRAY);
                        b51.setTextColor(Color.BLACK);
                        b52 = findViewById(R.id.button53);
                        b52.setBackgroundColor(Color.BLACK);
                        b52.setTextColor(Color.WHITE);
                        b51.setEnabled(false);
                        str1 = "en";
                        new Main5Activity.httpAsyncTask51().execute();
                        language_code = "en";
                        updateViews();
                        ImageView iv1 = findViewById(R.id.speaker_button52);
                        iv1.clearColorFilter();
                        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                        myIntent.putExtra("language_code", language_code);
                        myIntent.putExtra("playlist", playlist);
                        myIntent.putExtra("help_number", help_number);
                        startActivity(myIntent);
                        b51.setEnabled(true);
                    } else {
                        b51 = findViewById(R.id.button51);
                        b51.setBackgroundColor(Color.BLACK);
                        b51.setTextColor(Color.WHITE);
                        b52 = findViewById(R.id.button53);
                        b52.setBackgroundColor(Color.GRAY);
                        b52.setTextColor(Color.BLACK);
                        b52.setEnabled(false);
                        str1 = "zh-cn";
                        new Main5Activity.httpAsyncTask51().execute();
                        language_code = "zh";
                        updateViews();
                        ImageView iv1 = findViewById(R.id.speaker_button52);
                        iv1.clearColorFilter();
                        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                        myIntent.putExtra("language_code", language_code);
                        myIntent.putExtra("playlist", playlist);
                        myIntent.putExtra("help_number", help_number);
                        startActivity(myIntent);
                        b52.setEnabled(true);
                    }
                }

                break;
            }
        }
    }

    private void checkPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent =new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:"+getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    // Displays the text based on the language set as soon as the activity appears on the app
    private void updateViews() {
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        textView=findViewById(R.id.text51);
        textView.setText(resources.getString(R.string.translation1));

        setTitle(resources.getString(R.string.main_activity_toolbar_title));
    }

}
