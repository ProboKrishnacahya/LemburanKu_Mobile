package com.cahyaa.uas_2020_2_pt_lemburanku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.Data;

public class LemburRVAdapter extends RecyclerView.Adapter<LemburRVAdapter.LemburViewHolder> {

    private ArrayList<Data> listLembur;
    protected OnCardClickBeranda cardListener;

    public LemburRVAdapter(ArrayList<Data> listLembur, OnCardClickBeranda cardListener) {
        this.cardListener = cardListener;
        this.listLembur = listLembur;
    }

    @NonNull
    @Override
    public LemburViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview, parent, false);
        return new LemburViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LemburViewHolder holder, int position) {
        holder.cardview_hariTanggal.setText(listLembur.get(position).getTanggal());
        holder.cardview_durasi.setText(String.valueOf(listLembur.get(position).getJumlah_jam()));
        holder.cardview_jenisHari.setText(String.valueOf(listLembur.get(position).getJenis_hari()));
        holder.cardview_upah.setText(String.valueOf(listLembur.get(position).getTotal_upah()));
    }

    @Override
    public int getItemCount() {
        return listLembur.size();
    }

    public class LemburViewHolder extends RecyclerView.ViewHolder {

        private TextView cardview_hariTanggal, cardview_durasi, cardview_jenisHari, cardview_upah;

        public LemburViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview_hariTanggal = itemView.findViewById(R.id.cardview_hariTanggal);
            cardview_durasi = itemView.findViewById(R.id.cardview_durasi);
            cardview_jenisHari = itemView.findViewById(R.id.cardview_jenisHari);
            cardview_upah = itemView.findViewById(R.id.cardview_upah);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardListener.onCardClick(getAdapterPosition());
                }
            });
        }
    }
}