package samuelnunes.com.sicrediteste.presentation.screens.eventFeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.samuelnunes.utility_tool_kit.binding.goneIf
import dagger.hilt.android.AndroidEntryPoint
import samuelnunes.com.sicrediteste.R
import samuelnunes.com.sicrediteste.databinding.FragmentEventFeedBinding
import samuelnunes.com.sicrediteste.presentation.extensions.onTop

@AndroidEntryPoint
class EventFeedFragment : Fragment() {

    private val viewModel: EventFeedViewModel by viewModels()
    private lateinit var binding: FragmentEventFeedBinding

    private val eventListAdapter = EventListAdapter {
        val direction = EventFeedFragmentDirections.actionEventFeedFragmentToEventDetailsFragment(it.id, it.title ?: "Detalhes")
        findNavController().navigate(direction)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateEventList()
        loadingState()
        errorNotify()
    }

    private fun populateEventList() {
        binding.apply {
            rvEvents.adapter = eventListAdapter
        }
        viewModel.events.observe(viewLifecycleOwner) {
            eventListAdapter.submitList(it)
        }
    }

    private fun loadingState() {
        viewModel.loading.observe(viewLifecycleOwner, binding.srlContainer::setRefreshing)
        binding.srlContainer.setOnRefreshListener {
            viewModel.fetchEvents()
        }
    }

    private fun errorNotify() {
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Snackbar.make(binding.root, error.toString(requireContext()), Snackbar.LENGTH_SHORT).show()
        }
    }


}