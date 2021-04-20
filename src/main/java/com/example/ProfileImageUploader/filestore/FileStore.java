package com.example.ProfileImageUploader.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    public void save(
            String path,
            String filename,
            Optional<Map<String, String>> optionalMetadata,
            InputStream inputStream) {

        // Create an object metadata from s3 bucket
        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if (!map.isEmpty()) {
                // map.forEach((key, val) -> ObjectMetadata.addUserMetadata(key, val));
                // Method Reference:
                map.forEach(metadata::addUserMetadata);
            }
        });

        // save data to s3 bucket
        try {
            s3.putObject(path, filename, inputStream, metadata);
        } catch (AmazonServiceException err) {
            throw new IllegalStateException("Failed to store file to s3 bucket" + err);
        }
    }
}
