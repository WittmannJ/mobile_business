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

    public static Post fromSnapshot(DataSnapshot dataSnapshot){
        Post p = new Post();

        p.uid = dataSnapshot.child("uid").getValue().toString();
        p.author = dataSnapshot.child("author").getValue().toString();
        p.body = dataSnapshot.child("body").getValue().toString();
        p.timestamp = (long) dataSnapshot.child("timestamp").getValue();
        p.title = dataSnapshot.child("title").getValue().toString();

        p.firebaseKey = dataSnapshot.getKey();


        return p;
    }
}
