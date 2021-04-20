package com.example.ProfileImageUploader.userProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserProfileService {
    private final UserProfileDatabaseAccess userProfileDatabaseAccess;

    @Autowired
    public UserProfileService(UserProfileDatabaseAccess userProfileDatabaseAccess) {
        this.userProfileDatabaseAccess = userProfileDatabaseAccess;
    }

    List<UserProfile> getUserProfileList() {
        return userProfileDatabaseAccess.getUserProFileList();
    }

    public void uploadUserProfileImage(UUID userId, MultipartFile file) {

    }
}
