package com.handler.ticktock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;
import java.util.logging.Handler;
import java.util.zip.Inflater;

public class Recyclerview_adapter extends RecyclerView.Adapter<Recyclerview_adapter.myViewholder> {
    private List<model> mData;
    private Context mcontext;
    private ViewPager2 ViewPager2;
    public static final String TAG = "Recylerview";

    Recyclerview_adapter(Context mcontext, List<model> data, ViewPager2 viewPager2) {
        this.mcontext = mcontext;
        this.mData = data;
        this.ViewPager2 = viewPager2;
    }


    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.tick_tock_item, parent, false);
        return new myViewholder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerview_adapter.myViewholder holder, int position) {


        holder.tick_video.setVideoURI(Uri.parse(mData.get(position).getVideo()));

        holder.tick_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                float screenRatio = holder.tick_video.getWidth() / (float)
                        holder.tick_video.getHeight();
                float scaleX = videoRatio / screenRatio;
                if (scaleX >= 1f) {
                    holder.tick_video.setScaleX(scaleX);
                } else {
                    holder.tick_video.setScaleY(1f / scaleX);
                }

            }
        });

        holder.tick_video.start();

        Glide.with(mcontext)
                .load(mData.get(position).getProfile_image())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .override(150, 150)
                .into(holder.profile_image);

        holder.username.setText(mData.get(position).getUsername());
        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mcontext, "start new activity give the data to it", Toast.LENGTH_SHORT).show();
              /* mData.get(position).getUrl();
                load the url into something and url will come with api data which will be
                set with the video and profile
                instagram opens new activity or bottom sheet where he show all
                the userprofile user photo,reels and bottom navigation or tab layout become hidden  */

            }
        });

        holder.description.setText(mData.get(position).getVideo_description());
        //handle events on viewpager2
        ViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                holder.tick_video.start();
                holder.pause.setVisibility(View.INVISIBLE);
                holder.play.setVisibility(View.INVISIBLE);
                Log.e(TAG, "Method onPageSelected  :" + position);//give off the page position


            }


        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    //click event handle here
    public class myViewholder extends RecyclerView.ViewHolder {

        private VideoView tick_video;
        private ImageView profile_image;
        private TextView  username;
        private TextView  description;
        private ImageView play;
        private ImageView pause;

        @SuppressLint("ClickableViewAccessibility")
        public myViewholder(View rowItem) {
            super(rowItem);

            tick_video = itemView.findViewById(R.id.tick_video);
            profile_image = itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.Username);
            description = itemView.findViewById(R.id.description);
            play = itemView.findViewById(R.id.play_img);
            pause = itemView.findViewById(R.id.pause_img);

            //both are invisible by  initially
            pause.setVisibility(View.INVISIBLE);
            play.setVisibility(View.INVISIBLE);
            //for description scrolling
            description.setMovementMethod(new ScrollingMovementMethod());



            profile_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mcontext, getAdapterPosition() + "profile image  clicked", Toast.LENGTH_SHORT).show();
                }
            });

            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mcontext, "username clicked", Toast.LENGTH_SHORT).show();
                }
            });

            tick_video.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (tick_video.isPlaying()) {
                        tick_video.pause();
                        play.setVisibility(View.VISIBLE);
                        pause.setVisibility(View.INVISIBLE);
                        pause.setImageResource(R.drawable.ic_baseline_pause_24);
                        Toast.makeText(mcontext, "paused", Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        tick_video.start();

                        pause.setVisibility(View.INVISIBLE);
                        play.setVisibility(View.INVISIBLE);
                        play.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        Toast.makeText(mcontext, "play", Toast.LENGTH_SHORT).show();

                        return false;
                    }
                }
            });
        }


    }//myviewholder

}//recycler adapter

