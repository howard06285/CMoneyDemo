package com.shigaga.cmoneydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shigaga.cmoneydemo.ui.first_page.FirstFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FirstFragment.newInstance())
                    .commitNow()
        }
    }
}
