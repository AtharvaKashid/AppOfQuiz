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
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {
   private var br:BroadcastReceiver?=null
    var quizModels = ArrayList<QuizModel>()
    var questiontv: TextView? = null
    var status:TextView?=null
    var totaltv:TextView?=null
    var scoretv:TextView?=null
    var submit: Button? = null
    var restart:Button? = null
    var cat: String? = null
    var limit:String? = null
    var difficulty:String? = null
    var mainlayout: LinearLayout? = null
    var scorelayout:LinearLayout? = null
    private val idArray = intArrayOf(R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4)
    private val button = arrayOfNulls<Button>(idArray.size)
    var i = 0
    var isClick = 0
    var score = 1
    var q = 0
    val BASE_URL="https://the-trivia-api.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        if (br == null) {
            br = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val extras = intent.extras
                    val info = extras?.getParcelable<Parcelable>("networkInfo") as NetworkInfo?
                    val state = info!!.state
                    if (state == NetworkInfo.State.CONNECTED) {
                        setContentView(R.layout.activity_main)
                        try {
                            init()
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
            registerReceiver(br as BroadcastReceiver, intentFilter)
        }
    }
    fun init() {
        try {
            val intent = intent
            cat = intent.getStringExtra("cat")!!.lowercase()
            limit = intent.getStringExtra("limit")
            difficulty = intent.getStringExtra("difficulty")!!.lowercase()
            getResponse(cat!!, limit, difficulty!!)
        } catch (ignored: Exception) {
        }
        score = 1
        questiontv = findViewById(R.id.questionTV)
        status = findViewById(R.id.status)
        submit = findViewById(R.id.submit)
        restart = findViewById(R.id.restart)
        totaltv = findViewById(R.id.totaltv)
        scoretv = findViewById(R.id.score)
        mainlayout = findViewById(R.id.mainlayout)
        scorelayout = findViewById(R.id.scorelayout)
    }

    private fun getResponse(key1: String?, key2: String?, key3: String?) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val requestInteface: RequestInterface = retrofit.create(RequestInterface::class.java)
        val call: Call<List<QuizModel?>?> = requestInteface.getJson(key1, key2, key3)

        call.enqueue(object : Callback<List<QuizModel?>?> {

            override fun onResponse(
                call: Call<List<QuizModel?>?>,
                response: Response<List<QuizModel?>?>
            ) {
                if (response.body() != null) {
                    quizModels = ArrayList<QuizModel>(response.body())

                }
                try {
                    val strArray = arrayOfNulls<String>(4)
                    strArray[0] = quizModels[q].getIncorrectAnswers()!![0]
                    strArray[1] = quizModels[q].getIncorrectAnswers()!![1]
                    strArray[2] = quizModels[q].getIncorrectAnswers()!![2]
                    strArray[3] = quizModels[q].getCorrectAnswer()
                    Arrays.asList(*strArray).shuffle()
                    questiontv!!.text = quizModels[q].getQuestion()
                    status!!.text=(q.toString() + getString(R.string.slash).toString() + quizModels.size)
                    i = 0
                    while (i < idArray.size) {
                        button[i] = findViewById(idArray.get(i))
                        button[i]!!.setText(strArray[i])
                        button[i]!!.setBackground(getDrawable(R.drawable.bgbtn))
                        button[i]!!.setOnClickListener(View.OnClickListener { view ->
                            if (isClick == 0) {
                                isClick = 1
                                when (view.id) {
                                    R.id.btn1 -> {
                                        button[0]!!.setBackground(getDrawable(R.drawable.selectbtn))
                                        try {
                                            score =
                                                if (strArray[0] == quizModels[q].getCorrectAnswer()) {
                                                    score + 5
                                                } else {
                                                    score
                                                }
                                        } catch (w:Exception) {
                                            w.printStackTrace()
                                        }
                                    }
                                    R.id.btn2 -> {
                                        button[1]!!.setBackground(getDrawable(R.drawable.selectbtn))
                                        try {
                                            score =
                                                if (strArray[1] == quizModels[q].getCorrectAnswer()) {
                                                    score + 5
                                                } else {
                                                    score
                                                }
                                        } catch (w: Exception) {
                                            w.printStackTrace()
                                        }
                                    }
                                    R.id.btn3 -> {
                                        button[2]!!.setBackground(getDrawable(R.drawable.selectbtn))
                                        try {
                                            score =
                                                if (strArray[2] == quizModels[q].getCorrectAnswer()) {
                                                    score + 5
                                                } else {
                                                    score
                                                }
                                        } catch (w: Exception) {
                                            w.printStackTrace()
                                        }
                                    }
                                    R.id.btn4 -> {
                                        button[3]!!.setBackground(getDrawable(R.drawable.selectbtn))
                                        try {
                                            score =
                                                if (strArray[3] == quizModels[q].getCorrectAnswer()) {
                                                    score + 5
                                                } else {
                                                    score
                                                }
                                        } catch (w: Exception) {
                                            w.printStackTrace()
                                        }
                                    }
                                }
                                if (q < quizModels.size - 1) {
                                    q = q + 1
                                    isClick = 0
                                    getResponse(cat, limit, difficulty)
                                } else {
                                    submit!!.visibility = View.VISIBLE
                                    submitbtn()
                                }
                            }
                        })
                        i++
                    }

                } catch (ignored: Exception) {
                   ignored.printStackTrace()
                }
            }
            override fun onFailure(call: Call<List<QuizModel?>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun submitbtn() {
        submit!!.setOnClickListener {
            mainlayout!!.visibility = View.GONE
            scorelayout!!.visibility = View.VISIBLE
            totaltv!!.text= (q + 1).toInt().toString()
            scoretv!!.text = (score.toString() + "/" + (q + 1) * 5)
            restart()
        }
    }
    private fun restart() {
        restart!!.setOnClickListener {
            val intent = Intent(this@MainActivity, CategoryActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}

