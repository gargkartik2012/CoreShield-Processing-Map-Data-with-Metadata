package com.example.coreshieldassessment.model;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {


    private String id;
    private double latitude;
    private double longitude;
}
