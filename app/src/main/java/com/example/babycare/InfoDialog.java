package com.example.babycare;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class InfoDialog extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information")
                .setMessage("Hello and welcome to our application!\n" +
                        "\n" +
                        "To use our app, you must have our hardware to connect it via bluetooth.\n" +
                        "\n" +
                        "Here is a short explanation of how to use our app.\n" +
                        "In our app you have 3 buttons: Alert, History and Change message.\n" +
                        "The Alert button is for tests. If you want to check what the alert would look and sound like, you can press it and see.\n" +
                        "If you press the history button a list with all the latest alerts that was activated would be shown. You can erase them too.\n" +
                        "The Change message button lets you change the message that will appear when an alert is activated. You can change the message and then press the alert button and see how it looks.\n" +
                        "We hope you will enjoy our app and never have to use it :)")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
