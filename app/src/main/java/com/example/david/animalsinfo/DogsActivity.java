package com.example.david.animalsinfo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DogsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        new DogTableCreator().dataIntoTable();
        //dataIntoTable();
        //Log.i("something", "ok");

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_TWO);
        String message3 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_THREE);
        String message4 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_FOUR);
        //TableLayout[] tableLayout = intent.get(MainActivity.EXTRA_MESSAGE_FIVE);
        //TableLayout tableLayout = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_FIVE);
        //change this message5 into table
        //String table[] = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE_FIVE);
        //TableLayout tableLayout = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE_FIVE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.dogTitle);
        textView.setText(message);
        Log.i("textView", "= " + textView);
        TextView textView2 = findViewById(R.id.dogsTextBox);
        textView2.setText(message2);
        TextView textView3 = findViewById(R.id.dogsTextBox2);
        textView3.setText(message3);
        TextView textView4 = findViewById(R.id.dogsTextBox3);
        textView4.setText(message4);
    }
        //tHIS IS FOR THE DOGS TABLE

//        boolean startdogIntoTable;
//
        //if (true) {


        public class DogTableCreator {
            //public TableLayout dataIntoTable() {
            public TableLayout dataIntoTable() {
                Log.i("dataInto Table", "****************");
                //TableLayout mTableLayout=new TableLayout(MainActivity.this);
                setContentView(R.layout.activity_dogs);
                TableLayout mTableLayout = (TableLayout) findViewById(R.id.dogsTable);
                mTableLayout.setStretchAllColumns(true);
                mTableLayout.removeAllViews();
                //exTableLayout.setStretchAllColumns(true);
                TextView[][] listIntoTable = new TextView[30][4];
                TextView[] rowArray;
                //InvoiceData.java=DogsAddData.java
                //mTableLayout.setStretchAllColumns(true);
                //Invoices.java=DogsData.java

                //data=dogsAddData
                //invoices=dogsData
                Log.i("dataInto Table", "))))))))))))))))");

                //added this part
                //setContentView(R.layout.activity_dogs);

                //mTableLayout = (TableLayout) findViewById(R.id.dogsTable);


//This section can be done on background thread///////////////////

                int leftRowMargin = 0;
                int topRowMargin = 0;
                int rightRowMargin = 0;
                int bottomRowMargin = 0;
                int textSize = 0, smallTextSize = 0, mediumTextSize = 17;

                //TableLayout tableLayout2 = (TableLayout) findViewById(R.id.dogsTable);
                textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
                smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
                mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

//        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
//        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
//        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

                DogsData dogsData = new DogsData();
                DogsAddData[] dogsAddData = dogsData.getInfo();

                int rows = dogsAddData.length;
                TextView textSpacer = null;

                Log.i("tableRows", " " + rows);
                //mTableLayout.removeAllViews();
                //exTableLayout.removeAllViews();

//This section can be done on background thread///////////////

                // -1 means heading row
                for (int i = -1; i < rows; i++) {
                    DogsAddData row = null;
                    if (i > -1)
                        row = dogsAddData[i];
                    else {
                        textSpacer = new TextView(DogsActivity.this);
                        textSpacer.setText("");

                    }
                    // data columns
                    final TextView tv = new TextView(DogsActivity.this);
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

                    //LinearLayout for the dogs weight holds two TextViews
                    final LinearLayout layDogWeight = new LinearLayout(DogsActivity.this);
                    layDogWeight.setOrientation(LinearLayout.VERTICAL);
                    layDogWeight.setPadding(0, 10, 0, 10);
                    layDogWeight.setBackgroundColor(Color.parseColor("#f8f8f8"));

                    final TextView tv2 = new TextView(DogsActivity.this);
                    if (i == -1) {
                        tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                    } else {
                        tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                    }

                    //tv2.setGravity(Gravity.LEFT);
                    tv2.setGravity(Gravity.TOP);

                    tv2.setPadding(5, 15, 0, 15);
                    if (i == -1) {
                        tv2.setText("Dog Weight");
                        tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                    } else {
                        tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                        tv2.setTextColor(Color.parseColor("#000000"));
                        tv2.setText(String.valueOf(row.dogWeight));
                    }
                    layDogWeight.addView(tv2);

                    final TextView tv2b = new TextView(DogsActivity.this);
                    if (i > -1) {
                        tv2b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv2b.setGravity(Gravity.BOTTOM);
                        tv2b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                        tv2b.setPadding(5, 15, 0, 15);
                        tv2b.setBackgroundColor(Color.parseColor("#ffffff"));
                        tv2b.setTextColor(Color.parseColor("#000000"));
                        tv2b.setText(String.valueOf(row.dogPounds));
                    }
                    layDogWeight.addView(tv2b);

                    //tv2.setGravity(Gravity.LEFT);

                    //LinearLayout for the dogs height holds two TextViews
                    final LinearLayout layDogHeight = new LinearLayout(DogsActivity.this);
                    layDogHeight.setOrientation(LinearLayout.VERTICAL);
                    layDogHeight.setPadding(0, 10, 0, 10);
                    layDogHeight.setBackgroundColor(Color.parseColor("#f8f8f8"));

                    final TextView tv3 = new TextView(DogsActivity.this);
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
                    layDogHeight.addView(tv3);


                    final TextView tv3b = new TextView(DogsActivity.this);
                    if (i > -1) {
                        tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv3b.setGravity(Gravity.BOTTOM);
                        tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                        tv3b.setPadding(5, 15, 0, 15);
                        tv3b.setBackgroundColor(Color.parseColor("#ffffff"));
                        tv3b.setTextColor(Color.parseColor("#000000"));
                        tv3b.setText(String.valueOf(row.dogInches));
                    }
                    layDogHeight.addView(tv3b);


                    //LinearLayout for the dogs lifespan holds two TextViews
                    final LinearLayout layDogLifespan = new LinearLayout(DogsActivity.this);
                    layDogLifespan.setOrientation(LinearLayout.VERTICAL);
                    layDogLifespan.setPadding(0, 10, 0, 10);
                    layDogLifespan.setBackgroundColor(Color.parseColor("#f8f8f8"));



                    final TextView tv4 = new TextView(DogsActivity.this);
                    if (i == -1) {
                        tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv4.setPadding(5, 5, 5, 5);
                        //layAmounts.setBackgroundColor(Color.parseColor("#f7f7f7"));
                    } else {
                        tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv4.setPadding(5, 5, 5, 5);
                        //layAmounts.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                    tv4.setGravity(Gravity.LEFT);
                    //tv4.setGravity(Gravity.RIGHT);

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

                    layDogLifespan.addView(tv4);

                    final TextView tv4b = new TextView(DogsActivity.this);
                    if (i > -1) {
                        tv4b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv4b.setGravity(Gravity.BOTTOM);
                        tv4b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                        tv4b.setPadding(5, 15, 0, 15);
                        tv4b.setBackgroundColor(Color.parseColor("#ffffff"));
                        tv4b.setTextColor(Color.parseColor("#000000"));
                        tv4b.setText(String.valueOf(row.dogYears));
                    }

                    layDogLifespan.addView(tv4b);




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

                    final TableRow tr = new TableRow(DogsActivity.this);
                    tr.setId(i + 1);
                    TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT);
                    trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                    tr.setPadding(0, 0, 0, 0);
                    tr.setLayoutParams(trParams);


                    tr.addView(tv);
                    tr.addView(layDogWeight);
                    //tr.addView(tv2);
                    tr.addView(layDogHeight);
                    tr.addView(layDogLifespan);

                    //create array of arrays holding text views
                    int dogsRow = 4;


//                    TextView[] dogColumnArray = new TextView[dogsRow];
//                    //Log.i("IntV","intextview not showing");
//                    //for(int p = 0; p < dogsRow; p++) {
//                    dogColumnArray[0] = tv;
//                    dogColumnArray[1] = tv2;
//                    dogColumnArray[2] = tv3;
//                    dogColumnArray[3] = tv4;

                    //int r=0;
                    //
                    //listIntoTable[][]={dogColumnArray};
                    //for(int d = 0; d < rows; d++){
                    //listIntoTable[r][]={tv,tv2,tv3,tv4};
                    //r++;
                    //t is for rows and j is for columns iterates through all columns first then the row
//                    int t = 0;
//                    //for (t; t < listIntoTable.length; t++) {
//                    for (int j = 0; j < listIntoTable[t].length; j++) {
//                        listIntoTable[t][j] = dogColumnArray[j];
//                        //listIntoTable[t][j] = t + j;
//                        Log.i("2d Array", "" + listIntoTable[t][j]);
//                    }
//                    //}
//                    t++;


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
                        final TableRow trSep = new TableRow(DogsActivity.this);
                        TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.WRAP_CONTENT);
                        trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                        trSep.setLayoutParams(trParamsSep);
                        TextView tvSep = new TextView(DogsActivity.this);
                        TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT);
                        tvSepLay.span = 4;
                        tvSep.setLayoutParams(tvSepLay);
                        tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                        tvSep.setHeight(1);

                        trSep.addView(tvSep);
                        mTableLayout.addView(trSep, trParamsSep);
                        //tableLayout2.addView(mTableLayout);
                        Log.i("TL finished", " ////////////////////////////");

                        //TableLayout[] tableArray = {mTableLayout,tableLayout2};
                        //mTableLayout.setStretchAllColumns(true);

                        //exTableLayout.addView(trSep, trParamsSep);
                    }


                }
//            Bundle bundle = new Bundle();
//            bundle.putStringArray("key1", mTableLayout);
//            bundle.putStringArray("key2", tableLayout2);
//
//            Message message = new Message();
//            message.setData(bundle);
//            handler.sendMessage(message);
//
//            Message msgArray = new Message();
//            Log.i("runTaskOneButton","11111111111111111111111111");
//            msgArray.what = MAIN_THREAD_TASK_1;
//            msgArray.arg1=tableArray;
                //intent1.putExtra(EXTRA_MESSAGE_FIVE, tableArray);


                //return tableLayout2;
                //return tableArray;
                return mTableLayout;
            }
        }
    }
