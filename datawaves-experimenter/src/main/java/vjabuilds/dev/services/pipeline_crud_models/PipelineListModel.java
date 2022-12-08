package vjabuilds.dev.services.pipeline_crud_models;

public record PipelineListModel(
    Long id,
    String name, 
    String version, 
    String description, 
    String yamlFormat) {}
