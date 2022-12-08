package vjabuilds.dev.services.pipeline_crud_models;

import java.util.List;

import vjabuilds.dev.services.dataset_crud_models.DatasetListModel;

public record PipelineDetailsModel(
    Long id,
    String description,
    String name,
    String version,
    String yamlFormat,
    List<DatasetListModel> childDatasets) {}
