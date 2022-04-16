package com.zipzoong.seller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SellerDto {
    private Long sellerId;
    private String sellerName;
    private int sellerAge;


    @QueryProjection
    public SellerDto(Long sellerId, String sellerName, int sellerAge) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerAge = sellerAge;
    }
}
