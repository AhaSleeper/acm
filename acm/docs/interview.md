Java基础
1、List 和 Set 的区别
List允许添加重复元素，并且保证存储的元素是按照添加的先后顺序排列的，而set不允许存在重复元素，并且无法保证遍历的时候，元素可以按照存放时的
顺序取出，也就是无法保证元素是有序的。
2、HashSet 是如何保证不重复的 
hashSet是通过比较对象的hashCode，同时根据equals判断，如果二者都相同，那么对象就是重复的，否则就是不同对象。
3、HashMap 是线程安全的吗，为什么不是线程安全的（最好画图说明多线程环境下不安全）? 
hashMap不是线程安全的。在多线程中对hashMap进行操作，可能会产生不符合预期的问题：
1）put操作可能会导致元素丢失；
2）put和get并发时，可能导致get为null
线程1执行put时，因为元素个数超出threshold而导致rehash，线程2此时执行get，有可能导致这个问题；
3）jdk1.7中HashMap的并发put操作会造成循环链表的形成，从而导致get操作死循环。
4、HashMap 的扩容过程 
5、HashMap 1.7 与 1.8 的 区别，说明 1.8 做了哪些优化，如何优化的？ 
6、final finally finalize 
7、强引用 、软引用、 弱引用、虚引用 
8、Java反射 

Java 并发
1、synchronized 的实现原理以及锁优化？

2、volatile 的实现原理？

3、Java 的信号灯？

4、synchronized 在静态方法和普通方法的区别？

5、怎么实现所有线程在等待某个事件的发生才会去执行？

6、CAS？CAS 有什么缺陷，如何解决？

7、synchronized 和 lock 有什么区别？

8、Hashtable 是怎么加锁的 ？

9、HashMap 的并发问题？

10、ConcurrenHashMap 介绍？1.8 中为什么要用红黑树？

11、AQS

12、如何检测死锁？怎么预防死锁？

13、Java 内存模型？

14、如何保证多线程下 i++ 结果正确？

15、线程池的种类，区别和使用场景？

16、分析线程池的实现原理和线程的调度过程？

17、线程池如何调优，最大数目如何确认？

18、ThreadLocal原理，用的时候需要注意什么？ 

Spring
1、BeanFactory 和 FactoryBean？

2、Spring IOC 的理解，其初始化过程？

3、BeanFactory 和 ApplicationContext？

4、Spring Bean 的生命周期，如何被管理的？

5、Spring Bean 的加载过程是怎样的？

6、如果要你实现Spring AOP，请问怎么实现？

7、如果要你实现Spring IOC，你会注意哪些问题？

8、Spring 是如何管理事务的，事务管理机制？

9、Spring 的不同事务传播行为有哪些，干什么用的？

10、Spring 中用到了那些设计模式？ 

Netty
1、BIO、NIO和AIO

2、Netty 的各大组件

3、Netty的线程模型

4、TCP 粘包/拆包的原因及解决方法

5、了解哪几种序列化协议？包括使用场景和如何去选择

6、Netty的零拷贝实现

7、Netty的高性能表现在哪些方面

分布式相关
1、Dubbo的底层实现原理和机制

2、描述一个服务从发布到被消费的详细过程

3、分布式系统怎么做服务治理

4、接口的幂等性的概念

5、消息中间件如何解决消息丢失问题

6、Dubbo的服务请求失败怎么处理

7、重连机制会不会造成错误

8、对分布式事务的理解

9、如何实现负载均衡，有哪些算法可以实现？

10、Zookeeper的用途，选举的原理是什么？

11、数据的垂直拆分水平拆分。

12、zookeeper原理和适用场景

13、zookeeper watch机制

14、redis/zk节点宕机如何处理

15、分布式集群下如何做到唯一序列号

16、如何做一个分布式锁 

缓存
1、Redis用过哪些数据数据，以及Redis底层怎么实现

2、Redis缓存穿透，缓存雪崩

3、如何使用Redis来实现分布式锁

4、Redis的并发竞争问题如何解决

5、Redis持久化的几种方式，优缺点是什么，怎么实现的

6、Redis的缓存失效策略

7、Redis集群，高可用，原理

8、Redis缓存分片

9、Redis的数据淘汰策略

JVM
1、详细jvm内存模型

2、讲讲什么情况下回出现内存溢出，内存泄漏？ 

3、说说Java线程栈

4、JVM 年轻代到年老代的晋升过程的判断条件是什么呢？

5、JVM 出现 fullGC 很频繁，怎么去线上排查问题？

6、类加载为什么要使用双亲委派模式，有没有什么场景是打破了这个模式？

7、类的实例化顺序

8、JVM垃圾回收机制，何时触发MinorGC等操作

9、JVM 中一次完整的 GC 流程（从 ygc 到 fgc）是怎样的

10、各种回收器，各自优缺点，重点CMS、G1

11、各种回收算法

12、OOM错误，stackoverflow错误，permgen space错误


