package org.pursuit.unit_03_assessment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlanetName {



        @SerializedName("planets")
        @Expose
        private List<Planets> planets = null;

        public List<Planets> getPlanets() {
            return planets;
        }

        public void setPlanets(List<Planets> planets) {
            this.planets = planets;
        }


}
