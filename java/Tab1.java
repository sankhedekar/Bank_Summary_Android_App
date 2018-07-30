package com.example.sankhedekar.test;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

public class Tab1 extends Fragment {

    TextInputLayout tilWFCValue, tilWFSValue, tilCCValue, tilFBCValue, tilFBSValue, tilCashValue;
    EditText WFCValue, WFSValue, CCValue, FBCValue, FBSValue, CashValue;
    String txtWFCValue, txtWFSValue, txtCCValue, txtFBCValue, txtFBSValue, txtCashValue;
    TextView TotalValue;
    Button btnSubmit, btnReset;
    float flWFC, flWFS, flCC, flFBC, flFBS, flCash;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab1, container, false);
        super.onCreate(savedInstanceState);

        tilWFCValue = view.findViewById(R.id.tilWFC);
        tilWFSValue = view.findViewById(R.id.tilWFS);
        tilCCValue = view.findViewById(R.id.tilCC);
        tilFBCValue = view.findViewById(R.id.tilFBC);
        tilFBSValue = view.findViewById(R.id.tilFBS);
        tilCashValue = view.findViewById(R.id.tilCash);

        WFCValue = view.findViewById(R.id.WFCValue);
        WFSValue = view.findViewById(R.id.WFSValue);
        CCValue = view.findViewById(R.id.CCValue);
        FBCValue = view.findViewById(R.id.FBCValue);
        FBSValue = view.findViewById(R.id.FBSValue);
        CashValue = view.findViewById(R.id.CashValue);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnReset = view.findViewById(R.id.btnReset);
        TotalValue = view.findViewById(R.id.TotalValue);

        WFCValue.addTextChangedListener(new text_Watcher(WFCValue));
        WFSValue.addTextChangedListener(new text_Watcher(WFSValue));
        CCValue.addTextChangedListener(new text_Watcher(CCValue));
        FBCValue.addTextChangedListener(new text_Watcher(FBCValue));
        FBSValue.addTextChangedListener(new text_Watcher(FBSValue));
        CashValue.addTextChangedListener(new text_Watcher(CashValue));

        // Submit Button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtWFCValue = WFCValue.getText().toString().trim();
                txtWFSValue = WFSValue.getText().toString().trim();
                txtCCValue = CCValue.getText().toString().trim();
                txtFBCValue = FBCValue.getText().toString().trim();
                txtFBSValue = FBSValue.getText().toString().trim();
                txtCashValue = CashValue.getText().toString().trim();

                if (validateAmountAll()) {
                    float total = Float.parseFloat(txtWFCValue) + Float.parseFloat(txtWFSValue) +
                                  Float.parseFloat(txtCCValue) + Float.parseFloat(txtFBCValue) +
                                  Float.parseFloat(txtFBSValue)+ Float.parseFloat(txtCashValue);
                    String str_total = "Total Amount is: " + String.valueOf(total);
                    TotalValue.setText(str_total);
                }
            }
        });

        // Reset Button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
                WFCValue.requestFocus();
            }
        });

        return view;
    }

    public void Reset(){
        WFCValue.setText("");
        tilWFCValue.setErrorEnabled(false);
        WFSValue.setText("");
        tilWFSValue.setErrorEnabled(false);
        CCValue.setText("");
        tilCCValue.setErrorEnabled(false);
        FBCValue.setText("");
        tilFBCValue.setErrorEnabled(false);
        FBSValue.setText("");
        tilFBSValue.setErrorEnabled(false);
        CashValue.setText("");
        tilCashValue.setErrorEnabled(false);
        TotalValue.setText("");
    }

    public boolean validateAmountAll(){
        boolean boolWFC;
        boolean boolWFS;
        boolean boolCC;
        boolean boolFBC;
        boolean boolFBS;
        boolean boolCash;

        if(!txtWFCValue.isEmpty()){
            try {
                flWFC = Float.parseFloat(txtWFCValue);
                boolWFC = true;
                tilWFCValue.setErrorEnabled(false);
            } catch (NumberFormatException ex) {
                tilWFCValue.setErrorEnabled(true);
                tilWFCValue.setError(getString(R.string.err_amount));
                boolWFC = false;
            }
        }
        else{
            tilWFCValue.setErrorEnabled(true);
            tilWFCValue.setError(getString(R.string.err_amount));
            boolWFC = false;
        }

        if(!txtWFSValue.isEmpty()){
            try {
                flWFS = Float.parseFloat(txtWFSValue);
                boolWFS = true;
                tilWFSValue.setErrorEnabled(false);
            } catch (NumberFormatException ex) {
                tilWFSValue.setErrorEnabled(true);
                tilWFSValue.setError(getString(R.string.err_amount));
                boolWFS = false;
            }
        }
        else {
            tilWFSValue.setErrorEnabled(true);
            tilWFSValue.setError(getString(R.string.err_amount));
            boolWFS = false;
        }

        if(!txtCCValue.isEmpty()){
            try {
                flCC = Float.parseFloat(txtCCValue);
                boolCC = true;
                tilCCValue.setErrorEnabled(false);
            } catch (NumberFormatException ex) {
                tilCCValue.setErrorEnabled(true);
                tilCCValue.setError(getString(R.string.err_amount));
                boolCC = false;
            }
        }
        else {
            tilCCValue.setErrorEnabled(true);
            tilCCValue.setError(getString(R.string.err_amount));
            boolCC = false;
        }

        if(!txtFBCValue.isEmpty()){
            try {
                flFBC = Float.parseFloat(txtFBCValue);
                boolFBC = true;
                tilFBCValue.setErrorEnabled(false);
            } catch (NumberFormatException ex) {
                tilFBCValue.setErrorEnabled(true);
                tilFBCValue.setError(getString(R.string.err_amount));
                boolFBC = false;
            }
        }
        else {
            tilFBCValue.setErrorEnabled(true);
            tilFBCValue.setError(getString(R.string.err_amount));
            boolFBC = false;
        }

        if(!txtFBSValue.isEmpty()){
            try {
                flFBS = Float.parseFloat(txtFBSValue);
                boolFBS = true;
                tilFBSValue.setErrorEnabled(false);
            } catch (NumberFormatException ex) {
                tilFBSValue.setErrorEnabled(true);
                tilFBSValue.setError(getString(R.string.err_amount));
                boolFBS = false;
            }
        }
        else {
            tilFBSValue.setErrorEnabled(true);
            tilFBSValue.setError(getString(R.string.err_amount));
            boolFBS = false;
        }

        if(!txtCashValue.isEmpty()){
            try {
                flCash = Float.parseFloat(txtCashValue);
                boolCash = true;
                tilCashValue.setErrorEnabled(false);
            } catch (NumberFormatException ex) {
                tilCashValue.setErrorEnabled(true);
                tilCashValue.setError(getString(R.string.err_amount));
                boolCash = false;
            }
        }
        else {
            tilCashValue.setErrorEnabled(true);
            tilCashValue.setError(getString(R.string.err_amount));
            boolCash = false;
        }

        if (boolWFC && boolWFS && boolCC && boolFBC && boolFBS && boolCash){
            return true;
        }
        else{
            return false;
        }
    }

    public void validateAmount(EditText editText, TextInputLayout textInputLayout) {
        String editTextValue = editText.getText().toString().trim();
        if(!editTextValue.isEmpty()) {
            try {
                Float.parseFloat(editTextValue);
                textInputLayout.setErrorEnabled(false);
            } catch (NumberFormatException ex) {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(getString(R.string.err_amount));
            }
        }
        else {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(getString(R.string.err_amount_empty));
        }
    }

    public class text_Watcher implements TextWatcher {

        public View view;

        private text_Watcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.WFCValue:
                    validateAmount(WFCValue, tilWFCValue);
                    break;
                case R.id.WFSValue:
                    validateAmount(WFSValue, tilWFSValue);
                    break;
                case R.id.CCValue:
                    validateAmount(CCValue, tilCCValue);
                    break;
                case R.id.FBCValue:
                    validateAmount(FBCValue, tilFBCValue);
                    break;
                case R.id.FBSValue:
                    validateAmount(FBSValue, tilFBSValue);
                    break;
                case R.id.CashValue:
                    validateAmount(CashValue, tilCashValue);
                    break;
            }
        }
    }
}
