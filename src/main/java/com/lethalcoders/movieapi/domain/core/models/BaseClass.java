package com.lethalcoders.movieapi.domain.core.models;
import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public abstract class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    protected void onCreate() {
        Date date = new Date();
        created = date;
        updated = date;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
