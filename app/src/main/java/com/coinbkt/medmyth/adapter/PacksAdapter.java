package com.coinbkt.medmyth.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.coinbkt.medmyth.AfterGameActivity;
import com.coinbkt.medmyth.GameActivity;
import com.coinbkt.medmyth.R;
import com.coinbkt.medmyth.db.Packs;
import com.coinbkt.medmyth.utils.SPMedmyth;

import java.util.List;

public class PacksAdapter extends RecyclerView.Adapter<PacksAdapter.ViewHolder> {

    Context mContext;
    private List<Packs> packsList;

    final Boolean isFx = SPMedmyth.getIsFX(mContext);
    final MediaPlayer mp = MediaPlayer.create(mContext,R.raw.click);

    MaterialDialog alertDialog;

    public PacksAdapter(Context context, List<Packs> packsList) {
        this.mContext = context;
        this.packsList = packsList;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return packsList.size();
    }

    @NonNull
    @Override
    public PacksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pack_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PacksAdapter.ViewHolder holder, int position) {

        final Packs packs = packsList.get(position);

        holder.tvPackName.setText(packs.getPackName());
        holder.tvPackPoints.setText(packs.getPackPoints() + " %");
        holder.ivPackImage.setImageResource(packs.getPackImage());

        if(packs.getPackStatus().equals("Locked")){
            holder.ivImageShadow.setVisibility(View.VISIBLE);
            holder.ivLock.setVisibility(View.VISIBLE);
            holder.ivPackImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isFx)
                        mp.start();
                    showAlertDialog("Ups!", "Poin kamu masih belum cukup, silahkan coba lagi :)");
                }
            });
        }
        else{
            holder.ivPackImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isFx)
                        mp.start();
                    Intent i = new Intent(mContext, GameActivity.class);
                    i.putExtra("name", packs.getPackName());
                    i.putExtra("idPack", packs.getIdPack());
                    mContext.startActivity(i);
                    ((Activity)mContext).finish();
                }
            });
        }



    }
    /** References to the views for each data item **/
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPackName, tvPackPoints;
        ImageView ivPackImage, ivLock, ivImageShadow;

        ViewHolder(View itemView) {
            super(itemView);
            tvPackName = (TextView) itemView.findViewById(R.id.packName);
            tvPackPoints = (TextView) itemView.findViewById(R.id.packPoints);
            ivPackImage = (ImageView) itemView.findViewById(R.id.packImage);
            ivLock = (ImageView) itemView.findViewById(R.id.lock);
            ivImageShadow = (ImageView) itemView.findViewById(R.id.imgShadow);
        }
    }

    protected void showAlertDialog(String title, String message) {
        alertDialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .content(message)
                .positiveText("OK")
                .callback(new MaterialDialog.ButtonCallback() {

                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        dialog.dismiss();
                    }
                }).build();
        alertDialog.show();
    }
}
