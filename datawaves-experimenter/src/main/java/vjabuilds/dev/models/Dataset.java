package vjabuilds.dev.models;

import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dataset extends PanacheEntity {
    private Long datasetId;
    private String name;
    private String source;
    private String description;
    private String type; // can be structured, text, time_series or image
    private Long parentDatasetId;
    private Long parentPipelineId;
}
