package ru.vladikadiroff.htc.test.presentation.employees.home.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.vladikadiroff.htc.test.presentation.employees.home.models.EmployeeAdapterModel;
import ru.vladikadiroff.htc.test.presentation.employees.home.models.Employee;

public class EmployeesDiffUtil extends DiffUtil.ItemCallback<EmployeeAdapterModel> {

    @Override
    public boolean areItemsTheSame(@NonNull EmployeeAdapterModel oldItem,
                                   @NonNull EmployeeAdapterModel newItem) {
        if (oldItem instanceof Employee && newItem instanceof Employee) {
            return (((Employee) oldItem).getName().equals(((Employee) newItem).getName())
                    && ((Employee) oldItem).getPhone().equals(((Employee) newItem).getPhone()));
        } else {
            return false;
        }
    }

    @Override
    public boolean areContentsTheSame(@NonNull EmployeeAdapterModel oldItem,
                                      @NonNull EmployeeAdapterModel newItem) {
        if (oldItem instanceof Employee && newItem instanceof Employee) {
            return (((Employee) oldItem).getSkills().equals(((Employee) newItem).getSkills()));
        } else {
            return false;
        }
    }

}
