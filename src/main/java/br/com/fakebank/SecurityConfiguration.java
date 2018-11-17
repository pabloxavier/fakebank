package br.com.fakebank;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.fakebank.security.JWTAuthenticationFilter;
import br.com.fakebank.security.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable() //Cross-site request forgery (Falsificação de solicitação de acesso entre sites)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().permitAll()
            .and()
            
            // filtra requisições de login
            .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)
            
            // filtra outras requisições para verificar a presença do JWT no header
            .addFilterBefore(new JWTAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
            //usuário padrão criado em memória para autenticação
            //apenas para nosso teste inicial
            .withUser("admin")
            .password("1234")
            .roles("ADMIN");
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/v2/api-docs",
                         "/configuration/ui",
                         "/swagger-resources",
                         "/configuration/security",
                         "/swagger-ui.html",
                         "/webjars/**");
    }
}
