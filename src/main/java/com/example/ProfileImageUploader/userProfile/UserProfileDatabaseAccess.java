package com.example.ProfileImageUploader.userProfile;

import com.example.ProfileImageUploader.datastore.FakeUserDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserProfileDatabaseAccess {
    // Change to real database
    private final FakeUserDataStore fakeUserDataStore;

    @Autowired
    public UserProfileDatabaseAccess(FakeUserDataStore fakeUserDataStore) {
        this.fakeUserDataStore = fakeUserDataStore;
    }

    List<UserProfile> getUserProFileList() {
        return fakeUserDataStore.getUserProfileList();
    }
}
