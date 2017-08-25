package jp.co.rakus.ecommerce_c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class EcSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**");
	}
}
