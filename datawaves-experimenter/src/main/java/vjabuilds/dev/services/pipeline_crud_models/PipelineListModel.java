package vjabuilds.dev.services.pipeline_crud_models;

import vjabuilds.dev.models.Pipeline;

public record PipelineListModel(
    Long id,
    String name, 
    String version, 
    String description, 
    String yamlFormat) {
        public PipelineListModel(Pipeline p)
        {
            this(p.getPipelineId(),
            p.getName(),
            p.getVersion(),
            p.getDescription(),
            p.getYamlFormat());
        }
    }
