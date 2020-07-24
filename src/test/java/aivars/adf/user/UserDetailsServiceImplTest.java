package aivars.adf.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository repository;

    @Test
    public void loadUserByUsername() {
        UserDetails expected = new UserDetailsImpl(getUser());
        when(repository.findByUsername(anyString())).thenReturn(Optional.of(getUser()));
        assertEquals(expected, userDetailsService.loadUserByUsername("test"));
    }

    @Test
    public void loadUserByUsername_notFound() {
        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("test"));
    }

    private User getUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setRole(UserRole.ADMIN);
        return user;
    }

}
