package com.mydavidjerome.david.animalsinfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;

import org.jsoup.nodes.Document;

import java.util.AbstractMap;

import static com.mydavidjerome.david.animalsinfo.MainActivity.CENTER_COLUMN;
import static com.mydavidjerome.david.animalsinfo.MainActivity.LEFT_COLUMN;
import static com.mydavidjerome.david.animalsinfo.MainActivity.RIGHT_COLUMN;

public class GetHandlerThread extends HandlerThread {
    private static final String TAG = "GetHandlerThread";

    public static final int DOGS_TOP_TASK = 1;
    public static final int CATS_TOP_TASK = 2;
    public static final int FROGS_TOP_TASK = 3;

    private Handler handler;

    public GetHandlerThread() {
        super("GetHandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case DOGS_TOP_TASK:
                        Log.d(TAG, "Example Task, arg1: " + msg.arg1 + ", obj: " + msg.obj);
//                        Document doc=new ConnectionClass().getDoc(GlobalApplication.getAppContext());
                     //   msg.obj
                     //   AbstractMap.SimpleEntry<String, Context> entry=msg.obj;
//                        Document doc=new ConnectionClass().getDoc(msg.obj);
                        Document doc=new ConnectionClass().getDoc();
                        ParseWebPage parseWebPage=new ParseWebPage(doc);
                        String[] dogArray=parseWebPage.makeArray();
                        Bundle threeColumns=parseWebPage.threeColumns(dogArray);
//                        threeColumns.getInt(LEFT_COLUMN);
//                        threeColumns.getInt(CENTER_COLUMN);
//                        threeColumns.getInt(RIGHT_COLUMN);
                        break;
                    default:
                        Log.d(TAG,"ERROR no task");
                        break;
                }
            }
        };
    }

    public Handler getHandler() {
        return handler;
    }
}