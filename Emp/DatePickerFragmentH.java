package com.example.anjaleegamage.employeeassistancesystem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Umesh on 5/8/2017.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class DatePickerFragmentH extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        TextView tv1 = (TextView) getActivity().findViewById(R.id.DateShow);
        tv1.setText(yy + "/" + mm + "/" + dd);
    }
}


