package com.example.databasesekolah.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.databasesekolah.Edit_data;
import com.example.databasesekolah.Entity.DataSekolah;
import com.example.databasesekolah.R;
import com.example.databasesekolah.ui.main.MainContact;

import java.util.List;

public class SekolahAdapter extends RecyclerView.Adapter<SekolahAdapter.ViewHolder> {
    Context context;
    List<DataSekolah> list;
    MainContact.hapus view;

    public SekolahAdapter(Context context, List<DataSekolah> list, MainContact.hapus view) {
        this.view = view;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SekolahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_lihat_data, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final DataSekolah data = list.get(i);
        viewHolder.tvNama.setText(data.getNama_sekolah());
        viewHolder.tvAlamat.setText(data.getAlamat());
        viewHolder.tvSiswa.setText(data.getJml_siswa());
        viewHolder.tvGuru.setText(data.getJml_guru());
        viewHolder.id.setText(String.valueOf(data.getId()));
        viewHolder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.deleteData(data);

            }
        });
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(context, Edit_data.class);
                x.putExtra("nama", data.getNama_sekolah());
                x.putExtra("alamat", data.getAlamat());
                x.putExtra("jml_siswa", data.getJml_siswa());
                x.putExtra("jml_guru", data.getJml_guru());
                x.putExtra("id", data.getId());
                x.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(x);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvAlamat,tvSiswa, tvGuru, id;
        Button btnHapus, btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSiswa = itemView.findViewById(R.id.tv_item_jml_siswa);
            tvGuru = itemView.findViewById(R.id.tv_item_jml_guru);
            tvNama = itemView.findViewById(R.id.tv_item_nama_sekolah);
            tvAlamat = itemView.findViewById(R.id.tv_item_alamat);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            id = itemView.findViewById(R.id.tv_item_id);
        }
    }
}
