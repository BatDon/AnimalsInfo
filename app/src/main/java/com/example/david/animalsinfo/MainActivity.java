package com.example.david.animalsinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.safety.Whitelist;
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

    //Website where information will be pulled from
    public static final String EXTRA_MESSAGE = "@string/myPackage";
    public static final String EXTRA_MESSAGE_TWO = "@string/myPackage2";
    public static final String EXTRA_MESSAGE_THREE = "@string/myPackage3";
    public static final String EXTRA_MESSAGE_FOUR = "@string/myPackage4";

    public static final String EXTRA_MESSAGE_TEN = "@string/myPackage10";
    public static final String EXTRA_MESSAGE_ELEVEN = "@string/myPackage11";
    public static final String EXTRA_MESSAGE_TWELVE = "@string/myPackage12";
    public static final String EXTRA_MESSAGE_THIRTEEN = "@string/myPackage13";

    //THIS VARIABLE IS FOR THE DOG TABLE
    public static final String EXTRA_MESSAGE_FIVE = "@string/myPackage5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainx720);
        Log.i("onCreateMethod","0000000000000000000000");

        //dataIntoTable();
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
            catFormatted = formattedDoc2.returnCatStringArray();

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
            //CREATE DYNAMIC TABLE HERE
            //DogActivity dataIntoTable2=new DogsActivity.dataIntoTable();
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

//    //figure out rows for 30 in array
//    TableLayout exTableLayout;
//    TextView[][] listIntoTable=new TextView[30][4];
//    TextView[] rowArray;
//
public void dataIntoTable(){
    TableLayout mTableLayout = (TableLayout) findViewById(R.id.dogsTable);
    TextView[][] listIntoTable=new TextView[30][4];
    TextView[] rowArray;
    //InvoiceData.java=DogsAddData.java
    //Invoices.java=DogsData.java

    //data=dogsAddData
    //invoices=dogsData
    Log.i("dataInto Table", "))))))))))))))))");

    //added this part
    //setContentView(R.layout.activity_dogs);

    //mTableLayout = (TableLayout) findViewById(R.id.dogsTable);

    //mTableLayout.setStretchAllColumns(true);
    //exTableLayout.setStretchAllColumns(true);



    int leftRowMargin=0;
    int topRowMargin=0;
    int rightRowMargin=0;
    int bottomRowMargin = 0;
    int textSize = 0, smallTextSize =0, mediumTextSize = 0;

    textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
    smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
    mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

    DogsData dogsData = new DogsData();
    DogsAddData[] dogsAddData = dogsData.getInfo();

    int rows = dogsAddData.length;
    TextView textSpacer = null;

    //mTableLayout.removeAllViews();
    //exTableLayout.removeAllViews();

    // -1 means heading row
    for(int i = -1; i < rows; i ++) {
        DogsAddData row = null;
        if (i > -1)
            row = dogsAddData[i];
        else {
            textSpacer = new TextView(this);
            textSpacer.setText("");

        }
        // data columns
        final TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        tv.setGravity(Gravity.LEFT);

        tv.setPadding(5, 15, 0, 15);
        if (i == -1) {
            tv.setText("Dog Breed");
            tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        } else {
            tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
            tv.setText(String.valueOf(row.dogName));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        final TextView tv2 = new TextView(this);
        if (i == -1) {
            tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        } else {
            tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        tv2.setGravity(Gravity.LEFT);

        tv2.setPadding(5, 15, 0, 15);
        if (i == -1) {
            tv2.setText("Dog Weight");
            tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
        }else {
            tv2.setBackgroundColor(Color.parseColor("#ffffff"));
            tv2.setTextColor(Color.parseColor("#000000"));
            tv2.setText(String.valueOf(row.dogWeight));
        }


//        final LinearLayout layCustomer = new LinearLayout(this);
//        layCustomer.setOrientation(LinearLayout.VERTICAL);
//        layCustomer.setPadding(0, 10, 0, 10);
//        layCustomer.setBackgroundColor(Color.parseColor("#f8f8f8"));

        final TextView tv3 = new TextView(this);
        if (i == -1) {
            tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            tv3.setPadding(5, 5, 0, 5);
            tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        } else {
            tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            tv3.setPadding(5, 0, 0, 5);
            tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        tv3.setGravity(Gravity.TOP);


        if (i == -1) {
            tv3.setText("Dog Height");
            tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
        } else {
            tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
            tv3.setTextColor(Color.parseColor("#000000"));
            tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            tv3.setText(row.dogHeight);
        }
        //layCustomer.addView(tv3);


//            if (i > -1) {
//                final TextView tv3b = new TextView(this);
//                tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
//                        TableRow.LayoutParams.WRAP_CONTENT));
//
//                tv3b.setGravity(Gravity.RIGHT);
//                tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//                tv3b.setPadding(5, 1, 0, 5);
//                tv3b.setTextColor(Color.parseColor("#aaaaaa"));
//                tv3b.setBackgroundColor(Color.parseColor("#f8f8f8"));
//                tv3b.setText(row.customerAddress);
//                layCustomer.addView(tv3b);
//            }

//            final LinearLayout layAmounts = new LinearLayout(this);
//            layAmounts.setOrientation(LinearLayout.VERTICAL);
//            layAmounts.setGravity(Gravity.RIGHT);
//            layAmounts.setPadding(0, 10, 0, 10);
//            layAmounts.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
//                    TableRow.LayoutParams.MATCH_PARENT));
//
//
//
        final TextView tv4 = new TextView(this);
        if (i == -1) {
            tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            tv4.setPadding(5, 5, 1, 5);
            //layAmounts.setBackgroundColor(Color.parseColor("#f7f7f7"));
        } else {
            tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv4.setPadding(5, 0, 1, 5);
            //layAmounts.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        tv4.setGravity(Gravity.RIGHT);

        if (i == -1) {
            tv4.setText("Dog Lifespan");
            tv4.setBackgroundColor(Color.parseColor("#f7f7f7"));
            tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
        } else {
            tv4.setBackgroundColor(Color.parseColor("#ffffff"));
            tv4.setTextColor(Color.parseColor("#000000"));
            tv4.setText(String.valueOf(row.dogLifespan));
            tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        //layAmounts.addView(tv4);


//            if (i > -1) {
//                final TextView tv4b = new TextView(this);
//                tv4b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
//                        TableRow.LayoutParams.WRAP_CONTENT));
//
//                tv4b.setGravity(Gravity.RIGHT);
//                tv4b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//                tv4b.setPadding(2, 2, 1, 5);
//                tv4b.setTextColor(Color.parseColor("#00afff"));
//                tv4b.setBackgroundColor(Color.parseColor("#ffffff"));
//
//                String due = "";
//                if (row.amountDue.compareTo(new BigDecimal(0.01)) == 1) {
//                    due = "Due:" + decimalFormat.format(row.amountDue);
//                    due = due.trim();
//                }
//                tv4b.setText(due);
//                layAmounts.addView(tv4b);
//            }




        // add table row

        final TableRow tr = new TableRow(this);
        tr.setId(i + 1);
        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
        tr.setPadding(0,0,0,0);
        tr.setLayoutParams(trParams);



        tr.addView(tv);
        tr.addView(tv2);
        tr.addView(tv3);
        tr.addView(tv4);

        //create array of arrays holding text views
        int dogsRow=4;


        TextView[] dogColumnArray = new TextView[dogsRow];
        //Log.i("IntV","intextview not showing");
        //for(int p = 0; p < dogsRow; p++) {
        dogColumnArray[0] = tv;
        dogColumnArray[1] = tv2;
        dogColumnArray[2] = tv3;
        dogColumnArray[3] = tv4;

        //int r=0;
        //
        //listIntoTable[][]={dogColumnArray};
        //for(int d = 0; d < rows; d++){
        //listIntoTable[r][]={tv,tv2,tv3,tv4};
        //r++;
        //t is for rows and j is for columns iterates through all columns first then the row
        int t=0;
        //for (t; t < listIntoTable.length; t++) {
        for (int j = 0; j < listIntoTable[t].length; j++) {
            listIntoTable[t][j]=dogColumnArray[j];
            //listIntoTable[t][j] = t + j;
            Log.i("2d Array",""+listIntoTable[t][j]);
        }
        //}
        t++;



        // Log.i("2d Array",""+listIntoTable[t][j]);
        //}



//            tr.addView(layCustomer);
//            tr.addView(layAmounts);

//            if (i > -1) {
//
//                tr.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        TableRow tr = (TableRow) v;
//                        //do whatever action is needed
//
//                    }
//                });
        //     }



        mTableLayout.addView(tr, trParams);
        //exTableLayout.addView(tr, trParams);

        //mTableLayout.setStretchAllColumns(true);
        //exTableLayout.setStretchAllColumns(true);

        if (i > -1) {

            // add separator row
            final TableRow trSep = new TableRow(this);
            TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

            trSep.setLayoutParams(trParamsSep);
            TextView tvSep = new TextView(this);
            TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT);
            tvSepLay.span = 4;
            tvSep.setLayoutParams(tvSepLay);
            tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
            tvSep.setHeight(1);

            trSep.addView(tvSep);
            mTableLayout.addView(trSep, trParamsSep);
            mTableLayout.setStretchAllColumns(true);

            //exTableLayout.addView(trSep, trParamsSep);
        }


    }



}
    //MyObjects Parcelable class

//import java.util.ArrayList;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//    public class MyObjects implements Parcelable {
//
//        private int age;
//        private String name;
//
//        private ArrayList<String> address;
//
//        public MyObjects(String name, int age, ArrayList<String> address) {
//            this.name = name;
//            this.age = age;
//            this.address = address;
//        }
//
//        public MyObjects(Parcel source) {
//            age = source.readInt();
//            name = source.readString();
//            address = source.createStringArrayList();
//        }
//
//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeInt(age);
//            dest.writeString(name);
//            dest.writeStringList(address);
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public ArrayList<String> getAddress() {
//            if (!(address == null))
//                return address;
//            else
//                return new ArrayList<String>();
//        }
//
//        public static final Creator<MyObjects> CREATOR = new Creator<MyObjects>() {
//            @Override
//            public MyObjects[] newArray(int size) {
//                return new MyObjects[size];
//            }
//
//            @Override
//            public MyObjects createFromParcel(Parcel source) {
//                return new MyObjects(source);
//            }
//        };
//
//    }








//    MyObjects mObjects = new MyObjects("name","age","Address array here");
//
//    //Passing MyOjects instance
//    Intent mIntent = new Intent(FromActivity.this, ToActivity.class);
//mIntent.putExtra("UniqueKey", mObjects);
//    startActivity(mIntent);
//
//
//    //Getting MyObjects instance
//    Intent mIntent = getIntent();
//    MyObjects workorder = (MyObjects) mIntent.getParcelableExtra("UniqueKey");
//
//
//
//
////You can pass Arraylist of Parceble obect as below
//
//    //Array of MyObjects
//    ArrayList<MyObjects> mUsers;
//
//    //Passing MyOjects instance
//    Intent mIntent = new Intent(FromActivity.this, ToActivity.class);
//mIntent.putParcelableArrayListExtra("UniqueKey", mUsers);
//    startActivity(mIntent);
//
//
//    //Getting MyObjects instance
//    Intent mIntent = getIntent();
//    ArrayList<MyObjects> mUsers = mIntent.getParcelableArrayList("UniqueKey");































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
                Log.i("dog e6="," "+e6);
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

                //This intent message is for the table

               // intent1.putExtra(EXTRA_MESSAGE_FIVE,listIntoTable);


               // intent1.putExtra(EXTRA_MESSAGE_SIX,exTableLayout);


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

//    private class FormatDocument2 {
//        private String returnString2() {
//
//
//            try {
//                String url = getResources().getString(R.string.url);
//                //String url = "@string/url";
//                Document doc = Jsoup.connect(url).get();
//
//
//
//                String catHeader;
//
//                Element e2 = doc.select("p:contains(Cats)").get(0);
//                //Still hast HTML tags
//                catHeader = e2.toString();
//                catTitle = Jsoup.parse(catHeader).text();
//                String parString = e2.nextElementSibling().toString();
//                //String dogTextHtml = catTitle + parString;
//
//                //Document doc2 = Jsoup.parse(dogTextHtml);
//                String catTextBefore;
//                Document doc2 = Jsoup.parse(parString);
//
//
//                doc2.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
//                doc2.select("p").append("\\n");
//                doc2.select("li").prepend("\\n\\n");
//                String s = doc2.html().replaceAll("\\\\n", "\n");
//                catText = Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
//
//                //combinedString=e2+parElement;
//
//
//                Log.i("in Element", "paragraph of dogs");
//
//
//                Log.i("JSoup", "Connected successfully!");
//
//            } catch (IOException e) {
//                final StringBuilder builder = new StringBuilder();
//                builder.append("Error : ").append(e.getMessage()).append("\n");
//                Log.i("HTML ERROR", "exception reading HTML");
//            }
//            return catText;
//        }
//    }
    private class FormatDocument2 {
        private String[] catFormattedArray=new String[3];
        private String[] returnCatStringArray() {
            try {
                Log.i("runTaskOneButton","777777777777777777777777");
                String url = getResources().getString(R.string.url);
                //String url = "@string/url";
                Document doc = Jsoup.connect(url).get();
                Element e5 = doc.select("div.entry-content").first();
                Elements elements = doc.body().select("*");
//                Elements e5=elements.select("p:contains(Dogs)");
                Element masthead = doc.select("div.masthead").first();
//                Element e5 = doc.select("div.entry-content").first();
//                  <p>Dogs</p>
                //e6 has all the paragraphs
               //Element e6 = e5.child(1);
                Elements e11=e5.getElementsByTag("ol");
                //Elements e7 = e5.getElementsByTag("p");
                Element epar1=null;
                Element epar2=null;
                //Element e12=e11.getAllElements​();

                for (Element epar:e11){
                    epar1=epar;
                    break;

                    //Element epar3=epar;
                    //Log.i("epars","epar1 "+epar+" epar2 "+epar2+" epar3 "+epar3);
                }
                int ep=0;
                for (Element epar:e11){
                    epar2=epar;
                    if (ep==1){
                        break;
                    }
                    else
                        {ep++;
                        continue;}
                }
                Elements e10=epar1.getAllElements();
                Element e12=epar2.child(1);
                //Elements e9=epar2.getAllElements();
                //Elements e9=e12.getAllElements();
                Elements e9=epar2.getElementsByTag("li");
                //Elements e9=epar2.getAllElements();
                //Elements e9=epar2.getAllElements();
                //Element e9=epar2.firstElementSibling();
                Log.i("Cate11= "," "+e11);
                Log.i("Cate9= "," "+e9);
                Log.i("Cate10= "," "+e10);

                Log.i("epars","epar1 "+epar1+" epar2 "+epar2);

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

                        Log.i("eachCatHere: ", "" + (catArray[numberOfCats]));
                        numberOfCats+=1;
                   // }
                }

                String halfWords = catArray[1];

                Log.i("Cats/2=", "" + (numberOfCats / 2));

                //If an odd number of dogs add one more to left column
                int remCat = numberOfCats % 3;
                int catsInList=numberOfCats/3;

                int catsRem=0;
                int catsRem2=0;

                int catsLeft=0;
                int catsCenter=0;
                int catsRight=0;
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
                if (remCat == 2){
//                if (remCat > 0 && remCat<2){

                    //catsRem+=1;
                    catsCenter+=1;
                    catsLeft+=1;
                }
                else if (remCat == 1){
                    //catsRem2=2;
                    catsLeft+=1;

                }



                catsLeft=catsLeft+catsInList;
                catsCenter=catsCenter+catsInList;
                catsRight=catsInList;


                //Builds list for left side of Dogs
                StringBuilder catsLeftBuild=new StringBuilder();
                String leftCats="";
                for(int i=0;i<catsLeft;i++){
                    catsLeftBuild.append(catArray[i]);
                    catsLeftBuild.append(System.getProperty("line.separator"));
                    Log.i("Times ifn leftCats loop","lets see "+i);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                //String dogsLeftColumn=dogsLeftBuild.toString();
                leftCats=catsLeftBuild.toString();
                Log.i("CatsLEft Column=  ", ""+leftCats);
                String catTextLeft=leftCats;

                int limitCenterLoop=catsLeft+catsCenter;

                StringBuilder catsCenterBuild=new StringBuilder();
                String centerCats="";
                for(int p=catsLeft+1;p>=catsLeft&& p<=limitCenterLoop;p++){
                    catsCenterBuild.append(catArray[p]);
                    catsCenterBuild.append((System.getProperty("line.separator")));
                    //Log.i("Times ifn centerD loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                centerCats=catsCenterBuild.toString();
                //Log.i("DogsCenter Column=  ", ""+centerDogs);
                String catTextCenter=centerCats;
                Log.i("catsCenter", " "+ catTextCenter);

                //dogTextCenter="something";

                int limitRightLoop=catsCenter+catsRight;

                //Creates right side list for Dogs
                StringBuilder catsRightBuild=new StringBuilder();
                String rightCats="";
                for(int p=limitCenterLoop;p<=numberOfCats-1;p++){
                    catsRightBuild.append(catArray[p]);
                    catsRightBuild.append((System.getProperty("line.separator")));
                    //Log.i("Times ifn right loop","lets see "+p);
                    //leftDogs=dogArray[i];
                    //leftDogs=leftDogs+("\n");
                }
                rightCats=catsRightBuild.toString();
                //Log.i("DogsRight Column=  ", ""+rightDogs);
                String catTextRight=rightCats;
                Log.i("catsRight", " "+ catTextRight);


                String catsOnLeft=catsLeft+"";
                String catsOnRight=catsRight+"";





                //Element[] elementArr = arrayElements.toArray(new Element[]{});
                Log.i("numberOfCats =",""+numberOfCats);




                Element e2 = doc.select("p:contains(Dogs)").get(0);
                catTitle=e2.text();

                catWords=0;
                String cattt;

                Log.i("E9   Should Cats",""+e9);
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


                catFormattedArray[0]=catTextLeft;
                catFormattedArray[1]=catTextCenter;
                catFormattedArray[2]=catTextRight;

                intent2 = new Intent(MainActivity.this, CatsActivity.class);
                //Text editText =findViewById(R.id.somethingText);
                //String message = editText.getText().toString();
                Log.i("sendActivity","222222222222222222222222");
                intent2.putExtra(EXTRA_MESSAGE_TEN, catTitle);
                intent2.putExtra(EXTRA_MESSAGE_ELEVEN, catTextLeft);
                intent2.putExtra(EXTRA_MESSAGE_TWELVE,catTextCenter);
                intent2.putExtra(EXTRA_MESSAGE_THIRTEEN,catTextRight);

                //This intent message is for the table

                // intent1.putExtra(EXTRA_MESSAGE_FIVE,listIntoTable);


                // intent1.putExtra(EXTRA_MESSAGE_SIX,exTableLayout);


                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");

            } catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            //return dogTextLeft;
            return catFormattedArray;
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
