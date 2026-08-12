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
        this.totalPages = Math.max(page.getTotalPages(),1);
        this.endPage = (int)Math.ceil(this.currentPage/(double)windowSize)*windowSize;

        // ------감싸진 두 구문의 순서 중요------
        this.startPage = endPage-windowSize+1;
        if(this.totalPages < endPage){
            endPage = this.totalPages;
        }
        // ------감싸진 두 구문의 순서 중요------

        this.prev = this.currentPage>1;
        this.next = this.currentPage < this.totalPages;
    }
    public int getPrevPage(){
        return Math.max(this.startPage-1, 1);
    }
    public int getNextPage(){
        return Math.min(this.endPage+1, this.totalPages);
    }
}
