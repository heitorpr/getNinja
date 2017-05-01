package br.heitor.getninja.views.fragments;

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
import br.heitor.getninja.models.Offer;
import br.heitor.getninja.views.adapter.CustomAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OfferDetailFragment extends BaseFragment {
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

    private Offer offer;
    private String link;

    public OfferDetailFragment() {
    }

    public static OfferDetailFragment newInstance() {
        OfferDetailFragment fragment = new OfferDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offer_detail, container, false);
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
        Offer.fetch(link);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(ItemUpdateEvent event) {
        if (!event.getLink().equals(link)) return;

        offer = (Offer) event.getObject();
        updateUI();
    }

    private void updateUI() {
        if (offer == null) return;

        txtTitle.setText(offer.getTitle());
        txtPersonName.setText(offer.getUser().getName());
        txtPersonLocation.setText(offer.getAddress().getAddress());
        txtExtraInfo.setText(offer.getDistanceTextInKM());
        imgPersonName.setImageResource(R.drawable.ic_person_blue_24dp);
        imgPersonLocation.setImageResource(R.drawable.ic_place_blue_24dp);
        txtPersonPhone.setText(offer.getUser().getPhonesInString());
        txtPersonEmail.setText(offer.getUser().getEmail());

        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setTitle(R.string.offer);

        addInfoView(LayoutInflater.from(ctx), offer.getInfo());
    }

    private void addInfoView(LayoutInflater inflater, List<InfoData> extraInfoArray) {
        linearExtraInfo.removeAllViews();

        for (InfoData info : extraInfoArray) {
            RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.layout_extra_info, linearExtraInfo, false);

            ImageView imgExtraInfo = (ImageView) view.findViewById(R.id.img_extra_info);
            TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
            TextView txtExtraInfo = (TextView) view.findViewById(R.id.txt_extra_info);

            imgExtraInfo.setImageResource(R.drawable.ic_error_blue_24dp);
            txtTitle.setText(info.getLabel());
            txtExtraInfo.setText(info.getValuesInString());

            linearExtraInfo.addView(view);
        }
    }
}
