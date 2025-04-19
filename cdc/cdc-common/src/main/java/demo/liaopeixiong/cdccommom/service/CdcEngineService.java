package demo.liaopeixiong.cdccommom.service;

import org.springframework.stereotype.Service;

@Service
public interface CdcEngineService {

    public String addTask(String task);

    public String removeTask(String task);

    public String taskCount();

    public String checkTask();
}
