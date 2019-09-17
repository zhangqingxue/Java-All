

```
# redis版本 3.2.100
 
# 如果想要使用到配置文件，Redis服务必须以配置文件的路径作为第一个参数启动。如：
./redis-server /path/to/redis.conf
 
# 单位说明:当需要指定内存大小时，可能会用到不同的单位，如1k、5GB、4M等，这里给出其单位含义：
# 指定单位是大小写不敏感。如1GB、1gB、1Gb是一样的。
# 1k => 1000 bytes
# 1kb => 1024 bytes
# 1kb => 1024 bytes
# 1m => 1000000 bytes
# 1mb => 1024*1024 bytes
# 1g => 1000000000 bytes
# 1gb => 1024*1024*1024 bytes
 
######################### 引用 #########################
 
# 不同redis server可以使用同一个模版配置作为主配置，并引用其它配置文
# 件用于本server的个性化设置include并不会被CONFIG REWRITE命令覆盖。
# 但是主配置文件的选项会被覆盖。想故意覆盖主配置的话就把include放文件前面，
# 否则最好放末尾
# include /path/to/local.conf
# include /path/to/other.conf
 
######################### 网络 #########################
 
# 不指定bind的话redis将会监听所有网络接口。这个配置是肯定需要指定的。
# Examples:
# bind 192.168.1.100 10.0.0.1
# bind 127.0.0.1 ::1
# 下面这个配置是只允许本地客户端访问。
bind 127.0.0.1
 
# 是否开启保护模式。默认开启，如果没有设置bind项的ip和redis密码的话，服务将只允许本地访问。
protected-mode yes
 
# 端口设置，默认为 6379
# 如果port设置为0 redis将不会监听tcp socket
port 6379
 
# 在高并发环境下需要一个高backlog值来避免慢客户端连接问题。注意Linux内核默默将这个值减小到/proc/sys/net/core/somaxconn的值，所以需要确认增大somaxconn和tcp_max_syn_backlog 两个值来达到需要的效果。
tcp-backlog 511
 
# 指定用来监听Unix套套接字的路径。没有默认值，没有指定的情况下Redis不会监听Unix socket
# unixsocket /tmp/redis.sock
# unixsocketperm 700
 
# 客户端空闲多少秒后关闭连接（0为不关闭）
timeout 0
 
# tcp-keepalive设置。如果非零，
# 则设置SO_KEEPALIVE选项来向空闲连接的客户端发送ACK，用途如下：
# 1）能够检测无响应的对端
# 2）让该连接中间的网络设备知道这个连接还存活
# 在Linux上，这个指定的值(单位秒)就是发送ACK的时间间隔。
# 注意：要关闭这个连接需要两倍的这个时间值。
# 在其他内核上这个时间间隔由内核配置决定
tcp-keepalive 0
 
######################### 通用 #########################
 
# 是否将Redis作为守护进程运行。如果需要的话配置成'yes'
# 注意配置成守护进程后Redis会将进程号写入文件/var/run/redis.pid
daemonize no
 
# 是否通过upstart或systemd管理守护进程。默认no没有服务监控，其它选项有upstart, systemd, auto
supervised no
 
# pid文件在redis启动时创建，退出时删除。最佳实践为配置该项。
pidfile /var/run/redis_6379.pid
 
 
# 配置日志级别。选项有debug, verbose, notice, warning
loglevel notice
 
# 日志名称。空字符串表示标准输出。注意如果redis配置为后台进程，标准输出中信息会发送到/dev/null
logfile ""
 
# 是否启动系统日志记录。
# syslog-enabled no
 
# 指定系统日志身份。
# syslog-ident redis
 
# 指定syslog设备。必须是user或LOCAL0 ~ LOCAL7之一。
# syslog-facility local0
 
# 设置数据库个数。默认数据库是 DB 0
# 可以通过SELECT where dbid is a number between 0 and 'databases'-1为每个连接使用不同的数据库。
databases 16
 
######################### 备份  #########################
# 持久化设置:
# 下面的例子将会进行把数据写入磁盘的操作:
#  900秒（15分钟）之后，且至少1次变更
#  300秒（5分钟）之后，且至少10次变更
#  60秒之后，且至少10000次变更
# 不写磁盘的话就把所有 "save" 设置注释掉就行了。
# 通过添加一条带空字符串参数的save指令也能移除之前所有配置的save指令，如: save ""
save 900 1
save 300 10
save 60 10000
 
# 默认情况下如果上面配置的RDB模式开启且最后一次的保存失败，redis 将停止接受写操作，
# 让用户知道问题的发生。如果后台保存进程重新启动工作了，redis 也将自动的允许写操作。
# 如果有其它监控方式也可关闭。
stop-writes-on-bgsave-error yes
 
# 是否在备份.rdb文件时是否用LZF压缩字符串，默认设置为yes。如果想节约cpu资源可以把它设置为no。
rdbcompression yes
 
# 因为版本5的RDB有一个CRC64算法的校验和放在了文件的末尾。这将使文件格式更加可靠,
# 但在生产和加载RDB文件时，这有一个性能消耗(大约10%)，可以关掉它来获取最好的性能。
# 生成的关闭校验的RDB文件有一个0的校验和，它将告诉加载代码跳过检查
rdbchecksum yes
 
# rdb文件名称
dbfilename dump.rdb
 
# 备份文件目录，文件名就是上面的 "dbfilename" 的值。累加文件也放这里。
# 注意你这里指定的必须是目录，不是文件名。
dir ./
 
######################### 主从同步 #########################
 
# 主从同步配置。
# 1) redis主从同步是异步的，但是可以配置在没有指定slave连接的情况下使master停止写入数据。
# 2) 连接中断一定时间内，slave可以执行部分数据重新同步。
# 3) 同步是自动的，slave可以自动重连且同步数据。
# slaveof <masterip> <masterport>
 
# master连接密码
# masterauth <master-password>
 
# 当一个slave失去和master的连接，或者同步正在进行中，slave的行为有两种可能：
# 1) 如果 slave-serve-stale-data 设置为 "yes" (默认值)，slave会继续响应客户端请求，可能是正常数据，也可能是还没获得值的空数据。
# 2) 如果 slave-serve-stale-data 设置为 "no"，slave会回复"正在从master同步（SYNC with master in progress）"来处理各种请求，除了 INFO 和 SLAVEOF 命令。
slave-serve-stale-data yes
 
# 你可以配置salve实例是否接受写操作。可写的slave实例可能对存储临时数据比较有用(因为写入salve# 的数据在同master同步之后将很容被删除)，但是如果客户端由于配置错误在写入时也可能产生一些问题。
# 从Redis2.6默认所有的slave为只读
# 注意:只读的slave不是为了暴露给互联网上不可信的客户端而设计的。它只是一个防止实例误用的保护层。
# 一个只读的slave支持所有的管理命令比如config,debug等。为了限制你可以用'rename-command'来隐藏所有的管理和危险命令来增强只读slave的安全性。
slave-read-only yes
 
# 同步策略: 磁盘或socket，默认磁盘方式
repl-diskless-sync no
 
# 如果非磁盘同步方式开启，可以配置同步延迟时间，以等待master产生子进程通过socket传输RDB数据给slave。
# 默认值为5秒，设置为0秒则每次传输无延迟。
repl-diskless-sync-delay 5
 
# slave根据指定的时间间隔向master发送ping请求。默认10秒。
# repl-ping-slave-period 10
 
# 同步的超时时间
# 1）slave在与master SYNC期间有大量数据传输，造成超时
# 2）在slave角度，master超时，包括数据、ping等
# 3）在master角度，slave超时，当master发送REPLCONF ACK pings# 确保这个值大于指定的repl-ping-slave-period，否则在主从间流量不高时每次都会检测到超时
# repl-timeout 60
 
# 是否在slave套接字发送SYNC之后禁用 TCP_NODELAY
# 如果选择yes，Redis将使用更少的TCP包和带宽来向slaves发送数据。但是这将使数据传输到slave上有延迟，Linux内核的默认配置会达到40毫秒。
# 如果选择no，数据传输到salve的延迟将会减少但要使用更多的带宽。
# 默认我们会为低延迟做优化，但高流量情况或主从之间的跳数过多时，可以设置为“yes”。
repl-disable-tcp-nodelay no
 
# 设置数据备份的backlog大小。backlog是一个slave在一段时间内断开连接时记录salve数据的缓冲，所以一个slave在重新连接时，不必要全量的同步，而是一个增量同步就足够了，将在断开连接的这段# 时间内把slave丢失的部分数据传送给它。
# 同步的backlog越大，slave能够进行增量同步并且允许断开连接的时间就越长。
# backlog只分配一次并且至少需要一个slave连接。
# repl-backlog-size 1mb
 
# 当master在一段时间内不再与任何slave连接，backlog将会释放。以下选项配置了从最后一个
# slave断开开始计时多少秒后，backlog缓冲将会释放。
# 0表示永不释放backlog
# repl-backlog-ttl 3600
 
# slave的优先级是一个整数展示在Redis的Info输出中。如果master不再正常工作了，sentinel将用它来选择一个slave提升为master。
# 优先级数字小的salve会优先考虑提升为master，所以例如有三个slave优先级分别为10，100，25，sentinel将挑选优先级最小数字为10的slave。
# 0作为一个特殊的优先级，标识这个slave不能作为master，所以一个优先级为0的slave永远不会被# sentinel挑选提升为master。
# 默认优先级为100
slave-priority 100
 
# 如果master少于N个延时小于等于M秒的已连接slave，就可以停止接收写操作。
# N个slave需要是“oneline”状态。
# 延时是以秒为单位，并且必须小于等于指定值，是从最后一个从slave接收到的ping（通常每秒发送）开始计数。
# 该选项不保证N个slave正确同步写操作，但是限制数据丢失的窗口期。
# 例如至少需要3个延时小于等于10秒的slave用下面的指令：
# min-slaves-to-write 3
# min-slaves-max-lag 10
 
# 两者之一设置为0将禁用这个功能。
# 默认 min-slaves-to-write 值是0（该功能禁用）并且 min-slaves-max-lag 值是10。
 
######################### 安全 #########################
 
# 要求客户端在处理任何命令时都要验证身份和密码。
# requirepass foobared
 
# 命令重命名
# 在共享环境下，可以为危险命令改变名字。比如，你可以为 CONFIG 改个其他不太容易猜到的名字，这样内部的工具仍然可以使用。
# 例如：
# rename-command CONFIG b840fc02d524045429941cc15f59e41cb7be6c52
# 也可以通过改名为空字符串来完全禁用一个命令
# rename-command CONFIG ""
# 请注意：改变命令名字被记录到AOF文件或被传送到从服务器可能产生问题。
 
######################### 限制 #########################
 
# 设置最多同时连接的客户端数量。默认这个限制是10000个客户端，然而如果Redis服务器不能配置
# 处理文件的限制数来满足指定的值，那么最大的客户端连接数就被设置成当前文件限制数减32（因为Redis服务器保留了一些文件描述符作为内部使用）
# 一旦达到这个限制，Redis会关闭所有新连接并发送错误'max number of clients reached'
# maxclients 10000
 
# 不要使用比设置的上限更多的内存。一旦内存使用达到上限，Redis会根据选定的回收策略（参见：maxmemmory-policy）删除key。
# 如果因为删除策略Redis无法删除key，或者策略设置为 "noeviction"，Redis会回复需要更多内存的错误信息给命令。例如，SET,LPUSH等等，但是会继续响应像Get这样的只读命令。
# 在使用Redis作为LRU缓存，或者为实例设置了硬性内存限制的时候（使用 "noeviction" 策略）的时候，这个选项通常事很有用的。
# 警告：当有多个slave连上达到内存上限时，master为同步slave的输出缓冲区所需内存不计算在使用内存中。这样当移除key时，就不会因网络问题 / 重新同步事件触发移除key的循环，反过来slaves的输出缓冲区充满了key被移除的DEL命令，这将触发删除更多的key，直到这个数据库完全被清空为止。
# 总之，如果你需要附加多个slave，建议你设置一个稍小maxmemory限制，这样系统就会有空闲的内存作为slave的输出缓存区(但是如果最大内存策略设置为"noeviction"的话就没必要了)
# maxmemory <bytes>
 
# 最大内存策略：如果达到内存限制了，Redis如何选择删除key。
# volatile-lru -> 根据LRU算法删除设置过期时间的key
# allkeys-lru -> 根据LRU算法删除任何key
# volatile-random -> 随机移除设置过过期时间的key
# allkeys-random -> 随机移除任何key
# volatile-ttl -> 移除即将过期的key(minor TTL)
# noeviction -> 不移除任何key，只返回一个写错误
# 注意：对所有策略来说，如果Redis找不到合适的可以删除的key都会在写操作时返回一个错误。# 目前为止涉及的命令：set setnx setex append incr decr rpush lpush rpushx lpushx linsert lset rpoplpush sadd sinter sinterstore sunion sunionstore sdiff sdiffstore zadd zincrby zunionstore zinterstore hset hsetnx hmset hincrby incrby decrby getset mset msetnx exec sort
# 默认策略:
# maxmemory-policy noeviction
 
# LRU和最小TTL算法的实现都不是很精确，但是很接近（为了省内存），所以你可以用样本量做检测。 例如：默认Redis会检查3个key然后取最旧的那个，你可以通过下面的配置指令来设置样本的个数。
# 默认值为5，数字越大结果越精确但是会消耗更多CPU。
# maxmemory-samples 5
 
######################### APPEND ONLY MODE #########################
 
# 默认情况下，Redis是异步的把数据导出到磁盘上。这种模式在很多应用里已经足够好，但Redis进程出问题或断电时可能造成一段时间的写操作丢失(这取决于配置的save指令)。
# AOF是一种提供了更可靠的替代持久化模式，例如使用默认的数据写入文件策略（参见后面的配置）。
# 在遇到像服务器断电或单写情况下Redis自身进程出问题但操作系统仍正常运行等突发事件时，Redis能只丢失1秒的写操作。
# AOF和RDB持久化能同时启动并且不会有问题。
# 如果AOF开启，那么在启动时Redis将加载AOF文件，它更能保证数据的可靠性。
appendonly no
 
# AOF文件名（默认："appendonly.aof"）
appendfilename "appendonly.aof"
 
# fsync() 系统调用告诉操作系统把数据写到磁盘上，而不是等更多的数据进入输出缓冲区。
# 有些操作系统会真的把数据马上刷到磁盘上；有些则会尽快去尝试这么做。
# Redis支持三种不同的模式：
# no：不要立刻刷，只有在操作系统需要刷的时候再刷。比较快。
# always：每次写操作都立刻写入到aof文件。慢，但是最安全。
# everysec：每秒写一次。折中方案。
# 默认的 "everysec" 通常来说能在速度和数据安全性之间取得比较好的平衡。
# appendfsync always
appendfsync everysec
# appendfsync no
 
# 如果AOF的同步策略设置成 "always" 或者 "everysec"，并且后台的存储进程（后台存储或写入AOF 日志）会产生很多磁盘I/O开销。某些Linux的配置下会使Redis因为 fsync()系统调用而阻塞很久。
# 注意，目前对这个情况还没有完美修正，甚至不同线程的 fsync() 会阻塞我们同步的write(2)调用。
# 为了缓解这个问题，可以用下面这个选项。它可以在 BGSAVE 或 BGREWRITEAOF 处理时阻止fsync()。
# 这就意味着如果有子进程在进行保存操作，那么Redis就处于"不可同步"的状态。
# 这实际上是说，在最差的情况下可能会丢掉30秒钟的日志数据。（默认Linux设定）
# 如果把这个设置成"yes"带来了延迟问题，就保持"no"，这是保存持久数据的最安全的方式。
no-appendfsync-on-rewrite no
 
# 自动重写AOF文件。如果AOF日志文件增大到指定百分比，Redis能够通过 BGREWRITEAOF 自动重写AOF日志文件。# 工作原理：Redis记住上次重写时AOF文件的大小（如果重启后还没有写操作，就直接用启动时的AOF大小）
# 这个基准大小和当前大小做比较。如果当前大小超过指定比例，就会触发重写操作。你还需要指定被重写日志的最小尺寸，这样避免了达到指定百分比但尺寸仍然很小的情况还要重写。
# 指定百分比为0会禁用AOF自动重写特性。
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
 
# 如果设置为yes，如果一个因异常被截断的AOF文件被redis启动时加载进内存，redis将会发送日志通知用户。如果设置为no，erdis将会拒绝启动。此时需要用"redis-check-aof"工具修复文件。
aof-load-truncated yes
######################### LUA脚本  #########################
 
# Lua 脚本的最大执行时间，毫秒为单位
lua-time-limit 5000
 
######################### 集群 #########################
 
# 只有开启了以下选项，redis才能成为集群服务的一部分
# cluster-enabled yes
 
# 配置redis自动生成的集群配置文件名。确保同一系统中运行的各redis实例该配置文件不要重名。
# cluster-config-file nodes-6379.conf
 
# 集群节点超时毫秒数。超时的节点将被视为不可用状态。
# cluster-node-timeout 15000
 
# 如果数据太旧，集群中的不可用master的slave节点会避免成为备用master。如果slave和master失联时间超过:(node-timeout * slave-validity-factor) + repl-ping-slave-period则不会被提升为master。
# 如node-timeout为30秒，slave-validity-factor为10, 默认default repl-ping-slave-period为10秒,失联时间超过310秒slave就不会成为master。
# 较大的slave-validity-factor值可能允许包含过旧数据的slave成为master，同时较小的值可能会阻止集群选举出新master。
#为了达到最大限度的高可用性，可以设置为0，即slave不管和master失联多久都可以提升为master
# cluster-slave-validity-factor 10
 
# 只有在之前master有其它指定数量的工作状态下的slave节点时，slave节点才能提升为master。默认为1（即该集群至少有3个节点，1 master＋2 slaves，master宕机，仍有另外1个slave的情况下其中1个slave可以提升）
# 测试环境可设置为0，生成环境中至少设置为1
# cluster-migration-barrier 1
 
# 默认情况下如果redis集群如果检测到至少有1个hash slot不可用，集群将停止查询数据。
# 如果所有slot恢复则集群自动恢复。
# 如果需要集群部分可用情况下仍可提供查询服务，设置为no。
# cluster-require-full-coverage yes
 
######################### 慢查询日志 #########################
 
# 慢查询日志，记录超过多少微秒的查询命令。查询的执行时间不包括客户端的IO执行和网络通信时间，只是查询命令执行时间。
# 1000000等于1秒，设置为0则记录所有命令
slowlog-log-slower-than 10000
 
# 记录大小，可通过SLOWLOG RESET命令重置
slowlog-max-len 128
 
 
######################### 旧监控系统 #########################
# redis延时监控系统在运行时会采样一些操作，以便收集可能导致延时的数据根源。
# 通过 LATENCY命令 可以打印一些图样和获取一些报告，方便监控
# 这个系统仅仅记录那个执行时间大于或等于预定时间（毫秒）的操作,
# 这个预定时间是通过latency-monitor-threshold配置来指定的，
# 当设置为0时，这个监控系统处于停止状态
latency-monitor-threshold 0
 
######################### 事件通知 #########################
# K 	键空间通知，所有通知以 __keyspace@<db>__ 为前缀
# E 	键事件通知，所有通知以 __keyevent@<db>__ 为前缀
# g 	DEL 、 EXPIRE 、 RENAME 等类型无关的通用命令的通知
# $ 	字符串命令的通知
# l 	列表命令的通知
# s 	集合命令的通知
# h 	哈希命令的通知
# z 	有序集合命令的通知
# x 	过期事件：每当有过期键被删除时发送
# e 	驱逐(evict)事件：每当有键因为 maxmemory 政策而被删除时发送
# A 	参数 g$lshzxe 的别名
#  Example: to enable list and generic events, from the point of view of the
#           event name, use:
#
#  notify-keyspace-events Elg
#
#  Example 2: to get the stream of the expired keys subscribing to channel
#             name __keyevent@0__:expired use:
#
#  notify-keyspace-events Ex
# Redis能通知 Pub/Sub 客户端关于键空间发生的事件，默认关闭
notify-keyspace-events ""
 
############################### 高级配置 ###############################
 
# 当hash只有少量的entry时，并且最大的entry所占空间没有超过指定的限制时，会用一种节省内存的
# 数据结构来编码。可以通过下面的指令来设定限制
hash-max-ziplist-entries 512
hash-max-ziplist-value 64
 
 
# 当取正值的时候，表示按照数据项个数来限定每个quicklist节点上的ziplist长度。比如，当这个参数配置
# 成5的时候，表示每个quicklist节点的ziplist最多包含5个数据项。
# 当取负值的时候，表示按照占用字节数来限定每个quicklist节点上的ziplist长度。这时，它只能取-1到-5
# 这五个值，每个值含义如下：
#    -5: 每个quicklist节点上的ziplist大小不能超过64 Kb。（注：1kb => 1024 bytes）
#    -4: 每个quicklist节点上的ziplist大小不能超过32 Kb。
#    -3: 每个quicklist节点上的ziplist大小不能超过16 Kb。
#    -2: 每个quicklist节点上的ziplist大小不能超过8 Kb。（-2是Redis给出的默认值）
#    -1: 每个quicklist节点上的ziplist大小不能超过4 Kb。
list-max-ziplist-size -2
 
# 这个参数表示一个quicklist两端不被压缩的节点个数。
# 注：这里的节点个数是指quicklist双向链表的节点个数，而不是指ziplist里面的数据项个数。
# 实际上，一个quicklist节点上的ziplist，如果被压缩，就是整体被压缩的。
# 参数list-compress-depth的取值含义如下：
#    0: 是个特殊值，表示都不压缩。这是Redis的默认值。
#    1: 表示quicklist两端各有1个节点不压缩，中间的节点压缩。
#    2: 表示quicklist两端各有2个节点不压缩，中间的节点压缩。
#    3: 表示quicklist两端各有3个节点不压缩，中间的节点压缩。
#    依此类推…
# 由于0是个特殊值，很容易看出quicklist的头节点和尾节点总是不被压缩的，以便于在表的两端进行快速存取。
list-compress-depth 0
 
 
# set有一种特殊编码的情况：当set数据全是十进制64位有符号整型数字构成的字符串时。
# 下面这个配置项就是用来设置set使用这种编码来节省内存的最大长度。
set-max-intset-entries 512
 
# 与hash和list相似，有序集合也可以用一种特别的编码方式来节省大量空间。
# 这种编码只适合长度和元素都小于下面限制的有序集合
zset-max-ziplist-entries 128
zset-max-ziplist-value 64
 
# HyperLogLog稀疏结构表示字节的限制。该限制包括
# 16个字节的头。当HyperLogLog使用稀疏结构表示
# 这些限制，它会被转换成密度表示。
# 值大于16000是完全没用的，因为在该点
# 密集的表示是更多的内存效率。
# 建议值是3000左右，以便具有的内存好处, 减少内存的消耗
hll-sparse-max-bytes 3000
 
# 启用哈希刷新，每100个CPU毫秒会拿出1个毫秒来刷新Redis的主哈希表（顶级键值映射表）
activerehashing yes
 
# 客户端的输出缓冲区的限制，可用于强制断开那些因为某种原因从服务器读取数据的速度不够快的客户端
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit slave 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60
 
# 默认情况下，“hz”的被设定为10。提高该值将在Redis空闲时使用更多的CPU时，但同时当有多个key
# 同时到期会使Redis的反应更灵敏，以及超时可以更精确地处理
hz 10
 
# 当一个子进程重写AOF文件时，如果启用下面的选项，则文件每生成32M数据会被同步
aof-rewrite-incremental-fsync yes

```

###

```
# redis版本 5.0.0
 
# 如果想要使用到配置文件，Redis服务必须以配置文件的路径作为第一个参数启动。如：
./redis-server /path/to/redis.conf
 
# 单位说明:当需要指定内存大小时，可能会用到不同的单位，如1k、5GB、4M等，这里给出其单位含义：
# 指定单位是大小写不敏感。如1GB、1gB、1Gb是一样的。
# 1k => 1000 bytes
# 1kb => 1024 bytes
# 1m => 1000000 bytes
# 1mb => 1024*1024 bytes
# 1g => 1000000000 bytes
# 1gb => 1024*1024*1024 bytes
 
######################### 引用 #########################
 
# 不同redis server可以使用同一个模版配置作为主配置，并引用其它配置文
# 件用于本server的个性化设置include并不会被CONFIG REWRITE命令覆盖。
# 但是主配置文件的选项会被覆盖。想故意覆盖主配置的话就把include放文件前面，
# 否则最好放末尾
# include /path/to/local.conf
# include /path/to/other.conf
 
######################### 模块 ######################### 
# 启动时加载模块。如果服务器无法加载模块,则会中止。可以使用多个loadmodule指令。
# loadmodule /path/to/my_module.so
# loadmodule /path/to/other_module.so
 
######################### 网络 #########################
 
# 不指定bind的话redis将会监听所有网络接口。这个配置是肯定需要指定的。
# Examples:
# bind 192.168.1.100 10.0.0.1
# bind 127.0.0.1 ::1
# 下面这个配置是只允许本地客户端访问。
bind 127.0.0.1
 
# 是否开启保护模式。默认开启，如果没有设置bind项的ip和redis密码的话，服务将只允许本地访问。
protected-mode yes
 
# 端口设置，默认为 6379
# 如果port设置为0 redis将不会监听tcp socket
port 6379
 
# 在高并发环境下需要一个高backlog值来避免慢客户端连接问题。注意Linux内核默默将这个值减小到/proc/sys/net/core/somaxconn的值，所以需要确认增大somaxconn和tcp_max_syn_backlog 两个值来达到需要的效果。
tcp-backlog 511
 
# 指定用来监听Unix套套接字的路径。没有默认值，没有指定的情况下Redis不会监听Unix socket
# unixsocket /tmp/redis.sock
# unixsocketperm 700
 
# 客户端空闲多少秒后关闭连接（0为不关闭）
timeout 0
 
# tcp-keepalive设置。如果非零，
# 则设置SO_KEEPALIVE选项来向空闲连接的客户端发送ACK，用途如下：
# 1）能够检测无响应的对端
# 2）让该连接中间的网络设备知道这个连接还存活
# 在Linux上，这个指定的值(单位秒)就是发送ACK的时间间隔。
# 注意：要关闭这个连接需要两倍的这个时间值。
# 在其他内核上这个时间间隔由内核配置决定
tcp-keepalive 300
 
######################### 通用 #########################
 
# 是否将Redis作为守护进程运行。如果需要的话配置成'yes'
# 注意配置成守护进程后Redis会将进程号写入文件/var/run/redis.pid
daemonize no
 
# 是否通过upstart或systemd管理守护进程。默认no没有服务监控，其它选项有upstart, systemd, auto
supervised no
 
# pid文件在redis启动时创建，退出时删除。最佳实践为配置该项。
pidfile /var/run/redis_6379.pid
 
 
# 配置日志级别。选项有debug, verbose, notice, warning
loglevel notice
 
# 日志名称。空字符串表示标准输出。注意如果redis配置为后台进程，标准输出中信息会发送到/dev/null
logfile ""
 
# 是否启动系统日志记录。
# syslog-enabled no
 
# 指定系统日志身份。
# syslog-ident redis
 
# 指定syslog设备。必须是user或LOCAL0 ~ LOCAL7之一。
# syslog-facility local0
 
# 设置数据库个数。默认数据库是 DB 0
# 可以通过SELECT where dbid is a number between 0 and 'databases'-1为每个连接使用不同的数据库。
databases 16
 
# redis启动时是否显示Logo
always-show-logo yes
######################### 备份  #########################
# 持久化设置:
# 下面的例子将会进行把数据写入磁盘的操作:
#  900秒（15分钟）之后，且至少1次变更
#  300秒（5分钟）之后，且至少10次变更
#  60秒之后，且至少10000次变更
# 不写磁盘的话就把所有 "save" 设置注释掉就行了。
# 通过添加一条带空字符串参数的save指令也能移除之前所有配置的save指令，如: save ""
save 900 1
save 300 10
save 60 10000
 
# 默认情况下如果上面配置的RDB模式开启且最后一次的保存失败，redis 将停止接受写操作，
# 让用户知道问题的发生。如果后台保存进程重新启动工作了，redis 也将自动的允许写操作。
# 如果有其它监控方式也可关闭。
stop-writes-on-bgsave-error yes
 
# 是否在备份.rdb文件时是否用LZF压缩字符串，默认设置为yes。如果想节约cpu资源可以把它设置为no。
rdbcompression yes
 
# 因为版本5的RDB有一个CRC64算法的校验和放在了文件的末尾。这将使文件格式更加可靠,
# 但在生产和加载RDB文件时，这有一个性能消耗(大约10%)，可以关掉它来获取最好的性能。
# 生成的关闭校验的RDB文件有一个0的校验和，它将告诉加载代码跳过检查
rdbchecksum yes
 
# rdb文件名称
dbfilename dump.rdb
 
# 备份文件目录，文件名就是上面的 "dbfilename" 的值。累加文件也放这里。
# 注意你这里指定的必须是目录，不是文件名。
dir ./
 
######################### 主从同步 #########################
 
# 主从同步配置。
# 1) redis主从同步是异步的，但是可以配置在没有指定slave连接的情况下使master停止写入数据。
# 2) 连接中断一定时间内，slave可以执行部分数据重新同步。
# 3) 同步是自动的，slave可以自动重连且同步数据。
# replicaof <masterip> <masterport>
 
# master连接密码
# masterauth <master-password>
 
# 当一个slave失去和master的连接，或者同步正在进行中，slave的行为有两种可能：
# 1) 如果 replica-serve-stale-data 设置为 "yes" (默认值)，slave会继续响应客户端请求，可能是正常数据，也可能是还没获得值的空数据。
# 2) 如果 replica-serve-stale-data 设置为 "no"，slave会回复"正在从master同步（SYNC with master in progress）"来处理各种请求，除了 INFO 和 SLAVEOF 命令。
replica-serve-stale-data yes
 
# 你可以配置salve实例是否接受写操作。可写的slave实例可能对存储临时数据比较有用(因为写入salve# 的数据在同master同步之后将很容被删除)，但是如果客户端由于配置错误在写入时也可能产生一些问题。
# 从Redis2.6默认所有的slave为只读
# 注意:只读的slave不是为了暴露给互联网上不可信的客户端而设计的。它只是一个防止实例误用的保护层。
# 一个只读的slave支持所有的管理命令比如config,debug等。为了限制你可以用'rename-command'来隐藏所有的管理和危险命令来增强只读slave的安全性。
replica-read-only yes
 
# 同步策略: 磁盘或socket，默认磁盘方式
repl-diskless-sync no
 
# 如果非磁盘同步方式开启，可以配置同步延迟时间，以等待master产生子进程通过socket传输RDB数据给slave。
# 默认值为5秒，设置为0秒则每次传输无延迟。
repl-diskless-sync-delay 5
 
# slave根据指定的时间间隔向master发送ping请求。默认10秒。
# repl-ping-replica-period 10
 
# 同步的超时时间
# 1）slave在与master SYNC期间有大量数据传输，造成超时
# 2）在slave角度，master超时，包括数据、ping等
# 3）在master角度，slave超时，当master发送REPLCONF ACK pings# 确保这个值大于指定的repl-ping-slave-period，否则在主从间流量不高时每次都会检测到超时
# repl-timeout 60
 
# 是否在slave套接字发送SYNC之后禁用 TCP_NODELAY
# 如果选择yes，Redis将使用更少的TCP包和带宽来向slaves发送数据。但是这将使数据传输到slave上有延迟，Linux内核的默认配置会达到40毫秒。
# 如果选择no，数据传输到salve的延迟将会减少但要使用更多的带宽。
# 默认我们会为低延迟做优化，但高流量情况或主从之间的跳数过多时，可以设置为“yes”。
repl-disable-tcp-nodelay no
 
# 设置数据备份的backlog大小。backlog是一个slave在一段时间内断开连接时记录salve数据的缓冲，所以一个slave在重新连接时，不必要全量的同步，而是一个增量同步就足够了，将在断开连接的这段# 时间内把slave丢失的部分数据传送给它。
# 同步的backlog越大，slave能够进行增量同步并且允许断开连接的时间就越长。
# backlog只分配一次并且至少需要一个slave连接。
# repl-backlog-size 1mb
 
# 当master在一段时间内不再与任何slave连接，backlog将会释放。以下选项配置了从最后一个
# slave断开开始计时多少秒后，backlog缓冲将会释放。
# 0表示永不释放backlog
# repl-backlog-ttl 3600
 
# slave的优先级是一个整数展示在Redis的Info输出中。如果master不再正常工作了，sentinel将用它来选择一个slave提升为master。
# 优先级数字小的salve会优先考虑提升为master，所以例如有三个slave优先级分别为10，100，25，sentinel将挑选优先级最小数字为10的slave。
# 0作为一个特殊的优先级，标识这个slave不能作为master，所以一个优先级为0的slave永远不会被# sentinel挑选提升为master。
# 默认优先级为100
slave-priority 100
 
# 如果master少于N个延时小于等于M秒的已连接slave，就可以停止接收写操作。
# N个slave需要是“oneline”状态。
# 延时是以秒为单位，并且必须小于等于指定值，是从最后一个从slave接收到的ping（通常每秒发送）开始计数。
# 该选项不保证N个slave正确同步写操作，但是限制数据丢失的窗口期。
# 例如至少需要3个延时小于等于10秒的slave用下面的指令：
# min-replicas-to-write 3
# min-replicas-max-lag 10
 
# 两者之一设置为0将禁用这个功能。
# 默认 min-replicas-to-write 值是0（该功能禁用）并且 min-replicas-max-lag 值是10。
 
# A Redis master is able to list the address and port of the attached
# replicas in different ways. For example the "INFO replication" section
# offers this information, which is used, among other tools, by
# Redis Sentinel in order to discover replica instances.
# Another place where this info is available is in the output of the
# "ROLE" command of a master.
#
# The listed IP and address normally reported by a replica is obtained
# in the following way:
#
#   IP: The address is auto detected by checking the peer address
#   of the socket used by the replica to connect with the master.
#
#   Port: The port is communicated by the replica during the replication
#   handshake, and is normally the port that the replica is using to
#   listen for connections.
#
# However when port forwarding or Network Address Translation (NAT) is
# used, the replica may be actually reachable via different IP and port
# pairs. The following two options can be used by a replica in order to
# report to its master a specific set of IP and port, so that both INFO
# and ROLE will report those values.
#
# There is no need to use both the options if you need to override just
# the port or the IP address.
#
# replica-announce-ip 5.5.5.5
# replica-announce-port 1234
 
######################### 安全 #########################
 
# 要求客户端在处理任何命令时都要验证身份和密码。
# requirepass foobared
 
# 命令重命名
# 在共享环境下，可以为危险命令改变名字。比如，你可以为 CONFIG 改个其他不太容易猜到的名字，这样内部的工具仍然可以使用。
# 例如：
# rename-command CONFIG b840fc02d524045429941cc15f59e41cb7be6c52
# 也可以通过改名为空字符串来完全禁用一个命令
# rename-command CONFIG ""
# 请注意：改变命令名字被记录到AOF文件或被传送到从服务器可能产生问题。
 
######################### 限制 #########################
 
# 设置最多同时连接的客户端数量。默认这个限制是10000个客户端，然而如果Redis服务器不能配置
# 处理文件的限制数来满足指定的值，那么最大的客户端连接数就被设置成当前文件限制数减32（因为Redis服务器保留了一些文件描述符作为内部使用）
# 一旦达到这个限制，Redis会关闭所有新连接并发送错误'max number of clients reached'
# maxclients 10000
 
# 不要使用比设置的上限更多的内存。一旦内存使用达到上限，Redis会根据选定的回收策略（参见：maxmemmory-policy）删除key。
# 如果因为删除策略Redis无法删除key，或者策略设置为 "noeviction"，Redis会回复需要更多内存的错误信息给命令。例如，SET,LPUSH等等，但是会继续响应像Get这样的只读命令。
# 在使用Redis作为LRU缓存，或者为实例设置了硬性内存限制的时候（使用 "noeviction" 策略）的时候，这个选项通常事很有用的。
# 警告：当有多个slave连上达到内存上限时，master为同步slave的输出缓冲区所需内存不计算在使用内存中。这样当移除key时，就不会因网络问题 / 重新同步事件触发移除key的循环，反过来slaves的输出缓冲区充满了key被移除的DEL命令，这将触发删除更多的key，直到这个数据库完全被清空为止。
# 总之，如果你需要附加多个slave，建议你设置一个稍小maxmemory限制，这样系统就会有空闲的内存作为slave的输出缓存区(但是如果最大内存策略设置为"noeviction"的话就没必要了)
# maxmemory <bytes>
 
# 最大内存策略：如果达到内存限制了，Redis如何选择删除key。
# volatile-lru -> 根据LRU算法删除设置过期时间的key
# allkeys-lru -> 根据LRU算法删除任何key
# volatile-random -> 随机移除设置过过期时间的key
# allkeys-random -> 随机移除任何key
# volatile-ttl -> 移除即将过期的key(minor TTL)
# noeviction -> 不移除任何key，只返回一个写错误
# 注意：对所有策略来说，如果Redis找不到合适的可以删除的key都会在写操作时返回一个错误。# 目前为止涉及的命令：set setnx setex append incr decr rpush lpush rpushx lpushx linsert lset rpoplpush sadd sinter sinterstore sunion sunionstore sdiff sdiffstore zadd zincrby zunionstore zinterstore hset hsetnx hmset hincrby incrby decrby getset mset msetnx exec sort
# 默认策略:
# maxmemory-policy noeviction
 
# LRU和最小TTL算法的实现都不是很精确，但是很接近（为了省内存），所以你可以用样本量做检测。 例如：默认Redis会检查3个key然后取最旧的那个，你可以通过下面的配置指令来设置样本的个数。
# 默认值为5，数字越大结果越精确但是会消耗更多CPU。
# maxmemory-samples 5
 
# Starting from Redis 5, by default a replica will ignore its maxmemory setting
# (unless it is promoted to master after a failover or manually). It means
# that the eviction of keys will be just handled by the master, sending the
# DEL commands to the replica as keys evict in the master side.
#
# This behavior ensures that masters and replicas stay consistent, and is usually
# what you want, however if your replica is writable, or you want the replica to have
# a different memory setting, and you are sure all the writes performed to the
# replica are idempotent, then you may change this default (but be sure to understand
# what you are doing).
#
# Note that since the replica by default does not evict, it may end using more
# memory than the one set via maxmemory (there are certain buffers that may
# be larger on the replica, or data structures may sometimes take more memory and so
# forth). So make sure you monitor your replicas and make sure they have enough
# memory to never hit a real out-of-memory condition before the master hits
# the configured maxmemory setting.
#
# replica-ignore-maxmemory yes
 
######################### APPEND ONLY MODE #########################
# Redis has two primitives to delete keys. One is called DEL and is a blocking
# deletion of the object. It means that the server stops processing new commands
# in order to reclaim all the memory associated with an object in a synchronous
# way. If the key deleted is associated with a small object, the time needed
# in order to execute the DEL command is very small and comparable to most other
# O(1) or O(log_N) commands in Redis. However if the key is associated with an
# aggregated value containing millions of elements, the server can block for
# a long time (even seconds) in order to complete the operation.
#
# For the above reasons Redis also offers non blocking deletion primitives
# such as UNLINK (non blocking DEL) and the ASYNC option of FLUSHALL and
# FLUSHDB commands, in order to reclaim memory in background. Those commands
# are executed in constant time. Another thread will incrementally free the
# object in the background as fast as possible.
#
# DEL, UNLINK and ASYNC option of FLUSHALL and FLUSHDB are user-controlled.
# It's up to the design of the application to understand when it is a good
# idea to use one or the other. However the Redis server sometimes has to
# delete keys or flush the whole database as a side effect of other operations.
# Specifically Redis deletes objects independently of a user call in the
# following scenarios:
#
# 1) On eviction, because of the maxmemory and maxmemory policy configurations,
#    in order to make room for new data, without going over the specified
#    memory limit.
# 2) Because of expire: when a key with an associated time to live (see the
#    EXPIRE command) must be deleted from memory.
# 3) Because of a side effect of a command that stores data on a key that may
#    already exist. For example the RENAME command may delete the old key
#    content when it is replaced with another one. Similarly SUNIONSTORE
#    or SORT with STORE option may delete existing keys. The SET command
#    itself removes any old content of the specified key in order to replace
#    it with the specified string.
# 4) During replication, when a replica performs a full resynchronization with
#    its master, the content of the whole database is removed in order to
#    load the RDB file just transferred.
#
# In all the above cases the default is to delete objects in a blocking way,
# like if DEL was called. However you can configure each case specifically
# in order to instead release memory in a non-blocking way like if UNLINK
# was called, using the following configuration directives:
 
lazyfree-lazy-eviction no
lazyfree-lazy-expire no
lazyfree-lazy-server-del no
replica-lazy-flush no
 
 
# 默认情况下，Redis是异步的把数据导出到磁盘上。这种模式在很多应用里已经足够好，但Redis进程出问题或断电时可能造成一段时间的写操作丢失(这取决于配置的save指令)。
# AOF是一种提供了更可靠的替代持久化模式，例如使用默认的数据写入文件策略（参见后面的配置）。
# 在遇到像服务器断电或单写情况下Redis自身进程出问题但操作系统仍正常运行等突发事件时，Redis能只丢失1秒的写操作。
# AOF和RDB持久化能同时启动并且不会有问题。
# 如果AOF开启，那么在启动时Redis将加载AOF文件，它更能保证数据的可靠性。
appendonly no
 
# AOF文件名（默认："appendonly.aof"）
appendfilename "appendonly.aof"
 
# fsync() 系统调用告诉操作系统把数据写到磁盘上，而不是等更多的数据进入输出缓冲区。
# 有些操作系统会真的把数据马上刷到磁盘上；有些则会尽快去尝试这么做。
# Redis支持三种不同的模式：
# no：不要立刻刷，只有在操作系统需要刷的时候再刷。比较快。
# always：每次写操作都立刻写入到aof文件。慢，但是最安全。
# everysec：每秒写一次。折中方案。
# 默认的 "everysec" 通常来说能在速度和数据安全性之间取得比较好的平衡。
# appendfsync always
appendfsync everysec
# appendfsync no
 
# 如果AOF的同步策略设置成 "always" 或者 "everysec"，并且后台的存储进程（后台存储或写入AOF 日志）会产生很多磁盘I/O开销。某些Linux的配置下会使Redis因为 fsync()系统调用而阻塞很久。
# 注意，目前对这个情况还没有完美修正，甚至不同线程的 fsync() 会阻塞我们同步的write(2)调用。
# 为了缓解这个问题，可以用下面这个选项。它可以在 BGSAVE 或 BGREWRITEAOF 处理时阻止fsync()。
# 这就意味着如果有子进程在进行保存操作，那么Redis就处于"不可同步"的状态。
# 这实际上是说，在最差的情况下可能会丢掉30秒钟的日志数据。（默认Linux设定）
# 如果把这个设置成"yes"带来了延迟问题，就保持"no"，这是保存持久数据的最安全的方式。
no-appendfsync-on-rewrite no
 
# 自动重写AOF文件。如果AOF日志文件增大到指定百分比，Redis能够通过 BGREWRITEAOF 自动重写AOF日志文件。# 工作原理：Redis记住上次重写时AOF文件的大小（如果重启后还没有写操作，就直接用启动时的AOF大小）
# 这个基准大小和当前大小做比较。如果当前大小超过指定比例，就会触发重写操作。你还需要指定被重写日志的最小尺寸，这样避免了达到指定百分比但尺寸仍然很小的情况还要重写。
# 指定百分比为0会禁用AOF自动重写特性。
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
 
# 如果设置为yes，如果一个因异常被截断的AOF文件被redis启动时加载进内存，redis将会发送日志通知用户。如果设置为no，erdis将会拒绝启动。此时需要用"redis-check-aof"工具修复文件。
aof-load-truncated yes
 
# [RDB file][AOF tail]
# 加载时Redis识别出AOF文件以“REDIS”开头字符串，
# 并加载带此前缀的RDB文件，然后继续加载AOF
＃ 尾巴。
aof-use-rdb-preamble yes
######################### LUA脚本 #########################
 
# Lua 脚本的最大执行时间，毫秒为单位
lua-time-limit 5000
 
######################### 集群 #########################
 
# 只有开启了以下选项，redis才能成为集群服务的一部分
# cluster-enabled yes
 
# 配置redis自动生成的集群配置文件名。确保同一系统中运行的各redis实例该配置文件不要重名。
# cluster-config-file nodes-6379.conf
 
# 集群节点超时毫秒数。超时的节点将被视为不可用状态。
# cluster-node-timeout 15000
 
# 如果数据太旧，集群中的不可用master的slave节点会避免成为备用master。如果slave和master失联时间超过:(node-timeout * slave-validity-factor) + repl-ping-slave-period则不会被提升为master。
# 如node-timeout为30秒，slave-validity-factor为10, 默认default repl-ping-slave-period为10秒,失联时间超过310秒slave就不会成为master。
# 较大的slave-validity-factor值可能允许包含过旧数据的slave成为master，同时较小的值可能会阻止集群选举出新master。
#为了达到最大限度的高可用性，可以设置为0，即slave不管和master失联多久都可以提升为master
# cluster-replica-validity-factor 10
 
# 只有在之前master有其它指定数量的工作状态下的slave节点时，slave节点才能提升为master。默认为1（即该集群至少有3个节点，1 master＋2 slaves，master宕机，仍有另外1个slave的情况下其中1个slave可以提升）
# 测试环境可设置为0，生成环境中至少设置为1
# cluster-migration-barrier 1
 
# 默认情况下如果redis集群如果检测到至少有1个hash slot不可用，集群将停止查询数据。
# 如果所有slot恢复则集群自动恢复。
# 如果需要集群部分可用情况下仍可提供查询服务，设置为no。
# cluster-require-full-coverage yes
 
# 选项设置为yes时，会阻止replicas尝试对其master在主故障期间进行故障转移
# 然而，master仍然可以执行手动故障转移,如果强制这样做的话。
# cluster-replica-no-failover no
 
########################## CLUSTER DOCKER/NAT support  ########################
 
# In certain deployments, Redis Cluster nodes address discovery fails, because
# addresses are NAT-ted or because ports are forwarded (the typical case is
# Docker and other containers).
#
# In order to make Redis Cluster working in such environments, a static
# configuration where each node knows its public address is needed. The
# following two options are used for this scope, and are:
#
# * cluster-announce-ip
# * cluster-announce-port
# * cluster-announce-bus-port
#
# Each instruct the node about its address, client port, and cluster message
# bus port. The information is then published in the header of the bus packets
# so that other nodes will be able to correctly map the address of the node
# publishing the information.
#
# If the above options are not used, the normal Redis Cluster auto-detection
# will be used instead.
#
# Note that when remapped, the bus port may not be at the fixed offset of
# clients port + 10000, so you can specify any port and bus-port depending
# on how they get remapped. If the bus-port is not set, a fixed offset of
# 10000 will be used as usually.
#
# Example:
#
# cluster-announce-ip 10.1.1.5
# cluster-announce-port 6379
# cluster-announce-bus-port 6380
 
######################### 慢查询日志 #########################
 
# 慢查询日志，记录超过多少微秒的查询命令。查询的执行时间不包括客户端的IO执行和网络通信时间，只是查询命令执行时间。
# 1000000等于1秒，设置为0则记录所有命令
slowlog-log-slower-than 10000
 
# 记录大小，可通过SLOWLOG RESET命令重置
slowlog-max-len 128
 
 
 
######################### LATENCY MONITOR #########################
# redis延时监控系统在运行时会采样一些操作，以便收集可能导致延时的数据根源。
# 通过 LATENCY命令 可以打印一些图样和获取一些报告，方便监控
# 这个系统仅仅记录那个执行时间大于或等于预定时间（毫秒）的操作,
# 这个预定时间是通过latency-monitor-threshold配置来指定的，
# 当设置为0时，这个监控系统处于停止状态
latency-monitor-threshold 0
 
 
######################### 事件通知 #########################
# K 	键空间通知，所有通知以 __keyspace@<db>__ 为前缀
# E 	键事件通知，所有通知以 __keyevent@<db>__ 为前缀
# g 	DEL 、 EXPIRE 、 RENAME 等类型无关的通用命令的通知
# $ 	字符串命令的通知
# l 	列表命令的通知
# s 	集合命令的通知
# h 	哈希命令的通知
# z 	有序集合命令的通知
# x 	过期事件：每当有过期键被删除时发送
# e 	驱逐(evict)事件：每当有键因为 maxmemory 政策而被删除时发送
# A 	参数 g$lshzxe 的别名
#  Example: to enable list and generic events, from the point of view of the
#           event name, use:
#
#  notify-keyspace-events Elg
#
#  Example 2: to get the stream of the expired keys subscribing to channel
#             name __keyevent@0__:expired use:
#
#  notify-keyspace-events Ex
# Redis能通知 Pub/Sub 客户端关于键空间发生的事件，默认关闭
notify-keyspace-events ""
 
############################### ADVANCED CONFIG ###############################
 
# 当hash只有少量的entry时，并且最大的entry所占空间没有超过指定的限制时，会用一种节省内存的
# 数据结构来编码。可以通过下面的指令来设定限制
hash-max-ziplist-entries 512
hash-max-ziplist-value 64
 
 
# 当取正值的时候，表示按照数据项个数来限定每个quicklist节点上的ziplist长度。比如，当这个参数配置
# 成5的时候，表示每个quicklist节点的ziplist最多包含5个数据项。
# 当取负值的时候，表示按照占用字节数来限定每个quicklist节点上的ziplist长度。这时，它只能取-1到-5
# 这五个值，每个值含义如下：
#    -5: 每个quicklist节点上的ziplist大小不能超过64 Kb。（注：1kb => 1024 bytes）
#    -4: 每个quicklist节点上的ziplist大小不能超过32 Kb。
#    -3: 每个quicklist节点上的ziplist大小不能超过16 Kb。
#    -2: 每个quicklist节点上的ziplist大小不能超过8 Kb。（-2是Redis给出的默认值）
#    -1: 每个quicklist节点上的ziplist大小不能超过4 Kb。
list-max-ziplist-size -2
 
# 这个参数表示一个quicklist两端不被压缩的节点个数。
# 注：这里的节点个数是指quicklist双向链表的节点个数，而不是指ziplist里面的数据项个数。
# 实际上，一个quicklist节点上的ziplist，如果被压缩，就是整体被压缩的。
# 参数list-compress-depth的取值含义如下：
#    0: 是个特殊值，表示都不压缩。这是Redis的默认值。
#    1: 表示quicklist两端各有1个节点不压缩，中间的节点压缩。
#    2: 表示quicklist两端各有2个节点不压缩，中间的节点压缩。
#    3: 表示quicklist两端各有3个节点不压缩，中间的节点压缩。
#    依此类推…
# 由于0是个特殊值，很容易看出quicklist的头节点和尾节点总是不被压缩的，以便于在表的两端进行快速存取。
list-compress-depth 0
 
 
# set有一种特殊编码的情况：当set数据全是十进制64位有符号整型数字构成的字符串时。
# 下面这个配置项就是用来设置set使用这种编码来节省内存的最大长度。
set-max-intset-entries 512
 
# 与hash和list相似，有序集合也可以用一种特别的编码方式来节省大量空间。
# 这种编码只适合长度和元素都小于下面限制的有序集合
zset-max-ziplist-entries 128
zset-max-ziplist-value 64
 
# HyperLogLog稀疏结构表示字节的限制。该限制包括
# 16个字节的头。当HyperLogLog使用稀疏结构表示
# 这些限制，它会被转换成密度表示。
# 值大于16000是完全没用的，因为在该点
# 密集的表示是更多的内存效率。
# 建议值是3000左右，以便具有的内存好处, 减少内存的消耗
hll-sparse-max-bytes 3000
 
# Streams宏节点最大大小/项目。 流数据结构是基数编码内部多个项目的大节点树。 使用此配置
# 可以配置单个节点的字节数，以及切换到新节点之前可能包含的最大项目数
# 追加新的流条目。 如果以下任何设置设置为0，忽略限制，因此例如可以设置一个
# 大入口限制将max-bytes设置为0，将max-entries设置为所需的值
stream-node-max-bytes 4096
stream-node-max-entries 100
 
# 启用哈希刷新，每100个CPU毫秒会拿出1个毫秒来刷新Redis的主哈希表（顶级键值映射表）
activerehashing yes
 
# 客户端的输出缓冲区的限制，可用于强制断开那些因为某种原因从服务器读取数据的速度不够快的客户端
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit slave 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60
 
# 客户端查询缓冲区累积新命令。 它们仅限于固定的默认情况下，
# 多数情况下为了避免协议不同步导致客户端查询缓冲区中未绑定的内存使用量的错误
# 但是，如果你有使用的话，你可以在这里配置它，比如我们有很多执行请求或类似的。
# client-query-buffer-limit 1gb
 
# 在Redis协议中，批量请求，即表示单个的元素strings，通常限制为512 MB。
# 但是，您可以z更改此限制
# proto-max-bulk-len 512mb
 
# 默认情况下，“hz”的被设定为10。提高该值将在Redis空闲时使用更多的CPU时，但同时当有多个key
# 同时到期会使Redis的反应更灵敏，以及超时可以更精确地处理
hz 10
 
# Normally it is useful to have an HZ value which is proportional to the
# number of clients connected. This is useful in order, for instance, to
# avoid too many clients are processed for each background task invocation
# in order to avoid latency spikes.
#
# Since the default HZ value by default is conservatively set to 10, Redis
# offers, and enables by default, the ability to use an adaptive HZ value
# which will temporary raise when there are many connected clients.
#
# When dynamic HZ is enabled, the actual configured HZ will be used as
# as a baseline, but multiples of the configured HZ value will be actually
# used as needed once more clients are connected. In this way an idle
# instance will use very little CPU time while a busy instance will be
# more responsive.
dynamic-hz yes
 
# 当一个子进程重写AOF文件时，如果启用下面的选项，则文件每生成32M数据会被同步
aof-rewrite-incremental-fsync yes
 
 
# 当redis保存RDB文件时，如果启用了以下选项，每生成32 MB数据，文件将被fsync-ed。 
# 这很有用，以便以递增方式将文件提交到磁盘并避免大延迟峰值。
rdb-save-incremental-fsync yes
 
# Redis LFU eviction (see maxmemory setting) can be tuned. However it is a good
# idea to start with the default settings and only change them after investigating
# how to improve the performances and how the keys LFU change over time, which
# is possible to inspect via the OBJECT FREQ command.
#
# There are two tunable parameters in the Redis LFU implementation: the
# counter logarithm factor and the counter decay time. It is important to
# understand what the two parameters mean before changing them.
#
# The LFU counter is just 8 bits per key, it's maximum value is 255, so Redis
# uses a probabilistic increment with logarithmic behavior. Given the value
# of the old counter, when a key is accessed, the counter is incremented in
# this way:
#
# 1. A random number R between 0 and 1 is extracted.
# 2. A probability P is calculated as 1/(old_value*lfu_log_factor+1).
# 3. The counter is incremented only if R < P.
#
# The default lfu-log-factor is 10. This is a table of how the frequency
# counter changes with a different number of accesses with different
# logarithmic factors:
#
# +--------+------------+------------+------------+------------+------------+
# | factor | 100 hits   | 1000 hits  | 100K hits  | 1M hits    | 10M hits   |
# +--------+------------+------------+------------+------------+------------+
# | 0      | 104        | 255        | 255        | 255        | 255        |
# +--------+------------+------------+------------+------------+------------+
# | 1      | 18         | 49         | 255        | 255        | 255        |
# +--------+------------+------------+------------+------------+------------+
# | 10     | 10         | 18         | 142        | 255        | 255        |
# +--------+------------+------------+------------+------------+------------+
# | 100    | 8          | 11         | 49         | 143        | 255        |
# +--------+------------+------------+------------+------------+------------+
#
# NOTE: The above table was obtained by running the following commands:
#
#   redis-benchmark -n 1000000 incr foo
#   redis-cli object freq foo
#
# NOTE 2: The counter initial value is 5 in order to give new objects a chance
# to accumulate hits.
#
# The counter decay time is the time, in minutes, that must elapse in order
# for the key counter to be divided by two (or decremented if it has a value
# less <= 10).
#
# The default value for the lfu-decay-time is 1. A Special value of 0 means to
# decay the counter every time it happens to be scanned.
#
# lfu-log-factor 10
# lfu-decay-time 1
 
########################### ACTIVE DEFRAGMENTATION #######################
 
# 启用主动碎片整理
# activedefrag yes
 
# 启动活动碎片整理的最小碎片浪费量
# active-defrag-ignore-bytes 100mb
 
# 启动碎片整理的最小碎片百分比
# active-defrag-threshold-lower 10
 
# 使用最大消耗时的最大碎片百分比
# active-defrag-threshold-upper 100
 
# 在CPU百分比中进行碎片整理的最小消耗
# active-defrag-cycle-min 5
 
# 磁盘碎片整理的最大消耗
# active-defrag-cycle-max 75
 
# 将从主字典扫描处理的最大set / hash / zset / list字段数
# active-defrag-max-scan-fields 1000

```