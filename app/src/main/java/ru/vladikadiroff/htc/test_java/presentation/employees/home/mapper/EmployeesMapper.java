package ru.vladikadiroff.htc.test_java.presentation.employees.home.mapper;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ViewModelScoped;
import ru.vladikadiroff.htc.test_java.R;
import ru.vladikadiroff.htc.test_java.domain.employees.models.EmployeeDomainModel;
import ru.vladikadiroff.htc.test_java.domain.employees.models.EmployeesDomainModel;
import ru.vladikadiroff.htc.test_java.presentation.employees.home.models.CompanyInfo;
import ru.vladikadiroff.htc.test_java.presentation.employees.home.models.Employee;
import ru.vladikadiroff.htc.test_java.presentation.employees.home.models.EmployeeAdapterModel;

@ViewModelScoped
public class EmployeesMapper {

    private final Resources resources;

    @Inject
    EmployeesMapper(Resources resources) {
        this.resources = resources;
    }

    public CompanyInfo mapToCompanyInfo(EmployeesDomainModel model) {
        return new CompanyInfo(
                model.getCompanyName(),
                mapAgeFromDomain(model.getCompanyAge()),
                model.getCompanyCompetences()
        );
    }

    public List<EmployeeAdapterModel> mapEmployeesFromDomain(List<EmployeeDomainModel> employees) {
        List<EmployeeAdapterModel> list = new ArrayList<>();
        for (EmployeeDomainModel employee : employees) {
            list.add(mapEmployeeFromDomain(employee));
        }
        return list;
    }

    public List<EmployeeDomainModel> mapEmployeesToDomain(List<EmployeeAdapterModel> employees) {
        List<EmployeeDomainModel> list = new ArrayList<>();
        for (EmployeeAdapterModel employee : employees) {
            if (employee instanceof Employee) list.add(mapEmployeeToDomain((Employee) employee));
        }
        return list;
    }

    private EmployeeDomainModel mapEmployeeToDomain(Employee model) {
        return new EmployeeDomainModel(
                model.getName(),
                model.getPhone(),
                model.getSkills()
        );
    }

    private Employee mapEmployeeFromDomain(EmployeeDomainModel model) {
        return new Employee(
                model.getName(),
                model.getPhone(),
                model.getSkills()
        );
    }

    @NonNull
    private String mapAgeFromDomain(String age) {
        if (age.isEmpty()) return age;
        try {
            if (Integer.parseInt(age) == 1) return resources.getString(R.string.founded_year_ago);
            else return resources.getString(R.string.founded_years_ago, age);
        } catch (Exception e) {
            return "";
        }
    }

}
