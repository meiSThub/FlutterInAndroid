import 'package:flutter_module/pages/flutter_view.dart';
import 'package:flutter_module/pages/goods_list_page.dart';
import 'package:flutter_module/routers/page_num.dart';

/// 路由表
final routes = {
  PageNum.goodsList: (context) => const GoodsListPage(),
  PageNum.goodsImagePage: (context) => const GoodsImagePage(),
};
