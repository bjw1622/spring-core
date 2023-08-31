package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepositroy;

public class OrderServiceImpl implements OrderService {
    // 회원 찾기
    private final MemberRepository memberRepository = new MemoryMemberRepositroy();
    // 고정 할인 정책
    // 변경 할인 정책
    // 지금까지 단순히 DiscountPolicy 의존하는 줄 알았으나...
    // 하지만.. 실제로 RateDiscountPolicy 의존하고 있다, 실제 구현 클래스도 의존(DIP 위반)
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 어떤 구현체가 생성될지에는 관심을 꺼라!
    // 관심사 분리
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        /*
        *   단일 체계 원칙을 잘 지킨 예시
        *   OrderService 입장에서는 할인 정책에 대해서 전혀 알지 못함
        *   만약 할인 정책이 바뀌더라도 실질적으로 OrderService에서 바뀌는 값은 없음
        * */
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
