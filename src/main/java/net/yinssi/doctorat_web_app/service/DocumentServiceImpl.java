package net.yinssi.doctorat_web_app.service;

import net.yinssi.doctorat_web_app.entity.Document;
import net.yinssi.doctorat_web_app.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document uploadPDF(String fileName, InputStream fileInputStream) throws IOException {
        Document document = new Document();
        document.setFileName(fileName);

        // Read the PDF file into a byte array
        byte[] pdfBytes = fileInputStream.readAllBytes();
        document.setData(pdfBytes);

        documentRepository.save(document);
        return document;
    }

}
