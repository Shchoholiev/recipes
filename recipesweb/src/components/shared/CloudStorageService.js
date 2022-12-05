const CloudStorageService = {

    baseUrl: "http://localhost:8080/api/cloud-storage",

    upload: function(blobContainer, file) {
        var formData = new FormData();
        formData.append("file", file, file.name)
        return fetch(`${this.baseUrl}/upload/${blobContainer}`, {
            method: "POST",
            body: formData
        });
    },
}

export default CloudStorageService