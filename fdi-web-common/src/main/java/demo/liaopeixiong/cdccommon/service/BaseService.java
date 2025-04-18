package demo.liaopeixiong.cdccommon.service;

import demo.liaopeixiong.cdccommon.dao.BaseRepository;
import demo.liaopeixiong.cdccommon.entity.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class BaseService<T extends BaseEntity, R extends BaseRepository<T>> {

    protected final R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }

    // 保存或更新
    public T save(T entity) {
        return repository.save(entity);
    }

    // 根据 ID 查询
    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The data does not exist."));
    }

    // 分页查询
    public Page<T> findAllByPage(int page, int size, Sort sort) {
        return repository.findAllByPage(page, size, sort);
    }

    // 动态条件查询
    public List<T> findAllBySpecification(Specification<T> spec) {
        return repository.findAllBySpecification(spec);
    }

    // 删除
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // 创建动态查询条件的工具方法
    public static <T> Specification<T> buildSpecification(PredicateBuilder<T> builder) {
        return builder.build();
    }

    // 动态查询条件构建器（Lambda 风格）
    @FunctionalInterface
    public interface PredicateBuilder<T> {
        Specification<T> build();
    }
}
