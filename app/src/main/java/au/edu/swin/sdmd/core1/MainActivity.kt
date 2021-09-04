package au.edu.swin.sdmd.core1

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
        /*
        savedInstanceState?.let{
            opResult = it.getInt("LAPS")
            laps.text = opResult.toString()
        }
        */
        if(savedInstanceState != null){
            opResult = savedInstanceState.getInt("LAPS")
            laps.text = opResult.toString()
        }else{
            laps.text = 0.toString()
        }

        val lapAdd = findViewById<Button>(R.id.lapAdd)
        lapAdd.setOnClickListener {
            laps.text = (laps.text.toString().toInt() + 1).toString()
            opResult = laps.text.toString().toInt()
            if(laps.text.toString().toInt() == 15){
                mediaPlayer.start()
            }
        }

        val lapMinus = findViewById<Button>(R.id.lapMinus)
        lapMinus.setOnClickListener {
            laps.text = (laps.text.toString().toInt() - 1).toString()
            opResult = laps.text.toString().toInt()
            if(laps.text.toString().toInt() == 15){
                mediaPlayer.start()
            }
        }

        val lapReset = findViewById<Button>(R.id.lapReset)
        lapReset.setOnClickListener {
            laps.text = 0.toString()
            opResult = laps.text.toString().toInt()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("LAPS", opResult)
        Log.i("LIFECYCLE", "saveInstanceState $opResult")
    }
}