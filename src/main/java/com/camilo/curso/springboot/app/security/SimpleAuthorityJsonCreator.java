package com.camilo.curso.springboot.app.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleAuthorityJsonCreator {

	@JsonCreator
	public SimpleAuthorityJsonCreator(@JsonProperty("authority") String rol) {}
}
