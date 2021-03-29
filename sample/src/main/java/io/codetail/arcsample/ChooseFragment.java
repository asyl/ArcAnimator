package io.codetail.arcsample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChooseFragment extends Fragment {

    Button mArcAnimatorDemo;
    Button mTransitionDemo;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mArcAnimatorDemo = (Button) view.findViewById(R.id.demo_arcanimation);
        mTransitionDemo = (Button) view.findViewById(R.id.demo_transition);
        mArcAnimatorDemo.setOnClickListener(mClicker);
        mTransitionDemo.setOnClickListener(mClicker);
    }

    final View.OnClickListener mClicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.demo_arcanimation:
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ArcTestFragment())
                            .addToBackStack(getClass().getName()).commit();
                    break;
                case R.id.demo_transition:
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TransitionLoopFragment())
                            .addToBackStack(getClass().getName()).commit();
                    break;
            }
        }
    };
}
