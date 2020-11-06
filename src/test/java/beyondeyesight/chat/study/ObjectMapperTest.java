package beyondeyesight.chat.study;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectMapper.class)
public class ObjectMapperTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void haha() {
        System.out.println("kkk");
        System.out.println(objectMapper);
    }

}
