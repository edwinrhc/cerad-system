package com.cerad.ceradservice.service;

import com.cerad.ceradservice.entity.Detail;
import com.cerad.ceradservice.entity.Header;

public interface CeradService {

    Header saveHeader(Header header);
    Detail saveDetail(Detail detail);
    Header findHeaderById(Long id);
}
