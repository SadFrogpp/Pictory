package com.example.rxpictory.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.rxpictory.R
import com.example.rxpictory.ui.main.feed.FeedFragment
import com.example.rxpictory.ui.main.post.PostFragment
import com.example.rxpictory.ui.mypage.MypageFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var backPressedTime : Long = 0
    private lateinit var toast: Toast


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_framelayout, FeedFragment())
            commit()
        }
        mainNaviView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.menu_feed -> {
                transaction.replace(R.id.main_framelayout, FeedFragment())
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_post -> {
                transaction.replace(R.id.main_framelayout, PostFragment())
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_mypage -> {
                transaction.replace(R.id.main_framelayout, MypageFragment())
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.main_navigation_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_feed -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_framelayout, FeedFragment())
                    .commit()
            }
            R.id.menu_post -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_framelayout, PostFragment())
                    .commit()
            }
            R.id.menu_mypage -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_framelayout, MypageFragment())
                    .commit()
            }
        }

        return super.onContextItemSelected(item)
    }

    override fun onBackPressed() {
        //super.onBackPressed()

        if (System.currentTimeMillis() > backPressedTime + 2000) {
            backPressedTime = System.currentTimeMillis()
            toast = Toast.makeText(this, "뒤로 버튼을 한번 더 누르면 종료됩니다", Toast.LENGTH_SHORT)
            //Log.i("취소버튼", "${backPressedTime}")

            toast.show()
            return
        }
        if (System.currentTimeMillis() <= backPressedTime + 2000) {
            //Log.i("취소버튼", "취소완료")
            super.onBackPressed()
            toast.cancel()
        }
    }
}