package com.eddoubled.cbrviewer.service;

import com.eddoubled.cbrviewer.model.ERole;
import com.eddoubled.cbrviewer.model.Role;
import com.eddoubled.cbrviewer.model.User;
import com.eddoubled.cbrviewer.repository.RoleRepository;
import com.eddoubled.cbrviewer.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

/**
 * Инициализирует БД при старте приложения
 */
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Slf4j
public class Initializer {

    CurrencyService currencyService;

    ConvertService convertService;

    RoleRepository roleRepository;

    UserRepository userRepository;

    PasswordEncoder encoder;

    public void init() {
        // rewrite roles
        roleRepository.deleteAll();
        roleRepository.save(new Role(ERole.ROLE_USER));
        roleRepository.save(new Role(ERole.ROLE_ADMIN));
        roleRepository.save(new Role(ERole.ROLE_MODERATOR));

        // Create admin user's account
        userRepository.deleteAll();
        User user = new User("admin",
                "admin@mail.eu",
                encoder.encode("adminpwd"));

        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
        userRole.ifPresent(role -> user.setRoles(Collections.singleton(role)));
        userRepository.save(user);

        // uploading currency
        try {
            currencyService.clearAll();
            currencyService.uploadCurrencyToDb();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        // uploading actual exchange rate
        try {
            convertService.clearAll();
            convertService.uploadAllRatesToDb();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
