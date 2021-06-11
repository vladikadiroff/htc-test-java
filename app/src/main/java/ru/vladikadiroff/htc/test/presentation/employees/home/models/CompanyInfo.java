package ru.vladikadiroff.htc.test.presentation.employees.home.models;

import androidx.annotation.NonNull;

import java.util.List;

public class CompanyInfo {

    private final String name;
    private final String age;
    private final List<String> competences;

    public CompanyInfo(@NonNull String name,
                       @NonNull String age,
                       @NonNull List<String> competences) {
        this.name = name;
        this.age = age;
        this.competences = competences;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public boolean isEmpty(){
        return (name.isEmpty() && age.isEmpty() && competences.isEmpty());
    }

}
