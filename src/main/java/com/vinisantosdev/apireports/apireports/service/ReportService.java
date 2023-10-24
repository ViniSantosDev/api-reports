package com.vinisantosdev.apireports.apireports.service;

import com.vinisantosdev.apireports.apireports.layout.LayoutPDF;
import com.vinisantosdev.apireports.apireports.layout.LayoutPDF2;
import com.vinisantosdev.apireports.apireports.model.ReturnData1;
import com.vinisantosdev.apireports.apireports.repository.ReturnDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
@Service
public class ReportService {

    @Autowired
    private ReturnDataRepository repository;

    @Autowired
    private LayoutPDF layoutPDF1;

    @Autowired
    private LayoutPDF2 layoutPDF2;
    public byte[] generatePDF() throws IOException {
        List<ReturnData1> data = repository.findAll();
        File file = layoutPDF1.createDoc(data);
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayInputStream);
        return  byteArrayInputStream.toByteArray();
    }
}
