package com.example.featuretimer.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.featuretimer.R
import com.example.featuretimer.data.TimestampProvider
import com.example.featuretimer.databinding.FragmentTimerScreenBinding
import com.example.featuretimer.utils.ElapsedTimeCalculator
import com.example.featuretimer.utils.StopwatchStateCalculator
import com.example.featuretimer.utils.TimestampMillisecondsFormatter
import kotlinx.coroutines.*


class TimerScreenFragment : Fragment(R.layout.fragment_timer_screen) {

    private val binding: FragmentTimerScreenBinding by viewBinding()

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val timerScreenViewModel: TimerScreenViewModel by viewModels{
        TimerScreenViewModel.NewsViewModelFactory(
            StopwatchStateHolder(
                StopwatchStateCalculator(
                    timestampProvider,
                    ElapsedTimeCalculator(timestampProvider)
                ),
                ElapsedTimeCalculator(timestampProvider),
                TimestampMillisecondsFormatter()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTimer()

        with(binding) {
            buttonStart.setOnClickListener {
                timerScreenViewModel.start()
            }
            buttonPause.setOnClickListener {
                timerScreenViewModel.pause()
            }
            buttonStop.setOnClickListener {
                timerScreenViewModel.stop()
            }
        }
    }

    private fun getTimer() {
        viewLifecycleOwner.lifecycleScope.launch {
            timerScreenViewModel.ticker.collect{
                binding.textTime.text = it
            }
        }
    }
}






