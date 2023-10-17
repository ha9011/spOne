package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);// [0] Bean이름, [1] Bean타입
        OrderService orderService = ac.getBean("orderService", OrderService.class);// [0] Bean이름, [1] Bean타입

        Member memberA = new Member(1L, "memberA", Grade.VIP);

        memberService.join(memberA);
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + memberA.getName());
        System.out.println("find Member = " + findMember.getName());

        Order order = orderService.createOrder(memberA.getId(), "itemA", 10000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }
}
