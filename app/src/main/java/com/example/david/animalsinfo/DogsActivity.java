package com.example.david.animalsinfo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
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
        //Intent intent = this.getIntent();
        Intent intent = getIntent();
        //String message=this.getIntent().getExtras().getString(EXTRA_MESSAGE);
        String message=intent.getStringExtra(EXTRA_MESSAGE);
        //String message = intent.getStringExtra(getActivity().getBaseContext().EXTRA_MESSAGE);
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


        private class DogTableCreator {
        //public class DogTableCreator {
            //public TableLayout dataIntoTable() {
            //public TableLayout dataIntoTable() {
            private TableLayout dataIntoTable() {
                Log.i("dataInto Table", "****************");
                //TableLayout mTableLayout=new TableLayout(MainActivity.this);
                setContentView(R.layout.activity_dogs);
                TableLayout mTableLayout = (TableLayout) findViewById(R.id.dogsTable);

//                if(mTableLayout.getParent()!=null)
//                    ((ViewGroup)mTableLayout.getParent()).removeView(mTableLayout);


                mTableLayout.setStretchAllColumns(true);
                //exTableLayout.setStretchAllColumns(true);
                //TextView[][] listIntoTable = new TextView[16][4];
                TextView[][] listIntoTable = new TextView[30][4];
                TextView[] rowArray;
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

                DogsData dogsData = new DogsData();
                DogsAddData[] dogsAddData = dogsData.getInfo();

                int rows = dogsAddData.length;
                TextView textSpacer = null;

                Log.i("tableRows", " " + rows);
                //mTableLayout.removeAllViews();
                //exTableLayout.removeAllViews();

//This section can be done on background thread///////////////
                //String[][] dogArrayTB = new String[14][10];

                // -1 means heading row
                for (int i = 0; i < rows; i++) {
                    DogsAddData row = null;
                    if (i > 0)
                        row = dogsAddData[i];
                    else {
                        textSpacer = new TextView(DogsActivity.this);
                        textSpacer.setText(" ");

                    }
                    final LinearLayout layDogName = new LinearLayout(DogsActivity.this);
                    layDogName.setOrientation(LinearLayout.VERTICAL);
                    layDogName.setPadding(0, 0, 0, 0);
                    //layDogWeight.setPadding(0, 10, 0, 10);
                    layDogName.setBackgroundColor(Color.parseColor("#70E8D9B5"));
                    //layDogName.setBackgroundColor(Color.parseColor("#80a84908"));
                    //layDogName.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    //layDogName.setBackgroundColor(Color.parseColor("20000000"));
                    // data columns
                    final TextView tvTitle = new TextView(DogsActivity.this);
                    final TextView tv = new TextView(DogsActivity.this);

                    if (i == 0) {
                        //tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                //TableRow.LayoutParams.WRAP_CONTENT));
                        tvTitle.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                        tvTitle.setGravity(Gravity.TOP);
                    } else {
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        //tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                //TableRow.LayoutParams.MATCH_PARENT));
                        tv.setGravity(Gravity.TOP);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    }

                    //tv2.setGravity(Gravity.LEFT);
                    //tv.setPadding(0, 0, 0, 0);
                    if (i == 0) {
                        tvTitle.setPadding(10, 15, 0, 5);
                        //tv2.setText(String.valueOf(row.dogWeight));
                        tvTitle.setText("Dog Name");
                        tvTitle.setTextColor(Color.parseColor("#000000"));
                        tvTitle.setBackgroundColor(Color.parseColor("#80171c59"));
                        //tv.setBackgroundColor(Color.parseColor("#80a84908"));
                        //tv.setBackgroundColor(Color.parseColor("#f7f7f7"));
                    } else {
                        tv.setPadding(10, 15, 0, 5);
                        //tv.setBackgroundColor(Color.parseColor("#20000000"));
                        tv.setBackgroundColor(Color.parseColor("#80F7B05E"));
                        //tv.setBackgroundColor(Color.parseColor("#9145fd"));
                        //tv.setBackgroundColor(Color.parseColor("#ffffff"));
                        //tv.setTextColor(Color.parseColor("#000000"));
                        tv.setText(String.valueOf(row.dogName));
                        tv.setTextColor(Color.parseColor("#000000"));
                    }
                    layDogName.addView(tv);

                    final TextView tvb = new TextView(DogsActivity.this);

//                    if(tv2b.getParent()!=null)
//                        ((ViewGroup)tv2b.getParent()).removeView(tv2b);

                    if (i > 0) {
                        //tvb.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                //TableRow.LayoutParams.WRAP_CONTENT));
                        tvb.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tvb.setBackgroundColor(Color.parseColor("#70E8D9B5"));
                        tvb.setGravity(Gravity.BOTTOM);
                        tvb.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                        tvb.setPadding(10, 15, 0, 5);
                        //tvb.setBackgroundColor(Color.parseColor("#6ba814"));
                        //tvb.setBackgroundColor(Color.parseColor("#ffffff"));
                        //tvb.setTextColor(Color.parseColor("#000000"));
                        tvb.setText("");
                        layDogName.addView(tvb);
                        //tv2b.setText(String.valueOf(row.dogPounds));
                    }




                    final LinearLayout layDogWeight = new LinearLayout(DogsActivity.this);
                    layDogWeight.setOrientation(LinearLayout.VERTICAL);
                    layDogWeight.setPadding(0, 0, 0, 0);
                    //layDogWeight.setPadding(0, 10, 0, 10);
                    layDogWeight.setBackgroundColor(Color.parseColor("#70E8D9B5"));

                    final TextView tv2Title = new TextView(DogsActivity.this);
                    final TextView tv2 = new TextView(DogsActivity.this);

//                    if(tv2.getParent()!=null)
//                        ((ViewGroup)tv2.getParent()).removeView(tv2);

                    if (i == 0) {
                        tv2Title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv2Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                        tv2Title.setGravity(Gravity.TOP);
                    } else {
                        tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                        tv2.setGravity(Gravity.TOP);
                    }

                    //tv2.setGravity(Gravity.LEFT);



                    if (i == 0) {
                        tv2Title.setPadding(10, 15, 0, 5);
                        //tv2.setText(String.valueOf(row.dogWeight));
                        tv2Title.setText("Dog Weight");
                        tv2Title.setTextColor(Color.parseColor("#000000"));
                        tv2Title.setBackgroundColor(Color.parseColor("#80171c59"));
                    } else {
                        tv2.setBackgroundColor(Color.parseColor("#80F7B05E"));
                        tv2.setTextColor(Color.parseColor("#000000"));
                        tv2.setText(String.valueOf(row.dogWeight));
                        tv2.setPadding(10, 15, 0, 5);
                    }
                    layDogWeight.addView(tv2);

                    final TextView tv2b = new TextView(DogsActivity.this);

//                    if(tv2b.getParent()!=null)
//                        ((ViewGroup)tv2b.getParent()).removeView(tv2b);

                    if (i > 0) {
                        tv2b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv2b.setGravity(Gravity.BOTTOM);
                        tv2b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                        tv2b.setPadding(10, 15, 0, 5);
                        tv2b.setBackgroundColor(Color.parseColor("#70E8D9B5"));
                        tv2b.setTextColor(Color.parseColor("#000000"));
                        tv2b.setText(row.dogPounds);
                        layDogWeight.addView(tv2b);
                        //tv2b.setText(String.valueOf(row.dogPounds));
                    }


                    //tv2.setGravity(Gravity.LEFT);
                    final LinearLayout layDogHeight = new LinearLayout(DogsActivity.this);
                    layDogHeight.setOrientation(LinearLayout.VERTICAL);
                    layDogHeight.setPadding(0, 0, 0, 0);
                    layDogHeight.setBackgroundColor(Color.parseColor("#70E8D9B5"));

                    final TextView tv3Title = new TextView(DogsActivity.this);
                    final TextView tv3 = new TextView(DogsActivity.this);


//                    if(tv3.getParent()!=null)
//                        ((ViewGroup)tv3.getParent()).removeView(tv3);

                    if (i == 0) {
                        tv3Title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv3Title.setPadding(10, 15, 0, 5);
                        tv3Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                        tv3Title.setGravity(Gravity.TOP);
                    } else {
                        tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv3.setGravity(Gravity.TOP);
                        tv3.setPadding(10, 15, 0, 5);
                        tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    }



                    if (i == 0) {
                        //tv3.setText(String.valueOf(row.dogHeight));
                        tv3Title.setText("Dog Height");
                        tv3Title.setTextColor(Color.parseColor("#000000"));
                        tv3Title.setBackgroundColor(Color.parseColor("#80171c59"));
                    } else {
                        tv3.setBackgroundColor(Color.parseColor("#80F7B05E"));
                        tv3.setTextColor(Color.parseColor("#000000"));
                        //tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                        tv3.setText(row.dogHeight);
                    }

                    layDogHeight.addView(tv3);

                    final TextView tv3b = new TextView(DogsActivity.this);

//                    if(tv3b.getParent()!=null)
//                        ((ViewGroup)tv3b.getParent()).removeView(tv3b);

                    if (i > 0) {
                        tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv3b.setGravity(Gravity.BOTTOM);
                        tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                        tv3b.setPadding(10, 15, 0, 5);
                        tv3b.setBackgroundColor(Color.parseColor("#70E8D9B5"));
                        tv3b.setTextColor(Color.parseColor("#000000"));
                        tv3b.setText(String.valueOf(row.dogInches));
                        layDogHeight.addView(tv3b);
                    }

                    final LinearLayout layDogLifespan = new LinearLayout(DogsActivity.this);
                    layDogLifespan.setOrientation(LinearLayout.VERTICAL);
                    layDogLifespan.setPadding(0, 0, 0, 0);
                    layDogLifespan.setBackgroundColor(Color.parseColor("#70E8D9B5"));

                    final TextView tv4Title = new TextView(DogsActivity.this);
                    final TextView tv4 = new TextView(DogsActivity.this);

//                    if(tv4.getParent()!=null)
//                        ((ViewGroup)tv4.getParent()).removeView(tv4);

                    if (i == 0) {
                        tv4Title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv4Title.setGravity(Gravity.TOP);
                        tv4Title.setPadding(10, 15, 0, 5);
                        //layAmounts.setBackgroundColor(Color.parseColor("#f7f7f7"));
                    } else {
                        tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv4.setPadding(10, 15, 0, 5);
                        tv4.setGravity(Gravity.TOP);
                        //layAmounts.setBackgroundColor(Color.parseColor("#ffffff"));
                    }


                    //tv4.setGravity(Gravity.RIGHT);

                    if (i == 0) {
                        //tv4.setText(String.valueOf(row.dogLifespan));
                        tv4Title.setText("Dog Lifespan");
                        tv4Title.setTextColor(Color.parseColor("#000000"));
                        //"#E6f7f7f7"
                        //tv4.setBackgroundColor(Color.parseColor("#F2f7f7f7"));
                        tv4Title.setBackgroundColor(Color.parseColor("#80171c59"));
                        tv4Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                    } else {
                        tv4.setBackgroundColor(Color.parseColor("#80F7B05E"));
                        tv4.setTextColor(Color.parseColor("#000000"));
                        tv4.setText(String.valueOf(row.dogLifespan));
                        tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    }
                    layDogLifespan.addView(tv4);

                    final TextView tv4b = new TextView(DogsActivity.this);

//                    if(tv4b.getParent()!=null)
//                        ((ViewGroup)tv4b.getParent()).removeView(tv4b);

                    if (i > 0) {
                        tv4b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                                TableRow.LayoutParams.MATCH_PARENT));
                        tv4b.setGravity(Gravity.BOTTOM);
                        tv4b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                        tv4b.setPadding(10, 15, 0, 5);
                        tv4b.setBackgroundColor(Color.parseColor("#70E8D9B5"));
                        tv4b.setTextColor(Color.parseColor("#000000"));
                        tv4b.setText(String.valueOf(row.dogYears));
                        layDogLifespan.addView(tv4b);
                    }




                    // add table row

                    final TableRow tr = new TableRow(DogsActivity.this);
                    tr.setId(i + 1);
                    TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT);
                    trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                    tr.setPadding(0, 0, 0, 0);
                    tr.setLayoutParams(trParams);
                    if(i==0) {
                        tr.addView(tvTitle);
                        tr.addView(tv2Title);
                        tr.addView(tv3Title);
                        tr.addView(tv4Title);
                    }
                    if(i>0) {
                        tr.addView(layDogName);
                        tr.addView(layDogWeight);
                        tr.addView(layDogHeight);
                        tr.addView(layDogLifespan);
                    }




                    //create array of arrays holding text views
                    int dogsRow = 4;





                    mTableLayout.addView(tr, trParams);
                    //exTableLayout.addView(tr, trParams);

                    //mTableLayout.setStretchAllColumns(true);
                    //exTableLayout.setStretchAllColumns(true);

                    if (i > 0) {

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
                        //tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
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

                return mTableLayout;
            }
        }
    }
