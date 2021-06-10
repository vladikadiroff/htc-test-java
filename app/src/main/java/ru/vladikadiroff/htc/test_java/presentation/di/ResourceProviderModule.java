package ru.vladikadiroff.htc.test_java.presentation.di;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ResourceProviderModule {

    @Provides
    @Singleton
    Resources provideResources(@ApplicationContext Context context) {
        return context.getResources();
    }

}
