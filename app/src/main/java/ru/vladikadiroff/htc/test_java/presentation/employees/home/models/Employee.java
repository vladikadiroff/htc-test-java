package ru.vladikadiroff.htc.test_java.presentation.employees.home.models;

import androidx.annotation.NonNull;

import java.util.List;

public class Employee implements EmployeeAdapterModel {

    private final String name;
    private final String phone;
    private final List<String> skills;

    public Employee(@NonNull String name, @NonNull String phone, @NonNull List<String> skills) {
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

    @Override
    public int getViewType() {
        return EmployeeAdapterModel.EMPLOYEE;
    }

}
