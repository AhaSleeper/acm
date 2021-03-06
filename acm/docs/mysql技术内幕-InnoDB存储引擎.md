### 一、MySQL体系结构和存储引擎

1. 数据库和实例

   > **数据库：**物理操作系统文件或其他形式文件类型的集合。在MySQL数据库中，数据库文件可以是frm、MYD、MYI、ibd结尾的文件。当使用NDB引擎时，数据库的文件可能不是操作系统上的文件，而是存放于内存之中的文件，但是定义仍然不变。
   >
   > **实例：**MySQL数据库由后台线程以及一个共享内存区组成。共享内存可以被运行的后台线程所共享。需要牢记的是，数据库实例才是真正用于操作数据库文件的。

2. MySQL体系结构

   ![](images\mysql-architect.png)

   MySQL由以下几部分组成：连接池组件、管理服务和工具组件、SQL接口组件、查询分析器组件、优化器组件、缓冲组件、插件式存储引擎、物理文件。

3. MySQL存储引擎

### 二、InnoDB存储引擎

InnoDB从MySQL 5.5版本开始是默认的表存储引擎。该存储引擎是第一个完整支持ACID事务的MySQL存储引擎，其特点是行锁设计、支持MVCC、支持外键、提供一致性非锁定读，同时被设计用来最有效地利用以及使用内存和CPU。