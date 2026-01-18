package com.jay.shop.DTOs;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageDto {
    private int startPage;      // 현재 블록의 시작 번호 (예: 6)
    private int endPage;        // 현재 블록의 끝 번호 (예: 10)
    private boolean prev, next; // <, > 버튼 표시 여부
    private int totalPages;     // 전체 페이지 수 (>> 버튼용)
    private int currentPage;    // 현재 페이지 번호

    public PageDto(Page<?> page, int windowSize) {
        this.currentPage = page.getNumber() + 1; // 0-based -> 1-based
        this.totalPages = page.getTotalPages();

        // 1. 현재 블록의 끝 페이지 계산 (예: 7페이지고 windowSize가 5면, ceil(7/5)*5 = 10)
        this.endPage = (int) (Math.ceil(currentPage / (double) windowSize)) * windowSize;

        // 2. 현재 블록의 시작 페이지 계산 (10 - 5 + 1 = 6)
        this.startPage = endPage - (windowSize - 1);

        // 3. 실제 마지막 페이지가 계산한 endPage보다 작으면 endPage를 totalPages로 수정
        if (totalPages < endPage) {
            this.endPage = totalPages;
        }

        // 4. 이전/다음 블록 존재 여부
        this.prev = this.startPage > 1; // 시작페이지가 1보다 크면 이전 블록(<) 존재
        this.next = this.endPage < totalPages; // 끝페이지가 전체보다 작으면 다음 블록(>) 존재
    }
}
