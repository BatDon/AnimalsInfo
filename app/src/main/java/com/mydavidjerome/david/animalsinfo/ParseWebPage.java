package com.mydavidjerome.david.animalsinfo;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static com.mydavidjerome.david.animalsinfo.MainActivity.CENTER_COLUMN;
import static com.mydavidjerome.david.animalsinfo.MainActivity.LEFT_COLUMN;
import static com.mydavidjerome.david.animalsinfo.MainActivity.RIGHT_COLUMN;


public class ParseWebPage{
    Document doc;
    int numberOfDogs;

    int leftColumn;
    int middleColumn;
    int rightColumn;
    Bundle columnBundle;


    public ParseWebPage(Document doc){
        this.doc=doc;
    }

    public String[] makeArray(){
        Element e5 = doc.select("div.entry-content").first();
        Element dogGroup = e5.child(1);
        Log.i("dogHomePage dogGroup="," "+dogGroup);
        Elements dogList = dogGroup.children();
        Log.i("dogHomePage dogList="," "+dogList);
        //Element e7 = e5.child(2);
        //Element e8 = e5.child(3);


//                MAKE AN ARRAY HERE
        numberOfDogs = 0;
        String[] dogArray = new String[dogList.size()];
        //ArrayList<Element> arrayElements = new ArrayList<>();
        for (Element dog : dogList) {
            dogArray[numberOfDogs] = (dog.text());
            Log.i("dog: "+numberOfDogs, "" + (dogArray[numberOfDogs]));
            numberOfDogs += 1;
        }
        return dogArray;

    }
    public Bundle threeColumns(String[] dogArray){
        int remainingDogs = (numberOfDogs) % 3;
        Log.i("remainingDogs",""+remainingDogs);
        int dogsQuotient=numberOfDogs/3;
        Log.i("dogsQuotient",""+dogsQuotient);

        if(remainingDogs==0){
            leftColumn=dogsQuotient;
            middleColumn=dogsQuotient;
            rightColumn=dogsQuotient;
        }
        else if(remainingDogs==1){
            leftColumn=dogsQuotient+1;
        }
//remainingDogs=2
        else {
            leftColumn=dogsQuotient+1;
            middleColumn=dogsQuotient+1;
        }
        columnBundle=new Bundle();
        columnBundle.putInt(LEFT_COLUMN,leftColumn);
        columnBundle.putInt(CENTER_COLUMN,middleColumn);
        columnBundle.putInt(RIGHT_COLUMN,rightColumn);
        return columnBundle;
    }




}
