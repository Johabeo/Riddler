package com.example.riddler

import android.app.Application
import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp
import org.jetbrains.annotations.NotNull
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    //all singletons should be here
    override fun onCreate() {
        super.onCreate()
        if(!BuildConfig.DEBUG){
            Timber.plant(object : Timber.DebugTree(){
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return "Class ${super.createStackElementTag(element)} :" +
                            " Line ${element.lineNumber} Method ${element.methodName}"
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }

}

class ReleaseTree : @NotNull Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if(priority == Log.ERROR || priority == Log.WARN){
            //send report to crashlytics
            var crashlytics = Firebase.crashlytics
            //crashlytics.setUserId("user1")
            crashlytics.log("$tag: $message")
            //crashlytics.recordException()
        }
    }

}

class StaticObject{
    companion object {
        var isdebugMode = true
    }
}