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
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private int MAIN_THREAD_TASK_1 = 1;
    private int MAIN_THREAD_TASK_2 = 2;
    private int CHILD_THREAD_QUIT_LOOPER = 3;

    private Handler mainThreadHandler;

    private MyWorkerThread workerThread = null;

    private ImageButton runTaskOneButton;

    private ImageButton runTaskTwoButton;

    private TextView taskStatusTextView;

    public String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create and start the worker thread.
        workerThread = new MyWorkerThread();
        workerThread.start();

        // Handle message from main thread message queue.
        mainThreadHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                Log.i("MAIN_THREAD", "Receive message from child thread.");
                if(msg.what == MAIN_THREAD_TASK_1)
                {
                    // If task one button is clicked.
                    taskStatusTextView.setText(text);
                }else if(msg.what == MAIN_THREAD_TASK_2)
                {
                    // If task two button is clicked.
                    taskStatusTextView.setText("Task two execute.");
                }else if(msg.what == CHILD_THREAD_QUIT_LOOPER)
                {
                    // If quit child thread looper button is clicked.
                    taskStatusTextView.setText("Quit child thread looper.");
                }
            }
        };

        // Get run task buttons.
        runTaskOneButton = findViewById(R.id.dogButton);
        runTaskTwoButton = findViewById(R.id.catButton);

        // Set on click listener to each button.
        runTaskOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When click this button, create a message object.
                Message msg = new Message();
                msg.what = MAIN_THREAD_TASK_1;
                // Use worker thread message Handler to put message into worker thread message queue.
                workerThread.workerThreadHandler.sendMessage(msg);
            }
        });

        // Please see comments for runTaskOneButton.
        runTaskTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = MAIN_THREAD_TASK_2;
                workerThread.workerThreadHandler.sendMessage(msg);
            }
        });

        // Get status info TextView object.
        taskStatusTextView = findViewById(R.id.somethingText);

        // Get the quit child thread looper button.
        ImageButton quitChildThreadLooperButton = findViewById(R.id.frogButton);
        quitChildThreadLooperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click this button will quit child thread looper.
                workerThread.workerThreadHandler.getLooper().quit();
            }
        });
    }

    // This child thread class has it's own Looper and Handler object.
    private class MyWorkerThread extends Thread{
        //Log.i("MyWorkerThread","MyWorkerThread created")
        // This is worker thread handler.
        public Handler workerThreadHandler;

        @Override
        public void run() {
            // Prepare MyWorkerThread which is a child of Thread Looper object.
            //Prepares thread to add tasks in a loop
            Looper.prepare();

            final StringBuilder builder = new StringBuilder();

            try {
                //first connect to website then .get() downloads content
                //Document parse("<html><div><p>Dogs</p>","https://mydavidjerome.com/android-app/");
                //Document doc = Jsoup.parse(html);
                //Element body=doc.body();
                //String body2=body.toString();
                //text=body2;
                //.first().getElementsbyTag();
                Document doc = Jsoup.connect("https://mydavidjerome.com/android-app/").get();
                String title = doc.title();
                //gets links on html webpage
                //returns list to app
                //String paragraphs=doc.getElementsByTag("p").toString();
                //String paragraph="";
               /* while(true){
                    paragraphs.hasNext();
                }*/

               //String paragraphs=doc.getElementsByTag("p").toString();
                //document.select("div#newscontent").select("div.l").select("span.s2").select("a");
               /* Elements wholePar=doc.select("div.entry-content").select("p Dogs").select("ol li");
                StringBuilder part0=new StringBuilder();
                for(Element part: wholePar)
                {
                    Log.i("wholeParE",""+(part.toString()));
                    part0.append(part.toString());

                }*/
                //text=part0;
                //Element dogsParagraph=doc.select("p:contains(Dogs)").get(1);
                //String w2 = dogsParagraph.body().text();
                //text=w2;                                       //.get(1)
                //ALTERED HERE
                String dogsTitle="";
                String dogsPar="";
                Element e2=doc.select("p:contains(Dogs)").get(0);
                dogsTitle=e2.toString();
                dogsPar=e2.nextElementSibling().toString();
                text=dogsTitle+dogsPar;

                Log.i("in Element","paragraph of dogs");

                //text=word;
                //Log.i("word","Found Dogs Par");
                //String[] splitted = paragraphs.split("\\s+");

                /*for( int i = 0; i <= splitted.length - 1; i++)
                {
                    String paragraphTitle=splitted[i];
                    if(paragraphTitle.contains("<p>Dogs</p>"))
                    {
                        text="found paragraph "+paragraphTitle;
                        Log.i("String","Found paragraph");
                        break;
                    }
                    else Log.i("no Par","No paragraph found");
                        Log.i("Array",""+paragraphTitle);



                }*/
                //doc.body().children().select("*");
                    /*for(String paragraphs=doc.getElementsByTag("p").toString();paraphraph=="Dogs";paragraphs.Next())


                    Log.i("par","paragraphs");*/



                /*Iterator iter=doc.getElementsByTag("p").iterator();
                    for(Iterator<Element> iter = iter.iterator(); iter.hasNext(); )
                    text = doc.getElementsByTag("p").text();
                for text=text.hasNext();*/
                //for (String text : text)
                //text = doc.body().text();
                Log.i("JSoup", "Connected successfully!");


                //builder.append(title).append("\n").append(text);


                //This builds the webpage by going through each link
                //For loop may be unnecessary
                /*for (String Element : text) {
                    builder.append("\n").append("Link : ").append(link.attr("href"))
                            .append("\n").append("Text : ").append(link.text());
                }*/
            } catch (IOException e) {
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR","exception reading HTML");
            }

            //STILL NEED TO UPDATE UI
            //somethingText.setText(text);

            // Create child thread Handler. Connects Looper to current(MyWorkerThread)thread
            workerThreadHandler = new Handler(Looper.myLooper()){
                @Override
                public void handleMessage(Message msg) {
                    // When child thread handler get message from child thread message queue.
                    Log.i("CHILD_THREAD", "Receive message from main thread.");
                    Message message = new Message();
                    message.what = msg.what;
                    Log.i("AFtermsg.what","msg.what;");
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
}