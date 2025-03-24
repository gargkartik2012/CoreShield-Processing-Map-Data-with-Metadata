package com.example.coreshieldassessment.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.coreshieldassessment.model.Location;
import com.example.coreshieldassessment.model.MergedLocation;
import com.example.coreshieldassessment.model.Metadata;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MapDataService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void processMapData() throws Exception {


        InputStream locationStream = getClass().getClassLoader().getResourceAsStream("locations.json");
        InputStream metadataStream = getClass().getClassLoader().getResourceAsStream("metadata.json");

        if (locationStream == null || metadataStream == null) {
            throw new RuntimeException(" JSON files not found in resources folder!");
        }

        List<Location> locations = objectMapper.readValue(locationStream, new TypeReference<>() {});
        List<Metadata> metadataList = objectMapper.readValue(metadataStream, new TypeReference<>() {});


        Map<String, Location> locationMap = locations.stream()
                .collect(Collectors.toMap(Location::getId, loc -> loc));

        // Merge data
        List<MergedLocation> mergedLocations = metadataList.stream()
                .filter(m -> locationMap.containsKey(m.getId())) // Filter for matching IDs
                .map(m -> new MergedLocation(locationMap.get(m.getId()), m))
                .toList();

        // Count valid points per type
        Map<String, Long> countPerType = mergedLocations.stream()
                .collect(Collectors.groupingBy(m ->
                                (m.getMetadata() != null && m.getMetadata().getType() != null) ? m.getMetadata().getType() : "Unknown",
                        Collectors.counting()
                ));


        // Calculate average rating per type
        Map<String, Double> avgRatingPerType = mergedLocations.stream()
                .filter(m -> m.getMetadata() != null && m.getMetadata().getRating() > 0)
                .collect(Collectors.groupingBy(m ->
                                (m.getMetadata().getType() != null) ? m.getMetadata().getType() : "Unknown",
                        Collectors.averagingDouble(m -> m.getMetadata().getRating())
                ));


        // Find location with the highest reviews
        MergedLocation highestReviewedLocation = mergedLocations.stream()
                .filter(m -> m.getMetadata() != null)
                .max(Comparator.comparingInt(m -> m.getMetadata().getReviews()))
                .orElse(null);


        // Identify incomplete data
        List<MergedLocation> incompleteData = mergedLocations.stream()
                .filter(m -> m.getMetadata() == null ||
                        m.getMetadata().getType() == null ||
                        m.getMetadata().getRating() == 0 ||
                        m.getMetadata().getReviews() == 0)
                .toList();



        // the output for the provided JSON will be shown in terminal when application run
        System.out.println(" Load parse both JSON files:"+ mergedLocations);
        System.out.println(" Count per type: " + countPerType);
        System.out.println(" Average rating per type: " + avgRatingPerType);
        System.out.println(" Highest reviewed location: " + highestReviewedLocation);
        System.out.println(" Incomplete data: " + incompleteData);
    }
}
