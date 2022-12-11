package vjabuilds.dev.services.pipeline_crud_models;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record PipelineCreateModel(
    String description,
    String name,
    String version,
    String yamlFormat
) {}
