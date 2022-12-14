package vjabuilds.dev.services.experiment_crud_models;

import java.util.List;

import vjabuilds.dev.services.dataset_crud_models.DatasetDetailsModel;
import vjabuilds.dev.services.mlmodel_crud_models.MLModelDetailsModel;

public record ExperimentDetailsModel (
    Long experimentId,
    String name,
    String description,
    DatasetDetailsModel dataset,
    MLModelDetailsModel model,
    List<ArtifactListModel> artifacts
) {}
