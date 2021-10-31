package com.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsFragment : Fragment() {
    private var notesArray: NotesArray? = null
    private var notesSettings: NotesSettings? = null
    var cardSwitch: SwitchMaterial? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_settings, container, false)
        cardSwitch = v.findViewById(R.id.card_switch)
        cardSwitch?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                notesSettings!!.set.currentViewOfNotes = NotesSettings.CARD_VIEW
            } else {
                notesSettings!!.set.currentViewOfNotes = NotesSettings.HEADER_VIEW
            }
            notesSettings!!.setSettingsInSharedPrefs()
        })
        if (notesSettings!!.set.currentViewOfNotes == NotesSettings.CARD_VIEW) {
            cardSwitch?.setChecked(true)
        } else {
            cardSwitch?.setChecked(false)
        }
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(notesArray: NotesArray?, notesSettings: NotesSettings?): SettingsFragment {
            val fragment = SettingsFragment()
            fragment.notesArray = notesArray
            fragment.notesSettings = notesSettings
            return fragment
        }
    }
}