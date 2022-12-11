package vjabuilds.dev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Deployment extends BaseModel {
    @Id @GeneratedValue private Long datasetId;
    private String name;
    private String URL;
    private Boolean REST;
    private Boolean protoBuf;
    private Boolean flink;
    private String schedule;


    @ManyToOne
    private Artifact weights;
    @ManyToOne
    private MLModel model;
}
