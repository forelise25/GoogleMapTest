package youn0045.kr.hs.emirim.googlemaptest;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    GroundOverlayOptions loc_mark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(final GoogleMap googleMap){ //맵이 다 준비되었을 때 호출되는 메서드
        this.googleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(25.986818, 79.646946),17));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                loc_mark=new GroundOverlayOptions();
                loc_mark.image(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).position(latLng,100f,100f);
                googleMap.addGroundOverlay(loc_mark);

            }
        });
    }
    public static final int ITEM_SATELLITE = 1;
    public static final int ITEM_NOMAL = 2;
    public static final int ITEM_EASTHHRN_SEA = 3;
    public static final int ITEM_CHUNILMIDDLE = 4;
    public static final int ITEM_BUDAFAST = 5;
    public static final int ITEM_MARK_CLEAR = 6;
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "위성 지도");//1은 이벤트 프로그래밍시 메뉴구분
        menu.add(0, 2, 0, "일반 지도");
        SubMenu hotMenu = menu.addSubMenu("자주가는 곳");
        hotMenu.add(0,3,0,"강릉 안목해변");
        hotMenu.add(0,4,0,"천일 중학");
        hotMenu.add(0,5,0,"부다페스트");
        menu.add(0,ITEM_MARK_CLEAR,0,"위치 아이콘 제거");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {//1,2,3 중 하나가 반환
            case ITEM_SATELLITE: googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case ITEM_NOMAL: googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case ITEM_EASTHHRN_SEA: googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.773106, 128.948179), 17));
                return true;
            case ITEM_CHUNILMIDDLE: googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.546031, 127.127477), 100));
                return true;
            case ITEM_BUDAFAST: googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(47.495047, 19.040505),50));
                return true;
            case ITEM_MARK_CLEAR: googleMap.clear();
                return true;
        }
        return false;
    }
}
