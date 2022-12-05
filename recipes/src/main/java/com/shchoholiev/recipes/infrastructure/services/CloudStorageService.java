package com.shchoholiev.recipes.infrastructure.services;

import com.azure.storage.blob.BlobContainerClientBuilder;
import com.shchoholiev.recipes.application.interfaces.services.ICloudStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class CloudStorageService implements ICloudStorageService {

    @Value("${connectionStrings.azure.blobStorage}")
    private String _connectionString;

    @Override
    public String uploadFile(String containerName, String fileName, InputStream data) {
        var container = new BlobContainerClientBuilder()
                .connectionString(_connectionString)
                .containerName(containerName)
                .buildClient();

        var blob = container.getBlobClient(fileName);
        blob.upload(data, true);

        var url = blob.getBlobUrl();
        return  blob.getBlobUrl();
    }
}
