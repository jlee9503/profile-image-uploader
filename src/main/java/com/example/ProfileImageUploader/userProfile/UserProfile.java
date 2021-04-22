package com.example.ProfileImageUploader.userProfile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {

    private final UUID userId;
    private final String userName;
    private String profileImageLink;

    public UserProfile(UUID userId, String userName, String profileImageLink) {
        this.userId = userId;
        this.userName = userName;
        this.profileImageLink = profileImageLink;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    // Image link can be null
    public Optional<String> getProfileImageLink() {
        return Optional.ofNullable(profileImageLink);
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(profileImageLink, that.profileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, profileImageLink);
    }
}
