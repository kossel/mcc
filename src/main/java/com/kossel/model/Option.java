package com.kossel.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Yichao
 * Date: 12/9/13
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Indexed
@Table(name="opt")
public class Option implements Serializable {

    private Long id;

    private String whoLastEditPersons;
    private Date lastEditPersonsTime;
    private Integer changes;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

   @Temporal( TemporalType.TIMESTAMP )
   @Column(name="last_edit_persons_time")
    public Date getLastEditPersonsTime() {
        return lastEditPersonsTime;
    }

    public void setLastEditPersonsTime(Date lastEditPersonsTime) {
        this.lastEditPersonsTime = lastEditPersonsTime;
    }

    @Column(name="who_last_edit_persons")
    public String getWhoLastEditPersons() {
        return whoLastEditPersons;
    }

    public void setWhoLastEditPersons(String whoLastEditPersons) {
        this.whoLastEditPersons = whoLastEditPersons;
    }

    @Column(name="changes")
    public Integer getChanges() {
        return changes;
    }

    public void setChanges(Integer changes) {
        this.changes = changes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (!id.equals(option.id)) return false;
        if (lastEditPersonsTime != null ? !lastEditPersonsTime.equals(option.lastEditPersonsTime) : option.lastEditPersonsTime != null)
            return false;
        if (whoLastEditPersons != null ? !whoLastEditPersons.equals(option.whoLastEditPersons) : option.whoLastEditPersons != null)
            return false;

        return true;
    }

   /* @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (lastEditPersonsTime != null ? lastEditPersonsTime.hashCode() : 0);
        result = 31 * result + (whoLastEditPersons != null ? whoLastEditPersons.hashCode() : 0);
        return result;
    }      */
}
