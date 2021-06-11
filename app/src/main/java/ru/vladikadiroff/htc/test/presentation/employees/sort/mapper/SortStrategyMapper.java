package ru.vladikadiroff.htc.test.presentation.employees.sort.mapper;

import android.content.Context;
import android.content.res.Resources;
import javax.inject.Inject;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ViewModelScoped;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeesSortStrategy;

@ViewModelScoped
public class SortStrategyMapper {

    private final String packageName;
    private final Resources resources;

    @Inject
    public SortStrategyMapper(@ApplicationContext Context context, Resources resources) {
        this.resources = resources;
        packageName = context.getPackageName();
    }

    public String getStrategyName(EmployeesSortStrategy strategy) {
        int id = resources.getIdentifier(strategy.name().toLowerCase(), "string", packageName);
        return resources.getString(id);
    }

}
