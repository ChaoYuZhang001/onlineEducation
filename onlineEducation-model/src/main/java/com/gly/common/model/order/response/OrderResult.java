package com.gly.common.model.order.response;

import com.gly.common.model.response.ResponseResult;
import com.gly.common.model.response.ResultCode;
import com.gly.common.model.order.Orders;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderResult extends ResponseResult {
    private Orders Orders;
    public OrderResult(ResultCode resultCode, Orders Orders) {
        super(resultCode);
        this.Orders = Orders;
    }


}
