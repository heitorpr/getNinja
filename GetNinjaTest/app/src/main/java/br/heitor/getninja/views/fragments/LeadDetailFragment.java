package br.heitor.getninja.views.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import br.heitor.getninja.Events.ItemUpdateEvent;
import br.heitor.getninja.R;
import br.heitor.getninja.models.InfoData;
import br.heitor.getninja.models.Lead;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LeadDetailFragment extends BaseFragment {
    @BindView(R.id.linear_map)
    LinearLayout linearMap;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.view_separator_header)
    AppCompatImageView viewSeparatorHeader;
    @BindView(R.id.img_person_name)
    AppCompatImageView imgPersonName;
    @BindView(R.id.txt_person_name)
    TextView txtPersonName;
    @BindView(R.id.relative_person)
    RelativeLayout relativePerson;
    @BindView(R.id.img_person_location)
    AppCompatImageView imgPersonLocation;
    @BindView(R.id.txt_person_location)
    TextView txtPersonLocation;
    @BindView(R.id.txt_extra_info)
    TextView txtExtraInfo;
    @BindView(R.id.relative_location)
    RelativeLayout relativeLocation;
    @BindView(R.id.view_separator)
    AppCompatImageView viewSeparator;
    @BindView(R.id.linear_extra_info)
    LinearLayout linearExtraInfo;
    @BindView(R.id.txt_person_phone)
    TextView txtPersonPhone;
    @BindView(R.id.txt_person_email)
    TextView txtPersonEmail;
    @BindView(R.id.linear_contact_info)
    LinearLayout linearContactInfo;
    @BindView(R.id.txt_info_unblock)
    TextView txtInfoUnblock;

    private Lead lead;
    private String link;

    private TextView btnTelephone;
    private TextView btnWhatsApp;

    public LeadDetailFragment() {
    }

    public static LeadDetailFragment newInstance() {
        LeadDetailFragment fragment = new LeadDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lead_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initVariables();
        updateUI();

        return view;
    }

    @Override
    protected void initVariables() {
        super.initVariables();

        Bundle bundle = getArguments();
        link = bundle.getString("link");
        Lead.fetch(link);

        setEvents();
    }

    private void setEvents() {
        btnTelephone = (TextView) getActivity().findViewById(R.id.btn_telephone);
        btnWhatsApp = (TextView) getActivity().findViewById(R.id.btn_whatsapp);

        btnTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + lead.getUser().getPhones().get(0).getNumber()));
                startActivity(intent);
            }
        });
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(ItemUpdateEvent event) {
        if (!event.getLink().equals(link)) return;

        lead = (Lead) event.getObject();
        updateUI();
    }

    private void updateUI() {
        if (lead == null) return;

        txtTitle.setText(lead.getTitle());
        txtPersonName.setText(lead.getUser().getName());
        txtPersonLocation.setText(lead.getAddress().getAddress());
        txtExtraInfo.setText(lead.getDistanceTextInKM());
        txtPersonPhone.setText(lead.getUser().getPhonesInString());
        txtPersonEmail.setText(lead.getUser().getEmail());

        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setTitle(lead.getUser().getName());

        addInfoView(LayoutInflater.from(ctx), lead.getInfo());
    }

    private void addInfoView(LayoutInflater inflater, List<InfoData> extraInfoArray) {
        linearExtraInfo.removeAllViews();

        for (InfoData info : extraInfoArray) {
            RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.layout_extra_info, linearExtraInfo, false);

            ImageView imgExtraInfo = (ImageView) view.findViewById(R.id.img_extra_info);
            TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
            TextView txtExtraInfo = (TextView) view.findViewById(R.id.txt_extra_info);

            imgExtraInfo.setImageResource(R.drawable.ic_error_green_24dp);
            txtTitle.setText(info.getLabel());
            txtExtraInfo.setText(info.getValuesInString());

            linearExtraInfo.addView(view);
        }
    }
}
