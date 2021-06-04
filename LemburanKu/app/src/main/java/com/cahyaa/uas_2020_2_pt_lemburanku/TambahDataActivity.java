package com.cahyaa.uas_2020_2_pt_lemburanku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import model.Data;

public class TambahDataActivity extends AppCompatActivity {

    private ImageView tambahdata_backArrow;
    private RadioGroup tambahdata_jenisHari;
    private RadioButton tambahdata_hariBiasa, tambahdata_hariTerpendek, tambahdata_hariLimaKerja, tambahdata_hariEnamKerja;
    private EditText tambahdata_gaji, tambahdata_calendar;
    private TextInputLayout tambahdata_jamLembur, tambahdata_keterangan;
    private Button tambahdata_saveButton;
    private String jenis_hari, tanggal;
    private Double gajiPerJam;
    private int gajiPerBulan, total_upah;

    Calendar calendar = Calendar.getInstance();
    private final int year = calendar.get(Calendar.YEAR);
    private final int month = calendar.get(Calendar.MONTH);
    private final int day = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        initView();
        setListener();
    }

    private void setListener() {
        tambahdata_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tambahdata_jenisHari.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.tambahdata_hariBiasa) {
                    jenis_hari = "Hari kerja biasa";
                } else if (checkedId == R.id.tambahdata_hariTerpendek) {
                    jenis_hari = "Hari libur terpendek";
                } else if (checkedId == R.id.tambahdata_hariLimaKerja) {
                    jenis_hari = "5 hari kerja/minggu";
                } else if (checkedId == R.id.tambahdata_hariEnamKerja) {
                    jenis_hari = "6 hari kerja/minggu";
                }
            }
        });

        tambahdata_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TambahDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Store date in string
                        tanggal = dayOfMonth + "-" + month + "-" + year;
                        //Set date on plain text
                        tambahdata_calendar.setText(tanggal);
                    }
                },year,month,day
                );
                //Disable future date
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                //Show date picker dialog
                datePickerDialog.show();
            }
        });

        tambahdata_saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String keterangan = tambahdata_keterangan.getEditText().getText().toString().trim();
                int jumlah_jam = Integer.parseInt(tambahdata_jamLembur.getEditText().getText().toString().trim());
                tanggal = tambahdata_calendar.getText().toString().trim();

                //perhitungan total upah..
                if (jumlah_jam > 0 && jumlah_jam <= 3) {
                    gajiPerBulan = Integer.parseInt(tambahdata_gaji.getEditableText().toString().trim());
                    gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                    if (jumlah_jam == 1) {
                        total_upah = (int) (gajiPerJam * (double) 1.5);
                    } else if (jumlah_jam == 2) {
                        total_upah = (int) ((gajiPerJam * (double) 1.5) + (gajiPerJam * 2));
                    } else if (jumlah_jam == 3) {
                        total_upah = (int) ((gajiPerJam * (double) 1.5) + (gajiPerJam * 2));
                    }

                } else if (jumlah_jam >= 5 && jumlah_jam <= 8) {
                    gajiPerBulan = Integer.parseInt(tambahdata_gaji.getEditableText().toString().trim());
                    gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                    if (jumlah_jam == 5) {
                        total_upah = (int) (gajiPerJam * 5 * 2);
                    } else if (jumlah_jam == 6) {
                        total_upah = (int) ((gajiPerJam * 5 * 2) + (gajiPerJam * 3));
                    } else if (jumlah_jam == 7 || jumlah_jam == 8) {
                        total_upah = (int) ((gajiPerJam * 5 * 2) + (gajiPerJam * 4));
                    }

                } else if (jumlah_jam >= 8 && jumlah_jam <= 11) {
                    gajiPerBulan = Integer.parseInt(tambahdata_gaji.getEditableText().toString().trim());
                    gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                    if (jumlah_jam == 8) {
                        total_upah = (int) (gajiPerJam * 8 * 2);
                    } else if (jumlah_jam == 9) {
                        total_upah = (int) ((gajiPerJam * 8 * 2) + (gajiPerJam * 3));
                    } else if (jumlah_jam == 10 || jumlah_jam == 11) {
                        total_upah = (int) ((gajiPerJam * 8 * 2) + (gajiPerJam * 4));
                    }

                } else if (jumlah_jam >= 7 && jumlah_jam <= 10) {
                    gajiPerBulan = Integer.parseInt(tambahdata_gaji.getEditableText().toString().trim());
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
                postData(temp);
            }
        });
    }

    private void postData(Data temp) {
        String url = "http://192.168.1.6/exercise/lemburanku/TambahData.php";
        RequestQueue myRequest = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(getBaseContext(), BotnavActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), "Data Created", Toast.LENGTH_SHORT).show();
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
                return data;
            }
        };

        myRequest.add(request);
    }

    private void initView() {
        tambahdata_backArrow = findViewById(R.id.tambahdata_backArrow);
        tambahdata_calendar = findViewById(R.id.tambahdata_calendar);
        tambahdata_gaji = findViewById(R.id.tambahdata_gaji);
        tambahdata_jenisHari = findViewById(R.id.tambahdata_jenisHari);
        tambahdata_hariBiasa = findViewById(R.id.tambahdata_hariBiasa);
        tambahdata_hariTerpendek = findViewById(R.id.tambahdata_hariTerpendek);
        tambahdata_hariLimaKerja = findViewById(R.id.tambahdata_hariLimaKerja);
        tambahdata_hariEnamKerja = findViewById(R.id.tambahdata_hariEnamKerja);
        tambahdata_jamLembur = findViewById(R.id.tambahdata_jamLembur);
        tambahdata_keterangan = findViewById(R.id.tambahdata_keterangan);
        tambahdata_saveButton = findViewById(R.id.tambahdata_saveButton);
        jenis_hari = "";
        tanggal = "";
        gajiPerJam = 0.0;
        gajiPerBulan = 0;
        total_upah = 0;
    }
}