package demo.liaopeixiong.cdccommon.service;

import demo.liaopeixiong.cdccommon.dao.DataSourceRepository;
import demo.liaopeixiong.cdccommon.entity.DataSource;
import org.springframework.stereotype.Service;

@Service
public class DataSourceService extends BaseService<DataSource, DataSourceRepository>{

    public DataSourceService(DataSourceRepository repository) {
        super(repository);
    }

}
