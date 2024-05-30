package zw.co.zim.willplatform.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.mapper.MessageLogger;

import java.time.LocalDateTime;

@Service
public class TaskSchedule {
    private final MessageLogger logger;
    private  String message ="Message is here";

    public TaskSchedule(MessageLogger logger) {
        this.logger = logger;
    }

//    @Scheduled(cron = "*/5 * * * * *")
    protected void logMessage(){
        logger.accept(message+ LocalDateTime.now());
    }
}
