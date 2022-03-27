package com.estadia.encuesta.Model;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class GetImeiDevice {
    Context context;

    public GetImeiDevice(Context context){
        this.context = context;
    }

    public String getDeviceID(){
        String result = "";

        String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        result = deviceId;
        return result;
    }

    public String getImeiNumber(){
        TelephonyManager tManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String deviceIMEI = tManager.getDeviceId();

        return  deviceIMEI;
    }
}
