package demo.liaopeixiong.cdccommon.entity;

import com.alibaba.fastjson2.JSONObject;
import demo.liaopeixiong.cdccommon.exception.CdcNotSupportdException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "data_source",schema = "demo")
@Comment("数据源表")
public class DataSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("数据源名字")
    @Column(unique = true,length = 255, name = "name")
    private String name;

    @Comment("数据源所属应用")
    @Column(columnDefinition = "varchar",length = 255, name = "app_id")
    private String appId;

    @Comment("数据源配置")
    @Column(columnDefinition = "json",name = "data_source_config")
    private JSONObject dataSourceConfig;

    // TODO 这个字段先留着看看有没有用
    @Transient
    private JSONObject dataSourceConfigJson;

    @Comment("最后修改人")
    @Column(columnDefinition = "varchar",length = 255,name = "last_modifier")
    private String lastModifier;

    @Comment("创建人")
    @Column(columnDefinition = "varchar",length = 255,name = "creator")
    private String creator;

    @Comment("创建时间")
    @Column(columnDefinition = "timestamp",name = "create_time")
    private Timestamp createTime;

    @Comment("更新时间")
    @Column(columnDefinition = "timestamp",name = "update_time")
    private Timestamp updateTime;

    public void setDataSourceConfigJson(Object dataSourceConfigJson) {
        if(null == dataSourceConfigJson){
            this.dataSourceConfigJson = new JSONObject();
            return;
        }
        if(dataSourceConfigJson instanceof String){
            this.dataSourceConfigJson = JSONObject.parseObject((String) dataSourceConfigJson);
        }else if(dataSourceConfigJson instanceof JSONObject){
            this.dataSourceConfigJson = (JSONObject) dataSourceConfigJson;
        }else {
            throw new CdcNotSupportdException("Converting this type to json is not supported!");
        }
    }

    public JSONObject getDataSourceConfigJson(){
        if(null == dataSourceConfigJson){
            setDataSourceConfigJson(this.dataSourceConfig);
        }
        return this.dataSourceConfigJson;
    }
}
