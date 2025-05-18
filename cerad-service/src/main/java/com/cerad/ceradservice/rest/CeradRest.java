package com.cerad.ceradservice.rest;

import com.cerad.ceradservice.dto.UploadResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cerad")
public interface CeradRest {

    @PostMapping(path= "/upload")
    ResponseEntity<UploadResponseDTO> uploadFile(@RequestParam("file") MultipartFile file);
}
