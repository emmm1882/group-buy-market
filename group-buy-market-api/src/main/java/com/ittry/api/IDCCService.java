package com.ittry.api;

import com.ittry.api.response.Response;

public interface IDCCService {

    Response<Boolean> updateConfig(String key, String value);
}
