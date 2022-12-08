package vjabuilds.dev.services.pipeline_crud_models;

import java.util.List;

public record PipelineDetailsModel(
    Long id,
    String description,
    String name,
    String version,
    String yamlFormat,
    List<Long> childDatasets) {}
