package com.epsi.egostyleapp;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListeAdapter extends RecyclerView.Adapter<ListeAdapter.MyViewHolder> {
    private List<Pair<String, String>> reductions = Arrays.asList(
            Pair.create("-20% sur votre jean préférédgdffgdfgfgdgdfgdfgd", "2020-06-15"),
            Pair.create("-40% sur toutes les vestes", "2020-06-17"),
            Pair.create("10% de réduction pour 2 articles achetés", "2020-07-23")
    );
    private List<Pair<String, String>> m_coupons;
    public ListeAdapter(ArrayList<String> toto, ArrayList<String> dates){
//        for (int i = 0; i<toto.size(); i++){
//            m_coupons.add(Pair.create(toto.get(i),dates.get(i)));
//        }
    };
    @Override
    public int getItemCount() {
        return reductions.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> pair = reductions.get(position);
        holder.display(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView promotion;
        private final TextView date_limite;

        private Pair<String, String> currentPair;

        public MyViewHolder(final View itemView) {
            super(itemView);

            promotion = ((TextView) itemView.findViewById(R.id.list_cell_Reduc));
            date_limite = ((TextView) itemView.findViewById(R.id.list_cell_DateLimite));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentPair.first)
                            .setMessage("Expire le : "+currentPair.second)
                            .show();
                }
            });
        }

        public void display(Pair<String, String> pair) {
            currentPair = pair;
            promotion.setText(pair.first);
            date_limite.setText("Date d'expiration : "+ pair.second);
        }
    }
}
