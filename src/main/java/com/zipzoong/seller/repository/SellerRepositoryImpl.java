package com.zipzoong.seller.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zipzoong.seller.dto.*;
import com.zipzoong.seller.entity.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.zipzoong.house.entity.QHouse.house;
import static com.zipzoong.seller.entity.QSeller.seller;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class SellerRepositoryImpl implements SellerRepositoryCustom {
    public final JPAQueryFactory queryFactory;


    /*
      토탈이 필요 없을 경우(제일 마지막) 은 Count 쿼리가 안나간다.
     */
    @Override
    public Page<SellerHouseDto> getSellerAllInfo(SellerSearchCondition condition, Pageable pageable) {
        List<SellerHouseDto> content = queryFactory
                .select(
                        new QSellerHouseDto(
                                seller.sellerId,
                                seller.sellerName,
                                seller.sellerAge,
                                house.houseId,
                                house.houseAddress,
                                house.houseSellType,
                                house.sellPrice,
                                house.charterRentPrice,
                                house.monthlyRentPrice,
                                house.depositRentPrice,
                                house.createdDate
                        )
                ).from(seller)
                .leftJoin(seller.houses, house)
                .where(seller_nameEq(condition.getSeller_name()),
                        ageGoe(condition.getSeller_ageGoe()),
                        ageLoe(condition.getSeller_ageLoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Seller> countQuery =  queryFactory
                .select(seller)
                .from(seller)
                .leftJoin(seller.houses, house)
                .where(seller_nameEq(condition.getSeller_name()),
                        ageGoe(condition.getSeller_ageGoe()),
                        ageLoe(condition.getSeller_ageLoe()));

        return
                PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());

    }

    /*
      두개의 Entity 를 join 해서 조회 할 때는 QueryDsl 사용
     */
    @Override
    public List<SellerInfoDto> getSellerInfoByName(String sellerName) {
        return queryFactory.select(
                        new QSellerInfoDto(
                                seller.sellerId,
                                seller.sellerName,
                                seller.sellerAge,
                                house.houseId
                        ))
                .from(seller)
                .leftJoin(seller.houses, house)
                .where(seller.sellerName.eq(sellerName))
                .fetch();
    }

    private BooleanExpression seller_idEq(Long sellerId){
        if(sellerId == null){
            return null;
        }
        return  seller.sellerId.eq(sellerId);
    }

    private BooleanExpression seller_nameEq(String sellerName){
        return hasText(sellerName) ? seller.sellerName.eq(sellerName) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {

        return ageGoe != null ? seller.sellerAge.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {

        return ageLoe != null ? seller.sellerAge.loe(ageLoe) : null;
    }


}
