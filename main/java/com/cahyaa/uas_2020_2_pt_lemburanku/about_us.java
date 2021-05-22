package com.cahyaa.uas_2020_2_pt_lemburanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class about_us extends AppCompatActivity {

    private TextView about_textView_gmail1, about_textView_gmail2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        about_textView_gmail1 = (TextView) findViewById(R.id.about_textView_gmail1);
        about_textView_gmail2 = (TextView) findViewById(R.id.about_textView_gmail2);

        about_textView_gmail1.setText("nathanaelabel84@gmail.com");
        about_textView_gmail2.setText("prob.krshn@gmail.com");

        Linkify.addLinks(about_textView_gmail1, Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks(about_textView_gmail2, Linkify.EMAIL_ADDRESSES);
    }

}