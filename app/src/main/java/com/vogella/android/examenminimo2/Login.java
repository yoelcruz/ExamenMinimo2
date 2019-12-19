package com.vogella.android.examenminimo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button buttonLogin;
    EditText userNameEditText;
    EditText passwordEditText;
    String user;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        setUpView();
    }

    private void setUpView(){
        buttonLogin = findViewById(R.id.buttonLogin);
        userNameEditText = (EditText) findViewById(R.id.username_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        userNameEditText.setText(preferences.getString("nombreUsuario", ""));
        passwordEditText.setText(preferences.getString("passwordUsuario", ""));

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = userNameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor Obj_editor = preferencias.edit();
                Obj_editor.putString("nombreUsuario",userNameEditText.getText().toString());
                Obj_editor.putString("passwordUsuario",passwordEditText.getText().toString());
                Obj_editor.commit();

                if (user.equals("user") && password.equals("dsamola")){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"El usuario no son correctos ",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
