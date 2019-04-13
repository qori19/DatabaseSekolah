package com.example.databasesekolah.ui.main;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.databasesekolah.Adapter.SekolahAdapter;
import com.example.databasesekolah.Entity.AppDatabase;
import com.example.databasesekolah.Entity.DataSekolah;
import com.example.databasesekolah.Presenter.SekolahPresenter;
import com.example.databasesekolah.R;

import java.util.List;

public class LihatDataActivity extends AppCompatActivity implements MainContact.hapus {
    private AppDatabase appDatabase;
    private SekolahAdapter sekolahAdapter;
    private SekolahPresenter sekolahPresenter;
    View view;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        sekolahPresenter = new SekolahPresenter(this);
        recyclerView = findViewById(R.id.rc_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        readData(appDatabase);
    }

    public void readData(AppDatabase database) {
        List list;
        list = database.dao().getData();
        //view.getData(list);
        sekolahAdapter = new SekolahAdapter(getApplicationContext(), list, this);
        recyclerView.setAdapter(sekolahAdapter);
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "Data Telah di Hapus", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LihatDataActivity.class));
    }


    @Override
    public void deleteData(final DataSekolah item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // resetForm();
                        sekolahPresenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
