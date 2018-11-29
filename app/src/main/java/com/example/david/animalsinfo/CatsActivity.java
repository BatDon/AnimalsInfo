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
        //public class CatTableCreator {
        //public TableLayout dataIntoTable() {
        //public TableLayout dataIntoTable() {
        private TableLayout dataIntoTable2() {
            Log.i("dataInto Table", "****************");
            //TableLayout mTableLayout=new TableLayout(MainActivity.this);
            setContentView(R.layout.activity_cats);
            TableLayout mTableLayout = (TableLayout) findViewById(R.id.catsTable);

//                if(mTableLayout.getParent()!=null)
//                    ((ViewGroup)mTableLayout.getParent()).removeView(mTableLayout);


            mTableLayout.setStretchAllColumns(true);
            //exTableLayout.setStretchAllColumns(true);
            //TextView[][] listIntoTable = new TextView[16][4];
            TextView[][] listIntoTable = new TextView[30][4];
            TextView[] rowArray;
            //InvoiceData.java=CatsAddData.java
            //mTableLayout.setStretchAllColumns(true);
            //Invoices.java=CatsData.java

            //data=CatsAddDataif(tv.getParent()!=null)
            //((ViewGroup)tv.getParent()).removeView(tv);
            //invoices=CatsData
            Log.i("dataInto Table", "))))))))))))))))");

            //added this part
            //setContentView(R.layout.activity_cats);

            //mTableLayout = (TableLayout) findViewById(R.id.catsTable);


//This section can be done on background thread///////////////////

            int leftRowMargin = 0;
            int topRowMargin = 0;
            int rightRowMargin = 0;
            int bottomRowMargin = 0;
            int textSize, smallTextSize, mediumTextSize, largeTextSize;

            //TableLayout tableLayout2 = (TableLayout) findViewById(R.id.catsTable);
            textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
            smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
            mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);
            largeTextSize = (int) getResources().getDimension(R.dimen.font_size_large);

//        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
//        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
//        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

            CatsData CatsData = new CatsData();
            CatsAddData[] CatsAddData = CatsData.getInfo();

            int rows = CatsAddData.length;
            TextView textSpacer = null;

            Log.i("tableRows", " " + rows);
            //mTableLayout.removeAllViews();
            //exTableLayout.removeAllViews();

//This section can be done on background thread///////////////
            //String[][] catArrayTB = new String[14][10];

            // -1 means heading row
            for (int i = 0; i < rows; i++) {
                CatsAddData row = null;
                if (i > 0)
                    row = CatsAddData[i];
                else {
                    textSpacer = new TextView(CatsActivity.this);
                    textSpacer.setText(" ");

                }
                final LinearLayout layCatName = new LinearLayout(CatsActivity.this);
                layCatName.setOrientation(LinearLayout.VERTICAL);
                layCatName.setPadding(0, 0, 0, 0);
                //layCatWeight.setPadding(0, 10, 0, 10);
                layCatName.setBackgroundColor(Color.parseColor("#40133603"));
                //layCatName.setBackgroundColor(Color.parseColor("#80a84908"));
                //layCatName.setBackgroundColor(Color.parseColor("#f8f8f8"));
                //layCatName.setBackgroundColor(Color.parseColor("20000000"));
                // data columns
                final TextView tvTitle = new TextView(CatsActivity.this);
                final TextView tv = new TextView(CatsActivity.this);

                if (i == 0) {
                    //tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    //TableRow.LayoutParams.WRAP_CONTENT));
                    tvTitle.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tvTitle.setGravity(Gravity.TOP);
                    tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                } else {
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv.setGravity(Gravity.TOP);
                    //tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    //TableRow.LayoutParams.MATCH_PARENT));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                }

                //tv2.setGravity(Gravity.LEFT);

                //tv.setPadding(0, 0, 0, 0);
                if (i == 0) {
                    tvTitle.setPadding(10, 15, 0, 5);
                    //tv2.setText(String.valueOf(row.catWeight));
                    tvTitle.setText("Cat Name");
                    tvTitle.setTextColor(Color.parseColor("#000000"));
                    tvTitle.setBackgroundColor(Color.parseColor("#80145800"));
                    //tv.setBackgroundColor(Color.parseColor("#80a84908"));
                    //tv.setBackgroundColor(Color.parseColor("#f7f7f7"));
                } else {
                    tv.setPadding(10, 15, 0, 5);
                    //tv.setBackgroundColor(Color.parseColor("#20000000"));
                    tv.setBackgroundColor(Color.parseColor("#40306607"));
                    //tv.setBackgroundColor(Color.parseColor("#9145fd"));
                    //tv.setBackgroundColor(Color.parseColor("#ffffff"));
                    //tv.setTextColor(Color.parseColor("#000000"));
                    tv.setText(String.valueOf(row.catName));
                    tv.setTextColor(Color.parseColor("#000000"));
                }
                layCatName.addView(tv);

                final TextView tvb = new TextView(CatsActivity.this);

//                    if(tv2b.getParent()!=null)
//                        ((ViewGroup)tv2b.getParent()).removeView(tv2b);

                if (i > 0) {
                    //tvb.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    //TableRow.LayoutParams.WRAP_CONTENT));
                    tvb.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tvb.setBackgroundColor(Color.parseColor("#40133603"));
                    tvb.setGravity(Gravity.BOTTOM);
                    tvb.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    tvb.setPadding(10, 15, 0, 5);
                    //tvb.setBackgroundColor(Color.parseColor("#6ba814"));
                    //tvb.setBackgroundColor(Color.parseColor("#ffffff"));
                    //tvb.setTextColor(Color.parseColor("#000000"));
                    tvb.setText("");
                    layCatName.addView(tvb);
                    //tv2b.setText(String.valueOf(row.catPounds));
                }




                final LinearLayout layCatWeight = new LinearLayout(CatsActivity.this);
                layCatWeight.setOrientation(LinearLayout.VERTICAL);
                layCatWeight.setPadding(0, 0, 0, 0);
                //layCatWeight.setPadding(0, 10, 0, 10);
                layCatWeight.setBackgroundColor(Color.parseColor("#40133603"));

                final TextView tv2Title = new TextView(CatsActivity.this);
                final TextView tv2 = new TextView(CatsActivity.this);

//                    if(tv2.getParent()!=null)
//                        ((ViewGroup)tv2.getParent()).removeView(tv2);

                if (i == 0) {
                    tv2Title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv2Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                } else {
                    tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                }

                //tv2.setGravity(Gravity.LEFT);



                if (i == 0) {
                    tv2Title.setPadding(10, 15, 0, 5);
                    tv2Title.setGravity(Gravity.TOP);
                    //tv2.setText(String.valueOf(row.catWeight));
                    tv2Title.setText("Cat Weight");
                    tv2Title.setTextColor(Color.parseColor("#000000"));
                    tv2Title.setBackgroundColor(Color.parseColor("#80145800"));
                } else {
                    tv2.setBackgroundColor(Color.parseColor("#40306607"));
                    tv2.setTextColor(Color.parseColor("#000000"));
                    tv2.setText(String.valueOf(row.catWeight));
                    tv2.setPadding(10, 15, 0, 5);
                    tv2.setGravity(Gravity.TOP);
                }
                layCatWeight.addView(tv2);

                final TextView tv2b = new TextView(CatsActivity.this);

//                    if(tv2b.getParent()!=null)
//                        ((ViewGroup)tv2b.getParent()).removeView(tv2b);

                if (i > 0) {
                    tv2b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv2b.setGravity(Gravity.BOTTOM);
                    tv2b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    tv2b.setPadding(10, 15, 0, 5);
                    tv2b.setBackgroundColor(Color.parseColor("#40133603"));
                    tv2b.setTextColor(Color.parseColor("#000000"));
                    tv2b.setText(row.catPounds);
                    layCatWeight.addView(tv2b);
                    //tv2b.setText(String.valueOf(row.catPounds));
                }


                //tv2.setGravity(Gravity.LEFT);
                final LinearLayout layCatHeight = new LinearLayout(CatsActivity.this);
                layCatHeight.setOrientation(LinearLayout.VERTICAL);
                layCatHeight.setPadding(0, 0, 0, 0);
                layCatHeight.setBackgroundColor(Color.parseColor("#40133603"));

                final TextView tv3Title = new TextView(CatsActivity.this);
                final TextView tv3 = new TextView(CatsActivity.this);


//                    if(tv3.getParent()!=null)
//                        ((ViewGroup)tv3.getParent()).removeView(tv3);

                if (i == 0) {
                    tv3Title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv3Title.setPadding(10, 15, 0, 5);
                    tv3Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                    tv3.setGravity(Gravity.TOP);
                } else {
                    tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv3.setPadding(10, 15, 0, 5);
                    tv3.setGravity(Gravity.TOP);
                    tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                }

                //tv3.setGravity(Gravity.BOTTOM);


                if (i == 0) {
                    //tv3.setText(String.valueOf(row.catHeight));
                    tv3Title.setText("Cat Height");
                    tv3Title.setGravity(Gravity.TOP);
                    tv3Title.setTextColor(Color.parseColor("#000000"));
                    tv3Title.setBackgroundColor(Color.parseColor("#80145800"));
                } else {
                    tv3.setBackgroundColor(Color.parseColor("#40306607"));
                    tv3.setTextColor(Color.parseColor("#000000"));
                    //tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                    tv3.setText(row.catHeight);
                }

                layCatHeight.addView(tv3);

                final TextView tv3b = new TextView(CatsActivity.this);

//                    if(tv3b.getParent()!=null)
//                        ((ViewGroup)tv3b.getParent()).removeView(tv3b);

                if (i > 0) {
                    tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv3b.setGravity(Gravity.BOTTOM);
                    tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    tv3b.setPadding(10, 15, 0, 5);
                    tv3b.setBackgroundColor(Color.parseColor("#40133603"));
                    tv3b.setTextColor(Color.parseColor("#000000"));
                    tv3b.setText(String.valueOf(row.catInches));
                    layCatHeight.addView(tv3b);
                }

                final LinearLayout layCatLifespan = new LinearLayout(CatsActivity.this);
                layCatLifespan.setOrientation(LinearLayout.VERTICAL);
                layCatLifespan.setPadding(0, 0, 0, 0);
                layCatLifespan.setBackgroundColor(Color.parseColor("#40133603"));

                final TextView tv4Title = new TextView(CatsActivity.this);
                final TextView tv4 = new TextView(CatsActivity.this);

//                    if(tv4.getParent()!=null)
//                        ((ViewGroup)tv4.getParent()).removeView(tv4);

                if (i == 0) {
                    tv4Title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv4Title.setPadding(10, 15, 0, 5);
                    tv4Title.setGravity(Gravity.TOP);
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
                    //tv4.setText(String.valueOf(row.catLifespan));
                    tv4Title.setText("Cat Lifespan");
                    tv4Title.setTextColor(Color.parseColor("#000000"));
                    //"#E6f7f7f7"
                    //tv4.setBackgroundColor(Color.parseColor("#F2f7f7f7"));
                    tv4Title.setBackgroundColor(Color.parseColor("#80145800"));
                    tv4Title.setTextSize(TypedValue.COMPLEX_UNIT_PX, largeTextSize);
                } else {
                    tv4.setBackgroundColor(Color.parseColor("#40306607"));
                    tv4.setTextColor(Color.parseColor("#000000"));
                    tv4.setText(String.valueOf(row.catLifespan));
                    tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                }
                layCatLifespan.addView(tv4);

                final TextView tv4b = new TextView(CatsActivity.this);

//                    if(tv4b.getParent()!=null)
//                        ((ViewGroup)tv4b.getParent()).removeView(tv4b);

                if (i > 0) {
                    tv4b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.MATCH_PARENT));
                    tv4b.setGravity(Gravity.BOTTOM);
                    tv4b.setTextSize(TypedValue.COMPLEX_UNIT_PX, mediumTextSize);
                    tv4b.setPadding(10, 15, 0, 5);
                    tv4b.setBackgroundColor(Color.parseColor("#40133603"));
                    tv4b.setTextColor(Color.parseColor("#000000"));
                    tv4b.setText(String.valueOf(row.catYears));
                    layCatLifespan.addView(tv4b);
                }




                // add table row

                final TableRow tr = new TableRow(CatsActivity.this);
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
                    tr.addView(layCatName);
                    tr.addView(layCatWeight);
                    tr.addView(layCatHeight);
                    tr.addView(layCatLifespan);
                }




                //create array of arrays holding text views
                int catsRow = 4;





                mTableLayout.addView(tr, trParams);
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




