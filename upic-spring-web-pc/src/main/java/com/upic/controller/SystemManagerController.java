package com.upic.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/systemManager")
public class SystemManagerController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(SystemManagerController.class);
}
