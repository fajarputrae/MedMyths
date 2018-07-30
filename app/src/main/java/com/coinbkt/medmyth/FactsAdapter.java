package com.coinbkt.medmyth;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Facts> items;


    /** References to the views for each data item **/
    public class FactsViewHolder extends RecyclerView.ViewHolder{
        public TextView titleView;
        public ImageView imgView;

        public FactsViewHolder(View v) {
            super(v);

            titleView = v.findViewById(R.id.titleFacts);
            imgView = v.findViewById(R.id.imgFacts);
        }
    }

    /** Constructor **/
    public FactsAdapter(List<Facts> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.facts_view, parent, false);

        return new FactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Facts item = items.get(position);
        FactsViewHolder vh = (FactsViewHolder) holder;
        vh.titleView.setText(item.getFacts());
        vh.imgView.setImageResource(item.getThumbnail());
    }
}