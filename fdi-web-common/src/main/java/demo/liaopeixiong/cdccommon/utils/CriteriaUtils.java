package demo.liaopeixiong.cdccommon.utils;

import demo.liaopeixiong.cdccommon.exception.InvalidAttributeException;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.Specification;

public class CriteriaUtils {

    public static <T> Path<?> resolvePath(Root<T> root, String attributePath) {
        try {
            String[] attributes = attributePath.split("\\.");
            Path<?> path = root.get(attributes[0]);
            for (int i = 1; i < attributes.length; i++) {
                path = path.get(attributes[i]);
            }
            return path;
        } catch (IllegalArgumentException exception) {
            throw new InvalidAttributeException("属性路径错误: " + attributePath);
        }
    }

    /**
     * 动态控制关联加载策略（Fetch Mode）
     *
     * @param attributePath 关联属性路径（如 "role" 或 "orders.items"）
     * @param fetchMode     加载模式（JOIN、SELECT、SUBSELECT）
     */
    public static <T> Specification<T> fetch(String attributePath, FetchMode fetchMode) {
        return (root, query, cb) -> {
            String[] attributes = attributePath.split("\\.");
            Join<?, ?> join = null;
            for (String attr : attributes) {
                // 根据 FetchMode 设置加载策略
                Fetch<?, ?> fetch = root.fetch(attr, convertToJpaJoinType(fetchMode));
                // 转换为 Join 以便后续条件使用（如 ON 子句）
                join = (Join<?, ?>) fetch;
            }
            query.distinct(true); // 解决重复数据问题
            return null; // Fetch 不参与 WHERE 条件
        };
    }

    /**
     * 将 FetchMode 转换为 JPA 的 JoinType
     */
    private static JoinType convertToJpaJoinType(FetchMode fetchMode) {
        switch (fetchMode) {
            case JOIN:
                return JoinType.INNER; // 或 LEFT 根据需求调整
            case SELECT:
            case SUBSELECT:
                return JoinType.LEFT; // 默认使用 LEFT JOIN
            default:
                return JoinType.LEFT;
        }
    }

    /**
     * 等于（支持嵌套属性）
     */
    public static <T> Specification<T> eq(String attributePath, Object value) {
        return (root, query, cb) -> {
            Path<?> path = resolvePath(root, attributePath);
            return cb.equal(path, value);
        };
    }

    /**
     * 模糊匹配（支持嵌套属性）
     */
    public static <T> Specification<T> like(String attributePath, String value) {
        return (root, query, cb) -> {
            Path<String> path = (Path<String>) resolvePath(root, attributePath);
            return cb.like(path, "%" + value + "%");
        };
    }

    /**
     * 范围查询（支持嵌套属性）
     */
    public static <T> Specification<T> between(String attributePath, Comparable start, Comparable end) {
        return (root, query, cb) -> {
            Path<Comparable> path = (Path<Comparable>) resolvePath(root, attributePath);
            return cb.between(path, start, end);
        };
    }

    /**
     * 大于等于（Greater Than or Equal）
     */
//    public static <T> Specification<T> ge(String attributePath, Comparable<?> value) {
//        return (root, query, cb) ->
//                cb.greaterThanOrEqualTo(resolvePath(root, attributePath), value);
//    }

    /**
     * 小于等于（Less Than or Equal）
     */
//    public static <T> Specification<T> le(String attributePath, Comparable<?> value) {
//        return (root, query, cb) ->
//                cb.lessThanOrEqualTo(resolvePath(root, attributePath), value);
//    }

    /**
     * 不为空（isNotNull）
     */
    public static <T> Specification<T> isNotNull(String attributePath) {
        return (root, query, cb) ->
                cb.isNotNull(resolvePath(root, attributePath));
    }

    /**
     * 模糊匹配（忽略大小写）
     */
//    public static <T> Specification<T> ilike(String attributePath, String value) {
//        return (root, query, cb) ->
//                cb.like(cb.lower(resolvePath(root, attributePath)), "%" + value.toLowerCase() + "%");
//    }

}