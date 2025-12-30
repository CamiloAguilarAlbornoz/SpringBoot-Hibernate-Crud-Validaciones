package com.camilo.curso.springboot.app.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.camilo.curso.springboot.app.security.filter.JwtAthenticationFilter;
import com.camilo.curso.springboot.app.security.filter.JwtValidationFilter;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	/**
	 * Encripta contraseña
	 * @return
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(authz -> 
			authz
			.requestMatchers(HttpMethod.GET, "/api/users").permitAll()
			.requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
			.anyRequest().authenticated())
		.addFilter(new JwtAthenticationFilter(authenticationManager()))
		.addFilter(new JwtValidationFilter(authenticationManager()))
		.csrf(config -> config.disable())
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.sessionManagement(management ->
			management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		).build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		corsConfiguration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return urlBasedCorsConfigurationSource;
	}
	
	@Bean
	FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
		FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource()));
		filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filterRegistrationBean;
	}
}
