package com.cahyaa.uas_2020_2_pt_lemburanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

//import model.ArrayUser;
//import model.User;

public class login extends AppCompatActivity {

    private Intent intent;
    private TextInputLayout login_textInputLayout_email, login_textInputLayout_password;
    private Button login_button_login;
    private TextView login_textView_register;
    private Boolean validateLogin;

    private TextWatcher main_function_textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String email = login_textInputLayout_email.getEditText().getText().toString().trim();
            String password = login_textInputLayout_password.getEditText().getText().toString().trim();

            if (email.isEmpty() && password.isEmpty()) {
                login_button_login.setEnabled(false);
            } else {
                login_button_login.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_textInputLayout_email = findViewById(R.id.register_textInputLayout_email);
        login_textInputLayout_password = findViewById(R.id.register_textInputLayout_password);
        login_button_login = findViewById(R.id.register_button_register);
        login_textView_register = findViewById(R.id.register_textView_login);
        validateLogin = false;

        login_textInputLayout_email.getEditText().addTextChangedListener(main_function_textWatcher);
        login_textInputLayout_password.getEditText().addTextChangedListener(main_function_textWatcher);

        login_textView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), register.class);
                startActivity(intent);
            }
        });

        login_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_textInputLayout_email.getEditText().getText().toString().trim();
                String password = login_textInputLayout_password.getEditText().getText().toString().trim();

//                for (int i = 0; i < ArrayUser.rakUser.size(); i++) {
//                    User tempUser = ArrayUser.rakUser.get(i);
//                    if (tempUser.getName().equalsIgnoreCase(email) || tempUser.getEmail().equalsIgnoreCase(email) && tempUser.getPassword().equalsIgnoreCase(password)) {
//                        intent = new Intent(getBaseContext(), MainActivity.class);
//                        intent.putExtra("id_user", tempUser);
//                        startActivity(intent);
//                        finish();
//                        Toast.makeText(getApplicationContext(), " Berhasil Login", Toast.LENGTH_LONG).show();
//                        validateLogin = true;
//                        break;
//                    } else {
//                        validateLogin = false;
//                    }
//                }
//
//                if (validateLogin == false) {
//                    Toast.makeText(getApplicationContext(), "Tidak Dapat Login, Email/Password Salah", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        }
    });
    }
}