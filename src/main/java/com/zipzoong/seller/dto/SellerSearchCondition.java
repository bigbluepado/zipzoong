package com.zipzoong.seller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SellerSearchCondition {
    private Long seller_id;
    private String seller_name;
    private Integer seller_ageGoe;
    private Integer seller_ageLoe;
}
