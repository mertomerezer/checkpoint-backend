package com.mertezer.checkpoint.security;

import com.mertezer.checkpoint.entity.User;
import com.mertezer.checkpoint.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CheckpointUserDetailsService implements UserDetailsService {
 //UserDetailsService, Spring Security’nin tanımladığı interface.
 //Interface, bir sözleşme gibi: “Bu servisi kullanacaksan, kullanıcıyı yükleyen şu metodu sağlamalısın” der.

    private final UserRepository userRepository;

    public CheckpointUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Kullanıcı Bulunamadı"));

        //bu kod bizim veritabanından bulduğumuz kullanıcıyı Spring Security’nin anlayacağı bir UserDetails nesnesine dönüştürüyor.

        return org.springframework.security.core.userdetails.User //Spring Security’nin hazır User sınıfını seçiyoruz.
                .withUsername(user.getEmail()) // Spring Security’de kimlik alanının adı username olsa da burada değer olarak kullanıcının emailini veriyoruz.
                                                // Login akışında Spring bu değeri bizim loadUserByUsername metodumuza iletecek.
                .password(user.getPasswordHash()) //Kullanıcının ham parolasını değil, veritabanında saklanan BCrypt hash’ini veriyoruz.
                                                  // Spring, kullanıcının girişte gönderdiği ham parolayı PasswordEncoder ile bu hash’e karşı doğrular.
                .roles("USER") //Bu kullanıcıya USER rolünü veriyoruz. Spring bunu içeride genellikle ROLE_USER yetkisine dönüştürür.

                .build();  //Yukarıdaki bilgileri kullanarak UserDetails nesnesini oluşturur.
    }

    //findByEmail(username) veritabanında email ile kullanıcıyı buluyor.
    //withUsername(user.getEmail()) ise bulduğumuz kullanıcının emailini, Spring Security nesnesinin giriş kimliği olarak kaydediyor.

}
