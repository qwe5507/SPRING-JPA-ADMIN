package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagenation {
    private Integer totalPages;           // 몇개 페이지가 있는지
    private Long totalElements;         //몇개 있는지
    private Integer currentPage;        //현재페이지는 몇 번째 인지
    private Integer currentElements; //현재 몇개의 데이터가 내려 갔는지
}
