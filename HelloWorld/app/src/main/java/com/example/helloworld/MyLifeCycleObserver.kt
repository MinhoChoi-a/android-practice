package com.example.helloworld

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.OnLifecycleEvent

class MyLifeCycleObserver: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateEvent() {
        Log.i(LOG_TAG, "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onStartEvent() {
        Log.i(LOG_TAG, "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onResumeEvent() {
        Log.i(LOG_TAG, "onResume")
    }

}