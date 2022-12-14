package vjabuilds.dev.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Experiment extends BaseModel {
    @Id @GeneratedValue private Long experimentId;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mlModelId")
    private MLModel model;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "datasetId")
    private Dataset dataset;
    @OneToMany(mappedBy = "experiment")
    private List<Artifact> artifacts;
}
