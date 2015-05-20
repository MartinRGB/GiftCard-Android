package com.example.martinrgb.giftcard_android;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringChain;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;


public class MainActivity extends Activity {

    private static final SpringConfig config = SpringConfig.fromBouncinessAndSpeed(0, 2);
    private static final SpringConfig config2 = SpringConfig.fromOrigamiTensionAndFriction(5, 10);
    private SpringSystem mSpringSystem;
    private Spring mSpring;
    private Spring mSpring1;
    private Spring mSpring2;
    private Spring mSpring3;
    private Spring mSpring4;
    private Spring mSpring5;
    private ImageView ml1;
    private ImageView ml2;
    private ImageView ml3;
    private ImageView ml4;
    private ImageView ml5;
    private ImageView mpricelabel;
    private Button mframebutton;

    private final SpringChain mSpringChain = SpringChain.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //wave
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ObjectAnimator animator = ObjectAnimator.ofFloat(findViewById(R.id.wave1), "x", 0f, 250f);
                animator.setDuration(15000);
                animator.start();
                animator.setRepeatCount(Animation.INFINITE);
                animator.setInterpolator(new LinearInterpolator());

            }
        }, 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ObjectAnimator animator = ObjectAnimator.ofFloat(findViewById(R.id.wave2), "x", 0f, 200f);
                animator.setDuration(15000);
                animator.start();
                animator.setRepeatCount(Animation.INFINITE);
                animator.setInterpolator(new LinearInterpolator());

            }
        }, 0);

        // 为了延迟
        // 文字
        ml1 = (ImageView) findViewById(R.id.l1);
        ml2 = (ImageView) findViewById(R.id.l2);
        ml3 = (ImageView) findViewById(R.id.l3);
        ml4 = (ImageView) findViewById(R.id.l4);
        ml5 = (ImageView) findViewById(R.id.l5);
        mpricelabel = (ImageView) findViewById(R.id.pricelabel);
        mframebutton = (Button) findViewById(R.id.framebutton);


        mSpringSystem = SpringSystem.create();

        mSpring = mSpringSystem.createSpring();
        mSpring1 = mSpringSystem.createSpring();
        mSpring2 = mSpringSystem.createSpring();
        mSpring3 = mSpringSystem.createSpring();
        mSpring4 = mSpringSystem.createSpring();
        mSpring5 = mSpringSystem.createSpring();

        //mspring随便设置的
        mSpring.addListener(new ViewSpringListener(mpricelabel));
        mSpring1.addListener(new ViewSpringListener(ml1));
        mSpring2.addListener(new ViewSpringListener(ml2));
        mSpring3.addListener(new ViewSpringListener(ml3));
        mSpring4.addListener(new ViewSpringListener(ml4));
        mSpring5.addListener(new ViewSpringListener(ml5));


        mSpring.setSpringConfig(config);
        mSpring1.setSpringConfig(config);
        mSpring2.setSpringConfig(config);
        mSpring3.setSpringConfig(config);
        mSpring4.setSpringConfig(config);
        mSpring5.setSpringConfig(config);





        //设置起始位置
        findViewById(R.id.input).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                findViewById(R.id.input).setX(1085);
            }
        });

        findViewById(R.id.checkout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                findViewById(R.id.checkout).setX(1565);
            }
        });

        //帧动画&延迟mSpring数值
        mframebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationDrawable ad = (AnimationDrawable) mframebutton.getBackground();
                ad.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        findViewById(R.id.framebutton).setOnClickListener(null);
                    }
                }, 42 * 38);

                //也可以放进去，但是文本异常
                mSpring.setEndValue(0f);
                mSpring1.setEndValue(1f);
                mSpring2.setEndValue(1f);
                mSpring3.setEndValue(1f);
                mSpring4.setEndValue(1f);
                mSpring5.setEndValue(1f);
            }
        });

        findViewById(R.id.l1).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                findViewById(R.id.l1).setX(1000);
            }
        });
        findViewById(R.id.l2).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                findViewById(R.id.l2).setX(1000);
            }
        });

        findViewById(R.id.l3).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                findViewById(R.id.l3).setX(1000);
            }
        });

        findViewById(R.id.l4).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                findViewById(R.id.l4).setX(1000);
            }
        });

        findViewById(R.id.l5).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                findViewById(R.id.l5).setX(1000);
            }
        });


    }
    public class ViewSpringListener implements SpringListener {
        View _view;

        ViewSpringListener(View view) {
            this._view = view;
        }

        @Override
        public void onSpringUpdate(Spring spring) {



            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    mSpring.setEndValue(1);
                    float value = (float) mSpring.getCurrentValue();

                    float topscale =
                            (float) SpringUtil.mapValueFromRangeToRange(value, 0, 1, 1, 0.88);
                    topscale = Math.max(topscale, 0);
                    findViewById(R.id.topthings).setScaleX(topscale);
                    findViewById(R.id.topthings).setScaleY(topscale);
                    findViewById(R.id.wave1).setScaleX(topscale);
                    findViewById(R.id.wave1).setScaleY(topscale);
                    findViewById(R.id.wave2).setScaleX(topscale);
                    findViewById(R.id.wave2).setScaleY(topscale);

                    float topy = (float) SpringUtil.mapValueFromRangeToRange(value, 0, 1, 0, 500);
                    findViewById(R.id.topthings).setTranslationY(topy);
                    findViewById(R.id.wave1).setTranslationY(topy);
                    findViewById(R.id.wave2).setTranslationY(topy);

                    float buttonx = (float) SpringUtil.mapValueFromRangeToRange(value, 0, 1, 0, -705);
                    findViewById(R.id.framebutton).setTranslationX(buttonx);

                    float pricex = (float) SpringUtil.mapValueFromRangeToRange(value, 0, 1, 0, -705);
                    findViewById(R.id.pricelabel).setTranslationX(pricex);

                    float checkoutx = (float) SpringUtil.mapValueFromRangeToRange(value, 0, 1, 800, 0);
                    findViewById(R.id.checkout).setTranslationX(checkoutx);

                    float inputx = (float) SpringUtil.mapValueFromRangeToRange(value, 0, 1, 800, 0);
                    findViewById(R.id.input).setTranslationX(inputx);

                }
            }, 42 * 38 );

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    mSpring1.setEndValue(0);
                    float value1 = (float) mSpring1.getCurrentValue();

                    float l1x = (float) SpringUtil.mapValueFromRangeToRange(value1, 0, 1, 0, 1000);
                    findViewById(R.id.l1).setTranslationX(l1x);

                }
            }, 42 * 38 -100);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    mSpring2.setEndValue(0);
                    float value2= (float) mSpring2.getCurrentValue();
                    float l2x = (float) SpringUtil.mapValueFromRangeToRange(value2, 0, 1, 0, 1000);
                    findViewById(R.id.l2).setTranslationX(l2x);

                }
            }, 42 * 38 - 50);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSpring3.setEndValue(0);
                    float value3= (float) mSpring3.getCurrentValue();
                    float l3x = (float) SpringUtil.mapValueFromRangeToRange(value3, 0, 1, 0, 1000);
                    findViewById(R.id.l3).setTranslationX(l3x);

                }
            },42 * 38 );

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSpring4.setEndValue(0);
                    float value4= (float) mSpring4.getCurrentValue();
                    float l4x = (float) SpringUtil.mapValueFromRangeToRange(value4, 0, 1, 0, 1000);
                    findViewById(R.id.l4).setTranslationX(l4x);

                }
            }, 42 * 38 + 50);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSpring5.setEndValue(0);
                    float value5= (float) mSpring5.getCurrentValue();
                    float l5x = (float) SpringUtil.mapValueFromRangeToRange(value5, 0, 1, 0, 1000);
                    findViewById(R.id.l5).setTranslationX(l5x);

                }
            },42 * 38 + 100);
        }

        @Override
        public void onSpringAtRest(Spring spring) {
        }

        @Override
        public void onSpringActivate(Spring spring) {
        }

        @Override
        public void onSpringEndStateChange(Spring spring) {

        }

    }

}
