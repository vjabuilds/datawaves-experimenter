package dev.vjabuilds.datawavesexperimenter.models;

import java.util.Date;

import lombok.Data;

@Data
public abstract class BaseModel {
    protected Date created;
    protected Date updated;
    protected Date deleted;

    public BaseModel()
    {
        created = new Date();
        updated = new Date();
        deleted = null;
    }
}
