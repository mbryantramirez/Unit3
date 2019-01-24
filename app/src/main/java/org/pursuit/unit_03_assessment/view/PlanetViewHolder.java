package org.pursuit.unit_03_assessment.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.pursuit.unit_03_assessment.DisplayActivity;
import org.pursuit.unit_03_assessment.Model.Planets;
import org.pursuit.unit_03_assessment.network.PlanetService;
import org.pursuit.unit_03_assessment.network.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlanetViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "image_call";
    private SharedPreferences sharedPreferences;
    private TextView planetTextView;
    private TextView planetTextView2;

    //^?
    private Intent intent;

    public PlanetViewHolder(@NonNull View itemView) {
        super(itemView);
        planetTextView2 = itemView.findViewById(R.planet_number);
        sharedPreferences = itemView.getContext().getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    public void onBind(final String planet) {
        planetTextView.setText(planet);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(itemView.getContext(), DisplayActivity.class);
                intent.putExtra("breed", planet);
                Log.d(TAG, String.valueOf(sharedPreferences.contains((planet + "_image"))));
                if (sharedPreferences.contains((planet + "_image"))) {
                    intent.putExtra("image", sharedPreferences.getString((planet + "_image"), null));
                    itemView.getContext().startActivity(intent);
                } else {
                    Retrofit retrofit = RetrofitSingleton.getInstance();
                    PlanetService dogService = retrofit.create(PlanetService.class);
                    Call<Planets> planets = PlanetService.getPlanetImage(planetNane);
                    planets.enqueue(new Callback<Planets>() {
                        @Override
                        public void onResponse(Call<Planets> call, Response<Planets> response) {
                            Log.d(TAG, "onResponse: " + response.body().getMessage());
                            intent.putExtra("image", response.body().getMessage());
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(planet+"_image", response.body().getMessage());
                            editor.commit();
                            itemView.getContext().startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Planets> call, Throwable t) {
                            Log.d(TAG, "onResponse: " + t.toString());
                        }
                    });
                }
            }
        });
    }
}
