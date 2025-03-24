package com.example.coreshieldassessment.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {


    private String id;
    private String type;
    private double rating;
    private int reviews;
}
