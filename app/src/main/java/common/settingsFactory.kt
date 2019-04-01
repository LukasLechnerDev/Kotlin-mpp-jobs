package common

import com.russhwolf.settings.PlatformSettings
import com.russhwolf.settings.Settings
import platform.PlatformObjects

actual fun settingsFactory(): Settings.Factory {
    return PlatformSettings.Factory(PlatformObjects.app)
}