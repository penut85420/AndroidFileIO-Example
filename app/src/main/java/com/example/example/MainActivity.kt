package com.example.example

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.File

class MainActivity : AppCompatActivity() {
    companion object {
        const val LOG_TAG = "HelloApp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Hello.checkPermissionForIO(applicationContext)) {
            helloInit()
        } else {
            Log.d(LOG_TAG, "No Permission WutFace")
            Hello.requestPermission(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (Hello.checkPermissionForIO(applicationContext))
            helloInit()
        else
            findViewById<TextView>(R.id.hello).text = getString(R.string.reject)
    }

    private fun helloInit() {
        val fRoot = toFindRoot()
        Log.d(LOG_TAG, "Root: ${fRoot.absolutePath}")

        val fList = fRoot.listFiles()!!

        for (f in fList)
            Log.d(LOG_TAG, f.absolutePath)

        val ff = File(fRoot, "Hello/MyData/Config.json")
        Log.d(LOG_TAG, "${ff.absolutePath}: ${ff.exists()}")

        if (ff.exists()) {
            val sConfig = JSONObject(ff.readText(Charsets.UTF_8))
            Log.d(LOG_TAG, "sConfig: $sConfig")

            val sHost = sConfig.getString("Host")
            val sTmpMsg = "sConfig[\"Host\"] = $sHost"
            Log.d(LOG_TAG, sTmpMsg)
            findViewById<TextView>(R.id.hello).text = sTmpMsg

            sConfig.put("NewKey", Hello.randNum())

            val sOutText = sConfig.toString(4)
            ff.writeText(sOutText)
        } else {
            Log.e(LOG_TAG, "My data not found!")
        }
    }

    // Tricky way to find /storage/emulated/0/
    private fun toFindRoot(): File {
        var f = applicationContext.getExternalFilesDir(null)!!
        for (i in 0..3)
            f = f.parentFile!!
        return f
    }
}
