package com.example.david.animalsinfo;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DogsData {


    String[][] dogArrayTB = new String[30][10];
    String lines[];
    //String[]checksRows={};
    //int ar = 0;
    public String [][] getDogsStatsArray(String url2){
        //String[][] dogFormattedArray2=new String[row][column];
        String[][] dogFormattedArray2=new String[7][7];
        //public String[] returnStringArray2() {


            try {

                //String url = getResources().getString(R.string.url2);
                //String url = "@string/url";
                Document doc = Jsoup.connect(url2).get();
                Elements elements = doc.body().select("*");
                Log.i("FormatDatabase", " " + elements);
                Element e5 = doc.select("body").first();
                Log.i("FDe5 ", "" + e5);
                //elements of e5 is the same as e5
                //Elements nes =doc.select("body ~ br");
                Elements nes = doc.select("body");
                String dogText = elements.html();
                //String dogText =e5.html();
                dogText = dogText.replaceAll("<br>", "\n");
                Log.i("dogHTML", "" + dogText);
                Character c1;
                StringBuilder sb = new StringBuilder();
                //int ar=0;
                //String[][] dogArrayTB = new String[30][10];
                String fullName = "";
                //for(int i=0;i<13;i++) {
                //String lines[] = dogText.split("\\r?\\n");
                lines = dogText.split("\\r?\\n");


                Log.i("lines[0]", "" + lines[0]);
                Log.i("lines[2]", "" + lines[2]);
                Log.i("lines[4]", "" + lines[4]);
                Log.i("lines[6]", "" + lines[6]);
                //}
                String lines2[] = dogText.split("\\n");
                Log.i("lines2[0]", "" + lines2[0]);
                Log.i("lines2[4]", "" + lines2[4]);
                String regex = "[A-Z]&&[a-z]";
                //int ncol=0;
                int ar = 0;
                int ln2 = 0;
                int firstRow = 0;
                int firstRow2 = 0;
                int firstRow3 = 0;
                //String[]checksRows={};
                //checksRows={};
                //int forEight=0;
                for (int ln = 0; ln < (lines.length); ln += 2, ar++) {
                    Log.i("ln", "" + ln);
                    String sg = lines[ln];
                    String[] words = sg.split("\\s+");
                    int wd = 0;
                    int o2 = 0;
                    int endFirst = 0;
                    StringBuilder worStr = new StringBuilder();
                    StringBuilder worStr2 = new StringBuilder();
                    StringBuilder firRowStr = new StringBuilder();
//                    if (ncol>0){
//                        ar++;}
                    if (ar == 0) {
                        for (ln2 = 0; ln2 < (words.length) && ar == 0; ln2++) {
                            Log.i("words[ln2]", " " + words[ln2]);
                            Log.i("ln2=", " " + ln2);

//                            if(firstRow3<1&&firstRow==2){
//                                firRowStr.append(words[ln2+1]);
//                                firstRow3=3;
//                                continue;
//                            }
                            if (firstRow < 1) {
                                firRowStr.append(words[ln2]);
                                firRowStr.append(" ");
                                firRowStr.append(words[ln2 + 1]);
                                Log.i("firRowStr", " " + firRowStr);
                                Log.i("2 words[ln2]", " " + words[ln2]);
                                Log.i("2 ln2=", " " + ln2);
                                dogArrayTB[ar][0] = firRowStr.toString();
                                firRowStr.setLength(0);
                                ++ln2;
                                //ln2++;
                                firstRow = 2;
                                continue;
                            } else {
                                //ncol+=2;
                                Log.i("firRowStr", " " + firRowStr);
                                Log.i("3 words[ln2]", " " + words[ln2]);
                                Log.i("3 ln2=", " " + ln2);
                                dogArrayTB[ar][ln2 - 1] = words[ln2];
                                //dogArrayTB[ar][ln2] = words[ln2];
                                firRowStr.setLength(0);
                                endFirst++;
                                Log.i("ar=", "" + ar);
//                                if (endFirst == 2) {
//                                    //ar++;
//                                    ++ar;
//                                }
                                Log.i("elseFR", "" + firRowStr);
                            }
                        }
                    } else {
                        int arrPlace = 0;
                        if (words.length == 8) {
                            for (ln2 = 0; ln2 < (words.length) && ar > 0; ln2++) {
                                if (arrPlace < 1) {
                                    worStr = worStr.append(words[ln2]);
                                    worStr.append(" ");
                                    worStr.append(words[ln2 + 1]);
                                    Log.i("firRowStr", " " + firRowStr);
                                    Log.i("$0 words[ln2]", " " + words[ln2]);
                                    Log.i("$0 ln2=", " " + ln2);
                                    dogArrayTB[ar][0] = worStr.toString();
                                    worStr.setLength(0);
                                    arrPlace = 2;

                                    ++ln2;

                                    //checksRows[forEight]="8";
                                    //ln2++;
                                    continue;
                                } else {
                                    dogArrayTB[ar][ln2 - 1] = words[ln2];
                                    //dogArrayTB[ar][ln2 - 1] = worStr.toString();
                                    continue;
                                }
                            }
                        } else {
                            for (ln2 = 0; ln2 < (words.length) && ar > 0; ln2++) {
                                dogArrayTB[ar][ln2] = words[ln2];
                            }
                        }
                    }
                }


//                            if(firstRow3<1&&firstRow==2){
//                                firRowStr.append(words[ln2+1]);
//                                firstRow3=3;
//                                continue;
//                            }
                // if (firstRow2 < 1) {


//                            worStr.append(words[ln2]);
//                            char zeroCh = words[ln2].charAt(0);
////                            if (words[ln2+1]!=null){
////                                char firstCh = words[ln2 + 1].charAt(0);
////                            }
//                            if (ln2 + 1 >= words.length) {
//                                Log.i("firRowStr", " " + firRowStr);
//                                Log.i("$0 words[ln2]", " " + words[ln2]);
//                                Log.i("$0 ln2=", " " + ln2);
//                                dogArrayTB[ar][ln2 - 1] = worStr.toString();
//                                worStr.setLength(0);
//                                continue;
//                            } else {
//                                char firstCh = words[ln2 + 1].charAt(0);
//
//
//                                //char firstCh = words[ln2 + 1].charAt(0);
//                                if (Character.isLetter(firstCh) && Character.isLetter(zeroCh)) {
//                                    worStr.append(" ");
//                                    worStr.append(words[ln2 + 1]);
//                                    Log.i("$1firRowStr", " " + firRowStr);
//                                    Log.i("$1 words[ln2]", " " + words[ln2]);
//                                    Log.i("$1 ln2=", " " + ln2);
//                                    dogArrayTB[ar][0] = worStr.toString();
//                                    worStr.setLength(0);
//                                    ln2++;
//                                    continue;
//                                } else {
//                                    //ncol+=2;
//                                    Log.i("$firRowStr", " " + firRowStr);
//                                    Log.i("$3 words[ln2]", " " + words[ln2]);
//                                    Log.i("$3 ln2=", " " + ln2);
//                                    if(ln2>0) {
//                                        dogArrayTB[ar][ln2 - 1] = worStr.toString();
//                                        worStr.setLength(0);
//                                        continue;
//                                    }
//                                    else{
//                                        dogArrayTB[ar][ln2] = worStr.toString();
//                                        worStr.setLength(0);
//                                        continue;
//
//                                    }
//                                }
//                            }
////                                if (ln2 == 0) {
////                                    dogArrayTB[ar][ln2 - 1] = worStr.toString();
////                                    worStr.setLength(0);
////                                    continue;
////                                } else {
////                                    dogArrayTB[ar][ln2 - 1] = worStr.toString();
////                                    //dogArrayTB[ar][ln2] = words[ln2];
////                                    firRowStr.setLength(0);
////                                    Log.i("elseFR", "" + firRowStr);
////                                    continue;
////                                }
//                        }
//                        // }
//                    }
//                }


//                        if(firstRow>0) {
//                            dogArrayTB[ar][ln2] = firRow.toString();
//                            firRow.setLength(0);
//                            continue;
//
//                        }

//                        if (words[ln2].equals("\\n")) {
//                            ln2++;
//                            ar++;}
//                        if(firstRow2<1) {
//                           //firRow.append(" ");
//                           //firRow.append(ln2);
//                           firstRow2=3;
//                           //dogArrayTB[0][0] = firRow.toString();
//                            //Dog Breed now at first column of first row
//                            dogArrayTB[ar][0] = firRowStr.toString();
////                            if(ar>0&&ln2>0){
////                                dogArrayTB[0][0] = firRow.toString();
////                            }
//                            //dogArrayTB[ar][ln2-1] = firRow.toString();
//                            firRowStr.setLength(0);
//                            Log.i("ifFR",""+firRowStr);
//                            continue;
                //firstRow+=3;
                //                          }
//                        else {
//                            //ncol+=2;
//                            dogArrayTB[ar][ln2-1] = firRowStr.toString();
//                            firRowStr.setLength(0);
//                            Log.i("elseFR",""+firRowStr);
//                        }


//                    for (ln2 = 0; ln2 < (words.length); ln2 ++) {
//                        Log.i("ln2",""+ln2);
//                        Log.i("words[ln2]",""+words[ln2]);
//                        Log.i("dogArrayTB-1="," "+dogArrayTB[ar][ln2]);
//                        //if (words[ln2].equals("\n")) {
////                        if (ln2==8) {
////                            ln2++;
////                            ar++;
////                            continue;
////                        }
//                        char firstCh = words[ln2].charAt(0);
//                        //char secondCh=words[ln2].charAt(0);
//                        //if(words[ln2].charAt(0).equals(regex)){
//                        if (worStr.toString().isEmpty() && Character.isDigit(firstCh)) {
//                            dogArrayTB[ar][ln2] = words[ln2];
//                            Log.i("dogArrayTB0="," "+dogArrayTB[ar][ln2]);
//                            //dogArrayTB[ar][ln2]=words[ln2];
//                            continue;
//                        }
//                        if (worStr.toString().isEmpty() && Character.isLetter(firstCh)) {
//                            worStr.append(words[ln2]);
//                            Log.i("dogArrayTB1="," "+dogArrayTB[ar][ln2]);
//                            //dogArrayTB[ar][ln2]=words[ln2];
//                            continue;
//                        }
//                        //worStr.toString() == null &&
//                        //Character.isLetter(firstCh)
//                        //worStr.append(words[ln2]);}
//                        //Character.isLetter(firstCh)
//                        if (!worStr.toString().isEmpty() && Character.isLetter(firstCh)) {
//                            if (Character.isLetter(firstCh)) {
//                                worStr2.append(words[ln2]);
//                                String combStr = worStr.toString() + " " + worStr2.toString();
//                                dogArrayTB[ar][ln2] = combStr;
//                                worStr.setLength(0);
//                                worStr2.setLength(0);
//                                combStr = "";
//                                Log.i("dogArrayTB3=", " " + dogArrayTB[ar][ln2]);
//                                continue;
//                                //dogArrayTB[ar][ln2] = words[ln2];
//
//                                //worStr.setLength(0);
//                            } else {
//                                dogArrayTB[ar][ln2] = worStr.toString();
//                                Log.i("dogArrayTB4="," "+dogArrayTB[ar][ln2]);
//                                worStr.setLength(0);
//                                continue;
//                            }
//                        }
//                        else{
//                            dogArrayTB[ar][ln2]=worStr.toString();
//                            worStr.setLength(0);
//                            Log.i("dogArrayTB5="," "+dogArrayTB[ar][ln2]);
//                            continue;
//                        }
//
//                    }
////                        if (worStr.toString().isEmpty())
////                        if (worStr.toString().)
////                        if(o2==1 && Character.isLetter(firstCh)){
////                            worStr.append(words[ln2]+" ");
////                            dogArrayTB[ar][ln2]=worStr.toString();
////                            worStr.setLength(0);
////                            o2=0;
////                            Log.i("dogArrayTB1=",""+dogArrayTB[ar][ln2]);
////                            }
////                            else{
////                            dogArrayTB[ar][ln2]=worStr.toString();
////                            Log.i("dogArrayTB2=",""+dogArrayTB[ar][ln2]);
////                            worStr.setLength(0);
////                            o2=0;
////                            }
////
////
////                        o2++;
////                        //dogArrayTB[ar][ln2] = words[ln2];
////                        Log.i("dogArrayTB=",""+dogArrayTB[ar][ln2]);
////                        //wd++;
////                        Log.i("inWor", "" + words[ln2]);
////                        //ln2++;
////
//////                        if(ln2==8){
//////                            ln2=0;
//////                        }
////                    }
////                    ar++;
//                }


//                for (int i = 0; i < dogArrayTB.length; i++) {
//                    for (int j = 0; j < dogArrayTB[i].length; j++) {
//                        Log.i("wholeAr", "Values [" + i + "][" + j + "] is " + dogArrayTB[i][j]);
//                    }
//                }


                //now regex pattern string space string
                //if string space string then add to array
                //String [] splitted = sb.toString.split("\\s+");
                //else string space string then add to array
//                for (int i = 0; i < dogText.length(); i++) {
//
//                    sb.append(dogText.charAt(i));
//                    c1=dogText.charAt(i);
//                    if(c1.equals("\n")) {
//                        Log.i("array[0]",""+sb.toString());
//                        dogArrayTB[ar][0] = sb.toString();
//                        //String [] splitted = sb.toString.split("\\s+");
//                        //if splitted[0].equalsIgnoreCase("abcdefghijklmnopqrstuvwxyz")&& splitted[1].equalsIgnoreCase("abcdefghijklmnopqrstuvwxyz"){
//                        // fullName=splitted[0]+splitted[1]}
//                        Log.i("htmlarray",""+dogArrayTB[ar][0]);
//
//                        //array that goes up one everytime to build an array of strings
//                        ar++;
//                    }
//
//
//                }

//                Element e6 = e5.child(1);
//                Log.i("dog e6="," "+e6);
//                Element e6 = e5.child(1);
//                Log.i("dog e6="," "+e6);
//                Elements e9 = e6.children();
//                Log.i("FD e6", ""+e9);
                Elements e9 = e5.children();
                Log.i("dog e9=", " " + e9.text());
                //Elements e9 = e6.children();
                Log.i("FD e9", "" + e9);
                int numberOfDogs2 = 0;
                //String[][] dogArrayTB = new String[100][7];
                //ArrayList<Element> arrayElements = new ArrayList<>();
                //pattern to use if second element is "abcdefghijklmnopqrstuvwxyz" then join first and second element
                //for (Element e : nes) {
                //String[] splitted=new String[7];

//                for(String s:dogText){
//
//                }

//                int yu=0;
//                for (Element e : elements) {
//                    Log.i("eOFe9",""+e);
//                    Log.i("#times", ""+yu);
//                    yu++;
//                    Log.i("new","newlog message");
//                    //String[] s=e.text().split("\\s+");
//                    String [] splitted = e.text().split("\\s+");
//                    Log.i("dogArray",""+splitted[0]+splitted[1]+splitted[2]);
//                    numberOfDogs2 += 1;
//                    //dogArrayTB[numberOfDogs2] = (e.text());
//                    dogArrayTB[numberOfDogs2] = splitted;
//
//                    Log.i("eachDogHereTB: ", "" + (dogArrayTB[numberOfDogs2][0])+""+(dogArrayTB[numberOfDogs2][1]));
//                }


                Log.i("in Element", "paragraph of dogs");


                Log.i("JSoup", "Connected successfully!");

                for (int i = 0; i < dogArrayTB.length; i++) {
                    for (int j = 0; j<dogArrayTB[i].length; j++) {
                    //for (int j = 0; j < 8; j++) {

                        Log.i("wholeAr", "Values [" + i + "][" + j + "] is " + dogArrayTB[i][j]);
                    }
                }
            }




            catch (IOException e) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Error : ").append(e.getMessage()).append("\n");
                Log.i("HTML ERROR", "exception reading HTML");
            }
            //return dogTextLeft;
            return dogFormattedArray2;
        }



        //checksRows will tell which rows have 8 which means two names for the dogs
        //create an array that iterates through and assigns values to the variables below based on one or two names



    public com.example.david.animalsinfo.DogsAddData[] getInfo() {
        Log.i("getInfost","getInfostarted");
        //com.example.david.animalsinfo.DogsAddData[] data = new com.example.david.animalsinfo.DogsAddData[dogArrayTB.length];
        com.example.david.animalsinfo.DogsAddData[] data = new com.example.david.animalsinfo.DogsAddData[14];

        for (int i = 0; i < 14; i++) {
            //for (int i = 0; i < dogArrayTB.length; i++) {
            com.example.david.animalsinfo.DogsAddData row = new com.example.david.animalsinfo.DogsAddData();
//            if(checksRows[i].equals("8")){
//
//            }
            //for (int j = 0; j < dogArrayTB[i].length; j++) {
//            for (int j = 0; j < 8; j++) {
//
//                Log.i("wholeAr", "Values [" + i + "][" + j + "] is " + dogArrayTB[i][j]);
                //}
                //}
                //iterates through rows and sets data
                int j=0;

                //This is only for the first row
                if(i==0) {
                    row.dogName = dogArrayTB[i][j];
                    row.dogWeight = dogArrayTB[i][j + 1];
                    row.dogPounds = dogArrayTB[i][j + 2];
                    row.dogHeight = dogArrayTB[i][j + 3];

                    data[i]=row;
                }
                //iterates through columns
                //This is for all the other rows with information
                else {
                    row.dogName = dogArrayTB[i][j];
                    row.dogWeight = dogArrayTB[i][j + 1];
                    row.dogPounds = dogArrayTB[i][j + 2];
                    row.dogHeight = dogArrayTB[i][j + 3];
                    row.dogInches = dogArrayTB[i][j + 4];
                    row.dogLifespan = dogArrayTB[i][j + 5];
                    row.dogYears = dogArrayTB[i][j + 6];


//creates row with data
                    data[i] = row;
                    //data[i][j] = row;
                }
            }
        return data;
        }
        //return data;

    }

//    public com.example.david.animalsinfo.DogsAddData[] getInfo() {
//        com.example.david.animalsinfo.DogsAddData[] data = new com.example.david.animalsinfo.DogsAddData[20];
//
//
//
//
//        //iterates through rows and sets data
//        for (int i = 0; i < lines.length; i++) {
//            com.example.david.animalsinfo.DogsAddData row = new com.example.david.animalsinfo.DogsAddData();
//            //iterates through columns
//            row.dogName = ("dog " + i + 1);
//            row.dogWeight = ""+i * 8 ;
//            row.dogPounds = "pounds";
//            row.dogHeight = i * 2 + "";
//            row.dogInches = "inches";
//            row.dogLifespan = ""+i * 3;
//            row.dogYears = "years";
//
////creates row with data
//            data[i] = row;
//        }
//        return data;



//    public com.example.david.animalsinfo.DogsAddData[] getInfo() {
//        com.example.david.animalsinfo.DogsAddData[] data = new com.example.david.animalsinfo.DogsAddData[20];
//
//        //iterates through rows and sets data
//        for (int i = 0; i < 20; i++) {
//            com.example.david.animalsinfo.DogsAddData row = new com.example.david.animalsinfo.DogsAddData();
//            //iterates through columns
//            row.dogName = ("dog " + i + 1);
//            row.dogWeight = ""+i * 8 ;
//            row.dogPounds = "pounds";
//            row.dogHeight = i * 2 + "";
//            row.dogInches = "inches";
//            row.dogLifespan = ""+i * 3;
//            row.dogYears = "years";
//
////creates row with data
//            data[i] = row;
//        }
//        return data;
//
//    }


