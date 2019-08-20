import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_app/channels/channels.dart';
import 'package:flutter_app/embedded/fruits.dart';
import 'package:flutter_app/embedded/vegetables.dart';

class EmbeddedContainer extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return EmbeddedContainerState();
  }
}

class EmbeddedContainerState extends State<EmbeddedContainer> {
  Widget _currentWidget = Container(
    color: Colors.black,
  );

  @override
  void initState() {
    super.initState();
    _setup();
  }

  _setup() {
    RouteChannel.setRouteChangeHandler(_handleRouteChange, context);
    InitializationChannel.notify();
  }

  @override
  Widget build(BuildContext context) {
    return _currentWidget;
  }

  void _handleRouteChange(Widget widget) {
    // TODO: Widget is returning a Future Widget
    setState(() {
      _currentWidget = widget;
    });
  }
}
