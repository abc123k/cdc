package demo.liaopeixiong.cdccommom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public abstract class CdcController {

    // 启动任务
    @RequestMapping(path = "/start", method = {RequestMethod.POST})
    public String start() {

        return "ok";
    }

    // 结束任务
    @RequestMapping(path = "/stop", method = {RequestMethod.POST})
    public String stop(){
        return "ok";
    }

    // 查询任务状态
    @RequestMapping(path = "/checkTask", method = {RequestMethod.GET})
    public String checkTask(){
        return "ok";
    }

    @RequestMapping(path = "/taskCount", method = {RequestMethod.GET})
    public String taskCount(){
        return "ok";
    }
}
