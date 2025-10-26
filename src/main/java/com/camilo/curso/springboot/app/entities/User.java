package com.camilo.curso.springboot.app.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUser;
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	@ManyToMany
	@JoinTable(
		name = "users_roles",
		joinColumns = @JoinColumn(name = "id_user_fk"),
		inverseJoinColumns = @JoinColumn(name = "id_rol_fk"),
		uniqueConstraints = {
			@UniqueConstraint(
				columnNames = {
					"id_user_fk", "id_rol_fk"
				}
			)
		}
	)
	private List<Role> rolesList;
	
	@Transient
	private boolean isAdmin;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}
}
