package net.ynotapps.prayfor.ui.views.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.rengwuxian.materialedittext.MaterialEditText;

import net.ynotapps.prayfor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dougylee on 12/04/15.
 */
public abstract class EditTextDialog extends AlertDialog {

    private List<MaterialEditText> fieldList = new ArrayList<>();

    public EditTextDialog(Context context, List<String> titles, List<String> hints) {
        super(context);
        init(titles, hints);
    }

    public abstract void processFields(List<MaterialEditText> fieldList);

    private void init(List<String> titles, List<String> hints) {

        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < titles.size(); i++) {

            // Failsafe to prevent out of bounds errors
            if (hints.size() == i) {
                break;
            }

            String title = titles.get(i);
            String hint = hints.get(i);

            MaterialEditText editText = (MaterialEditText) View.inflate(getContext(), R.layout.insert_material_edit_text, null);
            editText.setFloatingLabelText(title);
            editText.setHint(hint);
            fieldList.add(editText);

            container.addView(editText);
        }

        setView(container);
    }

    public List<MaterialEditText> getFieldList() {
        return fieldList;
    }
}
