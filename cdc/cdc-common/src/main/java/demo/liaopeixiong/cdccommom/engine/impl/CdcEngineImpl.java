package demo.liaopeixiong.cdccommom.engine.impl;

import demo.liaopeixiong.cdccommom.engine.CdcEngine;
import demo.liaopeixiong.cdccommom.task.CdcTask;

import java.util.concurrent.ConcurrentHashMap;

public class CdcEngineImpl implements CdcEngine {

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
