package com.valdir.app.configurations;

import com.valdir.app.security.JWTAuthenticationFilter;
import com.valdir.app.security.JWTAuthorizationFilter;
import com.valdir.app.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Environment é uma interface que representa o ambiente no qual
     * o aplicativo atual está sendo executado. Ele pode ser usado
     * para obter perfis e propriedades do ambiente do aplicativo.
     */
    @Autowired
    private Environment environment;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"};
    private static final String[] PUBLIC_MATCHERS_POST = {"/usuarios/**"};

    /**
     * Qualquer endpoint que requeira defesa
     * contra vulnerabilidades comuns pode ser
     * especificado aqui, incluindo as públicas.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * Verificando os profiles ativos. Caso o perfil
         * ativo contenha test o acesso ao h2 será liberado
         */
        if(Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        // Habilita as configurações de cors e desabilita a proteção csrf
        http.cors().and().csrf().disable();
        // Registrando o filtro de authenticação JWT
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        // Registrando o filtro de autorização JWT
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .anyRequest().authenticated();

        // Assegurando que não será criada sessão de usuário
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Como será usada a autenticação do framework
     * esse metodo deve ser sobrescrito para informarmos
     * quem será o UserDetailsService que estamos usando
     * e quem é o algoritmo de codificação que no caso é
     * o BCryptPasswordEncoder. Usado pela implementação
     * padrão de authenticationManager()para tentar obter
     * um AuthenticationManager
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // A partir do auth eu digo quem é o UserDetailsService
        auth.userDetailsService(userDetailsService)
                // E digo quem é meu algoritmo de encriptação
                .passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * Configura o cors para receber requisições
     * de multiplas fontes
     *
     * @return source
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "DELETE"));
        configuration.setAllowedOrigins(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Bean criado para disponibilizar um BCrypt
     * para encodar a senha do usuario que possa
     * ser injetado em qualquer classe do sistema
     * @return new BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
