package samuelnunes.com.sicrediteste.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import samuelnunes.com.sicrediteste.databinding.FragmentEventDetailBinding


class EventDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        return binding.root

    }


}