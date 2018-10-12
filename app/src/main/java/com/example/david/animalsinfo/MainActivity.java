package com.example.david.animalsinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
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
    private int CHILD_THREAD_QUIT_LOOPER = 4;

    private Handler mainThreadHandler;

    private MyWorkerThread workerThread = null;
    //private MyWorkerThread2 workerThread2 = null;

    public String dogTitle;
    public String dogTextRight;
    public String dogTextLeft;
    public String dogTextCenter;

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
        ImageView v = (ImageView) findViewById(R.id.dogButton);
        Integer x = v.getWidth();
        Integer y = v.getHeight();
        final ImageView dogButton = (ImageView) findViewById(R.id.dogButton);
        Bitmap dogIcon = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        Bitmap dogIcon2 = Bitmap.createScaledBitmap(dogIcon, x, y, true);
//        Bitmap digIcon2 = Bitmap.createScaledBitmap(dogIcon, newWidth, newHeight, true);
        dogButton.setImageBitmap(dogIcon2);
        final ImageView catButton = (ImageView) findViewById(R.id.catButton);
        Bitmap catIcon = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        Bitmap catIcon2 = Bitmap.createScaledBitmap(catIcon, 774, 348, true);
//        Bitmap catIcon2 = Bitmap.createScaledBitmap(dogIcon, newWidth, newHeight, true);
        catButton.setImageBitmap(catIcon2);

        final ImageView frogButton = (ImageView) findViewById(R.id.frogButton);
        Bitmap frogIcon = BitmapFactory.decodeResource(getResources(), R.drawable.frog);
        Bitmap frogIcon2 = Bitmap.createScaledBitmap(frogIcon, 774, 348, true);
//        Bitmap catIcon2 = Bitmap.createScaledBitmap(dogIcon, newWidth, newHeight, true);
        frogButton.setImageBitmap(frogIcon2);
        //Animal sounds are created here
//        final MediaPlayer barking = MediaPlayer.create(this, R.raw.barking2);
//        final MediaPlayer meowing = MediaPlayer.create(this, R.raw.meowing);
//        final MediaPlayer ribbiting = MediaPlayer.create(this, R.raw.frog);


        // Create and start the worker thread.
        workerThread = new MyWorkerThread();
        workerThread.start();

//        workerThread2= new MyWorkerThread2();
//        workerThread2.start();

        // Handle message from main thread message queue.
        // Animal information connection is created when msg.what is set
        // Animal sounds are started here when the message is received from the button click
//        mainThreadHandler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                Log.i("MAIN_THREAD", "Receive message from child thread.");
//                //taskStatusTextView = findViewById(R.id.somethingText);
//                if (msg.what == MAIN_THREAD_TASK_1) {
//                    barking.start();
//                    //taskStatusTextView.setText(dogText);
//                    sendActivity(dogTitle,dogTextLeft,dogTextCenter,dogTextRight);
//                } else if (msg.what == MAIN_THREAD_TASK_2) {
//                    // If task two button is clicked.
//                    meowing.start();
//                    //taskStatusTextView.setText(catText);
//                    sendActivity2(catTitle,catText);
//                } else if (msg.what == MAIN_THREAD_TASK_3) {
//                    // If quit child thread looper button is clicked.
//                    ribbiting.start();
//                    sendActivity3(frogTitle,frogText);
//                    //taskStatusTextView.setText(frogText);
//                }
//            }
//        };


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
    public void sendActivity(String dogTitle,String dogTextLeft,String dogTextCenter,String dogTextRight) {
        Intent intent = new Intent(this, DogsActivity.class);
        //Text editText =findViewById(R.id.somethingText);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, dogTitle);
        intent.putExtra(EXTRA_MESSAGE_TWO, dogTextLeft);
        intent.putExtra(EXTRA_MESSAGE_THREE,dogTextCenter);
        intent.putExtra(EXTRA_MESSAGE_FOUR,dogTextRight);
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
            dogTextLeft = formattedDoc.returnString();

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
                    Log.i("AFtermsg.what", ""+msg.what);
                    if (msg.what == MAIN_THREAD_TASK_1) {
                        barking.start();
                        //taskStatusTextView.setText(dogText);
                        sendActivity(dogTitle,dogTextLeft,dogTextCenter,dogTextRight);
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
//    private class MyWorkerThread2 extends Thread {
//        //Log.i("MyWorkerThread","MyWorkerThread created")
//        // This is worker thread handler.
//        public Handler workerThreadHandler2;
//
//        @Override
//        public void run() {
//            // Prepare MyWorkerThread which is a child of Thread Looper object.
//            //Prepares thread to add tasks in a loop
//
//            Looper.prepare();
//
//            workerThreadHandler2 = new Handler(Looper.myLooper()) {
//                @Override
//                public void handleMessage(Message msg) {
//                    // When child thread handler gets message from child thread message queue.
//                    Log.i("CHILD_THREAD", "Receive message from main thread.");
//                    Message message = new Message();
//                    message.what = msg.what;
//                    Log.i("AFtermsg.what", ""+msg.what);
//
//                    // Send the message back to main thread message queue use main thread message Handler.
//                    mainThreadHandler.sendMessage(message);
//                }
//            };
//            // Loop the child thread message queue.
//            Looper.loop();
//
//            // The code after Looper.loop() will not be executed until you call workerThreadHandler.getLooper().quit()
//            Log.i("CHILD_THREAD", "This log is printed after Looper.loop() method. Only when this thread loop quit can this log be printed.");
//            // Send a message to main thread.
//            Message msg = new Message();
//            msg.what = CHILD_THREAD_QUIT_LOOPER;
//            mainThreadHandler.sendMessage(msg);
//        }
//    }


    private class FormatDocument {
        private String returnString() {
            try {
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
                Element e7 = e5.child(2);
                Element e8 = e5.child(3);


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
                dogTextLeft=leftDogs;

                int limitCenterLoop=dogsLeft+dogsCenter;

                StringBuilder dogsCenterBuild=new StringBuilder();
                String centerDogs="";
                for(int p=dogsLeft+1;p>dogsLeft&& p<=limitCenterLoop;p++){
                    dogsCenterBuild.append(dogArray[p]);
                    dogsCenterBuild.append((System.getProperty("line.separator")));
                    Log.i("Times ifn centerD loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                centerDogs=dogsCenterBuild.toString();
                Log.i("DogsCenter Column=  ", ""+centerDogs);
                dogTextCenter=centerDogs;

                //dogTextCenter="something";

                int limitRightLoop=dogsCenter+dogsRight;

                //Creates right side list for Dogs
                StringBuilder dogsRightBuild=new StringBuilder();
                String rightDogs="";
                for(int p=limitCenterLoop+1;p<=numberOfDogs;p++){
                    dogsRightBuild.append(dogArray[p]);
                    dogsRightBuild.append((System.getProperty("line.separator")));
                    Log.i("Times ifn right loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                rightDogs=dogsRightBuild.toString();
                Log.i("DogsRight Column=  ", ""+rightDogs);
                dogTextRight=rightDogs;


                String dogsOnLeft=dogsLeft+"";
                String dogsOnRight=dogsRight+"";





                //Element[] elementArr = arrayElements.toArray(new Element[]{});
                Log.i("numberOfDogs =",""+numberOfDogs);


                //Element e6=e5.select("p.Dogs").first();
                //Element e5=doc.tagName("<p>Dogs</p>");
//                Element e6=doc.getElementById("Dogs");
//                Element e7=e6.tagName("<li>");
                //Elements e7=elements.select("p:contains(Dogs)").tagName("<li>");

                Element e2 = doc.select("p:contains(Dogs)").get(0);
                dogTitle=e2.text();
                //String idString=e2.id();
                //Log.i("idSTring= ",""+idString);
                //String dogHeader=e2.toString();
//                String dogHeader2=dogHeader;
//                //Still hast HTML tags
//                dogTitle = Jsoup.parse(dogHeader).text();
//                Log.i("dogTitle:",""+dogTitle);
//                //added here for test
                //Element befParStr=e2.nextElementSibling();
                //Elements befParStr=Element.after(dogHeader2);
                //String parString = e2.nextElementSibling().toString();
                //String dogTextHtml = dogTitle + parString;

                //Document doc2 = Jsoup.parse(dogTextHtml);
                //Document doc2 = Jsoup.parse(parString);
//                doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
//                doc2.select("p").append("\\n");
//                doc2.select("li").prepend("\\n\\n");
//                String s = doc2.html().replaceAll("\\\\n", "\n");
//                dogText = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));

                //combinedString=e2+parElement;
                catWords=0;
                String cattt;
//                for(Element element : elements.tagName("<ol>")) {
//                    catWords += 1;
//                    Log.i("numberOfWords=    ", "" + catWords);
//                    cattt=element.toString();
//                    Log.i("Word=   ",""+cattt);
//
//                }
                //Log.i("E5= ",""+e5);
               // Log.i("E6= ",""+e6);
                //Log.i("E7= ",""+e7);
               // Log.i("E8= ",""+e8);
                Log.i("E9   Should Dogs",""+e9);
                Element cattEle=null;
//                for(Element element : e6){
//                    catWords += 1;
//                    Log.i("numberOfWords=    ", "" + catWords);
//                    cattEle=element;
//                    cattt=element.toString();
//                    Log.i("Word=   ",""+cattt);
//                    if(cattt.equals("Dogs") ){
//                        Log.i("DOGS FOUND", "0000000000000000000000000000000000000000000");
//                    }
//                }
                //This reads the amount of items there are in the list
                //This will be used to create the layout
//                int n=-1;
//                for (Node child : cattEle.childNodes()) {
//                    if (child instanceof TextNode) {
//                        Log.i("NOde of e6 ",""+child);
//                        n+=1;
//                        Log.i("Number of childn nodes", ""+n);
//                        //System.out.println(((TextNode) child).text());
//                    }
//                }


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




                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");

            } catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            return dogTextLeft;
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
}
