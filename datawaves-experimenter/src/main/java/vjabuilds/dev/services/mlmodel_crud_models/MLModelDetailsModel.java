package vjabuilds.dev.services.mlmodel_crud_models;

import vjabuilds.dev.value_objects.DatasetType;
import vjabuilds.dev.value_objects.TaskType;

public record MLModelDetailsModel(
    Long mlModelId,
    TaskType taskType,
    DatasetType datasetType,
    String name,
    String description,
    String yamlFormat
) {}
