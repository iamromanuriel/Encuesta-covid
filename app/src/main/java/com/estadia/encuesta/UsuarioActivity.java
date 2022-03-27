package com.estadia.encuesta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.estadia.encuesta.Model.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioActivity extends AppCompatActivity {

    //Lista tipo usuario y adaptador
    private List<Usuario> listaUsuario = new ArrayList<>();
    ArrayAdapter<Usuario> arrayAdapterUsuario;

    //variables de gatwets
    EditText nombre,apellido,imei;
    Spinner opciones,area;

    ListView lista;
    //Variables de firebaseAdministrador
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Usuario usuarioSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        nombre = findViewById(R.id.txt_nombre);
        apellido = findViewById(R.id.txt_apellido);
        imei = findViewById(R.id.txt_imei);
        opciones = findViewById(R.id.sp_opciones);
        area = findViewById(R.id.sp_area);
        lista = findViewById(R.id.lv_datos);


        String[] arrayOpcion = {"comun", "Administrador"};
        String[] arrayArea = {"Administrativa","Servicio","Ventas"};

        ArrayAdapter<String> adaptoOpcion = new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, arrayOpcion);
        opciones.setAdapter(adaptoOpcion);
        ArrayAdapter<String> adaptoArea = new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, arrayArea);
        area.setAdapter(adaptoArea);

        inicializarFirebase();
        listarDatos();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usuarioSelected = (Usuario) parent.getItemAtPosition(position);
                nombre.setText(usuarioSelected.getNombre());
                apellido.setText(usuarioSelected.getApellido());
                imei.setText(usuarioSelected.getImei());

                if(usuarioSelected.getTipo().equals(arrayOpcion[0])){
                    opciones.setSelection(0);

                }else if(usuarioSelected.getTipo().equals(arrayOpcion[1])){
                    opciones.setSelection(1);
                }

                if(usuarioSelected.getArea().equals(arrayArea[0])){
                    area.setSelection(0);
                }else if(usuarioSelected.getArea().equals(arrayArea[1])){
                    area.setSelection(1);
                }else if(usuarioSelected.getArea().equals(arrayArea[2])){
                    area.setSelection(2);
                }

            }
        });
    }

    private void listarDatos() {
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaUsuario.clear();
                for (DataSnapshot objDataSnapshot : dataSnapshot.getChildren()){
                    Usuario u = objDataSnapshot.getValue(Usuario.class);
                    listaUsuario.add(u);

                    arrayAdapterUsuario = new ArrayAdapter<Usuario>(UsuarioActivity.this, android.R.layout.simple_list_item_1,listaUsuario);
                    lista.setAdapter(arrayAdapterUsuario);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String vnombre = nombre.getText().toString();
        String vapellido = apellido.getText().toString();
        String vimei = imei.getText().toString();

        Usuario u = new Usuario();

        switch (item.getItemId()){
            case R.id.iconadd:
                if(vnombre.equals("") || vapellido.equals("") || vimei.equals("")){

                    validacion();
                }else {
                    //Persona p = new Persona();
                    u.setUid(UUID.randomUUID().toString());
                    u.setNombre(vnombre);
                    u.setApellido(vapellido);
                    u.setImei(vimei);
                    u.setTipo(opciones.getSelectedItem().toString());
                    u.setArea(area.getSelectedItem().toString());

                    databaseReference.child("Usuario").child(u.getUid()).setValue(u);
                    Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }
                break;
            case R.id.iconsave:

                try {
                    //Persona p = new Persona();
                    u.setUid(usuarioSelected.getUid());
                    u.setNombre(nombre.getText().toString().trim());
                    u.setApellido(apellido.getText().toString().trim());
                    u.setImei(imei.getText().toString().trim());
                    u.setTipo(opciones.getSelectedItem().toString());
                    u.setArea(area.getSelectedItem().toString());

                    databaseReference.child("Usuario").child(u.getUid()).setValue(u);
                    Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }catch (Exception e){
                    Toast.makeText(this, "Error al editar", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.icondelete:
                //Persona p = new Persona();
                try {
                    u.setUid(usuarioSelected.getUid());
                    databaseReference.child("Usuario").child(u.getUid()).removeValue();

                    Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }catch (Exception e){
                    Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();

                }


                break;
            default:break;
        }
        return true;
    }

    private void validacion() {
        String vnombre = nombre.getText().toString();
        String vapellido = apellido.getText().toString();
        String vimei = imei.getText().toString();


        if(vnombre.equals("")){
            nombre.setError("Requerido");
        }
        else if(vapellido.equals("")){
            apellido.setError("Requerido");
        }
        else if(vimei.equals("")){
            imei.setError("Requerido");
        }
    }

    private void limpiarCajas() {
        nombre.setText("");
        apellido.setText("");
        imei.setText("");
    }
}