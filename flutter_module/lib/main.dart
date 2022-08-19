import 'package:flutter/material.dart';
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
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: ListView(
        children: [
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
        ],
      ),
    );
  }
}
