package com.cerad.ceradservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobExecutionHistoryDTO {
    private Long id;
    private String jobName;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long durationInSeconds;

    public JobExecutionHistoryDTO(Long id, String jobName, String status, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.jobName = jobName;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationInSeconds = (startTime != null && endTime != null)
                ? java.time.Duration.between(startTime, endTime).getSeconds()
                : null;
    }

}
