package com.example.ghosthome.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.ghosthome.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    public VB binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        navigationUtil = new NavigationUtil(
//                this,
//                getNavigationFragmentId(),
//                (MainActivity) requireActivity()
//        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initializeViewBinding(inflater, container);

//        snackBarManager.initializeSnackBarManager(inflater, _binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView();
        observeData();
        fetchData();

    }

    public  NavController findNavController() {
        return Navigation.findNavController(requireActivity(), R.id.container);
    }

    private void initializeViewBinding(LayoutInflater inflater, ViewGroup container) {
        Type superclass = getClass().getGenericSuperclass();
        Class<?> clazz = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
        try {
            Method method = clazz.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            binding = (VB) method.invoke(null, inflater, container, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public abstract void setupView();
    public abstract void observeData();
    public abstract void fetchData();
    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
