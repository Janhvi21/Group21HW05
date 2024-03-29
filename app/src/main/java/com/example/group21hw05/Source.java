package com.example.group21hw05;

import androidx.annotation.NonNull;

public class Source {
public Source(){}
    String id;
    String name;

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
