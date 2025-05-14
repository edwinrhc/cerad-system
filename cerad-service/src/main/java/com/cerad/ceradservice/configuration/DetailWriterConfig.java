package com.cerad.ceradservice.configuration;

import com.cerad.ceradservice.entity.Detail;
import com.cerad.ceradservice.service.CeradService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DetailWriterConfig {


    @Bean
    @StepScope
    public ItemWriter<Detail> detailItemWriter(CeradService ceradService,
                                               @Value("#{jobParameters['headerId']}") Long headerId) {
        return details -> {
            for (Detail detail : details) {
                detail.setHeader(ceradService.findHeaderById(headerId));
                ceradService.saveDetail(detail);
            }
        };
    }
}

