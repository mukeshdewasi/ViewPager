package com.example.viewpager.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.viewpager.Data.item
import com.example.viewpager.R
import com.example.viewpager.databinding.ItemIntroLayoutBinding
import java.util.concurrent.RunnableFuture

class IntroPageAdapter(var context:Context, var itemlist:MutableList<item>) :PagerAdapter() {

    lateinit var binding:ItemIntroLayoutBinding
    override fun getCount(): Int {
    return itemlist.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding= ItemIntroLayoutBinding.inflate(LayoutInflater.from(context),container,false)

        var item=itemlist[position]
        binding.tvTittle.text="${item.tittle}"
        binding.tvDesc.text="${item.disc}"
        binding.ivImage.setImageResource(item.image)
        binding.layout.setBackgroundColor(context.resources.getColor(item.color))
       // binding.layout.background = ContextCompat.getDrawable(context, R.drawable.bg_gredient2)

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View?)
    }
}