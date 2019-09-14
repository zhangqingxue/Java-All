##LVS详解

```
LVS：

node01:
	ifconfig  eth0:8 192.168.150.100/24
node02~node03:
	1)修改内核：
		echo 1  >  /proc/sys/net/ipv4/conf/ens33/arp_ignore 
		echo 1  >  /proc/sys/net/ipv4/conf/all/arp_ignore 
		echo 2 > /proc/sys/net/ipv4/conf/ens33/arp_announce 
		echo 2 > /proc/sys/net/ipv4/conf/all/arp_announce 
	2）设置隐藏的vip：
		ifconfig  lo:3  192.168.150.100  netmask 255.255.255.255
		
RS中的服务：
node02~node03:
	yum install httpd -y
	service httpd start
	vi   /var/www/html/index.html
		from 192.168.150.1x

LVS服务配置
node01:
		yum install ipvsadm 
	ipvsadm -A  -t  192.168.199.110:80  -s rr
	ipvsadm -a  -t 192.168.199.110:80  -r  192.168.199.140 -g -w 1
	ipvsadm -a  -t 192.168.199.110:80  -r  192.168.199.141 -g -w 1
	ipvsadm -ln

验证：
	浏览器访问  192.168.150.100   看到负载  疯狂F5
	node01：
		netstat -natp   结论看不到socket连接
	node02~node03:
		netstat -natp   结论看到很多的socket连接
	node01:
		ipvsadm -lnc    查看偷窥记录本
		TCP 00:57  FIN_WAIT    192.168.150.1:51587 192.168.150.100:80 192.168.150.12:80
		FIN_WAIT： 连接过，偷窥了所有的包
		SYN_RECV： 基本上lvs都记录了，证明lvs没事，一定是后边网络层出问题
	
```


1）D-NAT技术：

![1566990905473](D:\idea-workspace\project\SpringBootMVC\src\main\resources\others\assets\1566990905473.png)



2）DR技术：

![1566990972507](D:\idea-workspace\project\SpringBootMVC\src\main\resources\others\assets\1566990972507.png)



3）TUN隧道技术：

![1566991015361](D:\idea-workspace\project\SpringBootMVC\src\main\resources\others\assets\1566991015361.png)


keepalived实验：
主机： node01~node04

node01:
	ipvsadm -C
	ifconfig eth0:8 down

----------------------------
node01,node04:
	yum install keepalived ipvsadm -y
	配置：
		cd  /etc/keepalived/
		cp keepalived.conf keepalived.conf.bak
		vi keepalived.conf
			node01:
			vrrp：（虚拟路由冗余协议）
				vrrp_instance VI_1 {
					state MASTER         //  node04  BACKUP
					interface eth0
					virtual_router_id 51
					priority 100		 //	 node04	 50
					advert_int 1
					authentication {
						auth_type PASS
						auth_pass 1111
					}
					virtual_ipaddress {
						192.168.150.100/24 dev eth0 label  eth0:3
					}
				}
			virtual_server 192.168.150.100 80 {
				delay_loop 6
				lb_algo rr
				lb_kind DR
				nat_mask 255.255.255.0
				persistence_timeout 0
				protocol TCP
				real_server 192.168.150.12 80 {
					weight 1
					HTTP_GET {
						url {
						  path /
						  status_code 200
						}
						connect_timeout 3
						nb_get_retry 3
						delay_before_retry 3
					}   
				}       
				real_server 192.168.150.13 80 {
					weight 1
					HTTP_GET {
						url {
						  path /
						  status_code 200
						}
						connect_timeout 3
						nb_get_retry 3
						delay_before_retry 3
					}
				}
			scp  ./keepalived.conf  root@node04:`pwd`


--------------------------------------------------------

global_defs {
   notification_email {
     aming@aminglinux.com
   }
   notification_email_from root@aminglinux.com
   smtp_server 127.0.0.1
   smtp_connect_timeout 30
   router_id LVS_DEVEL
}

vrrp_script chk_nginx {                        检测服务是否正常
    script "/usr/local/sbin/check_ng.sh"       检测脚本位置
    interval 3
}

vrrp_instance VI_1 {                           定义master
    state MASTER                               角色
    interface ens33                            定义网卡
    virtual_router_id 51                       定义路由器id
    priority 100                               权重
    advert_int 1   
    authentication {                           认证
        auth_type PASS 
        auth_pass aminglinux>com               密码
    }
    virtual_ipaddress {                        vip定义
        192.168.188.100                        随时定义ip
    }

    track_script {
        chk_nginx
    }

}
	