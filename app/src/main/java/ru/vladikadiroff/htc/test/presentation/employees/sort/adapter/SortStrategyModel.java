package ru.vladikadiroff.htc.test.presentation.employees.sort.adapter;

import ru.vladikadiroff.htc.test.domain.employees.models.EmployeesSortStrategy;

public class SortStrategyModel {

    private final String name;
    private final boolean isActive;
    private final EmployeesSortStrategy strategy;

    public SortStrategyModel(String name, boolean isActive, EmployeesSortStrategy strategy) {
        this.name = name;
        this.isActive = isActive;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public EmployeesSortStrategy getStrategy() {
        return strategy;
    }

}
