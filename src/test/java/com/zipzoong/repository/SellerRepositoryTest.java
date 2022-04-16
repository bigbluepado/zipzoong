package com.zipzoong.repository;

import com.zipzoong.house.entity.House;
import com.zipzoong.seller.entity.Seller;
import com.zipzoong.seller.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
class SellerRepositoryTest {
    @Autowired
    EntityManager em;
    @Autowired
    SellerRepository sellerRepository;

    @Test
    public void test(){
        House house1 = new House("seoul sung-buk-gu");
        em.persist(house1);
        Seller seller1 = new Seller("seller1", 10, house1);
        Seller seller2 = new Seller("seller2", 20, house1);
        em.persist(seller1);
        em.persist(seller2);

        List<Seller> result = sellerRepository.findAll();
        System.out.println("result = " + result);
        for (Seller seller : result) {
            System.out.println("seller = " + seller);
            List<House> houses = seller.getHouses();
            for (House house : houses) {
                System.out.println("-->house = " + house);
            }
        }
    }



    @Test
    public void findBySeller_name(){

        Seller seller1 = new Seller("seller1", 10);
        Seller seller2 = new Seller("seller2", 20);
        Seller seller3 = new Seller("seller3");
        Seller seller4 = new Seller("seller4");
        em.persist(seller1);
        em.persist(seller2);
        em.persist(seller3);
        em.persist(seller4);

        House house1 = new House("seoul sung-buk-gu");
        House house1001 = new House("seoul sung-buk-gu 1001");
        House house2 = new House("seoul kand-name-gu");
        House house3 = new House("seoul jong-ro-gu");

        em.persist(house1);
        em.persist(house1001);
        em.persist(house2);
        em.persist(house3);

        seller1.addHouse(house1);
        seller1.addHouse(house1001);
        seller2.addHouse(house2);
        seller3.addHouse(house3);


        List<Seller> resrults = sellerRepository.findBySellerName("seller1");
        for (Seller seller : resrults) {
            System.out.println("seller = " + seller);
        }

    }


}