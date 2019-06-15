package com.lch.carparking.dao;

import com.lch.carparking.network.ResponseModel;

public interface UsersDao<T> {
    ResponseModel save(String json);
    ResponseModel login(String json);
    ResponseModel register_login(String json);
}
