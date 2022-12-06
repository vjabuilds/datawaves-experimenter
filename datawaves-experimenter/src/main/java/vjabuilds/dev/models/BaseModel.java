package vjabuilds.dev.models;

import java.time.ZonedDateTime;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MappedSuperclass
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
