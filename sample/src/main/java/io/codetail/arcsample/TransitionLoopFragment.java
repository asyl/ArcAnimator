package io.codetail.arcsample;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.animation.arcanimator.ArcAnimator;
import io.codetail.animation.arcanimator.Side;


public class TransitionLoopFragment extends Fragment {

    View mParent;
    ImageButton mBlue;
    FrameLayout mBluePair;

    ImageButton mRed;

    float startBlueX;
    float startBlueY;

    int endBlueX;
    int endBlueY;

    float startRedX;
    float startRedY;

    int startBluePairBottom;

    final static AccelerateInterpolator ACCELERATE = new AccelerateInterpolator();
    final static AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
    final static DecelerateInterpolator DECELERATE = new DecelerateInterpolator();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transitionloop, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mParent = view;
        mBlue = (ImageButton) view.findViewById(R.id.transition_blue);
        mBluePair = (FrameLayout) view.findViewById(R.id.transition_blue_pair);
        mRed = (ImageButton) view.findViewById(R.id.transition_red);
        mBlue.setOnClickListener(mClicker);
    }

    View.OnClickListener mClicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startBlueX = Utils.centerX(mBlue);
            startBlueY = Utils.centerY(mBlue);

            endBlueX = mParent.getRight() / 2;
            endBlueY = (int) (mParent.getBottom() * 0.8f);
            ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(mBlue, endBlueX,
                    endBlueY, 90, Side.LEFT)
                    .setDuration(500);
            arcAnimator.addListener(new SimpleListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mBlue.setVisibility(View.INVISIBLE);
                    appearBluePair();
                }
            });
            arcAnimator.start();
        }
    };


    void appearBluePair() {
        mBluePair.setVisibility(View.VISIBLE);

        float finalRadius = Math.max(mBluePair.getWidth(), mBluePair.getHeight()) * 1.5f;

        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(mBluePair, endBlueX, endBlueY, mBlue.getWidth() / 2f,
                finalRadius);
        animator.setDuration(500);
        animator.setInterpolator(ACCELERATE);
        animator.addListener(new SimpleListener() {
            @Override
            public void onAnimationEnd() {
                raise();
            }
        });
        animator.start();
    }

    void raise() {
        startBluePairBottom = mBluePair.getBottom();
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mBluePair, "bottom", mBluePair.getBottom(), mBluePair.getTop() + dpToPx(100));
        objectAnimator.addListener(new SimpleListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                appearRed();
            }
        });
        objectAnimator.setInterpolator(ACCELERATE_DECELERATE);
        objectAnimator.start();
    }

    void appearRed() {
        mRed.setVisibility(View.VISIBLE);

        int cx = mRed.getWidth() / 2;
        int cy = mRed.getHeight() / 2;

        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(mRed, cx, cy, 0, mRed.getWidth() / 2);
        animator.addListener(new SimpleListener() {
            @Override
            public void onAnimationEnd() {
                upRed();
            }
        });
        animator.setInterpolator(ACCELERATE);
        animator.start();
    }

    void upRed() {
        startRedX = mRed.getX();
        startRedY = mRed.getY();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mRed, "y", mRed.getY(),
                mBluePair.getBottom() - mRed.getHeight() / 2);
        objectAnimator.addListener(new SimpleListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                disappearRed();
            }
        });
        objectAnimator.setDuration(650);
        objectAnimator.setInterpolator(ACCELERATE_DECELERATE);
        objectAnimator.start();
    }

    void disappearRed() {

        int cx = mRed.getWidth() / 2;
        int cy = mRed.getHeight() / 2;

        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(mRed, cx, cy, mRed.getWidth() / 2, 0);
        animator.addListener(new SimpleListener() {
            @Override
            public void onAnimationEnd() {
                mRed.setVisibility(View.INVISIBLE);
                mRed.setX(startRedX);
                mRed.setY(startRedY);
                release();
            }
        });
        animator.setInterpolator(DECELERATE);
        animator.start();
    }

    void release() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mBluePair, "bottom", mBluePair.getBottom(), startBluePairBottom);
        objectAnimator.addListener(new SimpleListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                disappearBluePair();
            }
        });
        objectAnimator.setInterpolator(ACCELERATE_DECELERATE);
        objectAnimator.start();
    }

    void disappearBluePair() {
        float finalRadius = Math.max(mBluePair.getWidth(), mBluePair.getHeight()) * 1.5f;

        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(mBluePair, endBlueX, endBlueY,
                finalRadius, mBlue.getWidth() / 2f);
        animator.setDuration(500);
        animator.addListener(new SimpleListener() {
            @Override
            public void onAnimationEnd() {
                mBluePair.setVisibility(View.INVISIBLE);
                returnBlue();
            }
        });
        animator.setInterpolator(DECELERATE);
        animator.start();
    }

    void returnBlue() {
        mBlue.setVisibility(View.VISIBLE);
        ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(mBlue, startBlueX,
                startBlueY, 90, Side.LEFT)
                .setDuration(500);
        arcAnimator.start();

    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    private static class SimpleListener implements SupportAnimator.AnimatorListener, ObjectAnimator.AnimatorListener {

        @Override
        public void onAnimationStart() {

        }

        @Override
        public void onAnimationEnd() {

        }

        @Override
        public void onAnimationCancel() {

        }

        @Override
        public void onAnimationRepeat() {

        }

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
