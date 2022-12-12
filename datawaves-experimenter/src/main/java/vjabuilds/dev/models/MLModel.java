package vjabuilds.dev.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vjabuilds.dev.value_objects.DatasetType;
import vjabuilds.dev.value_objects.TaskType;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class MLModel extends BaseModel {
    @Id @GeneratedValue private Long mlModelId;
    private TaskType taskType;
    private DatasetType datasetType;
    private String name;
    private String description;
    private String yamlFormat;

    @OneToMany(mappedBy = "model")
    private List<Experiment> experiments;
}
