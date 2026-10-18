package com.mertezer.checkpoint.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration

public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{ //isteklerden geçecek güvenlik filtrelerini ve sıralarını temsil eder.
        http.authorizeHttpRequests(authorize -> authorize //http istekleri için erişim kurallarını tanımaya başlar
                .requestMatchers("/auth/**").permitAll() // /auth/ altında ki yolları herkese açar şuan ki auth register ve ekleyeceğimiz auth logini herkesin görmesini sağlar
                .requestMatchers(HttpMethod.GET,"/checkpoint/games","/checkpoint/games/**").permitAll() //Oyun listesini ve oyun detayını GET ile okumaya izin verir
                .anyRequest().authenticated()); // Yukarıda ki kurallara girmeyen istekler için kullanıcının giriş yapmasını ister

        return http.build(); // tanımladığımız kuralları SecurityFilterChain nesnesine dönüştürüp springe verdi.
    }
    //permitAll.() -> Adrese giriş yapmamış isteklerin de ilgili endpoint’e ulaşmasına izin verir.
    //HttpMethod.GET -> “Bu kalıba uyan GET istekleri giriş yapmadan çağrılabilir. Örneğin aynı apiye POST isteği gelirse login olunması istenir
    //anyRequest().authenticated() -> Önceki kurallara uymayan tüm istekleri seçer ve bu istekler içink kullanıcının giriş yapmasını ister
    //Yani her .requestMatchers(...) belirli bir istek grubunu seçiyor; ardından .permitAll() veya .authenticated() o gruba uygulanacak erişim kararını söylüyor
    
}
