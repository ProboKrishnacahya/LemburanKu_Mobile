package com.cahyaa.uas_2020_2_pt_lemburanku;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CatatanFragment extends Fragment {

    private Button catatan_cta;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_catatan, container, false);

        catatan_cta = (Button) view.findViewById(R.id.catatan_cta);

        catatan_cta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), CatatanActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}