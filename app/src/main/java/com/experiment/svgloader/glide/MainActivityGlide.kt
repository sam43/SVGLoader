package com.experiment.svgloader.glide

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import android.app.Activity
import android.content.ContentResolver
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.util.Preconditions
import com.experiment.svgloader.R
import kotlinx.android.synthetic.main.activity_main.*

/** Displays an SVG image loaded from an android raw resource.  */
class MainActivityGlide : Activity() {

    private var imageViewRes: ImageView? = null
    private var imageViewNet: ImageView? = null
    private var requestBuilder: RequestBuilder<PictureDrawable>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //imageViewRes = findViewById<ImageView>(R.id.svg_image_view1)
        imageViewNet = findViewById<ImageView>(R.id.svg_image_view2)

        requestBuilder = Glide.with(this)
            .`as`(PictureDrawable::class.java)
            .error(R.drawable.image_error)
            .transition(withCrossFade())
            .listener(SvgSoftwareLayerSetter())
    }

    override fun onStart() {
        super.onStart()
        reload()
    }

    fun clearCache(v: View) {
        Log.w(TAG, "clearing cache")
        val glideRequests = Glide.with(this)
        imageViewRes?.let { glideRequests.clear(it) }
        imageViewNet?.let { glideRequests.clear(it) }
        Glide.get(this).clearMemory()
        val cacheDir = Preconditions.checkNotNull(Glide.getPhotoCacheDir(this))
        if (cacheDir.isDirectory) {
            for (child in cacheDir.listFiles()!!) {
                if (!child.delete()) {
                    Log.w(TAG, "cannot delete: $child")
                }
            }
        }
        reload()
    }

    fun cycleScaleType(v: View) {
        val curr = imageViewRes!!.scaleType
        Log.w(TAG, "cycle: current=$curr")
        val all = ImageView.ScaleType.values()
        val nextOrdinal = (curr.ordinal + 1) % all.size
        val next = all[nextOrdinal]
        Log.w(TAG, "cycle: next=$next")
        imageViewRes!!.scaleType = next
        imageViewNet!!.scaleType = next
        reload()
    }

    private fun reload() {
        Log.w(TAG, "reloading")
        //(findViewById(R.id.button) as TextView).text = getString(R.string.scaleType, imageViewRes!!.scaleType)
        //button.text = getString(R.string.scaleType, imageViewRes!!.scaleType)
        loadRes()
        loadNet()
    }

    private fun loadRes() {
        val uri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://"
                    + packageName
                    + "/"
                    + R.raw.android_toy_h
        )
        requestBuilder!!.load(uri).into(imageViewRes!!)
    }

    private fun loadNet() {
        val uri = Uri.parse("https://drakewhiterock.com/wp-content/uploads/2018/03/svg-floorplan-test.svg")
        requestBuilder!!.load(uri).into(imageViewNet!!)
    }

    companion object {
        private val TAG = "SVGActivity"
    }
}