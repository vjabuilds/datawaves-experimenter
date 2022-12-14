package vjabuilds.dev.services.dataset_crud_models;

import io.quarkus.runtime.annotations.RegisterForReflection;
import vjabuilds.dev.models.Dataset;
import vjabuilds.dev.value_objects.DatasetType;

@RegisterForReflection
public record DatasetListModel(
    Long id,
    String name,
    String source,
    String description,
    DatasetType type) {
        public DatasetListModel(Dataset ds)
        {
            this(ds.getDatasetId(), 
                ds.getName(), 
                ds.getSource(),
                ds.getDescription(),
                ds.getType());
        }
    }
