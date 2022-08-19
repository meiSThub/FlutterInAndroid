import 'package:flutter/material.dart';

/// 模拟商品列表页
class GoodsListPage extends StatefulWidget {
  const GoodsListPage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _GoodsListPageState();
}

class _GoodsListPageState extends State {
  final _dataList = [];

  @override
  void initState() {
    super.initState();
    for (int i = 0; i < 20; i++) {
      _dataList.add({
        "name": "商品名称-${i + 1}",
        "subName": "商品副标题",
        "price": (100 + i * 10)
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Flutter 商品列表页")),
      body: Padding(
        padding: const EdgeInsets.only(left: 10, right: 10),
        child: ListView.builder(
          itemCount: _dataList.length,
          itemBuilder: (context, index) {
            return Column(
              children: [
                ListTile(
                  title: Text(_dataList[index]["name"]),
                  subtitle: Text(_dataList[index]["subName"]),
                ),
                const Divider(),
              ],
            );
          },
        ),
      ),
    );
  }
}
