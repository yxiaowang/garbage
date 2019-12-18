package com.njupt.garbage.common.pojo;

import java.util.List;

public class EUDataGridResult {
    private Long total;
    //使用通配符?，list里面可以是任何类型
    private List<?> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
