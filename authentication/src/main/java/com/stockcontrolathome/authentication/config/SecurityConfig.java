package com.stockcontrolathome.authentication.config;

import com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.provider.ResendConfirmRegisterTokenProvider;
import com.stockcontrolathome.authentication.config.authentication.resendconfirmregister.service.ResendConfirmRegisterTokenService;
import com.stockcontrolathome.authentication.jwt.filter.JwtTokenFilter;
import com.stockcontrolathome.authentication.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private ResendConfirmRegisterTokenService resendConfirmRegisterTokenService;


    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(0)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }




	/*@Bean
	@Order(1)
	public SecurityFilterChain myOtherFilterChain(HttpSecurity http) throws Exception {
		http.cors((cors) -> cors
						.configurationSource(myWebsiteConfigurationSource())
				);
		return http.build();
	}



	CorsConfigurationSource myWebsiteConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://localhost:3001"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/users/**", configuration);
		return source;
	}
*/




    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return new ProviderManager(daoAuthenticationProvider(), resendConfirmRegisterTokenProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userDetailServiceImpl);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }

    @Bean
    public ResendConfirmRegisterTokenProvider resendConfirmRegisterTokenProvider(){
        return new ResendConfirmRegisterTokenProvider(resendConfirmRegisterTokenService, passwordEncoder());
    }


}
