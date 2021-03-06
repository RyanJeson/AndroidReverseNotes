package com.tencent.p177mm.boot.svg.p708a.p709a;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Looper;
import com.tencent.p177mm.svg.C5163c;
import com.tencent.p177mm.svg.WeChatSVGRenderC2Java;
import com.tencent.smtt.sdk.WebView;
import com.tencent.view.C31128d;

/* renamed from: com.tencent.mm.boot.svg.a.a.po */
public final class C25922po extends C5163c {
    private final int height = 204;
    private final int width = C31128d.MIC_PTU_ZIPAI_GRADIENT_MILKCOFFEE;

    /* renamed from: a */
    public final int mo10620a(int i, Object... objArr) {
        switch (i) {
            case 0:
                return C31128d.MIC_PTU_ZIPAI_GRADIENT_MILKCOFFEE;
            case 1:
                return 204;
            case 2:
                Canvas canvas = (Canvas) objArr[0];
                Looper looper = (Looper) objArr[1];
                Matrix h = C5163c.m7881h(looper);
                float[] g = C5163c.m7880g(looper);
                Paint k = C5163c.m7883k(looper);
                k.setFlags(385);
                k.setStyle(Style.FILL);
                Paint k2 = C5163c.m7883k(looper);
                k2.setFlags(385);
                k2.setStyle(Style.STROKE);
                k.setColor(WebView.NIGHT_MODE_COLOR);
                k2.setStrokeWidth(1.0f);
                k2.setStrokeCap(Cap.BUTT);
                k2.setStrokeJoin(Join.MITER);
                k2.setStrokeMiter(4.0f);
                k2.setPathEffect(null);
                C5163c.m7876a(k2, looper).setStrokeWidth(1.0f);
                Paint a = C5163c.m7876a(k, looper);
                a.setColor(-13158344);
                canvas.save();
                g = C5163c.m7878a(g, 1.0f, 0.0f, 2.0f, 0.0f, 1.0f, 2.0f);
                h.reset();
                h.setValues(g);
                canvas.concat(h);
                canvas.save();
                Paint a2 = C5163c.m7876a(a, looper);
                Path l = C5163c.m7884l(looper);
                l.moveTo(245.998f, 169.0001f);
                l.lineTo(152.0f, 169.0001f);
                l.lineTo(152.0f, 169.0001f);
                l.lineTo(108.0f, 169.0001f);
                l.lineTo(108.0f, 169.0001f);
                l.lineTo(14.002f, 169.0001f);
                l.cubicTo(7.936f, 169.0001f, 3.0f, 164.0641f, 3.0f, 157.9971f);
                l.lineTo(3.0f, 138.0001f);
                l.lineTo(257.0f, 138.0001f);
                l.lineTo(257.0f, 157.9971f);
                l.cubicTo(257.0f, 164.0641f, 252.064f, 169.0001f, 245.998f, 169.0001f);
                l.lineTo(245.998f, 169.0001f);
                l.close();
                l.moveTo(14.002f, 3.0001f);
                l.lineTo(245.998f, 3.0001f);
                l.cubicTo(252.064f, 3.0001f, 257.0f, 7.9361f, 257.0f, 14.0021f);
                l.lineTo(257.0f, 136.0001f);
                l.lineTo(3.0f, 136.0001f);
                l.lineTo(3.0f, 14.0021f);
                l.cubicTo(3.0f, 7.9361f, 7.936f, 3.0001f, 14.002f, 3.0001f);
                l.lineTo(14.002f, 3.0001f);
                l.close();
                l.moveTo(164.703f, 197.0001f);
                l.lineTo(95.297f, 197.0001f);
                l.lineTo(107.697f, 194.5201f);
                l.lineTo(110.634f, 172.0001f);
                l.lineTo(149.366f, 172.0001f);
                l.lineTo(152.303f, 194.5201f);
                l.lineTo(164.703f, 197.0001f);
                l.close();
                l.moveTo(245.998f, 1.0E-4f);
                l.lineTo(14.002f, 1.0E-4f);
                l.cubicTo(6.266f, 1.0E-4f, 0.0f, 6.2691f, 0.0f, 14.0021f);
                l.lineTo(0.0f, 157.9971f);
                l.cubicTo(0.0f, 165.7261f, 6.269f, 172.0001f, 14.002f, 172.0001f);
                l.lineTo(107.609f, 172.0001f);
                l.lineTo(105.0f, 192.0001f);
                l.lineTo(90.0f, 195.0001f);
                l.lineTo(90.0f, 200.0001f);
                l.lineTo(170.0f, 200.0001f);
                l.lineTo(170.0f, 195.0001f);
                l.lineTo(155.0f, 192.0001f);
                l.lineTo(152.391f, 172.0001f);
                l.lineTo(245.998f, 172.0001f);
                l.cubicTo(253.734f, 172.0001f, 260.0f, 165.7311f, 260.0f, 157.9971f);
                l.lineTo(260.0f, 14.0021f);
                l.cubicTo(260.0f, 6.2741f, 253.731f, 1.0E-4f, 245.998f, 1.0E-4f);
                l.lineTo(245.998f, 1.0E-4f);
                l.close();
                WeChatSVGRenderC2Java.setFillType(l, 2);
                canvas.drawPath(l, a2);
                canvas.restore();
                canvas.restore();
                C5163c.m7882j(looper);
                break;
        }
        return 0;
    }
}
