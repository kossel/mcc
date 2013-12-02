package com.kossel.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
public class Department extends BaseObject {
	

	private Long id;
	private String nameCH;
	private String nameES;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name_ch", length=150)
	public String getNameCH() {
		return nameCH;
	}
	public void setNameCH(String nameCH) {
		this.nameCH = nameCH;
	}
	
	@Column(name="name_es", length=150)
	public String getNameES() {
		return nameES;
	}
	public void setNameES(String nameES) {
		this.nameES = nameES;
	}

    @Transient
    public String getFullName(){
        return this.getNameCH()+" | "+this.getNameCH();
    }
	@Override
	public String toString() {
		return "Position [id=" + id + ", nameCH=" + nameCH + ", nameES="
				+ nameES + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nameCH == null) ? 0 : nameCH.hashCode());
		result = prime * result + ((nameES == null) ? 0 : nameES.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nameCH == null) {
			if (other.nameCH != null)
				return false;
		} else if (!nameCH.equals(other.nameCH))
			return false;
		if (nameES == null) {
			if (other.nameES != null)
				return false;
		} else if (!nameES.equals(other.nameES))
			return false;
		return true;
	}
	
}
