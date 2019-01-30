package io.codetail.arcsample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var url: String? = null
        when (item.itemId) {
            R.id.menuItem_all_my_apps -> url = "https://play.google.com/store/apps/developer?id=AndroidDeveloperLB"
            R.id.menuItem_all_my_repositories -> url = "https://github.com/AndroidDeveloperLB"
            R.id.menuItem_current_repository_website -> url = "https://github.com/AndroidDeveloperLB/ArcAnimator"
        }
        if (url == null)
            return true
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(intent)
        return true
    }
}
