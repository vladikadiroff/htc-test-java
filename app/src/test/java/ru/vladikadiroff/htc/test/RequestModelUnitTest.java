package ru.vladikadiroff.htc.test;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ru.vladikadiroff.htc.test.data.models.HtcServiceRequestModel;
import ru.vladikadiroff.htc.test.data.models.HtcServiceRequestModel.CompanyRequestModel;
import ru.vladikadiroff.htc.test.data.models.HtcServiceRequestModel.EmployeeRequestModel;
import static org.junit.Assert.assertEquals;

public class RequestModelUnitTest {

    private final String stringContent = "test";
    private final String stringEmpty = "";

    @Test
    public void employess_IsCorrect() {
        employees_CreateRepositoryRequest();
    }

    public CompanyRequestModel employees_CreateRepositoryRequest() {
        CompanyRequestModel test = new CompanyRequestModel();
        test.name = null;
        test.employees = repository_CreateListEmployees();
        test.competences = null;
        test.age = null;
        return test;
    }

    public List<EmployeeRequestModel> repository_CreateListEmployees(){
        return Arrays.asList(
                employee_CreateNullModel(),
                employee_CreateEmptyModel(),
                employee_CreateModelWithNullFields(),
                employee_CreateEmptyModelWithEmptySkillList(),
                employee_CreateModelWithEmptyName(),
                employee_CreateModelWithEmptyPhone(),
                employee_CreateNotEmptyModel()
        );
    }

    private EmployeeRequestModel employee_CreateNullModel() {
        return null;
    }

    private HtcServiceRequestModel.EmployeeRequestModel employee_CreateEmptyModel() {
        EmployeeRequestModel test = new EmployeeRequestModel();
        test.name = stringEmpty;
        test.phone = stringEmpty;
        test.skills = new ArrayList<>();
        return test;
    }

    private EmployeeRequestModel employee_CreateModelWithNullFields() {
        EmployeeRequestModel test = new EmployeeRequestModel();
        test.name = null;
        test.phone = null;
        test.skills = null;
        return test;
    }

    private EmployeeRequestModel employee_CreateEmptyModelWithEmptySkillList() {
        EmployeeRequestModel test = new EmployeeRequestModel();
        test.name = null;
        test.phone = null;
        test.skills = new ArrayList<>();
        return test;
    }

    private EmployeeRequestModel employee_CreateModelWithEmptyName() {
        EmployeeRequestModel test = new EmployeeRequestModel();
        test.name = null;
        test.phone = stringContent;
        test.skills = new ArrayList<>();
        return test;
    }

    private EmployeeRequestModel employee_CreateModelWithEmptyPhone() {
        EmployeeRequestModel test = new EmployeeRequestModel();
        test.name = null;
        test.phone = stringContent;
        test.skills = new ArrayList<>();
        return test;
    }

    private EmployeeRequestModel employee_CreateNotEmptyModel() {
        EmployeeRequestModel test = new EmployeeRequestModel();
        test.name = stringContent;
        test.phone = stringContent;
        test.skills = Arrays.asList(stringContent, stringContent);
        return test;
    }

}
