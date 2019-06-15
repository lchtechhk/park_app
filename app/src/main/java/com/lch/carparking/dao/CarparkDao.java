package com.lch.carparking.dao;

import com.lch.carparking.network.ResponseModel;

import java.util.List;

public interface CarparkDao<T> {
    List<T> findAll();
    List<T> findByUserId(String json);
    ResponseModel request_holding(String json);
    ResponseModel p_a(String json);
    ResponseModel request_payment(String json);
    ResponseModel request_arrival(String json);
    ResponseModel request_leave(String json);
}
