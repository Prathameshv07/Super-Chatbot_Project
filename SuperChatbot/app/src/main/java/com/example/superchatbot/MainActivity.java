package com.example.superchatbot;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.text.SimpleDateFormat;
import androidx.annotation.RequiresApi;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Message> messages;
    private RecyclerView recyclerView;
    private recyclerAdapter adapter;
    private ImageButton sendButton;
    private EditText msgInput;
    private getRequest request;
    public SwipeRefreshLayout swipeRefreshLayout;
    private long pressedTime;
    private int BrightS;
    private String facebookIntont = "com.facebook.katana";
    private char[] illegalChar = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
                                   'r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H',
                                   'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y',
                                   'Z','#','<','>','$','+','=','%','!','`','&','*','|','{','}','/',':','@'};

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(false);

        setContentView(R.layout.activity_main);

        request = new getRequest(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        recyclerView = findViewById(R.id.recyclerView);
        // Set RecyclerView layout manager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Set an animation
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        messages = new ArrayList<>();
        adapter = new recyclerAdapter(messages);
        recyclerView.setAdapter(adapter);

        sendButton = (ImageButton) findViewById(R.id.msgButton);
        msgInput = (EditText) findViewById(R.id.msgInput);

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message = msgInput.getText().toString();
                if(message.length() != 0 ){
                    messages.add(new Message(true, message));
                    int newPosition = messages.size() - 1;
                    adapter.notifyItemInserted(newPosition);
                    recyclerView.scrollToPosition(newPosition);
                    msgInput.setText("");
                    getReply(message);
                    switch(message) {
                        case "/Open caeser_cipher":

                            Intent CC = new Intent(MainActivity.this, caeserActivity.class);
                            MainActivity.this.startActivity(CC);

                            Toast forCaeser = Toast.makeText(getApplicationContext(), "Opening Caeser..", Toast.LENGTH_SHORT);
                            forCaeser.show();

                            msgInput.setText("");

                            break;
                        case "/Open control_volume":
                            //code to be executed;
                            Intent cV = new Intent(MainActivity.this, volumeActivity.class);
                            MainActivity.this.startActivity(cV);

                            Toast forVolume = Toast.makeText(getApplicationContext(), "Opening Volume..", Toast.LENGTH_SHORT);
                            forVolume.show();

                            msgInput.setText("");

                            break;
                        case "/Open morse_code":
                            //code to be executed;
                            Intent mC = new Intent(MainActivity.this, morseActivity.class);
                            MainActivity.this.startActivity(mC);

                            Toast forMorse = Toast.makeText(getApplicationContext(), "Opening Morse..", Toast.LENGTH_SHORT);
                            forMorse.show();

                            msgInput.setText("");


                            break;
                        case "/Open tic_tac_toe":
                            //code to be executed;

                            Intent ttt = new Intent(MainActivity.this, tttActivity.class);
                            MainActivity.this.startActivity(ttt);
                            Toast forTtt = Toast.makeText(getApplicationContext(), "Opening Ttt..", Toast.LENGTH_SHORT);
                            forTtt.show();

                            msgInput.setText("");

                            break;

                        case "/Open StepCount":
                            //code to be executed;
                            Intent SP = new Intent(MainActivity.this, stepActivity.class);
                            MainActivity.this.startActivity(SP);

                            Toast stepCount = Toast.makeText(getApplicationContext(), "Opening Step Counter..", Toast.LENGTH_SHORT);
                            stepCount.show();

                            msgInput.setText("");

                            break;

                        case "/ShowDateandTime":

                            Toast DT = Toast.makeText(getApplicationContext(), "Displaying D&T..", Toast.LENGTH_SHORT);
                            DT.show();

                            // on below line we are creating and initializing
                            // variable for simple date format.
                            SimpleDateFormat sdf = new SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss z");

                            // on below line we are creating a variable
                            // for current date and time and calling a simple date format in it.
                            String currentDateAndTime = sdf.format(new Date());

                            // on below line we are setting current
                            // date and time to our text view.
                            messages.add(new Message(false, currentDateAndTime));
                            int newPosition8 = messages.size() - 1;
                            adapter.notifyItemInserted(newPosition8);
                            recyclerView.scrollToPosition(newPosition8);

                            msgInput.setText("");

                            break;


                        case "/Open Youtube":
                            Intent youtubeIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                            if (youtubeIntent != null) {
                                startActivity(youtubeIntent);
                                Toast yT = Toast.makeText(getApplicationContext(), "Opening Youtube..", Toast.LENGTH_SHORT);
                                yT.show();
                                msgInput.setText("");
                            } else {
                                String packageError = "No Package of that Name!!!";

                                messages.add(new Message(false, packageError));
                                int position1 = messages.size() - 1;
                                adapter.notifyItemInserted(position1);
                                recyclerView.scrollToPosition(position1);
                                msgInput.setText("");
                            }
                            break;


                        case "/Open chrome":
                            Intent chromeIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
                            if (chromeIntent != null) {
                                startActivity(chromeIntent);
                                Toast chrome = Toast.makeText(getApplicationContext(), "Opening Chrome..", Toast.LENGTH_SHORT);
                                chrome.show();
                                msgInput.setText("");
                            } else {
                                String packageError = "No Package of that Name!!!";

                                messages.add(new Message(false, packageError));
                                int position2 = messages.size() - 1;
                                adapter.notifyItemInserted(position2);
                                recyclerView.scrollToPosition(position2);
                                msgInput.setText("");
                            }
                            break;

                        case "/Open Facebook":
                            Intent facebookIntent = getPackageManager().getLaunchIntentForPackage(facebookIntont);

                            try {
                                startActivity(facebookIntent);
                                Toast Facebook = Toast.makeText(getApplicationContext(), "Opening Facebook..", Toast.LENGTH_SHORT);
                                Facebook.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            /*-
                            if (facebookIntent != null) {
                                startActivity(facebookIntent);
                                Toast Facebook = Toast.makeText(getApplicationContext(), "Opening Facebook..", Toast.LENGTH_SHORT);
                                Facebook.show();
                                msgInput.setText("");
                            } else {
                                String packageError = "No Package of that Name!!!";

                                messages.add(new Message(false, packageError));
                                int position3 = messages.size() - 1;
                                adapter.notifyItemInserted(position3);
                                recyclerView.scrollToPosition(position3);
                                msgInput.setText("");
                            }
                            */

                            break;

                        /*
                        case "/Open twitter":

                            try {
                                Intent twitterIntent = getPackageManager().getLaunchIntentForPackage("com.twitter.android");
                                startActivity(twitterIntent);
                                Toast twitterT = Toast.makeText(getApplicationContext(),"Opening Twitter..",Toast.LENGTH_SHORT);
                                twitterT.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                         */

                        case "/Open BrightnessSettings1":
                            //code to be executed;
                            Intent BS = new Intent(MainActivity.this, brightActivity.class);
                            MainActivity.this.startActivity(BS);

                            Toast Brightness = Toast.makeText(getApplicationContext(), "Opening Brightness Settings..", Toast.LENGTH_SHORT);
                            Brightness.show();

                            msgInput.setText("");

                            break;


                        case "/Brightness":

                            String hw = "Give a number from Range 0 to 255";

                            messages.add(new Message(false, hw));
                            int position4 = messages.size() - 1;
                            adapter.notifyItemInserted(position4);
                            recyclerView.scrollToPosition(position4);

                            sendButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    String msg = msgInput.getText().toString();

                                    int BrightS = new Integer(msg).intValue();
                                    if (BrightS >= 0 && BrightS <= 255) {

                                        messages.add(new Message(true,"You typed: " + BrightS));
                                        int newPos = messages.size() - 1;
                                        adapter.notifyItemInserted(newPos);
                                        recyclerView.scrollToPosition(newPos);
                                        msgInput.setText("");

                                        messages.add(new Message(false, "Setting brightness at " + BrightS));
                                        int position5 = messages.size() - 1;
                                        adapter.notifyItemInserted(position5);
                                        recyclerView.scrollToPosition(position5);

                                        ContentResolver contentResolver = getContentResolver();
                                        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, Integer.parseInt(String.valueOf(BrightS)));
                                        shallRunBright();
                                        return;

                                    } else {
                                        String reply2 = "Please give correct Range";

                                        messages.add(new Message(false, reply2));
                                        int position5 = messages.size() - 1;
                                        adapter.notifyItemInserted(position5);
                                        recyclerView.scrollToPosition(position5);
                                        msgInput.setText("");
                                        return;
                                    }

                                }
                            });

                            break;


                        case "/Refresh Content":

                            refreshOn();

                            msgInput.setText("");

                            break;

                        case "/Exit":
                            Toast forEx = Toast.makeText(getApplicationContext(),"Exiting app",Toast.LENGTH_SHORT);
                            forEx.show();

                            // on below line we are finishing activity.
                            MainActivity.this.finish();

                            // on below line we are exiting our activity
                            System.exit(0);
                            break;





                    /*
                    case "/Open control-volume":
                        //code to be executed;
                        break;
                     */

                        default:
                            break;//do Nothing
                    }
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(
            new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                refreshOn();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void shallRunBright() {
        if(!Settings.System.canWrite(this)){
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + this.getPackageName()));
            startActivity(intent);
        }
        return;
    }

    public void refreshOn() {

        messages.clear();
        adapter.notifyDataSetChanged();
        Toast rh = Toast.makeText(getApplicationContext(),"Refreshing Content...",Toast.LENGTH_SHORT);
        rh.show();
    }

    private void getReply(String message) {
        request.getResponse(message, new com.example.superchatbot.getRequest.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Log.d("REQUEST ERROR", message);
            }

            @Override
            public void onResponse(String reply) {
                messages.add(new com.example.superchatbot.Message(false, reply));
                int newPosition = messages.size() - 1;
                adapter.notifyItemInserted(newPosition);
                recyclerView.scrollToPosition(newPosition);
            }
        });
    }



    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}