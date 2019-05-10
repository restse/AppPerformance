# AppPerformance
#### App启动优化 
- 1.伪优化：闪屏页Theme设置
- 2.异步延迟懒加载
#### 布局优化
- 1.AsyncLayoutInflater [Android AsyncLayoutInflater](https://www.jianshu.com/p/f0c0eda06ae4)
- 2.转换为Java文件 [掌阅X2C](https://github.com/iReaderAndroid/X2C)
- 3.多使用include merge ConstraintLayout 减少背景设置 减少布局嵌套
#### 内存优化
- 内存抖动：锯齿状 GC导致卡顿
- 内存泄露：可用内存减少 频繁GC
- 内存溢出：OOM、程序异常
- 工具：Memory Profiler 、Memory Analyzer 、LeakCanary
- 内存泄漏 之 MAT工具 [MAT](https://www.eclipse.org/mat/downloads.php)
- ARTHook检测不合理图片 [AOP Hook](https://github.com/tiann/epic)
#### 电量优化
- 工具：[Battery Historian](https://github.com/google/battery-historian)


