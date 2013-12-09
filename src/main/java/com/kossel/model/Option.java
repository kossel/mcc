package com.kossel.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Yichao
 * Date: 12/9/13
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Indexed
public class Option {

    private Long id;
    private Timestamp lastEditPersonsTime;
    private String whoLastEditPersons;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="last_edit_persons_time")
    public Timestamp getLastEditPersonsTime() {
        return lastEditPersonsTime;
    }

    public void setLastEditPersonsTime(Timestamp lastEditPersons) {
        this.lastEditPersonsTime = lastEditPersons;
    }

    @Column(name="who_last_edit_persons")
    public String getWhoLastEditPersons() {
        return whoLastEditPersons;
    }

    public void setWhoLastEditPersons(String whoLastEditPersons) {
        this.whoLastEditPersons = whoLastEditPersons;
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

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (lastEditPersonsTime != null ? lastEditPersonsTime.hashCode() : 0);
        result = 31 * result + (whoLastEditPersons != null ? whoLastEditPersons.hashCode() : 0);
        return result;
    }
}
