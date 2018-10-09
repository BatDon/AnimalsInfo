package com.example.david.animalsinfo;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private int MAIN_THREAD_TASK_1 = 1;
    private int MAIN_THREAD_TASK_2 = 2;
    private int MAIN_THREAD_TASK_3 = 3;
    private int CHILD_THREAD_QUIT_LOOPER = 4;

    private Handler mainThreadHandler;

    private MyWorkerThread workerThread = null;

    public String dogTitle;
    public String dogText;

    public String catTitle;
    public String catText;

    public String frogTitle;
    public String frogText;

    //Website where information will be pulled from
    public static final String EXTRA_MESSAGE = "@string/myPackage";
    public static final String EXTRA_MESSAGE_TWO = "@string/myPackage2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Animal sounds are created here
        final MediaPlayer barking = MediaPlayer.create(this, R.raw.barking2);
        final MediaPlayer meowing = MediaPlayer.create(this, R.raw.meowing);
        final MediaPlayer ribbiting = MediaPlayer.create(this, R.raw.frog);


        // Create and start the worker thread.
        workerThread = new MyWorkerThread();
        workerThread.start();

        // Handle message from main thread message queue.
        // Animal information connection is created when msg.what is set
        // Animal sounds are started here when the message is received from the button click
        mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i("MAIN_THREAD", "Receive message from child thread.");
                //taskStatusTextView = findViewById(R.id.somethingText);
                if (msg.what == MAIN_THREAD_TASK_1) {
                    barking.start();
                    //taskStatusTextView.setText(dogText);
                    sendActivity(dogTitle,dogText);
                } else if (msg.what == MAIN_THREAD_TASK_2) {
                    // If task two button is clicked.
                    meowing.start();
                    //taskStatusTextView.setText(catText);
                    sendActivity2(catTitle,catText);
                } else if (msg.what == MAIN_THREAD_TASK_3) {
                    // If quit child thread looper button is clicked.
                    ribbiting.start();
                    sendActivity3(frogTitle,frogText);
                    //taskStatusTextView.setText(frogText);
                }
            }
        };


        // Get run task buttons.
        ImageButton runTaskOneButton = findViewById(R.id.dogButton);

        ImageButton runTaskTwoButton = findViewById(R.id.catButton);

        ImageButton runTaskThreeButton = findViewById(R.id.frogButton);

        // Set on click listener to each button.
        runTaskOneButton.setOnClickListener(new View.OnClickListener()

                //When button is clicked the msg.what is given a value
        {
            @Override
            public void onClick(View view) {
                // When click this button, create a message object
                Message msg = new Message();
                msg.what = MAIN_THREAD_TASK_1;
                // Use worker thread message Handler to put message into worker thread message queue.
                workerThread.workerThreadHandler.sendMessage(msg);
            }
        });

        // Please see comments for runTaskOneButton.
        runTaskTwoButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                Message msg = new Message();
                msg.what = MAIN_THREAD_TASK_2;
                workerThread.workerThreadHandler.sendMessage(msg);
            }
        });
        runTaskThreeButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = MAIN_THREAD_TASK_3;
                workerThread.workerThreadHandler.sendMessage(msg);
            }
        });


    }

    //This sends the information to the Activity to be viewed
    public void sendActivity(String dogTitle,String dogText) {
        Intent intent = new Intent(this, DogsActivity.class);
        //Text editText =findViewById(R.id.somethingText);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, dogTitle);
        intent.putExtra(EXTRA_MESSAGE_TWO, dogText);
        startActivity(intent);
    }

    public void sendActivity2(String catTitle, String catText) {
        Intent intent = new Intent(this, CatsActivity.class);
        //Text editText =findViewById(R.id.somethingText);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, catTitle);
        intent.putExtra(EXTRA_MESSAGE_TWO, catText);
        startActivity(intent);
    }

    public void sendActivity3(String frogTitle, String frogText) {
        Intent intent = new Intent(this, FrogsActivity.class);
        //Text editText =findViewById(R.id.somethingText);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, frogTitle);
        intent.putExtra(EXTRA_MESSAGE_TWO, frogText);
        startActivity(intent);
    }


    // This child thread class has it's own Looper and Handler object.
    private class MyWorkerThread extends Thread {
        //Log.i("MyWorkerThread","MyWorkerThread created")
        // This is worker thread handler.
        public Handler workerThreadHandler;

        @Override
        public void run() {
            // Prepare MyWorkerThread which is a child of Thread Looper object.
            //Prepares thread to add tasks in a loop

            Looper.prepare();

            //Allows Media Player to have volume
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);

            //Creates a readable HTML document by calling FormatDocument()
            FormatDocument formattedDoc = new FormatDocument();
            dogText = formattedDoc.returnString();

            FormatDocument2 formattedDoc2 = new FormatDocument2();
            catText = formattedDoc2.returnString2();

            FormatDocument3 formattedDoc3 = new FormatDocument3();
            frogText = formattedDoc3.returnString3();


            // Create child thread Handler. Connects Looper to current(MyWorkerThread)thread
            workerThreadHandler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    // When child thread handler get message from child thread message queue.
                    Log.i("CHILD_THREAD", "Receive message from main thread.");
                    Message message = new Message();
                    message.what = msg.what;
                    Log.i("AFtermsg.what", "msg.what;");

                    // Send the message back to main thread message queue use main thread message Handler.
                    mainThreadHandler.sendMessage(message);
                }
            };
            // Loop the child thread message queue.
            Looper.loop();

            // The code after Looper.loop() will not be executed until you call workerThreadHandler.getLooper().quit()
            Log.i("CHILD_THREAD", "This log is printed after Looper.loop() method. Only when this thread loop quit can this log be printed.");
            // Send a message to main thread.
            Message msg = new Message();
            msg.what = CHILD_THREAD_QUIT_LOOPER;
            mainThreadHandler.sendMessage(msg);
        }
    }


    private class FormatDocument {
        private String returnString() {
            try {
                String url = getResources().getString(R.string.url);
                //String url = "@string/url";
                Document doc = Jsoup.connect(url).get();

                Element e2 = doc.select("p:contains(Dogs)").get(0);
                String dogHeader=e2.toString();
                //Still hast HTML tags
                dogTitle = Jsoup.parse(dogHeader).text();
                Log.i("dogTitle:",""+dogTitle);
                String parString = e2.nextElementSibling().toString();
                String dogTextHtml = dogTitle + parString;

                //Document doc2 = Jsoup.parse(dogTextHtml);
                Document doc2 = Jsoup.parse(parString);
                doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
                doc2.select("p").append("\\n");
                doc2.select("li").prepend("\\n\\n");
                String s = doc2.html().replaceAll("\\\\n", "\n");
                dogText = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));

                //combinedString=e2+parElement;

                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");

            } catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            return dogText;
        }
    }

    private class FormatDocument2 {
        private String returnString2() {


            try {
                String url = getResources().getString(R.string.url);
                //String url = "@string/url";
                Document doc = Jsoup.connect(url).get();



                String catHeader;

                Element e2 = doc.select("p:contains(Cats)").get(0);
                //Still hast HTML tags
                catHeader = e2.toString();
                catTitle = Jsoup.parse(catHeader).text();
                String parString = e2.nextElementSibling().toString();
                //String dogTextHtml = catTitle + parString;

                //Document doc2 = Jsoup.parse(dogTextHtml);
                Document doc2 = Jsoup.parse(parString);

                doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
                doc2.select("p").append("\\n");
                doc2.select("li").prepend("\\n\\n");
                String s = doc2.html().replaceAll("\\\\n", "\n");
                catText = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));

                //combinedString=e2+parElement;

                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");

            } catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            return catText;
        }
    }

    private class FormatDocument3 {
        private String returnString3() {


            try {
                String url = getResources().getString(R.string.url);
                //String url = "@string/url";
                Document doc = Jsoup.connect(url).get();



                String frogHeader;

                Element e2 = doc.select("p:contains(Frogs)").get(0);
                //Still hast HTML tags

                frogHeader = e2.toString();
                frogTitle = Jsoup.parse(frogHeader).text();
                String parString = e2.nextElementSibling().toString();
                String dogTextHtml = frogHeader + parString;

                //Document doc2 = Jsoup.parse(dogTextHtml);
                Document doc2 = Jsoup.parse(parString);

                doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
                doc2.select("p").append("\\n");
                doc2.select("li").prepend("\\n\\n");
                String s = doc2.html().replaceAll("\\\\n", "\n");
                frogText = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));

                //combinedString=e2+parElement;

                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");

            } catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            return frogText;
        }
    }
}
