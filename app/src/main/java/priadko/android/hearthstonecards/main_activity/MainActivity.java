package priadko.android.hearthstonecards.main_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.android.priadko.baselibrary.base_activity.ActivityBaseCustomResultMVP;
import com.android.priadko.baselibrary.dialog_errors.ERROR_TYPES;

import priadko.android.hearthstonecards.R;
import priadko.android.hearthstonecards.cards.FragCards;
import priadko.android.hearthstonecards.cards_back.FragCardsBack;
import priadko.android.hearthstonecards.cards_by_smthng.FragBySmth;
import priadko.android.hearthstonecards.constants.BUNDLE;
import priadko.android.hearthstonecards.main_activity.interactor.retrofit_model.ResponseInfo;
import priadko.android.hearthstonecards.main_activity.presenter.PresenterMainImpl;
import priadko.android.hearthstonecards.util.ManagerSharedPref;
import priadko.android.hearthstonecards.util.RealmController;


public class MainActivity
        extends ActivityBaseCustomResultMVP<PresenterMainImpl, ViewMainActivity>
        implements ViewMainActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FrameLayout layoutLoading;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  INIT
    //////////////////////////////////////////////////////////////////////////////////////////////
    @NonNull
    @Override
    protected PresenterMainImpl getPresenter() {
        return new PresenterMainImpl(getString(R.string.base_url));
    }

    @NonNull
    @Override
    protected ViewMainActivity getView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayoutLoading();
        initNavView();
        RealmController.init(this);
        presenter.getInfo(ManagerSharedPref.with(this));
    }

    private void initLayoutLoading() {
        layoutLoading = ((FrameLayout) findViewById(R.id.layout_loading));
    }

    private void initNavView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    protected int getFragContainerId() {
        return R.id.frag_container;
    }

    @Override
    public void onCustomActivityResult(Intent data, int requestCode, int resultCode) {

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragBySmth fragBySmth = new FragBySmth();
            Bundle bundle = new Bundle();
            switch (item.getItemId()) {
                case R.id.nav_cards:
                    replaceFragmentNoStack(new FragCards());
                    break;
                case R.id.nav_cards_back:
                    replaceFragmentNoStack(new FragCardsBack());
                    break;
                case R.id.nav_cards_class:
                    bundle.putSerializable(BUNDLE.SERIALIZABLE.SMTH_ITEM_DATA_TYPE, ManagerSharedPref.INFO.CLASSES);
                    fragBySmth.setArguments(bundle);
                    replaceFragmentNoStack(fragBySmth);
                    break;
                case R.id.nav_cards_faction:
                    bundle.putSerializable(BUNDLE.SERIALIZABLE.SMTH_ITEM_DATA_TYPE, ManagerSharedPref.INFO.FACTIONS);
                    fragBySmth.setArguments(bundle);
                    replaceFragmentNoStack(fragBySmth);
                    break;
                case R.id.nav_cards_quality:
                    bundle.putSerializable(BUNDLE.SERIALIZABLE.SMTH_ITEM_DATA_TYPE, ManagerSharedPref.INFO.QUALITIES);
                    fragBySmth.setArguments(bundle);
                    replaceFragmentNoStack(fragBySmth);
                    break;
                case R.id.nav_cards_race:
                    bundle.putSerializable(BUNDLE.SERIALIZABLE.SMTH_ITEM_DATA_TYPE, ManagerSharedPref.INFO.RACES);
                    fragBySmth.setArguments(bundle);
                    replaceFragmentNoStack(fragBySmth);
                    break;
                case R.id.nav_cards_set:
                    bundle.putSerializable(BUNDLE.SERIALIZABLE.SMTH_ITEM_DATA_TYPE, ManagerSharedPref.INFO.SETS);
                    fragBySmth.setArguments(bundle);
                    replaceFragmentNoStack(fragBySmth);
                    break;
                case R.id.nav_cards_type:
                    bundle.putSerializable(BUNDLE.SERIALIZABLE.SMTH_ITEM_DATA_TYPE, ManagerSharedPref.INFO.TYPES);
                    fragBySmth.setArguments(bundle);
                    replaceFragmentNoStack(fragBySmth);
                    break;

            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    //////////////////////////////////////////////////////////////////////////////////////////////
    //  ACTIONS
    //////////////////////////////////////////////////////////////////////////////////////////////
    private void showLoadingLayout(boolean show) {
        layoutLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoading() {
        showLoadingLayout(true);
    }

    @Override
    public void infoReceived(ResponseInfo info) {
        if (!ManagerSharedPref.with(this).isInfoExist(info.getPatch())) {
            ManagerSharedPref.with(this).saveInfo(info);
        }
    }

    @Override
    public void showFragAllCards() {
        replaceFragmentNoStack(new FragCards());
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void hideLoading() {
        showLoadingLayout(false);
    }

    @Override
    public void showLoadingError() {
        showErrorOk(ERROR_TYPES.WRONG_IN_SERVER);
    }
}
