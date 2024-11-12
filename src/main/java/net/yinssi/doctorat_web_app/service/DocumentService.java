package net.yinssi.doctorat_web_app.service;

import net.yinssi.doctorat_web_app.entity.Document;

import java.io.IOException;
import java.io.InputStream;

public interface DocumentService {

    public Document uploadPDF(String fileName, InputStream fileInputStream) throws IOException;
}
