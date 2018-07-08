package kumpol.sunteen.co.th.sunteenfriend.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import kumpol.sunteen.co.th.sunteenfriend.R;

public class MyAlertDialog {
    private Context context;

    public MyAlertDialog(Context context) {
        this.context = context;
    }

    public void normatDialog(String titleString, String detailString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setMessage(detailString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }



}//Main Class
