package com.memory.connect.model.test.entity;

import com.memory.connect.model.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "test")
public class Test extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private int id;

    @Column(name = "test_question", nullable = false)
    private String question;

    @Builder
    public Test(String question) {
        this.question = question;
    }
}
