package zw.co.zim.willplatform.common;

import zw.co.zim.willplatform.messages.response.basic.ApiResponse;

public interface ProcessorService<T> {
    ApiResponse<T> findAll();

    ApiResponse<T> findAll(Integer pageNo, Integer pageSize);

    ApiResponse<T> findById(Long id);

    ApiResponse<T> save(T t);

    ApiResponse<T> update(Long id, T t);

    ApiResponse<T> deleteById(Long id);
}
