package com.mertezer.checkpoint.repository;

import com.mertezer.checkpoint.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);  //repository’deki existsByEmail(...)
                                              // sadece true/false döndürüyor.
                                             //Login sırasında ise kullanıcıyı ve kayıtlı parola hash’ini almamız gerekiyor
                                            //Bu yüzden security paketi açıp UserDetailsService yazıp kullanıcıyı veritabanından bulacağız



}
