# zookeeper 安装教程


#### 官网 ： http://zookeeper.apache.org


````

wget https://www-eu.apache.org/dist/zookeeper/zookeeper-3.4.14/zookeeper-3.4.14.tar.gz

tar -zxvf zookeeper-3.4.14.tar.gz

vi /etc/profile 设置环境变量
       export ZK_HOME=/usr/local/zookeeper-3.4.14
       export PATH=$PATH:$ZK_HOME/bin

cd /usr/local/zookeeper-3.4.14/conf 修改配置文件

vi zoo.cfg (将zoo_sample.cfg 重命名)

    dataDir=/var/data/zookeeper
    server.6=172.16.94.206:2888:3888
    server.7=172.16.94.207:2888:3888
    server.8=172.16.94.208:2888:3888
    server.9=172.16.94.209:2888:3888

wq

mkdir -p /var/data/zookeepr

echo 6 > /var/data/zookeeper/myid

zkServer.sh start

netstat -natp | egrep  '(2888|3888)' 

````
