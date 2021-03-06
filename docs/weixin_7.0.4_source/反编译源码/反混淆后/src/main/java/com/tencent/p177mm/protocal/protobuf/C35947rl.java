package com.tencent.p177mm.protocal.protobuf;

import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.p177mm.p205bt.C1331a;
import java.util.LinkedList;
import p690e.p691a.p692a.C6087a;
import p690e.p691a.p692a.p693a.C6086a;
import p690e.p691a.p692a.p695b.p697b.C6091a;
import p690e.p691a.p692a.p698c.C6093a;

/* renamed from: com.tencent.mm.protocal.protobuf.rl */
public final class C35947rl extends bsr {
    public int Scene;
    public String Title;
    public String fJU;
    public String fKh;
    public String vYH;
    public String vYI;
    public String vYJ;
    public String vYK;
    public String vYL;
    public String vYM;

    /* renamed from: op */
    public final int mo4669op(int i, Object... objArr) {
        AppMethodBeat.m2504i(105144);
        int ix;
        if (i == 0) {
            C6093a c6093a = (C6093a) objArr[0];
            if (this.BaseRequest != null) {
                c6093a.mo13479iy(1, this.BaseRequest.computeSize());
                this.BaseRequest.writeFields(c6093a);
            }
            if (this.fJU != null) {
                c6093a.mo13475e(2, this.fJU);
            }
            if (this.Title != null) {
                c6093a.mo13475e(3, this.Title);
            }
            if (this.vYH != null) {
                c6093a.mo13475e(4, this.vYH);
            }
            if (this.vYI != null) {
                c6093a.mo13475e(5, this.vYI);
            }
            if (this.vYJ != null) {
                c6093a.mo13475e(6, this.vYJ);
            }
            if (this.vYK != null) {
                c6093a.mo13475e(7, this.vYK);
            }
            if (this.vYL != null) {
                c6093a.mo13475e(8, this.vYL);
            }
            if (this.fKh != null) {
                c6093a.mo13475e(9, this.fKh);
            }
            c6093a.mo13480iz(10, this.Scene);
            if (this.vYM != null) {
                c6093a.mo13475e(11, this.vYM);
            }
            AppMethodBeat.m2505o(105144);
            return 0;
        } else if (i == 1) {
            if (this.BaseRequest != null) {
                ix = C6087a.m9557ix(1, this.BaseRequest.computeSize()) + 0;
            } else {
                ix = 0;
            }
            if (this.fJU != null) {
                ix += C6091a.m9575f(2, this.fJU);
            }
            if (this.Title != null) {
                ix += C6091a.m9575f(3, this.Title);
            }
            if (this.vYH != null) {
                ix += C6091a.m9575f(4, this.vYH);
            }
            if (this.vYI != null) {
                ix += C6091a.m9575f(5, this.vYI);
            }
            if (this.vYJ != null) {
                ix += C6091a.m9575f(6, this.vYJ);
            }
            if (this.vYK != null) {
                ix += C6091a.m9575f(7, this.vYK);
            }
            if (this.vYL != null) {
                ix += C6091a.m9575f(8, this.vYL);
            }
            if (this.fKh != null) {
                ix += C6091a.m9575f(9, this.fKh);
            }
            ix += C6091a.m9572bs(10, this.Scene);
            if (this.vYM != null) {
                ix += C6091a.m9575f(11, this.vYM);
            }
            AppMethodBeat.m2505o(105144);
            return ix;
        } else if (i == 2) {
            C6086a c6086a = new C6086a((byte[]) objArr[0], unknownTagHandler);
            for (ix = C1331a.getNextFieldNumber(c6086a); ix > 0; ix = C1331a.getNextFieldNumber(c6086a)) {
                if (!super.populateBuilderWithField(c6086a, this, ix)) {
                    c6086a.ems();
                }
            }
            AppMethodBeat.m2505o(105144);
            return 0;
        } else if (i == 3) {
            C6086a c6086a2 = (C6086a) objArr[0];
            C35947rl c35947rl = (C35947rl) objArr[1];
            int intValue = ((Integer) objArr[2]).intValue();
            switch (intValue) {
                case 1:
                    LinkedList Vh = c6086a2.mo13445Vh(intValue);
                    int size = Vh.size();
                    for (intValue = 0; intValue < size; intValue++) {
                        byte[] bArr = (byte[]) Vh.get(intValue);
                        C7267hl c7267hl = new C7267hl();
                        C6086a c6086a3 = new C6086a(bArr, unknownTagHandler);
                        for (boolean z = true; z; z = c7267hl.populateBuilderWithField(c6086a3, c7267hl, C1331a.getNextFieldNumber(c6086a3))) {
                        }
                        c35947rl.BaseRequest = c7267hl;
                    }
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 2:
                    c35947rl.fJU = c6086a2.BTU.readString();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 3:
                    c35947rl.Title = c6086a2.BTU.readString();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 4:
                    c35947rl.vYH = c6086a2.BTU.readString();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 5:
                    c35947rl.vYI = c6086a2.BTU.readString();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 6:
                    c35947rl.vYJ = c6086a2.BTU.readString();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 7:
                    c35947rl.vYK = c6086a2.BTU.readString();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 8:
                    c35947rl.vYL = c6086a2.BTU.readString();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 9:
                    c35947rl.fKh = c6086a2.BTU.readString();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 10:
                    c35947rl.Scene = c6086a2.BTU.mo13458vd();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                case 11:
                    c35947rl.vYM = c6086a2.BTU.readString();
                    AppMethodBeat.m2505o(105144);
                    return 0;
                default:
                    AppMethodBeat.m2505o(105144);
                    return -1;
            }
        } else {
            AppMethodBeat.m2505o(105144);
            return -1;
        }
    }
}
