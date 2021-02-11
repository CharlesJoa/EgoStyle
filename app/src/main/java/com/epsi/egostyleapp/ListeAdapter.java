package com.epsi.egostyleapp;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ListeAdapter extends RecyclerView.Adapter<ListeAdapter.MyViewHolder> {

    // Create the attribute of the list for the coupons
    private final List<Bon> m_coupons;
    private static Context context;

    public ListeAdapter(ArrayList<Bon> coupons, Context context) {
        m_coupons = coupons;
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return m_coupons.size();
    }

    // Set the content view with the cell list view
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    // Method who describe for each element the behavior (get the current element and display)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bon bon = m_coupons.get(position);
        holder.display(bon);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView promotion;
        private final TextView date_limite;
        private final LinearLayout layout;

        private Bon currentBon;

        public MyViewHolder(final View itemView) {
            super(itemView);

            promotion = ((TextView) itemView.findViewById(R.id.list_cell_Reduc));
            date_limite = ((TextView) itemView.findViewById(R.id.list_cell_DateLimite));
            layout = ((LinearLayout) itemView.findViewById(R.id.layout_list));

            // Create a listener when you click, a window spawns
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentBon.getdescription())
                            .setMessage("Expire le : " + currentBon.getdate_limite())
                            .show();
                }
            });
        }

        // Method who manage the display
        public void display(Bon bon) {
            currentBon = bon;
            TextView toto = itemView.findViewById(R.id.list_cell_Reduc);
            TextView titi = itemView.findViewById(R.id.list_cell_DateLimite);

            LinearLayout.LayoutParams paramsExample = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams paramsExample2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            if (bon.getType() != null ){
                // Style of the bon's desc line
                toto.setTypeface(Typeface.create("sans-serif-black",Typeface.NORMAL));
                toto.setBackgroundColor(Color.WHITE);
                toto.setTextColor(Color.BLACK);
                toto.setTextSize(20);
                paramsExample.topMargin = 80;
                toto.setPadding(5,10,5,10);
                paramsExample.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                toto.setLayoutParams(paramsExample);

                // Style of the bon's date_limite line
                paramsExample2.leftMargin = 750;
                titi.setTextColor(Color.WHITE);
                titi.setTypeface(null, Typeface.BOLD);
                titi.setLayoutParams(paramsExample2);

                // Gets linearlayout
                LinearLayout layout = itemView.findViewById(R.id.layout_list);
                // Gets the layout params that will allow you to resize the layout
                LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                // Changes the height and width to the specified *pixels*
                params.height = 350;
                params.setMargins(30,15,30,15);
                layout.setLayoutParams(params);

                if (bon.getType().contentEquals("jean")) {
                    layout.setBackground(context.getResources().getDrawable(R.drawable.jeans));
                } else if (bon.getType().contentEquals("skate")) {
                    layout.setBackground(context.getResources().getDrawable(R.drawable.skate));
                } else if (bon.getType().contentEquals("t-shirt")) {
                    layout.setBackground(context.getResources().getDrawable(R.drawable.tshirt));
                }
            }

            promotion.setText(bon.getdescription());
            String date = bon.getdate_limite();
            try {
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                String dateP = formatter1.format(date2);

                date_limite.setText("Date d'expiration : " + dateP);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
