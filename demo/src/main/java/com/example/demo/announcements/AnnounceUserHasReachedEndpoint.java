package com.example.demo.announcements;

public class AnnounceUserHasReachedEndpoint {

    private int numberOfInvocations = 0;

    public AnnounceUserHasReachedEndpoint() {
        numberOfInvocations++;
    }

    public String announce() {
        return " has reached this endpoint";
    }

    public int getNumberOfInvocations() {
        return numberOfInvocations;
    }
}
