package dev.vjabuilds.datawavesexperimenter.models;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MLModel extends BaseModel {
    @Id
    private Long mlModelId;

    private String name;
    private String description;
    private String version;
    private String configFilePath;
    private List<Integer> inputShape;
    private List<Integer> outputShape;
}
