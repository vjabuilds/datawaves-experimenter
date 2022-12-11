package vjabuilds.dev.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Experiment extends BaseModel {
    @Id @GeneratedValue private Long datasetId;
    private String name;
    private String description;

    @ManyToOne
    private MLModel model;
    @ManyToOne
    private Dataset dataset;
    @OneToMany
    private List<Artifact> artifacts;
}
