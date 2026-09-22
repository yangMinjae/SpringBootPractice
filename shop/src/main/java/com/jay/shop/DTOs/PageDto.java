package com.jay.shop.DTOs;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageDto {
    private int currentPage;
    private int startPage;
    private int endPage;
    private int totalPages;
    private boolean prev;
    private boolean next;

    public PageDto(Page<?> page, int windowSize){
        this.currentPage = page.getNumber()+1;
        this.totalPages = Math.max(page.getTotalPages(), 1);
        this.endPage = (int)Math.ceil((double)this.currentPage/windowSize)*windowSize;
        this.startPage = endPage - windowSize +1;
        this.endPage = Math.min(totalPages, this.endPage);
        prev = this.currentPage > 1;
        next = this.currentPage < this.totalPages;
    }
    public int getNextPage(){
        return Math.min(this.endPage + 1, totalPages);
    }
    public int getPrevPage(){
        return Math.max(this.startPage - 1, 1);
    }
}
