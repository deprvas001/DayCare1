package com.development.daycare.model.homeModel;

import java.util.List;

public class HomeData {
        private List<HomeSlider> ADVERTISEMENTLIST;
        private List<MenuList> MENULIST;
        private List<HomeSlider> TOPSTORIESLIST;

    public List<HomeSlider> getADVERTISEMENTLIST() {
        return ADVERTISEMENTLIST;
    }

    public void setADVERTISEMENTLIST(List<HomeSlider> ADVERTISEMENTLIST) {
        this.ADVERTISEMENTLIST = ADVERTISEMENTLIST;
    }

    public List<MenuList> getMenuList() {
        return MENULIST;
    }

    public void setMenuList(List<MenuList> MENULIST) {
        this.MENULIST = MENULIST;
    }

    public List<HomeSlider> getTOPSTORIESLIST() {
        return TOPSTORIESLIST;
    }

    public void setTOPSTORIESLIST(List<HomeSlider> TOPSTORIESLIST) {
        this.TOPSTORIESLIST = TOPSTORIESLIST;
    }
}
