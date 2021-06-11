package ru.vladikadiroff.htc.test.presentation.employees.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ru.vladikadiroff.htc.test.domain.employees.LoadEmployeesUseCase;
import ru.vladikadiroff.htc.test.domain.employees.LoadEmployeesUseCase.OnLoadContentStateChangedListener;
import ru.vladikadiroff.htc.test.domain.employees.SortEmployeesUseCase;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeeDomainModel;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeesDomainModel;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeesSortStrategy;
import ru.vladikadiroff.htc.test.presentation.core.Event;
import ru.vladikadiroff.htc.test.presentation.employees.home.mapper.EmployeesMapper;
import ru.vladikadiroff.htc.test.presentation.employees.home.models.CompanyInfo;
import ru.vladikadiroff.htc.test.presentation.employees.home.models.EmployeeAdapterModel;
import ru.vladikadiroff.htc.test.presentation.employees.sort.mapper.SortStrategyMapper;

@HiltViewModel
public class EmployeesViewModel extends ViewModel implements OnLoadContentStateChangedListener {

    private final EmployeesMapper mapper;
    private final SortStrategyMapper strategyMapper;
    private final LoadEmployeesUseCase loadUseCase;
    private final SortEmployeesUseCase sortUseCase;
    private EmployeesSortStrategy currentStrategy = EmployeesSortStrategy.SORT_BY_NAME;

    // ViewState
    private final MutableLiveData<Boolean> sortButton = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> errorScreen = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> loadingScreen = new MutableLiveData<>(true);
    private final MutableLiveData<CompanyInfo> companyInfo = new MutableLiveData<>();
    private final MutableLiveData<List<EmployeeAdapterModel>> employees = new MutableLiveData<>();

    // ViewAction
    private final MutableLiveData<Event<String>> showMessageEvent = new MutableLiveData<>();
    private final MutableLiveData<Event<Boolean>> swipeRefreshEvent = new MutableLiveData<>();
    private final MutableLiveData<Event<EmployeesSortStrategy>> changeSortStrategyEvent = new MutableLiveData<>();

    @Inject
    EmployeesViewModel(LoadEmployeesUseCase loadUseCase, SortEmployeesUseCase sortUseCase,
                       SortStrategyMapper strategyMapper, EmployeesMapper mapper) {
        this.mapper = mapper;
        this.sortUseCase = sortUseCase;
        this.loadUseCase = loadUseCase;
        this.strategyMapper = strategyMapper;
        sortUseCase.setSortStrategy(currentStrategy);
        loadUseCase.observeOnLoadContentStateChanged(this);
        loadUseCase.loadContent();
    }

    public void onReturnChangeSortStrategy(EmployeesSortStrategy strategy) {
        if (currentStrategy == strategy) return;
        currentStrategy = strategy;
        sortUseCase.setSortStrategy(currentStrategy);
        if (employees.getValue() == null) return;
        List<EmployeeDomainModel> sortList =
                sortUseCase.sort(mapper.mapEmployeesToDomain(employees.getValue()));
        employees.setValue(mapper.mapEmployeesFromDomain(sortList));
        showMessageEvent.setValue(new Event<>(strategyMapper.getStrategyName(strategy)));
    }

    @Override
    public void onSuccess(EmployeesDomainModel content) {
        List<EmployeeDomainModel> sortEmployees = sortUseCase.sort(content.getEmployees());
        employees.setValue(mapper.mapEmployeesFromDomain(sortEmployees));
        companyInfo.setValue(mapper.mapToCompanyInfo(content));
        swipeRefreshEvent.setValue(new Event<>(false));
        loadingScreen.setValue(false);
        sortButton.setValue(!content.getEmployees().isEmpty());
    }

    @Override
    public void onError(Throwable error) {
        swipeRefreshEvent.setValue(new Event<>(false));
        loadingScreen.setValue(false);
        if (companyInfo.getValue() == null) errorScreen.setValue(true);
        showMessageEvent.setValue(new Event<>(error.toString()));
    }

    @Override
    public void onLoading() {
        if (errorScreen.getValue() == null || errorScreen.getValue()){
            loadingScreen.setValue(true);
            swipeRefreshEvent.setValue(new Event<>(false));
        }
        errorScreen.setValue(false);
    }

    public void changeSortStrategy() {
        changeSortStrategyEvent.setValue(new Event<>(currentStrategy));
    }

    public void refresh() {
        loadUseCase.loadContent();
    }

    public void onClickItem(String name) {
        showMessageEvent.setValue(new Event<>(name));
    }

    public LiveData<CompanyInfo> getCompanyInfo() {
        return companyInfo;
    }

    public LiveData<Boolean> getSortButtonState() {
        return sortButton;
    }

    public LiveData<Boolean> getErrorScreenState() {
        return errorScreen;
    }

    public LiveData<Boolean> getLoadingScreenState() {
        return loadingScreen;
    }

    public LiveData<Event<String>> getShowMessageEvent() {
        return showMessageEvent;
    }

    public LiveData<Event<Boolean>> getShowSwipeRefreshEvent() {
        return swipeRefreshEvent;
    }

    public LiveData<Event<EmployeesSortStrategy>> getChangeSortStrategyEvent() {
        return changeSortStrategyEvent;
    }

    public LiveData<List<EmployeeAdapterModel>> getEmployees() {
        return employees;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        loadUseCase.clear();
    }

}
