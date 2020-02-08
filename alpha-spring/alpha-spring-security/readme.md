# 验证
shell命令(测试登录):
curl \
-X POST \
-H "Content-Type:application/x-www-form-urlencoded" \
--data "username=thy&password=1231" \
http://localhost:8081/api/login 

或者:
curl \
-X POST \
-H "Content-Type:application/x-www-form-urlencoded" \
http://localhost:8081/api/login?username=thy\&password=1231

或者(注意&前面有转义符\)
curl -X POST http://localhost:8081/api/login?username=thy\&password=1231

# 参考资料
https://www.jianshu.com/p/c1543622b8c2