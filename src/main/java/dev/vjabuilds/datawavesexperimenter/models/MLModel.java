package dev.vjabuilds.datawavesexperimenter.models;

import java.util.List;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class MLModel extends BaseModel {
    @Id
    private Long mlmodelId;
    private String name;
    private String description;
    private String version;
    private String configFilePath;
    private List<Integer> inputShape;
    private List<Integer> outputShape;
}
