package com.example.david.animalsinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private int MAIN_THREAD_TASK_1 = 1;
    private int MAIN_THREAD_TASK_2 = 2;
    private int MAIN_THREAD_TASK_3 = 3;
    private int MAIN_THREAD_TASK_4 = 4;
    private int CHILD_THREAD_QUIT_LOOPER = 5;

    Intent intent1;

    private Handler mainThreadHandler;

    private MyWorkerThread workerThread = null;
    private MyWorkerThread2 workerThread2 = null;

    public String dogTitle;
    //public String dogTextRight;
    //public String dogTextLeft;
    //public String dogTextCenter;
    String[]dogFormatted=new String[10];
    String[]dogTable=new String[10];

    public String catTitle;
    public String catText;
    public Integer catWords;

    public String frogTitle;
    public String frogText;

    //Website where information will be pulled from
    public static final String EXTRA_MESSAGE = "@string/myPackage";
    public static final String EXTRA_MESSAGE_TWO = "@string/myPackage2";
    public static final String EXTRA_MESSAGE_THREE = "@string/myPackage3";
    public static final String EXTRA_MESSAGE_FOUR = "@string/myPackage4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainx720);
        Log.i("onCreateMethod","0000000000000000000000");

//        View vf=(View)findViewById(R.id.frogButton);
//        Integer x2 = vf.getWidth();
//        Integer y2 = vf.getHeight();
//        Log.i("Forg image","Width= "+(x2.toString())+" Height= "+(y2.toString());
//        final ImageView frogButton = (ImageView) findViewById(R.id.frogButton);
//        Bitmap frogIcon = BitmapFactory.decodeResource(getResources(), R.drawable.frog);
//        Bitmap frogIcon2 = Bitmap.createScaledBitmap(frogIcon, x2, y2, true);
//        frogButton.setImageBitmap(frogIcon2);



        ImageView v = (ImageView) findViewById(R.id.dogButton);

        v.getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalListenerClass());

        // Create and start the worker thread.
        workerThread = new MyWorkerThread();
        workerThread.start();

        workerThread2=new MyWorkerThread2();
        workerThread2.start();

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
                Log.i("runTaskOneButton","11111111111111111111111111");
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
    //public void sendActivity(String dogTitle,String dogTextLeft,String dogTextCenter,String dogTextRight) {
//    public void sendActivity(String dogTitle,String dogTextLeft,String dogTextCenter,String dogTextRight){
    public void sendActivity(Intent intent){
//        Intent intent = new Intent(this, DogsActivity.class);
//        //Text editText =findViewById(R.id.somethingText);
//        //String message = editText.getText().toString();
//        Log.i("sendActivity","222222222222222222222222");
//        intent.putExtra(EXTRA_MESSAGE, dogTitle);
//        intent.putExtra(EXTRA_MESSAGE_TWO, dogTextLeft);
//        intent.putExtra(EXTRA_MESSAGE_THREE,dogTextCenter);
//        intent.putExtra(EXTRA_MESSAGE_FOUR,dogTextRight);
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
            Log.i("run","3333333333333333333333");

            //Get Screen size to format images

                    //show ImageView width and height
                    //((TextView)findViewById(R.id.textXY)).setText(x+","+y);




            //Allows Media Player to have volume
            final MediaPlayer barking = MediaPlayer.create(MainActivity.this, R.raw.barking2);
            final MediaPlayer meowing = MediaPlayer.create(MainActivity.this, R.raw.meowing);
            final MediaPlayer ribbiting = MediaPlayer.create(MainActivity.this, R.raw.frog);

            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);

            //Creates a readable HTML document by calling FormatDocument()
            FormatDocument formattedDoc = new FormatDocument();
//            dogTextLeft = formattedDoc.returnString();
            Log.i("runTaskOneButton","4444444444444444444444444");
            dogFormatted = formattedDoc.returnStringArray();

            FormatDocument2 formattedDoc2 = new FormatDocument2();
            catText = formattedDoc2.returnString2();

            FormatDocument3 formattedDoc3 = new FormatDocument3();
            frogText = formattedDoc3.returnString3();


            // Create child thread Handler. Connects Looper to current(MyWorkerThread)thread
            workerThreadHandler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    Log.i("handleMessage","55555555555555555555555");
                    // When child thread handler get message from child thread message queue.
                    Log.i("CHILD_THREAD", "Receive message from main thread.");
                    Message message = new Message();
                    message.what = msg.what;
                    Log.i("AFtermsg.what", ""+msg.what);
                    if (msg.what == MAIN_THREAD_TASK_1) {
                        Log.i("if msg.what==Main","6666666666666666666666666");
                        barking.start();
                        //taskStatusTextView.setText(dogText);
                        sendActivity(intent1);
                        //sendActivity(dogTitle,dogTextLeft,dogTextCenter,dogTextRight);
                    } else if (msg.what == MAIN_THREAD_TASK_2) {
                        // If task two button is clicked.
                        meowing.start();
                        //taskStatusTextView.setText(catText);
                        sendActivity2(catTitle,catText);
                        ImageView v = (ImageView) findViewById(R.id.catButton);
                        String x = Integer.toString(v.getWidth());
                        String y = Integer.toString(v.getHeight());
                        Log.i("cat x and y","x= "+x+" y= "+y);
                    } else if (msg.what == MAIN_THREAD_TASK_3) {
                        // If quit child thread looper button is clicked.
                        ribbiting.start();
                        sendActivity3(frogTitle,frogText);
                        ImageView v = (ImageView) findViewById(R.id.frogButton);
                        String x = Integer.toString(v.getWidth());
                        String y = Integer.toString(v.getHeight());
                        Log.i("frog x and y","x= "+x+" y= "+y);
                        //taskStatusTextView.setText(frogText);
                    }

                    // Send the message back to main thread message queue use main thread message Handler.
//                    mainThreadHandler.sendMessage(message);
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

    //WorkerThread for Database from MySQL and PHP
    private class MyWorkerThread2 extends Thread {
        //Log.i("MyWorkerThread","MyWorkerThread created")
        // This is worker thread handler.
        public Handler workerThreadHandler2;

        @Override
        public void run() {
            // Prepare MyWorkerThread which is a child of Thread Looper object.
            //Prepares thread to add tasks in a loop
            Log.i("handleMessage","###################");
            Looper.prepare();
            //Gets database from website
            FormatDatabase formatDatabase=new FormatDatabase();
            dogTable = formatDatabase.returnStringArray2();


            // Create child thread Handler. Connects Looper to current(MyWorkerThread)thread
            workerThreadHandler2 = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    Log.i("handleMessage","$$$$$$$$$$$$$$$$$$$");
                    // When child thread handler get message from child thread message queue.
                    Log.i("CHILD_THREAD", "Receive message from main thread.");
                    Message message = new Message();
                    message.what = msg.what;
                    Log.i("AFtermsg.what", ""+msg.what);


                    // Send the message back to main thread message queue use main thread message Handler.
//                    mainThreadHandler.sendMessage(message);
                }

            };
            // Loop the child thread message queue.
            Looper.loop();

            // The code after Looper.loop() will not be executed until you call workerThreadHandler.getLooper().quit()
            Log.i("CHILD_THREAD", "This log is printed after Looper.loop() method. Only when this thread loop quit can this log be printed.");
            // Send a message to main thread.
            //Message msg = new Message();
            //msg.what = CHILD_THREAD_QUIT_LOOPER;
            //mainThreadHandler.sendMessage(msg);
        }
    }



    private class FormatDocument {
        private String[] dogFormattedArray=new String[3];
        private String[] returnStringArray() {
            try {
                Log.i("runTaskOneButton","777777777777777777777777");
                String url = getResources().getString(R.string.url);
                //String url = "@string/url";
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.body().select("*");
//                Elements e5=elements.select("p:contains(Dogs)");
                Element masthead = doc.select("div.masthead").first();


                //This selects the class that all the animals are in
                Element e5 = doc.select("div.entry-content").first();

                Element e6 = e5.child(1);
                Elements e9 = e6.children();
                //Element e7 = e5.child(2);
                //Element e8 = e5.child(3);


//                MAKE AN ARRAY HERE
                int numberOfDogs = 0;
                String[] dogArray = new String[100];
                //ArrayList<Element> arrayElements = new ArrayList<>();
                for (Element e : e9) {
                    numberOfDogs += 1;
                    dogArray[numberOfDogs] = (e.text());

                    Log.i("eachDogHere: ", "" + (dogArray[numberOfDogs]));
                }
                String halfWords = dogArray[1];

                Log.i("Dogs/2=", "" + (numberOfDogs / 2));

                //If an odd number of dogs add one more to left column
                int remDog = numberOfDogs % 3;
                int dogsInList=numberOfDogs/3;

                int dogsRem=0;
                int dogsRem2=0;

                int dogsLeft=0;
                int dogsCenter=0;
                int dogsRight=0;
                if (remDog > 0 && remDog<2){

                     dogsRem+=1;
                    dogsLeft+=1;
                }
                else if (remDog>0){
                    dogsRem2=2;
                    dogsLeft+=1;
                    dogsCenter+=1;
                }


                dogsLeft=dogsLeft+dogsInList;
                dogsCenter=dogsCenter+dogsInList;
                dogsRight=dogsInList;


                //Builds list for left side of Dogs
                StringBuilder dogsLeftBuild=new StringBuilder();
                String leftDogs="";
                for(int i=1;i<=dogsLeft;i++){
                    dogsLeftBuild.append(dogArray[i]);
                    dogsLeftBuild.append((System.getProperty("line.separator")));
                    Log.i("Times ifn leftDogs loop","lets see "+i);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                //String dogsLeftColumn=dogsLeftBuild.toString();
                leftDogs=dogsLeftBuild.toString();
                Log.i("DogsLEft Column=  ", ""+leftDogs);
                String dogTextLeft=leftDogs;

                int limitCenterLoop=dogsLeft+dogsCenter;

                StringBuilder dogsCenterBuild=new StringBuilder();
                String centerDogs="";
                for(int p=dogsLeft+1;p>dogsLeft&& p<=limitCenterLoop;p++){
                    dogsCenterBuild.append(dogArray[p]);
                    dogsCenterBuild.append((System.getProperty("line.separator")));
                    //Log.i("Times ifn centerD loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                centerDogs=dogsCenterBuild.toString();
                //Log.i("DogsCenter Column=  ", ""+centerDogs);
                String dogTextCenter=centerDogs;

                //dogTextCenter="something";

                int limitRightLoop=dogsCenter+dogsRight;

                //Creates right side list for Dogs
                StringBuilder dogsRightBuild=new StringBuilder();
                String rightDogs="";
                for(int p=limitCenterLoop+1;p<=numberOfDogs;p++){
                    dogsRightBuild.append(dogArray[p]);
                    dogsRightBuild.append((System.getProperty("line.separator")));
                    //Log.i("Times ifn right loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                rightDogs=dogsRightBuild.toString();
                //Log.i("DogsRight Column=  ", ""+rightDogs);
                String dogTextRight=rightDogs;


                String dogsOnLeft=dogsLeft+"";
                String dogsOnRight=dogsRight+"";





                //Element[] elementArr = arrayElements.toArray(new Element[]{});
                Log.i("numberOfDogs =",""+numberOfDogs);




                Element e2 = doc.select("p:contains(Dogs)").get(0);
                dogTitle=e2.text();

                catWords=0;
                String cattt;

                Log.i("E9   Should Dogs",""+e9);
                Element cattEle=null;



                //NEED TO MAKE A STRING ARRAY THAT CAN BE PUT INTO TWO DIFFERENT TEXT VIEWS SIDE BY SIDE
                //TO GET EACH SEPERATE ELEMENT DELIMITED BY /N
                //public class TestConsole {
                //   public static void main(String[] args) {
                //      String nixSampleLine = "Line 1 \n Line 2 \n Line 3";
                //      String[] lines = nixSampleLine.split("\\r?\\n");
                //      for (String line : lines) {
                //         System.out.println(line);
                //      }
                //   }
                //}


                dogFormattedArray[0]=dogTextLeft;
                dogFormattedArray[1]=dogTextCenter;
                dogFormattedArray[2]=dogTextRight;

                intent1 = new Intent(MainActivity.this, DogsActivity.class);
                //Text editText =findViewById(R.id.somethingText);
                //String message = editText.getText().toString();
                Log.i("sendActivity","222222222222222222222222");
                intent1.putExtra(EXTRA_MESSAGE, dogTitle);
                intent1.putExtra(EXTRA_MESSAGE_TWO, dogTextLeft);
                intent1.putExtra(EXTRA_MESSAGE_THREE,dogTextCenter);
                intent1.putExtra(EXTRA_MESSAGE_FOUR,dogTextRight);


                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");

            } catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            //return dogTextLeft;
            return dogFormattedArray;
        }
    }
    private class FormatDatabase {
        private String[] dogFormattedArray2=new String[3];
        private String[] returnStringArray2() {
            try {
                String url = getResources().getString(R.string.url2);
                //String url = "@string/url";
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.body().select("*");
                Log.i("FormatDatabase"," "+elements);
//                Elements e5=elements.select("p:contains(Dogs)");
                //Element masthead = doc.select("div.masthead").first();
                //Log.i("inFormatDatabase"," "+masthead);


                //This selects the class that all the animals are in
                //Element e5 = doc.select("div.entry-content").first();

                //Element e6 = e5.child(1);
                //Elements e9 = e6.children();
                //Element e7 = e5.child(2);
                //Element e8 = e5.child(3);


//                MAKE AN ARRAY HERE
//                int numberOfDogs = 0;
//                String[] dogArray = new String[100];
//                //ArrayList<Element> arrayElements = new ArrayList<>();
//                for (Element e : e9) {
//                    numberOfDogs += 1;
//                    dogArray[numberOfDogs] = (e.text());
//
//                    Log.i("eachDogHere: ", "" + (dogArray[numberOfDogs]));
//                }
                //String halfWords = dogArray[1];

                //Log.i("Dogs/2=", "" + (numberOfDogs / 2));

                //If an odd number of dogs add one more to left column
//                int remDog = numberOfDogs % 3;
//                int dogsInList=numberOfDogs/3;
//
//                int dogsRem=0;
//                int dogsRem2=0;
//
//                int dogsLeft=0;
//                int dogsCenter=0;
//                int dogsRight=0;
//                if (remDog > 0 && remDog<2){
//
//                    dogsRem+=1;
//                    dogsLeft+=1;
//                }
//                else if (remDog>0){
//                    dogsRem2=2;
//                    dogsLeft+=1;
//                    dogsCenter+=1;
//                }


//                dogsLeft=dogsLeft+dogsInList;
//                dogsCenter=dogsCenter+dogsInList;
//                dogsRight=dogsInList;


                //Builds list for left side of Dogs
                //StringBuilder dogsLeftBuild=new StringBuilder();
//                String leftDogs="";
//                for(int i=1;i<=dogsLeft;i++){
//                    dogsLeftBuild.append(dogArray[i]);
//                    dogsLeftBuild.append((System.getProperty("line.separator")));
//                    Log.i("Times ifn leftDogs loop","lets see "+i);
//                    //leftDogs=dogArray[i];
//                    //leftDogs=leftDogs+("\n");
//                }
//                //String dogsLeftColumn=dogsLeftBuild.toString();
//                leftDogs=dogsLeftBuild.toString();
//                Log.i("DogsLEft Column=  ", ""+leftDogs);
//                String dogTextLeft=leftDogs;
//
//                int limitCenterLoop=dogsLeft+dogsCenter;
//
//                StringBuilder dogsCenterBuild=new StringBuilder();
//                String centerDogs="";
//                for(int p=dogsLeft+1;p>dogsLeft&& p<=limitCenterLoop;p++){
//                    dogsCenterBuild.append(dogArray[p]);
//                    dogsCenterBuild.append((System.getProperty("line.separator")));
//                    //Log.i("Times ifn centerD loop","lets see "+p);
//                    //leftDogs=dogArray[i];
//                    //leftDogs=leftDogs+("\n");
//                }
//                centerDogs=dogsCenterBuild.toString();
//                //Log.i("DogsCenter Column=  ", ""+centerDogs);
//                String dogTextCenter=centerDogs;
//
//                //dogTextCenter="something";
//
//                int limitRightLoop=dogsCenter+dogsRight;
//
//                //Creates right side list for Dogs
//                StringBuilder dogsRightBuild=new StringBuilder();
//                String rightDogs="";
//                for(int p=limitCenterLoop+1;p<=numberOfDogs;p++){
//                    dogsRightBuild.append(dogArray[p]);
//                    dogsRightBuild.append((System.getProperty("line.separator")));
//                    //Log.i("Times ifn right loop","lets see "+p);
//                    //leftDogs=dogArray[i];
//                    //leftDogs=leftDogs+("\n");
//                }
//                rightDogs=dogsRightBuild.toString();
//                //Log.i("DogsRight Column=  ", ""+rightDogs);
//                String dogTextRight=rightDogs;
//
//
//                String dogsOnLeft=dogsLeft+"";
//                String dogsOnRight=dogsRight+"";





                //Element[] elementArr = arrayElements.toArray(new Element[]{});
                //Log.i("numberOfDogs =",""+numberOfDogs);




//                Element e2 = doc.select("p:contains(Dogs)").get(0);
//                dogTitle=e2.text();
//
//                catWords=0;
//                String cattt;
//
//                //Log.i("E9   Should Dogs",""+e9);
//                Element cattEle=null;



                //NEED TO MAKE A STRING ARRAY THAT CAN BE PUT INTO TWO DIFFERENT TEXT VIEWS SIDE BY SIDE
                //TO GET EACH SEPERATE ELEMENT DELIMITED BY /N
                //public class TestConsole {
                //   public static void main(String[] args) {
                //      String nixSampleLine = "Line 1 \n Line 2 \n Line 3";
                //      String[] lines = nixSampleLine.split("\\r?\\n");
                //      for (String line : lines) {
                //         System.out.println(line);
                //      }
                //   }
                //}


//                dogFormattedArray[0]=dogTextLeft;
//                dogFormattedArray[1]=dogTextCenter;
//                dogFormattedArray[2]=dogTextRight;
//
//                intent1 = new Intent(MainActivity.this, DogsActivity.class);
//                //Text editText =findViewById(R.id.somethingText);
//                //String message = editText.getText().toString();
//                Log.i("sendActivity","222222222222222222222222");
//                intent1.putExtra(EXTRA_MESSAGE, dogTitle);
//                intent1.putExtra(EXTRA_MESSAGE_TWO, dogTextLeft);
//                intent1.putExtra(EXTRA_MESSAGE_THREE,dogTextCenter);
//                intent1.putExtra(EXTRA_MESSAGE_FOUR,dogTextRight);


                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");


            } catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            //return dogTextLeft;
            return dogFormattedArray2;
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
                String catTextBefore;
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




    class MyGlobalListenerClass implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            View v = (View) findViewById(R.id.dogButton);
            Integer x = v.getWidth();
            Integer y = v.getHeight();
            //show ImageView width and height
            final ImageView dogButton = (ImageView) findViewById(R.id.dogButton);
            Bitmap dogIcon = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
            Bitmap dogIcon2 = Bitmap.createScaledBitmap(dogIcon, x, y, true);
            dogButton.setImageBitmap(dogIcon2);


            View vc=(View)findViewById(R.id.catButton);
            Integer x1 = vc.getWidth();
            Integer y1 = vc.getHeight();
            final ImageView catButton = (ImageView) findViewById(R.id.catButton);
            Bitmap catIcon = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
            Bitmap catIcon2 = Bitmap.createScaledBitmap(catIcon, x1, y1, true);
            catButton.setImageBitmap(catIcon2);

            View vf=(View)findViewById(R.id.frogButton);
            Integer x2 = vf.getWidth();
            Integer y2 = vf.getHeight();
            Log.i("Forg image","Width= "+x2.toString()+" Height= "+y2.toString());
            final ImageView frogButton = (ImageView) findViewById(R.id.frogButton);
            Bitmap frogIcon = BitmapFactory.decodeResource(getResources(), R.drawable.frog);
            Bitmap frogIcon2 = Bitmap.createScaledBitmap(frogIcon, x2, y2, true);
            //Bitmap frogIcon2 = Bitmap.createScaledBitmap(frogIcon, x2, y2, true);
            frogButton.setImageBitmap(frogIcon2);
          }
    }




            //final ImageView catButton = (ImageView) findViewById(R.id.catButton);

//        Bitmap catIcon = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
//        Bitmap catIcon2 = Bitmap.createScaledBitmap(catIcon, 774, 348, true);
////        Bitmap catIcon2 = Bitmap.createScaledBitmap(dogIcon, newWidth, newHeight, true);
//        catButton.setImageBitmap(catIcon2);

//        final ImageView frogButton = (ImageView) findViewById(R.id.frogButton);
//        Bitmap frogIcon = BitmapFactory.decodeResource(getResources(), R.drawable.frog);
//        Bitmap frogIcon2 = Bitmap.createScaledBitmap(frogIcon, 774, 348, true);
////        Bitmap catIcon2 = Bitmap.createScaledBitmap(dogIcon, newWidth, newHeight, true);
//        frogButton.setImageBitmap(frogI




}
