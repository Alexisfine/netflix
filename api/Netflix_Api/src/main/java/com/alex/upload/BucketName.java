package com.alex.upload;

public enum BucketName {
    PROFILE_IMAGE("alex-netflix-image");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
