package com.example.david.animalsinfo;

import android.app.Activity;
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

public class CatsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);
        new CatTableCreator().dataIntoTable2();
        //dataIntoTable();
        //Log.i("something", "ok");

        // Get the Intent that started this activity and extract the string
        Intent intent2 = getIntent();
        String message = intent2.getStringExtra(MainActivity.EXTRA_MESSAGE_TEN);
        String message2 = intent2.getStringExtra(MainActivity.EXTRA_MESSAGE_ELEVEN);
        String message3 = intent2.getStringExtra(MainActivity.EXTRA_MESSAGE_TWELVE);
        String message4 = intent2.getStringExtra(MainActivity.EXTRA_MESSAGE_THIRTEEN);
        //change this message5 into table
        //String table[] = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE_FIVE);
        //TableLayout tableLayout = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE_FIVE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.catTitle);
        textView.setText(message);
        Log.i("textView", "= " + textView);
        TextView textView2 = findViewById(R.id.catsTextBox);
        textView2.setText(message2);
        TextView textView3 = findViewById(R.id.catsTextBox2);
        textView3.setText(message3);
        TextView textView4 = findViewById(R.id.catsTextBox3);
        textView4.setText(message4);



    }

    private class CatTableCreator {
        //public class DogTableCreator {
        //public TableLayout dataIntoTable() {
        //public TableLayout dataIntoTable() {
        private TableLayout dataIntoTable2() {
            Log.i("dataInto Table", "****************");
            //TableLayout mTableLayout=new TableLayout(MainActivity.this);
            setContentView(R.layout.activity_cats);
            TableLayout mTableLayout2 = (TableLayout) findViewById(R.id.catsTable);

//                if(mTableLayout.getParent()!=null)
//                    ((ViewGroup)mTableLayout.getParent()).removeView(mTableLayout);


            mTableLayout2.setStretchAllColumns(true);
            //exTableLayout.setStretchAllColumns(true);
            //TextView[][] listIntoTable = new TextView[16][4];
            TextView[][] listIntoTable2 = new TextView[30][4];
            TextView[] rowArray2;
            //InvoiceData.java=DogsAddData.java
            //mTableLayout.setStretchAllColumns(true);
            //Invoices.java=DogsData.java

            //data=dogsAddDataif(tv.getParent()!=null)
            //((ViewGroup)tv.getParent()).removeView(tv);
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
            int textSize, smallTextSize, mediumTextSize, largeTextSize;

            //TableLayout tableLayout2 = (TableLayout) findViewById(R.id.dogsTable);
            textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
            smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
            mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);
            largeTextSize = (int) getResources().getDimension(R.dimen.font_size_large);

//        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
//        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
//        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

            CatsData catsData = new CatsData();
            CatsAddData[] catsAddData = catsData.getInfo();

            int rows = catsAddData.length;
            TextView textSpacer = null;

            Log.i("tableRows", " " + rows);
            //mTableLayout.removeAllViews();
            //exTableLayout.removeAllViews();

//This section can be done on background thread///////////////
            //String[][] dogArrayTB = new String[14][10];

            // -1 means heading row
            for (int i = 0; i < rows; i++) {
                CatsAddData row = null;
                if (i > 0)
                    row = catsAddData[i];
                else {
                    textSpacer = new TextView(CatsActivity.this);
                    textSpacer.setText("");

                }
                // data columns
                final TextView tv = new TextView(CatsActivity.this);

//                    if(tv.getParent()!=null)
//                        ((ViewGroup)tv.getParent()).removeView(tv);

                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                tv.setGravity(Gravity.LEFT);

                tv.setPadding(5, 15, 0, 15);
                if (i == 0) {
                    tv.setText("Cat Breed");
                    //tv.setText(String.valueOf(row.dogName));
                    tv.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                } else {
                    tv.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    tv.setText(String.valueOf(row.catName));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                }

                final LinearLayout layCatWeight = new LinearLayout(CatsActivity.this);
                layCatWeight.setOrientation(LinearLayout.VERTICAL);
                layCatWeight.setPadding(0, 10, 0, 10);
                //layDogWeight.setPadding(0, 10, 0, 10);
                layCatWeight.setBackgroundColor(Color.parseColor("#f8f8f8"));

                final TextView tv2 = new TextView(CatsActivity.this);

//                    if(tv2.getParent()!=null)
//                        ((ViewGroup)tv2.getParent()).removeView(tv2);

                if (i == 0) {
                    tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                } else {
                    tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                }

                //tv2.setGravity(Gravity.LEFT);
                tv2.setGravity(Gravity.TOP);

                tv2.setPadding(5, 5, 0, 5);
                if (i == 0) {
                    //tv2.setText(String.valueOf(row.dogWeight));
                    tv2.setText("Cat Weight");
                    tv2.setBackgroundColor(Color.parseColor("#f7f7f7"));
                } else {
                    tv2.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv2.setTextColor(Color.parseColor("#000000"));
                    tv2.setText(String.valueOf(row.catWeight));
                }
                layCatWeight.addView(tv2);

                final TextView tv2b = new TextView(CatsActivity.this);

//                    if(tv2b.getParent()!=null)
//                        ((ViewGroup)tv2b.getParent()).removeView(tv2b);

                if (i > 0) {
                    tv2b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv2b.setGravity(Gravity.BOTTOM);
                    tv2b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    tv2b.setPadding(5, 15, 0, 15);
                    tv2b.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv2b.setTextColor(Color.parseColor("#000000"));
                    tv2b.setText(row.catPounds);
                    layCatWeight.addView(tv2b);
                    //tv2b.setText(String.valueOf(row.dogPounds));
                }


                //tv2.setGravity(Gravity.LEFT);
                final LinearLayout layCatHeight = new LinearLayout(CatsActivity.this);
                layCatHeight.setOrientation(LinearLayout.VERTICAL);
                layCatHeight.setPadding(0, 10, 0, 10);
                layCatHeight.setBackgroundColor(Color.parseColor("#f8f8f8"));

                final TextView tv3 = new TextView(CatsActivity.this);

//                    if(tv3.getParent()!=null)
//                        ((ViewGroup)tv3.getParent()).removeView(tv3);

                if (i == 0) {
                    tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv3.setPadding(5, 5, 0, 5);
                    tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                } else {
                    tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv3.setPadding(5, 5, 0, 5);
                    tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                }

                tv3.setGravity(Gravity.TOP);


                if (i == 0) {
                    //tv3.setText(String.valueOf(row.dogHeight));
                    tv3.setText("Cat Height");
                    tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    tv3.setTextColor(Color.parseColor("#000000"));
                    //tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                    tv3.setText(row.catHeight);
                }

                layCatHeight.addView(tv3);

                final TextView tv3b = new TextView(CatsActivity.this);

//                    if(tv3b.getParent()!=null)
//                        ((ViewGroup)tv3b.getParent()).removeView(tv3b);

                if (i > 0) {
                    tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv3b.setGravity(Gravity.BOTTOM);
                    tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    tv3b.setPadding(5, 15, 0, 15);
                    tv3b.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv3b.setTextColor(Color.parseColor("#000000"));
                    tv3b.setText(String.valueOf(row.catInches));
                    layCatHeight.addView(tv3b);
                }
                //layDogHeight.addView(tv3b);
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
                final LinearLayout layCatLifespan = new LinearLayout(CatsActivity.this);
                layCatLifespan.setOrientation(LinearLayout.VERTICAL);
                layCatLifespan.setPadding(0, 10, 0, 10);
                layCatLifespan.setBackgroundColor(Color.parseColor("#f8f8f8"));

                final TextView tv4 = new TextView(CatsActivity.this);

//                    if(tv4.getParent()!=null)
//                        ((ViewGroup)tv4.getParent()).removeView(tv4);

                if (i == 0) {
                    tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv4.setPadding(5, 5, 0, 5);
                    //layAmounts.setBackgroundColor(Color.parseColor("#f7f7f7"));
                } else {
                    tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv4.setPadding(5, 5, 0, 5);
                    //layAmounts.setBackgroundColor(Color.parseColor("#ffffff"));
                }

                tv4.setGravity(Gravity.LEFT);
                //tv4.setGravity(Gravity.RIGHT);

                if (i == 0) {
                    //tv4.setText(String.valueOf(row.dogLifespan));
                    tv4.setText("Cat Lifespan");
                    tv4.setBackgroundColor(Color.parseColor("#f7f7f7"));
                    tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                } else {
                    tv4.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv4.setTextColor(Color.parseColor("#000000"));
                    tv4.setText(String.valueOf(row.catLifespan));
                    tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                }
                layCatLifespan.addView(tv4);

                final TextView tv4b = new TextView(CatsActivity.this);

//                    if(tv4b.getParent()!=null)
//                        ((ViewGroup)tv4b.getParent()).removeView(tv4b);

                if (i > 0) {
                    tv4b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv4b.setGravity(Gravity.BOTTOM);
                    tv4b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    tv4b.setPadding(5, 15, 0, 15);
                    tv4b.setBackgroundColor(Color.parseColor("#ffffff"));
                    tv4b.setTextColor(Color.parseColor("#000000"));
                    tv4b.setText(String.valueOf(row.catYears));
                    layCatLifespan.addView(tv4b);
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

                final TableRow tr = new TableRow(CatsActivity.this);
                tr.setId(i + 1);
                TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                tr.setPadding(0, 0, 0, 0);
                tr.setLayoutParams(trParams);

                tr.addView(tv);
                tr.addView(layCatWeight);
                tr.addView(layCatHeight);
                tr.addView(layCatLifespan);

//                    tr.addView(tv);
//                    tr.addView(tv2);
//                    tr.addView(tv3);
//                    tr.addView(tv4);

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


                mTableLayout2.addView(tr, trParams);
                //exTableLayout.addView(tr, trParams);

                //mTableLayout.setStretchAllColumns(true);
                //exTableLayout.setStretchAllColumns(true);

                if (i > 0) {

                    // add separator row
                    final TableRow trSep = new TableRow(CatsActivity.this);
                    TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT);
                    trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                    trSep.setLayoutParams(trParamsSep);
                    TextView tvSep = new TextView(CatsActivity.this);
                    TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT);
                    tvSepLay.span = 4;
                    tvSep.setLayoutParams(tvSepLay);
                    tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                    tvSep.setHeight(1);

                    trSep.addView(tvSep);
                    mTableLayout2.addView(trSep, trParamsSep);
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
            return mTableLayout2;
        }
    }
}

