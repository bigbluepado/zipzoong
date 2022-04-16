package com.zipzoong.seller.controller;

import com.zipzoong.seller.dto.SellerDto;
import com.zipzoong.seller.dto.SellerHouseDto;
import com.zipzoong.seller.dto.SellerInfoDto;
import com.zipzoong.seller.dto.SellerSearchCondition;
import com.zipzoong.seller.entity.Seller;
import com.zipzoong.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor

public class SellerController {
    private final SellerRepository sellerRepository;
    /*
      QueryDsl, Paging API
      SellerRepositoryImpl 에 구현되어 있다.
     */
   @GetMapping("/v1/searchSellers")
    public Page<SellerHouseDto> searchSellers(SellerSearchCondition condition, Pageable pageable){
        return sellerRepository.getSellerAllInfo(condition, pageable);
    }


    /*
      Spring JPA
      Entity는 Dto 로 변환해서 API 리턴한다.
      하나의 Entity 만 조회 할 때 사용 - Seller 정보만 조회
    */
    @GetMapping("/v1/getSellerByName")
    public List<SellerDto> getSellerByName(String sellerName){
        //findBy 뒤에 Entity의 속성을 넣으면 자동으로 Query 가 만들어진다. !!!!
        List<Seller> sellers =sellerRepository.findBySellerName(sellerName);

        List<SellerDto> SellerDtos =  sellers.stream().map(
                seller -> new SellerDto(
                        seller.getSellerId(),
                        seller.getSellerName(),
                        seller.getSellerAge()
                )
        ).collect(Collectors.toList());

        return SellerDtos;
    }

    /*
      Spring JPA  --> 학습용... 쓰지 말자, 되기는하지만 Query가 2번 나간다.
      Entity는 Dto 로 변환해서 API 리턴한다.
      두개의 Entity 를 join 해서 조회 할 때는 QueryDsl 사용하자!!
    */
    @GetMapping("/v0/getSellerByNameT")
    public List<SellerInfoDto> getSellerByNameT(String sellerName){
        List<Seller> sellers =sellerRepository.findBySellerName(sellerName);

        List<SellerInfoDto> sellerInfoDtos =  sellers.stream().map(
                seller -> new SellerInfoDto(
                        seller.getSellerId(),
                        seller.getSellerName(),
                        seller.getSellerAge(),
                        seller.getHouses().get(0).getHouseId()
                )
        ).collect(Collectors.toList());

        return sellerInfoDtos;
    }


    /*
        QueryDsl
        두개의 Entity 를 join 해서 조회 할 때는 QueryDsl 사용
        SellerRepositoryImpl 에 구현되어 있다.
      */
    @GetMapping("/v1/getSellerInfoByName")
    public List<SellerInfoDto> getSellerInfoByName(String sellerName) {
        List<SellerInfoDto> sellers = sellerRepository.getSellerInfoByName(sellerName);

        List<SellerInfoDto> sellerInfoDtos = sellers.stream().map(
                seller -> new SellerInfoDto(
                        seller.getSellerId(),
                        seller.getSellerName(),
                        seller.getSellerAge(),
                        seller.getHouseId()
                )
        ).collect(Collectors.toList());

        return sellerInfoDtos;
    }



}
