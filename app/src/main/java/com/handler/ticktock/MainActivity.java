package com.handler.ticktock;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import static com.handler.ticktock.Recyclerview_adapter.myViewholder.*;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.handler.ticktock.Recyclerview_adapter.myViewholder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    Recyclerview_adapter adapter;
    ArrayList<model> mdata;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hide_navigation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.recyclerview);



        fill_data_to_recycler();
        adapter = new Recyclerview_adapter(this,mdata,viewPager2);
        viewPager2.setAdapter(adapter);



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void hide_navigation() {
        //navigation bar color change
        getWindow().setNavigationBarColor(getColor(R.color.mattblack));

//change status bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.mattblack));

    }


    private void fill_data_to_recycler() {
       mdata = new ArrayList<>();
       mdata.add(new model(String.valueOf(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.astro)),
               "https://picsum.photos/200/300",
               "Big Buck Bunny","Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... and the rabbit ain't no bunny anymore! In the typical cartoon tradition he prepares the nasty rodents a comical revenge.\\n\\nLicensed under the Creative Commons Attribution license\\nhttp://www.bigbuckbunny.org\","));
       mdata.add(new model("https://static.videezy.com/system/resources/previews/000/019/596/original/Gitarre1.mp4",
               "https://picsum.photos/200/300",
               "Elephant Dream",
               "The first Blender Open Movie from 2006"));

       mdata.add(new model("https://static.videezy.com/system/resources/previews/000/045/098/original/stockvideo_02317k.mp4",
               "https://picsum.photos/200/300",
                  "For Bigger Blazes",
               "HBO GO now works with Chromecast -- the easiest way to enjoy online video on your TV. For when "));

       mdata.add(new model("https://media.istockphoto.com/id/1331647789/video/negative-cassette-rapid-test-for-covid-19-test-result-by-using-novel-coronavirus-covid-19.mp4?s=mp4-640x640-is&k=20&c=h6jOwUKRIl-QCuyDF75J1s10qH8EIrMhWrwxQKE-RN4=",
               "https://picsum.photos/200/300",
                 "Tears of Steel",
          "Introducing Chromecast. The easiest way to enjoy online video and music on your TV—for when Batman's"));


    }





  

    @Override
    protected void onRestart() {
        super.onRestart();
        //when we restart app it will need to load everything again and it will work perfectly
        viewPager2 = findViewById(R.id.recyclerview);
        fill_data_to_recycler();
        adapter = new Recyclerview_adapter(this,mdata,viewPager2);
        viewPager2.setAdapter(adapter);
        Toast.makeText(MainActivity.this, "app restart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(MainActivity.this, "restore instance state", Toast.LENGTH_SHORT).show();
    }



}