package demo.liaopeixiong.cdcwriter.consume;

import demo.liaopeixiong.cdccommom.consume.MsgConsume;
import demo.liaopeixiong.cdccommom.entity.CdcTask;

public class RocketMqConsume implements MsgConsume {

    private CdcTask cdcTask;

    @Override
    public void init(CdcTask cdcTask) {

    }
}
