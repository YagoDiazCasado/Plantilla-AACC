package com.aadd.ydc.vo.jpa;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class JpaMappedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)  //Si un objeto tiene muchso objs dentro
    private List<JpaChildEntity> children;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<JpaChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<JpaChildEntity> children) {
        this.children = children;
    }
}
