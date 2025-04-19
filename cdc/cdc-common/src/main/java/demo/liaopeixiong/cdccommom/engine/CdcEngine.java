package demo.liaopeixiong.cdccommom.engine;

import demo.liaopeixiong.cdccommom.entity.CdcTask;

public interface CdcEngine {

    public void init(CdcTask task);

    public void start();

    public void stop();

}
