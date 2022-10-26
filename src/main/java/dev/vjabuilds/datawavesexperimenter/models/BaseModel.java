package dev.vjabuilds.datawavesexperimenter.models;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public abstract class BaseModel {
    protected ZonedDateTime created;
    protected ZonedDateTime updated;
    protected ZonedDateTime deleted;

    public BaseModel()
    {
        created = ZonedDateTime.now();
        updated = ZonedDateTime.now();
        deleted = null;
    }
}
