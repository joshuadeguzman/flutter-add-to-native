package io.github.joshuadeguzman.android_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.view.FlutterMain
import io.flutter.view.FlutterNativeView
import io.flutter.view.FlutterRunArguments
import io.flutter.view.FlutterView
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * Created by Joshua de Guzman on 2019-08-19.
 */
class FirstFragment : Fragment() {

    companion object {
        var TAG = FirstFragment::class.java.simpleName
        fun newInstance() = FirstFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.loadFlutterEmbeddedWidget()
    }

    private fun loadFlutterEmbeddedWidget() {
        context?.let {
            FlutterMain.startInitialization(activity!!.applicationContext)
            FlutterMain.ensureInitializationComplete(activity!!.applicationContext, null)
            val nativeView = FlutterNativeView(activity!!)
            val flutterView = FlutterView(activity, null, nativeView)

            val arguments = FlutterRunArguments()
            arguments.bundlePath = FlutterMain.findAppBundlePath(activity!!.applicationContext)
            arguments.entrypoint = "embeddedMain"
            flutterView.runFromBundle(arguments)
            GeneratedPluginRegistrant.registerWith(flutterView.pluginRegistry)

            flutterContainerView.addView(flutterView)
        }
    }
}