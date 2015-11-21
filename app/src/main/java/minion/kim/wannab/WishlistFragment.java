package minion.kim.wannab;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


public class WishlistFragment extends Fragment {

    String url = "http://meeneeon.ddns.net/android_db_api/read_cart.php";
    ArrayList<HashMap<String,String>>Item_List;
    ProgressDialog PD;

    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String WHO = "who";
    public static final String WHERE = "where";
    public static final String IMGLNK = "imglnk";
    public static final String ORDLINK = "ordlnk";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Item_List = new ArrayList<HashMap<String, String>>();

        //TextView txtview = (TextView)getView().findViewById(R.id.textView1);
        PD = new ProgressDialog(getActivity());
        PD.setMessage("읽어들이는 중..");
        PD.setCancelable(false);

        ReadFromDB();

        //txtview.setText(Item_List.get(0).get(NAME));
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }

    private void ReadFromDB()
    {
        PD.show();
        JsonObjectRequest req = new JsonObjectRequest(Method.GET, url, (String)null,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response){
                        try{
                            int success = response.getInt("success");

                            if(success == 1){
                                JSONArray ja = response.getJSONArray("cart");

                                for(int i = 0; i<ja.length(); i++){
                                    JSONObject obj = ja.getJSONObject(i);
                                    HashMap<String,String> item = new HashMap<String,String>();
                                    item.put(NAME, obj.getString(NAME));
                                    item.put(PRICE, obj.getString(PRICE));
                                }

                                PD.dismiss();
                            }
                        } catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError err){
                      PD.dismiss();
                     }
        });

        AppController.getInstance().addToRequestQueue(req);
    }
}