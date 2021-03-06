package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.w;
import android.support.v4.widget.m;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.tencent.mm.R;
import java.lang.reflect.Field;

class ab extends ListView {
    private final Rect ahV = new Rect();
    private int ahW = 0;
    private int ahX = 0;
    private int ahY = 0;
    private int ahZ = 0;
    private int aia;
    private Field aib;
    private a aic;
    private boolean aid;
    private boolean aie;
    private boolean aif;
    private w aig;
    private m aih;
    private b aii;

    class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(ab abVar, byte b) {
            this();
        }

        public final void run() {
            ab.this.aii = null;
            ab.this.drawableStateChanged();
        }
    }

    static class a extends android.support.v7.d.a.a {
        boolean OH = true;

        a(Drawable drawable) {
            super(drawable);
        }

        public final boolean setState(int[] iArr) {
            if (this.OH) {
                return super.setState(iArr);
            }
            return false;
        }

        public final void draw(Canvas canvas) {
            if (this.OH) {
                super.draw(canvas);
            }
        }

        public final void setHotspot(float f, float f2) {
            if (this.OH) {
                super.setHotspot(f, f2);
            }
        }

        public final void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.OH) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        public final boolean setVisible(boolean z, boolean z2) {
            if (this.OH) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    ab(Context context, boolean z) {
        super(context, null, R.attr.fc);
        this.aie = z;
        setCacheColorHint(0);
        try {
            this.aib = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.aib.setAccessible(true);
        } catch (NoSuchFieldException e) {
        }
    }

    public boolean isInTouchMode() {
        return (this.aie && this.aid) || super.isInTouchMode();
    }

    public boolean hasWindowFocus() {
        return this.aie || super.hasWindowFocus();
    }

    public boolean isFocused() {
        return this.aie || super.isFocused();
    }

    public boolean hasFocus() {
        return this.aie || super.hasFocus();
    }

    public void setSelector(Drawable drawable) {
        this.aic = drawable != null ? new a(drawable) : null;
        super.setSelector(this.aic);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.ahW = rect.left;
        this.ahX = rect.top;
        this.ahY = rect.right;
        this.ahZ = rect.bottom;
    }

    /* Access modifiers changed, original: protected */
    public void drawableStateChanged() {
        if (this.aii == null) {
            super.drawableStateChanged();
            setSelectorEnabled(true);
            ia();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.aia = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
                break;
        }
        if (this.aii != null) {
            b bVar = this.aii;
            ab.this.aii = null;
            ab.this.removeCallbacks(bVar);
        }
        return super.onTouchEvent(motionEvent);
    }

    public int d(int i, int i2, int i3, int i4, int i5) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        listPaddingBottom += listPaddingTop;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int i6 = 0;
        View view = null;
        int i7 = 0;
        int count = adapter.getCount();
        int i8 = 0;
        while (i8 < count) {
            listPaddingTop = adapter.getItemViewType(i8);
            if (listPaddingTop != i7) {
                view = null;
                i7 = listPaddingTop;
            }
            view = adapter.getView(i8, view, this);
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            if (layoutParams.height > 0) {
                listPaddingTop = MeasureSpec.makeMeasureSpec(layoutParams.height, ErrorDialogData.SUPPRESSED);
            } else {
                listPaddingTop = MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, listPaddingTop);
            view.forceLayout();
            if (i8 > 0) {
                listPaddingTop = listPaddingBottom + dividerHeight;
            } else {
                listPaddingTop = listPaddingBottom;
            }
            listPaddingBottom = view.getMeasuredHeight() + listPaddingTop;
            if (listPaddingBottom < i4) {
                if (i5 < 0 || i8 < i5) {
                    listPaddingTop = i6;
                } else {
                    listPaddingTop = listPaddingBottom;
                }
                i8++;
                i6 = listPaddingTop;
            } else if (i5 < 0 || i8 <= i5 || i6 <= 0 || listPaddingBottom == i4) {
                return i4;
            } else {
                return i6;
            }
        }
        return listPaddingBottom;
    }

    private void setSelectorEnabled(boolean z) {
        if (this.aic != null) {
            this.aic.OH = z;
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.aii == null) {
            this.aii = new b(this, (byte) 0);
            b bVar = this.aii;
            ab.this.post(bVar);
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            actionMasked = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (actionMasked == -1 || actionMasked == getSelectedItemPosition()) {
                return onHoverEvent;
            }
            View childAt = getChildAt(actionMasked - getFirstVisiblePosition());
            if (childAt.isEnabled()) {
                setSelectionFromTop(actionMasked, childAt.getTop() - getTop());
            }
            ia();
            return onHoverEvent;
        }
        setSelection(-1);
        return onHoverEvent;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        this.aii = null;
        super.onDetachedFromWindow();
    }

    public boolean b(MotionEvent motionEvent, int i) {
        boolean z = true;
        Object obj = null;
        int actionMasked = motionEvent.getActionMasked();
        switch (actionMasked) {
            case 1:
                z = false;
                break;
            case 2:
                break;
            case 3:
                z = false;
                break;
        }
        int findPointerIndex = motionEvent.findPointerIndex(i);
        if (findPointerIndex < 0) {
            z = false;
        } else {
            int x = (int) motionEvent.getX(findPointerIndex);
            findPointerIndex = (int) motionEvent.getY(findPointerIndex);
            int pointToPosition = pointToPosition(x, findPointerIndex);
            if (pointToPosition == -1) {
                obj = 1;
            } else {
                Object obj2;
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                float f = (float) x;
                float f2 = (float) findPointerIndex;
                this.aif = true;
                if (VERSION.SDK_INT >= 21) {
                    drawableHotspotChanged(f, f2);
                }
                if (!isPressed()) {
                    setPressed(true);
                }
                layoutChildren();
                if (this.aia != -1) {
                    View childAt2 = getChildAt(this.aia - getFirstVisiblePosition());
                    if (!(childAt2 == null || childAt2 == childAt || !childAt2.isPressed())) {
                        childAt2.setPressed(false);
                    }
                }
                this.aia = pointToPosition;
                float left = f - ((float) childAt.getLeft());
                float top = f2 - ((float) childAt.getTop());
                if (VERSION.SDK_INT >= 21) {
                    childAt.drawableHotspotChanged(left, top);
                }
                if (!childAt.isPressed()) {
                    childAt.setPressed(true);
                }
                Drawable selector = getSelector();
                if (selector == null || pointToPosition == -1) {
                    obj2 = null;
                } else {
                    obj2 = 1;
                }
                if (obj2 != null) {
                    selector.setVisible(false, false);
                }
                Rect rect = this.ahV;
                rect.set(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
                rect.left -= this.ahW;
                rect.top -= this.ahX;
                rect.right += this.ahY;
                rect.bottom += this.ahZ;
                try {
                    z = this.aib.getBoolean(this);
                    if (childAt.isEnabled() != z) {
                        this.aib.set(this, Boolean.valueOf(!z));
                        if (pointToPosition != -1) {
                            refreshDrawableState();
                        }
                    }
                } catch (IllegalAccessException e) {
                }
                if (obj2 != null) {
                    rect = this.ahV;
                    top = rect.exactCenterX();
                    float exactCenterY = rect.exactCenterY();
                    selector.setVisible(getVisibility() == 0, false);
                    android.support.v4.a.a.a.a(selector, top, exactCenterY);
                }
                Drawable selector2 = getSelector();
                if (!(selector2 == null || pointToPosition == -1)) {
                    android.support.v4.a.a.a.a(selector2, f, f2);
                }
                setSelectorEnabled(false);
                refreshDrawableState();
                z = true;
                if (actionMasked == 1) {
                    performItemClick(childAt, pointToPosition, getItemIdAtPosition(pointToPosition));
                }
            }
        }
        if (!(z && obj == null)) {
            this.aif = false;
            setPressed(false);
            drawableStateChanged();
            View childAt3 = getChildAt(this.aia - getFirstVisiblePosition());
            if (childAt3 != null) {
                childAt3.setPressed(false);
            }
            if (this.aig != null) {
                this.aig.cancel();
                this.aig = null;
            }
        }
        if (z) {
            if (this.aih == null) {
                this.aih = new m(this);
            }
            this.aih.G(true);
            this.aih.onTouch(this, motionEvent);
        } else if (this.aih != null) {
            this.aih.G(false);
        }
        return z;
    }

    /* Access modifiers changed, original: 0000 */
    public void setListSelectionHidden(boolean z) {
        this.aid = z;
    }

    private void ia() {
        Drawable selector = getSelector();
        if (selector != null && this.aif && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    /* Access modifiers changed, original: protected */
    public void dispatchDraw(Canvas canvas) {
        if (!this.ahV.isEmpty()) {
            Drawable selector = getSelector();
            if (selector != null) {
                selector.setBounds(this.ahV);
                selector.draw(canvas);
            }
        }
        super.dispatchDraw(canvas);
    }
}
