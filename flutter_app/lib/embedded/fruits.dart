import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class FruitsEmbedded extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return FruitsEmbeddedState();
  }
}

class FruitsEmbeddedState extends State<FruitsEmbedded> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Container(
          height: double.infinity,
          width: double.infinity,
          color: Colors.red,
          child: Align(
            alignment: Alignment.center,
            child: Text(
              'Fruits',
            ),
          ),
        ),
      ),
    );
  }
}
