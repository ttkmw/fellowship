package beyondeyesight.chat.study;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class ClassPathResourceTest {

    // todo: classPath를 내가 설정하고, 그 안에 파일을 둬서 production과 상관없이 작동하게 하기.
    @Test
    public void construct() {
        ClassPathResource classPathResource = new ClassPathResource("db/cql/db-data.cql");
        assertTrue(classPathResource.exists());
    }

}
