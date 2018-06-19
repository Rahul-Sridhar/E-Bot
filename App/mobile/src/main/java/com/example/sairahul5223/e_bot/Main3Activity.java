package com.example.sairahul5223.e_bot;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
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
import java.util.List;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Main3Activity extends Activity {

    private ArrayList<String> playlist;
    private String str1, language_code, help_number, file_name, str11;
    private List<Item> itemList = new ArrayList<>();
    private List<Item> removed_songs;
    private InputStream is;
    private DialogMultipleChoiceAdapter adapter;
    Uri uri;
    Button b31, b32;
    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        playlist=getIntent().getExtras().getStringArrayList("playlist");
        language_code=getIntent().getStringExtra("language_code");
        help_number=getIntent().getStringExtra("help_number");
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        setTitle(resources.getString(R.string.main_activity_toolbar_title));
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this, "segoeuil.ttf", true);
        updateViews();
    }

    public void onButtonClickName31(View v){
        b31=findViewById(R.id.button34);
        b31.setBackgroundColor(Color.GRAY);
        b31.setTextColor(Color.BLACK);
        b31.setEnabled(false);
        b32=findViewById(R.id.button35);
        b32.setBackgroundColor(Color.BLACK);
        b32.setTextColor(Color.WHITE);
        String user_name31=getIntent().getStringExtra("user_name");
        Intent myIntent31= new Intent(this, Main4Activity.class);
        myIntent31.putExtra("user_name", user_name31);
        myIntent31.putExtra("language_code", language_code);
        myIntent31.putExtra("help_number", help_number);
        myIntent31.putExtra("playlist", playlist);
        myIntent31.putExtra("music_indicator", "0");
        startActivity(myIntent31);
        b31.setEnabled(true);
    }

    public void onButtonClickName32(View v){
        b31=findViewById(R.id.button34);
        b31.setBackgroundColor(Color.BLACK);
        b31.setTextColor(Color.WHITE);
        b32=findViewById(R.id.button35);
        b32.setBackgroundColor(Color.GRAY);
        b32.setTextColor(Color.BLACK);
        b32.setEnabled(false);
        AlertDialog alertDialog=new AlertDialog.Builder(this).setTitle("")
                .setMessage(translator_return(R.string.translation14))
                .setPositiveButton(translator_return(R.string.translation16), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String user_name35=getIntent().getStringExtra("user_name");
                        Intent myIntent32= new Intent(getBaseContext(), Main4Activity.class);
                        myIntent32.putExtra("language_code", language_code);
                        myIntent32.putExtra("user_name", user_name35);
                        myIntent32.putExtra("help_number", help_number);
                        myIntent32.putExtra("playlist", playlist);
                        myIntent32.putExtra("music_indicator", "0");
                        startActivity(myIntent32);
                        b32.setBackgroundColor(Color.BLACK);
                        b32.setTextColor(Color.WHITE);
                    }
                })
                .setNegativeButton(translator_return(R.string.translation15), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String str20=translator_return(R.string.translation17);
                        Toast.makeText(getApplicationContext(), str20, Toast.LENGTH_SHORT).show();
                        String user_name55=getIntent().getStringExtra("user_name");
                        String internet_connection3=getIntent().getStringExtra("internet_status");
                        Intent myIntent33= new Intent(getBaseContext(), Main3Activity.class);
                        myIntent33.putExtra("user_name", user_name55);
                        myIntent33.putExtra("internet_status", internet_connection3);
                        myIntent33.putExtra("language_code", language_code);
                        myIntent33.putExtra("help_number", help_number);
                        myIntent33.putExtra("playlist", playlist);
                        myIntent33.putExtra("music_indicator", "0");
                        startActivity(myIntent33);
                        b32.setBackgroundColor(Color.BLACK);
                        b32.setTextColor(Color.WHITE);
                    }
                }).show();
        TextView messageView = alertDialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);

        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        layoutParams.weight = 10;
        btnPositive.setLayoutParams(layoutParams);
        btnNegative.setLayoutParams(layoutParams);
        b32.setEnabled(true);
        /*String user_name32=getIntent().getStringExtra("user_name");
        String internet_connection=getIntent().getStringExtra("internet_status");
        Intent myIntent32=new Intent(this, Main5Activity.class);
        myIntent32.putExtra("user_name", user_name32);
        myIntent32.putExtra("internet_status", internet_connection);
        startActivity(myIntent32);*/
    }

    public void onButtonClickName33(View v){
        ImageView iv1=findViewById(R.id.speaker_button);
        iv1.setColorFilter(Color.GRAY, PorterDuff.Mode.LIGHTEN);
        String user_name33=getIntent().getStringExtra("user_name");
        String internet_connection1=getIntent().getStringExtra("internet_status");
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra("user_name", user_name33);
        intent.putExtra("internet_status", internet_connection1);
        intent.putExtra("language_code", language_code);
        intent.putExtra("help_number", help_number);
        intent.putExtra("playlist", playlist);
        intent.putExtra("music_indicator", "0");
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, 10);
        }else{
            String str2=translator_return(R.string.translation78);
            Toast.makeText(this, str2, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent myIntent33= new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent33);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    String user_name35 = getIntent().getStringExtra("user_name");
                    String internet_connection3 = getIntent().getStringExtra("internet_status");
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.v("HTTP", "Get returned: " + result.get(0));
                    str1 = result.get(0);
                    if (str1.equals("yes") == true || str1.equals("okay") == true || str1.equals("yeah") == true || str1.equals("yup") == true || str1.equals("yo") == true || str1.equals("是") == true || str1.equals("好的") == true || str1.equals("是啊") == true || str1.equals("对") == true || str1.equals("哟") == true || str1.equals("shi") == true || str1.equals("hao de") == true || str1.equals("shi a") == true || str1.equals("dui") == true || str1.equals("shia") == true) {
                        b31 = findViewById(R.id.button34);
                        b31.setBackgroundColor(Color.GRAY);
                        b31.setTextColor(Color.BLACK);
                        b31.setEnabled(false);
                        b32 = findViewById(R.id.button35);
                        b32.setBackgroundColor(Color.BLACK);
                        b32.setTextColor(Color.WHITE);
                        Intent myIntent31 = new Intent(this, Main4Activity.class);
                        myIntent31.putExtra("user_name", user_name35);
                        myIntent31.putExtra("language_code", language_code);
                        myIntent31.putExtra("help_number", help_number);
                        myIntent31.putExtra("playlist", playlist);
                        myIntent31.putExtra("music_indicator", "0");
                        startActivity(myIntent31);
                        ImageView iv1 = findViewById(R.id.speaker_button);
                        iv1.clearColorFilter();
                        b31.setEnabled(true);
                    } else if (str1.equals("no") == true || str1.equals("nope") == true || str1.equals("nah") == true || str1.equals("na") == true || str1.equals("not now") == true || str1.equals("没有") == true || str1.equals("不") == true || str1.equals("罗") == true || str1.equals("呐") == true || str1.equals("不是现在") == true || str1.equals("Bushi xianzai") == true || str1.equals("lua") == true || str1.equals("luo") == true || str1.equals("bu") == true || str1.equals("meiyou") == true) {
                        b31 = findViewById(R.id.button34);
                        b31.setBackgroundColor(Color.BLACK);
                        b31.setTextColor(Color.WHITE);
                        b32 = findViewById(R.id.button35);
                        b32.setBackgroundColor(Color.GRAY);
                        b32.setTextColor(Color.BLACK);
                        b32.setEnabled(false);
                        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("")
                                .setMessage(translator_return(R.string.translation14))
                                .setPositiveButton(translator_return(R.string.translation16), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        String user_name35 = getIntent().getStringExtra("user_name");
                                        Intent myIntent32 = new Intent(getBaseContext(), Main4Activity.class);
                                        myIntent32.putExtra("user_name", user_name35);
                                        myIntent32.putExtra("language_code", language_code);
                                        myIntent32.putExtra("help_number", help_number);
                                        myIntent32.putExtra("playlist", playlist);
                                        myIntent32.putExtra("music_indicator", "0");
                                        startActivity(myIntent32);
                                    }
                                })
                                .setNegativeButton(translator_return(R.string.translation15), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        String str20 = translator_return(R.string.translation17);
                                        Toast.makeText(getApplicationContext(), str20, Toast.LENGTH_SHORT).show();
                                        String user_name55 = getIntent().getStringExtra("user_name");
                                        String internet_connection3 = getIntent().getStringExtra("internet_status");
                                        Intent myIntent33 = new Intent(getBaseContext(), Main3Activity.class);
                                        myIntent33.putExtra("user_name", user_name55);
                                        myIntent33.putExtra("internet_status", internet_connection3);
                                        myIntent33.putExtra("language_code", language_code);
                                        myIntent33.putExtra("help_number", help_number);
                                        myIntent33.putExtra("playlist", playlist);
                                        myIntent33.putExtra("music_indicator", "0");
                                        startActivity(myIntent33);
                                    }
                                }).show();
                        TextView messageView = alertDialog.findViewById(android.R.id.message);
                        messageView.setGravity(Gravity.CENTER);
                        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
                        layoutParams.weight = 10;
                        btnPositive.setLayoutParams(layoutParams);
                        btnNegative.setLayoutParams(layoutParams);
                        ImageView iv1 = findViewById(R.id.speaker_button);
                        iv1.clearColorFilter();
                        b32.setEnabled(true);
                    }
                } else {
                    ImageView iv1 = findViewById(R.id.speaker_button);
                    iv1.clearColorFilter();
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    if (!DocumentsContract.isDocumentUri(this, data.getData()))
                        throw new RuntimeException("Not a documentsContract document");

                    try {
                        is = getContentResolver().openInputStream(data.getData());
                        uri = data.getData();
                        file_name = getFileName(uri);
                        new Main3Activity.httpAsyncTask417().execute();
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

    private void updateViews() {
        Context context = LocaleHelper.setLocale(this, language_code);
        Resources resources = context.getResources();
        String user_name=getIntent().getStringExtra("user_name");
        textView1=findViewById(R.id.text31);
        textView1.setText(resources.getString(R.string.translation9));
        textView2=findViewById(R.id.text32);
        textView2.setText(String.format(resources.getString(R.string.translation10), user_name));
        textView3=findViewById(R.id.text33);
        textView3.setText(resources.getString(R.string.translation11));
        b31=findViewById(R.id.button34);
        b31.setText(resources.getString(R.string.translation12));
        b32=findViewById(R.id.button35);
        b32.setText(resources.getString(R.string.translation13));
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
                                new Main3Activity.httpAsyncTask416().execute(removed_songs.get(i).getTitle());
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
                        new Main3Activity.httpAsyncTask415().execute();
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
                        new Main3Activity.httpAsyncTask415().execute();
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
