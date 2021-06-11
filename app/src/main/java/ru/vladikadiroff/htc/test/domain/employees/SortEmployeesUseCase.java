package ru.vladikadiroff.htc.test.domain.employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ViewModelScoped;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeeDomainModel;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeesSortStrategy;

@ViewModelScoped
public class SortEmployeesUseCase {

    private EmployeesSortStrategy sortStrategy = EmployeesSortStrategy.SORT_BY_NAME;

    @Inject
    SortEmployeesUseCase() {
    }

    public List<EmployeeDomainModel> sort(List<EmployeeDomainModel> list) {
        List<EmployeeDomainModel> sortList = new ArrayList<>(list);
        switch (sortStrategy) {
            case SORT_BY_NAME:
                Collections.sort(sortList, sortByName());
                break;
            case SORT_BY_PHONE:
                Collections.sort(sortList, sortByPhone());
                break;
            case SORT_BY_COUNT_SKILLS:
                Collections.sort(sortList, sortBySkillsCount());
                break;
        }
        return sortList;
    }

    private Comparator<EmployeeDomainModel> sortByName() {
        return (model1, model2) -> {
            if (model1.getName().isEmpty()) return 1;
            if (model2.getName().isEmpty()) return -1;
            return model1.getName().compareTo(model2.getName());
        };
    }

    private Comparator<EmployeeDomainModel> sortByPhone() {
        return (model1, model2) -> {
            if (model1.getPhone().isEmpty()) return 1;
            if (model2.getPhone().isEmpty()) return -1;
            return model1.getPhone().compareTo(model2.getPhone());
        };
    }

    private Comparator<EmployeeDomainModel> sortBySkillsCount() {
        return (model1, model2) -> model2.getSkills().size() - model1.getSkills().size();
    }

    public EmployeesSortStrategy getSortStrategy() {
        return sortStrategy;
    }

    public void setSortStrategy(EmployeesSortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

}
