package com.example.viewpager

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.viewpager.Adapter.IntroPageAdapter
import com.example.viewpager.Data.item
import com.example.viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
        lateinit var binding :ActivityMainBinding
        private var itemlist = mutableListOf<item>()
        lateinit var pagerAdapter:IntroPageAdapter
        private var currentpage=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemlist.add(item(1, "Discover", "Look for things around you", R.drawable.gpsnavigation, R.color.white))
        itemlist.add(item(2, "Offers", "Find great offers while you discover", R.drawable.rupee,R.color.white))
        itemlist.add(item(3, "Reward", "Free rechargs coupons & more", R.drawable.medal, R.color.white))

        pagerAdapter=IntroPageAdapter(this, itemlist)
        binding.viewPager.adapter=pagerAdapter


        binding.btnNext.setOnClickListener {
            if (currentpage<pagerAdapter.count-1){
                currentpage++
                binding.viewPager.currentItem=currentpage
            }else{
                var intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }

        binding.btnSkip.setOnClickListener {

               var intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }
        binding.viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                currentpage=position
                if (currentpage==pagerAdapter.count-1){
                    binding.btnNext.setText("Get Started")
                }else{
                    binding.btnNext.setText("Next")
                }
                updateIndicator(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


    }
    private fun updateIndicator(currentpos:Int){
        binding.indicator.removeAllViews()
        var layoutparam=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutparam.setMargins(16,0,0,0  )

        for (i in 0 until pagerAdapter.count){
            var imageView=ImageView(this)

            if (i==currentpos)
                imageView.setImageDrawable(ContextCompat.getDrawable(
                    this,

                    R.drawable.active_indicator
                ))else
                    imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.indicator

                        )
                    )
            binding.indicator.addView(imageView,layoutparam)
        }

    }
}