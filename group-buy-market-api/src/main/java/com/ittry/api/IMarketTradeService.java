package com.ittry.api;

import com.ittry.api.dto.LockMarketPayOrderRequestDTO;
import com.ittry.api.dto.LockMarketPayOrderResponseDTO;
import com.ittry.api.response.Response;

public interface IMarketTradeService {

    Response <LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO);
}
