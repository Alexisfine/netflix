package com.alex.upload;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public class FileStore {
    private AmazonS3 amazonS3;

    @Autowired
    public FileStore(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void save(
            String path,
            String fileName,
            Optional<Map<String, String>> optionalMetaData,
            InputStream inputStream) {

        ObjectMetadata metaData = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach((key,value) -> metaData.addUserMetadata(key, value));
            }
        });
        try {
            amazonS3.putObject(path, fileName, inputStream, metaData);
        } catch (AmazonServiceException ex) {
            throw new IllegalStateException("Failed to store file to S3", ex);
        }
    }
}
