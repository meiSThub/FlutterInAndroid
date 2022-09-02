import 'package:flutter/services.dart';

class BasicMessageChannelPlugin {
  static const BasicMessageChannel _basicMessageChannel =
      BasicMessageChannel('BasicMessageChannelPlugin', StringCodec());

  /// 使用 BasicMessageChannel 接收来自 native 端发送的消息，并向 Native 回复
  void receiveNativeMessage() {
    _basicMessageChannel.setMessageHandler((message) => Future<String>(() {
          /// todo 这里可以做一个消息的分发，并处理
          print('接收到到消息为：$message');

          /// 在处理完消息之后，可以返回一个处理结果给 native 端
          return "收到Native的消息：$message";
        }));
  }

  /// 向 native 发送消息
  Future<String?> sendMessageToNative(String message) async {
    /// 向 native 发送消息，并等待 native 的回复
    try {
      String response = await _basicMessageChannel.send(message);
      return response;
    } catch (e) {
      print(e);
    }
    return null;
  }
}
