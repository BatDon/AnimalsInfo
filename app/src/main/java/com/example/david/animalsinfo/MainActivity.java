package com.example.david.animalsinfo;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Reader;

public class MainActivity extends AppCompatActivity {

    private int MAIN_THREAD_TASK_1 = 1;
    private int MAIN_THREAD_TASK_2 = 2;
    private int MAIN_THREAD_TASK_3 = 3;
    private int CHILD_THREAD_QUIT_LOOPER = 4;

    private Handler mainThreadHandler;

    private MyWorkerThread workerThread = null;

    private ImageButton runTaskOneButton;

    private ImageButton runTaskTwoButton;

    private ImageButton runTaskThreeButton;
    public TextView taskStatusTextView;

    public String dogTextHtml;
    public String dogText;

    public String catTextHtml;
    public String catText;

    public String frogTextHtml;
    public String frogText;

    public Reader reader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create and start the worker thread.
        workerThread = new MyWorkerThread();
        workerThread.start();

        // Handle message from main thread message queue.
        mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.i("MAIN_THREAD", "Receive message from child thread.");
                taskStatusTextView = findViewById(R.id.somethingText);
                if (msg.what == MAIN_THREAD_TASK_1) {
                    taskStatusTextView.setText(dogText);}
                    else if (msg.what == MAIN_THREAD_TASK_2) {
                        // If task two button is clicked.
                        taskStatusTextView.setText(catText);}
                        else if (msg.what == MAIN_THREAD_TASK_3) {
                        // If quit child thread looper button is clicked.
                        taskStatusTextView.setText(frogText);
                    }
                }
            }

            ;

            // Get run task buttons.
            runTaskOneButton =findViewById(R.id.dogButton);

            runTaskTwoButton =findViewById(R.id.catButton);

            runTaskThreeButton=findViewById(R.id.frogButton);

            // Set on click listener to each button.
        runTaskOneButton.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                // When click this button, create a message object.
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
                public void onClick (View view){
                Message msg = new Message();
                msg.what = MAIN_THREAD_TASK_2;
                workerThread.workerThreadHandler.sendMessage(msg);
            }
            });
        runTaskThreeButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Message msg = new Message();
                msg.what = MAIN_THREAD_TASK_3;
                workerThread.workerThreadHandler.sendMessage(msg);
            }
        });


            // Get status info TextView object.
            taskStatusTextView =findViewById(R.id.somethingText);

            // Get the quit child thread looper button.
           /* ImageButton quitChildThreadLooperButton = findViewById(R.id.frogButton);
        quitChildThreadLooperButton.setOnClickListener(new View.OnClickListener()*/

            /*{
                @Override
                public void onClick (View view){
                // Click this button will quit child thread looper.
                workerThread.workerThreadHandler.getLooper().quit();
            }
            });*/
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
                FormatDocument formattedDoc=new FormatDocument();
                dogText=formattedDoc.returnString();

                FormatDocument2 formattedDoc2=new FormatDocument2();
                catText=formattedDoc2.returnString2();

                FormatDocument3 formattedDoc3=new FormatDocument3();
                frogText=formattedDoc3.returnString3();


                //final StringBuilder builder = new StringBuilder();
                //String dogText = formatted.returnString();

                //FormatDocument formattedString=new FormatDocument();

            /*public class FormatDocument {
                public static String returnString() {


                    try {
                        String url = "https://mydavidjerome.com/android-app/";
                        Document doc = Jsoup.connect(url).get();
                        String title = doc.title();


                        Element titleElement;
                        Element parElement;
                        String combinedString;


                        String dogTitle;
                        String dogsPar = null;
                        Element e2 = doc.select("p:contains(Dogs)").get(0);
                        //Still hast HTML tags
                        dogTitle = e2.toString();
                        String parString = e2.nextElementSibling().toString();
                        String dogTextHtml = dogTitle + parString;

                        Document doc2 = Jsoup.parse(dogTextHtml);


                        doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
                        doc2.select("p").append("\\n");
                        doc2.select("li").prepend("\\n\\n");
                        String s = doc2.html().replaceAll("\\\\n", "\n");
                        dogText = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));

                        //combinedString=e2+parElement;

                        Log.i("in Element", "paragraph of dogs");


                        Log.i("JSoup", "Connected successfully!");


                    } catch (IOException e) {
                        builder.append("Error : ").append(e.getMessage()).append("\n");
                        Log.i("HTML ERROR", "exception reading HTML");
                    }
                }
            }*/

                //STILL NEED TO UPDATE UI
                //somethingText.setText(text);

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




        public class FormatDocument {
            public String returnString() {


                try {
                    String url = "https://mydavidjerome.com/android-app/";
                    Document doc = Jsoup.connect(url).get();
                    String title = doc.title();


                    Element titleElement;
                    Element parElement;
                    String combinedString;


                    String dogTitle;
                    String dogsPar = null;
                    Element e2 = doc.select("p:contains(Dogs)").get(0);
                    //Still hast HTML tags
                    dogTitle = e2.toString();
                    String parString = e2.nextElementSibling().toString();
                    String dogTextHtml = dogTitle + parString;

                    Document doc2 = Jsoup.parse(dogTextHtml);


                    doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
                    doc2.select("p").append("\\n");
                    doc2.select("li").prepend("\\n\\n");
                    String s = doc2.html().replaceAll("\\\\n", "\n");
                    dogText = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));

                    //combinedString=e2+parElement;

                    Log.i("in Element", "paragraph of dogs");


                    Log.i("JSoup", "Connected successfully!");

                }

                 catch (IOException e) {
                    final StringBuilder builder = new StringBuilder();
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                    Log.i("HTML ERROR", "exception reading HTML");
                }
                return dogText;
            }
        }
    public class FormatDocument2 {
        public String returnString2() {


            try {
                String url = "https://mydavidjerome.com/android-app/";
                Document doc = Jsoup.connect(url).get();
                String title = doc.title();


                Element titleElement;
                Element parElement;
                String combinedString;


                String dogTitle;
                String dogsPar = null;
                Element e2 = doc.select("p:contains(Cats)").get(0);
                //Still hast HTML tags
                dogTitle = e2.toString();
                String parString = e2.nextElementSibling().toString();
                String dogTextHtml = dogTitle + parString;

                Document doc2 = Jsoup.parse(dogTextHtml);


                doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
                doc2.select("p").append("\\n");
                doc2.select("li").prepend("\\n\\n");
                String s = doc2.html().replaceAll("\\\\n", "\n");
                catText = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));

                //combinedString=e2+parElement;

                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");

            }

            catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            return catText;
        }
    }
    public class FormatDocument3 {
        public String returnString3() {


            try {
                String url = "https://mydavidjerome.com/android-app/";
                Document doc = Jsoup.connect(url).get();
                String title = doc.title();


                Element titleElement;
                Element parElement;
                String combinedString;


                String dogTitle;
                String dogsPar = null;
                Element e2 = doc.select("p:contains(Frogs)").get(0);
                //Still hast HTML tags
                dogTitle = e2.toString();
                String parString = e2.nextElementSibling().toString();
                String dogTextHtml = dogTitle + parString;

                Document doc2 = Jsoup.parse(dogTextHtml);


                doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
                doc2.select("p").append("\\n");
                doc2.select("li").prepend("\\n\\n");
                String s = doc2.html().replaceAll("\\\\n", "\n");
                frogText = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));

                //combinedString=e2+parElement;

                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");

            }

            catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            return frogText;
        }
    }
    }
