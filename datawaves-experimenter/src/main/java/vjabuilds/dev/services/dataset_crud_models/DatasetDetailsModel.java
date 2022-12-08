package vjabuilds.dev.services.dataset_crud_models;

import java.util.List;

import vjabuilds.dev.services.pipeline_crud_models.PipelineListModel;
import vjabuilds.dev.value_objects.DatasetType;

public record DatasetDetailsModel(
    Long id,
    String name,
    String source,
    String description,
    DatasetType type,
    DatasetListModel parentDataset,
    PipelineListModel parentPipeline,
    List<DatasetListModel> childDatasets) {}
