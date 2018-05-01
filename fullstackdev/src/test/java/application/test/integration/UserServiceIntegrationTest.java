package application.test.integration;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import application.backend.persistence.domain.backend.Role;
import application.backend.persistence.domain.backend.User;
import application.backend.persistence.domain.backend.UserRole;
import application.backend.services.UserService;
import application.enums.PlansEnum;
import application.enums.RolesEnum;
import application.utils.UsersUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Rule
    public TestName testName = new TestName();
    
    @Test
    public void testCreateNewUser() throws Exception {

        Set<UserRole> userRoles = new HashSet<>();
        
        String userName = testName.getMethodName(); 
		String email = testName.getMethodName() + "@email.com"; 
		
		User basicUser = UsersUtils.createBasicUser(userName, email);
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        User user = userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
        assertNotNull(user);
        assertNotNull(user.getId());

    }
}
