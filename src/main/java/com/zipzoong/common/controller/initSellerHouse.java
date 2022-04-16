package com.zipzoong.common.controller;

import com.zipzoong.house.entity.House;
import com.zipzoong.seller.entity.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Profile("local")
@Component
@RequiredArgsConstructor
public class initSellerHouse {

    private  final  InitMemberService initMemberService;

    @PostConstruct
    public void init(){
        initMemberService.init();

    }

    @Component
    static class InitMemberService{

        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init(){


            int k = 0;
            for (int i=0; i<10; i++){
                Seller seller = new Seller("seller" + k, (20 + i));
                em.persist(seller);
                k=k+1;
                House house = new House("서울시 서초구 방배동 1-15 방배아펠바움 " + i + "호",
                        20, 0, 1000000000, 0, 0);
                em.persist(house);
                seller.addHouse(house);
            }

            for (int i=0; i<10; i++){
                Seller seller = new Seller("seller" + k, (20 + i));
                em.persist(seller);
                k=k+1;
                House house = new House("서울시 서초구 동광로 몽마르트빌 A동 " + i + "호",
                        21, 0, 10000000, 50 + i, 0);
                em.persist(house);
                seller.addHouse(house);
            }


            for (int i=0; i<10; i++){
                Seller seller = new Seller("seller" + k, (20 + i));
                em.persist(seller);
                k=k+1;
                House house = new House("서울시 서초구 서울시 서초구 반포동 99-1 엘리제빌 " + i + "호",
                        30, 0, 0, 3000000 + i, 0);
                em.persist(house);
                seller.addHouse(house);
            }


            for (int i=0; i<10; i++){
                Seller seller = new Seller("seller" + k, (20 + i));
                em.persist(seller);
                k=k+1;
                House house = new House("서울시 서초구 방배동 845-1 성지라빌레뜨 " + i + "호",
                        31, 0, 0, 100 + i, 5000000);
                em.persist(house);
                seller.addHouse(house);
            }
        }

    }
}
