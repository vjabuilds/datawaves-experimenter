package vjabuilds.dev.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vjabuilds.dev.value_objects.DatasetType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Dataset extends BaseModel {
    @Id @GeneratedValue private Long datasetId;
    private String name;
    private String source;
    private String description;
    private DatasetType type;

    @ManyToOne
    @JoinColumn(name="parentDatasetId")
    private Dataset parentDataset;
    @ManyToOne
    @JoinColumn(name="parentPipelineId")
    private Pipeline parentPipeline;

    @OneToMany(mappedBy = "parentDataset")
    private List<Dataset> childrenDatasets;
}
