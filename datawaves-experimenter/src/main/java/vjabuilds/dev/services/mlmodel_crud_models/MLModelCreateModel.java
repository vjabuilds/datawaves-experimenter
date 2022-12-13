package vjabuilds.dev.services.mlmodel_crud_models;

import vjabuilds.dev.value_objects.DatasetType;
import vjabuilds.dev.value_objects.TaskType;

public record MLModelCreateModel(
    TaskType taskType,
    DatasetType datasetType,
    String name,
    String description,
    String yamlFormat
) {}
