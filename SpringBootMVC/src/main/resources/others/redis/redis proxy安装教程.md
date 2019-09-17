# redis proxy 相关

## 1. twitter twemproxy 

#### github ： https://github.com/twitter/twemproxy

在官方 github's README.md 上有详细的关于 twemproxy 的安装、特性、配置文件配置的介绍

### twemproxy 搭建


````

git clone https://github.com/twitter/twemproxy.git

yum install automake libtool -y

autoreconf -fvi

./configure

make (产生可执行文件 src/nutcracker)

cp twemproxy/scripts/nutcracker.init /etc/init.d/nutcracker

nutcracker.init 有如下配置：
     OPTIONS="-d -c /etc/nutcracker/nutcracker.yml"
     prog="nutcracker"

根据配置信息：
     1.将 nutcracker.yml 拷贝到 指定 /etc/nutcracker/ 目录下
     2.将 可执行文件 nutcracker 拷贝到 bin 目录下

cp twemproxy/conf/*  /etc/nutcracker/

cp twemproxy/src/nutcracker /usr/bin/
chmod +x nutcracker (给予可执行权限,否则无法执行)

修改配置文件内容： nutcracker.yml 修改前记得备份

cp nutcracker.yml nutcracker.yml.bak

配置示例：(github 上 Configuration栏 有详细配置介绍)

alpha: # 随意
  listen: 127.0.0.1:22121 # 监听端口号
  hash: fnv1a_64 # hash算法
  distribution: ketama # 分布式 密钥分发模式
  auto_eject_hosts: false # 用于控制服务器在连续失败server_failure_limit次数时是否将死服务器弹出哈希环
  redis: true # 是否使用redis true = redis, false = memcached
  server_retry_timeout: 2000 # 确保我们不会错误地将服务器标记为永久死亡，尤其是当故障非常短暂时
  server_failure_limit: 1 # 控制弹性与永久性和瞬态故障之间的权衡。
  servers: # 真实redis服务器地址
   - 127.0.0.1:6379:1
   - 127.0.0.1:6379:1


````

&nbsp;&nbsp;&nbsp;


## 2. predixy （一款高性能全特征redis代理，支持redis-sentinel和redis-cluster）

#### github ： https://github.com/joyieldInc/predixy

在官方 github's README.md 上有详细的关于 predixy 的特性、配置文件配置、使用的介绍

### predixy 搭建


````

wget https://github.com/joyieldInc/predixy/releases/download/1.0.5/predixy-1.0.5-bin-amd64-linux.tar.gz

tar -zxvf predixy-1.0.5-bin-amd64-linux.tar.gz 

配置文件设置：

predixy的配置类似redis, 具体配置项的含义在配置文件里有详细解释，请参考下列配置文件：

    predixy.conf，整体配置文件，会引用下面的配置文件
    cluster.conf，用于Redis Cluster时，配置后端redis信息
    sentinel.conf，用于Redis Sentinel时，配置后端redis信息
    auth.conf，访问权限控制配置，可以定义多个验证密码，可每个密码指定读、写、管理权限，以及定义可访问的健空间
    dc.conf，多数据中心支持，可以定义读写分离规则，读流量权重分配
    latency.conf， 延迟监控规则定义，可以指定需要监控的命令以及延时时间间隔
    
提供这么多配置文件实际上是按功能分开了，所有配置都可以写到一个文件里，也可以写到多个文件里然后在主配置文件里引用进来。

Examples:

    SentinelServerPool {
        Databases 16
        Hash crc16
        HashTag "{}"
        Distribution modula
        MasterReadPriority 60
        StaticSlaveReadPriority 50
        DynamicSlaveReadPriority 50
        RefreshInterval 1
        ServerTimeout 1
        ServerFailureLimit 10
        ServerRetryTimeout 1
        KeepAlive 120
        Sentinels {
            + 10.2.2.2:7500
            + 10.2.2.3:7500
            + 10.2.2.4:7500
        }
        Group shard001 {
        }
        Group shard002 {
        }
    }



````

&nbsp;&nbsp;&nbsp;


## 3. redis-culster （官方原生redis-cluster）


### redis-culster 搭建


````

# 开启集群相关配置 修改所有redis实例对应的6379.conf配置如下：

    cluster-enabled yes
    cluster-config-file nodes-6379 (6379区分集群中的其他).conf
    cluster-node-timeout 15000

redis-cli --cluster  create  127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384  --cluster-replicas 1



错误解决：

    Node 127.0.0.1:6379 is not empty > redis中有数据。导致无法集群,或者已经分片好了。

    Not all 16384 slots are covered by nodes. > redis节点分片错误
        redis-cli --cluster check 127.0.0.1:6379 (逐个检查每个redis)
        redis-cli --cluster fix 127.0.0.1:6380 (修复有问题的redis node)


查看主从关系脚本： get_master_slave.sh

    cmd="redis-cli -c -h $1 -p $2"
    master_id=(`$cmd cluster nodes | grep master | awk '{print $1}'`)
    master_ip_port=(`$cmd cluster nodes | grep master | awk '$2 ~ /@.*/{gsub("@.*","",$2);print $2}'`)
    
    len=${#master_id[@]}
    echo "master   ->   slave"
    for ((i=0;i<=$len-1;i++));
    do
    echo -n "${master_ip_port[$i]} ->  "
    echo `$cmd cluster slaves ${master_id[$i]} | awk '$2 ~ /@.*/{gsub("@.*","",$2);print $2}'` 
    done

````
