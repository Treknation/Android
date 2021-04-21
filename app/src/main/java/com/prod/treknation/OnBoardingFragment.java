package com.prod.treknation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import static com.prod.treknation.MainActivity.SHARED_PREFS;

public class OnBoardingFragment extends Fragment {

    public OnBoardingFragment(int pageCount) {
        this.pageCount = pageCount;
    }

    public static final String IS_FROM_CANADA = "isFromCanada";
    public static final String IS_INTRO_DONE = "isIntroDone";
    public static final String USER_ID = "user id";

    public static final String IS_FIRST_TIME = "IS_FIRST_TIME";
    private static final String PAGE_COUNT = "pageCount";
    public static final String USERNAME = "username";
    public static final String USERSTAGE = "userstage";
    public static final String EMAIL_ID="EMAIL_ID";
    private int pageCount = 0;
    private static final int DELAY = 200;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        assert getArguments() != null;
//        pageCount = getArguments().getInt(PAGE_COUNT);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (pageCount == 0) {
            return inflater.inflate(R.layout.layout_walkthrough1, null, false);
        } else if (pageCount == 1) {
            return inflater.inflate(R.layout.layout_walkthrough2, null, false);
        } else if (pageCount == 2) {
            return inflater.inflate(R.layout.layout_walkthrough3, null, false);
        } else if (pageCount == 3) {
            return inflater.inflate(R.layout.layout_walkthrough4, null, false);
        } else if (pageCount == 4) {
            return inflater.inflate(R.layout.layout_walkthrough5, null, false);
        } else if (pageCount == 5) {
            View view = inflater.inflate(R.layout.layout_walkthrough6, null, false);

            ImageView imageViewTarget = view.findViewById(R.id.ivJourney);
            Glide.with(this).asGif().load(R.raw.trek_nation).into(imageViewTarget);
            return view;
        }
        return inflater.inflate(R.layout.layout_walkthrough1, null, false);
    }

    private void showWelcomeScreen() {
        // data upload on

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("UsersList")
                .add(((OnBoardingActivity) getActivity()).mOnBoarding)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(OnBoardingFragment.class.getName(), "DocumentSnapshot added with ID: " + documentReference.getId());
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                        final SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(IS_INTRO_DONE, true);
                        editor.putBoolean(IS_FIRST_TIME, true);
                        editor.putString(USER_ID, documentReference.getId());
                        editor.apply();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(OnBoardingFragment.class.getName(), "Error adding document", e);
                    }
                });
    }

    @Override
    public void onViewCreated(final @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (pageCount == 0) {
            view.findViewById(R.id.btnGetStarted).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventTracker.Companion.logEvent(EventTracker.Companion.getGet_started());
                    onBoardingListener.onClick(pageCount);
                }
            });
        } else if (pageCount == 1) {
            view.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put(EventTracker.Companion.getValue(), "Yes");
                    EventTracker.Companion.logEvent(EventTracker.Companion.getStage_pr_process_select(), HashMap);

                    view.findViewById(R.id.llOptionButton).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.clYesNo).setVisibility(View.GONE);
                    TextView tvPermnanetAdd = view.findViewById(R.id.tvPermanentAddress);
                    tvPermnanetAdd.setText(getString(R.string.stage));
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setCanaResidenceProcess(true);
                }
            });

            view.findViewById(R.id.btnCreatedProfile).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    AppCompatButton btnProfile = view.findViewById(R.id.btnCreatedProfile);
                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put(EventTracker.Companion.getValue(), btnProfile.getText().toString());
                    EventTracker.Companion.logEvent(EventTracker.Companion.getStage_pr_process_value(), HashMap);

                    setUserStage(btnProfile.getText().toString());
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setUserStage(btnProfile.getText().toString());
                    onBoardingListener.onClick(pageCount);
                }
            });
            view.findViewById(R.id.btnReceivedITA).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    AppCompatButton btnProfile = view.findViewById(R.id.btnReceivedITA);
                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put(EventTracker.Companion.getValue(), btnProfile.getText().toString());
                    EventTracker.Companion.logEvent(EventTracker.Companion.getStage_pr_process_value(), HashMap);

                    setUserStage(btnProfile.getText().toString());
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setUserStage(btnProfile.getText().toString());
                    onBoardingListener.onClick(pageCount);
                }
            });
            view.findViewById(R.id.btnSubmittedDocs).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    AppCompatButton btnProfile = view.findViewById(R.id.btnSubmittedDocs);
                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put(EventTracker.Companion.getValue(), btnProfile.getText().toString());
                    EventTracker.Companion.logEvent(EventTracker.Companion.getStage_pr_process_value(), HashMap);

                    setUserStage(btnProfile.getText().toString());
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setUserStage(btnProfile.getText().toString());
                    onBoardingListener.onClick(pageCount);
                }
            });
            view.findViewById(R.id.btnLandingProcedures).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    AppCompatButton btnProfile = view.findViewById(R.id.btnLandingProcedures);
                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put(EventTracker.Companion.getValue(), btnProfile.getText().toString());
                    EventTracker.Companion.logEvent(EventTracker.Companion.getStage_pr_process_value(), HashMap);

                    setUserStage(btnProfile.getText().toString());
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setUserStage(btnProfile.getText().toString());
                    onBoardingListener.onClick(pageCount);
                }
            });

            view.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put(EventTracker.Companion.getValue(), "no");
                    EventTracker.Companion.logEvent(EventTracker.Companion.getStage_pr_process_value(), HashMap);

                    ((OnBoardingActivity) getActivity()).mOnBoarding.setCanaResidenceProcess(false);
                    onBoardingListener.onClick(pageCount);
                }
            });
        } else if (pageCount == 2) {
            view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    TextInputEditText etName = view.findViewById(R.id.editYourName);
                    TextInputEditText etEmail = view.findViewById(R.id.editYourEmail);
                    TextInputLayout tlYourName = view.findViewById(R.id.tlYourName);
                    TextInputLayout tlYourEmail = view.findViewById(R.id.tlYourEmail);

                    if (etName.getText().toString().isEmpty()) {
                        tlYourName.setError(getString(R.string.invalid_name));
                        return;
                    } else {
                        tlYourName.setError(null);
                    }
                    if (etEmail.getText().toString().isEmpty()) {
                        tlYourEmail.setError(getString(R.string.invalid_address));
                        return;
                    } else if (!etEmail.getText().toString().matches(getString(R.string.email_regex_1))) {
                        tlYourEmail.setError(getString(R.string.invalid_address));
                        return;
                    } else {
                        tlYourEmail.setError(null);
                    }

                    ((OnBoardingActivity) getActivity()).mOnBoarding.setUserName(etName.getText().toString());
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setUserEmail(etEmail.getText().toString());

                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(USERNAME, etName.getText().toString());
                    editor.putString(EMAIL_ID, etEmail.getText().toString());
                    editor.apply();

                    EventTracker.Companion.setUserProperties(etEmail.getText().toString(),etName.getText().toString());
                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put("email",etEmail.getText().toString());
                    HashMap.put("name",etName.getText().toString());
                    EventTracker.Companion.logEvent(EventTracker.Companion.getUser_details(), HashMap);
                    onBoardingListener.onClick(pageCount);
                }
            });
        } else if (pageCount == 3) {
            view.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setApplyFromCanada(true);

                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put(EventTracker.Companion.getValue(), "inside");
                    EventTracker.Companion.logEvent(EventTracker.Companion.getLocation_details(), HashMap);
                    onBoardingListener.onClick(pageCount);
                }
            });
            view.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setApplyFromCanada(false);
                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put(EventTracker.Companion.getValue(), "outside");
                    EventTracker.Companion.logEvent(EventTracker.Companion.getLocation_details(), HashMap);
                    onBoardingListener.onClick(pageCount);
                }
            });
        } else if (pageCount == 4) {
            final AppCompatTextView btnFamily = view.findViewById(R.id.btnFamily);
            final AppCompatTextView btnIndi = view.findViewById(R.id.btnIndividual);
            final CheckBox radioButton = view.findViewById(R.id.cbTermsCondition);
            final TextView textView = view.findViewById(R.id.tvTerms);

            final boolean[] isIndiCLicked = {false};
            final boolean[] isFamilyCLicked = {false};

            SharedPreferences pref = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

            view.findViewById(R.id.btnIndividual).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    btnIndi.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.oval_button_blue));
                    btnFamily.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.oval_transparent_button));
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setApplyIndiOrFamily(btnIndi.getText().toString());
                    pref.edit().putString("isSingle",btnIndi.getText().toString()).apply();
                    isIndiCLicked[0] = true;
                    isFamilyCLicked[0] = false;
                }
            });
            view.findViewById(R.id.btnFamily).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    btnIndi.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.oval_transparent_button));
                    btnFamily.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.oval_button_blue));
                    ((OnBoardingActivity) getActivity()).mOnBoarding.setApplyIndiOrFamily(btnFamily.getText().toString());
                    pref.edit().putString("isSingle",btnFamily.getText().toString()).apply();
                    isIndiCLicked[0] = false;
                    isFamilyCLicked[0] = true;
                }
            });

            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b && (isIndiCLicked[0] || isFamilyCLicked[0])) {
                        view.findViewById(R.id.btnStartMyJourney).setVisibility(View.VISIBLE);
                    } else {
                        view.findViewById(R.id.btnStartMyJourney).setVisibility(View.INVISIBLE);
                    }
                }
            });

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isInternetAvailable()) {
                        Toast.makeText(requireContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Intent intent5 = new Intent(requireContext().getApplicationContext(), WebsiteActivity.class);
                    intent5.putExtra("Url 1", "https://treknation.ca/terms-and-conditions/");
                    startActivity(intent5);
                }
            });

            view.findViewById(R.id.btnStartMyJourney).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    if (!isFamilyCLicked[0] && !isIndiCLicked[0]) {
                        Toast.makeText(requireContext(), "Please select individual or family", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!radioButton.isChecked()) {
                        Toast.makeText(requireContext(), "Please accept terms and conditions", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!isInternetAvailable()) {
                        Toast.makeText(requireContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();
                        return;
                    }

                    HashMap<String, String> HashMap = new HashMap<String, String>();
                    HashMap.put(EventTracker.Companion.getValue(), ((OnBoardingActivity) getActivity()).mOnBoarding.getApplyIndiOrFamily());
                    EventTracker.Companion.logEvent(EventTracker.Companion.getNo_of_applications(), HashMap);
                    onBoardingListener.onClick(pageCount);
                }
            });
        } else if (pageCount == 5) {
            TextView tvWelcomeTitle = view.findViewById(R.id.tvWelcomeTitle);

            SharedPreferences preferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String name = preferences.getString(USERNAME, "");
            tvWelcomeTitle.setText("Hold tight, " + name + " We are personalizing your journey.");

/*
            HandlerCompat.postDelayed(new Handler(), new Runnable() {
                @Override
                public void run() {
                    getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                    getActivity().finish();
                }
            },"0",DELAY);
*/
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_FROM_CANADA, ((OnBoardingActivity) getActivity()).mOnBoarding.isApplyFromCanada());
            editor.apply();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("UsersList")
                    .add(((OnBoardingActivity) getActivity()).mOnBoarding)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(OnBoardingFragment.class.getName(), "DocumentSnapshot added with ID: " + documentReference.getId());
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                            final SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(IS_INTRO_DONE, true);
                            editor.putBoolean(IS_FIRST_TIME, true);
                            editor.putString(USER_ID, documentReference.getId());
                            editor.apply();

                            getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
                            getActivity().getSupportFragmentManager().popBackStackImmediate();
                            getActivity().finish();


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(OnBoardingFragment.class.getName(), "Error adding document", e);
                        }
                    });

        }
    }

    private void setUserStage(String userStage) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERSTAGE, userStage);
        editor.apply();
    }

    private boolean isInternetAvailable() {
        ConnectivityManager mgr = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                // Internet Available
                return true;
            } else {
                //No internet
                return false;
            }
        } else {
            //No internet
            return false;
        }
    }

    public interface OnBoardingListener {
        void onClick(int pageCount);
    }

    public OnBoardingFragment attachOnBoardListener(OnBoardingListener onBoardingListener) {
        this.onBoardingListener = onBoardingListener;
        return this;
    }

    private OnBoardingListener onBoardingListener;
}
