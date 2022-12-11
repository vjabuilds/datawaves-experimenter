package vjabuilds.dev.services.dataset_crud_models;

import io.quarkus.runtime.annotations.RegisterForReflection;
import vjabuilds.dev.value_objects.DatasetType;

@RegisterForReflection
public record DatasetCreateModel(String name,
String source,
String description,
DatasetType type) {}
