package com.estadia.encuesta;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.estadia.encuesta.data.firebase.Model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IndexActivity extends AppCompatActivity {
    public static final String ENVIAR_IMEI ="nombre";

    private Button btn_validar,btn_mostrar;
    private EditText editimei;
    private TextView txt_informacion;
    String imei;
    String nombre;

    static final Integer PHONESTATS = 0x1;
    private final String TAG=MainActivity.class.getSimpleName();
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        //Usuario us= new Usuario();


        btn_validar=(Button)findViewById(R.id.btnvalidar);
        btn_mostrar =(Button)findViewById(R.id.btnmostrarimei);
        editimei = (EditText)findViewById(R.id.txtmostrarimei);
        txt_informacion = (TextView)findViewById(R.id.txtinfo);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario");



        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editimei.setText(imei);
            }
        });

        txt_informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

            //Valida el accesoa a un usuario registrado
        btn_validar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                consultarPermiso(Manifest.permission.READ_PHONE_STATE, PHONESTATS);

                databaseReference.orderByChild("imei").equalTo(imei).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for (DataSnapshot data : snapshot.getChildren()) {
                                Usuario u = data.getValue(Usuario.class);
                                if (u.getTipo().equals("comun")) {
                                    Intent intent = new Intent(view.getContext(), MenuComunActivity.class);

                                    startActivity(intent);
                                } else if (u.getTipo().equals("Administrador")) {
                                    Intent intent = new Intent(view.getContext(), MenuAdminActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }else{
                            Toast.makeText(IndexActivity.this, "No estas registrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
                );

            }
        });
        //Fin onclick


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void consultarPermiso(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(IndexActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(IndexActivity.this, permission)) {

                ActivityCompat.requestPermissions(IndexActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(IndexActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            imei = obtenerIMEI();
            //Toast.makeText(this,permission + " El permiso a la aplicación esta concedido.", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {

                // Validamos si el usuario acepta el permiso para que la aplicación acceda a los datos internos del equipo, si no denegamos el acceso
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    imei = obtenerIMEI();

                } else {

                    Toast.makeText(IndexActivity.this, "Has negado el permiso a la aplicación", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private String obtenerIMEI() {
        final TelephonyManager telephonyManager= (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Hacemos la validación de métodos, ya que el método getDeviceId() ya no se admite para android Oreo en adelante, debemos usar el método getImei()
            return telephonyManager.getImei();
        }
        else {
            return telephonyManager.getDeviceId();
        }

    }




}












