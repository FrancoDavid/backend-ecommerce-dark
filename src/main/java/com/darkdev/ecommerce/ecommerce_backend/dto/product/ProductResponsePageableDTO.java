package com.darkdev.ecommerce.ecommerce_backend.dto.product;

import java.util.List;

public class ProductResponsePageableDTO {
    private Integer page;
    private Integer size;
    private Integer count;
    private List<ProductResponseDTO> result;

    public ProductResponsePageableDTO(Integer page, Integer size, Integer count, List<ProductResponseDTO> result) {
        this.page = page;
        this.size = size;
        this.count = count;
        this.result = result;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductResponseDTO> getResult() {
        return result;
    }

    public void setResult(List<ProductResponseDTO> result) {
        this.result = result;
    }
}
