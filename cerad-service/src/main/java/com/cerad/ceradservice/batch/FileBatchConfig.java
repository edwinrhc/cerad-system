package com.cerad.ceradservice.batch;

import com.cerad.ceradservice.entity.Detail;
import com.cerad.ceradservice.entity.Header;
import com.cerad.ceradservice.service.CeradService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.job.builder.*;
import org.springframework.batch.core.repository.*;
import org.springframework.batch.core.step.builder.*;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.mapping.*;
import org.springframework.batch.item.file.transform.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;


import org.springframework.batch.item.database.JpaItemWriter;

@Configuration
@EnableBatchProcessing
public class FileBatchConfig {

    @Bean @StepScope
    public FlatFileItemReader<Detail> detailReader(
            @Value("#{jobParameters['filePath']}") String path) {

        FlatFileItemReader<Detail> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(path));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<Detail>() {{
            setLineTokenizer(new DelimitedLineTokenizer("|") {{
                setNames("productCode","description","amount");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Detail>() {{
                setTargetType(Detail.class);
            }});
        }});
        return reader;
    }

    @Bean @StepScope
    public ItemProcessor<Detail, Detail> detailProcessor(
            @Value("#{jobParameters['headerId']}") Long headerId,
            CeradService service) {
        return item -> {
            Header hdr = service.findHeaderById(headerId);
            item.setHeader(hdr);
            return item;
        };
    }

    @Bean
    public ItemWriter<Detail> detailWriter(EntityManagerFactory emf) {
        JpaItemWriter<Detail> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;
    }

    @Bean
    public Step importDetailStep(
            JobRepository repo,
            PlatformTransactionManager tx,
            FlatFileItemReader<Detail> detailReader,
            ItemProcessor<Detail,Detail> detailProcessor,
            ItemWriter<Detail> detailWriter
    ) {
        return new StepBuilder("importDetailStep", repo)
                .<Detail,Detail>chunk(10, tx)
                .reader(detailReader)
                .processor(detailProcessor)
                .writer(detailWriter)
                .build();
    }

    @Bean
    public Job importDetailJob(JobRepository repo, Step importDetailStep) {
        return new JobBuilder("importDetailJob", repo)
                .start(importDetailStep)
                .build();
    }
}
