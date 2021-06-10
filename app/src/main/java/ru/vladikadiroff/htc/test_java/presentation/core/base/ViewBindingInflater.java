package ru.vladikadiroff.htc.test_java.presentation.core.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

public interface ViewBindingInflater<VB extends ViewBinding> {
    VB viewBindingInflate(LayoutInflater layoutInflater, ViewGroup root, Boolean attachToRoot);
}
