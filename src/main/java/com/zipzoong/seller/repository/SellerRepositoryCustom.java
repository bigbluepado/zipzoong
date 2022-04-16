package com.zipzoong.seller.repository;

import com.zipzoong.seller.dto.SellerHouseDto;
import com.zipzoong.seller.dto.SellerInfoDto;
import com.zipzoong.seller.dto.SellerSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SellerRepositoryCustom {
    Page<SellerHouseDto> getSellerAllInfo(SellerSearchCondition condition, Pageable pageable);
    List<SellerInfoDto> getSellerInfoByName(String sellerName);
}
