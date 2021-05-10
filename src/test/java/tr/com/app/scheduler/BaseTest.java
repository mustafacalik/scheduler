package tr.com.app.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.TestContextManager;

@Slf4j
public class BaseTest {

    private final TestContextManager testContextManager;

    public BaseTest(){
        this.testContextManager = new TestContextManager(getClass());
        try {
            testContextManager.prepareTestInstance(this);
        } catch (Exception e) {
            log.error(String.valueOf(e.getStackTrace()));
        }
    }
}
