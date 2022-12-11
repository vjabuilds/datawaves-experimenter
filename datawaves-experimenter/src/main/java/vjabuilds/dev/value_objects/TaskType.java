package vjabuilds.dev.value_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TaskType {
    @JsonProperty("classification") CLASSIFICATION,
    @JsonProperty("regression") REGRESSION,
    @JsonProperty("clustering") CLUSTERING,
    @JsonProperty("generation") GENERATION
}
