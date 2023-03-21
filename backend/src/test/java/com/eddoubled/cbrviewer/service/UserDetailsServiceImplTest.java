package com.eddoubled.cbrviewer.service;

import com.eddoubled.cbrviewer.model.ERole;
import com.eddoubled.cbrviewer.model.Role;
import com.eddoubled.cbrviewer.model.User;
import com.eddoubled.cbrviewer.repository.UserRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {


    @Test
    void loadUserByUsername() {
        // user not found
        UserRepository userRepository = mock(UserRepository.class);
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(userRepository);
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("name"));
        // find once user with one role
        User user = new User();
        user.setRoles(Sets.newLinkedHashSet(new Role(ERole.ROLE_USER)));
        when(userRepository.findByUsername("name")).thenReturn(Optional.of(user));
        UserDetails result = userDetailsService.loadUserByUsername("name");
        assertEquals(1, result.getAuthorities().size());
    }
}