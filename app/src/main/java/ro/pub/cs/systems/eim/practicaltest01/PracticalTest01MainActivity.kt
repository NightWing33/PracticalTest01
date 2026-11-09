package ro.pub.cs.systems.eim.practicaltest01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01MainActivity : AppCompatActivity() {

    // Aici declarăm TOATE cele 4 componente (le aveai doar pe cele două de jos)
    private lateinit var leftTextField: EditText
    private lateinit var rightTextField: EditText
    private lateinit var leftButton: Button
    private lateinit var rightButton: Button
    private lateinit var navigateButton: Button

    // C.2 - Mecanismul pentru prinderea rezultatului (OK/Cancel) si afisarea unui Toast
    private val secondaryActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Rezultat primit: OK", Toast.LENGTH_SHORT).show()
        } else if (result.resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "Rezultat primit: Cancel", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_main)

        // A.2 - Legătura cu componentele grafice din XML
        leftTextField = findViewById(R.id.left_text_field)
        rightTextField = findViewById(R.id.right_text_field)
        leftButton = findViewById(R.id.left_button)
        rightButton = findViewById(R.id.right_button)
        navigateButton = findViewById(R.id.navigate_to_secondary_activity_button)

        // B.1 - Incrementare număr la apăsarea butonului din stânga
        leftButton.setOnClickListener {
            val count = leftTextField.text.toString().toIntOrNull() ?: 0
            leftTextField.setText((count + 1).toString())
        }

        // B.1 - Incrementare număr la apăsarea butonului din dreapta
        rightButton.setOnClickListener {
            val count = rightTextField.text.toString().toIntOrNull() ?: 0
            rightTextField.setText((count + 1).toString())
        }

        // C.2 - Lansarea activitatii secundare cu trimiterea sumei totale
        navigateButton.setOnClickListener {
            val leftClicks = leftTextField.text.toString().toIntOrNull() ?: 0
            val rightClicks = rightTextField.text.toString().toIntOrNull() ?: 0
            val total = leftClicks + rightClicks

            // Folosim intentia implicita definita prin actiunea din Manifest
            val intent = Intent("ro.pub.cs.systems.eim.practicaltest01.intent.action.PracticalTest01SecondaryActivity")
            intent.putExtra("TOTAL_CLICKS", total)
            secondaryActivityLauncher.launch(intent)
        }
    }

    // B.2.b - Salvarea manuală a stării în Bundle înainte ca activitatea să fie distrusă
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("LEFT_COUNT", leftTextField.text.toString())
        outState.putString("RIGHT_COUNT", rightTextField.text.toString())
    }

    // B.2.b - Restaurarea manuală a stării salvate
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey("LEFT_COUNT")) {
            leftTextField.setText(savedInstanceState.getString("LEFT_COUNT"))
        }
        if (savedInstanceState.containsKey("RIGHT_COUNT")) {
            rightTextField.setText(savedInstanceState.getString("RIGHT_COUNT"))
        }
    }
}
