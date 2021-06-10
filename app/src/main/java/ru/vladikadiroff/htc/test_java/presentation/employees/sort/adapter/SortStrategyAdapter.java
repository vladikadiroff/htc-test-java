package ru.vladikadiroff.htc.test_java.presentation.employees.sort.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.vladikadiroff.htc.test_java.R;
import ru.vladikadiroff.htc.test_java.databinding.ItemSortTypeBinding;
import ru.vladikadiroff.htc.test_java.domain.employees.models.EmployeesSortStrategy;

import static ru.vladikadiroff.htc.test_java.presentation.core.helpers.ViewHelper.createView;

public class SortStrategyAdapter extends
        RecyclerView.Adapter<SortStrategyAdapter.SortStrategyItem> {

    private OnClickStrategyListener listener;
    private final List<SortStrategyModel> list = new ArrayList<>();

    public void submitData(List<SortStrategyModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnClickStrategyListener(OnClickStrategyListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SortStrategyItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SortStrategyItem(createView(R.layout.item_sort_type, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull SortStrategyItem holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class SortStrategyItem extends RecyclerView.ViewHolder {

        private final ItemSortTypeBinding binding = ItemSortTypeBinding.bind(itemView);

        public SortStrategyItem(@NonNull View itemView) {
            super(itemView);
        }

        void bind(SortStrategyModel model, OnClickStrategyListener listener) {
            binding.name.setText(model.getName());
            binding.check.setVisibility(model.getIsActive() ? View.VISIBLE : View.INVISIBLE);
            binding.container.setOnClickListener(view -> listener.onClick(model.getStrategy()));
        }

    }

    public interface OnClickStrategyListener {
        void onClick(EmployeesSortStrategy strategy);
    }

}
