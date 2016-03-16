package contactsmanager.lab04.pdsd.systems.cs.pub.ro.contactsmanager;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BasicDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BasicDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasicDetailsFragment extends Fragment implements View.OnClickListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int CONTACTS_MANAGER_REQUEST_CODE = 2016;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button saveButton, cancelButton, showHideDetailsButton;
    private EditText nameField, phoneField, emailField, addressField, jobField, companyField, websiteField, imField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_details, container, false);
    }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
        }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        saveButton = (Button) getActivity().findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
        cancelButton = (Button) getActivity().findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);
        showHideDetailsButton = (Button) getActivity().findViewById(R.id.show_hide_details_button);
        showHideDetailsButton.setOnClickListener(this);

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            String phone = intent.getStringExtra("contactsmanager.lab04.pdsd.systems.cs.pub.ro.contactsmanager.PHONE_NUMBER_KEY");
            if (phone != null) {
                EditText et = (EditText) getActivity().findViewById(R.id.telephone_number_field);
                et.setText(phone);
            } else {
                Activity activity = getActivity();
                Toast.makeText(activity, activity.getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
            }
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(final View v) { //check for what button is pressed
        switch (v.getId()) {
            case R.id.show_hide_details_button:
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AdditionalDetailsFragment additionalDetailsFragment = (AdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.bottom_frame);
                if (additionalDetailsFragment == null) {
                    fragmentTransaction.add(R.id.bottom_frame, new AdditionalDetailsFragment());
                    ((Button)v).setText(getActivity().getResources().getString(R.string.hide_details));
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                } else {
                    fragmentTransaction.remove(additionalDetailsFragment);
                    ((Button)v).setText(getActivity().getResources().getString(R.string.show_details));
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
                }
                fragmentTransaction.commit();
                break;
            case R.id.cancel_button:
                getActivity().setResult(Activity.RESULT_CANCELED, new Intent());
                getActivity().finish();
                break;
            case R.id.save_button:
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                nameField = (EditText) getActivity().findViewById(R.id.name_field);
                addressField = (EditText) getActivity().findViewById(R.id.address_field);
                companyField = (EditText) getActivity().findViewById(R.id.company_field);
                websiteField = (EditText) getActivity().findViewById(R.id.website_field);
                emailField = (EditText) getActivity().findViewById(R.id.email_field);
                imField = (EditText) getActivity().findViewById(R.id.im_field);
                jobField = (EditText) getActivity().findViewById(R.id.job_field);
                phoneField = (EditText) getActivity().findViewById(R.id.telephone_number_field);
                if (nameField != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, nameField.getText().toString());
                }
                if (phoneField != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneField.getText().toString());
                }
                if (emailField != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, emailField.getText().toString());
                }
                if (addressField != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, addressField.getText().toString());
                }
                if (jobField != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobField.getText().toString());
                }
                if (companyField != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, companyField.getText().toString());
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (websiteField != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, websiteField.getText().toString());
                    contactData.add(websiteRow);
                }
                if (imField != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, imField.getText().toString());
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                getActivity().startActivity(intent);
                startActivityForResult(intent, CONTACTS_MANAGER_REQUEST_CODE);
                break;
            default:
                break;
        }
    }



    public void setPhoneFieldText(String value){
        phoneField.setText(value);
    }

    public EditText getPhoneField(){
        return phoneField;
    }


}
