package vjabuilds.dev.services.pipeline_crud_models;

public record PipelineCreateModel(
    String description,
    String name,
    String version,
    String yamlFormat
) {}
