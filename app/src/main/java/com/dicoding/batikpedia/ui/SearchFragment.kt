package com.dicoding.batikpedia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.batikpedia.R
import java.util.*

class SearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<BatikData>()
    private lateinit var adapter: BatikAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.searchView)

        val fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val scaleUpAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_up)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        addDataToList()
        adapter = BatikAdapter(mList)
        recyclerView.adapter = adapter

        recyclerView.startAnimation(fadeInAnimation)
        searchView.startAnimation(scaleUpAnimation)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        return view
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<BatikData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(context, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(
            BatikData(
                "Batik Parang",
                R.drawable.parang,
                "Batik Parang berasal dari Jawa, khususnya dari Kesultanan Mataram yang kini wilayahnya meliputi Yogyakarta dan Surakarta (Solo). Batik ini memiliki sejarah yang panjang dan kaya, sering dikaitkan dengan kerajaan dan keluarga bangsawan.\n" +
                        "\n" +
                        "Batik Parang memiliki motif yang sangat khas dan mudah dikenali, yaitu pola diagonal yang menyerupai bilah parang atau ombak. Garis-garis miring ini biasanya mengalir secara berkesinambungan dari atas ke bawah dengan pola yang teratur dan berulang. Motif ini melambangkan kekuatan, perjuangan, dan semangat yang tidak pernah padam.\n" +
                        "\n" +
                        "Batik Parang sering dianggap sebagai simbol kekuatan, keberanian, dan kebijaksanaan. Pada zaman dahulu, batik ini sering dipakai oleh para ksatria dan bangsawan, terutama saat upacara-upacara penting."
            )
        )
        mList.add(
            BatikData(
                "Batik Mega Mendung",
                R.drawable.megamendung,
                "Batik Mega Mendung berasal dari Cirebon, Jawa Barat. Batik ini memiliki akar budaya yang kuat di daerah pesisir dan dipengaruhi oleh kebudayaan Tionghoa, mengingat Cirebon adalah kota pelabuhan yang berhubungan erat dengan pedagang Tionghoa pada masa lalu.\n" +
                        "\n" +
                        "Batik Mega Mendung terkenal dengan motif awan berlapis yang menyerupai langit mendung. Pola ini terdiri dari garis-garis melengkung yang mengalir secara harmonis, menciptakan efek awan yang bertumpuk-tumpuk. Warna yang sering digunakan dalam Batik Mega Mendung biasanya adalah warna-warna cerah dan kontras seperti biru, merah, dan hijau, yang melambangkan kesejukan, ketenangan, dan kesejahteraan.\n" +
                        "\n" +
                        "Batik Mega Mendung sering dianggap sebagai simbol ketenangan, kesabaran, dan keteguhan hati. Motif awan yang melambangkan keteduhan dan kedamaian ini sering digunakan dalam berbagai acara, baik formal maupun informal, dan menjadi identitas kuat bagi masyarakat Cirebon."
            )
        )
        mList.add(
            BatikData(
                "Batik Kawung",
                R.drawable.kawung,
                "Batik Kawung berasal dari daerah Jawa, terutama dari Yogyakarta dan Surakarta (Solo). Batik ini merupakan salah satu motif batik tertua di Indonesia dan sering dikaitkan dengan kebudayaan keraton.\n" +
                        "\n" +
                        "Batik Kawung memiliki motif yang sangat khas, yaitu pola geometris yang menyerupai buah kawung (buah kolang-kaling atau buah aren) yang diatur dalam bentuk simetris. Pola ini terdiri dari lingkaran-lingkaran yang saling bertumpuk atau bersebelahan dengan titik-titik kecil di tengahnya. Warna yang sering digunakan dalam Batik Kawung adalah cokelat, hitam, dan putih, meskipun kini banyak juga yang menggunakan warna-warna modern.\n" +
                        "\n" +
                        "Batik Kawung sering dianggap sebagai simbol kesucian, keadilan, dan keabadian. Pada zaman dahulu, batik ini sering dipakai oleh keluarga kerajaan dan pejabat tinggi dalam acara-acara resmi. Motif kawung yang teratur dan simetris mencerminkan harmoni dan keseimbangan dalam kehidupan."
            )
        )
        mList.add(
            BatikData(
                "Batik Tujuh Rupa",
                R.drawable.tujuhrupa,
                "Batik Tujuh Rupa berasal dari Pekalongan, Jawa Tengah. Pekalongan dikenal sebagai salah satu pusat batik di Indonesia yang kaya akan variasi motif dan warna. Batik Tujuh Rupa adalah salah satu motif yang paling terkenal dari daerah ini.\n" +
                        "\n" +
                        "Batik Tujuh Rupa memiliki ciri khas dengan motif yang sangat beragam dan kompleks, mencakup berbagai unsur alam seperti tumbuhan, bunga, burung, dan hewan lainnya. Motif ini sering kali dipadukan dalam satu kain sehingga menciptakan tampilan yang kaya dan berwarna-warni. Batik ini sering menggunakan warna-warna cerah dan kontras, seperti merah, biru, hijau, dan kuning.\n" +
                        "\n" +
                        "Batik Tujuh Rupa sering dianggap sebagai simbol keanekaragaman dan kekayaan alam. Setiap motif yang terdapat pada Batik Tujuh Rupa memiliki makna tersendiri, seperti kemakmuran, kesejahteraan, dan harmoni dengan alam. Batik ini sering digunakan dalam berbagai acara, baik formal maupun informal, dan menjadi identitas kuat bagi masyarakat Pekalongan."
            )
        )
        mList.add(
            BatikData(
                "Batik Sidoluhur",
                R.drawable.sidoluhur,
                "Batik Sidoluhur berasal dari Surakarta (Solo), Jawa Tengah. Batik ini merupakan salah satu motif tradisional yang sering digunakan dalam upacara adat dan pernikahan, terutama di lingkungan keraton.\n" +
                        "\n" +
                        "Batik Sidoluhur memiliki motif yang khas dengan pola-pola geometris dan simbolis yang menggambarkan harapan dan doa. Kata \"Sidoluhur\" sendiri berasal dari bahasa Jawa, yang berarti \"menjadi mulia\" atau \"menjadi terhormat.\" Motif ini sering kali berupa pola-pola simetris dengan kombinasi elemen-elemen seperti bunga, daun, dan berbagai simbol tradisional lainnya.\n" +
                        "\n" +
                        "Batik Sidoluhur sering dianggap sebagai simbol kebijaksanaan, kemuliaan, dan kehormatan. Pada zaman dahulu, batik ini sering digunakan oleh calon pengantin wanita pada saat malam midodareni, sebuah ritual dalam tradisi Jawa sebelum pernikahan. Penggunaan Batik Sidoluhur mengandung harapan agar pengantin wanita kelak menjadi pribadi yang mulia dan terhormat dalam kehidupan rumah tangganya."
            )
        )
        mList.add(
            BatikData(
                "Batik Bali",
                R.drawable.bali,
                "Batik Bali berasal dari pulau Bali, sebuah daerah yang terkenal dengan budaya dan seni yang kaya. Meskipun batik bukan merupakan tradisi asli Bali, seni pembuatan batik diadaptasi dan dikembangkan dengan sentuhan lokal yang khas sejak beberapa dekade terakhir.\n" +
                        "\n" +
                        "Batik Bali memiliki motif yang sangat beragam dan dipengaruhi oleh keindahan alam serta budaya Bali yang kaya. Pola-pola batik Bali sering kali mencerminkan unsur-unsur alam seperti flora dan fauna, serta simbol-simbol budaya Hindu yang kuat di Bali. Motif yang digunakan biasanya lebih bebas dan kreatif dibandingkan batik dari Jawa.\n" +
                        "\n" +
                       "Batik Bali sering dianggap sebagai simbol keindahan, kebebasan, dan kreativitas. Motif-motif yang digunakan sering kali mencerminkan filosofi hidup masyarakat Bali yang harmonis dengan alam dan spiritualitas. Batik Bali banyak digunakan dalam berbagai kesempatan, baik formal maupun informal, dan sering menjadi pilihan oleh wisatawan sebagai oleh-oleh khas dari Bali."
            )
        )
        mList.add(
            BatikData(
                "Batik Lasem",
                R.drawable.lasem,
                "Batik Lasem berasal dari daerah Lasem, yang terletak di Kabupaten Rembang, Jawa Tengah. Lasem dikenal sebagai kota batik pesisir yang memiliki sejarah panjang dan merupakan salah satu pusat batik tertua di Indonesia. Batik Lasem sangat dipengaruhi oleh budaya Tionghoa, karena banyaknya komunitas Tionghoa yang bermukim di Lasem sejak abad ke-15.\n" +
                        "\n" +
                        "Batik Lasem memiliki ciri khas dengan motif yang sangat halus dan detail, serta penggunaan warna-warna yang mencolok. Batik ini sering kali menggambarkan perpaduan antara budaya Jawa dan Tionghoa. Motif-motif yang digunakan dalam Batik Lasem mencakup elemen-elemen alam, simbol-simbol budaya, dan cerita-cerita mitologi.\n" +
                        "\n" +
                       "Batik Lasem sering dianggap sebagai simbol keindahan, kekayaan budaya, dan perpaduan antara dua budaya besar, yaitu Jawa dan Tionghoa. Motif-motif dalam Batik Lasem mencerminkan filosofi hidup yang harmonis dan seimbang. Batik ini banyak digunakan dalam berbagai acara, baik formal maupun informal, dan menjadi salah satu warisan budaya yang berharga dari Lasem."
            )
        )
        mList.add(
            BatikData(
                "Batik Betawi",
                R.drawable.betawi,
                "Batik Betawi berasal dari Jakarta, ibu kota Indonesia. Batik ini mencerminkan kebudayaan masyarakat Betawi, kelompok etnis asli Jakarta. Batik Betawi berkembang seiring dengan pertumbuhan kota dan pengaruh budaya dari berbagai komunitas yang ada di Jakarta, termasuk pengaruh dari budaya Arab, Tionghoa, dan Belanda.\n" +
                        "\n" +
                        "Batik Betawi memiliki motif yang khas dan penuh warna, menggambarkan berbagai aspek kehidupan masyarakat Betawi. Pola-pola batik Betawi sering kali mencerminkan keindahan alam, flora dan fauna lokal, serta elemen-elemen budaya khas Betawi seperti ondel-ondel dan tanjidor.\n" +
                        "\n" +
                        "Batik Betawi sering dianggap sebagai simbol identitas dan kebanggaan masyarakat Betawi. Motif-motif yang digunakan dalam batik Betawi mencerminkan keanekaragaman budaya dan kehidupan sosial yang harmonis di Jakarta. Batik ini banyak digunakan dalam berbagai acara, baik formal maupun informal, dan menjadi salah satu warisan budaya yang berharga dari masyarakat Betawi."
            )
        )
        mList.add(
            BatikData(
                "Batik Truntum",
                R.drawable.truntum,
                "Batik Truntum berasal dari Surakarta (Solo), Jawa Tengah. Motif ini diciptakan oleh Kanjeng Ratu Kencana, permaisuri dari Paku Buwana III, seorang raja dari Keraton Surakarta, pada abad ke-18. Batik Truntum memiliki makna dan sejarah yang mendalam, khususnya dalam konteks pernikahan dan kehidupan berumah tangga.\n" +
                        "\n" +
                        "Batik Truntum memiliki motif yang khas berupa pola bunga kecil yang tersebar merata di seluruh kain. Kata \"truntum\" berasal dari bahasa Jawa yang berarti \"bersemi kembali\" atau \"tumbuh kembali,\" yang melambangkan cinta yang terus berkembang dan bersemi kembali.\n" +
                        "\n" +
                        "Batik Truntum sering digunakan dalam upacara pernikahan Jawa, khususnya oleh orang tua pengantin sebagai simbol kasih sayang dan restu untuk kedua mempelai. Motif ini mengandung harapan agar cinta dalam pernikahan selalu tumbuh dan berkembang, serta hubungan yang harmonis dan langgeng. Batik Truntum menjadi salah satu motif yang sangat dihargai dalam budaya Jawa karena makna filosofisnya yang dalam dan keindahan pola-pola halusnya."
            )
        )
        mList.add(
            BatikData(
                "Batik Buketan",
                R.drawable.buketan,
                "Batik Buketan berasal dari Pekalongan, Jawa Tengah. Pekalongan adalah salah satu pusat batik terkenal di Indonesia yang kaya akan variasi motif dan pengaruh budaya, terutama dari Belanda pada masa kolonial. Nama \"Buketan\" diambil dari kata \"bouquet\" dalam bahasa Belanda, yang berarti rangkaian bunga.\n" +
                        "\n" +
                        "Batik Buketan memiliki motif yang khas berupa rangkaian bunga atau buket bunga yang diatur dengan indah di seluruh kain. Motif ini mencerminkan keindahan alam dan sering kali menggambarkan bunga-bunga yang disusun dengan elegan.\n" +
                        "\n" +
                        "Batik Buketan sering dianggap sebagai simbol keindahan dan elegansi. Motif-motifnya yang detail dan berwarna-warni membuatnya sangat cocok untuk berbagai acara, baik formal maupun informal. Batik Buketan sangat diminati oleh mereka yang menyukai keindahan alam dan seni.")
        )
        mList.add(
            BatikData(
                "Batik Sogan",
                R.drawable.sogan,
                "Batik Sogan berasal dari daerah Yogyakarta dan Surakarta (Solo), Jawa Tengah. Batik ini adalah salah satu jenis batik klasik yang sering dikaitkan dengan keraton atau istana, karena motif dan warnanya yang khas. Nama \"Sogan\" berasal dari penggunaan pewarna alami yang disebut \"soga,\" yaitu sejenis kayu yang memberikan warna cokelat khas pada kain batik.\n" +
                        "\n" +
                        "Batik Sogan memiliki motif yang elegan dan klasik, dengan dominasi warna cokelat. Pola-pola yang digunakan sering kali mencerminkan kehidupan istana dan nilai-nilai budaya Jawa.\n" +
                        "\n" +
                        "Batik Sogan sering dianggap sebagai simbol kebijaksanaan, kekuatan, dan kemuliaan. Pada zaman dahulu, batik ini sering digunakan oleh keluarga kerajaan dan pejabat tinggi dalam berbagai upacara resmi. Motif-motif dalam Batik Sogan mencerminkan nilai-nilai tradisional Jawa yang kuat dan mendalam, serta keindahan dan keanggunan yang abadi. Batik Sogan tetap populer hingga kini dan banyak digunakan dalam berbagai acara formal, termasuk upacara adat dan pernikahan."
            )
        )
        mList.add(
            BatikData(
                "Batik Gentongan",
                R.drawable.gentongan,
                "Batik Gentongan berasal dari Madura, sebuah pulau di sebelah timur laut Jawa. Nama \"Gentongan\" diambil dari kata \"gentong,\" yang berarti \"wadah besar\" dalam bahasa Madura, merujuk pada proses pewarnaan batik yang menggunakan gentong untuk merendam kain dengan pewarna alami.\n" +
                        "\n" +
                        "Batik Gentongan memiliki motif yang khas dan unik dengan penggunaan warna-warna yang sangat cerah dan kontras. Motif-motif dalam batik ini biasanya terinspirasi dari alam, flora, fauna, dan elemen-elemen budaya lokal.\n" +
                        "\n" +
                        "Batik Gentongan sering dianggap sebagai simbol kreativitas dan kekayaan budaya Madura. Proses pembuatannya yang rumit dan membutuhkan waktu yang lama membuat batik ini sangat berharga. Motif-motif yang digunakan mencerminkan kehidupan sehari-hari dan keindahan alam Madura. Batik Gentongan banyak digunakan dalam berbagai acara, baik formal maupun informal, dan menjadi salah satu warisan budaya yang berharga dari masyarakat Madura."
            )
        )
        mList.add(
            BatikData(
                "Batik Kraton",
                R.drawable.kraton,
                "Batik Kraton berasal dari Yogyakarta dan Surakarta (Solo), Jawa Tengah. Batik ini dibuat dan dikembangkan di lingkungan keraton (istana) Jawa, dan sering kali digunakan oleh keluarga kerajaan dan para bangsawan. Batik Kraton memiliki sejarah yang panjang dan erat kaitannya dengan tradisi dan budaya Jawa.\n" +
                        "\n" +
                        "Batik Kraton memiliki motif yang sangat khas dan penuh makna filosofis. Motif-motif ini sering kali menggambarkan nilai-nilai luhur, kekuatan, dan keindahan. Batik Kraton umumnya menggunakan warna-warna klasik seperti cokelat, biru tua, hitam, dan putih.\n" +
                        "\n" +
                        "Batik Kraton sering dianggap sebagai simbol kebijaksanaan, kekuatan, dan keanggunan. Motif-motif dalam Batik Kraton tidak hanya memiliki nilai estetika tinggi, tetapi juga mengandung makna filosofis yang mendalam. Pada zaman dahulu, penggunaan Batik Kraton diatur secara ketat oleh aturan-aturan keraton, dan hanya boleh dipakai oleh anggota keluarga kerajaan atau dalam upacara-upacara khusus. Hingga kini, Batik Kraton tetap dihargai sebagai salah satu bentuk seni batik tertinggi dan sering digunakan dalam berbagai upacara adat dan formal."
            )
        )
        mList.add(
            BatikData(
                "Batik Pring Sedapur",
                R.drawable.pringsedapur,
                "Batik Pring Sedapur berasal dari Magetan, sebuah daerah di Jawa Timur yang terkenal dengan keindahan alamnya. Nama \"Pring Sedapur\" secara harfiah berarti \"bambu sekeluarga\" dalam bahasa Jawa, mencerminkan motif utama yang digunakan dalam batik ini.\n" +
                        "\n" +
                        "Batik Pring Sedapur memiliki motif yang khas berupa gambar rumpun bambu. Motif bambu ini melambangkan kesederhanaan, ketahanan, dan kekuatan, serta filosofi hidup yang harmonis dengan alam.\n" +
                        "\n" +
                        "Batik Pring Sedapur sering dianggap sebagai simbol kesederhanaan, ketahanan, dan keharmonisan dengan alam. Motif bambu dalam batik ini mencerminkan filosofi hidup yang kuat namun fleksibel, mampu bertahan dalam berbagai kondisi. Batik Pring Sedapur banyak digunakan dalam berbagai acara, baik formal maupun informal, dan menjadi salah satu warisan budaya yang berharga dari Magetan. Selain keindahannya, batik ini juga membawa pesan tentang pentingnya menjaga keseimbangan dengan alam dan menjalani kehidupan yang sederhana namun penuh makna."
            )
        )
        mList.add(
            BatikData(
                "Batik Geblek Renteng",
                R.drawable.geblekrenteng,
                "Batik Geblek Renteng berasal dari Kulon Progo, sebuah kabupaten di Provinsi Daerah Istimewa Yogyakarta. Nama \"Geblek Renteng\" diambil dari dua kata yaitu \"Geblek,\" sejenis makanan tradisional khas Kulon Progo yang terbuat dari singkong, dan \"Renteng,\" yang berarti berderet atau berurutan dalam bahasa Jawa.\n" +
                        "\n" +
                        "Batik Geblek Renteng memiliki motif yang unik dan khas, dengan pola yang terinspirasi dari bentuk makanan geblek yang disusun secara berderet. Motif ini mencerminkan kekayaan budaya dan kearifan lokal masyarakat Kulon Progo.\n" +
                        "\n" +
                        "Batik Geblek Renteng sering dianggap sebagai simbol identitas dan kebanggaan masyarakat Kulon Progo. Motif geblek yang berderet melambangkan kebersamaan, kekuatan komunitas, dan solidaritas sosial. Batik ini banyak digunakan dalam berbagai acara, baik formal maupun informal, dan menjadi salah satu ikon budaya yang berharga dari Kulon Progo. Dengan mengusung tema lokal yang kuat, Batik Geblek Renteng tidak hanya memperkaya ragam batik Indonesia tetapi juga mengangkat kearifan lokal dan tradisi kuliner khas daerah tersebut."
            )
        )
        mList.add(
            BatikData(
                "Batik Simbut",
                R.drawable.simbut,
                "Batik Simbut berasal dari Kabupaten Lebak, Banten. Motif batik ini diciptakan oleh masyarakat adat Baduy, yang tinggal di wilayah pedalaman Banten. Batik Simbut merupakan salah satu warisan budaya yang mencerminkan kehidupan dan kearifan lokal masyarakat Baduy.\n" +
                        "\n" +
                        "Batik Simbut memiliki motif yang sederhana namun penuh makna, dengan pola-pola yang terinspirasi dari daun simbut, sejenis tanaman liar yang banyak ditemukan di sekitar permukiman masyarakat Baduy.\n" +
                        "\n" +
                        "Batik Simbut sering dianggap sebagai simbol kesederhanaan, kearifan lokal, dan keberlanjutan. Motif daun simbut mencerminkan kehidupan masyarakat Baduy yang harmonis dengan alam dan menjaga tradisi leluhur. Batik ini banyak digunakan dalam berbagai acara, baik formal maupun informal, dan menjadi salah satu warisan budaya yang berharga dari masyarakat Baduy. Dengan keindahan dan kesederhanaannya, Batik Simbut tidak hanya memperkaya ragam batik Indonesia tetapi juga mengangkat nilai-nilai kearifan lokal yang penting untuk dilestarikan."
            )
        )
        mList.add(
            BatikData(
                "Batik Toraja",
                R.drawable.toraja,
                "Batik Toraja berasal dari Sulawesi Selatan, khususnya dari daerah Tana Toraja. Tana Toraja dikenal dengan budaya dan tradisinya yang unik dan kaya, yang tercermin dalam berbagai aspek kehidupan masyarakatnya, termasuk dalam seni batik.\n" +
                        "\n" +
                        "Batik Toraja memiliki motif yang khas dengan pola-pola yang terinspirasi dari ukiran dan simbol-simbol adat Toraja. Motif-motif ini mencerminkan kekayaan budaya, spiritualitas, dan filosofi hidup masyarakat Toraja.\n" +
                        "\n" +
                       "Batik Toraja sering dianggap sebagai simbol identitas budaya dan spiritualitas masyarakat Toraja. Motif-motif dalam Batik Toraja tidak hanya indah secara visual tetapi juga sarat dengan makna filosofis dan simbolis. Batik ini sering digunakan dalam berbagai upacara adat, perayaan, dan acara formal, serta menjadi salah satu ikon budaya yang penting dari Sulawesi Selatan. Dengan keunikan dan kekayaan motifnya, Batik Toraja memperkaya khazanah batik Indonesia dan mengangkat warisan budaya yang berharga dari Tana Toraja."
            )
        )
        mList.add(
            BatikData(
                "Batik Sasambo",
                R.drawable.sasambo,
                "Batik Sasambo berasal dari Nusa Tenggara Barat (NTB), khususnya dari tiga etnis utama di wilayah ini yaitu Suku Sasak (Lombok), Suku Samawa (Sumbawa), dan Suku Mbojo (Bima). Nama \"Sasambo\" merupakan akronim dari ketiga suku tersebut, mencerminkan persatuan dan kekayaan budaya di NTB.\n" +
                        "\n" +
                        "Batik Sasambo memiliki motif yang khas dan unik, dengan pola-pola yang mencerminkan keanekaragaman budaya dan alam dari ketiga suku utama di NTB. Motif-motif ini sering kali menggabungkan elemen-elemen dari flora, fauna, serta kehidupan sehari-hari masyarakat setempat.\n" +
                        "\n" +
                        "Batik Sasambo sering dianggap sebagai simbol persatuan dan kebanggaan masyarakat NTB. Motif-motif yang digunakan mencerminkan keanekaragaman budaya dan keindahan alam di NTB, serta menggambarkan kehidupan sehari-hari dan tradisi masyarakat setempat. Batik ini banyak digunakan dalam berbagai acara, baik formal maupun informal, dan menjadi salah satu warisan budaya yang berharga dari Nusa Tenggara Barat. Dengan keindahan dan keunikannya, Batik Sasambo memperkaya ragam batik Indonesia dan mengangkat nilai-nilai budaya dari suku-suku di NTB."
            )
        )

    }
}