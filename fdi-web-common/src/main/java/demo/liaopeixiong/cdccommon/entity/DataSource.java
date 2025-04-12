package demo.liaopeixiong.cdccommon.entity;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
public class DataSource implements Serializable {

    private static final long serialVersionUID = 1L;

    // 数据源名字
    private String name;

    // 数据源所属appid
    private String appId;

    // 数据源配置
    private JSONObject dataSourceConfig;

    // 创建时间
    private Timestamp createTime;

    //更新时间
    private Timestamp updateTime;
}
