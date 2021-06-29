package ru.vladikadiroff.htc.test.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import ru.vladikadiroff.htc.test.data.api.HtcService;
import ru.vladikadiroff.htc.test.data.models.HtcServiceRequestModel.CompanyRequestModel;

@Singleton
public class RepositoryImpl implements Repository {

    private final HtcService service;

    @Inject
    RepositoryImpl(HtcService service) {
        this.service = service;
    }

    @Override
    public Single<CompanyRequestModel> getContent() {
        return service.getContent().map(request -> request.company);
    }

}
