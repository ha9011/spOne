package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // orderservice 입장에선
        // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
        // 지정만 잘한다면, discountPrice는 알아서 주는거니 크게 discount에 신경을 안써도됨 -> 단일책임원칙

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
