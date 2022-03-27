package com.estadia.encuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MenuComunActivity extends AppCompatActivity {

    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_comun);

        btnIniciar = findViewById(R.id.btniniciarComun);


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator i = new IntentIntegrator(MenuComunActivity.this);
                i.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                i.setPrompt("Lector -  CP");
                i.setCameraId(0);
                i.setBeepEnabled(true);
                i.setBarcodeImageEnabled(true);
                i.initiateScan();
            }
        });
    }

    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this,"Lectura cancelada",Toast.LENGTH_SHORT).show();
            }else{

                if(result.getContents().equals("Aeroplasa")){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"El código es: "+result.getContents(), Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}