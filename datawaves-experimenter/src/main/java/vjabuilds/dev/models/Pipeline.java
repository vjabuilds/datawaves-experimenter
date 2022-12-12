package vjabuilds.dev.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Pipeline extends BaseModel {
    @Id @GeneratedValue private Long pipelineId;
    private String description;
    private String name;
    private String version;
    private String yamlFormat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentPipeline")
    private List<Dataset> childDatasets;
}
