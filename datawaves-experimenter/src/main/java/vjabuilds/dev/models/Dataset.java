package vjabuilds.dev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dataset extends BaseModel {
    @Id @GeneratedValue private Long datasetId;
    private String name;
    private String source;
    private String description;
    private String type;
    private Long parentDatasetId;
    private Long parentPipelineId;
}
