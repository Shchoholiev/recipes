package com.shchoholiev.recipes.api.controllers;

import com.azure.core.util.BinaryData;
import com.shchoholiev.recipes.application.interfaces.services.ICategoriesService;
import com.shchoholiev.recipes.application.interfaces.services.ICloudStorageService;
import com.shchoholiev.recipes.application.models.dtos.CategoryDto;
import com.shchoholiev.recipes.domain.common.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/cloud-storage")
public class CloudStorageController {

    @Autowired
    private ICloudStorageService _cloudStorageService;

    @PostMapping("/upload/{containerName}")
    public Object addCategory(@PathVariable String containerName, @RequestBody MultipartFile file) throws IOException {
        var url = _cloudStorageService.uploadFile(containerName, file.getOriginalFilename(), file.getInputStream());
        return new Object() { public String link = url; };
    }
}
