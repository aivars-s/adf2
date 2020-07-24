package aivars.adf.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsImplTest {

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void userDetails() {

        User user = new User();
        user.setUsername("test");
        user.setPassword(encoder.encode("test"));
        user.setRole(UserRole.ADMIN);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        assertThat(userDetails.getAuthorities().stream().map(Object::toString))
                .contains(UserRole.ADMIN.toString());
        assertEquals(userDetails.getPassword(), user.getPassword());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isEnabled());
    }

}
