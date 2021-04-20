package com.example.ProfileImageUploader.userProfile;

import com.example.ProfileImageUploader.bucket.BucketName;
import com.example.ProfileImageUploader.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService {
    private final UserProfileDatabaseAccess userProfileDatabaseAccess;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDatabaseAccess userProfileDatabaseAccess, FileStore fileStore) {
        this.userProfileDatabaseAccess = userProfileDatabaseAccess;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfileList() {
        return userProfileDatabaseAccess.getUserProFileList();
    }

    public void uploadUserProfileImage(UUID userId, MultipartFile file) {
        // Check the file validity
        isFileEmpty(file);
        isImage(file);

        // Check if the user exists in the database
        UserProfile user = userProfileDatabaseAccess.getUserProFileList()
                .stream()
                .filter(userProfile -> userProfile.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalStateException(String.format("File with user ID: %s doesn't exist", userId));
                });

        // Save metadata from file
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        // Store the image file in s3
        // Path: bucket_name/userId
        String path = String.format("%s/%s",BucketName.PROFILE_IMAGE.getBucketName(), user.getUserId());
        // File Name: original_fileName-randomId
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("Invalid file type: Must be an image file" + "[" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload the file" + "[" + file.getSize() + "]");
        }
    }
}
