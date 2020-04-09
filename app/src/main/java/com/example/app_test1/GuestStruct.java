package com.example.app_test1;

import java.util.ArrayList;

public class GuestStruct {
    ArrayList<Guest> guests;
    int curPage;
    int perPage;
    int totalPage;
    int total;

    public GuestStruct(int curPage, int perPage, int total, int totalPage) {
        this.curPage = curPage;
        this.perPage = perPage;
        this.total = total;
        this.totalPage = totalPage;

        guests = new ArrayList<>();
    }

    public Guest getGuests(int i) {
        return guests.get(i);
    }

    public ArrayList<Guest> getGuestList() {
        return guests;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setGuests(Guest guests) {
        this.guests.add(guests);
    }
}
