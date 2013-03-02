shorturl短地址应用
==================

* dependency： 

    
   >  commons-dbcp-1.4.jar
    
   > commons-pool-1.6.jar
    
   > mysql-connector-java-5.1.23-bin.jar
    
   > urlrewrite-3.2.0.jar
    		 
* notice:需把这四个jar导入到classpath中（即referenced libraries）

* 连接池采用dbcp，配置采用硬编码（info.superalsrk.utils.JdbcUtils）,没有采用properties

* 数据库采用myql5，所用表结构为

    
   > create table mappedurl (
    
   > shortcode char(6) not null primary key,
    
   > longurl text not null)
    
* 程序通过使用urlrewrite过滤器来实现url伪静态化技术