package com.estadia.encuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.estadia.encuesta.Model.Encuesta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ArrayList<Boolean> respuestas = new ArrayList<Boolean>();
    private RadioButton radiopsi,radiopno,radiossi,radiosno,radiotsi,radiotno,radiocsi,radiocno,radioqusi,radioquno,radiosexsi,radiosexno,radiosepsi,radiosepno,radiooctsi,radiooctno,radionovsi,radionovno,radiodecsi,radiodecno;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Encuesta encuestaSelected;

    private boolean dr,ts,dc,sn,dm,dg,po,dt,res,ccovid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radiopsi = findViewById(R.id.radiosip);
        radiopno = findViewById(R.id.radionop);
        radiossi = findViewById(R.id.radiosis);
        radiosno = findViewById(R.id.radionos);
        radiotsi = findViewById(R.id.radiosit);
        radiotno = findViewById(R.id.radionot);
        //
        radiocsi = findViewById(R.id.radiosicuart);
        radiocno = findViewById(R.id.radionocuart);
        radioqusi = findViewById(R.id.radiosiquin);
        radioquno = findViewById(R.id.radionoquin);
        radiosexsi = findViewById(R.id.radiosisext);
        radiosexno = findViewById(R.id.radionosext);
        radiosepsi = findViewById(R.id.radiosisept);
        radiosepno = findViewById(R.id.radionosept);
        radiooctsi = findViewById(R.id.radiosioct);
        radiooctno = findViewById(R.id.radionooct);
        radionovsi = findViewById(R.id.radiosinov);
        radionovno = findViewById(R.id.radiononov);
        radiodecsi = findViewById(R.id.radiosidec);
        radiodecno = findViewById(R.id.radionodec);
        iniciarFirebase();
    }
    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();

    }

    public void guardar (View v){
        Encuesta e = new Encuesta();

        primerPregunat();
        segundaPregunat();
        tercerPregunat();
        cuartaPregunat();
        quintaPregunat();
        sextaPregunat();
        septPregunat();
        octPregunat();
        novePregunat();
        decimPregunat();

        try {
            e.setEid(UUID.randomUUID().toString());
            e.setDrespirar(respuestas.get(0));
            e.setTosSec(respuestas.get(1));
            e.setDcabeza(respuestas.get(2));
            e.setSecnasal(respuestas.get(3));
            e.setDmuscular(respuestas.get(4));
            e.setDgarganta(respuestas.get(5));
            e.setPolfato(respuestas.get(6));
            e.setDtorasico(respuestas.get(7));
            e.setResfriado(respuestas.get(8));
            e.setCcovid(respuestas.get(9));
            databaseReference.child("Encuesta").child(e.getEid()).setValue(e);
            Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
        }catch (Exception c){
            Toast.makeText(this, "Ha ocurrido un problema al guardar su respuesta", Toast.LENGTH_SHORT).show();
        }




    }



    public void primerPregunat(){
        if(radiopsi.isChecked()){
            dr= true;
            respuestas.add(dr);
        }else if(radiopno.isChecked()){
            dr = false;
            respuestas.add(dr);
        }
    }

    public void segundaPregunat(){
        if(radiossi.isChecked()){
            ts= true;
            respuestas.add(ts);
        }else if(radiosno.isChecked()){
            ts = false;
            respuestas.add(ts);
        }
    }

    public void tercerPregunat(){
        if(radiotsi.isChecked()){
            dc= true;
            respuestas.add(dc);
        }else if(radiotno.isChecked()){
            dc = false;
            respuestas.add(dc);
        }
    }

    public void cuartaPregunat(){
        if(radiotsi.isChecked()){
            sn= true;
            respuestas.add(sn);
        }else if(radiotno.isChecked()){
            sn = false;
            respuestas.add(sn);
        }
    }

    public void quintaPregunat(){
        if(radiotsi.isChecked()){
            dm= true;
            respuestas.add(dm);
        }else if(radiotno.isChecked()){
            dm = false;
            respuestas.add(dm);
        }
    }

    public void sextaPregunat(){
        if(radiotsi.isChecked()){
            dg= true;
            respuestas.add(dg);
        }else if(radiotno.isChecked()){
            dg = false;
            respuestas.add(dg);
        }
    }

    public void septPregunat(){
        if(radiotsi.isChecked()){
            po= true;
            respuestas.add(po);
        }else if(radiotno.isChecked()){
            po = false;
            respuestas.add(po);
        }
    }

    public void octPregunat(){
        if(radiotsi.isChecked()){
            dt= true;
            respuestas.add(dt);
        }else if(radiotno.isChecked()){
            dt = false;
            respuestas.add(dt);
        }
    }

    public void novePregunat(){
        if(radiotsi.isChecked()){
            res= true;
            respuestas.add(res);
        }else if(radiotno.isChecked()){
            res = false;
            respuestas.add(res);
        }
    }
    public void decimPregunat(){
        if(radiotsi.isChecked()){
            ccovid= true;
            respuestas.add(ccovid);
        }else if(radiotno.isChecked()){
            ccovid = false;
            respuestas.add(ccovid);
        }
    }


}