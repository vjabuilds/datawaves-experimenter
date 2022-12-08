package vjabuilds.dev.services.dataset_crud_models;

import vjabuilds.dev.value_objects.DatasetType;

public record DatasetCreateModel(String name,
String source,
String description,
DatasetType type) {}
