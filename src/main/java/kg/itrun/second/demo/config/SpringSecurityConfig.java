package kg.itrun.second.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                   .antMatchers("/", "/home", "/about").permitAll()
                   .antMatchers("/css/**").permitAll()
                   .antMatchers("/admin/**").hasAnyRole("ADMIN")
                   .antMatchers("/user/**").hasAnyRole("USER")
                    .anyRequest().authenticated()
                .and()
                   .formLogin()
                   .loginPage("/login")
                .successForwardUrl("/admin")
                .successForwardUrl("/user")
                .defaultSuccessUrl("/user")
                .usernameParameter("roleid")
                .usernameParameter("login")
                .passwordParameter("pwd")
                    .permitAll()
                .and()
                   .logout()
                   .permitAll()
                .and()
                  .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("123").roles("USER")
                .and()
                .withUser("admin").password("123").roles("ADMIN");
    }*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder)
                .usersByUsernameQuery("select login, pwd from securitydb.users where login=? ")
                .authoritiesByUsernameQuery("select u.login, r.role_name from securitydb.users u inner join securitydb.role r on u.roleid = r.roleid where u.login = ?");

    }
}
