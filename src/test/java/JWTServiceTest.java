import com.tzy.ApplicationBootstrap;
import com.tzy.model.User;
import com.tzy.service.JWTService;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class JWTServiceTest {

    @Autowired
    private JWTService jwtService;
    @Test
    public void generateTokenTest(){
        //jwtService = new JWTService();
        User user = new User();
        user.setId(1L);
        user.setName("test");

        String token = jwtService.generateToken(user);
        System.out.println(token);
        Assert.assertNotNull(token);
    }

    @Test
    public void decryptTokenTest(){

        User user = new User();
        user.setId(1L);
        user.setName("test");
        String token = jwtService.generateToken(user);

        Claims c = jwtService.decryptJwtToken(token);
        String name = c.getSubject();
        Assert.assertEquals(user.getName(),name);

    }
}
