package com.example.databasesekolah.ui.main;

import android.view.View;

import com.example.databasesekolah.Entity.AppDatabase;
import com.example.databasesekolah.Entity.DataSekolah;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void resetForm();
        void sukses();
        //void getData(List<DataSekolah> list);
        void editData(DataSekolah item);
        //void deleteData(DataSekolah item);
    }
    interface datapresenter{
        //readData(AppDatabase database);
        void editData(String jml_siswa, String jml_guru, String nama_sekolah, String alamat, int id, AppDatabase database);
        void deleteData(DataSekolah dataDiri, AppDatabase database);
    }
    interface Cetak extends View.OnClickListener{
        void getData(List<DataSekolah> list);
    }
    interface hapus{
       // void resetForm();
       void sukses();
        void deleteData(DataSekolah item);
    }
}
