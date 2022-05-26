package main.security;

import main.security.jwt.JwtSecurityConfigurer;
import main.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/dekanat/auth/signin").permitAll()
                .antMatchers("/dekanat/auth/register").permitAll()
//                .antMatchers(HttpMethod.POST, "/dekanat/groups/add").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/dekanat/groups/delete/{id}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/dekanat/groups/deleteAll").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/dekanat/groups/add").permitAll()
                .antMatchers(HttpMethod.DELETE, "/dekanat/groups/delete/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/dekanat/groups/deleteAll").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/groups").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/groups/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/people").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/people/{id}").permitAll()
//                .antMatchers(HttpMethod.POST, "/dekanat/people/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/dekanat/people/add").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/people/findByGroupAndType/{group}/{type}").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/people/findBySubjectAndType/{subject}/{type}").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/dekanat/people/delete/{id}").hasRole("ADMIN")
////                .antMatchers(HttpMethod.DELETE, "/dekanat/people/deleteAll").hasRole("ADMIN")
////                .antMatchers(HttpMethod.DELETE, "/dekanat/subjects/delete/{id}").hasRole("ADMIN")
////                .antMatchers(HttpMethod.DELETE, "/dekanat/subjects/deleteAll").hasRole("ADMIN")
////                .antMatchers(HttpMethod.POST, "/dekanat/subjects/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/dekanat/people/delete/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/dekanat/people/deleteAll").permitAll()
                .antMatchers(HttpMethod.DELETE, "/dekanat/subjects/delete/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/dekanat/subjects/deleteAll").permitAll()
                .antMatchers(HttpMethod.POST, "/dekanat/subjects/add").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/subjects").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/subjects/{id}").permitAll()
//                .antMatchers(HttpMethod.POST, "/dekanat/marks/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/dekanat/marks/add").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/marks").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/marks/{id}").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/dekanat/marks/delete/{id}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/dekanat/marks/deleteAll").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/dekanat/marks/delete/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/dekanat/marks/deleteAll").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/marks/findByStudent/{student}").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/marks/findByStudentAndSubject/{student}/{subject}").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/marks/findByGroupAndSubject/{group}/{subject}").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/marks/findByTeacherAndSubject/{teacher}/{subject}").permitAll()
                .antMatchers(HttpMethod.GET, "/dekanat/people/findByType/{type}").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(tokenProvider));
    }


}
