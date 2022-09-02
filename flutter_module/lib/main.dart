import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/routers/page_num.dart';
import 'package:flutter_module/routers/page_routes.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      routes: routes,
      initialRoute: "/",
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  var result = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: ListView(
        children: [
          Text(
            result,
            style: const TextStyle(fontSize: 20, color: Colors.red),
          ),
          ElevatedButton(
            onPressed: () {
              Navigator.pushNamed(context, PageNum.goodsList);
            },
            child: const Text("商品列表页"),
          ),
          ElevatedButton(
            onPressed: () {
              Navigator.pushNamed(context, PageNum.goodsImagePage);
            },
            child: const Text("商品图片"),
          ),
          ElevatedButton(
            onPressed: () async {
              var methodChannel = const MethodChannel("method_channel_common");
              var battery = await methodChannel.invokeMethod("getBatteryLevel");
              setState(() {
                result = "获取电量为：$battery";
              });
            },
            child: const Text("MethodChannel,调用原生方法"),
          ),
          ElevatedButton(
            onPressed: () async {
              var methodChannel = const MethodChannel("method_channel_test");
              var battery = await methodChannel.invokeMethod("getBatteryLevel");
              setState(() {
                result = "FlutterPlugin，获取电量为：$battery";
              });
            },
            child: const Text("FlutterPlugin,调用原生方法"),
          ),
          ElevatedButton(
            onPressed: () async {
              var channel = const BasicMessageChannel(
                  "basic_message_channel_common", StringCodec());
              var backRes = await channel.send("Hello,I am Flutter");
              print('back result=$backRes');
              // 接收 Native 发送过来的 消息
              channel.setMessageHandler(
                (message) => Future<String>(() {
                  setState(() {
                    result = """
发送消息后，Native返回的的数据：$backRes,
接收到Native消息：$message
                    """;
                  });
                  return "";
                }),
              );
            },
            child: const Text("BasicMessageChannel,获取信息"),
          ),
        ],
      ),
    );
  }
}
