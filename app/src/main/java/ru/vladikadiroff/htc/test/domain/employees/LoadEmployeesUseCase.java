package ru.vladikadiroff.htc.test.domain.employees;

import javax.inject.Inject;
import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import ru.vladikadiroff.htc.test.data.RepositoryImpl;
import ru.vladikadiroff.htc.test.domain.di.SchedulerIO;
import ru.vladikadiroff.htc.test.domain.di.SchedulerMain;
import ru.vladikadiroff.htc.test.domain.employees.mapper.EmployeesDomainMapper;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeesDomainModel;

@ViewModelScoped
public class LoadEmployeesUseCase {

    private Disposable disposable;
    private OnLoadContentStateChangedListener listener;
    private final Scheduler ioThread;
    private final Scheduler mainThread;
    private final RepositoryImpl repository;
    private final EmployeesDomainMapper mapper;

    @Inject
    LoadEmployeesUseCase(RepositoryImpl repository, EmployeesDomainMapper mapper,
                         @SchedulerIO Scheduler ioThread, @SchedulerMain Scheduler mainThread) {
        this.repository = repository;
        this.mapper = mapper;
        this.ioThread = ioThread;
        this.mainThread = mainThread;
    }

    public void loadContent() {
        clear();
        listener.onLoading();
        disposable = repository.getContent()
                .subscribeOn(ioThread)
                .observeOn(mainThread)
                .map(mapper::fromRepository)
                .subscribe(listener::onSuccess, listener::onError);
    }

    public void observeOnLoadContentStateChanged(OnLoadContentStateChangedListener listener) {
        this.listener = listener;
    }

    public interface OnLoadContentStateChangedListener {
        void onSuccess(EmployeesDomainModel content);

        void onError(Throwable error);

        void onLoading();
    }

    public void clear() {
        if (disposable != null) disposable.dispose();
    }

}
