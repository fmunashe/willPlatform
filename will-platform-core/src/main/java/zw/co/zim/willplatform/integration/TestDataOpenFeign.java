package zw.co.zim.willplatform.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import zw.co.zim.willplatform.config.ServiceRequestInterceptor;
import zw.co.zim.willplatform.dto.TestData;

import java.util.List;

@FeignClient(name = "TestDataOpenFeign", url = "https://jsonplaceholder.typicode.com", configuration = {ServiceRequestInterceptor.class})
public interface TestDataOpenFeign {
    @GetMapping("/users")
    List<TestData> getUsersData();
}
