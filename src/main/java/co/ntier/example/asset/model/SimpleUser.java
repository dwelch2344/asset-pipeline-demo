package co.ntier.example.asset.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

@Entity
@Table(name="User")
public class SimpleUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String name, email, hashedPassword;
	private Boolean disabled;

	@Deprecated
	public SimpleUser() {}
	
	public SimpleUser(String name, String email, String hashedPassword) {
		Assert.hasText(name, "Must supply a name");
		Assert.hasText(email, "Must supply a valid email");
		Assert.hasText(hashedPassword, "Must supply a valid username");
		Assert.isTrue( hashedPassword.length() == 60, "Must supply 60 character hashedPassword");
		this.name = name;
		this.email = email;
		this.hashedPassword = hashedPassword;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	@Deprecated
	@Override
	public String getUsername() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// SpringSecurity methods

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_DEFAULT"));
		return authorities;
	}
	
	@JsonIgnore
	@Override
	public String getPassword() {
		return hashedPassword;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
