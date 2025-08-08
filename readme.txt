./gradlew gm -PmodName=hello -PpackName="com.example.hello"

命令解释:
-PmodName=hello 模块名, 必填; <hello> 开发中改为真实模块名
-PpackName="com.example.hello" 包名, 选填; "com.example.hello" 开发中改为真实包名

如输入 ./gradlew gm -PmodName=hello
默认包名则为: com.example.hello
默认包名可以到配置中修改