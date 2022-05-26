package main;

import main.entity.User;
import main.repository.GroupsRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TestDataInit implements CommandLineRunner {

    @Autowired
    GroupsRepository groupsRep;

    @Autowired
    UserRepository userRep;

    @Autowired
    PasswordEncoder pwdEncoder;

    @Override
    public void run(String... args) throws Exception {
//        userRep.save(new User("student", pwdEncoder.encode("studentpassword"), Collections.singletonList("ROLE_STUDENT")));
//        userRep.save(new User("admin", pwdEncoder.encode("adminpassword"), Collections.singletonList("ROLE_ADMIN")));
//        userRep.save(new User("teacher", pwdEncoder.encode("teacherpassword"), Collections.singletonList("ROLE_TEACHER")));
    }
}
