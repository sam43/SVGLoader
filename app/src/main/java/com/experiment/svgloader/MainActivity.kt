package com.experiment.svgloader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pixplicity.sharp.Sharp
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context
import android.widget.ImageView
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils.fetchSvg(this, svgData, svg_image_view2)
        /*Sharp.loadResource(resources, R.raw.android_toy_h)
            .into(svg_image_view2)
        Sharp.loadString(svgData)
            .into(svg_image_view2)*/
        //Sharp.loadPath("https://drakewhiterock.com/wp-content/uploads/2018/03/svg-floorplan-test.svg")
    }
    companion object {
        const val svgData = "https://drakewhiterock.com/wp-content/uploads/2018/03/svg-floorplan-test.svg"
    }

}

internal object Utils {
    private var httpClient: OkHttpClient? = null

    fun fetchSvg(context: Context, url: String, target: ImageView) {
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = OkHttpClient.Builder()
                .cache(Cache(context.cacheDir, 5 * 1024 * 1014))
                .build()
        }

        val request = Request.Builder().url(url).build()
        httpClient!!.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //target.setImageDrawable(ic_launcher_background)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val stream = response.body?.byteStream()
                Sharp.loadInputStream(stream).into(target)
                stream?.close()
            }
        })
    }
}