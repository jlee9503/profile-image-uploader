package com.example.ProfileImageUploader.datastore;

import com.example.ProfileImageUploader.userProfile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserDataStore {
    // Create fake user database using arraylist
    private static final List <UserProfile> userProfileList = new ArrayList<>();

    // Add fake user data
    static {
        userProfileList.add(new UserProfile(UUID.fromString("3a5b3778-9447-4843-a983-a8f4d5d0a5f2"), "johndoe", null));
        userProfileList.add(new UserProfile(UUID.fromString("7901ae03-5890-430e-86a9-c5b61d791ef8"), "janesmith", null));
    }

    // Get all users in the list
    public List<UserProfile> getUserProfileList() {
        return userProfileList;
    }
}
