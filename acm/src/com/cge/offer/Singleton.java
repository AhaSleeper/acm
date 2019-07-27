package com.cge.offer;

/**
 * 面试题2:实现Singleton模式
 */
public class Singleton {
    public static void main(String[] args){

    }

    /**
     * 枚举方式
     */
    enum Single {
        SINGLE;
        private Single(){

        }
        public void method(){

        }
    }
}

/**
 * 1.懒汉模式
 */
class SingletonLazy{
    private static SingletonLazy singleton = null;
    private SingletonLazy(){
    }

    /**
     * 非线程安全-单线程应用
     * @return
     */
    public static SingletonLazy getInstance(){
        if(null == singleton){
            singleton = new SingletonLazy();
        }
        return singleton;
    }

    /**
     * 线程安全-效率低
     * @return
     */
    public static synchronized SingletonLazy getInstanceConcurrent(){
        if(null == singleton){
            singleton = new SingletonLazy();
        }
        return singleton;
    }

    /**
     * 双重检查锁
     * @return
     */
    public static SingletonLazy getInstanceDboubleCheck(){
        if(null == singleton){
            synchronized (SingletonLazy.class){
                if(null == singleton){
                    singleton = new SingletonLazy();
                }
            }
        }
        return singleton;
    }

    /**
     * 匿名内部类方式
     * @return
     */
    public static SingletonLazy getInstanceByHolder(){
        return SingletonHolder.instance;
    }

    private static class SingletonHolder{
        private static final SingletonLazy instance = new SingletonLazy();
    }
}

/**
 * 2.饿汉模式
 */
class SingletonHungry{
    private static SingletonHungry singletonLazy = new SingletonHungry();
    private SingletonHungry(){
    }
    public static SingletonHungry getInstance(){
        return singletonLazy;
    }
}