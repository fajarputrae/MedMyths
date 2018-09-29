package com.coinbkt.medmyth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinbkt.medmyth.R;
import com.coinbkt.medmyth.db.FMLibrary;

public class CardAdapter extends ArrayAdapter<FMLibrary> {

    public CardAdapter(Context context){
        super(context, 0);
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        ViewHolder holder;

        if (contentView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            contentView = inflater.inflate(R.layout.card_view, parent, false);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        FMLibrary fmLibrary = getItem(position);

        holder.info.setText(fmLibrary.getTitle());
        Glide.with(getContext()).load(fmLibrary.getImg()).into(holder.image);

        return contentView;
    }

    private static class ViewHolder {
        public TextView info;
        public ImageView image;

        public ViewHolder(View view) {
            this.info = (TextView) view.findViewById(R.id.tvTitle);
            this.image = (ImageView) view.findViewById(R.id.ivMedicalInfo);
        }
    }
}
