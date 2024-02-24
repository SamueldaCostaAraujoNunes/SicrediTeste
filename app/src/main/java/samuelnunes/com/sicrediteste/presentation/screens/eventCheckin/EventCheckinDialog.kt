package samuelnunes.com.sicrediteste.presentation.screens.eventCheckin

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.samuelnunes.utility_tool_kit.binding.visibleIf
import com.samuelnunes.utility_tool_kit.events.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import samuelnunes.com.sicrediteste.R
import samuelnunes.com.sicrediteste.databinding.DialogFragmentEventCheckinBinding
import samuelnunes.com.sicrediteste.presentation.commons.fields_validator.EmailTextRule
import samuelnunes.com.sicrediteste.presentation.commons.fields_validator.EmptyTextRule
import samuelnunes.com.sicrediteste.presentation.commons.fields_validator.NameTextRule
import samuelnunes.com.sicrediteste.presentation.commons.fields_validator.validateRule
import samuelnunes.com.sicrediteste.presentation.components.BottomSheetDialogBase


@AndroidEntryPoint
class EventCheckinDialog: BottomSheetDialogBase() {

    private val args by navArgs<EventCheckinDialogArgs>()
    private lateinit var binding: DialogFragmentEventCheckinBinding
    private val viewModel: EventCheckinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentEventCheckinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnConfirmCheckin.setOnClickListener {
                if(validator()) {
                    viewModel.eventCheckin(
                        args.eventId,
                        tietName.text.toString(),
                        tietEmail.text.toString()
                    )
                }
            }
        }
        viewModel.event.observeEvent(viewLifecycleOwner) {
            if(it) {
                Snackbar.make(binding.root, R.string.checkin_success, Snackbar.LENGTH_LONG).onTop().show()
                dismiss()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            val content = it.toString(requireContext())
            Snackbar.make(binding.root, content, Snackbar.LENGTH_SHORT).onTop().show()
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.lpiLoading.visibleIf(it)
        }

        viewModel.hasNetwork.observe(viewLifecycleOwner) {
            binding.btnConfirmCheckin.isEnabled = it
            if(!it) Snackbar.make(binding.root, R.string.without_network, Snackbar.LENGTH_SHORT).onTop().show()
        }
    }

    private fun Snackbar.onTop(): Snackbar {
        val params = view.layoutParams as CoordinatorLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        return this
    }

    private fun validator(): Boolean {
        val isName = binding.tietName.validateRule(
            rules = listOf(EmptyTextRule, NameTextRule)
        )

        val isEmail = binding.tietEmail.validateRule(
            rules = listOf(EmptyTextRule, EmailTextRule)
        )

        return isName && isEmail
    }

}