package demo.liaopeixiong.cdcwriter.engine;

import demo.liaopeixiong.cdccommom.engine.CdcEngine;
import demo.liaopeixiong.cdccommom.entity.CdcTask;

public class CdcWriterEngine implements CdcEngine {

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

    private class EngineThread implements Runnable {



        @Override
        public void run() {

        }
    }
}
