package com.example.babycare;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


/**
 * This class is responsible for changing the alert message by the user
 */
public class NewMessageDialog extends AppCompatDialogFragment {

    private EditText editTitle;
    private EditText editMessage;
    private NewMessageDialogListener listener;


    /**
     * this function is opening a dialog box that the user can type in his own message
     * @param savedInstanceState bundle
     * @return builder
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Change message")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = editTitle.getText().toString();
                        String message = editMessage.getText().toString();
                        listener.applyTexts(title, message);

                    }
                });

        editTitle = view.findViewById(R.id.edit_title);
        editMessage = view.findViewById(R.id.edit_message);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (NewMessageDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement NewmessageDialogListener");
        }

    }

    /**
     * this function is delivering the title and the message that was set by the user
     */
    public interface NewMessageDialogListener{
        void applyTexts(String title, String message);
    }
}
