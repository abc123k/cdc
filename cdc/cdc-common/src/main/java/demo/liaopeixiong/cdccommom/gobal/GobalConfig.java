package demo.liaopeixiong.cdccommom.gobal;

import demo.liaopeixiong.cdccommom.task.CdcTask;

import java.util.concurrent.ConcurrentHashMap;

public final class GobalConfig {

    public static final ConcurrentHashMap<String, CdcTask> taskConfigMap = new ConcurrentHashMap();


}
