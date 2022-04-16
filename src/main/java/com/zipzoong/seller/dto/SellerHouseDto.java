package com.zipzoong.seller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter
@Setter
public class SellerHouseDto {
    private Long sellerId;
    private String sellerName;
    private int sellerAge;
    private Long houseId;
    private String houseAddress;
    private int houseSellYype;
    private int sellPrice;
    private int charterRentPrice;
    private int monthlyRentPrice;
    private int depositRentPrice;
    private LocalDateTime createdDate;

    @QueryProjection
    public SellerHouseDto(Long sellerId, String sellerName, int sellerAge, Long houseId,
                          String houseAddress, int houseSellYype, int sellPrice, int charterRentPrice,
                          int monthlyRentPrice, int depositRentPrice, LocalDateTime createdDate) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerAge = sellerAge;
        this.houseId = houseId;
        this.houseAddress = houseAddress;
        this.houseSellYype = houseSellYype;
        this.sellPrice = sellPrice;
        this.charterRentPrice = charterRentPrice;
        this.monthlyRentPrice = monthlyRentPrice;
        this.depositRentPrice = depositRentPrice;
        this.createdDate = createdDate;
    }
}
