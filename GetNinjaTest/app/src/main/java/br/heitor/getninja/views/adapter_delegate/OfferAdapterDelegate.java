package br.heitor.getninja.views.adapter_delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.heitor.getninja.Events.ItemClickEvent;
import br.heitor.getninja.R;
import br.heitor.getninja.collections.Collection;
import br.heitor.getninja.models.Offer;
import br.heitor.getninja.utils.DateUtils;
import br.heitor.getninja.views.adapter.AbstractAdapterDelegate;
import br.heitor.getninja.views.adapter.CustomAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class OfferAdapterDelegate extends AbstractAdapterDelegate<Collection<Offer>> {
    public OfferAdapterDelegate(Context ctx, CustomAdapter.ViewType viewType) {
        super(ctx, viewType);
    }

    @Override
    public boolean isForViewType(@NonNull Collection items, int position) {
        return items.get(position) instanceof Offer;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new OfferViewHolder(inflater.inflate(R.layout.row_lead_or_offer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Collection<Offer> items, int position, @NonNull RecyclerView.ViewHolder viewHolder) {
        Offer offer = items.get(position);

        OfferViewHolder holder = (OfferViewHolder) viewHolder;

        holder.txtTitle.setText(offer.getTitle());
        holder.txtPersonName.setText(offer.getUser().getName());
        holder.txtPersonDate.setText(DateUtils.format(offer.getCreated_at()));
        holder.txtPersonLocation.setText(offer.getAddress().getNeighborhood());

        setEvents(holder);
        setIcons(offer, holder);
    }

    private void setIcons(Offer offer, OfferViewHolder holder) {
        if(offer.isRead()) {
            setIconColors(holder, R.drawable.ic_person_grey_24dp, R.drawable.ic_place_grey_24dp);
            return;
        }

        setIconColors(holder, R.drawable.ic_person_blue_24dp, R.drawable.ic_place_blue_24dp);
    }

    private void setIconColors(OfferViewHolder holder, int resPerson, int resPlace) {
        holder.imgPersonDate.setImageResource(resPerson);
        holder.imgPersonName.setImageResource(resPerson);
        holder.imgPersonLocation.setImageResource(resPlace);
    }

    private void setEvents(final OfferViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ItemClickEvent(holder.getAdapterPosition(), viewType));
            }
        });
    }

    class OfferViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.view_separator)
        AppCompatImageView viewSeparator;
        @BindView(R.id.img_person_name)
        AppCompatImageView imgPersonName;
        @BindView(R.id.txt_person_name)
        TextView txtPersonName;
        @BindView(R.id.linear_person_name)
        LinearLayout linearPersonName;
        @BindView(R.id.img_person_date)
        AppCompatImageView imgPersonDate;
        @BindView(R.id.txt_person_date)
        TextView txtPersonDate;
        @BindView(R.id.linear_person_date)
        LinearLayout linearPersonDate;
        @BindView(R.id.img_person_location)
        AppCompatImageView imgPersonLocation;
        @BindView(R.id.txt_person_location)
        TextView txtPersonLocation;

        OfferViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
