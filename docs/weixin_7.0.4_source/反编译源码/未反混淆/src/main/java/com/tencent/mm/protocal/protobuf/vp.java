package com.tencent.mm.protocal.protobuf;

import com.tencent.matrix.trace.core.AppMethodBeat;
import com.tencent.mm.bt.a;

public final class vp extends a {
    public int Ret;
    public int vEp;

    public final int op(int i, Object... objArr) {
        AppMethodBeat.i(51387);
        int bs;
        if (i == 0) {
            e.a.a.c.a aVar = (e.a.a.c.a) objArr[0];
            aVar.iz(1, this.Ret);
            aVar.iz(2, this.vEp);
            AppMethodBeat.o(51387);
            return 0;
        } else if (i == 1) {
            bs = (e.a.a.b.b.a.bs(1, this.Ret) + 0) + e.a.a.b.b.a.bs(2, this.vEp);
            AppMethodBeat.o(51387);
            return bs;
        } else if (i == 2) {
            e.a.a.a.a aVar2 = new e.a.a.a.a((byte[]) objArr[0], unknownTagHandler);
            for (bs = a.getNextFieldNumber(aVar2); bs > 0; bs = a.getNextFieldNumber(aVar2)) {
                if (!super.populateBuilderWithField(aVar2, this, bs)) {
                    aVar2.ems();
                }
            }
            AppMethodBeat.o(51387);
            return 0;
        } else if (i == 3) {
            e.a.a.a.a aVar3 = (e.a.a.a.a) objArr[0];
            vp vpVar = (vp) objArr[1];
            switch (((Integer) objArr[2]).intValue()) {
                case 1:
                    vpVar.Ret = aVar3.BTU.vd();
                    AppMethodBeat.o(51387);
                    return 0;
                case 2:
                    vpVar.vEp = aVar3.BTU.vd();
                    AppMethodBeat.o(51387);
                    return 0;
                default:
                    AppMethodBeat.o(51387);
                    return -1;
            }
        } else {
            AppMethodBeat.o(51387);
            return -1;
        }
    }
}
