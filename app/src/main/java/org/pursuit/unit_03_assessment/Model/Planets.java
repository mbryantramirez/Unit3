package org.pursuit.unit_03_assessment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Planets {
    private String name;
    private Integer number;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}







//{"planets":[
//    {"name": "Mercury",
//    "number": 1,
//    "image": "https://www.go-astronomy.com/images/planets/planet-mercury.jpg"},
//    {"name": "Venus",
//    "number": 2,
//    "image": "http://blogs.scientificamerican.com/guest-blog/files/2012/06/Venus.jpg"},
//    {"name": "Earth",
//    "number": 3,
//    "image": "https://cdn.britannica.com/700x450/25/160325-004-AD594C66.jpg"},
//    {"name": "Mars",
//    "number": 4,
//    "image": "http://davidmkelly.net/wp-content/uploads/2013/09/planet-mars.jpg"},
//    {"name": "Jupiter",
//    "number": 5,
//    "image": "http://www.clker.com/cliparts/d/c/e/d/12786361781616141935jupiter.png"},
//    {"name": "Saturn",
//    "number": 6,
//    "image": "https://www.yourdictionary.com/images/definitions/lg/8616.saturn.jpg"},
//    {"name": "Uranus",
//    "number": 7,
//    "image": "https://www.startrekdb.se/astronomi/bilder/uranus2.jpg"},
//    {"name": "Neptune",
//    "number": 8,
//    "image": "http://imgc.allpostersimages.com/images/P-473-488-90/37/3792/96AIF00Z/posters/excellent-narrow-angle-camera-views-of-the-planet-neptune-taken-from-voyager-2-spacecraft.jpg"}
//  ]
//}