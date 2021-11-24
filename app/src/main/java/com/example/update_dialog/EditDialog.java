package com.example.update_dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * This class manages the Dialog that opens up in order to edit an item of the list.
 *
 * Layout File: layout_dialog.xml
 */
public class EditDialog extends AppCompatDialogFragment {

    private EditText editItem;
    private EditText editQuantity;
    private EditDialogListener listener;

    /**
     * The onCreateDialog method directs everything that needs to be done when the user opens the Dialog.
     * This includes the Layout for the Dialog by creating a Builder and assigning the Layout for the Dialog.
     * There is also the negative and positive feedback from the user that gets handled here.
     * @param savedInstanceState is a standard parameter.
     * @return builder.create() which creates the dialog for the user.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view);
        builder.setTitle("Edit Item");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { }
        });

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String item = editItem.getText().toString();
                String quantity = editQuantity.getText().toString();

                // The listener calls the applyTexts method which edits the item of the Shopping List
                listener.applyTexts(item, quantity);
            }
        });

        editItem = view.findViewById(R.id.edit_item);
        editQuantity = view.findViewById(R.id.edit_quantity);

        return builder.create();
    }

    /**
     * This method initializes the listener.
     * If the listener cannot be defined there is an Exception.
     * That exception demands the implementation of the EditDialog interface.
     * @param context is the activity.
     */
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);

        try {

            listener = (EditDialogListener) context;
        }
        catch (ClassCastException e) {

            throw new ClassCastException(context.toString() +"must implement EditDialogListener");
        }
    }

    /**
     * The EditDialogListener is an interface for implementing an applyTexts method to different classes.
     * It gets used in the MainActivity and the EditDialog.
     * The applyTexts method is necessary in order to get the user input from the dialog to the list itself.
     */
    public interface EditDialogListener {

        void applyTexts(String item, String quantity);
    }
}
