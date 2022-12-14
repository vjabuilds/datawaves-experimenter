package vjabuilds.dev.services.experiment_crud_models;

public record ExperimentCreateModel(
    String name,
    String description,
    Long datasetId,
    Long modelId
) {}
