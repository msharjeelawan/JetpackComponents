package com.example.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.os.SystemClock
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.kotlintest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var running:Boolean=false;
    var offset:Long=0;
    lateinit var chronometer:Chronometer;
    private lateinit var binding: ActivityMainBinding
    //const
    var RUNNING="running"
    var OFFSET="offset"
    var BASETIME="basetime"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(binding.toolbar)


        val  navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        var builder = AppBarConfiguration.Builder(navController.graph)
        val drawer = findViewById<DrawerLayout>(R.id.drawer)
        builder.setOpenableLayout(drawer)
        var appBarConfiguration = builder.build()
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_appbar)
//        bottomNavigationView.setupWithNavController(navController)

        val navigationView = findViewById<NavigationView>(R.id.navigation)
        navigationView.setupWithNavController(navController)

//        chronometer = findViewById<Chronometer>(R.id.chronometer)
//
//        if(savedInstanceState!=null){
//            Log.v("error","bundle not null")
//            running = savedInstanceState.getBoolean(RUNNING)
//            offset = savedInstanceState.getLong(OFFSET)
//            Log.v("error",running.toString())
//            Log.v("error",offset.toString())
//            if(running){
//                chronometer.base=savedInstanceState.getLong(BASETIME)
//                chronometer.start()
//            }else
//                setBaseTime()
//        }

//
//        findViewById<Button>(R.id.start).setOnClickListener(View.OnClickListener {
//            if (!running){
//                setBaseTime()
//                chronometer.start()
//                running=true
//            }
//        })
//        findViewById<Button>(R.id.stop).setOnClickListener(View.OnClickListener {
//            if (running){
//                setOffSet()
//            }
//        })
//        findViewById<Button>(R.id.reset).setOnClickListener(View.OnClickListener {
//            offset=0
//            chronometer.base=SystemClock.elapsedRealtime()-offset
//        })


        // var spinner:Spinner = findViewById<Spinner>(R.id.spinner);
        //spinner.onItemSelectedListener=this
        //var list= arrayOf("Android","IOS","Flutter","React Native")
        //var coursesPrice=arrayOf(100,200,99,30)
        //var adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);

        //spinner.adapter=adapter
        // var btn:Button = findViewById<Button>(R.id.button)
        // var text:TextView=findViewById(R.id.textView)
        // btn.setOnClickListener(View.OnClickListener {
//            Log.v("ee",spinner.selectedItem.toString())
//            Log.v("ee",spinner.selectedItemPosition.toString())
//            text.text=coursesPrice[spinner.selectedItemPosition].toString()
      //  })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        if(menu==null){
            Log.v("menu","menu null")
        }
        menuInflater.inflate(R.menu.toolbar_menu,menu)

      return  super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var navController = findNavController(R.id.fragment_container)

        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    fun setBaseTime(){
//        print(SystemClock.elapsedRealtime())
//        Log.v("base",SystemClock.elapsedRealtime().toString())
        chronometer.base=SystemClock.elapsedRealtime()-offset
    }

    fun setOffSet(){
        offset=SystemClock.elapsedRealtime() - chronometer.base
        running=false
        chronometer.stop()
        Log.v("offset",offset.toString())

    }


    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
        Log.v("ee","nothing")
    }
    override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long){
        Log.v("ee","selected")
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putBoolean(RUNNING,running)
//        outState.putLong(OFFSET,offset)
//        outState.putLong(BASETIME,chronometer.base)
//        super.onSaveInstanceState(outState)
//    }

//    override fun onRestart() {
//        super.onRestart()
//        if (running){
//            setBaseTime()
//            chronometer.start()
//        }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if(running){
//            offset=SystemClock.elapsedRealtime() - chronometer.base
//            chronometer.stop()
//        }
//    }

}



