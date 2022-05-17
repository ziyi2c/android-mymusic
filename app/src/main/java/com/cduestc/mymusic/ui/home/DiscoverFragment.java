package com.cduestc.mymusic.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cduestc.mymusic.R;
import com.cduestc.mymusic.databinding.FragmentHomeBinding;

public class DiscoverFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;
    private Button btnSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DiscoverViewModel discoverViewModel =
                new ViewModelProvider(this).get(DiscoverViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        init();

//        final TextView textView = binding.textHome;
//        discoverViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void init(){
//      绑定
        btnSearch = binding.searchSubmit;
//      注册事件
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchSubmit:

                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_LONG).show();
                break;
        }
    }
}