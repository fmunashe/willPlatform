package zw.co.zim.willplatform.config;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
public class ServiceRequestInterceptor implements RequestInterceptor {
    @SneakyThrows
    @Override
    public void apply(RequestTemplate requestTemplate) {
        traceRequest(requestTemplate.request());
    }

    private void traceRequest(Request request) throws IOException {
        log.info("===========================request begin=============================================");
        log.info("URI         : {}", request.url());
        log.info("Method      : {}", request.httpMethod());
        log.info("Headers     : {}", request.headers());
        log.info("Request body: {}", request.body());
        log.info("==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        log.info("============================response begin==========================================");
        log.info("Status code  : {}", response.getStatusCode());
        log.info("Status text  : {}", response.getStatusText());
        log.info("Headers      : {}", response.getHeaders());
        log.info("Response body: {}", inputStringBuilder.toString());
        log.info("=======================response end=================================================");
    }
}
