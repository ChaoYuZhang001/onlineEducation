package com.gly.common.model.order.response;

import com.gly.common.model.response.ResponseResult;
import com.gly.common.model.response.ResultCode;
import com.gly.common.model.order.OrdersPay;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PayOrderResult extends ResponseResult {
    public PayOrderResult(ResultCode resultCode) {
        super(resultCode);
    }
    public PayOrderResult(ResultCode resultCode,  OrdersPay ordersPay) {
        super(resultCode);
        this.ordersPay = ordersPay;
    }
    private OrdersPay ordersPay;
    private String orderNumber;

    //当tradeState为NOTPAY（未支付）时显示支付二维码
    private String codeUrl;
    private Float money;


}
