package com.mumbaitourist.tourist.mumbaitour;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder> {
    private static final String LOG_TAG = PlacesAdapter.class.getSimpleName();
    private List<String> placesList;

    public PlacesAdapter(List<String> placesList) {
        this.placesList = placesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView placeName;

        public MyViewHolder(View view) {
            super(view);
            placeName = (TextView) view.findViewById(R.id.place_name_text);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String placeNameText = placesList.get(position);
        holder.placeName.invalidate();
        holder.placeName.setText(placeNameText);
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }
}
