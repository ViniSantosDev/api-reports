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
        ByteArrayOutputStream byteArrayInputStream = fileToByteArray(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayInputStream);
        return  byteArrayInputStream.toByteArray();
    }

    //TESTEINICIO
    public static ByteArrayOutputStream fileToByteArray(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException("O arquivo não existe.");
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // Você pode ajustar o tamanho do buffer conforme necessário
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
        byteArrayOutputStream.close();

        return byteArrayOutputStream;
    }
}
