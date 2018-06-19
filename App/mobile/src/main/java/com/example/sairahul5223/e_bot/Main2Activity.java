package com.example.sairahul5223.e_bot;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.speech.RecognizerIntent;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Main2Activity extends Activity {

    private ArrayList<String> playlist;
    private String str1, str2, str3, language_code, help_number, file_name, str11;
    private List<Item> itemList = new ArrayList<>();
    private List<Item> removed_songs;
    private InputStream is;
    private DialogMultipleChoiceAdapter adapter;
    Uri uri;
    Button b21;
    //private static int TIME_OUT = 200;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        str3=getIntent().getStringExtra("user_name");
        playlist=getIntent().getExtras().getStringArrayList("playlist");
        language_code=getIntent().getStringExtra("language_code");
        help_number=getIntent().getStringExtra("help_number");
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        setTitle(resources.getString(R.string.main_activity_toolbar_title));
        updateViews();
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this, "segoeuil.ttf", true);
        new httpAsyncTask21().execute();
    }

    public class httpAsyncTask21 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {

                //long startTime=System.currentTimeMillis();
                final URL addr = new URL("http://172.17.59.97/connection");
                final HttpURLConnection con = (HttpURLConnection) addr.openConnection();
                con.setConnectTimeout(10000);
                InputStream is = con.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                str1=br.readLine();
                Log.v("HTTP", "Get returned: " + str1);
                con.disconnect();
                //long stopTime=System.currentTimeMillis();
                //long elapsedTime=stopTime-startTime;
                //Log.v("HTTP", "Get returned: " + Long.toString(elapsedTime));
            }
            /*catch(java.net.SocketTimeoutException ex){

                Log.v("HTTP12", "Get returned: " + str1);
            }*/ catch(java.io.IOException ex) {
                //Log.e("HTTP ASYNC", ex);
                str1="Connection error between Android Phone and E-Bot. Make sure to connect the E-Bot and Phone to the same WiFi.";
                Log.v("HTTP12", "Get returned: " + str1);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            String user21=getIntent().getStringExtra("user_name");
            str3=user21;

            Log.v("HTTP21", "Get returned: " + str1);
            /*new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, TIME_OUT);*/

            if(str1.equals("E-Bot is starting..")){
                translator(R.id.text21, R.string.translation8, "t");
                new httpAsyncTask22().execute();
            }else{
                translator(R.id.text21, R.string.translation6, "t");
                TextView myText22=findViewById(R.id.text21);
                myText22.setTextSize(20);
                Button button = findViewById(R.id.button21);
                button.setVisibility(View.VISIBLE);
            }

        }
    }

    public class httpAsyncTask22 extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                //String user_name21 =  URLEncoder.encode(str3, 'utf-8');
                //long startTime = System.currentTimeMillis();
                final URL addr1 = new URL("http://172.17.59.97/internet_connection?name="+str3);
                final HttpURLConnection con1 = (HttpURLConnection) addr1.openConnection();
                con1.setConnectTimeout(10000);
                InputStream is1 = con1.getInputStream();
                InputStreamReader isr1 = new InputStreamReader(is1);
                BufferedReader br1 = new BufferedReader(isr1);
                str2=br1.readLine();
                Log.v("AsyncHTTP", "Get returned: " + str2);
                con1.disconnect();
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
            String user21=getIntent().getStringExtra("user_name");
            String language=getIntent().getStringExtra("language_code");
            Intent i = new Intent(Main2Activity.this, Main3Activity.class);
            i.putExtra("user_name", user21);
            i.putExtra("internet_status", str2);
            i.putExtra("language_code", language);
            i.putExtra("help_number", help_number);
            i.putExtra("playlist", playlist);
            Log.v("HTTP", "Get returned: " + str2);
            startActivity(i);
            finish();
        }

    }

    public void onButtonClickName21(View v){
        String user_name21=getIntent().getStringExtra("user_name");
        String language=getIntent().getStringExtra("language_code");
        Intent intent=new Intent(Main2Activity.this, Main2Activity.class);
        intent.putExtra("user_name", user_name21);
        intent.putExtra("language_code", language);
        intent.putExtra("playlist", playlist);
        intent.putExtra("help_number", help_number);
        startActivity(intent);

    }

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

    private void updateViews() {
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        b21=findViewById(R.id.button21);
        b21.setText(resources.getString(R.string.translation7));
        setTitle(resources.getString(R.string.main_activity_toolbar_title));
    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    if(!DocumentsContract.isDocumentUri(this, data.getData()))
                        throw new RuntimeException("Not a documentsContract document");

                    try {
                        is = getContentResolver().openInputStream(data.getData());
                        uri=data.getData();
                        file_name=getFileName(uri);
                        new Main2Activity.httpAsyncTask417().execute();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
        }
    }

    private String translator_return(int b){
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        String str20=resources.getString(b);
        return str20;
    }

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
                taskEditText.setText(help_number);
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
                        help_number=taskEditText.getText().toString();
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

                final AlertDialog alertDialog2=new AlertDialog.Builder(this).setTitle(translator_return(R.string.translation88))
                        .setAdapter(adapter, null)
                        .setPositiveButton(translator_return(R.string.translation84), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton(translator_return(R.string.translation89), new DialogInterface.OnClickListener() {
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
                                new Main2Activity.httpAsyncTask416().execute(removed_songs.get(i).getTitle());
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
                        new Main2Activity.httpAsyncTask415().execute();
                        language_code="zh";
                        updateViews();
                        alertDialog1.dismiss();
                    }
                });
                Button btnNegative1 = alertDialog1.getButton(AlertDialog.BUTTON_NEGATIVE);
                btnNegative1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        str11="en";
                        new Main2Activity.httpAsyncTask415().execute();
                        language_code="en";
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

