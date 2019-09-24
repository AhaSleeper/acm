1. 简述hibernate运行原理或者工作原理

   > hibernate里面提供了三个核心接口：Configuration、SessionFactory、Session
   >
   > 1）hibernate启动的时候利用Configuration读取xml配置文件
   >
   > 2）通过配置文件创建SessionFactory对象，初始化hibernate基本信息
   >
   > 3）获取Session然后调用crud方法进行数据操作，hibernate会把我们的数据进行三种状态的划分，然后根据状态进行管理我们的数据，对应的发送SQL进行数据操作
   >
   > 4）关闭session，如果有事务的情况下，需要手动获取事务并开启，然后事务结束后提交事务。
   >
   > 5）在提交事务的时候，去验证我们的快照里面的数据和缓存数据是否一致，如果不一致，发送SQL进行修改

2. 简述hibernate的get和load方法区别

   > 1）get和load都是利用主键策略查询数据
   >
   > 2）get默认不使用懒加载机制，load默认要使用懒加载机制，所谓的懒加载就是我们这个数据如果不使用，hibernate就不发送SQL到数据库查询数据
   >
   > 3）当查询数据库不存在的数据的时候，get方法返回null，load方法抛出空指针异常。原因：load方法是采用的动态代理方式实现的，我们使用load方法的时候，hibernate会创建一个该实体的代理对象，该代理只保存了该对象的ID，当我们访问该实体对象其他属性，hibernate就发送SQL查询数据封装到代理对象，然后利用代理对象返回给我们实际的数据。

3. 简述hibernate数据三种状态

   > hibernate数据的三种状态：
   >
   > 1）瞬时态（刚new 出来的数据，内存有，数据库没有）
   >
   > 2）持久态（从数据库查询的，或者刚保存到数据库，session没关闭的，数据库有，内存也有）
   >
   > 3）游离态/脱管态（数据库有，内存没有）
   >
   > ![hibernate数据的三种状态](images\hibernate-data-status.png)
   >
   > 实际上hibernate对数据划分三种状态，主要是为了管理我们持久的数据，在事务提交的时候，hibernate去对比处于持久状态的数据是否发生改变，(快照区、一级缓存区)，当我们会话结束前，对持久状态数据进行了修改的话，快照区的数据会跟着改变。当session提交事务的时候，如果发现快照区和一级缓存的数据不一致，就会发送SQL进行修改。

4. 简述hibernate的缓存机制

   > hibernate分为2级缓存：
   >
   > 一级缓存又叫session缓存，又叫事务级缓存，生命周期从事务开始到事务结束，一级缓存是hibernate自带的，当我们创建session时就已有这个缓存了，数据库就会自动往缓存存放。
   >
   > 二级缓存是hibernate提供的一组开放的接口方式实现的，都是通过整合第三方的缓存框架来实现的，二级缓存又叫sessionFactory的缓存，可以跨session访问。常用的Ehcace,OScache，需要配置才能使用。
   >
   > 每次查询数据时，首先是到一级缓存查看是否存在该对象，如果有直接返回，如果没有就去二级缓存查看，如果有直接返回，如果没有就发送SQL到数据库查询数据。
   >
   > 当SQL发送查询并获得该数据的时候，hibernate就会把该对象以主键标记的形式存储到二级缓存和一级缓存，如果返回的是集合，会把集合打散，然后以主键的形式存储到缓存。一级缓存和二级缓存只针对以id查询的方式生效。

5. 简述hibernate的getCurrentSession和openSession区别

   > getCurrentSession和openSession都是通过hibernate的SessionFactory去获取数据的回话对象，
   >
   > 1）getCurrentSession会绑定当前线程，而openSession不会，因为我们把hibernate交给我们的spring来管理之后，我们是有事务配置，这个有事务的线程就会绑定当前的工厂里面的每一个session，而openSession是创建一个新的session
   >
   > 2）getCurrentSession事务是由spring控制的，而openSession需要我们手动开启和手动提交事务
   >
   > 3）getCurrentSession是不需要我们手动关闭的，因为工厂会自己管理，而openSession需要我们手动关闭
   >
   > 4）getCurrentSession需要我们手动设置绑定事务的机制，有三种设置方式，jdbc本地的，Thread、JTA、第三种是spring提供的事务管理机制org.springframework.orm.hibernate4.SpringSessinContext，而且spring默认使用该种事务管理机制

6. 简述hibernate的乐观锁和悲观锁

7. 简述hibernate的懒加载机制

8. 简述hibernate的事务机制

9. 什么是hibernate的并发机制？怎么去处理并发问题？

   > hibernate的并发机制：
   >
   > - hibernate的session对象是非线程安全的，对于单个请求，单个会话，单个的工作单元（即单个事务，单个线程），它通常只使用一次，然后就丢弃。
   > - 多个事务并发访问同一块资源，可能会引发第一类丢失更新，脏读，幻读，不可重复读，第二类丢失更新一系列问题。
   >
   > 解决方案：设置事务隔离级别。
   >
   > Serializable:串行化。隔离级别最高。
   >
   > Repeatable Read:可重复读
   >
   > Read Committed:已提交数据读
   >
   > Read Uncommitted：未提交数据读。隔离级别最差。
   >
   > 设置锁：乐观锁和悲观锁。
   >
   > 乐观锁：使用版本号或时间戳来检测更新丢失，在映射中设置optimistic-lock="all"可以在没有版本或者时间戳属性映射的情况下实现版本检查，此时hibernate将比较一行记录的每个字段的状态；
   >
   > 行级悲观锁：hibernate总是使用数据库的锁定机制，从不在内存中锁定对象！只要为JDBC连接指定以下隔离级别，然后让数据库去搞定一切就够了。类LockMode定义了hibernate所需的不同的锁定级别：LockMode.UPGRADE,LockMode.UPGRADE_NOWAIT,LockMode.READ;

10. update和saveOrUpdate的区别？

    > update()和saveOrUpdate()是用来对跨Session的PO进行状态管理的。
    > update()方法操作的对象必须是持久化了的对象。也就是说，如果此对象在数据库中不存在的话，就不能使用update()方法。
    > saveOrUpdate()方法操作的对象既可以使持久化了的，也可以使没有持久化的对象。如果是持久化了的对象调用saveOrUpdate()则会 更新数据库中的对象；如果是未持久化的对象使用此方法,则save到数据库中。

11. hibernate的三种状态之间如何转换？

    > 当对象由瞬时状态(Transient)一save()时，就变成了持久化状态；
    > 当我们在Session里存储对象的时候，实际是在Session的Map里存了一份， 也就是它的缓存里放了一份，然后，又到数据库里存了一份，在缓存里这一份叫持久对象(Persistent)。 Session 一 Close()了,它的缓存也都关闭了,整个Session也就失效了,这个时候，这个对象变成了游离状态(Detached)，但数据库中还是存在的。
    > 当游离状态(Detached)update()时，又变为了持久状态(Persistent)。
    > 当持久状态(Persistent)delete()时，又变为了瞬时状态(Transient), 此时，数据库中没有与之对应的记录。

12. 比较hibernate的三种检索策略优缺点？

    > 1）立即检索；
    > 优点： 对应用程序完全透明，不管对象处于持久化状态，还是游离状态，应用程序都可以方便的从一个对象导航到与它关联的对象；
    > 缺点： 1.select语句太多；2.可能会加载应用程序不需要访问的对象白白浪费许多内存空间；
    > 2）延迟检索：
    > 优点： 由应用程序决定需要加载哪些对象，可以避免可执行多余的select语句，以及避免加载应用程序不需要访问的对象。因此能提高检索性能，并且能节省内存空间；
    > 缺点： 应用程序如果希望访问游离状态代理类实例，必须保证他在持久化状态时已经被初始化；
    > 3 ）迫切左外连接检索
    > 优点： 1对应用程序完全透明，不管对象处于持久化状态，还是游离状态，应用程序都可以方便地冲一个对象导航到与它关联的对象。2使用了外连接，select语句数目少；
    > 缺点： 1 可能会加载应用程序不需要访问的对象，白白浪费许多内存空间；2复杂的数据库表连接也会影响检索性能；

13. 如何在控制台看到hibernate生成并执行的sql？

    > 在定义数据库和数据库属性的文件applicationConfig.xml里面，把hibernate.show_sql 设置为true;这样生成的SQL就会在控制台出现了注意：这样做会加重系统的负担，不利于性能调优

14. hibernate都支持哪些缓存策略？

    > Read-only: 这种策略适用于那些频繁读取却不会更新的数据，这是目前为止最简单和最有效的缓存策略
    >
    > - Read/write:这种策略适用于需要被更新的数据，比read-only更耗费资源，在非JTA环境下，每个事务需要在session.close和session.disconnect()被调用
    > - Nonstrict read/write: 这种策略不保障两个同时进行的事务会修改同一块数据，这种策略适用于那些经常读取但是极少更新的数据
    > - Transactional: 这种策略是完全事务化得缓存策略，可以用在JTA环境下

15. hibernate里面的sorted collection和ordered collection有什么区别？

    > sorted collection是在内存中通过Java比较器进行排序的
    > ordered collection是在数据库中通过order by进行排序的

16. hibernate工作原理以及为什么要用？

    > 1）读取并解析配置文件
    > 2）读取并解析映射信息，创建SessionFactory
    > 3）打开Sesssion
    > 4）创建事务Transation
    > 5）持久化操作
    > 6）提交事务
    > 7）关闭Session
    > 8）关闭SesstionFactory
    >
    > 为什么要用：
    >
    > 1）对JDBC访问数据库的代码做了封装，大大简化了数据访问层繁琐的重复性代码。
    >
    > Hibernate是一个基于JDBC的主流持久化框架，是一个优秀的ORM实现。他很大程度的简化DAO层的编码工作
    >
    > hibernate使用Java反射机制，而不是字节码增强程序来实现透明性。
    >
    > hibernate的性能非常好，因为它是个轻量级框架。映射的灵活性很出色。它支持各种关系数据库，从一对一到多对多的各种复杂关系。

17. hibernate中怎样实现类之间的关系？（如：一对多，多对多的关系）

    > 类与类之间的关系主要体现在表与表之间的关系进行操作，它们都是对对象进行操作，我们程序中把所有的表与类都映射在一起，它们通过配置文件中的many-to-one、one-to-many、many-to-many、

18. 说下hibernate的缓存机制

    > 1) 内部缓存存在Hibernate中又叫一级缓存，属于应用事物级缓存
    >
    > 2）二级缓存：
    > a) 应用及缓存
    > b) 分布式缓存
    > 条件：数据不会被第三方修改、数据大小在可接受范围、数据更新频率低、同一数据被系统频繁使用、非关键数据
    > c) 第三方缓存的实现

19. hibernate的查询方式

    > Sql、Criteria,objectcomposition
    > Hql：
    > 1) 属性查询
    > 2) 参数查询、命名参数查询
    > 3) 关联查询
    > 4) 分页查询
    > 5) 统计函数

20. **如何优化hibernate？**

    > 1)使用双向一对多关联，不使用单向一对多
    > 2)灵活使用单向一对多关联
    > 3)不用一对一，用多对一取代
    > 4)配置对象缓存，不使用集合缓存
    > 5)一对多集合使用Bag,多对多集合使用Set
    >
    > 6）继承类使用显式多态
    >
    > 7）表字段要少，表关联不要怕多，有二级缓存撑腰

21. hibernate有几种查询数据的方式？

    > 3种：hql、条件查询QBC(QueryBy Criteria)、原生sql （通过createSQLQuery建立）

22. 谈谈hibernate中inverse的作用？

    > inverse属性默认是false,就是说关系的两端都来维护关系。
    > 比如Student和Teacher是多对多关系，用一个中间表TeacherStudent维护。Gp)i
    > 如果Student这边inverse=”true”, 那么关系由另一端Teacher维护，就是说当插入Student时，不会操作TeacherStudent表（中间表）。只有Teacher插入或删除时才会触发对中间表的操作。所以两边都inverse=”true”是不对的，会导致任何操作都不触发对中间表的影响；当两边都inverse=”false”或默认时，会导致在中间表中插入两次关系。

23. Detached Object（游离对象）有什么好处？

    > Detached Object（游离对象）可以传递到任何层直到表现层而不是用任何DTO(DataTransfer Objects). 然后你还可以重新把游离对象赋给另外一个Session.

24. JDBC hibernate和ibatis的区别

    > jdbc:手动
    > 手动写sql
    > delete、insert、update要将对象的值一个一个取出传到sql中,不能直接传入一个对象。
    > select:返回的是一个resultset，要从ResultSet中一行一行、一个字段一个字段的取出，然后封装到一个对象中，不直接返回一个对象。
    > ibatis的特点:半自动化
    > sql要手动写
    > delete、insert、update:直接传入一个对象
    > select:直接返回一个对象
    > hibernate:全自动
    > 不写sql,自动封装
    > delete、insert、update:直接传入一个对象
    > select:直接返回一个对象

25. 在数据库中条件查询速度很慢的时候，如何优化？

    > 1）建索引
    > 2）减少表之间的关联
    > 3）优化sql，尽量让sql很快定位数据，不要让sql做全表查询，应该走索引,把数据量大的表排在前面
    > 4）简化查询字段，没用的字段不要，已经对返回结果的控制，尽量返回少量数据

26. 什么是SessionFactory，它是线程安全的吗？

    > SessionFactory 是Hibrenate单例数据存储和线程安全的，以至于可以多线程同时访问。一个SessionFactory 在启动的时候只能建立一次。SessionFactory应该包装各种单例以至于它能很简单的在一个应用代码中储存.

27. Hibernate的五个核心接口

    > Configuration 接口：配置Hibernate，根据其启动hibernate，创建
    > SessionFactory 对象；
    > SessionFactory 接口：初始化Hibernate，充当数据存储源的代理，创建
    > session 对象，sessionFactory 是线程安全的，意味着它的同一个实例可以被应
    > 用的多个线程共享，是重量级、二级缓存；
    > Session 接口：负责保存、更新、删除、加载和查询对象，是线程不安全的，
    > 避免多个线程共享同一个session，是轻量级、一级缓存；
    > Transaction 接口：管理事务；
    > Query 和Criteria 接口：执行数据库的查询。