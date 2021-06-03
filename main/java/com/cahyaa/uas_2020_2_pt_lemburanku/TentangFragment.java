package com.cahyaa.uas_2020_2_pt_lemburanku;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TentangFragment extends Fragment {

    private View view;
    private TextView about_textView_gmail1, about_textView_gmail2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tentang, container, false);

        about_textView_gmail1 = (TextView) view.findViewById(R.id.about_textView_gmail1);
        about_textView_gmail2 = (TextView) view.findViewById(R.id.about_textView_gmail2);

        about_textView_gmail1.setText("nathanaelabel84@gmail.com");
        about_textView_gmail2.setText("prob.krshn@gmail.com");

        Linkify.addLinks(about_textView_gmail1, Linkify.EMAIL_ADDRESSES);
        Linkify.addLinks(about_textView_gmail2, Linkify.EMAIL_ADDRESSES);

        return view;

        }
    }