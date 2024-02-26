package samuelnunes.com.sicrediteste.presentation.screens.eventDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import samuelnunes.com.sicrediteste.R
import samuelnunes.com.sicrediteste.data.local.entitys.EventEntity
import samuelnunes.com.sicrediteste.databinding.FragmentEventDetailBinding
import samuelnunes.com.sicrediteste.presentation.MainViewModel
import timber.log.Timber


@AndroidEntryPoint
class EventDetailsFragment : Fragment() {

    private val args by navArgs<EventDetailsFragmentArgs>()
    private val viewModel: EventDetailsViewModel by viewModels()
    private val sharedViewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentEventDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEvent(args.eventId)
        viewModel.event.observe(viewLifecycleOwner) {
            binding.apply {
                ivEvent.load(it.image) {
                    listener(
                        onError = { _, result ->
                            Timber.e(result.throwable, "Erro ao carregar imagem: ${it.image}")
                            ivEvent.visibility = View.GONE
                        }
                    )
                }
                tvDescription.text = it.description
                btnChecking.setOnClickListener { _ ->
                    val direction =
                        EventDetailsFragmentDirections.actionEventDetailsFragmentToEventCheckinDialog(
                            it.id
                        )
                    findNavController().navigate(direction)
                }
                btnTraceRoute.setOnClickListener { _ ->
                    startActivity(it.getLocationIntent())
                }
                btnAddToCalendar.isEnabled = it.date != null
                btnAddToCalendar.setOnClickListener { _ ->
                    startActivity(it.getCreateEventIntent())
                }
                fab.setOnClickListener { _ ->
                    startActivity(Intent.createChooser(it.shareEventIntent(), getString(R.string.share_text_by)))
                }
            }

            sharedViewModel.networkConnectivity.observe(viewLifecycleOwner) {
                binding.btnChecking.isEnabled = it
            }
        }
    }

    private fun EventEntity.getLocationIntent(): Intent = Intent(Intent.ACTION_VIEW,
        Uri.parse(buildString {
            append("geo:")
            append(latitude)
            append(",")
            append(longitude)
            append("?q=")
            append(latitude)
            append(",")
            append(longitude)
        })
    )

    private fun EventEntity.getCreateEventIntent(): Intent = Intent(Intent.ACTION_INSERT).apply {
        type = "vnd.android.cursor.item/event"
        putExtra("allDay", true)
        putExtra("title", title)
        putExtra("beginTime", date)
        putExtra("eventLocation", "$latitude, $longitude")
    }

    private fun EventEntity.shareEventIntent(): Intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, buildString {
            append(title)
            append("\n\n")
            append(description)
            append("\n")
            append("Localização: $latitude, $longitude")
        });
    }



}