package com.coderglasser.individualproject;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class AddDialog extends Dialog {
    Activity context;
    private Button inputButton;
    private Button outputButton;
    public EditText inputNumber;
    public ImageView addImage;
    public TextView addText;
    private View.OnClickListener inputListener;
    private View.OnClickListener outputListener;
    OnMyDialogResult myDialogResult;

    public AddDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public AddDialog(Activity context, View.OnClickListener inputListener, View.OnClickListener outputListener) {
        super(context);
        this.context = context;
        this.inputListener = inputListener;
        this.outputListener = outputListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.input_window);
        inputButton = (Button) findViewById(R.id.btn_input);
        outputButton = (Button) findViewById(R.id.btn_output);
        inputNumber = (EditText) findViewById(R.id.txt_number);
        addImage = (ImageView) findViewById(R.id.add_icon);
        addText = (TextView) findViewById(R.id.add_text);

        inputButton.setOnClickListener(inputListener);
        outputButton.setOnClickListener(outputListener);
        inputListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.btn_input){
                    if( myDialogResult != null ){
                        Date date = new Date();
                        myDialogResult.finish(new Data(date.getTime(),addImage.getId(),String.valueOf(addText.getText()),String.valueOf(inputNumber.getText()),date));
                    }
                    AddDialog.this.dismiss();
                }
            }
        };
        outputListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.btn_output){
                    if( myDialogResult != null ){
                        Date date = new Date();
                        myDialogResult.finish(new Data(date.getTime(),addImage.getId(),String.valueOf(addText.getText()),"-"+String.valueOf(inputNumber.getText()),date));
                    }
                    AddDialog.this.dismiss();
                }
            }
        };
    }

    public void setDialogResult(OnMyDialogResult dialogResult){
        myDialogResult = dialogResult;
    }

    public interface OnMyDialogResult{
        void finish(Data result);
    }
}

