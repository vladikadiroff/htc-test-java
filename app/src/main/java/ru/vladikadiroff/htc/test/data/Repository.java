package ru.vladikadiroff.htc.test.data;

import io.reactivex.Single;
import ru.vladikadiroff.htc.test.data.models.HtcServiceRequestModel;

public interface Repository {
    Single<HtcServiceRequestModel.CompanyRequestModel> getContent();
}
