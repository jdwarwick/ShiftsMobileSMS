package jon.john.com.myapplicationtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by VocaLink on 05/11/2017.
 */

public class SMSLog extends BroadcastReceiver {

    private SharedPreferences preferences;
    private String sms_code;
    private String[] code;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msg_from;
            String msg_body="";
            if (bundle != null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                        msg_from = msgs[i].getOriginatingAddress();
                        msg_body = msgs[i].getMessageBody();



                    }
                    code = msg_body.split("(?x):");
                    sms_code = code[2];
                    try
                    {


                    }
                    catch (Exception p){}
                }
                catch(Exception e) {
                    Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }

    /**
     * Method to convert current long type timestamp to human readable format - Easter egg ;)
     */
    public String getreadabledate() {
        long milles = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm", Locale.getDefault());
        Date resultdate = new Date(milles);
        return df.format(resultdate);
    }
}
