package com.example.coreshieldassessment.model;


import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class MergedLocation {

    private Location location;
    private Metadata metadata;

    public MergedLocation(Location location, Metadata metadata) {
        this.location = location;
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Location ID: " + location.getId() + ", Type: " + metadata.getType() +
                ", Rating: " + metadata.getRating() + ", Reviews: " + metadata.getReviews();
    }

}
