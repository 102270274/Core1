package au.edu.swin.sdmd.core1

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var opResult: Int = 0

    override fun onStart(){
        super.onStart()
        Log.i("LIFECYCLE", "onCreate")
    }

    override fun onResume(){
        super.onResume()
        Log.i("LIFECYCLE", "onResume")
    }

    override fun onPause(){
        super.onPause()
        Log.i("LIFECYCLE", "onPause")
    }

    override fun onStop(){
        super.onStop()
        Log.i("LIFECYCLE", "onStop")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("LIFECYCLE", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LIFECYCLE", "onRestart")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mediaPlayer = MediaPlayer.create(this, R.raw.team_cheer)
        //mediaPlayer.start()

        val laps = findViewById<TextView>(R.id.laps)

        if(savedInstanceState != null){
            opResult = savedInstanceState.getInt("LAPS")
            laps.text = opResult.toString()
            when(laps.text.toString().toInt()){
                in 5..9 -> laps.setTextColor(Color.RED)
                in 10..15 -> laps.setTextColor(Color.BLUE)
                else -> laps.setTextColor(Color.BLACK)
            }
        }else{
            laps.text = 0.toString()
            laps.setTextColor(Color.BLACK)
        }

        val lapAdd = findViewById<Button>(R.id.lapAdd)
        lapAdd.setOnClickListener {
            if (laps.text.toString().toInt() < 15){
                laps.text = (laps.text.toString().toInt() + 1).toString()
            }
            // Handle text colour and media player
            when(laps.text.toString().toInt()){
                in 5..9 -> laps.setTextColor(Color.RED)
                in 10..15 -> laps.setTextColor(Color.BLUE)
                15 -> mediaPlayer.start()
                else -> laps.setTextColor(Color.BLACK)
            }
            opResult = laps.text.toString().toInt()
        }

        val lapMinus = findViewById<Button>(R.id.lapMinus)
        lapMinus.setOnClickListener {
            if (laps.text.toString().toInt() > 0){
                laps.text = (laps.text.toString().toInt() - 1).toString()
            }
             // Handle text colour and media player
            when(laps.text.toString().toInt()){
                in 5..9 -> laps.setTextColor(Color.RED)
                in 10..15 -> laps.setTextColor(Color.BLUE)
                15 -> mediaPlayer.start()
                else -> laps.setTextColor(Color.BLACK)
            }
            opResult = laps.text.toString().toInt()
        }

        val lapReset = findViewById<Button>(R.id.lapReset)
        lapReset.setOnClickListener {
            laps.text = 0.toString()
            opResult = laps.text.toString().toInt()
            laps.setTextColor(Color.BLACK)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("LAPS", opResult)
        Log.i("LIFECYCLE", "saveInstanceState $opResult")
    }
}