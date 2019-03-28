package com.example.myapplication.presentation_layer.utils

import io.reactivex.ObservableTransformer

abstract class Transformer<T> : ObservableTransformer<T, T>