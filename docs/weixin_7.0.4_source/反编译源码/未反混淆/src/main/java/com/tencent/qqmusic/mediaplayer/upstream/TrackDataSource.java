package com.tencent.qqmusic.mediaplayer.upstream;

import android.text.TextUtils;
import android.util.Pair;
import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.qqmusic.mediaplayer.AudioFormat.AudioType;
import com.tencent.qqmusic.mediaplayer.BaseMediaPlayer;
import com.tencent.qqmusic.mediaplayer.PlayerListenerCallback;
import com.tencent.qqmusic.mediaplayer.audioplaylist.TrackInfo;
import com.tencent.qqmusic.mediaplayer.seektable.SeekTable;
import com.tencent.qqmusic.mediaplayer.util.Logger;
import java.io.File;
import java.io.IOException;

public class TrackDataSource implements PlayerListenerCallback, IDataSource {
    private static final String TAG = "TrackDataSource";
    private IDataSource mDataSource;
    private long mEndBytePosition;
    private long mStartBytePosition;
    private long mStartTimePosition;
    private TrackInfo mTrackInfo;
    private TrackStateCallback mTrackStateCallback;

    public interface TrackStateCallback {
        void onTrackPrepared(TrackInfo trackInfo);
    }

    public TrackDataSource(TrackInfo trackInfo) {
        AppMethodBeat.i(104554);
        String uri = trackInfo.getUri();
        DataSourceException dataSourceException;
        if (TextUtils.isEmpty(trackInfo.getUri())) {
            dataSourceException = new DataSourceException(-1, "track info has no media file path!", null);
            AppMethodBeat.o(104554);
            throw dataSourceException;
        } else if (new File(uri).exists()) {
            this.mDataSource = new FileDataSource(trackInfo.getUri());
            this.mTrackInfo = trackInfo;
            AppMethodBeat.o(104554);
        } else {
            dataSourceException = new DataSourceException(-1, "track info's media file not exists!", null);
            AppMethodBeat.o(104554);
            throw dataSourceException;
        }
    }

    public void setTrackStateCallback(TrackStateCallback trackStateCallback) {
        this.mTrackStateCallback = trackStateCallback;
    }

    public void open() {
        AppMethodBeat.i(104555);
        this.mDataSource.open();
        AppMethodBeat.o(104555);
    }

    public int readAt(long j, byte[] bArr, int i, int i2) {
        AppMethodBeat.i(104556);
        if (this.mEndBytePosition <= 0 || j <= this.mEndBytePosition) {
            int readAt = this.mDataSource.readAt(j, bArr, i, i2);
            AppMethodBeat.o(104556);
            return readAt;
        }
        AppMethodBeat.o(104556);
        return -1;
    }

    public long getSize() {
        AppMethodBeat.i(104557);
        long size = this.mDataSource.getSize();
        AppMethodBeat.o(104557);
        return size;
    }

    public void close() {
        AppMethodBeat.i(104558);
        this.mDataSource.close();
        AppMethodBeat.o(104558);
    }

    public AudioType getAudioType() {
        AppMethodBeat.i(104559);
        AudioType audioType = this.mDataSource.getAudioType();
        AppMethodBeat.o(104559);
        return audioType;
    }

    public void onBufferingUpdate(BaseMediaPlayer baseMediaPlayer, int i) {
    }

    public void onCompletion(BaseMediaPlayer baseMediaPlayer) {
    }

    public void onSeekComplete(BaseMediaPlayer baseMediaPlayer, int i) {
    }

    public void onError(BaseMediaPlayer baseMediaPlayer, int i, int i2, int i3) {
    }

    public void setEndBytePosition(long j) {
        this.mEndBytePosition = j;
    }

    public void onPrepared(BaseMediaPlayer baseMediaPlayer) {
        AppMethodBeat.i(104560);
        try {
            this.mDataSource.open();
            long size = this.mDataSource.getSize();
            SeekTable createSeekTable = baseMediaPlayer.createSeekTable();
            Pair range = this.mTrackInfo.getRange();
            Logger.i(TAG, "start time: " + range.first + ", end time: " + range.second);
            if (((Long) range.first).longValue() > ((Long) range.second).longValue()) {
                Logger.e(TAG, "start range larger, return");
                AppMethodBeat.o(104560);
                return;
            }
            this.mStartTimePosition = ((Long) range.first).longValue();
            long seek = createSeekTable.seek(((Long) range.first).longValue());
            long seek2 = createSeekTable.seek(((Long) range.second).longValue());
            if (size <= 0 || seek <= size) {
                if (size > 0 && seek2 > size) {
                    seek2 = size;
                }
                this.mStartBytePosition = seek;
                this.mEndBytePosition = seek2;
                Logger.i(TAG, "start byte: " + this.mStartBytePosition + ", end byte: " + this.mEndBytePosition);
                if (this.mTrackStateCallback != null) {
                    this.mTrackStateCallback.onTrackPrepared(this.mTrackInfo);
                }
                AppMethodBeat.o(104560);
                return;
            }
            Logger.e(TAG, "startBytePosition larger than size!");
            AppMethodBeat.o(104560);
        } catch (IOException e) {
            Logger.e(TAG, "dataSource open failed!", e);
            AppMethodBeat.o(104560);
        }
    }

    public void onStarted(BaseMediaPlayer baseMediaPlayer) {
        AppMethodBeat.i(104561);
        baseMediaPlayer.seekTo(0);
        AppMethodBeat.o(104561);
    }

    public void onStateChanged(BaseMediaPlayer baseMediaPlayer, int i) {
    }
}
