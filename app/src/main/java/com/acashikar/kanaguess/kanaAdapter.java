package com.acashikar.kanaguess;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class kanaAdapter extends RecyclerView.Adapter<kanaAdapter.kanaViewHolder> {
    private ArrayList<String> incorrect;

    public static class kanaViewHolder extends RecyclerView.ViewHolder {
        public TextView kana;
        public kanaViewHolder(TextView v) {
            super(v);
            kana = v;
        }
    }

    public kanaAdapter(ArrayList<String> in) {
        incorrect = in;
    }

    @Override
    public kanaAdapter.kanaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.kana_text_view, parent, false);
        kanaViewHolder vh = new kanaViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(kanaViewHolder holder, int position) {
        holder.kana.setText(incorrect.get(position));
    }

    @Override
    public int getItemCount() {
        return incorrect.size();
    }
}
