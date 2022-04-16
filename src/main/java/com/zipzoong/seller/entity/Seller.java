package com.zipzoong.seller.entity;

import com.zipzoong.common.entity.BaseTimeEntity;
import com.zipzoong.house.entity.House;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//객체를 찍으면 아래의 변수가 찍힌다. 편할려고 사용
//seller 은 연관관계가 있어 찍으면 안된다.!! 무한루프...
@ToString(of = {"sellerId", "sellerName", "sellerAge"})
public class Seller extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "seller_id")
    private Long sellerId;
    private String sellerName;
    private int sellerAge;

    //join 될 경우 FK 없는 쪽에 mappedBy를 걸어준다.
    // Member 테이블이 Many 쪽이므로 FK 가 있다. PK는 One 쪽이다.
    @OneToMany(mappedBy = "seller")
    private List<House> houses = new ArrayList<>();


    //JPA는 agr 없은 기본 생성자를 protected 까지만 만들어야 한다.
    //lombok의 @NoArgsConstructor(access = AccessLevel.PROTECTED) 대체 가능
    //protected Team(){}


    public Seller(String seller_name, int seller_age) {
        this.sellerName = seller_name;
        this.sellerAge = seller_age;
    }

    public Seller(String seller_name, int seller_age, House house) {
        this.sellerName = seller_name;
        this.sellerAge = seller_age;
        houses.add(house);
    }

    public Seller(String seller_name){
        this.sellerName = seller_name;
    }

    public void addHouse(House house){
        this.houses.add(house);
        house.setSeller(this);
    }

}
