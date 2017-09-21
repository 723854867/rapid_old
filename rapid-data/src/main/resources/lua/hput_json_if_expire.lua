local data = redis.call("hget", KEYS[1], ARGV[1])
if (data)
then
	local cdata = cjson.decode(data)
	local ccreated = cdata["created"]
	local ndata = cjson.decode(ARGV[2])
	local ncreated = ndata["created"]
	local timestamp = ccreated + tonumber(ARGV[3])
	if ((timestamp >= ncreated) or (ccreated >= ncreated))
	then
		return 0
	end
end
redis.call("hset", KEYS[1], ARGV[1], ARGV[2])
return 1
