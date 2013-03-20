package co.ntier.example.asset.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Configuration
@ImportResource("/WEB-INF/config/security.xml")
public class SecurityConfig {

	@Bean(name = "org.springframework.security.authenticationManager")
	public ProviderManager providerManager(UserDetailsService userDetailsService) {
		List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
		providers.add(provider(userDetailsService));
		return new ProviderManager(providers);
	}

	private DaoAuthenticationProvider provider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BcryptPasswordEncoder());
		return provider;
	}
	
	private class BcryptPasswordEncoder implements PasswordEncoder {

		@Override
		public boolean isPasswordValid(String encPass, String rawPass,
				Object saltObject) {
			return BCrypt.checkpw(rawPass, encPass);
		}

		@Override
		public String encodePassword(String rawPass, Object salt) {
			return BCrypt.hashpw(rawPass, salt == null ? BCrypt.gensalt() : salt.toString());
		}
	}
	

}