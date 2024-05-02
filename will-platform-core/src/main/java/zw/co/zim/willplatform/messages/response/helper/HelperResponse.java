package zw.co.zim.willplatform.messages.response.helper;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HelperResponse {

    public static <S, T> PagedResponse<T> buildResponse(Page<S> page, Function<S, T> mapper) {
        PagedResponse<T> response = new PagedResponse<>();
        List<T> content = HelperResponse.buildContent(page, mapper);
        response.setContent(content);
        response.setPageNo(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setFirst(page.isFirst());
        response.setLast(page.isLast());
        response.setNext(page.hasNext());
        response.setPrevious(page.hasPrevious());
        return response;
    }

    public static <S, T> List<T> buildContent(Page<S> page, Function<S, T> mapper) {
        return page.getContent()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    public static <S, T> List<T> mappedDtoList(List<S> list, Function<S, T> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }
}
