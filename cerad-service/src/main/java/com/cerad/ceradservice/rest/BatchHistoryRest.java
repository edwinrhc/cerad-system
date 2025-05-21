package com.cerad.ceradservice.rest;

import com.cerad.ceradservice.dto.JobExecutionHistoryDTO;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/batch")
public interface BatchHistoryRest {


    @GetMapping("/jobs")
    List<String> listJobNames();

    @GetMapping("/jobs/{jobName}/history")
    List<JobExecutionHistoryDTO> getJobExecutionHistory(
            @PathVariable("jobName") String jobName,
            @RequestParam(name = "count", defaultValue = "10") int count);



}
