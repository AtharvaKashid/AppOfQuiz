package com.example.appofquiz

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {
    private var br: BroadcastReceiver? = null
   private var radioGroup: RadioGroup? = null
    private var radioButton: RadioButton? = null
    private var start: Button? = null
    private var spinner: Spinner? = null
    private var catspinner:Spinner? = null
   private var limi = arrayOf(5,10,15)

    private var category = arrayOf(
        "Arts & Literature",
        "Film & TV",
        "Food & Drink",
        "General Knowledge",
        "Geography",
        "History",
        "Music",
        "Science",
        "Society & Culture",
        "Sport & Leisure"
    )
    var cat: String? = null
    var limit:String? = ""
    var difficulty:String? = null
    private var backpresstime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        if (br == null) {
            br = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val extras = intent.extras
                    val info = extras
                        ?.getParcelable<Parcelable>("networkInfo") as NetworkInfo?
                    val state = info!!.state
                    if (state == NetworkInfo.State.CONNECTED) {
                        setContentView(R.layout.activity_category)
                        try {
                            initcat()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else {
                        setContentView(R.layout.internet_screen)
                        window.setFlags(
                            WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN
                        )
                        Toast.makeText(
                            applicationContext,
                            "Internet connection is Off",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            val intentFilter = IntentFilter()
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            registerReceiver(br, intentFilter)
        }
    }

    private fun initcat() {
        radioGroup = findViewById(R.id.radioGroup)
        start = findViewById(R.id.start)
        spinner = findViewById(R.id.spinner)
        catspinner = findViewById(R.id.catspinner)


        val catAdapter = ArrayAdapter(this@CategoryActivity, R.layout.spinner_item, category)
        catAdapter.setDropDownViewResource(R.layout.spinner_item)
        catspinner!!.setAdapter(catAdapter)
        catspinner!!.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                cat = adapterView.getItemAtPosition(i).toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })

        val limitAdapter = ArrayAdapter(this@CategoryActivity, R.layout.spinner_item, limi)
        limitAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinner!!.setAdapter(limitAdapter)
        spinner!!.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                limit = adapterView.getItemAtPosition(i).toString()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })


        start!!.setOnClickListener(View.OnClickListener {
            if (difficulty != null) {
                val intent = Intent(this@CategoryActivity, MainActivity::class.java)
                intent.putExtra("cat", cat)
                intent.putExtra("limit", limit)
                intent.putExtra("difficulty", difficulty)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@CategoryActivity, "Select Difficulty Level", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
    fun checkButtion(v: View?) {
        val radioid = radioGroup!!.checkedRadioButtonId
        radioButton = findViewById(radioid)
        difficulty = radioButton!!.getText().toString()
    }
    override fun onBackPressed() {
        if (backpresstime + 2000 > System.currentTimeMillis()) {
            val builder = AlertDialog.Builder(this@CategoryActivity)
            builder.setMessage("Are you sure want to exit?")
            builder.setTitle("Confirmation Notice!")
            builder.setCancelable(true)
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setNegativeButton(
                "NO"
            ) { dialog, which -> dialog.cancel() }
            builder.setPositiveButton(
                "YES"
            ) { dialog, which ->
                finish()
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addCategory(Intent.CATEGORY_HOME)
                startActivity(intent)
                finish()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        } else {
            Toast.makeText(applicationContext, "Press again to exit", Toast.LENGTH_SHORT).show()
        }
        backpresstime = System.currentTimeMillis()
    }


}