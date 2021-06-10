package ru.vladikadiroff.htc.test_java.domain.employees.models;

import androidx.annotation.NonNull;

import java.util.List;

public class EmployeesDomainModel {

    private final String companyName;
    private final String companyAge;
    private final List<String> getCompanyCompetences;
    private final List<EmployeeDomainModel> employees;

    public EmployeesDomainModel(@NonNull String companyName,
                                @NonNull String companyAge,
                                @NonNull List<String> getCompanyCompetences,
                                @NonNull List<EmployeeDomainModel> employees) {
        this.companyName = companyName;
        this.companyAge = companyAge;
        this.getCompanyCompetences = getCompanyCompetences;
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAge() {
        return companyAge;
    }

    public List<String> getCompanyCompetences() {
        return getCompanyCompetences;
    }

    public List<EmployeeDomainModel> getEmployees() {
        return employees;
    }

}
