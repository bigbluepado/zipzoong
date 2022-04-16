package com.zipzoong.seller.repository;

import com.zipzoong.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> , SellerRepositoryCustom{
     /*
       QueryDsl의 기능은
        SellerRepositoryCustom 을 구현한 SellerRepositorylmpl 에 구현되어 있다. !!!
     */

    // 아래는 전부 Spring JPA 를 사용함.
    //findBy + Entity의 속성(sellerName, sellerAge 등등...)을 넣으면 자동으로 Query 가 만들어진다. !!!!
    List<Seller> findBySellerName(String name);

}
