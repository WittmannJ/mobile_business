package com.stell.wowa.bytepluto.model;

import com.google.firebase.database.DataSnapshot;

public class Post {
    public String uid;
    public String author;
    public String title;
    public String body;
    public long timestamp;
    public String firebaseKey;

    public Post() {
    }

    public Post(String uid, String author, String title, String body, long timestamp) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
    }

    public static Post fromSnapshot(DataSnapshot dataSnapshot) {
        Post p = new Post();
        p.uid = (String) dataSnapshot.child("uid").getValue();
        p.author = (String) dataSnapshot.child("author").getValue();
        p.title = (String) dataSnapshot.child("title").getValue();
        p.body = (String) dataSnapshot.child("body").getValue();
        p.timestamp = (long) dataSnapshot.child("timestamp").getValue();

        p.firebaseKey = dataSnapshot.getKey();
        return p;
    }
}
