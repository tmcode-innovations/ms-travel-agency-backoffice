package com.saas.tourismagency.admin.backoffice.core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Component
public class FileStorageService {

    public StoredFile store(MultipartFile file, String basePath) {
        if (basePath == null || basePath.isEmpty()) {
            throw new IllegalArgumentException("El directorio base de almacenamiento no puede ser nulo o vacio.");
        }
        File dir = new File(basePath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created && !dir.exists()) {
                throw new IllegalArgumentException("No se pudo crear el directorio de almacenamiento: " + dir.getAbsolutePath());
            }
        }
        if (!dir.canWrite()) {
            throw new IllegalArgumentException("No se tiene permiso de escritura en el directorio: " + dir.getAbsolutePath());
        }

        String originalName = file.getOriginalFilename();
        String extension = getExtension(originalName);
        String randomFile = UUID.randomUUID().toString().replaceAll("-", "");

        Path serverFile = Paths.get(dir.getAbsolutePath(), randomFile + "." + extension);

        try (FileOutputStream stream = new FileOutputStream(serverFile.toFile())) {
            stream.write(file.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException("Error al guardar el archivo en " + serverFile.toAbsolutePath(), e);
        }

        return new StoredFile(
                serverFile.toAbsolutePath().toString(),
                originalName,
                extension,
                randomFile
        );
    }

    public byte[] read(String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        Path file = Paths.get(path);
        if (Files.exists(file)) {
            try {
                return Files.readAllBytes(file);
            } catch (IOException e) {
                log.error("Error al leer el archivo en {}", path, e);
            }
        }
        return null;
    }

    private String getExtension(String filename) {
        if (filename == null) {
            return "";
        }
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1) {
            return "";
        }
        return filename.substring(extensionIndex + 1);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoredFile {
        private String path;
        private String fileName;
        private String extension;
        private String identify;
    }
}