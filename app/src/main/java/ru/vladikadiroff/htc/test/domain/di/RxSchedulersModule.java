package ru.vladikadiroff.htc.test.domain.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
@InstallIn(SingletonComponent.class)
public class RxSchedulersModule {

    private final Scheduler ioScheduler = Schedulers.io();
    private final Scheduler mainScheduler = AndroidSchedulers.mainThread();

    @Provides
    @Singleton
    @SchedulerIO
    Scheduler provideSchedulerIO() {
        return ioScheduler;
    }

    @Provides
    @Singleton
    @SchedulerMain
    Scheduler provideSchedulerMain() {
        return mainScheduler;
    }

}
