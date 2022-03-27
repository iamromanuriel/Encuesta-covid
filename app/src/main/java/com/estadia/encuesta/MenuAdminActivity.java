package com.estadia.encuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MenuAdminActivity extends AppCompatActivity {
    private Button btn_iniciar,btn_usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        btn_usuarios = findViewById(R.id.btnusuarios);
        btn_iniciar = findViewById(R.id.btniniciar);




        btn_usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UsuarioActivity.class);
                startActivity(intent);
            }
        });

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator i = new IntentIntegrator(MenuAdminActivity.this);
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