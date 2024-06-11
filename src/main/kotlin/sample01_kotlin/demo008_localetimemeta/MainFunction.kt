package sample01_kotlin.demo008_localetimemeta

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main1() {
    val targetPath = "C:\\Users\\zoyng\\OneDrive\\바탕 화면\\locale"
    File(targetPath).walkTopDown().forEach {
        try {
            readFile(it)
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun main2() {
    val date = LocalDate.of(2024, 1,28)
    val format = DateTimeFormatter.ofPattern("DD", Locale.CHINA) // dd, D

    println(date.format(format))
}

fun readFile(file: File) {
//    println(file.name + " " + file.absolutePath + " --------------------- start")
    // year month day 글자획득

    val valExtractor: (String)->String = {rawVal ->
        rawVal.replace("yy: ", "")
            .replace(" ", "")
            .replace("%d", "")
            .replace("'", "")
            .replace(",", "")
            .trim()
    }

    var yearWord = ""
    var monthWord = ""
    var dayWord = ""

    var reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    reader.lines().forEach {
        if (it.trim().startsWith("yy: ")) {
            yearWord = valExtractor(it.replace("YY:","").trim())
        } else if (it.trim().startsWith("MM: ")) {
            monthWord = valExtractor(it.replace("MM:","").trim())
        } else if (it.trim().startsWith("dd: ")) {
            dayWord = valExtractor(it.replace("dd:","").trim())
        }
//        println(it)
    }

    var ymdSort = ""
    reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    reader.lines().forEach {
        if (it.trim().startsWith("LL: ")) {
            ymdSort = it.trim()
                .replace(Regex("[^YMD]"), "")
                .replace("YY", "Y")
                .replace("MM", "M")
                .replace("DD", "D")
                .replace("YY", "Y")
                .replace("MM", "M")
                .replace("DD", "D")
                .trim()
//            println("ymdSort : " + ymdSort)
        }
    }
//}

    val localeName = file.name.replaceAfter(".","").replace(".","")
    //*
    println(
        "%-10s".format(localeName.replace("-","_")) +
        "(" +
            "\"$ymdSort\"," +
            "\"$yearWord\"," +
            "\"$monthWord\"," +
            "\"$dayWord\"" +
        "),"
    )
    //*/

}
/*
ar-ly
be
bs
cv
en
gom-latn
lb
me
mr
tlh
tzl
uk
fi


*/

/*
enum class LocaleDateMeta(
    ymdSort: String,
    yearWord: String,
    monthWord: String,
    dayWord: String,
) {
    af        ("DMY","jaar","maande","dae"),
    am        ("MDY","ዓመታት","ወራት","ቀናት"),
    ar_dz     ("DMY","سنوات","أشهر","أيام"),
    ar_iq     ("DMY","سنوات","أشهر","أيام"),
    ar_kw     ("DMY","سنوات","أشهر","أيام"),
    ar_ly     ("DMY","","",""),
    ar_ma     ("DMY","سنوات","أشهر","أيام"),
    ar_sa     ("DMY","سنوات","أشهر","أيام"),
    ar_tn     ("DMY","سنوات","أشهر","أيام"),
    ar        ("DMY","أعوام","أشهر","أيام"),
    az        ("DMY","il","ay","gün"),
    be        ("DMY","","",""),
    bg        ("DMY","години","месеца","дена"),
    bi        ("DMY","yia","manis","dei"),
    bm        ("MDY","san","kalo","tile"),
    bn_bd     ("DMY","বছর","মাস","দিন"),
    bn        ("DMY","বছর","মাস","দিন"),
    bo        ("DMY","ལོ་","ཟླ་བ་","ཉིན་"),
    br        ("DMY","specialMutationForYears","relativeTimeWithMutation","relativeTimeWithMutation"),
    bs        ("DMY","","",""),
    ca        ("DMY","anys","mesos","dies"),
    cs        ("DMY","translate","translate","translate"),
    cv        ("YMD","","",""),
    cy        ("DMY","flynedd","mis","diwrnod"),
    da        ("DMY","år","måneder","dage"),
    de_at     ("DMY","relativeTimeFormatter","relativeTimeFormatter","relativeTimeFormatter"),
    de_ch     ("DMY","relativeTimeFormatter","relativeTimeFormatter","relativeTimeFormatter"),
    de        ("DMY","relativeTimeFormatter","relativeTimeFormatter","relativeTimeFormatter"),
    dv        ("DMY","އަހަރު","މަސް","ދުވަސް"),
    el        ("DMY","χρόνια","μήνες","μέρες"),
    en_au     ("DMY","years","months","days"),
    en_ca     ("MDY","years","months","days"),
    en_gb     ("DMY","years","months","days"),
    en_ie     ("DMY","years","months","days"),
    en_il     ("DMY","years","months","days"),
    en_in     ("DMY","years","months","days"),
    en_nz     ("DMY","years","months","days"),
    en_sg     ("DMY","years","months","days"),
    en_tt     ("DMY","years","months","days"),
    en        ("","","",""),
    eo        ("DMY","jaroj","monatoj","tagoj"),
    es_do     ("DMY","años","meses","días"),
    es_mx     ("DMY","años","meses","días"),
    es_pr     ("DMY","años","meses","días"),
    es_us     ("DMY","años","meses","días"),
    es        ("DMY","años","meses","días"),
    et        ("DMY","relativeTimeWithTense","relativeTimeWithTense","päeva"),
    eu        ("YMD","urte","hilabete","egun"),
    fa        ("DMY","سال","ماه","روز"),
    fi        ("DMY","relativeTimeFormatter","relativeTimeFormatter","relativeTimeFormatter"),
    fo        ("DMY","ár","mánaðir","dagar"),
    fr_ca     ("DMY","ans","mois","jours"),
    fr_ch     ("DMY","ans","mois","jours"),
    fr        ("DMY","ans","mois","jours"),
    fy        ("DMY","jierren","moannen","dagen"),
    ga        ("DMY","bliain","mí","lá"),
    gd        ("DMY","bliadhna","mìosan","latha"),
    gl        ("DMY","anos","meses","días"),
    gom_latn  ("DMY","","",""),
    gu        ("DMY","વર્ષ","મહિનો","દિવસ"),
    he        ("DMY","relativeTimeFormatter","relativeTimeFormatter","relativeTimeFormatter"),
    hi        ("DMY","वर्ष","महीने","दिन"),
    hr        ("DMY","godine","mjeseci","dana"),
    ht        ("DMY","ane","mwa","jou"),
    hu        ("YMD","(ns___isFuture)=>`${n}${isFuture||s?év:éve}`","(ns___isFuture)=>`${n}${isFuture||s?hónap:hónapja}`","(ns___isFuture)=>`${n}${isFuture||s?nap:napja}`"),
    hy_am     ("DMY","տարի","ամիս","օր"),
    id        ("DMY","tahun","bulan","hari"),
    is        ("DMY","relativeTimeFormatter","relativeTimeFormatter","relativeTimeFormatter"),
    it_ch     ("DMY","anni","mesi","giorni"),
    it        ("DMY","anni","mesi","giorni"),
    ja        ("YMD","年","ヶ月","日"),
    jv        ("DMY","taun","wulan","dinten"),
    ka        ("DMY","წლის","თვის","დღისგანმავლობაში"),
    kk        ("DMY","жыл","ай","күн"),
    km        ("DMY","ឆ្នាំ","ខែ","ថ្ងៃ"),
    kn        ("DMY","ವರ್ಷ","ತಿಂಗಳು","ದಿನ"),
    ko        ("YMD","년","달","일"),
    ku        ("DMY","ساڵ","مانگ","ڕۆژ"),
    ky        ("DMY","жыл","ай","күн"),
    lb        ("DMY","","",""),
    lo        ("DMY","ປີ","ເດືອນ","ມື້"),
    lt        ("YMD","metus","mėnesius","dienas"),
    lv        ("YDM","gadiem","mēnešiem","dienām"),
    me        ("DMY","","",""),
    mi        ("DMY","tau","marama","ra"),
    mk        ("DMY","години","месеци","дена"),
    ml        ("DMY","വർഷം","മാസം","ദിവസം"),
    mn        ("YMD","ж","с","ө"),
    mr        ("DMY","","",""),
    ms_my     ("DMY","tahun","bulan","hari"),
    ms        ("DMY","tahun","bulan","hari"),
    mt        ("DMY","sni","xhur","ġranet"),
    my        ("DMY","နှစ်","လ","ရက်"),
    nb        ("DMY","år","måneder","dager"),
    ne        ("DMY","वर्ष","महिना","दिन"),
    nl_be     ("DMY","jaar","maanden","dagen"),
    nl        ("DMY","jaar","maanden","dagen"),
    nn        ("DMY","år","månadar","dagar"),
    oc_lnc    ("DMY","ans","meses","jorns"),
    pa_in     ("DMY","ਸਾਲ","ਮਹੀਨੇ","ਦਿਨ"),
    pl        ("DMY","translate","translate","dni"),
    pt_br     ("DMY","anos","meses","dias"),
    pt        ("DMY","anos","meses","dias"),
    rn        ("DMY","imyaka","amezi","iminsi"),
    ro        ("DMY","ani","luni","zile"),
    ru        ("DMY","relativeTimeWithPlural","relativeTimeWithPlural","relativeTimeWithPlural"),
    rw        ("DMY","imyaka","amezi","iminsi"),
    sd        ("DMY","سال","مهينا","ڏينهن"),
    se        ("MDY","jagit","mánut","beaivvit"),
    si        ("YMD","වසර","මාස","දින"),
    sk        ("DMY","translate","translate","translate"),
    sl        ("DMY","translate","translate","translate"),
    sq        ("DMY","vite","muaj","ditë"),
    sr_cyrl   ("DMY","translator.relativeTimeFormatter","translator.relativeTimeFormatter","translator.relativeTimeFormatter"),
    sr        ("DMY","translator.relativeTimeFormatter","translator.relativeTimeFormatter","translator.relativeTimeFormatter"),
    ss        ("DMY","iminyaka","tinyanga","emalanga"),
    sv_fi     ("DMY","år","månader","dagar"),
    sv        ("DMY","år","månader","dagar"),
    sw        ("DMY","miaka","miezi","masiku"),
    ta        ("DMY","ஆண்டுகள்","மாதங்கள்","நாட்கள்"),
    te        ("DMY","సంవత్సరాలు","నెలలు","రోజులు"),
    tet       ("DMY","tinan","fulan","loron"),
    tg        ("DMY","сол","моҳ","рӯз"),
    th        ("DMY","ปี","เดือน","วัน"),
    tk        ("DMY","ýyl","aý","gün"),
    tl_ph     ("MDY","taon","buwan","araw"),
    tlh       ("DMY","","",""),
    tr        ("DMY","yıl","ay","gün"),
    tzl       ("DMY","","",""),
    tzm_latn  ("DMY","isgasn","iyyirn","ossan"),
    tzm       ("DMY","ⵉⵙⴳⴰⵙⵏ","ⵉⵢⵢⵉⵔⵏ","oⵙⵙⴰⵏ"),
    ug_cn     ("YMD","يىل","ئاي","كۈن"),
    uk        ("DMY","relativeTimeWithPlural","relativeTimeWithPlural","relativeTimeWithPlural"),
    ur        ("DMY","سال","ماہ","دن"),
    uz_latn   ("DMY","yil","oy","kun"),
    uz        ("DMY","йил","ой","кун"),
    vi        ("DMY","năm","tháng","ngày"),
    x_pseudo  ("DMY","ý~éárs","m~óñt~hs","d~áýs"),
    yo        ("DMY","ọdún","osù","ọjọ́"),
    zh_cn     ("YMD","年","个月","天"),
    zh_hk     ("YMD","年","個月","天"),
    zh_tw     ("YMD","年","個月","天"),
    zh        ("YMD","年","个月","天"),
}

*/