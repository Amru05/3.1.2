package ru.Amru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.Amru.model.Role;
import ru.Amru.model.User;
import ru.Amru.repository.RoleRepository;
import ru.Amru.repository.UserRepository;

import java.util.HashSet;

@SpringBootApplication
public class SpringBootSecurityUsersBootstrapApplication implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SpringBootSecurityUsersBootstrapApplication(RoleRepository roleRepository,
                                                       UserRepository userRepository,
                                                       PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityUsersBootstrapApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        roleRepository.save(admin);
        roleRepository.save(user);
        roleRepository.save(new Role("ROLE_GUEST"));

        userRepository.save(new User("Василий", "Уткин", 49, "admin@mail.com", passwordEncoder.encode("pass"),
                new HashSet<>() {{
                    add(admin);
                    add(user);
                }}));
        userRepository.save(new User("Дмитрий", "Губерниев", 46, "user@mail.com", passwordEncoder.encode("pass1"),
                new HashSet<>()

                ));
    }
}
