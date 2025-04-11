-- KEYS[1] 表示号源总数的Redis键
-- ARGV 无参数传入

-- 获取当前号源总数
local total = redis.call('GET', KEYS[1])

-- 如果号源总数大于0
if total and tonumber(total) > 0 then
    -- 监视号源总数的键,确保执行后续操作时没有其他客户端修改了该键
    redis.call('WATCH', KEYS[1])

    -- 获取当前号源总数,确保没有并发修改
    total = redis.call('GET', KEYS[1])
    if total and tonumber(total) > 0 then
        -- 开启事务,确保后续操作的原子性
        redis.call('MULTI')

        -- 减少号源总数
        redis.call('DECRBY', KEYS[1], 1)

        -- 执行事务
        local success = redis.call('EXEC')

        -- 如果事务执行成功,返回1表示抢号成功
        if success then
            return 1
        end
    end
end

-- 如果号源已经用完或者事务执行失败,返回0表示抢号失败
return 0
