package com.cahyaa.uas_2020_2_pt_lemburanku;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import model.Data;

public class BerandaFragment extends Fragment implements OnCardClickBeranda {

    private RecyclerView main_recyclerView;
    private ArrayList<Data> dataLembur;
    private LemburRVAdapter adapter;
    private FloatingActionButton main_FAB_addButton;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_beranda, container, false);

            initView();
            setupRecyclerView();
            loadDataDB();
            setListener();
            return view;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 1){
                if (resultCode == 200){
                    Data lemburBaru = data.getParcelableExtra("lemburBaru");
                    dataLembur.add(lemburBaru);
                    adapter.notifyDataSetChanged();
                }
            }
        }

        private void setListener() {
            main_FAB_addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), TambahDataActivity.class);
                    startActivity(intent);
                }
            });
        }

        private void loadDataDB(){
            String url = "http://192.168.1.6/exercise/lemburanku/ReadAllData.php";
            RequestQueue myQueue = Volley.newRequestQueue(getContext().getApplicationContext());

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonLembur = response.getJSONArray("data");
                                for(int i = 0; i < jsonLembur.length(); i++){
                                    JSONObject objLembur = jsonLembur.getJSONObject(i);
                                    Data lemburBaru = new Data();
                                    lemburBaru.setId(objLembur.getInt("id"));
                                    lemburBaru.setTanggal(objLembur.getString("tanggal"));
                                    lemburBaru.setJumlah_jam(objLembur.getInt("jumlah_jam"));
                                    lemburBaru.setJenis_hari(objLembur.getString("jenis_hari"));
                                    lemburBaru.setTotal_upah(objLembur.getInt("total_upah"));
                                    dataLembur.add(lemburBaru);
                                }
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }
            );

            myQueue.add(request);
        }

        private void setupRecyclerView() {
            RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
            main_recyclerView.setLayoutManager(manager);
            main_recyclerView.setAdapter(adapter);
        }

        private void initView() {
            main_recyclerView = view.findViewById(R.id.main_recyclerView);
            dataLembur = new ArrayList<Data>();
            adapter = new LemburRVAdapter(dataLembur, this);
            main_FAB_addButton = view.findViewById(R.id.main_FAB_addButton);
        }

        @Override
        public void onCardClick(int position) {
            int id = dataLembur.get(position).getId();
            Intent intent = new Intent(view.getContext(), DetaildataActivity.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }