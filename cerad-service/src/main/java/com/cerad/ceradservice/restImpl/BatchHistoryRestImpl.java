package com.cerad.ceradservice.restImpl;


import com.cerad.ceradservice.dto.JobExecutionHistoryDTO;
import com.cerad.ceradservice.rest.BatchHistoryRest;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BatchHistoryRestImpl implements BatchHistoryRest {

    private final JobExplorer jobExplorer;

    public BatchHistoryRestImpl(JobExplorer jobExplorer) {
        this.jobExplorer = jobExplorer;
    }

    /** 1) Listar todos los jobs registrados **/
    @Override
    public List<String> listJobNames() {
        return jobExplorer.getJobNames();
    }


    /** 2) Obtener las últimas N ejecuciones de un job **/
    @Override
    public List<JobExecutionHistoryDTO> getJobExecutionHistory(@PathVariable("jobName") String jobName, @RequestParam(name = "count", defaultValue = "10") int count) {

        List<JobInstance> jobInstances = jobExplorer.getJobInstances(jobName, 0, count);

        return jobInstances.stream()
                .flatMap(instance -> jobExplorer.getJobExecutions(instance).stream())
                .map(execution -> new JobExecutionHistoryDTO(
                        execution.getId(),
                        execution.getJobInstance().getJobName(),
                        execution.getStatus().toString(),
                        execution.getStartTime(),
                        execution.getEndTime()
                ))
                .collect(Collectors.toList());
    }
}
