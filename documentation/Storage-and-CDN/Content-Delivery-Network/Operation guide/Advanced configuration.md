# 高级配置
点击【域名列表】--选择加速域名--【高级配置】

## 过滤参数

开启后，CDN将忽略请求URL中 "?" 之后的参数。

## range回源

range回源是指客户端向源站请求时的内容为部分，且携带请求内容的字节范围，range回源可减少回源流量消耗，并且提升资源响应时间。

开启后，需要源站支持range请求。