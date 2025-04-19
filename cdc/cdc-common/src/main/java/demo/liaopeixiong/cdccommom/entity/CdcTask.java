package demo.liaopeixiong.cdccommom.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CdcTask {

    // 任务名
    private String name;

    // 应用名
    private String appId;

    // 源端数据源配置
    private JSONObject sourceDataSource;

    // 目标端数据源配置
    private JSONObject targetDataSource;

    // 任务配置
    private JSONObject config;

    // 部署
    private String deploy;

    // 部署端口
    private String port;
}
