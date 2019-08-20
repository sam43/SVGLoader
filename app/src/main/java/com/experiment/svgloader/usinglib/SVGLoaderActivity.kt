package com.experiment.svgloader.usinglib

import android.annotation.TargetApi
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.experiment.svgloader.R
import at.lukle.clickableareasimage.ClickableArea
import uk.co.senab.photoview.PhotoViewAttacher
import at.lukle.clickableareasimage.ClickableAreasImage
import at.lukle.clickableareasimage.OnClickableAreaClickedListener
import com.caverock.androidsvg.SVGParser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_svgloader.*
import java.util.ArrayList


class SVGLoaderActivity : AppCompatActivity(), OnClickableAreaClickedListener<State> {
    override fun onClickableAreaTouched(item: State?) {
        if (item is State) {
            val text = item.resName
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }

    private val TAG = javaClass.simpleName

    private val clickableAreas: List<ClickableArea<*>>
        get() {

            val clickableAreas = ArrayList<ClickableArea<*>>()

            clickableAreas.add(ClickableArea(600, 100, 50, 50, State("Lower Austria")))
            clickableAreas.add(ClickableArea(440, 125, 50, 50, State("Upper Austria")))
            clickableAreas.add(ClickableArea(700, 126, 50, 50, State("Vienna")))

            clickableAreas.add(ClickableArea(685, 270, 50, 50, State("Burgenland")))
            clickableAreas.add(ClickableArea(420, 350, 50, 50, State("Carinthia")))
            clickableAreas.add(ClickableArea(370, 245, 50, 50, State("Salzburg")))

            clickableAreas.add(ClickableArea(170, 280, 50, 50, State("Tyrol")))
            clickableAreas.add(ClickableArea(30, 280, 50, 50, State("Vorarlberg")))
            clickableAreas.add(ClickableArea(570, 250, 50, 50, State("Styria")))

            return clickableAreas
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_svgloader)

        // Add image
        //val image = findViewById<View>(R.id.imageView) as ImageView
        //val svg = SVGParser.
        imageView.setImageResource(R.drawable.austria_map)

        // Create your image
        val clickableAreasImage = ClickableAreasImage(PhotoViewAttacher(imageView), this)

        // Define your clickable area (pixel values: x coordinate, y coordinate, width, height) and assign an object to it
        val clickableAreas = clickableAreas
        clickableAreasImage.clickableAreas = clickableAreas
    }

    // Listen for touches on your images:

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
