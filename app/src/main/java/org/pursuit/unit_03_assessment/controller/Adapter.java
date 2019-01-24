package org.pursuit.unit_03_assessment.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.unit_03_assessment.view.PlanetViewHolder;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<PlanetViewHolder> {

    List<String> planetList;

    public Adapter(List<String> planetList) {
        this.planetList = planetList;
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler, viewGroup, false);
        return new PlanetViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder planetViewHolder, int i) {
        planetViewHolder.onBind(planetList.get(i));
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }
}
