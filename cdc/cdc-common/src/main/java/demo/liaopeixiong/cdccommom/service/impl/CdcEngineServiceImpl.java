package demo.liaopeixiong.cdccommom.service.impl;

import demo.liaopeixiong.cdccommom.entity.CdcTask;
import demo.liaopeixiong.cdccommom.service.CdcEngineService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CdcEngineServiceImpl implements CdcEngineService {

    private static ConcurrentHashMap<String, CdcTask> taskMap = new ConcurrentHashMap();

    @Override
    public String addTask(String task) {
        return "";
    }

    @Override
    public String removeTask(String task) {
        return "";
    }

    @Override
    public String taskCount() {
        return "";
    }

    @Override
    public String checkTask() {
        return "";
    }
}
