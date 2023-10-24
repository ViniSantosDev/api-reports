package com.vinisantosdev.apireports.apireports.layout;

import com.vinisantosdev.apireports.apireports.model.ReturnData1;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
@Slf4j
@Component
public class LayoutPDF1 {

    private String htmlBody = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\">\n" +
            "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <title>Document</title>\n" +
            "</head>\n" +
            "<style>\n" +
            "    h1 {\n" +
            "        font-size: 18px;\n" +
            "        margin: 0;\n" +
            "    }\n" +
            "\n" +
            "    h2 {\n" +
            "        font-size: 16px;\n" +
            "        margin: 0;\n" +
            "    }\n" +
            "\n" +
            "    p {\n" +
            "        margin: 0;\n" +
            "    }\n" +
            "\n" +
            "    p span {\n" +
            "        font-weight: bold;\n" +
            "    }\n" +
            "\n" +
            "    table {\n" +
            "        width: 100%;\n" +
            "        border: 3px solid;\n" +
            "        border-spacing: 0;\n" +
            "        /* Removes the cell spacing via CSS */\n" +
            "        border-collapse: collapse;\n" +
            "    }\n" +
            "\n" +
            "    th {\n" +
            "        border-bottom: 1px solid;\n" +
            "        padding: 10px 0;\n" +
            "    }\n" +
            "\n" +
            "    td {\n" +
            "        text-align: center;\n" +
            "        padding: 10px 0;\n" +
            "    }\n" +
            "\n" +
            "    .division {\n" +
            "        border-right: 1px solid;\n" +
            "    }\n" +
            "\n" +
            "    .container {\n" +
            "        padding: 0 20px;\n" +
            "    }\n" +
            "\n" +
            "    .logo-wrapper img {\n" +
            "        width: 250px;\n" +
            "    }\n" +
            "\n" +
            "    .title-wrapper,\n" +
            "    .date-wrapper,\n" +
            "    .logo-wrapper {\n" +
            "        display: flex;\n" +
            "        flex-direction: column;\n" +
            "    }\n" +
            "\n" +
            "    .title-wrapper h2 {\n" +
            "        text-align: center;\n" +
            "        margin-top: 10px;\n" +
            "    }\n" +
            "\n" +
            "    .title-wrapper {\n" +
            "        margin-right: 100px;\n" +
            "    }\n" +
            "\n" +
            "    .header {\n" +
            "        display: flex;\n" +
            "        justify-content: space-between;\n" +
            "        margin-bottom: 50px;\n" +
            "    }\n" +
            "\n" +
            "    .info-wrapper {\n" +
            "        border: 3px solid;\n" +
            "        margin: 15px 0;\n" +
            "    }\n" +
            "\n" +
            "    .info-wrapper .top-wrapper {\n" +
            "        padding: 20px;\n" +
            "        padding-bottom: 10px;\n" +
            "        border-bottom: 1px solid;\n" +
            "    }\n" +
            "\n" +
            "    .info-wrapper .bottom-wrapper {\n" +
            "        padding: 20px;\n" +
            "        padding-bottom: 10px;\n" +
            "    }\n" +
            "\n" +
            "    .info-wrapper p {\n" +
            "        margin-bottom: 10px;\n" +
            "    }\n" +
            "\n" +
            "    .title {\n" +
            "        text-align: center;\n" +
            "        margin: 15px 0;\n" +
            "    }\n" +
            "\n" +
            "    .align-right {\n" +
            "        text-align: right;\n" +
            "    }\n" +
            "\n" +
            "    .table-division {\n" +
            "        border-left: 2px solid;\n" +
            "        border-right: 2px solid;\n" +
            "    }\n" +
            "\n" +
            "    .content-table th {\n" +
            "        padding-right: 10px;\n" +
            "        padding-left: 10px;\n" +
            "    }\n" +
            "\n" +
            "    .content-table td {\n" +
            "        padding-right: 10px;\n" +
            "        padding-left: 10px;\n" +
            "    }\n" +
            "\n" +
            "    .content,\n" +
            "    .second-content {\n" +
            "        margin-top: 40px;\n" +
            "    }\n" +
            "\n" +
            "    .bold {\n" +
            "        font-weight: bold;\n" +
            "    }\n" +
            "\n" +
            "    .second-content table {\n" +
            "        width: 50%;\n" +
            "        margin: 0 auto;\n" +
            "    }\n" +
            "\n" +
            "    .second-content table th {\n" +
            "        border: none;\n" +
            "    }\n" +
            "\n" +
            "    .second-content table td {\n" +
            "        border: none;\n" +
            "    }\n" +
            "\n" +
            "    .second-content .border-top {\n" +
            "        border-top: 1px solid;\n" +
            "    }\n" +
            "\n" +
            "    .label {\n" +
            "        display: inline-block;\n" +
            "        width: 100px;\n" +
            "    }\n" +
            "\n" +
            "    .currency-row td {\n" +
            "        font-weight: bold;\n" +
            "    }\n" +
            "\n" +
            "    .total-row td {\n" +
            "        border-top: 2px solid;\n" +
            "        border-bottom: 2px solid;\n" +
            "        font-weight: bold;\n" +
            "    }\n" +
            "\n" +
            "    .total-row .initial {\n" +
            "        border-top: none;\n" +
            "    }\n" +
            "</style>\n" +
            "\n" +
            "<body>\n" +
            "<div class=\"header\">\n" +
            "  <div class=\"logo-wrapper\">\n" +
            "    <img src=\"logo.png\" alt=\"Grupo Santander\">\n" +
            "  </div>\n" +
            "  <div class=\"title-wrapper\">\n" +
            "    <h1>\n" +
            "      JJ - Sistema de Conciliação de Contas\n" +
            "    </h1>\n" +
            "    <h2>\n" +
            "      INVENTÁRIO DE PENDÊNCIAS\n" +
            "    </h2>\n" +
            "  </div>\n" +
            "  <div class=\"date-wrapper\">\n" +
            "    <p>\n" +
            "      <span>Data:</span> 08/05/2023\n" +
            "    </p>\n" +
            "    <p>\n" +
            "      <span>Hora:</span> 09:12\n" +
            "    </p>\n" +
            "    <p>\n" +
            "      <span>Pg:</span> 1\n" +
            "    </p>\n" +
            "  </div>\n" +
            "</div>\n" +
            "\n" +
            "<section class=\"content\">\n" +
            "  <div class=\"container\">\n" +
            "    <p>\n" +
            "      <span class=\"label\">Titular:</span>${titular}\n" +
            "    </p>\n" +
            "    <p>\n" +
            "      <span class=\"label\">Data base:</span>28/04/2023\n" +
            "    </p>\n" +
            "\n" +
            "    <div class=\"content\">\n" +
            "      <div class=\"content-table\">\n" +
            "        <table>\n" +
            "          <tr>\n" +
            "            <th colspan=\"3\" class=\"\">Agente</th>\n" +
            "            <th colspan=\"3\" class=\"\">Cód Swift</th>\n" +
            "            <th colspan=\"3\" class=\"\">Número da Conta</th>\n" +
            "            <th class=\"table-division\">Saldo Extrato</th>\n" +
            "            <th class=\"table-division\">Saldo Consistência Contábil</th>\n" +
            "            <th class=\"table-division\">Débito Contábil</th>\n" +
            "            <th class=\"table-division\">Crédito Contábil</th>\n" +
            "            <th class=\"table-division\">Débitos Extratos</th>\n" +
            "            <th class=\"table-division\">Crédito Extratos</th>\n" +
            "            <th class=\"table-division\">Débito Contábil</th>\n" +
            "          </tr>\n" +
            "          <tbody class=\"group\">\n" +
            "          <tr class=\"currency-row\">\n" +
            "            <td>Moeda:</td>\n" +
            "            <td>EUR</td>\n" +
            "            <td>EURO</td>\n" +
            "            <td colspan=\"3\"></td>\n" +
            "            <td colspan=\"3\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO CENTRAL DE LUXEMBURGO</td>\n" +
            "            <td colspan=\"3\">BCLXLULL</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO CENTRAL DE LUXEMBURGO</td>\n" +
            "            <td colspan=\"3\">BCLXLULL</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO CENTRAL DE LUXEMBURGO</td>\n" +
            "            <td colspan=\"3\">BCLXLULLXXX</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO SANTANDER CENTRAL HISPANO</td>\n" +
            "            <td colspan=\"3\">BCLXLULLXXX</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">244,460.38</td>\n" +
            "            <td class=\"table-division\">-244,091.29</td>\n" +
            "            <td class=\"table-division\">-539,336.93</td>\n" +
            "            <td class=\"table-division\">72,191.83</td>\n" +
            "            <td class=\"table-division\">-3,448.29</td>\n" +
            "            <td class=\"table-division\">507,501.65</td>\n" +
            "            <td class=\"table-division\">36,539.18</td>\n" +
            "          </tr>\n" +
            "          <tr class=\"total-row\">\n" +
            "            <td colspan=\"3\" class=\"initial\"></td>\n" +
            "            <td colspan=\"3\" class=\"initial\"></td>\n" +
            "            <td colspan=\"3\" class=\"initial\"></td>\n" +
            "            <td class=\"table-division\">244,460.38</td>\n" +
            "            <td class=\"table-division\">-244,091.29</td>\n" +
            "            <td class=\"table-division\">-539,336.93</td>\n" +
            "            <td class=\"table-division\">72,191.83</td>\n" +
            "            <td class=\"table-division\">-3,448.29</td>\n" +
            "            <td class=\"table-division\">507,501.65</td>\n" +
            "            <td class=\"table-division\">36,539.18</td>\n" +
            "          </tr>\n" +
            "          </tbody>\n" +
            "          <tbody class=\"group\">\n" +
            "          <tr class=\"currency-row\">\n" +
            "            <td>Moeda:</td>\n" +
            "            <td>USD</td>\n" +
            "            <td>DOLAR AMERICANO</td>\n" +
            "            <td colspan=\"3\"></td>\n" +
            "            <td colspan=\"3\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO CENTRAL DE LUXEMBURGO</td>\n" +
            "            <td colspan=\"3\">BCLXLULL</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO CENTRAL DE LUXEMBURGO</td>\n" +
            "            <td colspan=\"3\">BCLXLULL</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO CENTRAL DE LUXEMBURGO</td>\n" +
            "            <td colspan=\"3\">BCLXLULLXXX</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO SANTANDER CENTRAL HISPANO</td>\n" +
            "            <td colspan=\"3\">BCLXLULLXXX</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">244,460.38</td>\n" +
            "            <td class=\"table-division\">-244,091.29</td>\n" +
            "            <td class=\"table-division\">-539,336.93</td>\n" +
            "            <td class=\"table-division\">72,191.83</td>\n" +
            "            <td class=\"table-division\">-3,448.29</td>\n" +
            "            <td class=\"table-division\">507,501.65</td>\n" +
            "            <td class=\"table-division\">36,539.18</td>\n" +
            "          </tr>\n" +
            "          <tr class=\"total-row\">\n" +
            "            <td colspan=\"3\" class=\"initial\"></td>\n" +
            "            <td colspan=\"3\" class=\"initial\"></td>\n" +
            "            <td colspan=\"3\" class=\"initial\"></td>\n" +
            "            <td class=\"table-division\">244,460.38</td>\n" +
            "            <td class=\"table-division\">-244,091.29</td>\n" +
            "            <td class=\"table-division\">-539,336.93</td>\n" +
            "            <td class=\"table-division\">72,191.83</td>\n" +
            "            <td class=\"table-division\">-3,448.29</td>\n" +
            "            <td class=\"table-division\">507,501.65</td>\n" +
            "            <td class=\"table-division\">36,539.18</td>\n" +
            "          </tr>\n" +
            "          </tbody>\n" +
            "          <tbody class=\"group\">\n" +
            "          <tr class=\"currency-row\">\n" +
            "            <td>Moeda:</td>\n" +
            "            <td>MXN</td>\n" +
            "            <td>PESO MEXICANO</td>\n" +
            "            <td colspan=\"3\"></td>\n" +
            "            <td colspan=\"3\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "            <td class=\"table-division\"></td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO CENTRAL DE LUXEMBURGO</td>\n" +
            "            <td colspan=\"3\">BCLXLULL</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO CENTRAL DE LUXEMBURGO</td>\n" +
            "            <td colspan=\"3\">BCLXLULL</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO CENTRAL DE LUXEMBURGO</td>\n" +
            "            <td colspan=\"3\">BCLXLULLXXX</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "            <td class=\"table-division\">0.00</td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td colspan=\"3\">BANCO SANTANDER CENTRAL HISPANO</td>\n" +
            "            <td colspan=\"3\">BCLXLULLXXX</td>\n" +
            "            <td colspan=\"3\">LU29999000140303000E</td>\n" +
            "            <td class=\"table-division\">244,460.38</td>\n" +
            "            <td class=\"table-division\">-244,091.29</td>\n" +
            "            <td class=\"table-division\">-539,336.93</td>\n" +
            "            <td class=\"table-division\">72,191.83</td>\n" +
            "            <td class=\"table-division\">-3,448.29</td>\n" +
            "            <td class=\"table-division\">507,501.65</td>\n" +
            "            <td class=\"table-division\">36,539.18</td>\n" +
            "          </tr>\n" +
            "          <tr class=\"total-row\">\n" +
            "            <td colspan=\"3\" class=\"initial\"></td>\n" +
            "            <td colspan=\"3\" class=\"initial\"></td>\n" +
            "            <td colspan=\"3\" class=\"initial\"></td>\n" +
            "            <td class=\"table-division\">244,460.38</td>\n" +
            "            <td class=\"table-division\">-244,091.29</td>\n" +
            "            <td class=\"table-division\">-539,336.93</td>\n" +
            "            <td class=\"table-division\">72,191.83</td>\n" +
            "            <td class=\"table-division\">-3,448.29</td>\n" +
            "            <td class=\"table-division\">507,501.65</td>\n" +
            "            <td class=\"table-division\">36,539.18</td>\n" +
            "          </tr>\n" +
            "          </tbody>\n" +
            "        </table>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</section>\n" +
            "</body>\n" +
            "\n" +
            "</html>\n";

    public void replacesValues(List<ReturnData1> data) {

        data.stream().forEach(d -> {
            htmlBody = htmlBody + "${titular}";
            htmlBody = htmlBody.replace("{$titular}", d.getTitular());
            log.info(htmlBody.toString());
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
