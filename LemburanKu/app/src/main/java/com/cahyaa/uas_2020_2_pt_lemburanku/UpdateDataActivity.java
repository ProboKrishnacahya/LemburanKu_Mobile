package com.cahyaa.uas_2020_2_pt_lemburanku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import model.Data;

public class UpdateDataActivity extends AppCompatActivity {

    private ImageView updatedata_backArrow;
    private RadioGroup updatedata_jenisHari;
    private RadioButton updatedata_hariBiasa, updatedata_hariTerpendek, updatedata_hariLimaKerja, updatedata_hariEnamKerja;
    private EditText updatedata_gaji, updatedata_calendar;
    private TextInputLayout updatedata_jamLembur, updatedata_keterangan;
    private Button updatedata_saveButton;
    private String jenis_hari, tanggal;
    private Double gajiPerJam;
    private int gajiPerBulan, total_upah, jumlah_jam, id;

    Calendar calendar = Calendar.getInstance();

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        initView();
        setListener();
    }

    private void setListener() {
        updatedata_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updatedata_jenisHari.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.updatedata_hariBiasa) {
                    jenis_hari = "Hari kerja biasa";
                } else if (checkedId == R.id.updatedata_hariTerpendek) {
                    jenis_hari = "Hari libur terpendek";
                } else if (checkedId == R.id.updatedata_hariLimaKerja) {
                    jenis_hari = "5 hari kerja/minggu";
                } else if (checkedId == R.id.updatedata_hariEnamKerja) {
                    jenis_hari = "6 hari kerja/minggu";
                }
            }
        });

        updatedata_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdateDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Store date in string
                        tanggal = dayOfMonth + "-" + month + "-" + year;
                        //Set date on plain text
                        updatedata_calendar.setText(tanggal);
                    }
                },year,month,day
                );
                //Disable future date
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                //Show date picker dialog
                datePickerDialog.show();
            }
        });

        updatedata_saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String keterangan = updatedata_keterangan.getEditText().getText().toString().trim();
                tanggal = updatedata_calendar.getText().toString().trim();
                jumlah_jam = Integer.parseInt(updatedata_jamLembur.getEditText().getText().toString().trim());

                //perhitungan total upah..
                if (jumlah_jam > 0 && jumlah_jam <= 3) {
                    gajiPerBulan = Integer.parseInt(updatedata_gaji.getEditableText().toString().trim());
                    gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                    if (jumlah_jam == 1) {
                        total_upah = (int) (gajiPerJam * (double) 1.5);
                    } else if (jumlah_jam == 2) {
                        total_upah = (int) ((gajiPerJam * (double) 1.5) + (gajiPerJam * 2));
                    } else if (jumlah_jam == 3) {
                        total_upah = (int) ((gajiPerJam * (double) 1.5) + (gajiPerJam * 2));
                    }

                } else if (jumlah_jam >= 5 && jumlah_jam <= 8) {
                    gajiPerBulan = Integer.parseInt(updatedata_gaji.getEditableText().toString().trim());
                    gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                    if (jumlah_jam == 5) {
                        total_upah = (int) (gajiPerJam * 5 * 2);
                    } else if (jumlah_jam == 6) {
                        total_upah = (int) ((gajiPerJam * 5 * 2) + (gajiPerJam * 3));
                    } else if (jumlah_jam == 7 || jumlah_jam == 8) {
                        total_upah = (int) ((gajiPerJam * 5 * 2) + (gajiPerJam * 4));
                    }

                } else if (jumlah_jam >= 8 && jumlah_jam <= 11) {
                    gajiPerBulan = Integer.parseInt(updatedata_gaji.getEditableText().toString().trim());
                    gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                    if (jumlah_jam == 8) {
                        total_upah = (int) (gajiPerJam * 8 * 2);
                    } else if (jumlah_jam == 9) {
                        total_upah = (int) ((gajiPerJam * 8 * 2) + (gajiPerJam * 3));
                    } else if (jumlah_jam == 10 || jumlah_jam == 11) {
                        total_upah = (int) ((gajiPerJam * 8 * 2) + (gajiPerJam * 4));
                    }

                } else if (jumlah_jam >= 7 && jumlah_jam <= 10) {
                    gajiPerBulan = Integer.parseInt(updatedata_gaji.getEditableText().toString().trim());
                    gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                    if (jumlah_jam == 7) {
                        total_upah = (int) (gajiPerJam * 7 * 2);
                    } else if (jumlah_jam == 8) {
                        total_upah = (int) ((gajiPerJam * 7 * 2) + (gajiPerJam * 3));
                    } else if (jumlah_jam == 9 || jumlah_jam == 10) {
                        total_upah = (int) ((gajiPerJam * 7 * 2) + (gajiPerJam * 4));
                    }

                }

                Data temp = new Data(jenis_hari, tanggal, keterangan, jumlah_jam, total_upah);
                updateData(temp);
            }
        });
    }

    private void updateData(Data temp) {
        String url = "http://192.168.1.6/exercise/lemburanku/UpdateData.php";
        RequestQueue myRequest = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(getBaseContext(), BotnavActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        //intent.getIntExtra("id", id);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), "Data Updated", Toast.LENGTH_SHORT).show();
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("jenis_hari", temp.getJenis_hari());
                data.put("tanggal", temp.getTanggal());
                data.put("keterangan", temp.getKeterangan());
                data.put("jumlah_jam", String.valueOf(temp.getJumlah_jam()));
                data.put("total_upah", String.valueOf(temp.getTotal_upah()));
                data.put("id", String.valueOf(id));
                return data;
            }
        };

        myRequest.add(request);
    }

    private void initView() {
        updatedata_backArrow = findViewById(R.id.updatedata_backArrow);
        updatedata_calendar = findViewById(R.id.updatedata_calendar);
        updatedata_gaji = findViewById(R.id.updatedata_gaji);
        updatedata_jenisHari = findViewById(R.id.updatedata_jenisHari);
        updatedata_hariBiasa = findViewById(R.id.updatedata_hariBiasa);
        updatedata_hariTerpendek = findViewById(R.id.updatedata_hariTerpendek);
        updatedata_hariLimaKerja = findViewById(R.id.updatedata_hariLimaKerja);
        updatedata_hariEnamKerja = findViewById(R.id.updatedata_hariEnamKerja);
        updatedata_jamLembur = findViewById(R.id.updatedata_jamLembur);
        updatedata_keterangan = findViewById(R.id.updatedata_keterangan);
        updatedata_saveButton = findViewById(R.id.updatedata_saveButton);
        jenis_hari = "";
        tanggal = "";
        gajiPerJam = 0.0;
        gajiPerBulan = 0;
        jumlah_jam = 0;
        total_upah = 0;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }
}