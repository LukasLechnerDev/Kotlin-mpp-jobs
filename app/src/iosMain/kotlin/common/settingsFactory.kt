package common

import com.russhwolf.settings.PlatformSettings
import com.russhwolf.settings.Settings

actual fun settingsFactory(): Settings.Factory {
    return PlatformSettings.Factory()
}