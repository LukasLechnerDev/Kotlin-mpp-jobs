package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

actual class Sample {
    actual fun checkMe() = 44
}

actual object Platform {
    actual val name: String = "Android"
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hello()
        Sample().checkMe()
        setContentView(R.layout.activity_main)
        val textView = findViewById(R.id.textView) as TextView
        textView.text = hello()
    }
}