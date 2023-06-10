package com.example.seccion11_ejercicio.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.seccion11_ejercicio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmailFragment extends Fragment implements View.OnClickListener, DialogInterface.OnClickListener {
  private FloatingActionButton fab;
  private TextView textViewTitle;
  private AlertDialog.Builder builder;
  private EditText editTextMail;

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public EmailFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment EmailFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static EmailFragment newInstance(String param1, String param2) {
    EmailFragment fragment = new EmailFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_email, container, false);

    fab = (FloatingActionButton) view.findViewById(R.id.fab);
    fab.setOnClickListener(this);

    textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);

    return view;
  }

  @Override
  public void onClick(DialogInterface dialogInterface, int which) {
    if (which == DialogInterface.BUTTON_POSITIVE) {

      String email = editTextMail.getText().toString();
      if (!email.isEmpty()) {
        textViewTitle.setText(email);
      }

    } else if (which == DialogInterface.BUTTON_NEGATIVE) {
      dialogInterface.cancel();
    }
  }

  @Override
  public void onClick(View view) {
    builder = new AlertDialog.Builder(getContext());
    builder.setTitle("EMAIL");
    builder.setMessage("Type your email address to be displayed in the middle of the screen");

    // Set up the input
    editTextMail = new EditText(getContext());
    editTextMail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    builder.setView(editTextMail);

    // Set up the buttons
    builder.setPositiveButton("OK", this);
    builder.setNegativeButton("Cancel", this);
    builder.show();
  }
}