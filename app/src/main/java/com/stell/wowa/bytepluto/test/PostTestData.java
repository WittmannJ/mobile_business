package com.stell.wowa.bytepluto.test;

import com.stell.wowa.bytepluto.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostTestData {
    public static List<Post> createTestData(){
        String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ";
        int amountFakePosts = 10;
        List<Post> postTestList = new ArrayList<>();
        long time = new java.util.Date().getTime();

        for (int i = 0; i < amountFakePosts; i++){
            postTestList.add( new Post(""+i, "Author " + i, "Title " + i, body, time++));
        }

        return postTestList;
    }
}
