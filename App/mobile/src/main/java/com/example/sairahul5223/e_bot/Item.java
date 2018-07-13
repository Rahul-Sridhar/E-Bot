/**
 *
 * Each object of this class is a song on the playlist
 *
 */

package com.example.sairahul5223.e_bot;

public class Item {
    private String title;
    private boolean checked;

    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
