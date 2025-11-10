package com.camilo.curso.springboot.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRol;
	
	@Column(unique = true)
	private String nameRol;

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
}
