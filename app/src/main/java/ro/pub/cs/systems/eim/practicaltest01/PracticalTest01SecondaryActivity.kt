package ro.pub.cs.systems.eim.practicaltest01

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
// Pasul Extra: Importam clasa R din pachetul aplicatiei ca sa recunoasca ID-urile
import ro.pub.cs.systems.eim.practicaltest01.R

class PracticalTest01SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_secondary)
        val totalTextView = findViewById<TextView>(R.id.total_clicks_text_view)
        val okButton = findViewById<Button>(R.id.ok_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)

        // C.1 - Corectat: Transmitem doar valorile ("TOTAL_CLICKS", 0) direct, fara etichete de argumente
        val totalClicks = intent.getIntExtra("TOTAL_CLICKS", 0)
        totalTextView.text = "Total clicks: $totalClicks"

        // Daca se apasa OK, trimitem inapoi RESULT_OK si inchidem ecranul
        okButton.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        // Daca se apasa Cancel, trimitem inapoi RESULT_CANCELED
        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
