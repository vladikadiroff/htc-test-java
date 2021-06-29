package ru.vladikadiroff.htc.test.presentation.employees.sort.mapper;

import android.content.res.Resources;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ViewModelScoped;
import ru.vladikadiroff.htc.test.R;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeesSortStrategy;

@ViewModelScoped
public class SortStrategyMapper {

    private final Resources resources;

    @Inject
    public SortStrategyMapper(Resources resources) {
        this.resources = resources;
    }

    public String getStrategyName(EmployeesSortStrategy strategy) {
        int id = 0;
        switch (strategy){
            case SORT_BY_NAME:
                id = R.string.sort_by_name;
                break;
            case SORT_BY_PHONE:
                id = R.string.sort_by_phone;
                break;
            case SORT_BY_COUNT_SKILLS:
                id = R.string.sort_by_count_skills;
        }
        return resources.getString(id);
    }

}
