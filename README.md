# Multiple_Modules / 组件化
The project is divided into multiple modules
将一个项目分成多个模块， 每个模块可以单独调试。

快速生成module， 在Terminal中输入指令：

./gradlew gm -PmodName=hello -PpackName="com.example.hello"

命令解释:
-PmodName=hello 模块名, 必填; <hello> 开发中改为真实模块名
-PpackName="com.example.hello" 包名, 选填; "com.example.hello" 开发中改为真实包名

如输入 ./gradlew gm -PmodName=hello
默认包名则为: com.example.hello
默认包名可以到配置中修改


详情: https://blog.csdn.net/lin18775411773/article/details/149972048?spm=1001.2014.3001.5502
