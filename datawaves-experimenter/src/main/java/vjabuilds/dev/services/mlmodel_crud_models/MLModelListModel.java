package vjabuilds.dev.services.mlmodel_crud_models;

import vjabuilds.dev.models.MLModel;
import vjabuilds.dev.value_objects.DatasetType;
import vjabuilds.dev.value_objects.TaskType;

public record MLModelListModel(
    Long mlModelId,
    TaskType taskType,
    DatasetType datasetType,
    String name,
    String description,
    String yamlFormat
) {
    public MLModelListModel(MLModel model)
    {
        this(model.getMlModelId(), 
            model.getTaskType(),
            model.getDatasetType(),
            model.getName(),
            model.getDescription(),
            model.getYamlFormat()
            );
    }
}
