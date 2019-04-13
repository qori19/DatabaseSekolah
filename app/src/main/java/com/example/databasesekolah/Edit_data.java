package com.example.databasesekolah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasesekolah.Adapter.SekolahAdapter;
import com.example.databasesekolah.Entity.AppDatabase;
import com.example.databasesekolah.Entity.DataSekolah;
import com.example.databasesekolah.Presenter.SekolahPresenter;
import com.example.databasesekolah.ui.main.LihatDataActivity;
import com.example.databasesekolah.ui.main.MainContact;

public class Edit_data extends AppCompatActivity implements MainContact.view {
    private AppDatabase appDatabase;
    private SekolahPresenter sekolahPresenter;
    private SekolahAdapter sekolahAdapter;
    private EditText  ETnamaSekolah, ETalamatSekolah, ETjmlSiswa, ETjmlGuru;
    private Button BTNsubmit;
    private String NamaSekolah, AlamatSekolah, JmlSiswa, JmlGuru;
    private boolean edit = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        ETnamaSekolah = findViewById(R.id.et_nama_sekolah);
        ETalamatSekolah = findViewById(R.id.et_alamat_sekolah);
        ETjmlSiswa = findViewById(R.id.et_jml_siswa);
        ETjmlGuru = findViewById(R.id.et_jml_guru);
        BTNsubmit = findViewById(R.id.btn_submit);

        sekolahPresenter = new SekolahPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        NamaSekolah = getIntent().getStringExtra("nama");
        AlamatSekolah = getIntent().getStringExtra("alamat");
        JmlSiswa = getIntent().getStringExtra("jml_siswa");
        JmlGuru = getIntent().getStringExtra("jml_guru");
        id = getIntent().getIntExtra("id", 99);
        ETnamaSekolah.setText(NamaSekolah);
        ETalamatSekolah.setText(AlamatSekolah);
        ETjmlSiswa.setText(JmlSiswa);
        ETjmlGuru.setText(JmlGuru);
        BTNsubmit.setOnClickListener(this);
    }

    @Override
    public void resetForm() {
        ETnamaSekolah.setText("");
        ETalamatSekolah.setText("");
        ETjmlSiswa.setText("");
        ETjmlGuru.setText("");
        BTNsubmit.setText("Submit");
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LihatDataActivity.class));
    }

    @Override
    public void editData(DataSekolah item) {
        ETnamaSekolah.setText(item.getNama_sekolah());
        ETalamatSekolah.setText(item.getAlamat());
        ETjmlSiswa.setText(item.getJml_siswa());
        ETjmlGuru.setText(item.getJml_guru());
        edit = true;
        BTNsubmit.setText("Update");
    }

    @Override
    public void onClick(View v) {
        String NamaSekolah, AlamatSekolah, JumlahSiswa, JumlahGuru;
        NamaSekolah = ETnamaSekolah.getText().toString();
        AlamatSekolah = ETalamatSekolah.getText().toString();
        JumlahSiswa = ETjmlSiswa.getText().toString();
        JumlahGuru = ETjmlGuru.getText().toString();

        if(v ==  BTNsubmit){
            if(JumlahSiswa.equals("") || JumlahGuru.equals("") || NamaSekolah.equals("") || AlamatSekolah.equals("")) {
                Toast.makeText(this, "Isi semua data dahulu", Toast.LENGTH_SHORT).show();
            } else {

                    sekolahPresenter.editData(JumlahSiswa, JumlahGuru, NamaSekolah, AlamatSekolah, id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }

