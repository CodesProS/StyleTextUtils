package io.soham.styletextutils;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BulletSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.TypefaceSpan;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.view.MotionEvent;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import android.text.TextPaint;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
//import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;


public class StyleTextUtils extends AndroidNonvisibleComponent {

    private Context f0a;

     public StyleTextUtils(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.f0a = componentContainer.$context();
    }

    public final class CustomClickableSpan extends ClickableSpan {

    private int gstart;

    private boolean gunderline;

    private AndroidViewComponent gcomponent;

    private String gText;

    private int gcolor;

    private int gend;

    public CustomClickableSpan(AndroidViewComponent component, int start, int end, boolean underline,  String str,  int color) {
        gend = end;
        gunderline = underline;
        gcomponent = component;
        gText = str;
        gstart = start;
        gcolor = color;
    }

    public final void onClick(View view) {
      TextClicked(gcomponent, gText.substring(gstart - 1, gend - 1));
    }

    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(gcolor);
        textPaint.setUnderlineText(gunderline);
    }
}

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public void BlurText(AndroidViewComponent androidViewComponent, int i, int i2, int i3) {
        View view = androidViewComponent.getView();
        if (Check(((TextView) view).getText().toString().length(), i, i2)) {
            BlurMaskFilter blurMaskFilter = new BlurMaskFilter((float) i3, BlurMaskFilter.Blur.NORMAL);
            SpannableString spannableString = new SpannableString(((TextView) view).getText());
            spannableString.setSpan(new MaskFilterSpan(blurMaskFilter), i - 1, i2 - 1, 33);
            ((TextView) view).setText(spannableString);
        }
    }

    public boolean Check(int i, int i2, int i3) {
        if (i2 <= 0) {
            throw new YailRuntimeError("Start position can't be less than 1", "StyleTextUtils");
        } else if (i2 > i3) {
            throw new YailRuntimeError("Start position can't be greater than end position", "StyleTextUtils");
        } else if (i3 > i + 1) {
            throw new YailRuntimeError("End position can't be greater than text length+1", "StyleTextUtils");
        } else if (i2 != i3) {
            return true;
        } else {
            throw new YailRuntimeError("Start position can't be equal to end position", "StyleTextUtils");
        }
    }

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public String GetEndPos(AndroidViewComponent androidViewComponent, String str) {
        String str2 = "";
        View view = androidViewComponent.getView();
        int i = -1;
        boolean z = false;
        while (true) {
            int indexOf = ((TextView) view).getText().toString().indexOf(str, i + 1);
            if (indexOf == -1) {
                return str2;
            }
            str2 = (z ? str2 + "," : str2) + String.valueOf(indexOf + 1 + str.length());
            i = indexOf + 1;
            z = true;
        }
    }

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public String GetStartPos(AndroidViewComponent androidViewComponent, String str) {
        String str2 = "";
        View view = androidViewComponent.getView();
        int i = -1;
        boolean z = false;
        while (true) {
            int indexOf = ((TextView) view).getText().toString().indexOf(str, i + 1);
            if (indexOf == -1) {
                return str2;
            }
            str2 = (z ? str2 + "," : str2) + String.valueOf(indexOf + 1);
            i = indexOf + 1;
            z = true;
        }
    }

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public void RemoveStyling(AndroidViewComponent androidViewComponent) {
        View view = androidViewComponent.getView();
        ((TextView) view).setText(HtmlCompat.fromHtml(((TextView) view).getText().toString(), 0));
    }

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public void SetClickable(AndroidViewComponent component, int start, int end, int color, boolean underline) {
        View view = component.getView();
        if (Check(((TextView) view).getText().toString().length(), start, end)) {
            String charSequence = ((TextView) view).getText().toString();
            SpannableString spannableString = new SpannableString(((TextView) view).getText());
            spannableString.setSpan(new CustomClickableSpan(component, start, end, underline, charSequence, color), start - 1, end - 1, 33);
            ((TextView) view).setMovementMethod(LinkMovementMethod.getInstance());
            ((TextView) view).setText(spannableString);
        }
    }

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public void SetCustomTypeface(AndroidViewComponent androidViewComponent, int i, int i2, String str, int i3) {
        View view = androidViewComponent.getView();
        if (Check(((TextView) view).getText().toString().length(), i, i2)) {
            SpannableString spannableString = new SpannableString(((TextView) view).getText());
            spannableString.setSpan(new TypefaceSpan(Typeface.createFromAsset(this.f0a.getAssets(), str)), i - 1, i2 - 1, 33);
            spannableString.setSpan(new AbsoluteSizeSpan(i3, true), i - 1, i2 - 1, 33);
            ((TextView) view).setText(spannableString);
        }
    }

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public void SetLineHeight(AndroidViewComponent androidViewComponent, int i) {
        View view = androidViewComponent.getView();
        SpannableString spannableString = new SpannableString(((TextView) view).getText());
        spannableString.setSpan(new LineHeightSpan.Standard(i), 0, ((TextView) view).getText().length(), 33);
        ((TextView) view).setText(spannableString);
    }

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public void SetTextBulleted(AndroidViewComponent androidViewComponent, int i, int i2, int i3, int i4, int i5) {
        View view = androidViewComponent.getView();
        if (Check(((TextView) view).getText().toString().length(), i, i2)) {
            SpannableString spannableString = new SpannableString(((TextView) view).getText());
            spannableString.setSpan(new BulletSpan(i4, i5, i3), i - 1, i2 - 1, 33);
            ((TextView) view).setText(spannableString);
        }
    }

    @SimpleEvent(description = "Returns the sum of the given list of integers.")
    public void TextClicked(AndroidViewComponent androidViewComponent, String str) {
        EventDispatcher.dispatchEvent(this, "TextClicked", androidViewComponent, str);
    }
}
