<img width="843" height="504" alt="image" src="https://github.com/user-attachments/assets/c76c54aa-23ee-4d89-8c36-b942d3790a9f" />jvm监控工具，实时采集jvm进程的cpu、内存、线程等，并通过REST APP 提供查询接口。
jvm性能监控数据，用于实时采集jvm进程cpu、内存、线程数等指标，通过Rest API提供查询接口
启动类
MiniProfilerApplication （启动入口）
@SpringBootApplication 自动配置spring环境
@EnableScheduling 开启定时任务功能 让ProfilerService中的@Scheduled方法能定期执行
使用main方法，启动整个程序
定义实体类
DataPoint（简单的pojo,封装采集到的指标并自动转换为json格式返回给前端）
定义属性：timestamp采集本地日期时间、cpuLoad cpu使用率（0~1）、headMemoryUsed内存使用量、threadCount当前jvm线程数
最后有创建对象的构造方法和getter方法用于获取数据
储存类
RingButterStorage(使用环形队列的方式)环形队列存储优点：避免内存的无限增长，当数据超过100条时，自动覆盖最早的记录。更适合高频采集
用Linkedlist作为底层容器
用synchronized修饰，保证多线程环境下的数据一致性
add()方法检查容器，超出则删除开始元素。
告警服务
AlerService(当负载过高，会输出警告)
检查采集的数据是否超过阈值（cpu>80%/内存>500MB）,超过则输出警告。实际可与微信，邮件联系等（复杂）
控制输出类
ProfilerController(Rest控制器)对外提供http接口，让用户监控数据
浏览器访问localhost:8080/api/profiler/all是所有数据 localhost:8080/api/profiler/recent(默认是10条)
【因调试代码时有错误出现故有测试接口的/test】
