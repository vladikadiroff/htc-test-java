package ru.vladikadiroff.htc.test_java.data;

import io.reactivex.Single;
import ru.vladikadiroff.htc.test_java.data.models.HtcServiceRequestModel;

public interface Repository {
    Single<HtcServiceRequestModel.CompanyRequestModel> getContent();
}
