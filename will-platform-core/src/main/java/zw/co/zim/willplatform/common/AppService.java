package zw.co.zim.willplatform.common;

import java.util.List;
import java.util.Optional;

public interface AppService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    T update(Long id, T t);

    void deleteById(Long id);
}
