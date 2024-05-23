package zw.co.zim.willplatform.common;

import zw.co.zim.willplatform.model.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface AppService<T extends BaseEntity> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    T update(Long id, T t);

    void deleteById(Long id);
}
