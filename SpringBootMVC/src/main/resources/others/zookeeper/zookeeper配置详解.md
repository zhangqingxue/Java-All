

```

# The number of milliseconds of each tick
# 通信心跳时间 
# 客户端与服务器或者服务器与服务器之间维持心跳，也就是每个tickTime时间就会发送一次心跳。
# 通过心跳不仅能够用来监听机器的工作状态，还可以通过心跳来控制Flower跟Leader的通信时间，默认情况下FL的会话时常是心跳间隔的两倍

tickTime=2000

# The number of ticks that the initial
# synchronization phase can take
# 集群中的follower服务器(F)与leader服务器(L)之间初始连接时能容忍的最多心跳数（tickTime的数量）= initLimit * tickTime。

initLimit=10


# The number of ticks that can pass between
# sending a request and getting an acknowledgement
# 集群中flower服务器（F）跟leader（L）服务器之间的请求和答应最多能容忍的心跳数 = syncLimit * tickTime。   

syncLimit=5


# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just
# example sakes.
# 该属性对应的目录是用来存放myid信息跟一些版本，日志，跟服务器唯一的ID信息等。

dataDir=/var/data/zookeeper


# the port at which the clients will connect
# 对外端口号

clientPort=2181


# the maximum number of client connections.
# increase this if you need to handle more clients
#最大允许连接数

#maxClientCnxns=60


# Be sure to read the maintenance section of the
# administrator guide before turning on autopurge.
#
# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
#
# The number of snapshots to retain in dataDir
#autopurge.snapRetainCount=3
# Purge task interval in hours
# Set to "0" to disable auto purge feature
#autopurge.purgeInterval=1

# 设置集群

server.6=172.16.94.206:2888:3888
server.7=172.16.94.207:2888:3888
server.8=172.16.94.208:2888:3888
server.9=172.16.94.209:2888:3888




```