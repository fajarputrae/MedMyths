package com.coinbkt.medmyth.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coinbkt.medmyth.FMDetailActivity;
import com.coinbkt.medmyth.R;
import com.coinbkt.medmyth.db.FMLibrary;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FMAdapter extends RecyclerView.Adapter<FMAdapter.ViewHolder> {

    Context mContext;
    private List<FMLibrary> fmLibraryList;

    public FMAdapter(Context context, List<FMLibrary> fmLibraryList) {
        this.mContext = context;
        this.fmLibraryList = fmLibraryList;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return fmLibraryList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FMLibrary fmLibrary = fmLibraryList.get(position);

        holder.titleFacts.setText(fmLibrary.getTitle());
        holder.imgFacts.setImageResource(fmLibrary.getThumbImg());

        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, FMDetailActivity.class);
                i.putExtra("category", fmLibrary.getCategory());
                i.putExtra("title", fmLibrary.getTitle());
                i.putExtra("desc", fmLibrary.getDesc());
                mContext.startActivity(i);
            }
        });
    }

    /** References to the views for each data item **/
    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvContainer;
        TextView titleFacts;
        ImageView imgFacts;

        ViewHolder(View itemView) {
            super(itemView);
            cvContainer = (CardView) itemView.findViewById(R.id.cvContainer);
            titleFacts = (TextView) itemView.findViewById(R.id.titleFacts);
            imgFacts = (ImageView) itemView.findViewById(R.id.imgFacts);
        }
    }
}