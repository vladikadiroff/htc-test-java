package ru.vladikadiroff.htc.test_java.presentation.employees.sort;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import ru.vladikadiroff.htc.test_java.R;
import ru.vladikadiroff.htc.test_java.databinding.DialogEmployeesSortStrategyBinding;
import ru.vladikadiroff.htc.test_java.domain.employees.models.EmployeesSortStrategy;
import ru.vladikadiroff.htc.test_java.presentation.core.base.ViewBindingBottomSheetDialog;
import ru.vladikadiroff.htc.test_java.presentation.employees.sort.adapter.SortStrategyAdapter;

@AndroidEntryPoint
public class ChangeSortStrategyDialog extends
        ViewBindingBottomSheetDialog<DialogEmployeesSortStrategyBinding> {

    private ChangeSortStrategyViewModel viewModel;
    private final SortStrategyAdapter adapter = new SortStrategyAdapter();

    @Override
    protected void initDialog() {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_Demo_BottomSheetDialog);
        initViewModel();
        getViewBinding().strategies.setAdapter(adapter);
        adapter.setOnClickStrategyListener(this::returnStrategy);
        viewModel.initViewState(getCurrentStrategy());
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ChangeSortStrategyViewModel.class);
        viewModel.getStrategies().observe(this, adapter::submitData);
    }

    private EmployeesSortStrategy getCurrentStrategy() {
        String key = getString(R.string.current_sort_strategy_key);
        String arg = (getArguments() != null) ? getArguments().getString(key) :
                EmployeesSortStrategy.SORT_BY_NAME.name();
        return EmployeesSortStrategy.valueOf(arg);
    }

    private void returnStrategy(EmployeesSortStrategy strategy) {
        String key = getString(R.string.employees_change_sort_strategy_key);
        Objects.requireNonNull(NavHostFragment.findNavController(this)
                .getPreviousBackStackEntry())
                .getSavedStateHandle()
                .set(key, strategy);
        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public DialogEmployeesSortStrategyBinding viewBindingInflate(LayoutInflater layoutInflater,
                                                                 ViewGroup root,
                                                                 Boolean attachToRoot) {
        return DialogEmployeesSortStrategyBinding.inflate(layoutInflater, root, attachToRoot);
    }

}
