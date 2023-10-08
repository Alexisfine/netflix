package com.alex.api;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * Convert Spring Data's Page Info
     * @param page
     * @return
     * @param <T>
     */

    public static <T> CommonPage<T> restPage(Page<T> page) {
        CommonPage<T> res = new CommonPage<>();
        res.setPageNum(page.getNumber());
        res.setPageSize(page.getSize());
        res.setTotalPage(page.getTotalPages());
        res.setTotal(page.getTotalElements());
        res.setList(page.getContent());
        return res;
    }
}
