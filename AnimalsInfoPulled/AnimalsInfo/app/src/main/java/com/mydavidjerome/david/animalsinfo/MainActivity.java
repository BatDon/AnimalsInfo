package com.mydavidjerome.david.animalsinfo;

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
import android.widget.TableLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int MAIN_THREAD_TASK_1 = 1;
    private int MAIN_THREAD_TASK_2 = 2;
    private int MAIN_THREAD_TASK_3 = 3;
    private int MAIN_THREAD_TASK_4 = 4;
    private int CHILD_THREAD_QUIT_LOOPER = 5;

    private int MAIN_THREAD_TASK_10 = 10;
    private int MAIN_THREAD_TASK_11 = 11;
    private int MAIN_THREAD_TASK_12 = 12;
    private int MAIN_THREAD_TASK_13 = 13;

    Intent intent1;
    Intent intent2;
    Intent intent3;

    Integer x;
    String dogHeight,catHeight,frogHeight;
    String dogWidth,catWidth,frogWidth;


    Document doc;

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
    String[]catFormatted=new String[10];
    String[]catTable=new String[10];


    public String frogTitle;
    public String frogText;
    public Integer frogWords;
    String[]frogFormatted=new String[10];
    String[]frogTable=new String[10];

    //Website where information will be pulled from
    public static final String EXTRA_MESSAGE = "@string/myPackage";
    public static final String EXTRA_MESSAGE_TWO = "@string/myPackage2";
    public static final String EXTRA_MESSAGE_THREE = "@string/myPackage3";
    public static final String EXTRA_MESSAGE_FOUR = "@string/myPackage4";
    public static final String EXTRA_MESSAGE_FIVE = "@string/myPackage5";

    public static final String EXTRA_MESSAGE_TEN = "@string/myPackage10";
    public static final String EXTRA_MESSAGE_ELEVEN = "@string/myPackage11";
    public static final String EXTRA_MESSAGE_TWELVE = "@string/myPackage12";
    public static final String EXTRA_MESSAGE_THIRTEEN = "@string/myPackage13";
    public static final String EXTRA_MESSAGE_FOURTEEN = "@string/myPackage14";

    public static final String EXTRA_MESSAGE_SIXTEEN = "@string/myPackage16";
    public static final String EXTRA_MESSAGE_SEVENTEEN = "@string/myPackage17";
    public static final String EXTRA_MESSAGE_EIGHTEEN = "@string/myPackage18";
    public static final String EXTRA_MESSAGE_NINETEEN = "@string/myPackage19";
    public static final String EXTRA_MESSAGE_TWENTY = "@string/myPackage20";


    //THIS VARIABLE IS FOR THE DOG TABLE
    //public static final String EXTRA_MESSAGE_FIVE = "@string/myPackage5";
    TableLayout mTableLayout;
    TableLayout tableLayout2;
    TableLayout[] tableArray;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainx720);
        //Log.i("onCreateMethod","0000000000000000000000");
        //String url2 = getResources().getString(R.string.url2);



        //mTableLayout = (TableLayout) findViewById(R.id.dogsTable);
        //mTableLayout.setStretchAllColumns(true);

        //DogTableCreator dtc=new DogTableCreator();
        //dtc.dataIntoTable();

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



        //dataIntoTable();

        // Create and start the worker thread.
        workerThread = new MyWorkerThread();
        workerThread.start();
        //mTableLayout = (TableLayout) findViewById(R.id.dogsTable);

        workerThread2=new MyWorkerThread2();
        workerThread2.start();

        // Get run task buttons.
        ImageButton runTaskOneButton = findViewById(R.id.dogButton);

        ImageButton runTaskTwoButton = findViewById(R.id.catButton);

        ImageButton runTaskThreeButton = findViewById(R.id.frogButton);

//        try{
//            for(int i=5 ; i>0 ; i--){
//                //System.out.println("main thread: " + i);
//                Thread.sleep(2000);
//
//            }
//        }
//        catch(InterruptedException e){
//            Log.i("sleep","main thread interrupted");
//        }

//        try{
//            Thread.sleep(3000);
//        }
//        catch (IOException e) {
//            final StringBuilder builder = new StringBuilder();
//            builder.append("Error : ").append(e.getMessage()).append("\n");
//            Log.i("SLEEP ER", "exception main thread sleeping");
//        }





        // Set on click listener to each button.
        runTaskOneButton.setOnClickListener(new View.OnClickListener()

                //When button is clicked the msg.what is given a value
        {
            @Override
            public void onClick(View view) {
                // When click this button, create a message object
                Message msg = new Message();
                //Log.i("runTaskOneButton","AAAAAAAAAAAAAAAAAAAA");
                msg.what = MAIN_THREAD_TASK_1;
                // Use worker thread message Handler to put message into worker thread message queue.
                workerThread.workerThreadHandler.sendMessage(msg);
                //workerThread2.workerThreadHandler2.sendMessage(msg);
            }
        });

        // Please see comments for runTaskOneButton.
        runTaskTwoButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                Message msg = new Message();
                msg.what = MAIN_THREAD_TASK_2;
                //Log.i("runTaskTwoButton","BBBBBBBBBBBBBBBBBBBBB");
                workerThread.workerThreadHandler.sendMessage(msg);
            }
        });
        runTaskThreeButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = MAIN_THREAD_TASK_3;
                //Log.i("runTaskThreeButton","CCCCCCCCCCCCCCCCCCCC");
                workerThread.workerThreadHandler.sendMessage(msg);
            }
        });



    }


    //This sends the information to the Activity to be viewed
    //public void sendActivity(String dogTitle,String dogTextLeft,String dogTextCenter,String dogTextRight) {
//    public void sendActivity(String dogTitle,String dogTextLeft,String dogTextCenter,String dogTextRight){
    public void sendActivity1(Intent intent1){
//        Intent intent = new Intent(this, DogsActivity.class);
//        //Text editText =findViewById(R.id.somethingText);
//        //String message = editText.getText().toString();
//        Log.i("sendActivity","222222222222222222222222");
//        intent.putExtra(EXTRA_MESSAGE, dogTitle);
//        intent.putExtra(EXTRA_MESSAGE_TWO, dogTextLeft);
//        intent.putExtra(EXTRA_MESSAGE_THREE,dogTextCenter);
//        intent.putExtra(EXTRA_MESSAGE_FOUR,dogTextRight);
        startActivity(intent1);
    }

    public void sendActivity2(Intent intent2) {

        startActivity(intent2);
    }
//    public void sendActivity2(String catTitle, String catText) {
//        Intent intent = new Intent(this, CatsActivity.class);
//        //Text editText =findViewById(R.id.somethingText);
//        //String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, catTitle);
//        intent.putExtra(EXTRA_MESSAGE_TWO, catText);
//        startActivity(intent);
 //   }

    public void sendActivity3(Intent intent3) {

        startActivity(intent3);
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
            //Log.i("run","3333333333333333333333");

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
            ConnectionClass connection=new ConnectionClass();
            connection.getDoc();

            FormatDocument formattedDoc = new FormatDocument();
//            dogTextLeft = formattedDoc.returnString();
            //Log.i("runTaskOneButton","4444444444444444444444444");
            dogFormatted = formattedDoc.returnStringArray();


            FormatDocument2 formattedDoc2 = new FormatDocument2();
            catFormatted = formattedDoc2.returnCatStringArray();

            FormatDocument3 formattedDoc3 = new FormatDocument3();
            frogFormatted = formattedDoc3.returnFrogStringArray();
            //Log.i("Wthread","at end of run");

            // Create child thread Handler. Connects Looper to current(MyWorkerThread)thread
            workerThreadHandler = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    //Log.i("handleMessage","55555555555555555555555");
                    // When child thread handler get message from child thread message queue.
                    //Log.i("CHILD_THREAD", "Receive message from main thread.");
                    Message message = new Message();
                    message.what = msg.what;
                    //Log.i("AFtermsg.what", ""+msg.what);
                    if (msg.what == MAIN_THREAD_TASK_1) {
                        //Log.i("if msg.what==Main","6666666666666666666666666");
                        barking.start();
                        //taskStatusTextView.setText(dogText);
                        sendActivity1(intent1);
                        //sendActivity(dogTitle,dogTextLeft,dogTextCenter,dogTextRight);
                    } else if (msg.what == MAIN_THREAD_TASK_2) {
                        // If task two button is clicked.
                        meowing.start();
                        //taskStatusTextView.setText(catText);
                        sendActivity2(intent2);
//                        else if (msg.what == MAIN_THREAD_TASK_2) {
//                            // If task two button is clicked.
//                            meowing.start();
//                            //taskStatusTextView.setText(catText);
//                            sendActivity2(catTitle,catText);
//                            ImageView v = (ImageView) findViewById(R.id.catButton);
//                            String x = Integer.toString(v.getWidth());
//                            String y = Integer.toString(v.getHeight());
//                            Log.i("cat x and y","x= "+x+" y= "+y);
                    } else if (msg.what == MAIN_THREAD_TASK_3) {
                        // If quit child thread looper button is clicked.
                        ribbiting.start();
                        sendActivity3(intent3);
//                        sendActivity3(frogTitle,frogText);
//                        ImageView v = (ImageView) findViewById(R.id.frogButton);
//                        String x = Integer.toString(v.getWidth());
//                        String y = Integer.toString(v.getHeight());
//                        Log.i("frog x and y","x= "+x+" y= "+y);
                        //taskStatusTextView.setText(frogText);
                    }

                    // Send the message back to main thread message queue use main thread message Handler.
//                    mainThreadHandler.sendMessage(message);
                }

            };
            // Loop the child thread message queue.

            Looper.loop();

            // The code after Looper.loop() will not be executed until you call workerThreadHandler.getLooper().quit()
//            Log.i("CHILD_THREAD", "This log is printed after Looper.loop() method. Only when this thread loop quit can this log be printed.");
//            // Send a message to main thread.
//            Message msg = new Message();
//            msg.what = CHILD_THREAD_QUIT_LOOPER;
//            mainThreadHandler.sendMessage(msg);
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
            //Log.i("handleMessage","###################");
            Looper.prepare();
            String url2 = getResources().getString(R.string.url2);
            new DogsData().getDogsStatsArray(url2);
            new CatsData().getCatsStatsArray(url2);
            new FrogsData().getFrogsStatsArray(url2);











            //Gets database from website
            //CREATE DYNAMIC TABLE HERE
            //Log.i("beforeDogCr","eator &&&&&&&&&&&&");
            //Log.i("after","dogcreator    ");
            //FormatDatabase formatDatabase=new FormatDatabase();
            final TableLayout dogLayout;
            TableLayout[] dogLayoutArray;
            //DogTableCreator dtc =new DogTableCreator();
            //dogLayout=dtc.dataIntoTable();
            //dogLayoutArray=dtc.dataIntoTable();
            //final TableLayout tabDog=dogLayoutArray[0];
            //final TableLayout tabDogView=dogLayoutArray[1];
            //formatDatabase.returnStringArray2();
            //dogTable = formatDatabase.returnStringArray2();


            // Create child thread Handler. Connects Looper to current(MyWorkerThread)thread
            workerThreadHandler2 = new Handler(Looper.getMainLooper()) {
            //workerThreadHandler2 = new Handler(Looper.myLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==MAIN_THREAD_TASK_1) {
                        final TableLayout dogLayout;
                        TableLayout[] dogLayoutArray;
                        //DogTableCreator dtc = new DogTableCreator();
                        //dogLayout=dtc.dataIntoTable();
                        //dogLayoutArray = dtc.dataIntoTable();
                        //final TableLayout tabDog = dogLayoutArray[0];
                        //final TableLayout tabDogView = dogLayoutArray[1];
//            dogTable = formatDatabase.returnStringArray2();
                        //tabDogView.addView(tabDog);
                        //tableLayout2.addView(mTableLayout);
                        //Log.i("handleMessage", "$$$$$$$$$$$$$$$$$$$");
                        // When child thread handler get message from child thread message queue.
                        //Log.i("CHILD_THREAD", "Receive message from main thread.");
                        Message message = new Message();
                        message.what = msg.what;
                        //Log.i("AFtermsg.what", "" + msg.what);
                    }

                    // Send the message back to main thread message queue use main thread message Handler.
//                    mainThreadHandler.sendMessage(message);
                }

            };
            // Loop the child thread message queue.

            Looper.loop();

            // The code after Looper.loop() will not be executed until you call workerThreadHandler.getLooper().quit()
            //Log.i("CHILD_THREAD", "This log is printed after Looper.loop() method. Only when this thread loop quit can this log be printed.");
            // Send a message to main thread.
            //Message msg = new Message();
            //msg.what = CHILD_THREAD_QUIT_LOOPER;
            //mainThreadHandler.sendMessage(msg);
        }
    }

//    //figure out rows for 30 in array
//    TableLayout exTableLayout;
//    TextView[][] listIntoTable=new TextView[30][4];
//    TextView[] rowArray;
//
    //TableLayout mTableLayout=new TableLayout(MainActivity.this);
    //TableLayout mTableLayout;

//    new LongOperation().execute();
//    //LongOperation lo=new LongOperation();
//    //lo.execute();
//
//        private class LongOperation extends AsyncTask<void, void, TableLayout> {
//
//            @Override
//            protected TableLayout doInBackground() {
//                //protected String doInBackground(String... params) {
//                TableLayout tableForDogs=null;
//
//                    String firstparameter;
//                    try {
//                        DogTableCreator dC = new DogTableCreator();
//                        tableForDogs=dC.dataIntoTable();
//                    } catch (IllegalArgumentException e) {
//                        Thread.interrupted();
//                    }
//
//                return tableForDogs;
//            }
//
//            @Override
//            //protected void onPostExecute(String result) {
//            protected void onPostExecute(TableLayout tableForDogs) {
//                //receives argument from doInBackground
//                String lastParamter;
//                setContentView(R.layout.activity_dogs);
//                //TableLayout tabDog = tableForDogs[0];
//                //TableLayout tabDogView = tableArray2[1];
//                //tabDogView.addView(tabDog);
//                //intent1.putExtra(EXTRA_MESSAGE_FIVE, tableForDogs);
//                //TextView txt = (TextView) findViewById(R.id.dogsTable);
//                //txt.setText("Executed"); // txt.setText(result);
//                // might want to change "executed" for the returned string passed
//                // into onPostExecute() but that is upto you
//            }
//
//            @Override
//            protected void onPreExecute() {
//            }
//
//            @Override
//            protected void onProgressUpdate() {
//                String[] middle;
//
//                //protected void onProgressUpdate(String ... values) {
//
//            }
////            @Override
////            protected void doInBackground(){
////                //receives argument from first parameter
////                //do some work then
////                //pass parameter to onPostExecute() method
////                return tableArray;
////            }
//        }



    private class ConnectionClass {

        private Document getDoc() {
            try {


                String url = getResources().getString(R.string.url);
                //String url = "@string/url";
                doc = Jsoup.connect(url).get();

            } catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                //Log.i("HTML ERROR", "exception reading HTML");
            }
            return doc;

        }

    }













    private class FormatDocument {
        private String[] dogFormattedArray=new String[3];
        private String[] returnStringArray() {
            try {
                //Log.i("runTaskOneButton","777777777777777777777777");
//                String url = getResources().getString(R.string.url);
//                //String url = "@string/url";
//                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.body().select("*");
//                Elements e5=elements.select("p:contains(Dogs)");
                Element masthead = doc.select("div.masthead").first();


                //This selects the class that all the animals are in
                Element e5 = doc.select("div.entry-content").first();

                Element e6 = e5.child(1);
                //Log.i("dog e6="," "+e6);
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

                    //Log.i("eachDogHere: ", "" + (dogArray[numberOfDogs]));
                }
                String halfWords = dogArray[1];

                //Log.i("Dogs/2=", "" + (numberOfDogs / 2));

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
                    //Log.i("Times ifn leftDogs loop","lets see "+i);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                //String dogsLeftColumn=dogsLeftBuild.toString();
                leftDogs=dogsLeftBuild.toString();
                //Log.i("DogsLEft Column=  ", ""+leftDogs);
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
                //Log.i("numberOfDogs =",""+numberOfDogs);




                Element e2 = doc.select("p:contains(Dogs)").get(0);
                dogTitle=e2.text();

                catWords=0;
                String cattt;

                //Log.i("E9   Should Dogs",""+e9);
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
                //Log.i("sendActivity","222222222222222222222222");
                intent1.putExtra(EXTRA_MESSAGE, dogTitle);
                intent1.putExtra(EXTRA_MESSAGE_TWO, dogTextLeft);
                intent1.putExtra(EXTRA_MESSAGE_THREE,dogTextCenter);
                intent1.putExtra(EXTRA_MESSAGE_FOUR,dogTextRight);
                intent1.putExtra(EXTRA_MESSAGE_FIVE,dogWidth);



                //This intent message is for the table

               // intent1.putExtra(EXTRA_MESSAGE_FIVE,listIntoTable);


               // intent1.putExtra(EXTRA_MESSAGE_SIX,exTableLayout);


                //Log.i("in Element", "paragraph of dogs");


                //Log.i("JSoup", "Connected successfully!");

            } catch(Throwable e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                //Log.i("HTML ERROR", "exception reading HTML");
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
                //Log.i("FormatDatabase"," "+elements);
                Element e5 = doc.select("body").first();
                //Elements nes =doc.select("body ~ br");
                Elements nes =doc.select("body");

//                Element e6 = e5.child(1);
//                Log.i("dog e6="," "+e6);
//                Element e6 = e5.child(1);
//                Log.i("dog e6="," "+e6);
//                Elements e9 = e6.children();
//                Log.i("FD e6", ""+e9);
                Elements e9 = e5.children();
                //Log.i("dog e9="," "+e9);
                //Elements e9 = e6.children();
                //Log.i("FD e6", ""+e9);
                int numberOfDogs2 = 0;
                String[] dogArrayTB = new String[100];
                //ArrayList<Element> arrayElements = new ArrayList<>();
                for (Element e : nes) {
                    numberOfDogs2 += 1;
                    dogArrayTB[numberOfDogs2] = (e.text());

                    //Log.i("eachDogHereTB: ", "" + (dogArrayTB[numberOfDogs2]));
                }


                //Log.i("in Element", "paragraph of dogs");


                //Log.i("JSoup", "Connected successfully!");


            }



            catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                //Log.i("HTML ERROR", "exception reading HTML");
            }
            //return dogTextLeft;
            return dogFormattedArray2;
        }
    }



    private class FormatDocument2 {
        private String[] catFormattedArray = new String[3];

        private String[] returnCatStringArray() {
            try {
                //Log.i("runTaskOneButton", "777777777777777777777777");
//                String url = getResources().getString(R.string.url);
//                //String url = "@string/url";
//                Document doc = Jsoup.connect(url).get();
                Element e5 = doc.select("div.entry-content").first();
                Elements elements = doc.body().select("*");
//                Elements e5=elements.select("p:contains(Dogs)");
                Element masthead = doc.select("div.masthead").first();
//                Element e5 = doc.select("div.entry-content").first();
//                  <p>Dogs</p>
                //e6 has all the paragraphs
                //Element e6 = e5.child(1);
                Elements e11 = e5.getElementsByTag("ol");
                //Elements e7 = e5.getElementsByTag("p");
                Element epar1 = null;
                Element epar2 = null;
                //Element e12=e11.getAllElements​();

                for (Element epar : e11) {
                    epar1 = epar;
                    break;

                    //Element epar3=epar;
                    //Log.i("epars","epar1 "+epar+" epar2 "+epar2+" epar3 "+epar3);
                }
                int ep = 0;
                for (Element epar : e11) {
                    epar2 = epar;
                    if (ep == 1) {
                        break;
                    } else {
                        ep++;
                        continue;
                    }
                }
                Elements e10 = epar1.getAllElements();
                Element e12 = epar2.child(1);
                //Elements e9=epar2.getAllElements();
                //Elements e9=e12.getAllElements();
                Elements e9 = epar2.getElementsByTag("li");
                //Elements e9=epar2.getAllElements();
                //Elements e9=epar2.getAllElements();
                //Element e9=epar2.firstElementSibling();
                //Log.i("Cate11= ", " " + e11);
                //Log.i("Cate9= ", " " + e9);
                //Log.i("Cate10= ", " " + e10);

                //Log.i("epars", "epar1 " + epar1 + " epar2 " + epar2);

                //Elements e9=e6.getElementsByIndexEquals(2);
//                for (Element e:e7) {
//                    Element child = e6.get( i );
//                    if (child instanceof TextNode) {
//                        if(brFound){
//                            working += ((TextNode) child).text();
//                        }
//                    }
//                    if (child instanceof Element) {
//                        Element childElement = (Element)child;
//                        if(brFound){
//                            working += childElement.text();
//                        }
//                        if(childElement.tagName().equals( "br" )){
//                            brFound = true;
//                        }
//                    }


//                Elements e9 = e6.children();

                //This selects the class that all the animals are in
                //Element e5 = doc.select("div.entry-content").first();
//                Element e4 = doc.select("paragraph").first();
//                Element e5 = doc.select("paragraph").first();
                //Elements e6=e5.getElementsByAttribute("p");
                //Elements e6=e5.children();
                //Elements e7=e6.eq(2);
//                Elements e6=e5.children();
//                for (Element par : e6) {
//                    Element e4=par;
//
//                    System.out.println("Href: " + link.attr("href"));
//                    System.out.println("Text: " + link.text());
//                }
//                Elements e7=e6.children();
//
//                Element e6 = e5.child(1);
//                Elements e9 = e6.children();
                //Element e7 = e5.child(2);
                //Element e8 = e5.child(3);


//                MAKE AN ARRAY HERE
                int numberOfCats = 0;
                String[] catArray = new String[100];
                //String[] catArray = null;
                ArrayList<Element> arrayElements = new ArrayList<>();

                for (Element e : e9) {
//                    if (numberOfCats==0){
//                    e.text();
//                    numberOfCats += 1;}
                    //if (numberOfCats >= 1) {

                    catArray[numberOfCats] = (e.text());

                    //Log.i("eachCatHere: ", "" + (catArray[numberOfCats]));
                    numberOfCats += 1;
                    // }
                }

                String halfWords = catArray[1];

                //Log.i("Cats/2=", "" + (numberOfCats / 2));

                //If an odd number of dogs add one more to left column
                int remCat = numberOfCats % 3;
                int catsInList = numberOfCats / 3;

                int catsRem = 0;
                int catsRem2 = 0;

                int catsLeft = 0;
                int catsCenter = 0;
                int catsRight = 0;
//                if (remCat >= 0 && remCat<=2){
////                if (remCat > 0 && remCat<2){
//
//                    catsRem+=1;
//                    catsLeft+=1;
//                }
//                else if (remCat>0){
//                    catsRem2=2;
//                    catsLeft+=1;
//                    catsCenter+=1;
//                }
                if (remCat == 2) {
//                if (remCat > 0 && remCat<2){

                    //catsRem+=1;
                    catsCenter += 1;
                    catsLeft += 1;
                } else if (remCat == 1) {
                    //catsRem2=2;
                    catsLeft += 1;

                }


                catsLeft = catsLeft + catsInList;
                catsCenter = catsCenter + catsInList;
                catsRight = catsInList;


                //Builds list for left side of Dogs
                StringBuilder catsLeftBuild = new StringBuilder();
                String leftCats = "";
                for (int i = 0; i < catsLeft; i++) {
                    catsLeftBuild.append(catArray[i]);
                    catsLeftBuild.append(System.getProperty("line.separator"));
                    //Log.i("Times ifn leftCats loop", "lets see " + i);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                //String dogsLeftColumn=dogsLeftBuild.toString();
                leftCats = catsLeftBuild.toString();
                //Log.i("CatsLEft Column=  ", "" + leftCats);
                String catTextLeft = leftCats;

                int limitCenterLoop = catsLeft + catsCenter;

                StringBuilder catsCenterBuild = new StringBuilder();
                String centerCats = "";
                for (int p = catsLeft + 1; p >= catsLeft && p <= limitCenterLoop; p++) {
                    catsCenterBuild.append(catArray[p]);
                    catsCenterBuild.append((System.getProperty("line.separator")));
                    //Log.i("Times ifn centerD loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                centerCats = catsCenterBuild.toString();
                //Log.i("DogsCenter Column=  ", ""+centerDogs);
                String catTextCenter = centerCats;
                //Log.i("catsCenter", " " + catTextCenter);

                //dogTextCenter="something";

                int limitRightLoop = catsCenter + catsRight;

                //Creates right side list for Dogs
                StringBuilder catsRightBuild = new StringBuilder();
                String rightCats = "";
                for (int p = limitCenterLoop; p <= numberOfCats - 1; p++) {
                    catsRightBuild.append(catArray[p]);
                    catsRightBuild.append((System.getProperty("line.separator")));
                    //Log.i("Times ifn right loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                rightCats = catsRightBuild.toString();
                //Log.i("DogsRight Column=  ", ""+rightDogs);
                String catTextRight = rightCats;
                //Log.i("catsRight", " " + catTextRight);


                String catsOnLeft = catsLeft + "";
                String catsOnRight = catsRight + "";


                //Element[] elementArr = arrayElements.toArray(new Element[]{});
                //Log.i("numberOfCats =", "" + numberOfCats);


                Element e2 = doc.select("p:contains(Cats)").get(0);
                catTitle = e2.text();

                catWords = 0;
                String cattt;

                //Log.i("E9   Should Cats", "" + e9);
                Element cattEle = null;


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


                catFormattedArray[0] = catTextLeft;
                catFormattedArray[1] = catTextCenter;
                catFormattedArray[2] = catTextRight;

                intent2 = new Intent(MainActivity.this, CatsActivity.class);
                //Text editText =findViewById(R.id.somethingText);
                //String message = editText.getText().toString();
                //Log.i("sendActivity", "222222222222222222222222");
                intent2.putExtra(EXTRA_MESSAGE_TEN, catTitle);
                intent2.putExtra(EXTRA_MESSAGE_ELEVEN, catTextLeft);
                intent2.putExtra(EXTRA_MESSAGE_TWELVE, catTextCenter);
                intent2.putExtra(EXTRA_MESSAGE_THIRTEEN, catTextRight);
                intent2.putExtra(EXTRA_MESSAGE_FOURTEEN,catWidth);

                //This intent message is for the table

                // intent1.putExtra(EXTRA_MESSAGE_FIVE,listIntoTable);


                // intent1.putExtra(EXTRA_MESSAGE_SIX,exTableLayout);


                //Log.i("in Element", "paragraph of dogs");


                //Log.i("JSoup", "Connected successfully!");

            } catch (Throwable e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                //log.i("HTML ERROR", "exception reading HTML");
//                catch (IOException e) {
//                final StringBuilder builder = new StringBuilder();
//                builder.append("Error : ").append(e.getMessage()).append("\n");
//                Log.i("HTML ERROR", "exception reading HTML");
//            }
                //return dogTextLeft;

            }
            return catFormattedArray;
        }
    }

    private class FormatDocument3 {
        private String[] frogFormattedArray = new String[3];
        private String[] returnFrogStringArray() {
            try {
                //log.i("runTaskOneButton", "777777777777777777777777");
//                String url = getResources().getString(R.string.url);
//                //String url = "@string/url";
//                Document doc = Jsoup.connect(url).get();
                Element e5 = doc.select("div.entry-content").first();
                Elements elements = doc.body().select("*");
//                Elements e5=elements.select("p:contains(Frogs)");
                Element masthead = doc.select("div.masthead").first();
//                Element e5 = doc.select("div.entry-content").first();
//                  <p>Frogs</p>
                //e6 has all the paragraphs
                //Element e6 = e5.child(1);
                Elements e11 = e5.getElementsByTag("ol");
                //Elements e7 = e5.getElementsByTag("p");
                Element epar1 = null;
                Element epar2 = null;
                Element epar3 = null;
                //Element e12=e11.getAllElements​();
                int ep = 0;
//                for (Element epar : e11) {
//                    epar1 = epar;
//                    break;
//
//                    //Element epar3=epar;
//                    //Log.i("epars","epar1 "+epar+" epar2 "+epar2+" epar3 "+epar3);
//                }
//
//                for (Element epar : e11) {
//                    epar2 = epar;
//                    if (ep == 1) {
//                        break;
//                    } else {
//                        ep++;
//                        continue;
//                    }
//                }
                for (Element epar : e11) {
                    epar3 = epar;
                    if (ep == 2) {
                        break;
                    } else {
                        ep++;
                        continue;
                    }
                }
                Elements e10 = epar3.getAllElements();
                Element e12 = epar3.child(1);
                //Elements e9=epar2.getAllElements();
                //Elements e9=e12.getAllElements();
                Elements e9 = epar3.getElementsByTag("li");
                //Elements e9=epar2.getAllElements();
                //Elements e9=epar2.getAllElements();
                //Element e9=epar2.firstElementSibling();
                //Log.i("Cate11= ", " " + e11);
                //Log.i("Cate9= ", " " + e9);
                //Log.i("Cate10= ", " " + e10);

                //Log.i("epars", "epar1 " + epar1 + " epar2 " + epar2 + "epar3 "+epar3);

                //Elements e9=e6.getElementsByIndexEquals(2);
//                for (Element e:e7) {
//                    Element child = e6.get( i );
//                    if (child instanceof TextNode) {
//                        if(brFound){
//                            working += ((TextNode) child).text();
//                        }
//                    }
//                    if (child instanceof Element) {
//                        Element childElement = (Element)child;
//                        if(brFound){
//                            working += childElement.text();
//                        }
//                        if(childElement.tagName().equals( "br" )){
//                            brFound = true;
//                        }
//                    }


//                Elements e9 = e6.children();

                //This selects the class that all the animals are in
                //Element e5 = doc.select("div.entry-content").first();
//                Element e4 = doc.select("paragraph").first();
//                Element e5 = doc.select("paragraph").first();
                //Elements e6=e5.getElementsByAttribute("p");
                //Elements e6=e5.children();
                //Elements e7=e6.eq(2);
//                Elements e6=e5.children();
//                for (Element par : e6) {
//                    Element e4=par;
//
//                    System.out.println("Href: " + link.attr("href"));
//                    System.out.println("Text: " + link.text());
//                }
//                Elements e7=e6.children();
//
//                Element e6 = e5.child(1);
//                Elements e9 = e6.children();
                //Element e7 = e5.child(2);
                //Element e8 = e5.child(3);


//                MAKE AN ARRAY HERE
                int numberOfFrogs = 0;
                String[] frogArray = new String[100];
                //String[] frogArray = null;
                ArrayList<Element> arrayElements = new ArrayList<>();

                for (Element e : e9) {
//                    if (numberOfCats==0){
//                    e.text();
//                    numberOfCats += 1;}
                    //if (numberOfCats >= 1) {

                    frogArray[numberOfFrogs] = (e.text());

                    //Log.i("eachFrogHere: ", "" + (frogArray[numberOfFrogs]));
                    numberOfFrogs += 1;
                    // }
                }

                String halfWords = frogArray[1];

                //Log.i("Frogs/2=", "" + (numberOfFrogs / 2));

                //If an odd number of dogs add one more to left column
                int remFrog = numberOfFrogs % 3;
                int frogsInList = numberOfFrogs / 3;

                int frogsRem = 0;
                int frogsRem2 = 0;

                int frogsLeft = 0;
                int frogsCenter = 0;
                int frogsRight = 0;
//                if (remCat >= 0 && remCat<=2){
////                if (remCat > 0 && remCat<2){
//
//                    catsRem+=1;
//                    catsLeft+=1;
//                }
//                else if (remCat>0){
//                    catsRem2=2;
//                    catsLeft+=1;
//                    catsCenter+=1;
//                }
                if (remFrog == 2) {
//                if (remFrog > 0 && remFrog<2){

                    //frogsRem+=1;
                    frogsCenter += 1;
                    frogsLeft += 1;
                } else if (remFrog == 1) {
                    //catsRem2=2;
                    frogsLeft += 1;

                }


                frogsLeft = frogsLeft + frogsInList;
                frogsCenter = frogsCenter + frogsInList;
                frogsRight = frogsInList;


                //Builds list for left side of Dogs
                StringBuilder frogsLeftBuild = new StringBuilder();
                String leftFrogs = "";
                for (int i = 0; i < frogsLeft; i++) {
                    frogsLeftBuild.append(frogArray[i]);
                    frogsLeftBuild.append(System.getProperty("line.separator"));
                    //Log.i("Times leftFrogs loop", "lets see " + i);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                //String dogsLeftColumn=dogsLeftBuild.toString();
                leftFrogs = frogsLeftBuild.toString();
                //Log.i("FrogsLEft Column=  ", "" + leftFrogs);
                String frogTextLeft = leftFrogs;

                int limitCenterLoop = frogsLeft + frogsCenter;

                StringBuilder frogsCenterBuild = new StringBuilder();
                String centerFrogs = "";
                for (int p = frogsLeft + 1; p >= frogsLeft && p <= limitCenterLoop; p++) {
                    frogsCenterBuild.append(frogArray[p]);
                    frogsCenterBuild.append((System.getProperty("line.separator")));
                    //Log.i("Times ifn centerD loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                centerFrogs = frogsCenterBuild.toString();
                //Log.i("DogsCenter Column=  ", ""+centerDogs);
                String frogTextCenter = centerFrogs;
                //Log.i("frogsCenter", " " + frogTextCenter);

                //frogTextCenter="something";

                int limitRightLoop = frogsCenter + frogsRight;

                //Creates right side list for Dogs
                StringBuilder frogsRightBuild = new StringBuilder();
                String rightFrogs = "";
                for (int p = limitCenterLoop; p <= numberOfFrogs - 1; p++) {
                    frogsRightBuild.append(frogArray[p]);
                    frogsRightBuild.append((System.getProperty("line.separator")));
                    //Log.i("Times ifn right loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                rightFrogs = frogsRightBuild.toString();
                //Log.i("DogsRight Column=  ", ""+rightDogs);
                String frogTextRight = rightFrogs;
                //Log.i("frogsRight", " " + frogTextRight);


                String frogOnLeft = frogsLeft + "";
                String frogsOnRight = frogsRight + "";


                //Element[] elementArr = arrayElements.toArray(new Element[]{});
                //Log.i("numberOfFrogs =", "" + numberOfFrogs);


                Element e2 = doc.select("p:contains(Frogs)").get(0);
                frogTitle = e2.text();

                frogWords = 0;
                String froggg;

                //Log.i("E9   Should Frogs", "" + e9);
                Element froggEle = null;


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


                frogFormattedArray[0] = frogTextLeft;
                frogFormattedArray[1] = frogTextCenter;
                frogFormattedArray[2] = frogTextRight;

                intent3 = new Intent(MainActivity.this, FrogsActivity.class);
                //Text editText =findViewById(R.id.somethingText);
                //String message = editText.getText().toString();
                //Log.i("sendActivity", "222222222222222222222222");
                intent3.putExtra(EXTRA_MESSAGE_SIXTEEN, frogTitle);
                intent3.putExtra(EXTRA_MESSAGE_SEVENTEEN, frogTextLeft);
                intent3.putExtra(EXTRA_MESSAGE_EIGHTEEN, frogTextCenter);
                intent3.putExtra(EXTRA_MESSAGE_NINETEEN, frogTextRight);
                intent3.putExtra(EXTRA_MESSAGE_TWENTY, frogWidth);

                //This intent message is for the table

                // intent1.putExtra(EXTRA_MESSAGE_FIVE,listIntoTable);


                // intent1.putExtra(EXTRA_MESSAGE_SIX,exTableLayout);


                //Log.i("in Element", "paragraph of dogs");


                //Log.i("JSoup", "Connected successfully!");

            } catch (Throwable e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                //Log.i("HTML ERROR", "exception reading HTML");
//                catch (IOException e) {
//                final StringBuilder builder = new StringBuilder();
//                builder.append("Error : ").append(e.getMessage()).append("\n");
//                Log.i("HTML ERROR", "exception reading HTML");
//            }
                //return dogTextLeft;

            }
            return frogFormattedArray;
        }
    }

    class MyGlobalListenerClass implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            View v = (View) findViewById(R.id.dogButton);
            x=v.getWidth();
            int dogWidInt=x;
            dogWidth=x+"";
            //Integer x = v.getWidth();
            Integer y = v.getHeight();
            int dogHiInt=y;
            dogHeight=""+y;
            //show ImageView width and height
            final ImageView dogButton = (ImageView) findViewById(R.id.dogButton);
            Bitmap dogIcon = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
            Bitmap dogIcon2 = Bitmap.createScaledBitmap(dogIcon, x, y, true);
            dogButton.setImageBitmap(dogIcon2);


            View vc=(View)findViewById(R.id.catButton);
            Integer x1 = vc.getWidth();
            int catWidInt=x1;
            catWidth=x1+"";
            Integer y1 = vc.getHeight();
            int catHiInt=y1;
            catHeight=y1+"";
            final ImageView catButton = (ImageView) findViewById(R.id.catButton);
            Bitmap catIcon = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
            Bitmap catIcon2 = Bitmap.createScaledBitmap(catIcon, x1, y1, true);
            catButton.setImageBitmap(catIcon2);

            View vf=(View)findViewById(R.id.frogButton);
            Integer x2 = vf.getWidth();
            int frogWidInt=x2;
            frogWidth=x2+"";
            Integer y2 = vf.getHeight();
            int frogHiInt=y2;
            frogHeight=y2+"";
            //Log.i("Forg image","Width= "+x2.toString()+" Height= "+y2.toString());
            final ImageView frogButton = (ImageView) findViewById(R.id.frogButton);
            Bitmap frogIcon = BitmapFactory.decodeResource(getResources(), R.drawable.frog);
            Bitmap frogIcon2 = Bitmap.createScaledBitmap(frogIcon, x2, y2, true);
            //Bitmap frogIcon2 = Bitmap.createScaledBitmap(frogIcon, x2, y2, true);
            frogButton.setImageBitmap(frogIcon2);

//            if(dogWidInt>dogHiInt){
//                dogWidth=dogHiInt+"";
//
//            }
//            else{
//                dogWidth=dogWidInt+"";
//            }
//            if(catWidInt>catHiInt){
//                catWidth=catHiInt+"";
//
//            }
//            else{
//                catWidth=catWidInt+"";
//            }
//            if(frogWidInt>frogHiInt){
//                frogWidth=frogHiInt+"";
//            }
//            else{
//                catWidth=catWidInt+"";
//            }
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
