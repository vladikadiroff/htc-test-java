package ru.vladikadiroff.htc.test.presentation.employees.home.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.vladikadiroff.htc.test.R;
import ru.vladikadiroff.htc.test.databinding.ItemEmployeeBinding;
import ru.vladikadiroff.htc.test.presentation.core.helpers.ChipGroupHelper;
import ru.vladikadiroff.htc.test.presentation.core.helpers.TextViewHelper;
import ru.vladikadiroff.htc.test.presentation.employees.home.models.Employee;
import ru.vladikadiroff.htc.test.presentation.employees.home.models.EmployeeAdapterModel;

import static ru.vladikadiroff.htc.test.presentation.core.helpers.ViewHelper.createView;

public class EmployeesViewHolderFactory {

    public static RecyclerView.ViewHolder create(ViewGroup root, int viewType) {
        if (viewType == EmployeeAdapterModel.EMPLOYEE)
            return new EmployeeItem(createView(R.layout.item_employee, root));
        return new EmployeesListEmptyItem(createView(R.layout.item_employees_list_empty, root));
    }

    public static class EmployeeItem extends RecyclerView.ViewHolder {

        private final ItemEmployeeBinding binding = ItemEmployeeBinding.bind(itemView);

        public EmployeeItem(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Employee model, EmployeesAdapter.OnItemClickListener listener) {
            if (model.getName().isEmpty()) binding.name.setText(binding.getRoot().getContext().getResources().getString(R.string.uknown_person));
            else binding.name.setText(model.getName());
            TextViewHelper.setTextOrGone(binding.phone, model.getPhone());
            ChipGroupHelper.replaceChipsByList(binding.skills, model.getSkills(), R.attr.CustomChipStyle);
            binding.container.setOnClickListener(view -> listener.onItemClick(model.getName()));
        }

    }

    public static class EmployeesListEmptyItem extends RecyclerView.ViewHolder {
        public EmployeesListEmptyItem(@NonNull View itemView) {
            super(itemView);
        }
    }

}
