package com.example.sankhedekar.test;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Tab2 extends Fragment {

    TextInputLayout tilCredit;
    EditText CreditValue;
    TextView TextCreditCard;
    Button btnSubmit, btnReset;
    float flCredit;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab2, container, false);
        super.onCreate(savedInstanceState);

        tilCredit = view.findViewById(R.id.tilCredit);
        CreditValue = view.findViewById(R.id.CreditValue);

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnReset = view.findViewById(R.id.btnReset);

        TextCreditCard = view.findViewById(R.id.TextCreditCard);

        CreditValue.addTextChangedListener(new Tab2.text_Watcher(CreditValue));

        // Submit Button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateAmountAll(container)) {
                    flCredit = Float.parseFloat(CreditValue.getText().toString().trim());
                    String str_total = "Credit Card Amount is: " + String.valueOf(flCredit);
                    TextCreditCard.setText(str_total);
                }
            }
        });

        // Reset Button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Reset();
            CreditValue.requestFocus();
            }
        });

        return view;
    }

    public void Reset(){
        CreditValue.setText("");
        tilCredit.setErrorEnabled(false);
    }

    public boolean validateAmountAll(ViewGroup viewGroup){
        final int childrenCount = viewGroup.getChildCount();
        for (int i = 0; i < childrenCount; i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof EditText) {
                if (((EditText) child).getText().toString().isEmpty())
                    ((EditText) child).setError(getText(R.string.err_amount_empty));
                return false;
            }
        }
        return true;
    }

    public void validateAmount(EditText editText, TextInputLayout textInputLayout) {
        String editTextValue = editText.getText().toString().trim();
        if(editTextValue.isEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(getString(R.string.err_amount_empty));
        }
        else {
            textInputLayout.setErrorEnabled(false);
        }

        try {
            Float.parseFloat(editTextValue);
        } catch (NumberFormatException ex) {
            editText.setError(getString(R.string.err_amount));
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
                case R.id.CreditValue:
                    validateAmount(CreditValue, tilCredit);
                    break;
            }
        }
    }
}
