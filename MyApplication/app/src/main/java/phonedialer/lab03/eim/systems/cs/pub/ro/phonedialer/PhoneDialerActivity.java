package phonedialer.lab03.eim.systems.cs.pub.ro.phonedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneDialerActivity extends Activity implements View.OnClickListener{

    public static final int CONTACTS_MANAGER_REQUEST_CODE = 2016;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        Button btn0 = (Button)findViewById(R.id.button_0);
        btn0.setOnClickListener(this);
        Button btn1 = (Button)findViewById(R.id.button_1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button)findViewById(R.id.button_2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button)findViewById(R.id.button_3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button)findViewById(R.id.button_4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button)findViewById(R.id.button_5);
        btn5.setOnClickListener(this);
        Button btn6 = (Button)findViewById(R.id.button_6);
        btn6.setOnClickListener(this);
        Button btn7 = (Button)findViewById(R.id.button_7);
        btn7.setOnClickListener(this);
        Button btn8 = (Button)findViewById(R.id.button_8);
        btn8.setOnClickListener(this);
        Button btn9 = (Button)findViewById(R.id.button_9);
        btn9.setOnClickListener(this);
        Button btn_star = (Button)findViewById(R.id.button_star);
        btn_star.setOnClickListener(this);
        Button btn_diez = (Button)findViewById(R.id.button_hashtag);
        btn_diez.setOnClickListener(this);
        Button btn_add_contact = (Button)findViewById(R.id.add_contact);
        btn_add_contact.setOnClickListener(this);

        ImageButton btn_delete = (ImageButton)findViewById(R.id.button_delete);
        btn_delete.setOnClickListener(this);
        ImageButton btn_call = (ImageButton)findViewById(R.id.button_call);
        btn_call.setOnClickListener(this);
        ImageButton btn_end_call = (ImageButton)findViewById(R.id.button_end_call);
        btn_end_call.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phone_dialer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.button_0: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "0");
                break;
            }

            case  R.id.button_1: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "1");
                break;
            }

            case  R.id.button_2: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "2");
                break;
            }
            case  R.id.button_3: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "3");
                break;
            }
            case  R.id.button_4: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "4");
                break;
            }
            case  R.id.button_5: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "5");
                break;
            }
            case  R.id.button_6: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "6");
                break;
            }
            case  R.id.button_7: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "7");
                break;
            }
            case  R.id.button_8: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "8");
                break;
            }
            case  R.id.button_9: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "9");
                break;
            }
            case  R.id.button_star: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "*");
                break;
            }
            case  R.id.button_hashtag: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                tv.setText(tv.getText() + "#");
                break;
            }
            case  R.id.button_delete: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                if(tv.getText().toString().length() != 0)
                    tv.setText(tv.getText().toString().substring(0,tv.getText().toString().length()-1));
                break;
            }
            case  R.id.button_call: {
                TextView tv = (TextView)findViewById(R.id.show_number);
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + tv.getText().toString()));
                startActivity(intent);
                break;
            }
            case  R.id.button_end_call: {
                finish();
                break;
            }
            case R.id.add_contact:{
                TextView tv = (TextView)findViewById(R.id.show_number);
                String phoneNumber = tv.getText().toString();
                if (phoneNumber.length() > 0) {
                    Intent intent = new Intent("contactsmanager.lab04.pdsd.systems.cs.pub.ro.contactsmanager.intent.action.ContactsManagerActivity");
                    intent.putExtra("contactsmanager.lab04.pdsd.systems.cs.pub.ro.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                    startActivityForResult(intent, CONTACTS_MANAGER_REQUEST_CODE);
                } else {
                    Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
                }

                break;
            }

        }
    }
}
