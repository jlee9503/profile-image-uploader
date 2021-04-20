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
        userProfileList.add(new UserProfile(UUID.randomUUID(), "johndoe", null));
        userProfileList.add(new UserProfile(UUID.randomUUID(), "janesmith", null));
    }

    // Get all users in the list
    public List<UserProfile> getUserProfileList() {
        return userProfileList;
    }
}
