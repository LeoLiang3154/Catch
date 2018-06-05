package com.mad.jiajianliang.service;

import com.mad.jiajianliang.data.Channel;

public interface WeatherServiceCallBack {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);


}
