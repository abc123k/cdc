package demo.liaopeixiong.cdccommom.engine.impl;

import demo.liaopeixiong.cdccommom.engine.CdcEngine;
import demo.liaopeixiong.cdccommom.entity.CdcTask;

public class CdcEngineImpl implements CdcEngine {

    private CdcTask cdcTask;

    @Override
    public void init(CdcTask task) {
        this.cdcTask = task;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
