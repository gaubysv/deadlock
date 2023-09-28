package com.demo.deadlock.data;

import com.demo.deadlock.entity.Property;

import static com.demo.deadlock.constant.ApplicationConstants.SIMILARITY_CHECK;

public class PropertyTestData {

    public static Property getSimilarityCheckProperty() {
        return Property.builder()
                .key(SIMILARITY_CHECK)
                .build();
    }
}
