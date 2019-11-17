package com.loyaltyone.interview.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String text;

    private Timestamp timestamp;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "parentPost")
    private List<Post> comments;

    @ManyToOne(targetEntity=Post.class)
    @JoinColumn(name="parent_post")
    private Post parentPost;

    private String city;

    public Post() {
    }

    public Post(String text, Timestamp timestamp, User user) {
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;
    }

    public Post(String text, Timestamp timestamp, User user, Post parentPost) {
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;
        this.parentPost = parentPost;
    }

    public Post(String text, Timestamp timestamp, User user, String city) {
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;
        this.city = city;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getComments() {
        return comments;
    }

    public void setComments(List<Post> comments) {
        this.comments = comments;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
