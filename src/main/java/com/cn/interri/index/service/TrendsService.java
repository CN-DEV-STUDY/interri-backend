package com.cn.interri.index.service;

import com.cn.interri.index.dto.IndexDto;

import java.io.IOException;

public interface TrendsService {

    IndexDto getIndex() throws IOException;
}
