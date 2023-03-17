package br.com.valim.contratoapi.utils;

import com.itextpdf.html2pdf.HtmlConverter;
import lombok.experimental.UtilityClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@UtilityClass
public class HandlePdf {
    public byte[] createPdfFromHtml(String html) throws Exception {
        OutputStream os = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(new ByteArrayInputStream(html.getBytes()), os);
        byte[] pdfBytes = ((ByteArrayOutputStream) os).toByteArray();
        return pdfBytes;
    }
}
