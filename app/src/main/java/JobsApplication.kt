package sample

import android.app.Application
import platform.PlatformObjects

class JobsApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        PlatformObjects.app = this
    }
}