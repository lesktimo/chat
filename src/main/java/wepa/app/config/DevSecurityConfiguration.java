package wepa.app.config;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import wepa.app.service.AccountService;

@Profile("dev")
@Configuration
@EnableWebSecurity
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    private AccountService accService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

//        http.authorizeRequests()
//                //resurssit, rekisesteröintisivu, h2-console ja index auki kaikille     
//                .antMatchers(
//                        "/resources/**", "/css/**", "/reg", "/console/*", "/index","/ws/*","/channel/*", "/register/*")
//                .permitAll()
//                //kaikki muut sivut vaativat autentikaation
//                .anyRequest().authenticated()
//                //login-sivun määreet
//                .and()
//                .formLogin().loginPage("/login")
//                .permitAll()
//                //logout määreet
//                .and()
//                .logout().permitAll()
//                .logoutSuccessUrl("/login?logout");
        
         http.authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                .anyRequest().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(accService.passwordEncoder());
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
     @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }
}
