package com.example.lemburanku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

import model.Data;

public class TambahDataActivity extends AppCompatActivity {

    private ImageView tambahdata_backArrow;
    private RadioGroup tambahdata_jenisHari;
    private RadioButton tambahdata_hariBiasa, tambahdata_hariTerpendek, tambahdata_hariLimaKerja, tambahdata_hariEnamKerja;
    private EditText tambahdata_gaji, tambahdata_calendar;
    private TextInputLayout tambahdata_jamLembur, tambahdata_keterangan;
    private Button tambahdata_saveButton;
    private String jenis_hari;
    private Double gajiPerJam;
    private int gajiPerBulan, total_upah;

    Calendar calendar = Calendar.getInstance();

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

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
                    jenis_hari = "Lembur hari kerja biasa";
                    if (R.id.tambahdata_jamLembur > 0 || R.id.tambahdata_jamLembur <= 3) {
                        gajiPerBulan = R.id.tambahdata_gaji;
                        gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                        if (R.id.tambahdata_jamLembur == 1) {
                            total_upah = (int) (gajiPerJam * (double) 1.5);
                        } else if (R.id.tambahdata_jamLembur == 2) {
                            total_upah = (int) ((gajiPerJam * (double) 1.5) + (gajiPerJam * 2));
                        } else if (R.id.tambahdata_jamLembur == 3) {
                            total_upah = (int) ((gajiPerJam * (double) 1.5) + (gajiPerJam * 2));
                        }

                    } else {
                        tambahdata_keterangan.setError("Jam lembur hari kerja biasa maksimal 1 s.d. 3 jam");
                    }
                } else if (checkedId == R.id.tambahdata_hariTerpendek) {
                    jenis_hari = "Lembur hari libur terpendek";
                    if (R.id.tambahdata_jamLembur >= 5 || R.id.tambahdata_jamLembur <= 8) {
                        gajiPerBulan = R.id.tambahdata_gaji;
                        gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                        if (R.id.tambahdata_jamLembur == 5) {
                            total_upah = (int) (gajiPerJam * 5 * 2);
                        } else if (R.id.tambahdata_jamLembur == 6) {
                            total_upah = (int) ((gajiPerJam * 5 * 2) + (gajiPerJam * 3));
                        } else if (R.id.tambahdata_jamLembur == 7 || R.id.tambahdata_jamLembur == 8) {
                            total_upah = (int) ((gajiPerJam * 5 * 2) + (gajiPerJam * 4));
                        }

                    } else {
                        tambahdata_keterangan.setError("Jam lembur hari libur terpendek maksimal 5 s.d. 8 jam");
                    }
                } else if (checkedId == R.id.tambahdata_hariLimaKerja) {
                    jenis_hari = "Lembur hari libur 5 hari kerja/minggu";
                    if (R.id.tambahdata_jamLembur >= 8 || R.id.tambahdata_jamLembur <= 11) {
                        gajiPerBulan = R.id.tambahdata_gaji;
                        gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                        if (R.id.tambahdata_jamLembur == 8) {
                            total_upah = (int) (gajiPerJam * 8 * 2);
                        } else if (R.id.tambahdata_jamLembur == 9) {
                            total_upah = (int) ((gajiPerJam * 8 * 2) + (gajiPerJam * 3));
                        } else if (R.id.tambahdata_jamLembur == 10 || R.id.tambahdata_jamLembur == 11) {
                            total_upah = (int) ((gajiPerJam * 8 * 2) + (gajiPerJam * 4));
                        }

                    } else {
                        tambahdata_keterangan.setError("Jam lembur hari libur 5 hari kerja/minggu maksimal 8 s.d. 11 jam");
                    }
                } else if (checkedId == R.id.tambahdata_hariEnamKerja) {
                    jenis_hari = "Lembur hari libur 6 hari kerja/minggu";
                    if (R.id.tambahdata_jamLembur >= 7 || R.id.tambahdata_jamLembur <= 10) {
                        gajiPerBulan = R.id.tambahdata_gaji;
                        gajiPerJam = ((double) 1 / 173) * gajiPerBulan;

                        if (R.id.tambahdata_jamLembur == 7) {
                            total_upah = (int) (gajiPerJam * 7 * 2);
                        } else if (R.id.tambahdata_jamLembur == 8) {
                            total_upah = (int) ((gajiPerJam * 7 * 2) + (gajiPerJam * 3));
                        } else if (R.id.tambahdata_jamLembur == 9 || R.id.tambahdata_jamLembur == 10) {
                            total_upah = (int) ((gajiPerJam * 7 * 2) + (gajiPerJam * 4));
                        }

                    } else {
                        tambahdata_keterangan.setError("Jam lembur hari libur 6 hari kerja/minggu maksimal 7 s.d. 10 jam");
                    }
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
                        String tanggal = dayOfMonth + "/" + month + "/" + year;
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
                //String jenis_hari = "";
                String tanggal = tambahdata_calendar.getText().toString().trim();
                String keterangan = tambahdata_keterangan.getEditText().getText().toString().trim();
                int jumlah_jam = Integer.parseInt(tambahdata_jamLembur.getEditText().toString().trim());
                //int total_upah = Integer.parseInt("");

                Data temp = new Data(jenis_hari, tanggal, keterangan, jumlah_jam, total_upah);
                postData(temp);
            }
        });
    }

    private void postData(Data temp) {
        String url = "http://192.168.1.6/exercise/lemburanku/CreateData.php";
        RequestQueue myRequest = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

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
        gajiPerJam = 0.0;
        gajiPerBulan = 0;
        total_upah = 0;
    }
}