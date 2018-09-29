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

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private int MAIN_THREAD_TASK_1 = 1;
    private int MAIN_THREAD_TASK_2 = 2;
    private int CHILD_THREAD_QUIT_LOOPER = 3;

    private Handler mainThreadHandler;

    private MyWorkerThread workerThread = null;

    private ImageButton runTaskOneButton;

    private ImageButton runTaskTwoButton;

    private TextView taskStatusTextView;


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
                    taskStatusTextView.setText("Task one execute.");
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
                Document doc = Jsoup.connect("https://mydavidjerome.com/android-app/").get();
                String title = doc.title();
                Elements links = doc.select("a[href]");

                builder.append(title).append("\n");

                for (Element link : links) {
                    builder.append("\n").append("Link : ").append(link.attr("href"))
                            .append("\n").append("Text : ").append(link.text());
                }
            } catch (IOException e) {
                builder.append("Error : ").append(e.getMessage()).append("\n");
            }

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