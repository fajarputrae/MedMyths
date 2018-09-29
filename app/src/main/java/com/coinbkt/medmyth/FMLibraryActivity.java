package com.coinbkt.medmyth;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.coinbkt.medmyth.adapter.FMAdapter;
import com.coinbkt.medmyth.db.DaoSession;
import com.coinbkt.medmyth.db.FMLibrary;
import com.coinbkt.medmyth.db.FMLibraryDao;
import com.coinbkt.medmyth.utils.SPMedmyth;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FMLibraryActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.backBtn)
    ImageButton backButton;
    @BindView(R.id.searchBar)
    MaterialSearchBar searchBar;

    FMAdapter adapter;

    MedMythApp medMythApp;
    DaoSession daoSession;
    List<FMLibrary> fmLibraryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmlibrary);
        ButterKnife.bind(this);

        final Boolean isFx = SPMedmyth.getIsFX(this);
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.click);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFx)
                    mp.start();
                FMLibraryActivity.this.finish();
            }
        });

        medMythApp = (MedMythApp) getApplication();
        daoSession = medMythApp.getDaoSession();
        fmLibraryList = daoSession.getFMLibraryDao().queryBuilder().list();

        setUpRecyclerView();
        populate();

        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                populate();
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                librarySearch();
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                if(isFx)
                    mp.start();
            }
        });

    }

    private void setUpRecyclerView() {
        RecyclerView.LayoutManager linLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new FMAdapter(this, fmLibraryList);
        recyclerView.setAdapter(adapter);

    }

    private void librarySearch() {

        fmLibraryList.clear();
        String data = searchBar.getText();

        fmLibraryList.addAll(daoSession.getFMLibraryDao().queryBuilder().where(FMLibraryDao.Properties.Title.like("%"+ data+"%")).list());

        adapter.notifyDataSetChanged();
    }

    private void populate() {
        int[] factImg = new int[]{
                R.drawable.jalan_39,
                R.drawable.susu_35,
                R.drawable.brokoli_36,
                R.drawable.tinta_45,
                R.drawable.jahe_41,
                R.drawable.gosong_44,
                R.drawable.hiv_46,
                R.drawable.soda_32,
                R.drawable.tawa_31,
                R.drawable.stress_40

        };

        int[] factImgThumb = new int[]{
                R.drawable.thumb_jalan_39,
                R.drawable.thumb_susu_35,
                R.drawable.thumb_brokoli_36,
                R.drawable.thumb_tinta_45,
                R.drawable.thumb_jahe_41,
                R.drawable.thumb_gosong_44,
                R.drawable.thumb_hiv_46,
                R.drawable.thumb_soda_32,
                R.drawable.thumb_tawa_31,
                R.drawable.thumb_stress_40

        };

        int[] mythImg = new int[]{
                R.drawable.choco_1,
                R.drawable.mandi_4,
                R.drawable.asma_6,
                R.drawable.pisang_10,
                R.drawable.gula_19,
                R.drawable.olga_23,
                R.drawable.kopi_25,
                R.drawable.pilek_26,
                R.drawable.hiper_28,
                R.drawable.kolesterol_13
        };

        int[] mythImgThumb = new int[]{
                R.drawable.thumb_choco_1,
                R.drawable.thumb_mandi_4,
                R.drawable.thumb_asma_6,
                R.drawable.thumb_pisang_10,
                R.drawable.thumb_gula_19,
                R.drawable.thumb_olga_23,
                R.drawable.thumb_kopi_25,
                R.drawable.thumb_pilek_26,
                R.drawable.thumb_hiper_28,
                R.drawable.thumb_kolesterol_13
        };

        daoSession.getFMLibraryDao().deleteAll();
        fmLibraryList.clear();
        adapter.notifyDataSetChanged();

        FMLibrary fmLibrary10 = new FMLibrary();
        fmLibrary10.setCategory("Myth");
        fmLibrary10.setTitle("Coklat Menyebabkan Jerawat");
        fmLibrary10.setDesc("Sebuah jurnal kesehatan di amerika yaitu Journal of the American Medical Association menyebutkan hasil penelitian tidak menemukan kaitan antara makan coklat dengan jerawat.");
        fmLibrary10.setImg(mythImg[0]);
        fmLibrary10.setThumbImg(mythImgThumb[0]);
        fmLibrary10.setPackName("Food");
        fmLibrary10.setStatus("Left");

        FMLibrary fmLibrary11 = new FMLibrary();
        fmLibrary11.setCategory("Myth");
        fmLibrary11.setTitle("Mandi Malam Bikin Rematik");
        fmLibrary11.setDesc("Seorang penderita penyakit artritis reumatoid atau rematik lainnya memang disarankan untuk tidak mandi di malam hari, karena udara dan angin dingin merupakan faktor predisposisi yang menyebabkan nyeri sendi.\n" + "Tapi jika orang sehat melakukan mandi malam tidak menyebabkan rematik, sampai saat ini tidak ada penelitian yang memuktikannya\n.");
        fmLibrary11.setImg(mythImg[1]);
        fmLibrary11.setThumbImg(mythImgThumb[1]);
        fmLibrary11.setPackName("HIV");
        fmLibrary11.setStatus("Left");

        FMLibrary fmLibrary12 = new FMLibrary();
        fmLibrary12.setCategory("Myth");
        fmLibrary12.setTitle("Anak-Anak Penderita Asma Tidak Seharusnya Berolahraga");
        fmLibrary12.setDesc("Aktivitas fisik seringkali membutuhkan kekuatan bernapas, oleh karena itu para orangtua dan dokter tidak menganjurkan para anak yang menderita asma untuk melakukan olahraga. Namun tidak semua kegiatan olahraga ini dapat memicu serangan asma. Aerobik ternyata bisa menambah kekuatan paru-paru dari para penderita. Beberapa penelitian membuktikan bahwa ketika penderita berolahraga, mereka menjadi jarang terkena serangan asma, tidak terlalu ketergantungan dengan obat dan lebih sering hadir di sekolah. Meskipun ada olahraga yang harus dihindari seperti berlari, ada banyak olahraga lainnya yang tidak memicu EIA (Excercise-Induced Ashtma) seperti berjalan kaki, bersepeda, berenang, baseball, softball, voli, dan juga tenis.");
        fmLibrary12.setImg(mythImg[2]);
        fmLibrary12.setThumbImg(mythImgThumb[2]);
        fmLibrary12.setPackName("HIV");
        fmLibrary12.setStatus("Left");

        FMLibrary fmLibrary13 = new FMLibrary();
        fmLibrary13.setCategory("Myth");
        fmLibrary13.setTitle("Pisang merupakan makanan yang menggemukkan");
        fmLibrary13.setDesc("Pisang adalah makanan yang rendah lemak. Pisang hanya mengandung 1/2 gram lemak dan 95 kalori.");
        fmLibrary13.setImg(mythImg[3]);
        fmLibrary13.setThumbImg(mythImgThumb[3]);
        fmLibrary13.setPackName("Food");
        fmLibrary13.setStatus("Left");

        FMLibrary fmLibrary14 = new FMLibrary();
        fmLibrary14.setCategory("Myth");
        fmLibrary14.setTitle("Ngemil di malam hari bisa bikin gemuk");
        fmLibrary14.setDesc("Ngemil di malam hari bukanlah penyebab utama seseorang mengalami obesitas. Pasalnya, tidak ada bedanya ngemil di malam hari dengan saat siang hari. Salah satu penyebab seseorang bertambah gemuk adalah kurangnya aktivitas fisik.");
        fmLibrary14.setImg(mythImg[4]);
        fmLibrary14.setThumbImg(mythImgThumb[4]);
        fmLibrary14.setPackName("Food");
        fmLibrary14.setStatus("Left");

        FMLibrary fmLibrary15 = new FMLibrary();
        fmLibrary15.setCategory("Myth");
        fmLibrary15.setTitle("Otot berubah menjadi lemak jika Anda tidak berolahraga");
        fmLibrary15.setDesc("Otot dan lemak adalah dua jaringan tubuh yang berbeda dan tidak mungkin mengkonversi satu sama lain. Ketika Anda berhenti berolahraga, otot menyusut dan memungkinkan lemak untuk memasuki tempat otot berada.\n" + "Orang berhenti berolahraga yang cenderung mengonsumsi jumlah kalori sama ketika mereka berolahraga dulu. Namun berat badan naik itu karena sedikit kalori yang dikeluarkan.\n");
        fmLibrary15.setImg(mythImg[5]);
        fmLibrary15.setThumbImg(mythImgThumb[5]);
        fmLibrary15.setPackName("Sport");
        fmLibrary15.setStatus("Left");

        FMLibrary fmLibrary16 = new FMLibrary();
        fmLibrary16.setCategory("Myth");
        fmLibrary16.setTitle("Kopi membuat orang tidak ngantuk");
        fmLibrary16.setDesc("Banyak orang percaya bahwa jika meminum kopi akan membuat kita terjaga, banyak sekali dari kita yang meminum kopi hanya untuk bergadang semalaman untuk mengerjakan perkerjaan atau tugas kuliah. Namun anggapan tersebut ternyata salah, Kafein pada kopi bertugas sebagai stimulan dan menghasilkan efek penenang serta kewaspadaan.\n" + "Hal ini akan membuat denyut jantung dan tekanan darah meningkat, efek ini sering dianggap dapat membuat seseorang terjaga alias tidak mengantuk, namun hasil penelitian di Inggris membantah pernyataan tersebut dan mengungkapkan efek tersebut hanyalah sugesti.\n" + "Ketika tubuh kita sedang lelah dan lemas, biasanya kita menyiasati dengan minum kopi, padahal kalau minum kopi malah membuat tubuh kita semakin lemas dan mengantuk. Minum kopi dipagi hari khususnya akan merangsang hormon kortisol dan adrenalin, jika kamu minum kopi sepanjang hari, kamu akan menguras kedua hormon tersebut dan menjadi kelelahan serta mengantuk.\n");
        fmLibrary16.setImg(mythImg[6]);
        fmLibrary16.setThumbImg(mythImgThumb[6]);
        fmLibrary16.setPackName("Drink");
        fmLibrary16.setStatus("Left");

        FMLibrary fmLibrary17 = new FMLibrary();
        fmLibrary17.setCategory("Myth");
        fmLibrary17.setTitle("Udara dingin adalah penyebab pilek");
        fmLibrary17.setDesc("Saat ini kita percaya bahwa udara dingin apalagi saat musim hujan dapat menyebabkan flu atau pilek. Kenyataannya, flu disebabkan oleh 200 jenis virus yang berbeda, bukan karena udara dingin. Udara yang dingin tidak akan merusak kekebalan tubuh kecuali kita mengidap hiportemia.\n" + "Yang menjadi penyebab kita terkena pilek saat musim dingin atau musim hujan, yaitu pada kondisi ini orang yang terlalu sering berada didalam rumah, sehingga virus cenderung terkurung dalam ruangan tertutup dan menular.\n");
        fmLibrary17.setImg(mythImg[7]);
        fmLibrary17.setThumbImg(mythImgThumb[7]);
        fmLibrary17.setPackName("HIV");
        fmLibrary17.setStatus("Left");

        FMLibrary fmLibrary18 = new FMLibrary();
        fmLibrary18.setCategory("Myth");
        fmLibrary18.setTitle("Kebanyakan gula dapat membuat anak Hiperaktif");
        fmLibrary18.setDesc("Kebanyakan orang tua saat ini membatasi konsumsi gula pada anaknya agar tidak hiperaktif, membatasi makanan yang mengandung gula pada anak memang ada baiknya, namun istilah anak menjadi hiperaktif adalah penalaran yang salah.\n" + "Makanan dengan kalori tinggi memang menawarkan sedikit gizi dan dapat menyebabkan berbagai masalah kesehatan seperti kegemukan, namun sampai saat ini tidak ada bukti ilmiah yang mengatakan gula menyebabkan hiperaktif, gula memang dapat meningkatkan energi jangka pendek, namun tidak dengan hiperaktif.");
        fmLibrary18.setImg(mythImg[8]);
        fmLibrary18.setThumbImg(mythImgThumb[8]);
        fmLibrary18.setPackName("Drink");
        fmLibrary18.setStatus("Left");

        FMLibrary fmLibrary19 = new FMLibrary();
        fmLibrary19.setCategory("Myth");
        fmLibrary19.setTitle("Dengan rajin berolahraga, tidak perlu khawatir kolesterol darah");
        fmLibrary19.setDesc("Selain olahraga, masih banyak faktor lain yang mempengaruhi kadar kolesterol darah seperti makanan, berat badan, kebiasaan merokok dan keturunan. Olahraga membantu mengontrol kadar kolesterol darah dengan cara meningkatkan kesehatan jantung dan pembuluh darah. Namun dengan olahraga saja belum tentu kadar kolesterol darah akan terjaga dengan baik.");
        fmLibrary19.setImg(mythImg[9]);
        fmLibrary19.setThumbImg(mythImgThumb[9]);
        fmLibrary19.setPackName("Sport");
        fmLibrary19.setStatus("Left");

        FMLibrary fmLibrary = new FMLibrary();
        fmLibrary.setCategory("Facts");
        fmLibrary.setTitle("Jalan kaki dapat menghilangkan stress");
        fmLibrary.setDesc("Sedang merasa stress? Perbanyaklah jalan kaki. Sebab jalan kaki mampu meningkatkan hormon norepinefrin dalam otak. Zat ini bermanfaat meredakan stres. Hormon endorfin yang dilepaskan saat Anda berjalan tidak hanya dapat membuat tubuh Anda bahagia namun juga dapat meningkatkan mood secara keseluruhan.");
        fmLibrary.setImg(factImg[0]);
        fmLibrary.setThumbImg(factImgThumb[0]);
        fmLibrary.setPackName("Sport");
        fmLibrary.setStatus("Right");

        FMLibrary fmLibrary1 = new FMLibrary();
        fmLibrary1.setCategory("Facts");
        fmLibrary1.setTitle("Minum susu membuat tidur nyenyak");
        fmLibrary1.setDesc("Kalsium adalah mineral yang membantu Anda untuk tertidur dengan nyenyak. Oleh karena itu makan banyak kalsium seperti bayam, labu, atau minum segelas susu setiap harinya bila ingin tidur nyenyak.");
        fmLibrary1.setImg(factImg[1]);
        fmLibrary1.setThumbImg(factImgThumb[1]);
        fmLibrary1.setPackName("Drink");
        fmLibrary1.setStatus("Right");

        FMLibrary fmLibrary2 = new FMLibrary();
        fmLibrary2.setCategory("Facts");
        fmLibrary2.setTitle("Brokoli dapat menurunkan kolestrol");
        fmLibrary2.setDesc("Jika Anda ingin menurunkan tingkat kolesterol, konsumsilah brokoli. Karena brokoli penuh dengan serat larut yang mampu menurunkan kolesterol.");
        fmLibrary2.setImg(factImg[2]);
        fmLibrary2.setThumbImg(factImgThumb[2]);
        fmLibrary2.setPackName("Food");
        fmLibrary2.setStatus("Right");

        FMLibrary fmLibrary3 = new FMLibrary();
        fmLibrary3.setCategory("Facts");
        fmLibrary3.setTitle("Tinta printer berbahaya bila terkena kulit");
        fmLibrary3.setDesc("Tinta printer mengandung logam berat. Kadang kita ketumpahan tinta printer, dan dengan tenang kita tidak mencuci tangan dengan benar. Tau kan risiko terkena logam berat? Lama kelamaan bisa menyebabkan kerusakan organ bahkan kematian.\n" + "Tertawa membantu pelepasan endorfin sehingga membawa perubahan positif terhadap pola pikir seseorang.\n");
        fmLibrary3.setImg(factImg[3]);
        fmLibrary3.setThumbImg(factImgThumb[3]);
        fmLibrary3.setPackName("HIV");
        fmLibrary3.setStatus("Right");

        FMLibrary fmLibrary4 = new FMLibrary();
        fmLibrary4.setCategory("Facts");
        fmLibrary4.setTitle("Mengunyah jahe dapat mengurasi rasa sakit");
        fmLibrary4.setDesc("Jika kamu mengunyah jahe, maka akan membantu mengurangi rasa sakit hingga 25% karena jahe memiliki sifat anti inflamasi yang kuat.");
        fmLibrary4.setImg(factImg[4]);
        fmLibrary4.setThumbImg(factImgThumb[4]);
        fmLibrary4.setPackName("Drink");
        fmLibrary4.setStatus("Right");

        FMLibrary fmLibrary5 = new FMLibrary();
        fmLibrary5.setCategory("Facts");
        fmLibrary5.setTitle("Makan yang gosong-gosong lebih berisiko kanker");
        fmLibrary5.setDesc("Karena di dalam kegosongan itu terdapat \"benzena\" yang merupakan karsinogen. Jadi makanan yang direbus lebih sehat dibanding yang dibakar bakar. Gitu ya, yang enak ternyata ada efek sampingnya.");
        fmLibrary5.setImg(factImg[5]);
        fmLibrary5.setThumbImg(factImgThumb[5]);
        fmLibrary5.setPackName("Food");
        fmLibrary5.setStatus("Right");

        FMLibrary fmLibrary6 = new FMLibrary();
        fmLibrary6.setCategory("Facts");
        fmLibrary6.setTitle("Minum dari gelas yang sama dengan penderita hiv dapat menularkan penyakit HIV");
        fmLibrary6.setDesc("HIV ditularkan melalui hubungan seks yang tak terlindungi, berbagi penggunaan alat suntikan, atau diberi transfusi dengan darah yang tercemar dapat terinfeksi HIV. Bayi dapat terinfeksi HIV dari ibunya selama masa kehamilan, selama proses persalinan, atau setelah kelahiran melalui pemberian air susu ibu. sehingga minum dari gelas yang sama dengan penderita HIV tidak memiliki resiko penularan, sepanjang tidak terdapat luka pada mulut yang dapat mengeluarkan darah.");
        fmLibrary6.setImg(factImg[6]);
        fmLibrary6.setThumbImg(factImgThumb[6]);
        fmLibrary6.setPackName("HIV");
        fmLibrary6.setStatus("Right");

        FMLibrary fmLibrary7 = new FMLibrary();
        fmLibrary7.setCategory("Facts");
        fmLibrary7.setTitle("Minuman bersoda dapat menyebabkan diabetes");
        fmLibrary7.setDesc("Mengonsumsi satu kaleng minuman bersoda setiap hari akan menambah resiko anda terkena diabetes tipe 2 sebanyak 22%.");
        fmLibrary7.setImg(factImg[7]);
        fmLibrary7.setThumbImg(factImgThumb[7]);
        fmLibrary7.setPackName("Drink");
        fmLibrary7.setStatus("Right");

        FMLibrary fmLibrary8 = new FMLibrary();
        fmLibrary8.setCategory("Facts");
        fmLibrary8.setTitle("Tertawa memiliki manfaat pada kesehatan");
        fmLibrary8.setDesc("Tertawa sebanyak 100 kali setara dengan berolahraga menggunakan sepeda stasioner selama 15 menit.");
        fmLibrary8.setImg(factImg[8]);
        fmLibrary8.setThumbImg(factImgThumb[8]);
        fmLibrary8.setPackName("Sport");
        fmLibrary8.setStatus("Right");

        FMLibrary fmLibrary9 = new FMLibrary();
        fmLibrary9.setCategory("Facts");
        fmLibrary9.setTitle("Stress menimbulkan penyakit");
        fmLibrary9.setDesc("Menurut penelitian terbaru tentang stres dikatakan bahwa stres adalah akar penyebab dari hampir 90% penyakit yang datang, baik fisik maupun mental.");
        fmLibrary9.setImg(factImg[9]);
        fmLibrary9.setThumbImg(factImgThumb[9]);
        fmLibrary9.setPackName("Sport");
        fmLibrary9.setStatus("Right");

        daoSession.getFMLibraryDao().insert(fmLibrary);
        daoSession.getFMLibraryDao().insert(fmLibrary1);
        daoSession.getFMLibraryDao().insert(fmLibrary10);
        daoSession.getFMLibraryDao().insert(fmLibrary2);
        daoSession.getFMLibraryDao().insert(fmLibrary11);
        daoSession.getFMLibraryDao().insert(fmLibrary3);
        daoSession.getFMLibraryDao().insert(fmLibrary12);
        daoSession.getFMLibraryDao().insert(fmLibrary4);
        daoSession.getFMLibraryDao().insert(fmLibrary5);
        daoSession.getFMLibraryDao().insert(fmLibrary13);
        daoSession.getFMLibraryDao().insert(fmLibrary14);
        daoSession.getFMLibraryDao().insert(fmLibrary15);
        daoSession.getFMLibraryDao().insert(fmLibrary6);
        daoSession.getFMLibraryDao().insert(fmLibrary16);
        daoSession.getFMLibraryDao().insert(fmLibrary7);
        daoSession.getFMLibraryDao().insert(fmLibrary17);
        daoSession.getFMLibraryDao().insert(fmLibrary8);
        daoSession.getFMLibraryDao().insert(fmLibrary9);
        daoSession.getFMLibraryDao().insert(fmLibrary18);
        daoSession.getFMLibraryDao().insert(fmLibrary19);

        fmLibraryList.addAll(daoSession.getFMLibraryDao().queryBuilder().list());

    }
}
