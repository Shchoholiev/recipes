package com.shchoholiev.recipes.application.interfaces.services;

import com.azure.core.util.BinaryData;

import java.io.InputStream;

public interface ICloudStorageService {
    String uploadFile(String containerName, String fileName, InputStream data);
}
