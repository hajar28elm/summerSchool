package com.example.summerschool.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String insight;

    private String requirements;
    public List<String> getRequirementsList() {
        return Arrays.asList(requirements.split(","));
    }

    public void setRequirementsList(List<String> requirementsList) {
        this.requirements = String.join(",", requirementsList);
    }
    @ManyToOne
    private Organizer organizer;
}
