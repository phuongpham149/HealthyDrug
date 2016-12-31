package com.example.phuong.healthydrug.receivers.bus;

import com.squareup.otto.Bus;

/**
 * Created by phuong on 31/12/2016.
 */

public final class BusProvider {
    private static Bus mBus;

    private BusProvider() {
    }

    public static synchronized Bus getInstance() {
        if (mBus == null) {
            mBus = new Bus();
        }
        return mBus;
    }
}
