package ru.vladikadiroff.htc.test.presentation.employees.home.models;

public class EmployeesEmpty implements EmployeeAdapterModel {
    @Override
    public int getViewType() {
        return EmployeeAdapterModel.EMPLOYEES_EMPTY;
    }
}
