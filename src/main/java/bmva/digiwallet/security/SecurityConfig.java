package bmva.digiwallet.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((request) -> request 
                		// lambda para especificar más de una ruta
                		// csrf no requiere ser desahibilitado porque es por formulario
                		.requestMatchers("/auth/**").permitAll()
                		.requestMatchers("/css/**").permitAll()
                		.requestMatchers("/img/**").permitAll()
                		.requestMatchers("/js/**").permitAll()
                		.requestMatchers("/").permitAll()
                		.requestMatchers("/wallet/api/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/wallet/inicio"))
                .logout((logout) -> logout.permitAll());

        return httpSecurity.build();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
