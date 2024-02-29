package com.school.progress.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="progresses")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(nullable = false)
    private Long courseId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String module;

    public Progress() {
    }
    public Progress(Long Id, Long courseId, Long userId, String status, String module) {
        this.Id = Id;
        this.courseId = courseId;
        this.userId = userId;
        this.status = status;
        this.module = module;
    }

    public Long getProgressId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}