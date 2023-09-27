package com.bankproject.loggers;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "log")
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;

    public LogEntry(String message,Date timeStamp){
        this.message=message;
        this.timeStamp=timeStamp;
    }

    public LogEntry() {

    }
}
