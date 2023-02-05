package com.etech.mockito.reducestubbing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void storeUser() {
        User user = new User();
        user.setName("duke");
        user.setId(1L);

        when(userRepository.save(any(User.class))).thenReturn(user);

        Long result = userService.storeNewUser("duke");

        Assertions.assertEquals(1, result);
    }


    @Test
    void storeUserReduceUser() {
        when(userRepository.save(any(User.class))).then(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L);
            return savedUser;
        });

        Long result = userService.storeNewUser("duke");

        Assertions.assertEquals(1, result);
    }
}
