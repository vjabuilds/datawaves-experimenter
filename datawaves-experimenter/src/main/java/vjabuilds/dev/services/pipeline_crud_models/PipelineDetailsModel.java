package vjabuilds.dev.services.pipeline_crud_models;

import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;
import vjabuilds.dev.services.dataset_crud_models.DatasetListModel;

@RegisterForReflection
public record PipelineDetailsModel(
    Long id,
    String description,
    String name,
    String version,
    String yamlFormat,
    List<DatasetListModel> childDatasets) {}
