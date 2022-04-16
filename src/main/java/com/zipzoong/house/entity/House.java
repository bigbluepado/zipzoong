package com.zipzoong.house.entity;

import com.zipzoong.common.entity.BaseTimeEntity;
import com.zipzoong.seller.entity.Seller;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//객체를 찍으면 아래의 변수가 찍힌다. 편할려고 사용
//seller 은 연관관계가 있어 찍으면 안된다.!! 무한루프...
@ToString(of = {"houseId", "houseAddress", "houseSellType"})
public class House extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "house_id")
    private Long houseId;
    private String houseAddress;
    private int houseSellType;
    private int sellPrice;
    private int charterRentPrice;
    private int monthlyRentPrice;
    private int depositRentPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    //JPA는 agr 없은 기본 생성자를 protected 까지만 만들어야 한다.
    //lombok의 @NoArgsConstructor(access = AccessLevel.PROTECTED) 대체 가능
    //protected Member(){}


    public House(String houseAddress, int houseSellType,
                 int sellPrice, int charterRentPrice, int monthlyRentPrice,
                 int depositRentPrice, Seller seller) {
        this.houseAddress = houseAddress;
        this.houseSellType = houseSellType;
        this.sellPrice = sellPrice;
        this.charterRentPrice = charterRentPrice;
        this.monthlyRentPrice = monthlyRentPrice;
        this.depositRentPrice = depositRentPrice;
        this.seller = seller;
    }

    public House(String houseAddress, int houseSellType, int sellPrice,
                 int charterRentPrice, int monthlyRentPrice, int depositRentPrice) {
        this.houseAddress = houseAddress;
        this.houseSellType = houseSellType;
        this.sellPrice = sellPrice;
        this.charterRentPrice = charterRentPrice;
        this.monthlyRentPrice = monthlyRentPrice;
        this.depositRentPrice = depositRentPrice;
    }

    public House(String houseAddress){
        this.houseAddress = houseAddress;
    }

    public House(String houseAddress, int houseSellType){
        this.houseAddress = houseAddress;
        this.houseSellType = houseSellType;
    }

    public House(String houseAddress, int houseSellType, Seller seller){
        this.houseAddress = houseAddress;
        this.houseSellType = houseSellType;
        if (seller != null){
            setSeller(seller);
        }
    }

    //연관관계를 변경할 수 있는 메소드를 만들어 줘야 함.
    private void changeSeller(Seller seller) {
        this.seller = seller;
        seller.getHouses().add(this);
    }

}
