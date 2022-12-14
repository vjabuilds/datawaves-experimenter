package vjabuilds.dev.services.experiment_crud_models;

import vjabuilds.dev.models.Artifact;
import vjabuilds.dev.value_objects.ArtifactType;

public record ArtifactListModel(
    Long artifactId,
    String name,
    String path,
    String description,
    ArtifactType type
)
{
    public ArtifactListModel(Artifact art)
    {
        this(art.getArtifactId(), art.getName(), art.getPath(), art.getDescription(), art.getType());
    }
}