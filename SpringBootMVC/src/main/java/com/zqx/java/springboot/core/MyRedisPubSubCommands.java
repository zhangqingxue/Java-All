package com.zqx.java.springboot.core;

import io.lettuce.core.*;
import io.lettuce.core.output.*;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandType;
import io.lettuce.core.protocol.ProtocolKeyword;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MyRedisPubSubCommands implements RedisPubSubCommands {
    @Override
    public void psubscribe(Object[] patterns) {

    }

    @Override
    public void punsubscribe(Object[] patterns) {

    }

    @Override
    public void subscribe(Object[] channels) {

    }

    @Override
    public void unsubscribe(Object[] channels) {

    }

    @Override
    public void setTimeout(Duration timeout) {

    }

    @Override
    public void setTimeout(long timeout, TimeUnit unit) {

    }

    @Override
    public String auth(String password) {
        return null;
    }

    @Override
    public String clusterBumpepoch() {
        return null;
    }

    @Override
    public String clusterMeet(String ip, int port) {
        return null;
    }

    @Override
    public String clusterForget(String nodeId) {
        return null;
    }

    @Override
    public String clusterAddSlots(int... slots) {
        return null;
    }

    @Override
    public String clusterDelSlots(int... slots) {
        return null;
    }

    @Override
    public String clusterSetSlotNode(int slot, String nodeId) {
        return null;
    }

    @Override
    public String clusterSetSlotStable(int slot) {
        return null;
    }

    @Override
    public String clusterSetSlotMigrating(int slot, String nodeId) {
        return null;
    }

    @Override
    public String clusterSetSlotImporting(int slot, String nodeId) {
        return null;
    }

    @Override
    public String clusterInfo() {
        return null;
    }

    @Override
    public String clusterMyId() {
        return null;
    }

    @Override
    public String clusterNodes() {
        return null;
    }

    @Override
    public List<String> clusterSlaves(String nodeId) {
        return null;
    }

    @Override
    public List clusterGetKeysInSlot(int slot, int count) {
        return null;
    }

    @Override
    public Long clusterCountKeysInSlot(int slot) {
        return null;
    }

    @Override
    public Long clusterCountFailureReports(String nodeId) {
        return null;
    }

    @Override
    public Long clusterKeyslot(Object key) {
        return null;
    }

    @Override
    public String clusterSaveconfig() {
        return null;
    }

    @Override
    public String clusterSetConfigEpoch(long configEpoch) {
        return null;
    }

    @Override
    public List<Object> clusterSlots() {
        return null;
    }

    @Override
    public String asking() {
        return null;
    }

    @Override
    public String clusterReplicate(String nodeId) {
        return null;
    }

    @Override
    public String clusterFailover(boolean force) {
        return null;
    }

    @Override
    public String clusterReset(boolean hard) {
        return null;
    }

    @Override
    public String clusterFlushslots() {
        return null;
    }

    @Override
    public String select(int db) {
        return null;
    }

    @Override
    public String swapdb(int db1, int db2) {
        return null;
    }

    @Override
    public StatefulRedisPubSubConnection getStatefulConnection() {
        return null;
    }

    @Override
    public Long publish(Object channel, Object message) {
        return null;
    }

    @Override
    public List pubsubChannels() {
        return null;
    }

    @Override
    public List pubsubChannels(Object channel) {
        return null;
    }

    @Override
    public Map pubsubNumsub(Object[] channels) {
        return null;
    }

    @Override
    public Long pubsubNumpat() {
        return null;
    }

    @Override
    public Object echo(Object msg) {
        return null;
    }

    @Override
    public List<Object> role() {
        return null;
    }

    @Override
    public String ping() {
        return null;
    }

    @Override
    public String readOnly() {
        return null;
    }

    @Override
    public String readWrite() {
        return null;
    }

    @Override
    public String quit() {
        return null;
    }

    @Override
    public Long waitForReplication(int replicas, long timeout) {
        return null;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public Object dispatch(ProtocolKeyword type, CommandOutput output, CommandArgs args) {
        return null;
    }

    @Override
    public Object dispatch(ProtocolKeyword type, CommandOutput output) {
        return null;
    }

    @Override
    public Long geoadd(Object key, double longitude, double latitude, Object member) {
        return null;
    }

    @Override
    public Long geoadd(Object key, Object... lngLatMember) {
        return null;
    }

    @Override
    public List<Value<String>> geohash(Object key, Object[] members) {
        return null;
    }

    @Override
    public Set georadius(Object key, double longitude, double latitude, double distance, GeoArgs.Unit unit) {
        return null;
    }

    @Override
    public List<GeoWithin> georadius(Object key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoArgs geoArgs) {
        return null;
    }

    @Override
    public Long georadius(Object key, double longitude, double latitude, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs geoRadiusStoreArgs) {
        return null;
    }

    @Override
    public Set georadiusbymember(Object key, Object member, double distance, GeoArgs.Unit unit) {
        return null;
    }

    @Override
    public List<GeoWithin> georadiusbymember(Object key, Object member, double distance, GeoArgs.Unit unit, GeoArgs geoArgs) {
        return null;
    }

    @Override
    public Long georadiusbymember(Object key, Object member, double distance, GeoArgs.Unit unit, GeoRadiusStoreArgs geoRadiusStoreArgs) {
        return null;
    }

    @Override
    public List<GeoCoordinates> geopos(Object key, Object[] members) {
        return null;
    }

    @Override
    public Double geodist(Object key, Object from, Object to, GeoArgs.Unit unit) {
        return null;
    }

    @Override
    public Long pfadd(Object key, Object[] values) {
        return null;
    }

    @Override
    public String pfmerge(Object destkey, Object[] sourcekeys) {
        return null;
    }

    @Override
    public Long pfcount(Object[] keys) {
        return null;
    }

    @Override
    public Long hdel(Object key, Object[] fields) {
        return null;
    }

    @Override
    public Boolean hexists(Object key, Object field) {
        return null;
    }

    @Override
    public Object hget(Object key, Object field) {
        return null;
    }

    @Override
    public Long hincrby(Object key, Object field, long amount) {
        return null;
    }

    @Override
    public Double hincrbyfloat(Object key, Object field, double amount) {
        return null;
    }

    @Override
    public Map hgetall(Object key) {
        return null;
    }

    @Override
    public Long hgetall(KeyValueStreamingChannel channel, Object key) {
        return null;
    }

    @Override
    public List hkeys(Object key) {
        return null;
    }

    @Override
    public Long hkeys(KeyStreamingChannel channel, Object key) {
        return null;
    }

    @Override
    public Long hlen(Object key) {
        return null;
    }

    @Override
    public List<KeyValue> hmget(Object key, Object[] fields) {
        return null;
    }

    @Override
    public Long hmget(KeyValueStreamingChannel channel, Object key, Object[] fields) {
        return null;
    }

    @Override
    public String hmset(Object key, Map map) {
        return null;
    }

    @Override
    public MapScanCursor hscan(Object key) {
        return null;
    }

    @Override
    public MapScanCursor hscan(Object key, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public MapScanCursor hscan(Object key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public MapScanCursor hscan(Object key, ScanCursor scanCursor) {
        return null;
    }

    @Override
    public StreamScanCursor hscan(KeyValueStreamingChannel channel, Object key) {
        return null;
    }

    @Override
    public StreamScanCursor hscan(KeyValueStreamingChannel channel, Object key, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public StreamScanCursor hscan(KeyValueStreamingChannel channel, Object key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public StreamScanCursor hscan(KeyValueStreamingChannel channel, Object key, ScanCursor scanCursor) {
        return null;
    }

    @Override
    public Boolean hset(Object key, Object field, Object value) {
        return null;
    }

    @Override
    public Boolean hsetnx(Object key, Object field, Object value) {
        return null;
    }

    @Override
    public Long hstrlen(Object key, Object field) {
        return null;
    }

    @Override
    public List hvals(Object key) {
        return null;
    }

    @Override
    public Long hvals(ValueStreamingChannel channel, Object key) {
        return null;
    }

    @Override
    public Long del(Object[] keys) {
        return null;
    }

    @Override
    public Long unlink(Object[] keys) {
        return null;
    }

    @Override
    public byte[] dump(Object key) {
        return new byte[0];
    }

    @Override
    public Long exists(Object[] keys) {
        return null;
    }

    @Override
    public Boolean expire(Object key, long seconds) {
        return null;
    }

    @Override
    public Boolean expireat(Object key, Date timestamp) {
        return null;
    }

    @Override
    public Boolean expireat(Object key, long timestamp) {
        return null;
    }

    @Override
    public List keys(Object pattern) {
        return null;
    }

    @Override
    public Long keys(KeyStreamingChannel channel, Object pattern) {
        return null;
    }

    @Override
    public String migrate(String host, int port, Object key, int db, long timeout) {
        return null;
    }

    @Override
    public String migrate(String host, int port, int db, long timeout, MigrateArgs migrateArgs) {
        return null;
    }

    @Override
    public Boolean move(Object key, int db) {
        return null;
    }

    @Override
    public String objectEncoding(Object key) {
        return null;
    }

    @Override
    public Long objectIdletime(Object key) {
        return null;
    }

    @Override
    public Long objectRefcount(Object key) {
        return null;
    }

    @Override
    public Boolean persist(Object key) {
        return null;
    }

    @Override
    public Boolean pexpire(Object key, long milliseconds) {
        return null;
    }

    @Override
    public Boolean pexpireat(Object key, Date timestamp) {
        return null;
    }

    @Override
    public Boolean pexpireat(Object key, long timestamp) {
        return null;
    }

    @Override
    public Long pttl(Object key) {
        return null;
    }

    @Override
    public Object randomkey() {
        return null;
    }

    @Override
    public String rename(Object key, Object newKey) {
        return null;
    }

    @Override
    public Boolean renamenx(Object key, Object newKey) {
        return null;
    }

    @Override
    public String restore(Object key, long ttl, byte[] value) {
        return null;
    }

    @Override
    public String restore(Object key, byte[] value, RestoreArgs args) {
        return null;
    }

    @Override
    public List sort(Object key) {
        return null;
    }

    @Override
    public Long sort(ValueStreamingChannel channel, Object key) {
        return null;
    }

    @Override
    public List sort(Object key, SortArgs sortArgs) {
        return null;
    }

    @Override
    public Long sort(ValueStreamingChannel channel, Object key, SortArgs sortArgs) {
        return null;
    }

    @Override
    public Long sortStore(Object key, SortArgs sortArgs, Object destination) {
        return null;
    }

    @Override
    public Long touch(Object[] keys) {
        return null;
    }

    @Override
    public Long ttl(Object key) {
        return null;
    }

    @Override
    public String type(Object key) {
        return null;
    }

    @Override
    public KeyScanCursor scan() {
        return null;
    }

    @Override
    public KeyScanCursor scan(ScanArgs scanArgs) {
        return null;
    }

    @Override
    public KeyScanCursor scan(ScanCursor scanCursor, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public KeyScanCursor scan(ScanCursor scanCursor) {
        return null;
    }

    @Override
    public StreamScanCursor scan(KeyStreamingChannel channel) {
        return null;
    }

    @Override
    public StreamScanCursor scan(KeyStreamingChannel channel, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public StreamScanCursor scan(KeyStreamingChannel channel, ScanCursor scanCursor, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public StreamScanCursor scan(KeyStreamingChannel channel, ScanCursor scanCursor) {
        return null;
    }

    @Override
    public KeyValue blpop(long timeout, Object[] keys) {
        return null;
    }

    @Override
    public KeyValue brpop(long timeout, Object[] keys) {
        return null;
    }

    @Override
    public Object brpoplpush(long timeout, Object source, Object destination) {
        return null;
    }

    @Override
    public Object lindex(Object key, long index) {
        return null;
    }

    @Override
    public Long linsert(Object key, boolean before, Object pivot, Object value) {
        return null;
    }

    @Override
    public Long llen(Object key) {
        return null;
    }

    @Override
    public Object lpop(Object key) {
        return null;
    }

    @Override
    public Long lpush(Object key, Object[] values) {
        return null;
    }

    @Override
    public Long lpushx(Object key, Object[] values) {
        return null;
    }

    @Override
    public List lrange(Object key, long start, long stop) {
        return null;
    }

    @Override
    public Long lrange(ValueStreamingChannel channel, Object key, long start, long stop) {
        return null;
    }

    @Override
    public Long lrem(Object key, long count, Object value) {
        return null;
    }

    @Override
    public String lset(Object key, long index, Object value) {
        return null;
    }

    @Override
    public String ltrim(Object key, long start, long stop) {
        return null;
    }

    @Override
    public Object rpop(Object key) {
        return null;
    }

    @Override
    public Object rpoplpush(Object source, Object destination) {
        return null;
    }

    @Override
    public Long rpush(Object key, Object[] values) {
        return null;
    }

    @Override
    public Long rpushx(Object key, Object[] values) {
        return null;
    }

    @Override
    public Object eval(String script, ScriptOutputType type, Object[] keys) {
        return null;
    }

    @Override
    public Object eval(String script, ScriptOutputType type, Object[] keys, Object[] values) {
        return null;
    }

    @Override
    public Object evalsha(String digest, ScriptOutputType type, Object[] keys) {
        return null;
    }

    @Override
    public Object evalsha(String digest, ScriptOutputType type, Object[] keys, Object[] values) {
        return null;
    }

    @Override
    public List<Boolean> scriptExists(String... digests) {
        return null;
    }

    @Override
    public String scriptFlush() {
        return null;
    }

    @Override
    public String scriptKill() {
        return null;
    }

    @Override
    public String scriptLoad(Object script) {
        return null;
    }

    @Override
    public String digest(Object script) {
        return null;
    }

    @Override
    public String bgrewriteaof() {
        return null;
    }

    @Override
    public String bgsave() {
        return null;
    }

    @Override
    public Object clientGetname() {
        return null;
    }

    @Override
    public String clientSetname(Object name) {
        return null;
    }

    @Override
    public String clientKill(String addr) {
        return null;
    }

    @Override
    public Long clientKill(KillArgs killArgs) {
        return null;
    }

    @Override
    public Long clientUnblock(long id, UnblockType type) {
        return null;
    }

    @Override
    public String clientPause(long timeout) {
        return null;
    }

    @Override
    public String clientList() {
        return null;
    }

    @Override
    public List<Object> command() {
        return null;
    }

    @Override
    public List<Object> commandInfo(String... commands) {
        return null;
    }

    @Override
    public List<Object> commandInfo(CommandType... commands) {
        return null;
    }

    @Override
    public Long commandCount() {
        return null;
    }

    @Override
    public Map<String, String> configGet(String parameter) {
        return null;
    }

    @Override
    public String configResetstat() {
        return null;
    }

    @Override
    public String configRewrite() {
        return null;
    }

    @Override
    public String configSet(String parameter, String value) {
        return null;
    }

    @Override
    public Long dbsize() {
        return null;
    }

    @Override
    public String debugCrashAndRecover(Long delay) {
        return null;
    }

    @Override
    public String debugHtstats(int db) {
        return null;
    }

    @Override
    public String debugObject(Object key) {
        return null;
    }

    @Override
    public void debugOom() {

    }

    @Override
    public void debugSegfault() {

    }

    @Override
    public String debugReload() {
        return null;
    }

    @Override
    public String debugRestart(Long delay) {
        return null;
    }

    @Override
    public String debugSdslen(Object key) {
        return null;
    }

    @Override
    public String flushall() {
        return null;
    }

    @Override
    public String flushallAsync() {
        return null;
    }

    @Override
    public String flushdb() {
        return null;
    }

    @Override
    public String flushdbAsync() {
        return null;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String info(String section) {
        return null;
    }

    @Override
    public Date lastsave() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public void shutdown(boolean save) {

    }

    @Override
    public String slaveof(String host, int port) {
        return null;
    }

    @Override
    public String slaveofNoOne() {
        return null;
    }

    @Override
    public List<Object> slowlogGet() {
        return null;
    }

    @Override
    public List<Object> slowlogGet(int count) {
        return null;
    }

    @Override
    public Long slowlogLen() {
        return null;
    }

    @Override
    public String slowlogReset() {
        return null;
    }

    @Override
    public List time() {
        return null;
    }

    @Override
    public Long sadd(Object key, Object[] members) {
        return null;
    }

    @Override
    public Long scard(Object key) {
        return null;
    }

    @Override
    public Set sdiff(Object[] keys) {
        return null;
    }

    @Override
    public Long sdiff(ValueStreamingChannel channel, Object[] keys) {
        return null;
    }

    @Override
    public Long sdiffstore(Object destination, Object[] keys) {
        return null;
    }

    @Override
    public Set sinter(Object[] keys) {
        return null;
    }

    @Override
    public Long sinter(ValueStreamingChannel channel, Object[] keys) {
        return null;
    }

    @Override
    public Long sinterstore(Object destination, Object[] keys) {
        return null;
    }

    @Override
    public Boolean sismember(Object key, Object member) {
        return null;
    }

    @Override
    public Boolean smove(Object source, Object destination, Object member) {
        return null;
    }

    @Override
    public Set smembers(Object key) {
        return null;
    }

    @Override
    public Long smembers(ValueStreamingChannel channel, Object key) {
        return null;
    }

    @Override
    public Object spop(Object key) {
        return null;
    }

    @Override
    public Set spop(Object key, long count) {
        return null;
    }

    @Override
    public Object srandmember(Object key) {
        return null;
    }

    @Override
    public List srandmember(Object key, long count) {
        return null;
    }

    @Override
    public Long srandmember(ValueStreamingChannel channel, Object key, long count) {
        return null;
    }

    @Override
    public Long srem(Object key, Object[] members) {
        return null;
    }

    @Override
    public Set sunion(Object[] keys) {
        return null;
    }

    @Override
    public Long sunion(ValueStreamingChannel channel, Object[] keys) {
        return null;
    }

    @Override
    public Long sunionstore(Object destination, Object[] keys) {
        return null;
    }

    @Override
    public ValueScanCursor sscan(Object key) {
        return null;
    }

    @Override
    public ValueScanCursor sscan(Object key, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public ValueScanCursor sscan(Object key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public ValueScanCursor sscan(Object key, ScanCursor scanCursor) {
        return null;
    }

    @Override
    public StreamScanCursor sscan(ValueStreamingChannel channel, Object key) {
        return null;
    }

    @Override
    public StreamScanCursor sscan(ValueStreamingChannel channel, Object key, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public StreamScanCursor sscan(ValueStreamingChannel channel, Object key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public StreamScanCursor sscan(ValueStreamingChannel channel, Object key, ScanCursor scanCursor) {
        return null;
    }

    @Override
    public KeyValue bzpopmin(long timeout, Object[] keys) {
        return null;
    }

    @Override
    public KeyValue bzpopmax(long timeout, Object[] keys) {
        return null;
    }

    @Override
    public Long zadd(Object key, double score, Object member) {
        return null;
    }

    @Override
    public Long zadd(Object key, Object... scoresAndValues) {
        return null;
    }

    @Override
    public Long zadd(Object key, ScoredValue[] scoredValues) {
        return null;
    }

    @Override
    public Long zadd(Object key, ZAddArgs zAddArgs, double score, Object member) {
        return null;
    }

    @Override
    public Long zadd(Object key, ZAddArgs zAddArgs, Object... scoresAndValues) {
        return null;
    }

    @Override
    public Long zadd(Object key, ZAddArgs zAddArgs, ScoredValue[] scoredValues) {
        return null;
    }

    @Override
    public Double zaddincr(Object key, double score, Object member) {
        return null;
    }

    @Override
    public Double zaddincr(Object key, ZAddArgs zAddArgs, double score, Object member) {
        return null;
    }

    @Override
    public Long zcard(Object key) {
        return null;
    }

    @Override
    public Long zcount(Object key, double min, double max) {
        return null;
    }

    @Override
    public Long zcount(Object key, String min, String max) {
        return null;
    }

    @Override
    public Double zincrby(Object key, double amount, Object member) {
        return null;
    }

    @Override
    public Long zinterstore(Object destination, Object[] keys) {
        return null;
    }

    @Override
    public Long zinterstore(Object destination, ZStoreArgs storeArgs, Object[] keys) {
        return null;
    }

    @Override
    public Long zlexcount(Object key, String min, String max) {
        return null;
    }

    @Override
    public Long zlexcount(Object key, Range range) {
        return null;
    }

    @Override
    public ScoredValue zpopmin(Object key) {
        return null;
    }

    @Override
    public List<ScoredValue> zpopmin(Object key, long count) {
        return null;
    }

    @Override
    public ScoredValue zpopmax(Object key) {
        return null;
    }

    @Override
    public List<ScoredValue> zpopmax(Object key, long count) {
        return null;
    }

    @Override
    public List zrange(Object key, long start, long stop) {
        return null;
    }

    @Override
    public Long zrange(ValueStreamingChannel channel, Object key, long start, long stop) {
        return null;
    }

    @Override
    public List<ScoredValue> zrangeWithScores(Object key, long start, long stop) {
        return null;
    }

    @Override
    public Long zrangeWithScores(ScoredValueStreamingChannel channel, Object key, long start, long stop) {
        return null;
    }

    @Override
    public List zrangebylex(Object key, String min, String max) {
        return null;
    }

    @Override
    public List zrangebylex(Object key, Range range) {
        return null;
    }

    @Override
    public List zrangebylex(Object key, String min, String max, long offset, long count) {
        return null;
    }

    @Override
    public List zrangebylex(Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public List zrangebyscore(Object key, double min, double max) {
        return null;
    }

    @Override
    public List zrangebyscore(Object key, String min, String max) {
        return null;
    }

    @Override
    public List zrangebyscore(Object key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public List zrangebyscore(Object key, String min, String max, long offset, long count) {
        return null;
    }

    @Override
    public Long zrangebyscore(ValueStreamingChannel channel, Object key, double min, double max) {
        return null;
    }

    @Override
    public Long zrangebyscore(ValueStreamingChannel channel, Object key, String min, String max) {
        return null;
    }

    @Override
    public Long zrangebyscore(ValueStreamingChannel channel, Object key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public Long zrangebyscore(ValueStreamingChannel channel, Object key, String min, String max, long offset, long count) {
        return null;
    }

    @Override
    public List<ScoredValue> zrangebyscoreWithScores(Object key, double min, double max) {
        return null;
    }

    @Override
    public List<ScoredValue> zrangebyscoreWithScores(Object key, String min, String max) {
        return null;
    }

    @Override
    public List<ScoredValue> zrangebyscoreWithScores(Object key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public List<ScoredValue> zrangebyscoreWithScores(Object key, String min, String max, long offset, long count) {
        return null;
    }

    @Override
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, double min, double max) {
        return null;
    }

    @Override
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, String min, String max) {
        return null;
    }

    @Override
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, double min, double max, long offset, long count) {
        return null;
    }

    @Override
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, String min, String max, long offset, long count) {
        return null;
    }

    @Override
    public Long zrank(Object key, Object member) {
        return null;
    }

    @Override
    public Long zrem(Object key, Object[] members) {
        return null;
    }

    @Override
    public Long zremrangebylex(Object key, String min, String max) {
        return null;
    }

    @Override
    public Long zremrangebylex(Object key, Range range) {
        return null;
    }

    @Override
    public Long zremrangebyrank(Object key, long start, long stop) {
        return null;
    }

    @Override
    public Long zremrangebyscore(Object key, double min, double max) {
        return null;
    }

    @Override
    public Long zremrangebyscore(Object key, String min, String max) {
        return null;
    }

    @Override
    public List zrevrange(Object key, long start, long stop) {
        return null;
    }

    @Override
    public Long zrevrange(ValueStreamingChannel channel, Object key, long start, long stop) {
        return null;
    }

    @Override
    public List<ScoredValue> zrevrangeWithScores(Object key, long start, long stop) {
        return null;
    }

    @Override
    public Long zrevrangeWithScores(ScoredValueStreamingChannel channel, Object key, long start, long stop) {
        return null;
    }

    @Override
    public List zrevrangebylex(Object key, Range range) {
        return null;
    }

    @Override
    public List zrevrangebylex(Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public List zrevrangebyscore(Object key, double max, double min) {
        return null;
    }

    @Override
    public List zrevrangebyscore(Object key, String max, String min) {
        return null;
    }

    @Override
    public List zrevrangebyscore(Object key, double max, double min, long offset, long count) {
        return null;
    }

    @Override
    public List zrevrangebyscore(Object key, String max, String min, long offset, long count) {
        return null;
    }

    @Override
    public Long zrevrangebyscore(ValueStreamingChannel channel, Object key, double max, double min) {
        return null;
    }

    @Override
    public Long zrevrangebyscore(ValueStreamingChannel channel, Object key, String max, String min) {
        return null;
    }

    @Override
    public Long zrevrangebyscore(ValueStreamingChannel channel, Object key, double max, double min, long offset, long count) {
        return null;
    }

    @Override
    public Long zrevrangebyscore(ValueStreamingChannel channel, Object key, String max, String min, long offset, long count) {
        return null;
    }

    @Override
    public List<ScoredValue> zrevrangebyscoreWithScores(Object key, double max, double min) {
        return null;
    }

    @Override
    public List<ScoredValue> zrevrangebyscoreWithScores(Object key, String max, String min) {
        return null;
    }

    @Override
    public List<ScoredValue> zrevrangebyscoreWithScores(Object key, double max, double min, long offset, long count) {
        return null;
    }

    @Override
    public List<ScoredValue> zrevrangebyscoreWithScores(Object key, String max, String min, long offset, long count) {
        return null;
    }

    @Override
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, double max, double min) {
        return null;
    }

    @Override
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, String max, String min) {
        return null;
    }

    @Override
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, double max, double min, long offset, long count) {
        return null;
    }

    @Override
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, String max, String min, long offset, long count) {
        return null;
    }

    @Override
    public Long zrevrank(Object key, Object member) {
        return null;
    }

    @Override
    public ScoredValueScanCursor zscan(Object key) {
        return null;
    }

    @Override
    public ScoredValueScanCursor zscan(Object key, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public ScoredValueScanCursor zscan(Object key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public ScoredValueScanCursor zscan(Object key, ScanCursor scanCursor) {
        return null;
    }

    @Override
    public StreamScanCursor zscan(ScoredValueStreamingChannel channel, Object key) {
        return null;
    }

    @Override
    public StreamScanCursor zscan(ScoredValueStreamingChannel channel, Object key, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public StreamScanCursor zscan(ScoredValueStreamingChannel channel, Object key, ScanCursor scanCursor, ScanArgs scanArgs) {
        return null;
    }

    @Override
    public StreamScanCursor zscan(ScoredValueStreamingChannel channel, Object key, ScanCursor scanCursor) {
        return null;
    }

    @Override
    public Double zscore(Object key, Object member) {
        return null;
    }

    @Override
    public Long zunionstore(Object destination, Object[] keys) {
        return null;
    }

    @Override
    public Long zunionstore(Object destination, ZStoreArgs storeArgs, Object[] keys) {
        return null;
    }

    @Override
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public Long zrevrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, Range range) {
        return null;
    }

    @Override
    public List<ScoredValue> zrevrangebyscoreWithScores(Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public List<ScoredValue> zrevrangebyscoreWithScores(Object key, Range range) {
        return null;
    }

    @Override
    public Long zrevrangebyscore(ValueStreamingChannel channel, Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public Long zrevrangebyscore(ValueStreamingChannel channel, Object key, Range range) {
        return null;
    }

    @Override
    public List zrevrangebyscore(Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public List zrevrangebyscore(Object key, Range range) {
        return null;
    }

    @Override
    public Long zremrangebyscore(Object key, Range range) {
        return null;
    }

    @Override
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public Long zrangebyscoreWithScores(ScoredValueStreamingChannel channel, Object key, Range range) {
        return null;
    }

    @Override
    public List<ScoredValue> zrangebyscoreWithScores(Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public List<ScoredValue> zrangebyscoreWithScores(Object key, Range range) {
        return null;
    }

    @Override
    public Long zrangebyscore(ValueStreamingChannel channel, Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public Long zrangebyscore(ValueStreamingChannel channel, Object key, Range range) {
        return null;
    }

    @Override
    public List zrangebyscore(Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public List zrangebyscore(Object key, Range range) {
        return null;
    }

    @Override
    public Long zcount(Object key, Range range) {
        return null;
    }

    @Override
    public Long xack(Object key, Object group, String... messageIds) {
        return null;
    }

    @Override
    public String xadd(Object key, Map body) {
        return null;
    }

    @Override
    public String xadd(Object key, XAddArgs args, Map body) {
        return null;
    }

    @Override
    public String xadd(Object key, Object... keysAndValues) {
        return null;
    }

    @Override
    public String xadd(Object key, XAddArgs args, Object... keysAndValues) {
        return null;
    }

    @Override
    public List<StreamMessage> xclaim(Object key, Consumer consumer, long minIdleTime, String... messageIds) {
        return null;
    }

    @Override
    public List<StreamMessage> xclaim(Object key, Consumer consumer, XClaimArgs args, String... messageIds) {
        return null;
    }

    @Override
    public Long xdel(Object key, String... messageIds) {
        return null;
    }

    @Override
    public String xgroupCreate(XReadArgs.StreamOffset streamOffset, Object group) {
        return null;
    }

    @Override
    public Boolean xgroupDelconsumer(Object key, Consumer consumer) {
        return null;
    }

    @Override
    public Boolean xgroupDestroy(Object key, Object group) {
        return null;
    }

    @Override
    public String xgroupSetid(XReadArgs.StreamOffset streamOffset, Object group) {
        return null;
    }

    @Override
    public Long xlen(Object key) {
        return null;
    }

    @Override
    public List<Object> xpending(Object key, Object group) {
        return null;
    }

    @Override
    public List<StreamMessage> xread(XReadArgs.StreamOffset[] streams) {
        return null;
    }

    @Override
    public List<StreamMessage> xread(XReadArgs args, XReadArgs.StreamOffset[] streams) {
        return null;
    }

    @Override
    public List<StreamMessage> xreadgroup(Consumer consumer, XReadArgs.StreamOffset[] streams) {
        return null;
    }

    @Override
    public List<StreamMessage> xreadgroup(Consumer consumer, XReadArgs args, XReadArgs.StreamOffset[] streams) {
        return null;
    }

    @Override
    public Long xtrim(Object key, long count) {
        return null;
    }

    @Override
    public Long xtrim(Object key, boolean approximateTrimming, long count) {
        return null;
    }

    @Override
    public List<StreamMessage> xrevrange(Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public List<StreamMessage> xrevrange(Object key, Range range) {
        return null;
    }

    @Override
    public List<StreamMessage> xrange(Object key, Range range, Limit limit) {
        return null;
    }

    @Override
    public List<StreamMessage> xrange(Object key, Range range) {
        return null;
    }

    @Override
    public List<Object> xpending(Object key, Consumer consumer, Range range, Limit limit) {
        return null;
    }

    @Override
    public List<Object> xpending(Object key, Object group, Range range, Limit limit) {
        return null;
    }

    @Override
    public Long append(Object key, Object value) {
        return null;
    }

    @Override
    public Long bitcount(Object key) {
        return null;
    }

    @Override
    public Long bitcount(Object key, long start, long end) {
        return null;
    }

    @Override
    public List<Long> bitfield(Object key, BitFieldArgs bitFieldArgs) {
        return null;
    }

    @Override
    public Long bitpos(Object key, boolean state) {
        return null;
    }

    @Override
    public Long bitpos(Object key, boolean state, long start) {
        return null;
    }

    @Override
    public Long bitpos(Object key, boolean state, long start, long end) {
        return null;
    }

    @Override
    public Long bitopAnd(Object destination, Object[] keys) {
        return null;
    }

    @Override
    public Long bitopNot(Object destination, Object source) {
        return null;
    }

    @Override
    public Long bitopOr(Object destination, Object[] keys) {
        return null;
    }

    @Override
    public Long bitopXor(Object destination, Object[] keys) {
        return null;
    }

    @Override
    public Long decr(Object key) {
        return null;
    }

    @Override
    public Long decrby(Object key, long amount) {
        return null;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Long getbit(Object key, long offset) {
        return null;
    }

    @Override
    public Object getrange(Object key, long start, long end) {
        return null;
    }

    @Override
    public Object getset(Object key, Object value) {
        return null;
    }

    @Override
    public Long incr(Object key) {
        return null;
    }

    @Override
    public Long incrby(Object key, long amount) {
        return null;
    }

    @Override
    public Double incrbyfloat(Object key, double amount) {
        return null;
    }

    @Override
    public List<KeyValue> mget(Object[] keys) {
        return null;
    }

    @Override
    public Long mget(KeyValueStreamingChannel channel, Object[] keys) {
        return null;
    }

    @Override
    public String mset(Map map) {
        return null;
    }

    @Override
    public Boolean msetnx(Map map) {
        return null;
    }

    @Override
    public String set(Object key, Object value) {
        return null;
    }

    @Override
    public String set(Object key, Object value, SetArgs setArgs) {
        return null;
    }

    @Override
    public Long setbit(Object key, long offset, int value) {
        return null;
    }

    @Override
    public String setex(Object key, long seconds, Object value) {
        return null;
    }

    @Override
    public String psetex(Object key, long milliseconds, Object value) {
        return null;
    }

    @Override
    public Boolean setnx(Object key, Object value) {
        return null;
    }

    @Override
    public Long setrange(Object key, long offset, Object value) {
        return null;
    }

    @Override
    public Long strlen(Object key) {
        return null;
    }

    @Override
    public String discard() {
        return null;
    }

    @Override
    public TransactionResult exec() {
        return null;
    }

    @Override
    public String multi() {
        return null;
    }

    @Override
    public String watch(Object[] keys) {
        return null;
    }

    @Override
    public String unwatch() {
        return null;
    }
}
