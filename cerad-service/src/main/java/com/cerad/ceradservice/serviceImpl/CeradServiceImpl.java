package com.cerad.ceradservice.serviceImpl;

import com.cerad.ceradservice.entity.Detail;
import com.cerad.ceradservice.entity.Header;
import com.cerad.ceradservice.repository.DetailRepository;
import com.cerad.ceradservice.repository.HeaderRepository;
import com.cerad.ceradservice.service.CeradService;
import org.springframework.stereotype.Service;

@Service
public class CeradServiceImpl implements CeradService {

    private final HeaderRepository headerRepository;
    private final DetailRepository detailRepository;

    public CeradServiceImpl(HeaderRepository headerRepository, DetailRepository detailRepository) {
        this.headerRepository = headerRepository;
        this.detailRepository = detailRepository;
    }

    @Override
    public Header saveHeader(Header header) {
        return headerRepository.save(header);
    }

    @Override
    public Detail saveDetail(Detail detail) {
        return detailRepository.save(detail);
    }

    @Override
    public Header findHeaderById(Long id) {
        return headerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Header no encontrado con ID: " + id));
    }
}
