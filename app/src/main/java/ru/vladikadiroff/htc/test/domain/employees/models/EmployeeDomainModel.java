package ru.vladikadiroff.htc.test.domain.employees.models;

import java.util.List;

public class EmployeeDomainModel {

    private final String name;
    private final String phone;
    private final List<String> skills;

    public EmployeeDomainModel(String name, String phone, List<String> skills) {
        this.name = name;
        this.phone = phone;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getSkills() {
        return skills;
    }

}