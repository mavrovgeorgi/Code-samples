/**
 * Spring Security configuration for an admin login page. The application
 * consists of two login pages(for users and admins).
 */
@Configuration
// @Order(2)
@EnableAutoConfiguration
public class SpringSecurityAdminConfig extends WebSecurityConfigurerAdapter {
    public SpringSecurityAdminConfig() {
        super();
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(getPasswordEncoder())
                .usersByUsernameQuery("select username, password, enabled from login_account where username=?")
                .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("ADMIN")

                .and().formLogin().loginPage("/admin/login").defaultSuccessUrl("/admin").permitAll()

                .and().logout().logoutUrl("/admin/logout").permitAll().deleteCookies()

                .and().exceptionHandling().accessDeniedPage("/403")

                .and().csrf().disable();
    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web)
            throws Exception {
        web.ignoring().antMatchers("/admin/register", "/html/**", "/css/**", "/js/**", "/images/**", "/config.json",
                "/fallback.json", "/manifest.json", "/login.html", "/sw.js");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}