package com.epsi.egostyleapp;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ListeAdapter extends RecyclerView.Adapter<ListeAdapter.MyViewHolder> {

    // Create the attribute of the list for the coupons
    private List<Pair<String, String>> m_coupons;

    public ListeAdapter(ArrayList<Pair<String, String>> coupons){
        m_coupons = coupons;
    };


    @Override
    public int getItemCount() {
        return m_coupons.size();
    }

    // Set the content view with the cell list view
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    // Method who describe for each element the behavior (get the current element and display)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> pair = m_coupons.get(position);
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

            // Create a listener when you click, a window spawns
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

        // Method who manage the display
        public void display(Pair<String, String> pair) {
            currentPair = pair;
            promotion.setText(pair.first);
            String date = pair.second;
            try {
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
                String dateP = formatter1.format(date2);

                date_limite.setText("Date d'expiration : "+ dateP);
            } catch (Exception e) {
                e.printStackTrace();
            }



        }
    }
}
