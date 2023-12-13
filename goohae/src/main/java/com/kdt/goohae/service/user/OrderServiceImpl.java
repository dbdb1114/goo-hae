package com.kdt.goohae.service.user;

import com.kdt.goohae.domain.user.OrderDetailVO;
import com.kdt.goohae.domain.user.OrderInfoVO;
import com.kdt.goohae.domain.user.PaymentVO;
import com.kdt.goohae.mapper.user.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    OrderMapper orderMapper;
    public OrderServiceImpl(OrderMapper orderMapper) { this.orderMapper = orderMapper; }

    @Override
    public int insert(OrderInfoVO vo){ return orderMapper.insert(vo); }
    @Override
    public int delete(OrderInfoVO vo){ return orderMapper.delete(vo); }
    @Override
    public OrderInfoVO selectOne(OrderInfoVO vo) { return orderMapper.selectOne(vo); }
    @Override
    public ArrayList<OrderInfoVO> selectList(String loginId) { return orderMapper.selectList(loginId); }


    @Override
    public int insertOrder(OrderInfoVO vo) {
        return orderMapper.insertOrder(vo);
    }

    @Override
    public OrderInfoVO getOrderInfo(OrderInfoVO vo) {
        return orderMapper.getOrderInfo(vo);
    }

    @Override
    public int insertDetail(OrderDetailVO vo) {
        return orderMapper.insertDetail(vo);
    }

    @Override
    public List<OrderDetailVO> getOrderDetail(OrderInfoVO vo) {
        return orderMapper.getOrderDetail(vo);
    }

    @Override
    public int insertPay(PaymentVO vo) {
        return orderMapper.insertPay(vo);
    }

    @Override
    public PaymentVO getPay(PaymentVO vo) {
        return orderMapper.getPay(vo);
    }

    @Override
    public OrderInfoVO getOrder(PaymentVO vo) {
        return orderMapper.getOrder(vo);
    }
}
