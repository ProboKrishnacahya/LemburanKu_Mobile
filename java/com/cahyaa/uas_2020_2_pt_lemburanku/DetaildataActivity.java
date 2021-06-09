package com.cahyaa.uas_2020_2_pt_lemburanku;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import model.Data;

    public class DetaildataActivity extends AppCompatActivity {

        private ImageView detaildata_backArrow;
        private TextView detail_jenis_hari, detail_tanggal, detail_jumlah_jam, detail_keterangan, detail_total_upah;
        private Button detail_editButton, detail_deleteButton;
        private int id;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detaildata);
            initView();
            getData();
            setListener();
        }

        private void setListener() {
            detaildata_backArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            detail_editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), UpdateDataActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });

            detail_deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(DetaildataActivity.this);
                    dialog.setCancelable(false);
                    dialog.setTitle("Delete Data");
                    dialog.setMessage("Yakin ingin menghapus data ini?" );
                    dialog.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            deleteData();
                        }
                    })
                            .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    final AlertDialog alert = dialog.create();
                    alert.show();
                }
            });
        }

        private void deleteData() {

            String url = "http://192.168.1.6/exercise/lemburanku/DeleteData.php";

            RequestQueue myQueue = Volley.newRequestQueue(this);

            JSONObject parameter = new JSONObject();
            try {
                parameter.put("id", id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Intent intent = new Intent(getBaseContext(), BotnavActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            Toast.makeText(getBaseContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getBaseContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            myQueue.add(request);
        }

        private void getData() {

            String url = "http://192.168.1.6/exercise/lemburanku/ReadDataByID.php";

            RequestQueue myQueue = Volley.newRequestQueue(this);

            JSONObject parameter = new JSONObject();

            try {
                parameter.put("id", id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameter,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONObject dData = null;
                            try {
                                dData = response.getJSONObject("data");
                                detail_jenis_hari.setText(dData.getString("jenis_hari"));
                                detail_tanggal.setText(dData.getString("tanggal"));
                                detail_keterangan.setText(dData.getString("keterangan"));
                                detail_jumlah_jam.setText(String.valueOf(dData.getInt("jumlah_jam")));
                                detail_total_upah.setText(String.valueOf(dData.getInt("total_upah")));
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

        private void initView() {
            detaildata_backArrow = findViewById(R.id.detaildata_backArrow);
            detail_jenis_hari = findViewById(R.id.detail_jenis_hari);
            detail_tanggal = findViewById(R.id.detail_tanggal);
            detail_jumlah_jam = findViewById(R.id.detail_jumlah_jam);
            detail_keterangan = findViewById(R.id.detail_keterangan);
            detail_total_upah = findViewById(R.id.detail_total_upah);
            detail_editButton = findViewById(R.id.detail_editButton);
            detail_deleteButton = findViewById(R.id.detail_deleteButton);
            Intent intent = getIntent();
            id = intent.getIntExtra("id", 0);
        }
    }