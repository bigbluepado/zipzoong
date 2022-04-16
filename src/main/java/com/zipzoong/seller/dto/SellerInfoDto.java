package com.zipzoong.seller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SellerInfoDto {
    private Long sellerId;
    private String sellerName;
    private int sellerAge;
    private Long houseId;


    @QueryProjection
    public SellerInfoDto(Long sellerId, String sellerName, int sellerAge, Long houseId) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerAge = sellerAge;
        this.houseId = houseId;
    }
}
