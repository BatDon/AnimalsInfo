package com.mydavidjerome.david.animalsinfo;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static com.mydavidjerome.david.animalsinfo.R.string.url;


//NEED TO MAKE THIS SINGLETON
public class ConnectionClass{
    Document doc;


    public Document getDoc() {
        try {
//            if (context==null){
//                Log.i("ConnectionClass","getDoc context=null");
//            }


            //String url = context.getResources().getString(R.string.url);
            //String url = "@string/url";
            String url="https://mydavidjerome.com/android-app/";
            doc = Jsoup.connect(url).get();

        } catch (IOException e) {
            final StringBuilder builder = new StringBuilder();
            builder.append("Error : ").append(e.getMessage()).append("\n");
            //Log.i("HTML ERROR", "exception reading HTML");
        }
        return doc;

    }

}
