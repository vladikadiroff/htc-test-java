package ru.vladikadiroff.htc.test_java;

import org.junit.Test;
import ru.vladikadiroff.htc.test_java.domain.employees.mapper.EmployeesDomainMapper;

public class DomainMapperUnitTest {

    @Test
    public void mapper_DifferentRequests() {
        EmployeesDomainMapper mapper = new EmployeesDomainMapper();
        RequestModelUnitTest requestTest = new RequestModelUnitTest();
        mapper.fromRepository(requestTest.employees_CreateRepositoryRequest());
    }

}
