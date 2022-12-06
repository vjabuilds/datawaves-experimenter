package vjabuilds.dev.models;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class BaseModel {
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private ZonedDateTime deleted;

    public BaseModel()
    {
        created = ZonedDateTime.now();
        updated = ZonedDateTime.now();
        deleted = ZonedDateTime.now();
    }
}
