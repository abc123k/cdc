package demo.liaopeixiong.cdcwriter.consume;

import demo.liaopeixiong.cdccommom.consume.MsgConsume;
import demo.liaopeixiong.cdccommom.entity.CdcTask;

public class KafkaConsume implements MsgConsume {

    private CdcTask cdcTask;

    @Override
    public void init(CdcTask cdcTask) {
        this.cdcTask = cdcTask;
    }
}
