package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.user.OrderDetailVO;
import com.kdt.goohae.domain.user.OrderInfoVO;
import com.kdt.goohae.domain.user.PaymentVO;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {

    public int insert(OrderInfoVO vo);
    public int delete(OrderInfoVO vo);
    OrderInfoVO selectOne(OrderInfoVO vo);
    ArrayList<OrderInfoVO> selectList(String loginId);


    /* 주문 정보 테이블 */
    int insertOrder(OrderInfoVO vo);

    /* 주문 정보 가져오기 */
    OrderInfoVO getOrderInfo(OrderInfoVO vo);

    /* 주문 상세 테이블 */
    int insertDetail(OrderDetailVO vo);

    /* 주문 상세 가져오기 */
    List<OrderDetailVO> getOrderDetail(OrderInfoVO vo);

    /* 결제 */
    int insertPay(PaymentVO vo);

    /* 결제 정보 가져오기 */
    PaymentVO getPay(PaymentVO vo);

    /* 주문 정보 가져오기 */
    OrderInfoVO getOrder(PaymentVO vo);
}
