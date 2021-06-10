package ru.vladikadiroff.htc.test_java.data.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import ru.vladikadiroff.htc.test_java.data.models.HtcServiceRequestModel;

public interface HtcService {
    @GET("/v2/5ddcd3673400005800eae483")
    Single<HtcServiceRequestModel> getContent();
}
