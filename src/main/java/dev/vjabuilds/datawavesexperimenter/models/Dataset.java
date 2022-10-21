package dev.vjabuilds.datawavesexperimenter.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dataset {
    @Id
    private Long dataset_id;
    private String name;
    private String source;
    private String description;
    private String type; // can be structured, text, time_series or image
}
