package test;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class OOMTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        List<String> list = new LinkedList<>();
        /*while(true) {
//            list.add(new String("hello"));
        }*/
        User xiaowang = new User();
        xiaowang.setName("xiaoWang");
        User xiaoLi = new User();
        xiaoLi.setName("xiaoLi");
        Thread t1 = new Thread(){
            @Override
            public void run() {
                synchronized (xiaowang){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    xiaoLi.sayName();
                }
            }
        };
        t1.start();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                synchronized (xiaoLi){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    xiaowang.sayName();
                }
            }
        };
        t2.start();

    }

    public String testOverload(int i){
        return "string overload";
    }

}
