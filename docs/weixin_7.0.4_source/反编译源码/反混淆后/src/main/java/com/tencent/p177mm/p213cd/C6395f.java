package com.tencent.p177mm.p213cd;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Pair;
import android.util.Printer;
import android.util.StringBuilderPrinter;
import com.tencent.mars.cdn.CdnLogic;
import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.p177mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.p177mm.p213cd.p214a.C7595f;
import com.tencent.p177mm.plugin.report.C3734b;
import com.tencent.p177mm.plugin.report.C7053e;
import com.tencent.p177mm.sdk.p599a.C4872b;
import com.tencent.p177mm.sdk.platformtools.C4990ab;
import com.tencent.p177mm.sdk.platformtools.C4996ah;
import com.tencent.p177mm.sdk.platformtools.C5046bo;
import com.tencent.p177mm.sdk.platformtools.C5058f;
import com.tencent.p177mm.vfs.C5728b;
import com.tencent.ttpic.baseutils.IOUtils;
import com.tencent.wcdb.Cursor;
import com.tencent.wcdb.CursorWindow;
import com.tencent.wcdb.CursorWrapper;
import com.tencent.wcdb.DatabaseErrorHandler;
import com.tencent.wcdb.database.SQLiteAsyncCheckpointer;
import com.tencent.wcdb.database.SQLiteCheckpointListener;
import com.tencent.wcdb.database.SQLiteCipherSpec;
import com.tencent.wcdb.database.SQLiteCursor;
import com.tencent.wcdb.database.SQLiteDatabase;
import com.tencent.wcdb.database.SQLiteDatabase.CursorFactory;
import com.tencent.wcdb.database.SQLiteDatabaseConfiguration;
import com.tencent.wcdb.database.SQLiteDebug;
import com.tencent.wcdb.database.SQLiteDirectCursor;
import com.tencent.wcdb.database.SQLiteException;
import com.tencent.wcdb.database.SQLiteGlobal;
import com.tencent.wcdb.database.SQLiteTrace;
import com.tencent.wcdb.database.SQLiteTrace.TraceInfo;
import com.tencent.wcdb.support.LogPrinter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.tencent.mm.cd.f */
public final class C6395f implements DatabaseErrorHandler, SQLiteTrace {
    private static final SQLiteCipherSpec kIB = new SQLiteCipherSpec().setPageSize(WXMediaMessage.DESCRIPTION_LENGTH_LIMIT).setSQLCipherVersion(1);
    private static SQLiteTrace ybC;
    private static C3734b ybD = new C3734b();
    private static final int[] ybE = new int[]{27, 39, 24, 36, 27, 39, 48, 54, 33, 45, 30, 42, 33, 45, 51, 57};
    private static Map<String, SQLiteDatabase> ybv = new HashMap();
    private static final SharedPreferences ybw;
    private static int ybx;
    private static final SQLiteCheckpointListener yby;
    private boolean ybA = false;
    private volatile String ybB = "";
    SQLiteDatabase ybt = null;
    SQLiteDatabase ybu = null;
    private boolean ybz = false;

    /* renamed from: com.tencent.mm.cd.f$a */
    static class C6397a extends CursorWrapper {
        private long mCreateTime;
        private String xIB;
        private int ybJ;

        public C6397a(Cursor cursor, String str) {
            super(cursor);
            AppMethodBeat.m2504i(59055);
            this.xIB = str;
            if (cursor instanceof SQLiteDirectCursor) {
                this.ybJ = 2;
            } else if (cursor instanceof SQLiteCursor) {
                this.ybJ = 1;
            } else {
                this.ybJ = -1;
            }
            this.mCreateTime = System.nanoTime();
            AppMethodBeat.m2505o(59055);
        }

        public final void close() {
            AppMethodBeat.m2504i(59056);
            this.mCursor.close();
            long nanoTime = (System.nanoTime() - this.mCreateTime) / 1000;
            int i = -1;
            if (this.ybJ == 1) {
                i = 0;
            } else if (this.ybJ == 2) {
                i = 3;
            }
            if (i >= 0) {
                C6395f.ybD.mo8373g(i + 1, i + 2, nanoTime);
            }
            AppMethodBeat.m2505o(59056);
        }

        public final int getCount() {
            AppMethodBeat.m2504i(59057);
            C4990ab.m7420w("MicroMsg.MMDataBase", "Slow operation: " + C5046bo.m7574l(new Throwable()));
            int count = this.mCursor.getCount();
            AppMethodBeat.m2505o(59057);
            return count;
        }

        public final double getDouble(int i) {
            AppMethodBeat.m2504i(59058);
            try {
                double d = super.getDouble(i);
                AppMethodBeat.m2505o(59058);
                return d;
            } catch (IllegalStateException e) {
                C4990ab.printErrStackTrace("MicroMsg.MMDataBase", e, "", new Object[0]);
                AppMethodBeat.m2505o(59058);
                return 0.0d;
            }
        }

        public final float getFloat(int i) {
            AppMethodBeat.m2504i(59059);
            try {
                float f = super.getFloat(i);
                AppMethodBeat.m2505o(59059);
                return f;
            } catch (IllegalStateException e) {
                C4990ab.printErrStackTrace("MicroMsg.MMDataBase", e, "", new Object[0]);
                AppMethodBeat.m2505o(59059);
                return 0.0f;
            }
        }

        public final int getInt(int i) {
            int i2 = 0;
            AppMethodBeat.m2504i(59060);
            try {
                i2 = super.getInt(i);
                AppMethodBeat.m2505o(59060);
            } catch (IllegalStateException e) {
                C4990ab.printErrStackTrace("MicroMsg.MMDataBase", e, "", new Object[i2]);
                AppMethodBeat.m2505o(59060);
            }
            return i2;
        }

        public final long getLong(int i) {
            AppMethodBeat.m2504i(59061);
            try {
                long j = super.getLong(i);
                AppMethodBeat.m2505o(59061);
                return j;
            } catch (IllegalStateException e) {
                C4990ab.printErrStackTrace("MicroMsg.MMDataBase", e, "", new Object[0]);
                AppMethodBeat.m2505o(59061);
                return 0;
            }
        }

        public final short getShort(int i) {
            short s = (short) 0;
            AppMethodBeat.m2504i(59062);
            try {
                s = super.getShort(i);
                AppMethodBeat.m2505o(59062);
            } catch (IllegalStateException e) {
                C4990ab.printErrStackTrace("MicroMsg.MMDataBase", e, "", new Object[s]);
                AppMethodBeat.m2505o(59062);
            }
            return s;
        }

        public final String getString(int i) {
            AppMethodBeat.m2504i(59063);
            try {
                String string = super.getString(i);
                AppMethodBeat.m2505o(59063);
                return string;
            } catch (IllegalStateException e) {
                C4990ab.printErrStackTrace("MicroMsg.MMDataBase", e, "", new Object[0]);
                AppMethodBeat.m2505o(59063);
                return null;
            }
        }

        public final byte[] getBlob(int i) {
            AppMethodBeat.m2504i(59064);
            try {
                byte[] blob = super.getBlob(i);
                AppMethodBeat.m2505o(59064);
                return blob;
            } catch (IllegalStateException e) {
                C4990ab.printErrStackTrace("MicroMsg.MMDataBase", e, "", new Object[0]);
                AppMethodBeat.m2505o(59064);
                return null;
            }
        }
    }

    static {
        AppMethodBeat.m2504i(59087);
        SQLiteGlobal.loadLib();
        SQLiteDebug.setIOTraceFlags(0);
        CursorWindow.windowSize(3145728);
        SharedPreferences sharedPreferences = C4996ah.getContext().getSharedPreferences("db_config", 0);
        ybw = sharedPreferences;
        ybx = sharedPreferences.getInt("flags", 0);
        int i = ybw.getInt("acp", 100);
        yby = new SQLiteAsyncCheckpointer(i >>> 16, i & CdnLogic.kBizGeneric) {
            public final void onCheckpointResult(SQLiteDatabase sQLiteDatabase, int i, int i2, long j) {
                AppMethodBeat.m2504i(59052);
                int i3 = i == i2 ? 18 : 21;
                C6395f.ybD.mo8373g(i3 + 1, i3 + 2, j);
                C4990ab.m7411d("MicroMsg.MMDataBase", "Checkpointed, pages: %d/%d, time: %d, db: %s", Integer.valueOf(i2), Integer.valueOf(i), Long.valueOf(j), sQLiteDatabase.getPath());
                AppMethodBeat.m2505o(59052);
            }
        };
        AppMethodBeat.m2505o(59087);
    }

    /* renamed from: a */
    public static void m10546a(SQLiteTrace sQLiteTrace) {
        ybC = sQLiteTrace;
    }

    /* renamed from: aV */
    public static void m10548aV(Map<String, ?> map) {
        AppMethodBeat.m2504i(59065);
        Map all = ybw.getAll();
        if (all == null || all.equals(map)) {
            AppMethodBeat.m2505o(59065);
            return;
        }
        Editor edit = ybw.edit();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Integer) {
                edit.putInt(str, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                edit.putLong(str, ((Long) value).longValue());
            } else if (value instanceof Boolean) {
                edit.putBoolean(str, ((Boolean) value).booleanValue());
            } else if (value instanceof String) {
                edit.putString(str, value.toString());
            } else if (value instanceof Float) {
                edit.putFloat(str, ((Float) value).floatValue());
            }
        }
        edit.apply();
        AppMethodBeat.m2505o(59065);
    }

    @SuppressLint({"DefaultLocale"})
    private static String dvs() {
        AppMethodBeat.m2504i(59066);
        int i = ybw.getInt("acp", 100);
        int i2 = i >>> 16;
        i &= CdnLogic.kBizGeneric;
        String format = String.format("flags: 0x%04x\nacp: %d / %d", new Object[]{Integer.valueOf(ybw.getInt("flags", 0)), Integer.valueOf(i2), Integer.valueOf(i)});
        AppMethodBeat.m2505o(59066);
        return format;
    }

    static void aqu(String str) {
        int i = 0;
        AppMethodBeat.m2504i(59067);
        C5728b c5728b = new C5728b(str);
        C5728b dMC = c5728b.dMC();
        String name = c5728b.getName();
        C5728b c5728b2 = new C5728b(c5728b.dMC(), "corrupted");
        c5728b2.mkdirs();
        String[] strArr = new String[]{"", "-journal", "-wal", ".ini", ".bak", ".sm", "-vfslog", "-vfslo1"};
        while (i < 8) {
            String str2 = strArr[i];
            new C5728b(dMC, name + str2).mo11675o(new C5728b(c5728b2, name + str2));
            i++;
        }
        AppMethodBeat.m2505o(59067);
    }

    public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
        AppMethodBeat.m2504i(59068);
        boolean isOpen = sQLiteDatabase.isOpen();
        C4990ab.m7413e("MicroMsg.MMDataBase", "Database corrupted, isOpen: %s, path: %s", Boolean.toString(isOpen), sQLiteDatabase.getPath());
        if (!this.ybA) {
            this.ybA = true;
            int lastErrorLine = SQLiteDebug.getLastErrorLine();
            String dvs = C6395f.dvs();
            String format = String.format("%s (line: %d, open: %s)\n%s", new Object[]{new C5728b(sQLiteDatabase.getPath()).getName(), Integer.valueOf(lastErrorLine), Boolean.toString(sQLiteDatabase.isOpen()), dvs});
            HashMap hashMap = new HashMap();
            hashMap.put("fileSystem", C1357b.dvn());
            C7053e.pXa.mo8382g("DBCorrupt", format, hashMap);
        }
        List<Pair> attachedDbs = sQLiteDatabase.getAttachedDbs();
        if (isOpen) {
            try {
                int i;
                if (!sQLiteDatabase.isWriteAheadLoggingEnabled()) {
                    i = 42;
                } else if (sQLiteDatabase.getAsyncCheckpointEnabled()) {
                    i = 43;
                } else {
                    i = 41;
                }
                C7053e.pXa.mo8378a(181, (long) i, 1, true);
                sQLiteDatabase.close();
                if (attachedDbs == null) {
                    C6395f.aqu(r9);
                    AppMethodBeat.m2505o(59068);
                    return;
                }
                for (Pair pair : attachedDbs) {
                    C6395f.aqu((String) pair.second);
                }
                AppMethodBeat.m2505o(59068);
                return;
            } catch (Exception e) {
                AppMethodBeat.m2505o(59068);
                return;
            }
        }
        AppMethodBeat.m2505o(59068);
    }

    public final void onSQLExecuted(SQLiteDatabase sQLiteDatabase, String str, int i, long j) {
        int i2 = 0;
        AppMethodBeat.m2504i(59069);
        if (sQLiteDatabase.getPath().endsWith("EnMicroMsg.db")) {
            int i3;
            int i4 = sQLiteDatabase.isWriteAheadLoggingEnabled() ? 2 : 0;
            if (sQLiteDatabase.getAsyncCheckpointEnabled()) {
                i3 = 4;
            } else {
                i3 = 0;
            }
            if (i != 1) {
                i2 = 8;
            }
            i4 = ybE[(i4 + i3) + i2];
            ybD.mo8373g(i4 + 1, i4 + 2, (long) ((int) j));
        }
        if (ybC != null) {
            ybC.onSQLExecuted(sQLiteDatabase, str, i, j);
        }
        AppMethodBeat.m2505o(59069);
    }

    public final void onConnectionObtained(SQLiteDatabase sQLiteDatabase, String str, long j, boolean z) {
        int i = 0;
        AppMethodBeat.m2504i(59070);
        if (sQLiteDatabase.getPath().endsWith("EnMicroMsg.db")) {
            int i2;
            int i3 = sQLiteDatabase.isWriteAheadLoggingEnabled() ? 2 : 0;
            if (sQLiteDatabase.getAsyncCheckpointEnabled()) {
                i2 = 4;
            } else {
                i2 = 0;
            }
            if (z) {
                i = 8;
            }
            i3 = ybE[((i3 + i2) + i) + 1];
            ybD.mo8373g(i3 + 1, i3 + 2, (long) ((int) j));
        }
        AppMethodBeat.m2505o(59070);
    }

    @SuppressLint({"DefaultLocale"})
    public final void onConnectionPoolBusy(SQLiteDatabase sQLiteDatabase, String str, long j, boolean z, List<TraceInfo<String>> list, List<TraceInfo<StackTraceElement[]>> list2) {
        AppMethodBeat.m2504i(59071);
        String name = new C5728b(sQLiteDatabase.getPath()).getName();
        long currentTimeMillis = System.currentTimeMillis();
        final StringBuilder stringBuilder = new StringBuilder(2048);
        stringBuilder.append(name).append(IOUtils.LINE_SEPARATOR_UNIX);
        if (!(list == null || list.isEmpty())) {
            for (TraceInfo traceInfo : list) {
                stringBuilder.append("[RUNNING] ").append((String) traceInfo.info).append(10);
            }
            stringBuilder.append(10);
        }
        if (!(list2 == null || list2.isEmpty())) {
            for (TraceInfo traceInfo2 : list2) {
                stringBuilder.append("[TRANSACTION]\n");
                for (StackTraceElement stackTraceElement : (StackTraceElement[]) traceInfo2.info) {
                    stringBuilder.append("  at ").append(stackTraceElement.toString()).append(10);
                }
                stringBuilder.append(10);
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        if (this.ybB.equals(stringBuilder2)) {
            AppMethodBeat.m2505o(59071);
            return;
        }
        C4990ab.m7420w("MicroMsg.MMDataBase", "Database connection pool busy: ".concat(String.valueOf(name)));
        C4990ab.m7420w("MicroMsg.MMDataBase", "[REQUEST primary: " + z + "] " + str);
        if (!(list == null || list.isEmpty())) {
            for (TraceInfo traceInfo3 : list) {
                C4990ab.m7421w("MicroMsg.MMDataBase", "[RUNNING tid: %d (%d ms)] %s", Integer.valueOf(traceInfo3.tid), Long.valueOf(currentTimeMillis - traceInfo3.time), traceInfo3.info);
            }
            stringBuilder.append(10);
        }
        if (!(list2 == null || list2.isEmpty())) {
            for (TraceInfo traceInfo32 : list2) {
                stringBuilder.setLength(0);
                stringBuilder.append(String.format("[TRANSACTION tid: %d (%d ms)]\n", new Object[]{Integer.valueOf(traceInfo32.tid), Long.valueOf(currentTimeMillis - traceInfo32.time)}));
                for (StackTraceElement stackTraceElement2 : (StackTraceElement[]) traceInfo32.info) {
                    stringBuilder.append("  at ").append(stackTraceElement2.toString()).append(10);
                }
                stringBuilder.append(10);
                C4990ab.m7420w("MicroMsg.MMDataBase", stringBuilder.toString());
            }
        }
        stringBuilder.setLength(0);
        sQLiteDatabase.dump(new Printer() {
            final Printer ybF = new StringBuilderPrinter(stringBuilder);
            final Printer ybG = new LogPrinter(5, "MicroMsg.MMDataBase");

            public final void println(String str) {
                AppMethodBeat.m2504i(59054);
                this.ybF.println(str);
                this.ybG.println(str);
                AppMethodBeat.m2505o(59054);
            }
        }, false);
        String stringBuilder3 = stringBuilder.toString();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("SQL", str);
        linkedHashMap.put("dump", stringBuilder3);
        linkedHashMap.put("settings", C6395f.dvs());
        C7053e.pXa.mo8382g("DBPoolBusy", stringBuilder2, linkedHashMap);
        this.ybB = stringBuilder2;
        C7053e.pXa.mo8378a(463, 6, 1, false);
        AppMethodBeat.m2505o(59071);
    }

    public final void onDatabaseCorrupted(SQLiteDatabase sQLiteDatabase) {
    }

    /* renamed from: ca */
    public static C6395f m10549ca(String str, boolean z) {
        AppMethodBeat.m2504i(59072);
        C6395f c6395f = new C6395f();
        if (str == null || str.length() == 0) {
            c6395f.ybu = SQLiteDatabase.create(null);
            c6395f.ybz = true;
            if (c6395f.ybu == null) {
                AppMethodBeat.m2505o(59072);
                return null;
            }
            AppMethodBeat.m2505o(59072);
            return c6395f;
        }
        int i = 268435456;
        if (z) {
            i = 805306368;
        }
        try {
            c6395f.ybu = SQLiteDatabase.openDatabase(str, null, i, c6395f);
            c6395f.ybu.setTraceCallback(c6395f);
            if (C6395f.dvu()) {
                c6395f.ybu.setCheckpointCallback(yby);
                C4990ab.m7416i("MicroMsg.MMDataBase", "Enable async checkpointer for DB: " + c6395f.getPath());
            }
            if (C5058f.xxZ.amr("ENABLE_STETHO")) {
                ybv.put(c6395f.getPath(), c6395f.ybu);
            }
            if (c6395f.ybu == null) {
                AppMethodBeat.m2505o(59072);
                return null;
            }
            AppMethodBeat.m2505o(59072);
            return c6395f;
        } catch (SQLiteException e) {
            C7053e.pXa.mo8382g("DBCantOpen", "DB (" + new C5728b(str).getName() + ") can't open: " + C5046bo.m7574l(e), null);
            AppMethodBeat.m2505o(59072);
            throw e;
        }
    }

    public final String getPath() {
        AppMethodBeat.m2504i(59073);
        String path;
        if (this.ybt != null) {
            path = this.ybt.getPath();
            AppMethodBeat.m2505o(59073);
            return path;
        }
        path = this.ybu.getPath();
        AppMethodBeat.m2505o(59073);
        return path;
    }

    public static boolean dvt() {
        return (ybx & 1) == 0;
    }

    public static boolean dvu() {
        return (ybx & 8) != 0;
    }

    /* renamed from: E */
    public static C6395f m10545E(String str, String str2, boolean z) {
        String str3;
        SQLiteCipherSpec sQLiteCipherSpec;
        byte[] bArr;
        AppMethodBeat.m2504i(59074);
        try {
            C5728b c5728b = new C5728b(str + "-vfslog");
            C5728b c5728b2 = new C5728b(str + "-vfslo1");
            if (c5728b.exists() && c5728b.length() > 256) {
                c5728b.delete();
            }
            if (c5728b2.exists() && c5728b2.length() > 256) {
                c5728b2.delete();
            }
        } catch (Throwable th) {
            C4990ab.printErrStackTrace("MicroMsg.MMDataBase", th, "", new Object[0]);
        }
        C6395f c6395f = new C6395f();
        int i = 268435456;
        if (C5046bo.isNullOrNil(str)) {
            str3 = SQLiteDatabaseConfiguration.MEMORY_DB_PATH;
            c6395f.ybz = true;
        } else {
            str3 = str;
        }
        if (C5046bo.isNullOrNil(str2)) {
            sQLiteCipherSpec = null;
            bArr = null;
        } else {
            bArr = str2.getBytes();
            sQLiteCipherSpec = kIB;
        }
        boolean dvt = C6395f.dvt();
        if (z || dvt) {
            i = 805306368;
        }
        try {
            c6395f.ybt = SQLiteDatabase.openDatabase(str3, bArr, sQLiteCipherSpec, null, i, c6395f);
            c6395f.ybt.setTraceCallback(c6395f);
            if (C6395f.dvu()) {
                c6395f.ybt.setCheckpointCallback(yby);
                C4990ab.m7416i("MicroMsg.MMDataBase", "Enable async checkpointer for DB: " + c6395f.getPath());
            }
            if (C5058f.xxZ.amr("ENABLE_STETHO")) {
                ybv.put(c6395f.getPath(), c6395f.ybt);
            }
            if (c6395f.ybt == null) {
                AppMethodBeat.m2505o(59074);
                return null;
            }
            AppMethodBeat.m2505o(59074);
            return c6395f;
        } catch (SQLiteException e) {
            C7053e.pXa.mo8382g("DBCantOpen", "DB (" + new C5728b(str3).getName() + ") can't open: " + C5046bo.m7574l(e), null);
            AppMethodBeat.m2505o(59074);
            throw e;
        }
    }

    public final boolean isOpen() {
        AppMethodBeat.m2504i(59075);
        boolean isOpen;
        if (this.ybt != null) {
            isOpen = this.ybt.isOpen();
            AppMethodBeat.m2505o(59075);
            return isOpen;
        } else if (this.ybu != null) {
            isOpen = this.ybu.isOpen();
            AppMethodBeat.m2505o(59075);
            return isOpen;
        } else {
            AppMethodBeat.m2505o(59075);
            return false;
        }
    }

    public final void close() {
        AppMethodBeat.m2504i(59076);
        try {
            if (this.ybt != null && this.ybt.isOpen()) {
                this.ybt.close();
                this.ybt = null;
            }
            if (this.ybu != null && this.ybu.isOpen()) {
                this.ybu.close();
                this.ybu = null;
            }
            AppMethodBeat.m2505o(59076);
        } catch (Exception e) {
            C4990ab.m7413e("MicroMsg.MMDataBase", "exception:%s", C5046bo.m7574l(e));
            AppMethodBeat.m2505o(59076);
        }
    }

    /* renamed from: a */
    public final android.database.Cursor mo14612a(String str, String[] strArr, int i) {
        CursorFactory cursorFactory;
        android.database.Cursor c6397a;
        AppMethodBeat.m2504i(59077);
        SQLiteDatabase sQLiteDatabase = this.ybt != null ? this.ybt : this.ybu;
        Object obj = (ybx & 2) != 0 ? 1 : null;
        if (i == 4) {
            cursorFactory = C7595f.FACTORY;
        } else if (C4872b.dnO() || obj != null) {
            switch (i) {
                case 0:
                    cursorFactory = null;
                    break;
                case 1:
                    cursorFactory = SQLiteCursor.FACTORY;
                    break;
                case 2:
                    cursorFactory = SQLiteDirectCursor.FACTORY;
                    break;
                case 3:
                    cursorFactory = null;
                    break;
                default:
                    cursorFactory = null;
                    break;
            }
        } else {
            cursorFactory = null;
        }
        Cursor rawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cursorFactory, str, strArr, null);
        if (i == 0 || i == 4) {
            Object c6397a2 = rawQueryWithFactory;
        } else {
            c6397a2 = new C6397a(rawQueryWithFactory, str);
        }
        AppMethodBeat.m2505o(59077);
        return c6397a2;
    }

    public final void execSQL(String str) {
        AppMethodBeat.m2504i(59078);
        if (this.ybt != null) {
            this.ybt.execSQL(str);
            AppMethodBeat.m2505o(59078);
            return;
        }
        this.ybu.execSQL(str);
        AppMethodBeat.m2505o(59078);
    }

    /* renamed from: a */
    public final android.database.Cursor mo14613a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, int i) {
        android.database.Cursor c6397a;
        AppMethodBeat.m2504i(59079);
        SQLiteDatabase sQLiteDatabase = this.ybt != null ? this.ybt : this.ybu;
        Object obj = (ybx & 2) != 0 ? 1 : null;
        CursorFactory cursorFactory = null;
        if (i != 4) {
            if (C4872b.dnO() || obj != null) {
                switch (i) {
                    case 0:
                        cursorFactory = null;
                        break;
                    case 1:
                        cursorFactory = SQLiteCursor.FACTORY;
                        break;
                    case 2:
                        cursorFactory = SQLiteDirectCursor.FACTORY;
                        break;
                    case 3:
                        cursorFactory = null;
                        break;
                    default:
                        cursorFactory = null;
                        break;
                }
            }
        }
        cursorFactory = C7595f.FACTORY;
        Cursor queryWithFactory = sQLiteDatabase.queryWithFactory(cursorFactory, false, str, strArr, str2, strArr2, str3, str4, str5, null);
        if (i == 0 || i == 4) {
            Object c6397a2 = queryWithFactory;
        } else {
            c6397a2 = new C6397a(queryWithFactory, str);
        }
        AppMethodBeat.m2505o(59079);
        return c6397a2;
    }

    public final long insert(String str, String str2, ContentValues contentValues) {
        AppMethodBeat.m2504i(59080);
        long insert;
        if (this.ybt != null) {
            insert = this.ybt.insert(str, str2, contentValues);
            AppMethodBeat.m2505o(59080);
            return insert;
        }
        insert = this.ybu.insert(str, str2, contentValues);
        AppMethodBeat.m2505o(59080);
        return insert;
    }

    public final int update(String str, ContentValues contentValues, String str2, String[] strArr) {
        AppMethodBeat.m2504i(59081);
        int update;
        if (this.ybt != null) {
            update = this.ybt.update(str, contentValues, str2, strArr);
            AppMethodBeat.m2505o(59081);
            return update;
        }
        update = this.ybu.update(str, contentValues, str2, strArr);
        AppMethodBeat.m2505o(59081);
        return update;
    }

    public final long replace(String str, String str2, ContentValues contentValues) {
        AppMethodBeat.m2504i(59082);
        long replace;
        if (this.ybt != null) {
            replace = this.ybt.replace(str, str2, contentValues);
            AppMethodBeat.m2505o(59082);
            return replace;
        }
        replace = this.ybu.replace(str, str2, contentValues);
        AppMethodBeat.m2505o(59082);
        return replace;
    }

    public final int delete(String str, String str2, String[] strArr) {
        AppMethodBeat.m2504i(59083);
        int delete;
        if (this.ybt != null) {
            delete = this.ybt.delete(str, str2, strArr);
            AppMethodBeat.m2505o(59083);
            return delete;
        }
        delete = this.ybu.delete(str, str2, strArr);
        AppMethodBeat.m2505o(59083);
        return delete;
    }

    public final void beginTransaction() {
        AppMethodBeat.m2504i(59084);
        try {
            if (this.ybt != null) {
                this.ybt.beginTransaction();
                AppMethodBeat.m2505o(59084);
                return;
            }
            this.ybu.beginTransaction();
            AppMethodBeat.m2505o(59084);
        } catch (Exception e) {
            C4990ab.printErrStackTrace("MicroMsg.MMDataBase", e, "", new Object[0]);
            AppMethodBeat.m2505o(59084);
        }
    }

    public final void endTransaction() {
        AppMethodBeat.m2504i(59085);
        try {
            if (this.ybt != null) {
                this.ybt.setTransactionSuccessful();
                this.ybt.endTransaction();
                AppMethodBeat.m2505o(59085);
                return;
            }
            this.ybu.setTransactionSuccessful();
            this.ybu.endTransaction();
            AppMethodBeat.m2505o(59085);
        } catch (Exception e) {
            C4990ab.printErrStackTrace("MicroMsg.MMDataBase", e, "", new Object[0]);
            AppMethodBeat.m2505o(59085);
        }
    }

    /* renamed from: a */
    public static boolean m10547a(C6395f c6395f, String str) {
        AppMethodBeat.m2504i(59086);
        if (c6395f == null || str == null) {
            String str2 = "MicroMsg.MMDataBase";
            String str3 = "[arthurdan.checkTableExist] Notice!!! null == db: %b, table: %s";
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(c6395f == null);
            objArr[1] = str;
            C4990ab.m7413e(str2, str3, objArr);
            AppMethodBeat.m2505o(59086);
            return false;
        }
        android.database.Cursor a = c6395f.mo14612a("select tbl_name from sqlite_master  where type = \"table\" and tbl_name=\"" + str + "\"", null, 0);
        if (a == null) {
            AppMethodBeat.m2505o(59086);
            return false;
        }
        int count = a.getCount();
        a.close();
        if (count > 0) {
            AppMethodBeat.m2505o(59086);
            return true;
        }
        AppMethodBeat.m2505o(59086);
        return false;
    }
}
