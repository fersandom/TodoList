package com.ferran.todo.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;

	private String authority;

	public Role(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

}
