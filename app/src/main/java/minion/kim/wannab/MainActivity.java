package minion.kim.wannab;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.*;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class MainActivity extends Activity {

    private AccountHeader headerResult = null;
    private Drawer result = null;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = new SQLiteHandler((getApplicationContext()));
        session = new SessionManager(getApplicationContext());

        if(!session.isLoggedIn()){ logoutUser();}

        PrimaryDrawerItem home = new PrimaryDrawerItem().withName("홈").withIdentifier(0).withIcon(R.drawable.home);
        PrimaryDrawerItem cart = new PrimaryDrawerItem().withName("장바구니").withIdentifier(1).withIcon(R.drawable.cart);
        PrimaryDrawerItem logout = new PrimaryDrawerItem().withName("로그아웃").withIdentifier(2).withIcon(R.drawable.exit);

        final IProfile profile = new ProfileDrawerItem().withName("WannaBe").withEmail("wannabe@inu.ac.kr").withIcon(R.drawable.ic_launcher).withIdentifier(100);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        headerResult = new AccountHeaderBuilder()           // 헤더 부분
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
                .build();

        Drawer drawer = new DrawerBuilder() // drawer 빌드.
                .withActivity(this)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        home,
                        cart,
                        new DividerDrawerItem(),
                        logout,
                        new DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 0) {
                                selectFrag(0);
                                //intent = new Intent(MainActivity.this, WishlistFragment.class);
                            } else if (drawerItem.getIdentifier() == 1) {
                                selectFrag(0);
                                //intent = new Intent(MainActivity.this, WishlistFragment.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                logoutUser();
                            }

                            if (intent != null) {
                                MainActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })

                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    public void selectFrag(int view){
        Fragment fr;
        FragmentManager fm = getFragmentManager();

        if(view == 0){
            fr = new WishlistFragment();
        }else {
            fr = new WishlistFragment();
        }

        fm.beginTransaction()
                .replace(R.id.container, fr)
                .commit();
        //FragmentTransaction fragmentTransaction = fm.beginTransaction();
        //fragmentTransaction.replace(R.id.Main, fr);
        //fragmentTransaction.commit();
    }

    private void logoutUser()
    {
        session.setLogin(false);

        db.deleteUsers();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
