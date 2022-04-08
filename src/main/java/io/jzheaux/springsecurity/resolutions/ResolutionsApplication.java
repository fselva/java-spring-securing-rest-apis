package io.jzheaux.springsecurity.resolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class ResolutionsApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ResolutionsApplication.class, args);
	}

	@Bean
	UserDetailsService userDetailsService(UserRepository users) {
		return new UserRepositoryUserDetailsService(users);
	}

	// @Bean
	// UserDetailsService userDetailsService(DataSource dataSource) {
	// 	// return new JdbcUserDetailsManager(dataSource) {
	// 	// 	@Override
	// 	// 	protected List<GrantedAuthority> loadUserAuthorities(String username) {
	// 	// 		return AuthorityUtils.createAuthorityList("resolution:read");
	// 	// 	}
	// 	// };
	// 	return new JdbcUserDetailsManager(dataSource);
	// }

	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	return new InMemoryUserDetailsManager(
	// 		org.springframework.security.core.userdetails.User
	// 			.withUsername("user")
	// 			//.password("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W")
	// 			.password("{noop}pwd")
	// 			.authorities("resolution:read")
	// 			.build());
	// }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authz -> authz
                .mvcMatchers(GET, "/resolutions", "/resolution/**").hasAuthority("resolution:read")
                .anyRequest().hasAuthority("resolution:write"))
            .httpBasic(basic -> {});
    }

}
