package zw.co.zim.willplatform.dto.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class MessageLogger implements Consumer<String> {
    @Override
    public void accept(String s) {
       log.info(s);
    }
}
