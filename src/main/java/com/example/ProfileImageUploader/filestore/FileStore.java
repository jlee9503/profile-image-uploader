package com.example.ProfileImageUploader.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public byte[] download(String path, String key) {
        try {
            S3Object s3Object = s3.getObject(path, key);
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
            return IOUtils.toByteArray(s3ObjectInputStream);
        } catch (AmazonServiceException | IOException err) {
            throw new IllegalStateException("Failed to download the file from s3 bucket" + err);
        }
    }
}
