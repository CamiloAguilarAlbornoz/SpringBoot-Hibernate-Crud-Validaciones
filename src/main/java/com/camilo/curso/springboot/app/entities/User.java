package com.camilo.curso.springboot.app.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUser;
	
	@Column(unique = true)
	@NotBlank
	@Size(min = 4, max = 12)
	private String userName;
	
	@NotBlank
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@JsonIgnoreProperties({
		"usersList",
		"handler",
		"hibernateLazyInitializer"
	})
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
	
	private boolean enabled;

	@Transient
	@JsonProperty(access = Access.WRITE_ONLY)
	private boolean isAdmin;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

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
	
	@PrePersist
	public void PrePersist() {
		this.enabled = true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUser, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(idUser, other.idUser) && Objects.equals(userName, other.userName);
	}
}
