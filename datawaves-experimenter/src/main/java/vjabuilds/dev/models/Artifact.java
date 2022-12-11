package vjabuilds.dev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vjabuilds.dev.value_objects.ArtifactType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Artifact extends BaseModel {
    @Id @GeneratedValue private Long artifactId;
    private String name;
    private String path;
    private String description;
    private ArtifactType type;
    @ManyToOne
    @JoinColumn(name = "experimentId")
    private Experiment experiment;
}
