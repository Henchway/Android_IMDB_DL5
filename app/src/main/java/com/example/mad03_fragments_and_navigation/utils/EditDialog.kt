package com.example.mad03_fragments_and_navigation.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class EditDialog : DialogFragment() {

    internal lateinit var listener: DialogListener

    interface DialogListener {
        fun onDialogPositiveClick(text: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as DialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(
                (context.toString() +
                        " must implement DialogListener")
            )
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val input: EditText = EditText(context)
            builder.setView(input)
                .setPositiveButton(
                    "Save", DialogInterface.OnClickListener { _, _ -> input.text.toString() })
                // Create the AlertDialog object and return it
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
