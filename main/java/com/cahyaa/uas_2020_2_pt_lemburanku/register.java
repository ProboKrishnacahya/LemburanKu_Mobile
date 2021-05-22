package com.cahyaa.uas_2020_2_pt_lemburanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

//import model.ArrayUser;
//import model.User;

public class register extends AppCompatActivity {

    private TextInputLayout register_textInputLayout_email, register_textInputLayout_nama, register_textInputLayout_password;
    private Button register_button_register;
    private TextView register_textView_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_textInputLayout_email = findViewById(R.id.register_textInputLayout_email);
        register_textInputLayout_nama = findViewById(R.id.register_textInputLayout_nama);
        register_textInputLayout_password = findViewById(R.id.register_textInputLayout_password);
        register_button_register = findViewById(R.id.register_button_register);
        register_textView_login = findViewById(R.id.register_textView_login);

        register_button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = register_textInputLayout_email.getEditText().getText().toString().trim();
                String name = register_textInputLayout_nama.getEditText().getText().toString().trim();
                String password = register_textInputLayout_password.getEditText().getText().toString().trim();

//                if (!email.isEmpty() && !name.isEmpty() && !password.isEmpty()) {
//                    Intent intent = new Intent(getBaseContext(), login.class);
//                    User user = new User(email, name, password);
//                    ArrayUser.rakUser.add(user);
//                    intent.putExtra("id_user", user);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    Toast.makeText(getApplicationContext(), "Akun Berhasil Dibuat", Toast.LENGTH_LONG).show();
//                    startActivity(intent);
//                }
            }
        });

        private void Regist(){
            register_button_register.setVisibility(View.GONE);

        }

        register_textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), login.class);
                startActivity(intent);
            }
        });
    }
}