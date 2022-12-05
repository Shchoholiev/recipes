package com.shchoholiev.recipes.domain.common;

import java.util.List;

public class PaginationWrapper<T> {

    private List<T> items;

    private int pagesCount;

    private int totalCount;

    public PaginationWrapper() { }

    public PaginationWrapper(List<T> items, int pagesCount) {
        this.items = items;
        this.pagesCount = pagesCount;
    }

    public PaginationWrapper(List<T> items, int pagesCount, int totalCount) {
        this.items = items;
        this.pagesCount = pagesCount;
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
