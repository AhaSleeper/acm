package com.boss.utils.db;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.boss.service.indexj.search.input.model.ProductRankingModelNews;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

public class DbSLKit {

    //本地
//	private static final String jdbcUrl = "jdbc:mysql://127.0.0.1/test?useUnicode=true&characterEncoding=UTF8&useServerPrepStmts=true&prepStmtCacheSqlLimit=256&cachePrepStmts=true&prepStmtCacheSize=256&rewriteBatchedStatements=true";
//	private static final String user = "root";
//	private static final String pwd = "root";

    //正式
//	private static final String jdbcUrl = "jdbc:mysql://57ad6eae5d497.gz.cdb.myqcloud.com:16062/souliao?useUnicode=true&characterEncoding=UTF8&useServerPrepStmts=true&prepStmtCacheSqlLimit=256&cachePrepStmts=true&prepStmtCacheSize=256&rewriteBatchedStatements=true";
//	private static final String user = "cdb_outerroot";
//	private static final String pwd = "suL23080!@#";

    //测试0.9
    private static final String jdbcUrl = "jdbc:mysql://192.168.0.9/souliao?useUnicode=true&characterEncoding=UTF8&useServerPrepStmts=true&prepStmtCacheSqlLimit=256&cachePrepStmts=true&prepStmtCacheSize=256&rewriteBatchedStatements=true";
    private static final String user = "sl";
    private static final String pwd = "sl321";


    static DruidPlugin dpWs;
    static ActiveRecordPlugin arpWs;

    public static void initDb() {
        if (dpWs != null) {
            return;
        }
        // DruidPlugin
        dpWs = new DruidPlugin(jdbcUrl, user, pwd);
        dpWs.setDriverClass("com.mysql.jdbc.Driver");
        dpWs.start();

        dpWs.addFilter(new StatFilter());
        WallFilter wall = new WallFilter();
        wall.setDbType("mysql");
        dpWs.addFilter(wall);
        // 设置大小写不敏感
        // arpWs.setContainerFactory(new CaseInsensitiveContainerFactory());

        // ActiveRecordPlugin
        if (null == arpWs)
            arpWs = new ActiveRecordPlugin("souliao_tsf", dpWs);
        arpWs.addMapping(ProductRankingModelNews.table_name, "id", ProductRankingModelNews.class);
        arpWs.start();
//		System.out.println("................souliao数据库初始化成功.............user="+user);
        // 所有的库表和JavaBean映射配置放到MappingKit
    }

}
