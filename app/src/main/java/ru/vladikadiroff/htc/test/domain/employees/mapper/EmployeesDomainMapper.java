package ru.vladikadiroff.htc.test.domain.employees.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.scopes.ViewModelScoped;
import ru.vladikadiroff.htc.test.data.models.HtcServiceRequestModel.CompanyRequestModel;
import ru.vladikadiroff.htc.test.data.models.HtcServiceRequestModel.EmployeeRequestModel;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeeDomainModel;
import ru.vladikadiroff.htc.test.domain.employees.models.EmployeesDomainModel;

@ViewModelScoped
public class EmployeesDomainMapper {

    @Inject
    public EmployeesDomainMapper() {
    }

    public EmployeesDomainModel fromRepository(CompanyRequestModel model) {
        return new EmployeesDomainModel(
                (model.name != null) ? model.name : "",
                (model.age != null) ? model.age : "",
                (model.competences != null) ? model.competences : new ArrayList<>(),
                (model.employees != null) ? mapEmployees(model.employees) : new ArrayList<>()
        );
    }

    private List<EmployeeDomainModel> mapEmployees(List<EmployeeRequestModel> employees) {
        List<EmployeeDomainModel> list = new ArrayList<>();
        for (EmployeeRequestModel employee : employees) {
            if (!isEmptyEmployee(employee)) {
                list.add(mapEmployee(employee));
            }
        }
        return list;
    }

    private EmployeeDomainModel mapEmployee(EmployeeRequestModel model) {
        return new EmployeeDomainModel(
                (model.name != null) ? model.name : "",
                (model.phone != null) ? model.phone : "",
                (model.skills != null) ? model.skills : new ArrayList<>()
        );
    }

    private boolean isEmptyEmployee(EmployeeRequestModel model) {
        if (model == null) return true;
        boolean isEmptyName = (model.name == null || model.name.isEmpty());
        boolean isEmptyPhone = (model.phone == null || model.phone.isEmpty());
        boolean isEmptySkills = (model.skills == null || model.skills.isEmpty());
        return (isEmptyName && isEmptyPhone && isEmptySkills);
    }

}
