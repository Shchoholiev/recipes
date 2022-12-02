package com.shchoholiev.recipes.domain.common;

import java.util.List;

public class PaginationWrapper<T> {

    private List<T> items;

    private int totalCount;

    public PaginationWrapper() { }

    public PaginationWrapper(List<T> items, int totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
