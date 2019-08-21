##centos7下的Nginx启动脚本

```shell
[root@localhost ~]# vim /lib/systemd/system/nginx.service
[Unit]
Description=nginx
After=network.target
 
[Service]
Type=forking
ExecStart=/usr/local/nginx/sbin/nginx
ExecReload=/usr/local/nginx/sbin/nginx -s reload
ExecStop=/usr/local/nginx/sbin/nginx -s quit
PrivateTmp=true
#EnvironmentFile=/etc/sysconfig/rdisc
#ExecStart=/sbin/rdisc $RDISCOPTS
 
[Install]
WantedBy=multi-user.target

```

##说明

[Unit]:服务的说明

Description:描述服务

After:描述服务类别

[Service]服务运行参数的设置

Type=forking是后台运行的形式

ExecStart为服务的具体运行命令

ExecReload为重启命令

ExecStop为停止命令

PrivateTmp=True表示给服务分配独立的临时空间


注意：[Service]的启动、重启、停止命令全部要求使用绝对路径

[Install]运行级别下服务安装的相关设置，可设置为多用户，即系统运行级别为3



