package vjabuilds.dev.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import vjabuilds.dev.models.Dataset;
import vjabuilds.dev.models.Pipeline;
import vjabuilds.dev.repos.PipelineRepo;
import vjabuilds.dev.services.pipeline_crud_models.PipelineCreateModel;
import vjabuilds.dev.services.pipeline_crud_models.PipelineDetailsModel;
import vjabuilds.dev.services.pipeline_crud_models.PipelineListModel;
import vjabuilds.dev.value_objects.DatasetType;

@QuarkusTest
public class PipelineCrudServiceTest {
    @InjectMock PipelineRepo repo;
    @Inject PipelineCrudService service;

    public static List<Pipeline> setupPipelines()
    {
        List<Pipeline> result = new ArrayList<>();
        Long i;
        for(i=0l;i<10;i++)
        {
            result.add(new Pipeline(
                i,
                "description" + i.toString(),
                "name" + i.toString(),
                "version" + i.toString(),
                "yaml" + i.toString(),
                null 
            ));
        }

        Pipeline p = new Pipeline(
            i,
            "description" + i.toString(),
            "name" + i.toString(),
            "version" + i.toString(),
            "yaml" + i.toString(),
            null
        );

        Dataset parent = new Dataset(
            0l,
            "parent_ds",
            "parent_src",
            "parent_desc",
            DatasetType.STRUCTURED,
            null,
            null,
            null,
            null
        );

        List<Dataset> datasets = new ArrayList<Dataset>();
        for(Long j=1l;j<=3;j++)
            datasets.add(new Dataset(
                j,
                "child_ds" + j,
                "child_src" + j,
                "child_desc" + j,
                DatasetType.STRUCTURED,
                parent,
                p,
                null,
                null
            ));
        p.setChildDatasets(datasets);
        result.add(p);
        return result;
    }

    private static final List<Pipeline> pipelines = setupPipelines();

    @BeforeEach
    public void setup() {
        Mockito.when(repo.listAll()).thenReturn(Uni.createFrom().item(pipelines));
        for(long i=0l;i<11;i++)
            Mockito.when(repo.findById(i)).thenReturn(Uni.createFrom().item(pipelines.get((int)i)));
    }

    @Test
    public void testGetAll() throws Throwable {
        var result = service.getPipelines().await().indefinitely();
        Assertions.assertEquals(result.size(), 11);
        for(int i=0;i<11;i++)
            Assertions.assertEquals(result.get(i), new PipelineListModel(pipelines.get(i)));
    }

    @ParameterizedTest
    @ValueSource(longs = {1l, 2l, 3l, 10l})
    public void testGetDetails(Long id) throws Throwable {
        var result = service.getPipelineDetails(id).await().indefinitely();
        if(id == 10)
            Assertions.assertEquals(result.childDatasets().size(), 3);
        else
            Assertions.assertEquals(result.childDatasets(), List.of());
    }

    @Test
    public void testInsert() throws Throwable {
        PipelineCreateModel pcm = new PipelineCreateModel("description", "name", "version", "format");
        Pipeline p = new Pipeline(null, pcm.description(), pcm.name(), pcm.version(), pcm.yamlFormat(), null);
        Pipeline target = new Pipeline(12l, 
                                        pcm.description(), 
                                        pcm.name(), 
                                        pcm.version(), 
                                        pcm.yamlFormat(), 
                                        null);
        Mockito.when(repo.persist(p))
            .thenReturn(Uni.createFrom().item(target));
        Mockito.when(repo.findById(target.getPipelineId())).thenReturn(Uni.createFrom().item(target));
        var result = service.createPipeline(pcm).await().indefinitely();
        Assertions.assertEquals(result, new PipelineDetailsModel(
            12l, pcm.description(), pcm.name(), pcm.version(), pcm.yamlFormat(), List.of()
        ));
    }
}
