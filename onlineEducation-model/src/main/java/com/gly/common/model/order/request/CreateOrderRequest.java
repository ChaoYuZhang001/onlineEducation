package com.gly.common.model.order.request;


import com.gly.common.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateOrderRequest extends RequestData {

    String courseId;

}
