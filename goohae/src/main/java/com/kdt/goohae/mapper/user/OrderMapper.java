package com.kdt.goohae.mapper.user;

import com.kdt.goohae.domain.user.OrderDetailVO;
import com.kdt.goohae.domain.user.OrderInfoVO;
import com.kdt.goohae.domain.user.PaymentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrderMapper {

    int insert(OrderInfoVO vo);
    int delete(OrderInfoVO vo);
    OrderInfoVO selectOne(OrderInfoVO vo);
    ArrayList<OrderInfoVO> selectList(String loginId);


    /* 주문 정보 테이블 */
    int insertOrder(OrderInfoVO vo);

    /* 주문 정보 가져오기 ( 주문 직후 ) */
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
