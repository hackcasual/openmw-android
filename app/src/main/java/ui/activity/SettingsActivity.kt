/*
    Copyright (C) 2019 Ilya Zhuravlev

    This file is part of OpenMW-Android.

    OpenMW-Android is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    OpenMW-Android is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with OpenMW-Android.  If not, see <https://www.gnu.org/licenses/>.
*/

package ui.activity

import com.libopenmw.openmw.R

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import file.GameInstaller
import kotlinx.android.synthetic.main.activity_settings.*
import android.view.MenuItem
import java.io.File


import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.content.Intent
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceGroup

class FragmentGameSettings : PreferenceFragment(), OnSharedPreferenceChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.game_settings)
/*
        findPreference("game_settings_game_mechanics").setOnPreferenceClickListener {
            getPreferenceScreen().removeAll()
            addPreferencesFromResource(R.xml.gs_game_mechanics)
            true
        }

        findPreference("game_settings_visuals").setOnPreferenceClickListener {
            getPreferenceScreen().removeAll()
            addPreferencesFromResource(R.xml.gs_visuals)
            true
        }
*/
        findPreference("game_settings_game_mechanics").setOnPreferenceClickListener {
            val intent = Intent(activity, Game_Mechanics_SettingsActivity::class.java)
            this.startActivity(intent)
            true
        }
/*
        findPreference("game_settings_visuals").setOnPreferenceClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            this.startActivity(intent)
            true
        }

        findPreference("game_settings_camera").setOnPreferenceClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            this.startActivity(intent)
            true
        }

        findPreference("game_settings_interface").setOnPreferenceClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            this.startActivity(intent)
            true
        }

        findPreference("game_settings_bug_fixes").setOnPreferenceClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            this.startActivity(intent)
            true
        }

        findPreference("game_settings_miscellaneous").setOnPreferenceClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            this.startActivity(intent)
            true
        }

        findPreference("game_settings_engine").setOnPreferenceClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            this.startActivity(intent)
            true
        }
*/
       preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        updatePreference(findPreference(key), key)
    }

    private fun updatePreference(preference: Preference?, key: String) {
        if (preference == null)
            return
    }

}

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.settings_toolbar))

        // Enable the "back" icon in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
/*
        // Switch tabs between categories
        settings_tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {	
                settings_flipper.displayedChild = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
*/

        fragmentManager.beginTransaction()
            .replace(R.id.settings_frame, FragmentGameSettings()).commit()
    }

    /**
     * Makes the "back" icon in the actionbar perform the back operation
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}



class FragmentGameSettingsPage(val res: Int) : PreferenceFragment(), OnSharedPreferenceChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(res)
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
    }
}

class Game_Mechanics_SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.settings_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fragmentManager.beginTransaction().replace(R.id.settings_frame, FragmentGameSettingsPage(R.xml.gs_game_mechanics)).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}