package com.mydavidjerome.david.animalsinfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DogsData {


    static String[][] dogArrayTB = new String[16][7];
    //String[][] dogArrayTB = new String[16][10];
    //String[][] dogArrayTB = new String[30][10];
    String lines[];
    //String[]checksRows={};
    //int ar = 0;
    public String [][] getDogsStatsArray(String url2){
        //public static String[][] dogArrayTB = new String[16][7];
        //String[][] dogFormattedArray2=new String[row][column];
        String[][] dogFormattedArray2=new String[7][7];
        //public String[] returnStringArray2() {


            try {

                //String url = getResources().getString(R.string.url2);
                //String url = "@string/url";
                Document doc = Jsoup.connect(url2).get();
                Elements elements = doc.body().select("*");
                //Log.i("FormatDatabase", " " + elements);
                Element dogElement= doc.select("#dogPara").first();
                Elements dogElements3= doc.select("#dogPara");
                //Log.i("dogElement",""+dogElements3);
                String dogText3=dogElements3.html();
                //Log.i("dogAsHTML",""+dogText3);
                dogElement.removeAttr("<p>");
                //Elements dogElements = doc.select("#dogPara");
                //Element dogElement2=dogElement.child(1);
               // Log.i("dogElement2",""+dogElement2);
                //Log.i("dogElement2",""+dogElement2.toString());
                //Elements dogElements=dogElement2.children();
//                Elements dogElements=dogElement.siblingElements();
//                Log.i("dogElements",""+dogElements);
//                Elements dogElements2=dogElement.children();
//                Log.i("dogElements2",""+dogElements2);
//                String dogText2=dogElements.html();
                Element e5 = doc.select("body").first();
//                Log.i("FDe5 ", "" + e5);
                //elements of e5 is the same as e5
                //Elements nes =doc.select("body ~ br");
                Elements nes = doc.select("body");
                //String dogText = elements.html();
                //String dogText = dogElements.html();
                //String dogText = dogElements.html();
                //String dogText =e5.html();


                dogText3 = dogText3.replaceAll("<br>", "\n");
                //dogText2.replaceAll("<p>","");
                //Whitelist dogWhitelist = new Whitelist();
                //dogWhitelist.addTags("br");
                //Jsoup.clean(fragment, dogWhitelist);
                //Log.i("dogHTML", "" + dogText2);
                Character c1;
                StringBuilder sb = new StringBuilder();
                //int ar=0;
                //String[][] dogArrayTB = new String[30][10];
                String fullName = "";
                //for(int i=0;i<13;i++) {
                //String lines[] = dogText.split("\\r?\\n");
                lines = dogText3.split("\\r?\\n");
//                if (lines[4].isEmpty()){
//                    Log.i("linesERROR",""+lines[0]);
//                }

//                for (Element eDog:dogElements)
//                {
//                    lines=eDog.toString().split("\\s");
//                }
                //lines=dogElements.split("\\r?\\n");


                //Log.i("lines[0]", "" + lines[0]);
                //Log.i("lines[2]", "" + lines[2]);
                //Log.i("lines[4]", "" + lines[4]);
                //Log.i("lines[6]", "" + lines[6]);
                //}
                String lines2[] = dogText3.split("\\n");
                //Log.i("lines2[0]", "" + lines2[0]);
                //Log.i("lines2[4]", "" + lines2[4]);
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
                //for (int ln = 0; ln < (lines.length); ln += 2, ar++) {
                for (int ln = 0; ln < (lines.length); ln += 1, ar++) {
                    //Log.i("ln", "" + ln);
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
                            //Log.i("words[ln2]", " " + words[ln2]);
                            //Log.i("ln2=", " " + ln2);

//                            if(firstRow3<1&&firstRow==2){
//                                firRowStr.append(words[ln2+1]);
//                                firstRow3=3;
//                                continue;
//                            }
                            if (firstRow < 1) {
                                firRowStr.append(words[ln2]);
                                firRowStr.append(" ");
                                firRowStr.append(words[ln2 + 1]);
                                //Log.i("firRowStr", " " + firRowStr);
                                //Log.i("2 words[ln2]", " " + words[ln2]);
                                //Log.i("2 ln2=", " " + ln2);
                                dogArrayTB[ar][0] = firRowStr.toString();
                                firRowStr.setLength(0);
                                ++ln2;
                                //ln2++;
                                firstRow = 2;
                                continue;
                            } else {
                                //ncol+=2;
                                //Log.i("firRowStr", " " + firRowStr);
                                //Log.i("3 words[ln2]", " " + words[ln2]);
                                //Log.i("3 ln2=", " " + ln2);
                                dogArrayTB[ar][ln2-1] = words[ln2];
                                //dogArrayTB[ar][ln2] = words[ln2];
                                firRowStr.setLength(0);
                                endFirst++;
                                //Log.i("ar=", "" + ar);
//                                if (endFirst == 2) {
//                                    //ar++;
//                                    ++ar;
//                                }
                                //Log.i("elseFR", "" + firRowStr);
                            }
                        }
                    } else {
                        int arrPlace = 0;
                        if (words.length == 8) {
                            for (ln2 = 0; ln2 < (words.length) && ar > 0; ln2++) {
                                if (arrPlace < 1) {
                                    worStr = worStr.append(words[ln2]);
                                    worStr.append(" ");
                                    worStr.append(words[ln2+1]);
                                    //Log.i("firRowStr", " " + firRowStr);
                                    //Log.i("$0 words[ln2]", " " + words[ln2]);
                                    //Log.i("$0 ln2=", " " + ln2);
                                    dogArrayTB[ar][0] = worStr.toString();
                                    worStr.setLength(0);
                                    arrPlace = 2;
                                    
                                    ++ln2;

                                    //checksRows[forEight]="8";
                                    //ln2++;
                                    continue;
                                } else {
                                    dogArrayTB[ar][ln2-1] = words[ln2];
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



                for (int i = 0; i < dogArrayTB.length; i++) {
                    for (int j = 0; j < dogArrayTB[i].length; j++) {
                        //Log.i("wholeAr", "Values [" + i + "][" + j + "] is " + dogArrayTB[i][j]);
                    }
                }









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
//                Log.i("dogHomePage e6="," "+e6);
//                Element e6 = e5.child(1);
//                Log.i("dogHomePage e6="," "+e6);
//                Elements e9 = e6.children();
//                Log.i("FD e6", ""+e9);
                    Elements e9 = e5.children();
                    //Log.i("dogHomePage e9=", " " + e9.text());
                    //Elements e9 = e6.children();
                    //Log.i("FD e9", "" + e9);
                    int numberOfDogs2 = 0;



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



        //checksRows will tell which rows have 8 which means two names for the dogs
        //create an array that iterates through and assigns values to the variables below based on one or two names



    public com.mydavidjerome.david.animalsinfo.DogsAddData[] getInfo() {
        //Log.i("getInfost","getInfostarted");
        com.mydavidjerome.david.animalsinfo.DogsAddData[] data = new com.mydavidjerome.david.animalsinfo.DogsAddData[dogArrayTB.length];

        for (int i = 0; i < dogArrayTB.length; i++) {
            com.mydavidjerome.david.animalsinfo.DogsAddData row = new com.mydavidjerome.david.animalsinfo.DogsAddData();
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


                //iterates through columns
                row.dogName = dogArrayTB[i][j];
                row.dogWeight =dogArrayTB[i][j+1];
                row.dogPounds = dogArrayTB[i][j+2];
                row.dogHeight = dogArrayTB[i][j+3];
                row.dogInches = dogArrayTB[i][j+4];
                row.dogLifespan = dogArrayTB[i][j+5];
                row.dogYears = dogArrayTB[i][j+6];
                //Log.i("i in for"," "+i);
                //Log.i("row.dogName"," "+dogArrayTB[i][j]);

//creates row with data
                data[i] = row;

                //data[i][j] = row;
            }
        return data;
        }
        //return data;

    }








