package dev.vjabuilds.datawavesexperimenter.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Experiment extends BaseModel {
    @Id
    private Long experiment_id;
    private String name;
    private String description;
    private Long mlModelId;
    private Long datasetId;
}
