package com.dicoding.batikpedia.ui

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.dicoding.batikpedia.ViewPagerAdapter
import com.dicoding.batikpedia.R
import com.dicoding.batikpedia.databinding.ActivityOnBoardingBinding

@Suppress("DEPRECATION")
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    private var mSlideViewPager: ViewPager? = null
    private var mDotLayout: LinearLayout? = null

    private lateinit var dots: Array<TextView?>
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backbtn = binding.backbtn
        val nextbtn = binding.nextbtn
        val skipbtn = binding.skipButton

        backbtn.setOnClickListener {
            if (getitem(0) > 0) {
                mSlideViewPager?.setCurrentItem(getitem(-1), true)
            }
        }
        nextbtn.setOnClickListener {
            if (getitem(0) < 3) {
                mSlideViewPager?.setCurrentItem(getitem(1), true)
            } else {
                val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        skipbtn.setOnClickListener {
            val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        mSlideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.indicator_layout)
        viewPagerAdapter = ViewPagerAdapter(this)
        mSlideViewPager?.adapter = viewPagerAdapter

        setUpIndicator(0)
        mSlideViewPager?.addOnPageChangeListener(viewListener)
    }

    private fun setUpIndicator(position: Int) {
        dots = arrayOfNulls(4)
        mDotLayout?.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]?.text = Html.fromHtml("&#8226")
            dots[i]?.textSize = 35f
            dots[i]?.setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            mDotLayout?.addView(dots[i])
        }
        dots[position]?.setTextColor(resources.getColor(R.color.active, applicationContext.theme))
    }

    private val viewListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            setUpIndicator(position)
            binding.backbtn.visibility = if (position > 0) View.VISIBLE else View.INVISIBLE
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun getitem(i: Int): Int {
        return mSlideViewPager?.currentItem?.plus(i) ?: 0
    }
}
