package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepositroy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 나의 application 전체를 설정하고 구성
public class AppConfig {
    public MemberService memberService() {
        // 생성과 동시에 생성자로 전달
        // MemberSercieImpl 입장에서는 외부에서(appConfig)가 의존 관계를 주입하는 것 처럼
        return new MemberServiceImpl(new MemoryMemberRepositroy());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepositroy(), new FixDiscountPolicy());
    }
}
