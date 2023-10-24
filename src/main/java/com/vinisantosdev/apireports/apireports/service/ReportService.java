package com.vinisantosdev.apireports.apireports.service;

import com.vinisantosdev.apireports.apireports.layout.LayoutPDF1;
import com.vinisantosdev.apireports.apireports.layout.LayoutPDF2;
import com.vinisantosdev.apireports.apireports.model.ReturnData1;
import com.vinisantosdev.apireports.apireports.model.ReturnData2;
import com.vinisantosdev.apireports.apireports.repository.ReturnData1Repository;
import com.vinisantosdev.apireports.apireports.repository.ReturnData2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
@Service
public class ReportService {

    @Autowired
    private ReturnData1Repository repository1;

    @Autowired
    private ReturnData2Repository repository2;

    @Autowired
    private LayoutPDF1 layoutPDF1;

    @Autowired
    private LayoutPDF2 layoutPDF2;
    public byte[] generatePDFLayout1() throws IOException {
        List<ReturnData1> data = repository1.findAll();
        File file = layoutPDF1.createDoc(data);
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayInputStream);
        return  byteArrayInputStream.toByteArray();
    }

    public byte[] generatePDFLayout2() throws IOException {
        List<ReturnData2> data = repository2.findAll();
        File file = layoutPDF2.createDoc(data);
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayInputStream);
        return  byteArrayInputStream.toByteArray();
    }
}
