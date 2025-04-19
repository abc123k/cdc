package demo.liaopeixiong.cdccommon.dao;

import demo.liaopeixiong.cdccommon.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean // 标记为不生成实例
public interface BaseRepository<T extends BaseEntity>
        extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    /**
     * 分页查询（带排序）
     */
    default Page<T> findAllByPage(int page, int size, Sort sort) {
        return findAll(PageRequest.of(page, size, sort));
    }

    /**
     * 动态条件查询（使用 Specification）
     */
    default List<T> findAllBySpecification(Specification<T> spec) {
        return findAll(spec);
    }
}