package io.codetail.arcsample;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import io.codetail.animation.arcanimator.ArcAnimator;
import io.codetail.animation.arcanimator.ArcDebugView;
import io.codetail.animation.arcanimator.Side;


public class ArcTestFragment extends Fragment {

    ViewGroup mParent;

    Button mDefiner;

    ImageButton mElement;

    EditText mDegreeEditor;

    EditText mSideEditor;

    EditText mDurationEditor;

    Side mSide;

    ArcDebugView mArcDebugView;

    float startX;
    float startY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_arctest, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mParent = (ViewGroup) view;

        mDefiner = (Button) view.findViewById(R.id.arc_definer);
        mElement = (ImageButton) view.findViewById(R.id.circle_element);
        mDegreeEditor = (EditText) view.findViewById(R.id.arc_edit);
        mSideEditor = (EditText) view.findViewById(R.id.arc_edit1);
        mDurationEditor = (EditText) view.findViewById(R.id.arc_edit2);
        mArcDebugView = (ArcDebugView) view.findViewById(R.id.arc_debug);

        startX = mElement.getTranslationX();
        startX = mElement.getTranslationY();

        mDefiner.setOnClickListener(mClicker);
    }

    View.OnClickListener mClicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(mDegreeEditor.getText().toString()) ||
                    TextUtils.isEmpty(mSideEditor.getText().toString()) ||
                    TextUtils.isEmpty(mDurationEditor.getText().toString())) {
                return;
            }
            if (mSideEditor.getText().toString().equals("0")) {
                mSide = Side.RIGHT;
            } else if (mSideEditor.getText().toString().equals("1")) {
                mSide = Side.LEFT;
            } else {
                return;
            }

            mElement.setTranslationX(startX);
            mElement.setTranslationY(startY);

            ArcAnimator arcAnimator = ArcAnimator.createArcAnimator(mElement, mParent, Float.parseFloat(mDegreeEditor.getText().toString()), mSide)
                    .setDuration(Integer.parseInt(mDurationEditor.getText().toString()));
            arcAnimator.start();
            mArcDebugView.drawArcAnimator(arcAnimator);
        }
    };
}
