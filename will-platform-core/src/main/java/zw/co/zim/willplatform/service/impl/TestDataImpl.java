package zw.co.zim.willplatform.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.TestData;
import zw.co.zim.willplatform.integration.TestDataOpenFeign;

import java.util.List;

@Service
@Slf4j
public class TestDataImpl {
    private final TestDataOpenFeign client;

    public TestDataImpl(TestDataOpenFeign client) {
        this.client = client;
    }

//    @Scheduled(cron = "0 */2 * * * *")
    protected void processUserDataFromRemoteAPI() {
        log.info("===== Init Data Processing =====");
        List<TestData> userData = client.getUsersData();
        for (TestData data : userData) {
            log.info("data is {}", data);
        }
        log.info("===== Data Processing Completed =====");
    }
}
