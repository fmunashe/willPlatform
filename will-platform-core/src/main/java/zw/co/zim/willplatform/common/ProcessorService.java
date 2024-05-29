package zw.co.zim.willplatform.common;

import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface ProcessorService<T, S> {

    ApiResponse<T> findAll(Integer pageNo, Integer pageSize);

    ApiResponse<T> findById(Long id);

    ApiResponse<T> save(S s);

    ApiResponse<T> update(Long id, T t);

    ApiResponse<T> deleteById(Long id);
}
