package vjabuilds.dev.services.experiment_crud_models;

import vjabuilds.dev.models.Experiment;

public record ExperimentListModel (
    Long experimentId,
    String name,
    String description,
    String datasetName,
    Long datasetId,
    String mlModelName,
    Long mlModeId
) {
    public ExperimentListModel(Experiment model)
    {
        this(model.getExperimentId(), 
            model.getName(),
            model.getDescription(),
            model.getDataset().getName(),
            model.getDataset().getDatasetId(),
            model.getModel().getName(),
            model.getModel().getMlModelId());
    }
}
