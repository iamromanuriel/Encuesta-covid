package com.estadia.encuesta.data.repository;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ViewModelScoped;

@Singleton
public class SignInRepository  {
    private Context context;

    @Inject
    public SignInRepository(@ApplicationContext Context context) {
        this.context = context;
    }

    public String getImei(){
        final TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            return telephonyManager.getImei();
        }else{
            return telephonyManager.getDeviceId();
        }

    }


    public void signIn(String imei, String name){

    }
}
