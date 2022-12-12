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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Deployment extends BaseModel {
    @Id @GeneratedValue private Long deploymentId;
    private String name;
    private String URL;
    private Boolean REST;
    private Boolean protoBuf;
    private Boolean flink;
    private String schedule;


    @ManyToOne
    @JoinColumn(name = "artifactId")
    private Artifact weights;
    @ManyToOne
    @JoinColumn(name = "modelId")
    private MLModel model;
}
