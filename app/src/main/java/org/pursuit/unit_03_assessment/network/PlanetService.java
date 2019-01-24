package org.pursuit.unit_03_assessment.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlanetService {
    @GET("planets")
    Call<Planets> getDogBreeds();

    @GET("api/breed/{type}/images/random")
    Call<Dog> getDogImage(@Path("type") String breed);
}
}
