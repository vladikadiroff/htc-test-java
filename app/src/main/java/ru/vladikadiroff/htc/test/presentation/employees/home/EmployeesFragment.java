package ru.vladikadiroff.htc.test.presentation.employees.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import ru.vladikadiroff.htc.test.R;
import ru.vladikadiroff.htc.test.databinding.FragmentEmployeesBinding;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeesSortStrategy;
import ru.vladikadiroff.htc.test.presentation.core.base.ViewBindingFragment;
import ru.vladikadiroff.htc.test.presentation.core.helpers.AppbarHelper;
import ru.vladikadiroff.htc.test.presentation.core.helpers.ChipGroupHelper;
import ru.vladikadiroff.htc.test.presentation.core.helpers.TextViewHelper;
import ru.vladikadiroff.htc.test.presentation.employees.home.adapter.EmployeesAdapter;

@AndroidEntryPoint
public class EmployeesFragment extends ViewBindingFragment<FragmentEmployeesBinding> {

    private EmployeesViewModel viewModel;
    private final EmployeesAdapter adapter = new EmployeesAdapter();

    @Override
    protected void initFragment() {
        initViews();
        initViewModel();
        observeOnChangeSortStrategy();
    }

    private void initViews() {
        initToolbar();
        getViewBinding().employees.setAdapter(adapter);
        getViewBinding().fabSort.setOnClickListener(view -> viewModel.changeSortStrategy());
        getViewBinding().swipeRefresh.setOnRefreshListener(() -> viewModel.refresh());
        adapter.setOnItemClickListener(name -> viewModel.onClickItem(name));
        adapter.setOnDifferCalculateListener(() -> getViewBinding().employees.scrollToPosition(0));
    }

    private void initToolbar() {
        enableCollapsingToolbar(false);
        float ratio = (100f - 75f) / 100f;
        AppbarHelper.onAppBarScrollPositionChange(getViewBinding().appBarLayout, scrollPosition -> {
            float alpha = 1f - (1f - scrollPosition) / ratio;
            getViewBinding().collapsingContent.container.setAlpha(alpha);
            enableToolbarShadowForLowApiByAppBarScrollPosition(scrollPosition);
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(EmployeesViewModel.class);
        viewModel.getCompanyInfo().observe(this, content -> {
            enableCollapsingToolbar(!content.isEmpty());
            getViewBinding().collapsingContent.title.setText(content.getName());
            TextViewHelper.setTextOrGone(getViewBinding().collapsingContent.description,
                    content.getAge());
            ChipGroupHelper.replaceChipsByList(getViewBinding().collapsingContent.competences,
                    content.getCompetences(), R.attr.CompetentionChipStyle);
        });
        viewModel.getEmployees().observe(this, adapter::submitData);
        viewModel.getErrorScreenState().observe(this, state ->
                getViewBinding().errorScreen.getRoot().setVisibility(convertToVisibility(state)));
        viewModel.getLoadingScreenState().observe(this, state ->
                getViewBinding().loadingScreen.getRoot().setVisibility(convertToVisibility(state)));
        viewModel.getSortButtonState().observe(this, state ->
                getViewBinding().fabSort.setVisibility(convertToVisibility(state)));
        viewModel.getChangeSortStrategyEvent().observe(this, event -> {
            if (!event.contentHasBeenHandled) changeSortStrategy(event.getContentIfNotHandled());
        });
        viewModel.getShowSwipeRefreshEvent().observe(this, event -> {
            if (!event.contentHasBeenHandled)
                getViewBinding().swipeRefresh.setRefreshing(event.getContentIfNotHandled());
        });
        viewModel.getShowMessageEvent().observe(this, event -> {
            if (!event.contentHasBeenHandled)
                Snackbar.make(requireView(), event.getContentIfNotHandled(), Snackbar.LENGTH_SHORT).show();
        });
    }

    private int convertToVisibility(Boolean state) {
        return state ? View.VISIBLE : View.GONE;
    }

    private void enableCollapsingToolbar(boolean enable) {
        getViewBinding().collapsingContent.getRoot().setVisibility(convertToVisibility(enable));
        getViewBinding().collapsingToolbarLayout.setTitleEnabled(enable);
    }

    private void enableToolbarShadowForLowApiByAppBarScrollPosition(Float scrollPosition) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) return;
        int visibility = convertToVisibility((scrollPosition == 0f || scrollPosition.isNaN()));
        getViewBinding().toolbarShadow.setVisibility(visibility);
    }

    private void changeSortStrategy(EmployeesSortStrategy strategy) {
        Bundle args = new Bundle();
        args.putString(getString(R.string.current_sort_strategy_key), strategy.name());
        NavHostFragment.findNavController(this).navigate(R.id.actionToSortDialog, args);
    }

    private void observeOnChangeSortStrategy() {
        String key = getString(R.string.employees_change_sort_strategy_key);
        Objects.requireNonNull(NavHostFragment.findNavController(this)
                .getCurrentBackStackEntry())
                .getSavedStateHandle()
                .getLiveData(key)
                .observe(this, strategy ->
                        viewModel.onReturnChangeSortStrategy((EmployeesSortStrategy) strategy)
                );
    }

    @Override
    public FragmentEmployeesBinding viewBindingInflate(LayoutInflater layoutInflater,
                                                       ViewGroup root, Boolean attachToRoot) {
        return FragmentEmployeesBinding.inflate(layoutInflater, root, attachToRoot);
    }

}
