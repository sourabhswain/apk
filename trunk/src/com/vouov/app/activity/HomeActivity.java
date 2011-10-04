package com.vouov.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.vouov.bean.MyGEO;
import com.vouov.exception.SVException;
import com.vouov.util.HttpUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.v("YUML","******************************************");
        JSONObject json = new JSONObject();
        try {
            json.put("provider", "provider1");
            json.put("longitude", 1.10);
            json.put("latitude", 2.20);
            //MyGEO myGEO = HttpUtils.json2Bean(json, MyGEO.class);
            //Log.v("YUML","provider = "+myGEO.getProvider()+",longitude = "+myGEO.getLongitude()+",latitude = "+myGEO.getLatitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("YUML","******************************************");
    }
}
