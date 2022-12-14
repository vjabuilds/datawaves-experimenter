package vjabuilds.dev.services.experiment_crud_models;

import vjabuilds.dev.value_objects.ArtifactType;

public record ArtifactCreateModel(
    String name,
    String path,
    String description,
    ArtifactType type,
    Long experimentId
){}
