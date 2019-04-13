package com.example.databasesekolah.ui.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databasesekolah.Entity.AppDatabase;
import com.example.databasesekolah.Entity.DataSekolah;
import com.example.databasesekolah.R;

public class MainActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etSiswa, etGuru ;
    private Button btnSubmit, btnLihat;
    private String setNama, setAlamat, setSiswa,setGuru ;

    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.et_nama_sekolah);
        etAlamat = findViewById(R.id.et_alamat_sekolah);
        etSiswa = findViewById(R.id.et_jml_siswa);
        etGuru = findViewById(R.id.et_jml_guru);

        btnSubmit = findViewById(R.id.btn_submit);
        btnLihat = findViewById(R.id.btn_lihat);
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
                Intent submit = new Intent(getApplicationContext(), LihatDataActivity.class);
                startActivity(submit);
            }
        });
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lihat = new Intent(getApplicationContext(), LihatDataActivity.class);
                startActivity(lihat);
            }
        });
    }
    public void input(){
        setNama = etNama.getText().toString();
        setAlamat = etAlamat.getText().toString();
        setSiswa = etSiswa.getText().toString();
        setGuru = etGuru.getText().toString();

        final DataSekolah dataSekolah = new DataSekolah();

        dataSekolah.setNama_sekolah(setNama);
        dataSekolah.setAlamat(setAlamat);
        dataSekolah.setJml_siswa(setSiswa);
        dataSekolah.setJml_guru(setGuru);

        new InsertData(appDatabase, dataSekolah).execute();
    }
    class InsertData extends AsyncTask<Void, Void, Long> {
        private AppDatabase database;
        private DataSekolah dataSekolah;

        public InsertData(AppDatabase database, DataSekolah dataSekolah) {
            this.database = database;
            this.dataSekolah = dataSekolah;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return database.dao().insertData(dataSekolah);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();

        }

    }
}
