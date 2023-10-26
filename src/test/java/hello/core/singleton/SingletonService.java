package hello.core.singleton;

public class SingletonService {

    // static이라 영역에 한개만 올라오게 됨
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // private 생성자로 다른 클레스에서 생성하지 못하게 막기
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
