package id.maemaem.footballschedule.util

import android.view.View

//Kita belum menerapkan apapun pada ketiga anggota dari interface MainView tersebut.
// Method showLoading dan hideLoading akan kita gunakan untuk mengatur ProgressBar. Oleh karena itu, kita akan membuat extensions function baru untuk melakukannya.
// Buatlah kelas Utils.Kt dan tambahkan 2 (dua) fungsi berikut:

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}