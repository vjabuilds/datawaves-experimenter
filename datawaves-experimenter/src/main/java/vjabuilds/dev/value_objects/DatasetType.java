package vjabuilds.dev.value_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DatasetType {
    @JsonProperty("structured") STRUCTURED,
    @JsonProperty("image") IMAGE,
    @JsonProperty("text") TEXT,
    @JsonProperty("timeseries") TIMESERIES,
    @JsonProperty("video") VIDEO
}
