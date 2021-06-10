package ru.vladikadiroff.htc.test_java.presentation.employees.sort;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ru.vladikadiroff.htc.test_java.domain.employees.models.EmployeesSortStrategy;
import ru.vladikadiroff.htc.test_java.presentation.employees.sort.adapter.SortStrategyModel;
import ru.vladikadiroff.htc.test_java.presentation.employees.sort.mapper.SortStrategyMapper;

@HiltViewModel
public class ChangeSortStrategyViewModel extends ViewModel {

    private final SortStrategyMapper mapper;
    private final MutableLiveData<List<SortStrategyModel>> strategies = new MutableLiveData<>();

    @Inject
    public ChangeSortStrategyViewModel(SortStrategyMapper mapper) {
        this.mapper = mapper;
    }

    public void initViewState(EmployeesSortStrategy currentStrategy) {
        strategies.setValue(createStrategiesList(currentStrategy));
    }

    private List<SortStrategyModel> createStrategiesList(EmployeesSortStrategy currentStrategy) {
        List<SortStrategyModel> list = new ArrayList<>();
        for (EmployeesSortStrategy strategy : EmployeesSortStrategy.values()) {
            SortStrategyModel item = new SortStrategyModel(
                    mapper.getStrategyName(strategy),
                    currentStrategy == strategy,
                    strategy);
            list.add(item);
        }
        return list;
    }

    public LiveData<List<SortStrategyModel>> getStrategies() {
        return strategies;
    }

}
