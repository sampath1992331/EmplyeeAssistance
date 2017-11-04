package com.example.anjaleegamage.employeeassistancesystem;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Umesh on 5/8/2017.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class TimePickerFragmentFrom extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(),this,hour,minute,true);
    }



    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int month) {
        TextView tv2=(TextView) getActivity().findViewById(R.id.ShowTimeFrom);
        tv2.setText(hour+":"+month);
    }
}
