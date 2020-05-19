package br.com.marvel.dayNight.presentation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.marvel.base.BaseDialogFragment
import br.com.marvel.dayNight.R
import br.com.marvel.dayNight.databinding.FragmentDayNightBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class DayNightBottomSheetDialog : BaseDialogFragment() {

    private val vm by appViewModel<DayNightViewModel>()
    private lateinit var binding: FragmentDayNightBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(this.context!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day_night, container, true)
        binding.vm = vm
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO improve to TwoWayDataBinding
        binding.themeGroup.setOnCheckedChangeListener { _, checkedId ->
            vm.onThemeSelected(checkedId)
        }
    }

    private fun setupObservers() {
        vm.selectCheck.observe(this, Observer {
            when (it) {
                SelectedCheck.SelectThemeLight -> {
                    binding.themeLight.isChecked = true
                }
                SelectedCheck.SelectThemeDark -> {
                    binding.themeDark.isChecked = true
                }
                SelectedCheck.SelectThemeForBattery -> {
                    binding.themeBattery.isChecked = true
                }
                SelectedCheck.SelectThemeSystem -> {
                    binding.themeSystem.isChecked = true
                }
                else -> { }
            }
        })
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        activity?.recreate()
    }
}
