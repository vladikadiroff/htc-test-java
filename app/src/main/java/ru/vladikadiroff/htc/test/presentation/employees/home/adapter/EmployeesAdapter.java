package ru.vladikadiroff.htc.test.presentation.employees.home.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import ru.vladikadiroff.htc.test.presentation.employees.home.adapter.EmployeesViewHolderFactory.EmployeeItem;
import ru.vladikadiroff.htc.test.presentation.employees.home.models.Employee;
import ru.vladikadiroff.htc.test.presentation.employees.home.models.EmployeeAdapterModel;
import ru.vladikadiroff.htc.test.presentation.employees.home.models.EmployeesEmpty;

public class EmployeesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener onItemClick;
    private OnDifferCalculateCompleteListener onDifferCalculateListener;
    private final AsyncListDiffer<EmployeeAdapterModel> differ =
            new AsyncListDiffer<>(this, new EmployeesDiffUtil());


    public void submitData(List<EmployeeAdapterModel> list) {
        if (list.isEmpty()) differ.submitList(Collections.singletonList(new EmployeesEmpty()),
                () -> onDifferCalculateListener.onComplete());
        else differ.submitList(list, () -> onDifferCalculateListener.onComplete());
    }

    public void setOnDifferCalculateListener(OnDifferCalculateCompleteListener listener) {
        onDifferCalculateListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClick = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return EmployeesViewHolderFactory.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EmployeesViewHolderFactory.EmployeeItem)
            ((EmployeeItem) holder).bind((Employee) differ.getCurrentList().get(position), onItemClick);
    }

    @Override
    public int getItemViewType(int position) {
        return differ.getCurrentList().get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public interface OnItemClickListener {
        void onItemClick(String name);
    }

    public interface OnDifferCalculateCompleteListener {
        void onComplete();
    }

}
