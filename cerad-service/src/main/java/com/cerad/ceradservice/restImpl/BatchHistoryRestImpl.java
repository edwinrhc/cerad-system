package com.cerad.ceradservice.restImpl;


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
    public List<JobExecution> listExcetions(@PathVariable("jobName") String jobName,  @RequestParam(name = "count", defaultValue = "10") int count) {

        // Recupera las instancias recientes
        List<JobInstance> instances =
                jobExplorer.getJobInstances(jobName,0, count);
        // Para cada instancia, recoge sus ejecuciones
        return instances.stream()
                .flatMap(inst -> jobExplorer.getJobExecutions(inst).stream())
                .collect(Collectors.toList());

    }
}
