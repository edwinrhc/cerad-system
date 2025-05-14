package com.cerad.ceradservice.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/cerad")
public interface CeradRest {

    @PostMapping(path= "/upload")
    ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file);
}
