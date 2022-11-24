package cz.vsb.swi.vea2022;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                //.antMatcher("/")
                .authorizeHttpRequests((authorize) -> authorize

                        .antMatchers("/order*").hasRole("ADMIN")
                        .anyRequest().hasRole("USER")
                        //.anyRequest().authenticated()
                )
               /* .authorizeHttpRequests().antMatchers("/").authenticated().and()
                .antMatcher("/address")
                .authorizeHttpRequests()
                    .antMatchers("/address").hasRole("ADMIN")
                    .and()
*/
                .httpBasic(withDefaults())
                .formLogin(withDefaults());
       // http.ant

        // @formatter:on
        return http.build();
    }

    // @formatter:off
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        var m = new InMemoryUserDetailsManager();
        m.createUser(user);
        user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN", "USER")
                .build();
        m.createUser(user);

        return m;
    }
    // @formatter:on
}
