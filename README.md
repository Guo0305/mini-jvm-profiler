jvm性能监控数据，用于实时采集jvm进程cpu、内存、线程数等指标，通过Rest API提供查询接口

模块说明

MiniProfilerApplication – 启动类，开启定时任务

DataPoint – 数据实体（时间、CPU、内存、线程数）

RingBufferStorage – 环形队列存储，容量 100，线程安全

AlertService – 超过阈值（CPU 80% / 内存 500MB）打印告警

ProfilerService – 每 2 秒通过 JMX 采集指标并存储

ProfilerController – REST 控制器，提供数据查询接口
