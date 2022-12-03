package vjabuilds.dev.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dataset {
    private Long datasetId;
    private String name;
    private String source;
    private String description;
    private String type; // can be structured, text, time_series or image
    private Long parentDatasetId;
    private Long parentPipelineId;
}
