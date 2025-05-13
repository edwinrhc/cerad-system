package com.cerad.ceradservice.batch;

import com.cerad.ceradservice.entity.Detail;
import com.cerad.ceradservice.service.CeradService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class FileBatchConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<Detail> detailItemReader(@Value("#{jobParameters['filePath']}") String filePath) {
        FlatFileItemReader<Detail> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(filePath));
        reader.setLinesToSkip(1);

        reader.setLineMapper(new DefaultLineMapper<Detail>() {{
            setLineTokenizer(new DelimitedLineTokenizer("|") {{
                setNames("productCode", "description", "amount");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Detail>() {{
                setTargetType(Detail.class);
            }});
        }});

        return reader;
    }

    @Bean
    public Step importDetailStep(JobRepository jobRepository,
                                 PlatformTransactionManager transactionManager,
                                 FlatFileItemReader<Detail> detailItemReader,
                                 ItemWriter<Detail> detailItemWriter) {

        return new StepBuilder("importDetailStep", jobRepository)
                .<Detail, Detail>chunk(10, transactionManager)
                .reader(detailItemReader)
                .writer(detailItemWriter)
                .build();
    }

    @Bean
    public Job importDetailJob(JobRepository jobRepository, Step importDetailStep) {
        return new JobBuilder("importDetailJob", jobRepository)
                .start(importDetailStep)
                .build();
    }

}
