import 'dart:ui';

import 'package:flutter/material.dart';

/// 商品图片
class GoodsImagePage extends StatefulWidget {
  const GoodsImagePage({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _GoodsImagePageState();
}

class _GoodsImagePageState extends State {
  String? _ImageUrl = null;

  @override
  void initState() {
    super.initState();
    var routeUrl = window.defaultRouteName;
    print('routeUrl=$routeUrl');
    var uri = Uri.parse(routeUrl);
    var imageUrl = uri.queryParameters["imageUrl"];
    print('imageUrl=$imageUrl');
    setState(() {
      _ImageUrl = imageUrl;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: SizedBox(
        width: double.infinity,
        child: Column(
          children: [
            Text("$_ImageUrl"),
            AspectRatio(
              aspectRatio: 16 / 9,
              child: Image.network(
                _ImageUrl ??
                    "http://pic1.win4000.com/wallpaper/7/57ba6b2a9d75e.jpg",
                fit: BoxFit.cover,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
