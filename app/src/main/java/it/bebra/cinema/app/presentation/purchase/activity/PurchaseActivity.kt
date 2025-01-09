package it.bebra.cinema.app.presentation.purchase.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import dagger.hilt.android.AndroidEntryPoint
import it.bebra.cinema.app.common.ui.edgeToEdge
import it.bebra.cinema.app.common.ui.padding
import it.bebra.cinema.app.presentation.purchase.viewmodel.PurchaseViewModel
import it.bebra.cinema.databinding.ActivityPurchaseBinding
import it.bebra.cinema.domain.Resource.Error
import it.bebra.cinema.domain.Resource.Success
import it.bebra.cinema.domain.dto.ticket.TicketCreateRequest
import kotlin.properties.Delegates

@AndroidEntryPoint
class PurchaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPurchaseBinding
    private val vm: PurchaseViewModel by viewModels()

    private var sessionId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEdgeToEdge()

        handleIntent(intent)

        setupObservers()
        setupListeners()
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
            binding.root.padding(statusBars())
        }
    }

    private fun handleIntent(intent: Intent) {
        intent.extras?.let {
            this.sessionId = it.getInt("sessionId")
        }
    }

    private fun setupListeners() {
        binding.purchaseBtn.setOnClickListener {
            val rowInput = binding.rowInputLayout.editText?.text?.trim().toString()
            val placeInput = binding.placeInputLayout.editText?.text?.trim().toString()

            val row = rowInput.toIntOrNull()
            val place = placeInput.toIntOrNull()

            if (row == null || place == null) {
                Toast.makeText(this, "Укажите числовые значения", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            vm.createTicket(TicketCreateRequest(sessionId, row, place))
        }

        binding.cancelBtn.setOnClickListener {
            finish()
        }
    }

    private fun setupObservers() {
        vm.purchaseResultFlow.observe(this) {
            when (it) {
                is Success -> {
                    Toast.makeText(this, "Билет успешно куплен", Toast.LENGTH_SHORT).show()
                    finish()
                }

                is Error -> Toast.makeText(this, "Произогла ошибка", Toast.LENGTH_SHORT).show()

                else -> Unit
            }
        }
    }
}