package com.badplay.service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileService {

    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;

    public FileService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String uploadArquivo(MultipartFile arquivo) {
        try {
            boolean encontrado = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!encontrado) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            String nomeArquivo = UUID.randomUUID() + "-" + arquivo.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(nomeArquivo)
                            .stream(arquivo.getInputStream(), arquivo.getSize(), -1)
                            .contentType(arquivo.getContentType())
                            .build()
            );

            return nomeArquivo;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao subir arquivo para o MinIO: " + e.getMessage());
        }
    }
}