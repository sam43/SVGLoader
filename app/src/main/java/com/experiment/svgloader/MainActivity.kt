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
        svgMap.setMapResId(R.raw.bangladesh_low)
    }
}