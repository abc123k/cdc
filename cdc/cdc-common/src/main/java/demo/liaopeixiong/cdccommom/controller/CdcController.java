package demo.liaopeixiong.cdccommom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cdc")
public abstract class CdcController {

    // 启动任务
    public String start(){
        return "ok";
    }

    // 结束任务
    public String stop(){
        return "ok";
    }

    // 查询任务状态
    public String checkTask(){
        return "ok";
    }

    public String taskCount(){
        return "ok";
    }
}
