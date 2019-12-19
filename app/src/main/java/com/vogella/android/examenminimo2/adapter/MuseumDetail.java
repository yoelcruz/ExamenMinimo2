package com.vogella.android.examenminimo2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.vogella.android.examenminimo2.R;
import com.vogella.android.examenminimo2.models.Element;


import java.util.List;


public class MuseumDetail extends RecyclerView.Adapter<MuseumDetail.ElementAdapterHolder>{


    private List<Element> elements;
    private Context context;


    public MuseumDetail(Context context, List<Element> elements) {
        this.context = context;
        this.elements = elements;
    }


    @NonNull
    @Override
    public ElementAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_element, parent, false);
        return new ElementAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementAdapterHolder holder, int position) {
        /*Element element = elements.get(position);
        holder.nombreMuseo.setText(element.getAdrecaNom());*/
        holder.nombreMuseo.setText(elements.get(position).getAdrecaNom());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(elements.get(position).getImatge().get(0))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public void setData(List<Element> elements){
        this.elements = elements;
        notifyDataSetChanged();
    }

    public class ElementAdapterHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView nombreMuseo;

        public ElementAdapterHolder (@NonNull View itemView){
            super(itemView);
            imagen = itemView.findViewById(R.id.imageView);
            nombreMuseo = itemView.findViewById(R.id.textView);
        }
    }
}
