package io.github.joshuadeguzman.android_app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.view.FlutterMain
import io.flutter.view.FlutterNativeView
import io.flutter.view.FlutterRunArguments
import io.flutter.view.FlutterView
import io.github.joshuadeguzman.android_app.flutter.channels.InitializationChannel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * Created by Joshua de Guzman on 2019-08-19.
 */
class FirstFragment : Fragment() {

    private var initializationChannel: InitializationChannel? = null

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
            // TODO: Migrate this to a reusable based custom Flutter main file
            FlutterMain.startInitialization(activity!!.applicationContext)
            FlutterMain.ensureInitializationComplete(activity!!.applicationContext, null)
            val nativeView = FlutterNativeView(activity!!)
            val flutterView = FlutterView(activity, null, nativeView)

            // Run FlutterView on a custom dart entry point
            val arguments = FlutterRunArguments()
            arguments.bundlePath = FlutterMain.findAppBundlePath(activity!!.applicationContext)
            arguments.entrypoint = "embeddedMain"
            flutterView.runFromBundle(arguments)
            GeneratedPluginRegistrant.registerWith(flutterView.pluginRegistry)

            // Subscribe to the initialization channel
            initializationChannel = InitializationChannel(flutterView)
            initializationChannel?.setupWithMessenger()
            initializationChannel?.setupMessageHandler({ message ->
                Log.d(TAG, "Message from Flutter $message")
                flutterContainerView.addView(flutterView)

                // Update visibility of android layout groups
                mainView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
            }, { error ->
                Log.e(TAG, "Error message from Flutter ${error?.localizedMessage}")

                // Update visibility of android layout groups
                mainView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            })
        }
    }
}