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

    @Provides
    @Singleton
    @SchedulerIO
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @SchedulerMain
    Scheduler provideSchedulerMain() {
        return AndroidSchedulers.mainThread();
    }

}
