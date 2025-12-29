package com.camilo.curso.springboot.app.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRol;
	
	@Column(unique = true)
	private String nameRol;
	
	@JsonIgnoreProperties({
		"handler",
		"hibernateLazyInitializer"
	})
	@ManyToMany(mappedBy = "rolesList")
	private List<User> usersList;

	public Role(String nameRol) {
		this.nameRol = nameRol;
	}

	public Role() {
	}

	public Integer getIdRole() {
		return idRol;
	}

	public void setIdRole(Integer idRole) {
		this.idRol = idRole;
	}

	public String getnameRol() {
		return nameRol;
	}

	public void setnameRol(String nameRol) {
		this.nameRol = nameRol;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRol, nameRol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(idRol, other.idRol) && Objects.equals(nameRol, other.nameRol);
	}
}
