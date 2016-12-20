package wepa.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import wepa.app.service.AccountService;

@Profile("production")
@Configuration
@EnableWebSecurity
public class ProductionSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountService accService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                //resurssit, rekisesteröintisivu ja index auki kaikille  
                .antMatchers("/resources/**", "/css/**", "/reg", "/index").permitAll()
                //kaikki muut sivut vaativat autentikaation
                .anyRequest().authenticated()
                //login-sivun määreet
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                //logout määreet
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/login?logout");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(accService.passwordEncoder());
    }

}
