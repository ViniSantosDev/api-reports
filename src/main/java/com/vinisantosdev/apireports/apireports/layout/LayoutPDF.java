package com.vinisantosdev.apireports.apireports.layout;

import com.vinisantosdev.apireports.apireports.model.ReturnData1;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

@Component
public class LayoutPDF {

    private String htmlBody = "html base";

    public void replacesValues(List<ReturnData1> data) {

        data.stream().forEach(d -> {
            htmlBody = htmlBody + "only fields";
        });
    }

    public File createDoc(List<ReturnData1> data) throws IOException {

        String fileNameHtml = "/SaldoConsistencia" + ".html";
        File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
        fileNameHtml = tempDirectory.getAbsolutePath() + fileNameHtml;
        File fileHtml = new File(fileNameHtml);
        replacesValues(data);
        StringBuilder html = new StringBuilder();
        html.append(htmlBody);
        File newHtmlFile = fileHtml;
        FileUtils.writeStringToFile(newHtmlFile, html.toString(), Charset.defaultCharset());
        String fileNamePdf = "/SaldoDeConsistencia" + ".pdf";
        fileNamePdf = tempDirectory.getAbsolutePath() + fileNamePdf;
        File filePDF = generatePDF(fileNameHtml, fileNamePdf);
        return filePDF;
    }


    public static File generatePDF(String inputHtmlPath, String outputPdfPath) {
        File outputFile = new File(outputPdfPath);
        try {
            File parentDir = outputFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!parentDir.canWrite()) {
                throw new IOException("Output directory is not writable");
            }
            if (outputFile.isDirectory()) {
                throw new IOException("Output path is a directory");
            }
            if (!outputFile.getName().toLowerCase().endsWith(".pdf")) {
                throw new IllegalArgumentException("Output file should have .pdf extension");
            }
            String url = new File(inputHtmlPath).toURI().toURL().toString();
            System.out.println("URL: " + url);
            OutputStream out = new FileOutputStream(outputFile);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(url);
            renderer.layout();
            renderer.createPDF(out);
            out.close();
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    }
