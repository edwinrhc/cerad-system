package com.cerad.ceradservice.restImpl;

import com.cerad.ceradservice.entity.Header;
import com.cerad.ceradservice.rest.CeradRest;
import com.cerad.ceradservice.service.CeradService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class CeradRestImpl implements CeradRest {

    private final CeradService ceradService;
    private final JobLauncher jobLauncher;
    private final Job importDetailJob;

    public CeradRestImpl(CeradService ceradService, JobLauncher jobLauncher, Job importDetailJob) {
        this.ceradService = ceradService;
        this.jobLauncher = jobLauncher;
        this.importDetailJob = importDetailJob;
    }

    @Override
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 1. Guardar el archivo en una ruta temporal del sistema (dinámica)
            File tempFile = File.createTempFile("cerad-", ".txt");
            file.transferTo(tempFile);

            // 2. Guardar Header (mock, luego lo harás dinámico con contenido del archivo)
            Header header = new Header();
            header.setDocumentNumber("DOC-001");
            header.setClientName("Cliente Prueba");
            header.setUploadDate(LocalDateTime.now());
            Header savedHeader = ceradService.saveHeader(header);

            // 3. Preparar JobParameters (incluyendo la ruta del archivo dinámico)
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("filePath", tempFile.getAbsolutePath())
                    .addLong("headerId", savedHeader.getId())  // Aquí pasas el headerId
                    .addLong("time", System.currentTimeMillis()) // Para evitar colisiones
                    .toJobParameters();

            // 4. Ejecutar Job de Spring Batch
            jobLauncher.run(importDetailJob, jobParameters);

            // 5. Respuesta exitosa
            return ResponseEntity.ok("Archivo procesado correctamente");

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al guardar archivo: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al procesar archivo: " + e.getMessage());
        }
    }
}
