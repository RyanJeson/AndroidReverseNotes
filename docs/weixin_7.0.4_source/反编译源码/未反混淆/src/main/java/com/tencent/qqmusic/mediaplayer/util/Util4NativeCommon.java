package com.tencent.qqmusic.mediaplayer.util;

import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.qqmusic.mediaplayer.AudioPlayerConfigure;
import com.tencent.qqmusic.mediaplayer.NativeLibs;

public class Util4NativeCommon {
    private static final String TAG = "Util4NativeCommon";

    public static native boolean isSupportNeon();

    static {
        AppMethodBeat.i(128389);
        try {
            AudioPlayerConfigure.getSoLibraryLoader().load(NativeLibs.audioCommon.getName());
            AppMethodBeat.o(128389);
        } catch (Throwable th) {
            Logger.e(TAG, th);
            AppMethodBeat.o(128389);
        }
    }
}
