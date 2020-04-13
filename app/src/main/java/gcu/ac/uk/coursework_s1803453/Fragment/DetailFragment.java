/**
 * @author Tício Victoriano
 * @matric S1803453
 */

package gcu.ac.uk.coursework_s1803453.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import gcu.ac.uk.coursework_s1803453.Model.Item;
import gcu.ac.uk.coursework_s1803453.R;

public class DetailFragment extends Fragment implements OnMapReadyCallback {

    private TextView title;
    private TextView description;
    private TextView depth;
    private TextView location;
    private TextView coordinates;
    private TextView magnetude;
    private TextView date;
    private Item item;
    private SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        init(view);

        if(savedInstanceState != null){
            item = (Item) savedInstanceState.getSerializable("item");
        }else{
            item = (Item) getArguments().getSerializable("item");
        }

        if(item.getTitle() == null){
            title.setText(item.getLocation());
        }else{
            title.setText(item.getTitle());
        }

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        description.setText(item.getDescription());
        depth.setText(item.getDepth());
        date.setText(item.getDate());
        location.setText(item.getLocation());
        magnetude.setText(item.getMagnitude());
        coordinates.setText(item.getLat()+", "+item.getLon());

        return view;
    }



    public void init(View view){
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        depth = view.findViewById(R.id.depth);
        location = view.findViewById(R.id.location);
        coordinates = view.findViewById(R.id.coordinates);
        magnetude = view.findViewById(R.id.magnitude);
        date = view.findViewById(R.id.date);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(Double.parseDouble(item.getLat()), Double.parseDouble(item.getLon()));
        googleMap.addMarker(new MarkerOptions()
                 .position(latLng)
                 .anchor(0.5f, 0.5f).title(item.getLocation()));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("item", item);
    }
}
