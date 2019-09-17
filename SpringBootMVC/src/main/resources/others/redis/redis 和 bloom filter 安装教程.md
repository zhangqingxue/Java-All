# redis 和 bloom filter 安装教程

## 介绍

####官方网站： https://redis.io

####中文官网：http://www.redis.cn

####关于redis的学习都在redis官网上。


### redis搭建
#####redis下载方式：/images/redis/redis下载方式.png
````
yum install wget

wget http://download.redis.io/releases/redis-5.0.5.tar.gz

tar zxvf redis-5.0.5.tar.gz

cd redis-src

看README.md

make 
yum install  gcc  (如果linux没有gcc情况下需要下载)
(make distclean 不需要，当需要清除make的文件时使用)

make

cd src   （生成了可执行程序）

make install PREFIX=/usr/local/redis

vi /etc/profile

...export  REDIS_HOME=/usr/local/redis
...export PATH=$PATH:$REDIS_HOME/bin

source /etc/profile

echo $PATH (查看导入的环境变量是否成功)

cd utils

./install_server.sh  （可以执行一次或多次）
    a)  一个物理机中可以有多个redis实例（进程），通过port区分
    b)  可执行程序就一份在目录，但是内存中未来的多个实例需要各自的配置文件，持久化目录等资源
    c)  service   redis_6379  start/stop/stauts   --> linux  /etc/init.d/**** 

redis-server --port 6666 注意：这种启动方式启动，所在位置即是数据目录。

````

redis顺序性：每个连接内的命令执行是顺序的。

### redis bloom filter 布隆过滤器
````
redis.io官网 modules栏下找到 RedisBloom 模块

访问RedisBloom的github
      https://github.com/RedisBloom/RedisBloom
wget https://github.com/RedisBloom/RedisBloom/archive/master.zip

unzip master.zip

make

cp bloom.so  /usr/loca/redis （任意你喜欢的目录）

redis-server --loadmodule  /opt/mashibing/redis5/redisbloom.so

redis-cli  

...bf.add  ooxx  abc
...bf.exits   abc
...bf.exits  sdfsdf
...cf.add   #  布谷鸟过滤器

````