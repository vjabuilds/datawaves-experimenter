package vjabuilds.dev.value_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ArtifactType {
    @JsonProperty("weights") WEIGHTS,
    @JsonProperty("metrics") METRICS,
    @JsonProperty("activations") ACTIVATIONS
}
