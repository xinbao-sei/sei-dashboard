package com.changhong.sei.dashboard.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.context.SessionUser;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.util.JsonUtils;
import com.changhong.sei.dashboard.api.HelloApi;
import com.changhong.sei.dashboard.service.HelloService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <strong>实现功能:</strong>
 * <p>调试你好的API服务实现</p>
 *
 * @author 王锦光 wangj
 * @version 1.0.1 2019-12-16 17:22
 */
@RestController
@RefreshScope
@Api(value = "HelloApi", tags = "调试你好的API服务")
@RequestMapping(path = "dashboardHello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HelloController implements HelloApi {
    @Autowired
    private HelloService service;

    @Value("${dashboard.test-key}")
    private String testKey;

    /**
     * 你好
     * @param name 姓名
     * @return 返回句子
     */
    public ResultData<String> sayHello(String name){
        try {
            SessionUser sessionUser = ContextUtil.getSessionUser();
            System.out.println(JsonUtils.toJson(sessionUser));
            String data = service.sayHello(name, testKey);
            return ResultData.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail("你好说失败了！"+e.getMessage());
        }
    }
}
