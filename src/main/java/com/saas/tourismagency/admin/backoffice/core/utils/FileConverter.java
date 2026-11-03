package com.saas.tourismagency.admin.backoffice.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class FileConverter {
    public ByteArrayResource toByteArrayResource(String base64EncodedString){
        return new ByteArrayResource(toByteArray(base64EncodedString));
    }

    public ByteArrayResource toByteArrayResource(byte[] arrayByte){
        return new ByteArrayResource(arrayByte);
    }

    public byte[] toByteArray(String base64EncodedString){
        return Base64.getMimeDecoder().decode(base64EncodedString);
    }

    public byte[] toByteArray(InputStream inputStream) throws IOException {
        return IOUtils.toByteArray(inputStream);
    }

    public String toBase64(InputStream inputStream) throws IOException {
        return Base64.getEncoder().encodeToString(toByteArray(inputStream));
    }

    public String toBase64(byte[] arrayByte){
        return Base64.getEncoder().encodeToString(arrayByte);
    }

    public InputStream toInputStream(String base64EncodedString){
        return new ByteArrayInputStream(toByteArray(base64EncodedString));
    }

    public InputStream toInputStream(byte[] arrayByte){
        return new ByteArrayInputStream(arrayByte);
    }



    public String getContentType(String base64EncodedString){
        String[] parts = base64EncodedString.split(";");
        if (parts.length > 0) {
            String[] typeParts = parts[0].split(":");
            if (typeParts.length > 1) {
                return typeParts[1];
            }
        }
        return null;
    }

    public String getExtension(String base64EncodedString){
        if(base64EncodedString != null && !base64EncodedString.isEmpty()){
            String contentType = getContentType(base64EncodedString);
            String extension[] = contentType.split("/");
            return ".".concat(extension[1]);
        }
        return null;
    }

    public byte[] convertBase64ToByteArray(String base64String){
        if(isBasicBase64(base64String)){
            return Base64.getDecoder().decode(base64String);
        }else if(isUrlSafeBase64(base64String)){
            return Base64.getUrlDecoder().decode(base64String);
        }else if(isMimeBase64(base64String)){
            return Base64.getMimeDecoder().decode(base64String);
        }

        return null;
    }

    public File convertInputreamToFile(InputStream inputStream,String filename,String destinationFolder) throws IOException {
        File file = new File(destinationFolder,filename);
        FileUtils.copyInputStreamToFile(inputStream, file);
        return file;
    }

    private boolean isBasicBase64(String base64String) {
        System.out.println("Basic Decoder");
        try {
            Base64.getDecoder().decode(base64String);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isUrlSafeBase64(String base64String) {
        System.out.println("Url Safe Decoder");
        try {
            Base64.getUrlDecoder().decode(base64String);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isMimeBase64(String base64String) {
        System.out.println("Mime Decoder");
        try {
            Base64.getMimeDecoder().decode(base64String);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
