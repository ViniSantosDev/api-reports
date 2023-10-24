package com.vinisantosdev.apireports.apireports.controller;

import com.vinisantosdev.apireports.apireports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/operations")
public class ReportController {

    @Autowired
    private ReportService service;


    @GetMapping("/{operation_id}/report")
    public ResponseEntity<byte[]> reportData1(@PathVariable Long account) throws IOException {
        byte[] pdfContent = service.generatePDFLayout1();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "example.pdf");
        return ResponseEntity.ok().headers(headers).body(pdfContent);
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> layout() throws IOException {
        byte[] pdfContent = service.generatePDFLayout1();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "example.pdf");
        return ResponseEntity.ok().headers(headers).body(pdfContent);
    }

    @GetMapping("/{operation_id}/report2")
    public ResponseEntity<byte[]> reportData2(@PathVariable Long account) throws IOException {
        byte[] pdfContent = service.generatePDFLayout2();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "example.pdf");
        return ResponseEntity.ok().headers(headers).body(pdfContent);
    }

}
