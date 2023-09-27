package com.bankproject.loggers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class LogDbService {
    private LogEntryRepository logEntryRepository;

    public void saveLog(String logMessage){
        LogEntry logEntry=new LogEntry(logMessage,new Date());
        logEntryRepository.save(logEntry);
    }


}
