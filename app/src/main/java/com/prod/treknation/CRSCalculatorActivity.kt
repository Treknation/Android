package com.prod.treknation

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prod.treknation.LangCRSFragment.Companion.Primary
import com.prod.treknation.LangCRSFragment.Companion.Secondary
import com.prod.treknation.crs.AppDatabase
import com.prod.treknation.crs.CRS
import com.prod.treknation.crs.CRSUserInfo
import com.prod.treknation.crs.Language
import com.prod.treknation.model.CLB

@Suppress("UNREACHABLE_CODE")
class CRSCalculatorActivity : AppCompatActivity(), OnFragmentActionCallback {

    private var preferences: SharedPreferences? = null
    private var mMaritalStatus: Boolean? = false
    private var mCRSUserInfo: CRSUserInfo? = null
    private lateinit var crsCalculatorFragment: CRSCalculatorFragment
    private var db: AppDatabase? = null
    private var UserId: String = ""
    private var ageScore: Int = 0;
    private var mCRS: CRS? = null
    private var additionalPointScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crs_calculator)
        db = AppDatabase(this)
        mCRS = CRS()
        preferences = getSharedPreferences(MainActivity.SHARED_PREFS, MODE_PRIVATE)
        preferences?.getString(OnBoardingFragment.USER_ID, "")?.let {
            UserId = it;
        }

        crsCalculatorFragment = CRSCalculatorFragment.newInstance()
        crsCalculatorFragment.setAttachCallback(this)

        supportFragmentManager.beginTransaction()
                .replace(R.id.flCRSContainer, crsCalculatorFragment, CRSCalculatorFragment::class.simpleName)
                .commit()
    }

    override fun onAgeSelected() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flCRSContainer, AgeCRSFragment.newInstance(), AgeCRSFragment::class.simpleName)
                .addToBackStack(AgeCRSFragment::class.simpleName)
                .commit()
    }

    override fun onExpSelected() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flCRSContainer, ExpCRSFragment.newInstance(), ExpCRSFragment::class.simpleName)
                .addToBackStack(ExpCRSFragment::class.simpleName)
                .commit()
    }

    override fun onEduSelected() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flCRSContainer, EduCRSFragment.newInstance(), EduCRSFragment::class.simpleName)
                .addToBackStack(EduCRSFragment::class.simpleName)
                .commit()
    }

    override fun onLangSelected() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flCRSContainer, LangCRSFragment.newInstance(), LangCRSFragment::class.simpleName)
                .addToBackStack(LangCRSFragment::class.simpleName)
                .commit()
    }

    override fun onAdditionalPointSelected() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flCRSContainer, AdditionalPointsCRSFragment.newInstance(), AdditionalPointsCRSFragment::class.simpleName)
                .addToBackStack(AdditionalPointsCRSFragment::class.simpleName)
                .commit()
    }

    override fun onSpouseEduSelected() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flCRSContainer, EduCRSFragment.newInstance(), EduCRSFragment::class.simpleName)
                .addToBackStack(EduCRSFragment::class.simpleName)
                .commit()
    }

    override fun onSpouseExpSelected() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flCRSContainer, ExpCRSFragment.newInstance(), ExpCRSFragment::class.simpleName)
                .addToBackStack(ExpCRSFragment::class.simpleName)
                .commit()
    }

    override fun onSpouseLangSelected() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flCRSContainer, LangCRSFragment.newInstance(), LangCRSFragment::class.simpleName)
                .addToBackStack(LangCRSFragment::class.simpleName)
                .commit()
    }

    public fun getCRSUserInfo(marital_status: Boolean) {
        CRS.married = marital_status
        db?.crsUserInfoDao()?.findByID(UserId).let { crsInfp ->
            if (crsInfp == null) {
                mCRSUserInfo = CRSUserInfo(UserId)
                mCRSUserInfo?.marital_status = marital_status
                db?.crsUserInfoDao()?.insert(mCRSUserInfo)
            } else {
                mCRSUserInfo = crsInfp
                mCRSUserInfo?.marital_status = marital_status
                updateCRSUser()
            }
        }
    }

    public fun getLanguage(TYPE: String, languageName: String): Language {
        db?.languageDao()?.findByID(TYPE, languageName).let { lanag ->
            if (lanag == null) {
                var language = Language(TYPE)
                language.UserID = mCRSUserInfo?.id
                language.LanguageName = languageName
                db?.languageDao()?.insert(language)
                return language
            } else {
                return lanag;
            }
        }
    }

    public fun getLanguage(TYPE: String): Language {
        db?.languageDao()?.findByID(TYPE).let { lanag ->
            if (lanag == null) {
                var language = Language(TYPE)
                language.UserID = mCRSUserInfo?.id
                db?.languageDao()?.insert(language)
                return language
            } else {
                return lanag;
            }
        }
    }

    public fun getExistingUser(): CRSUserInfo? {
        db?.crsUserInfoDao()?.findByID(UserId).let { crsInfp ->
            mCRSUserInfo = crsInfp
            crsInfp?.marital_status?.let {
                CRS.married = it;
            }
            return mCRSUserInfo
        }
        return null
    }


    public fun getCRSUser(): CRSUserInfo? {
        return mCRSUserInfo
    }

    public fun updateCRSUser() {
        mCRSUserInfo?.let { db?.crsUserInfoDao()?.updateTodo(it) }
    }

    public fun updateCRSUser(boolean: Boolean) {
        mCRSUserInfo?.marital_status = boolean
        mCRSUserInfo?.let { db?.crsUserInfoDao()?.updateTodo(it) }
    }

    public fun clearData() {
        mCRSUserInfo?.let { db?.crsUserInfoDao()?.delete(it) }
        mCRSUserInfo?.let { db?.languageDao()?.delete() }

        mCRSUserInfo = null;
    }


    public fun updateLanguage(language: Language) {
        db?.languageDao()?.updateTodo(language)
    }


    public fun age(): Int? {
        mCRSUserInfo?.age?.toInt()?.let {
            updateCRSUser()
            return mCRS?.ageCalculator(it)
        }
        return 0
    }

    public fun age(ageVlaue: Int): Int? {
        ageVlaue.let {
            return mCRS?.ageCalculator(it)
        }
        return 0
    }

    fun skillsTransferability(): Int {
        var total = 0
        var total15and16 = 0
        var totalForeigWorkExperience=0

        total15and16 += mCRS?.goodLanguageScorePostSecondaryDegree(mCRSUserInfo?.primaryCLB, mCRSUserInfo?.educationOutsideCanada
                ?: "") ?: 0
        total15and16 += mCRS?.canadianExperiencePostsecondaryDegree(mCRSUserInfo?.canWorkExp
                ?: 0, mCRSUserInfo?.educationOutsideCanada ?: "") ?: 0

        if (total15and16 >= 50) total += 50 else total += total15and16

        total += workExpOnOtherParameters2() ?: 0

        totalForeigWorkExperience += foreignWorkExp() ?: 0

        totalForeigWorkExperience += mCRS?.foreignWorkExperienceWithLanguage(mCRSUserInfo?.forWorkExp
                ?: 0, mCRSUserInfo?.primaryCLB, mCRSUserInfo?.canWorkExp ?: 0) ?: 0


        if (totalForeigWorkExperience >= 50) total += 50 else total += totalForeigWorkExperience


        return if (total >= 100) 100 else total

    }

    fun frenchLanAddtionalPoint(): Int {
        var total = 0

        if (mCRSUserInfo?.primaryLang?.contains("EF Canada") == true || mCRSUserInfo?.primaryLang?.contains("TCF Canada") == true) {

            if (mCRSUserInfo?.primaryCLB ?: 0 >= 7) {

                when (mCRSUserInfo?.secondaryCLB ?: 0) {
                    in 0..4 -> {
                        total = 25
                    }
                    in 5..20 -> {
                        total = 50
                    }

                }

            }
        }
        return total
    }

    public fun education(): Int {

        var total = educationInsideCanadaCalculator().plus(educationOutsideCalculator())

        return total
    }


    public fun educationOutsideCalculator(): Int {
        mCRSUserInfo?.educationOutsideCanada?.let {
            mCRS?.let { crs ->
                return crs.educationOutsideCanadaCalculator(it)
            }
        }
        return 0
    }

    public fun educationInsideCanadaCalculator(): Int {
        mCRSUserInfo?.educationInsideCanada?.let {
            mCRS?.let { crs ->
                return crs.educationInsideCanadaCalculator(it)

            }
        }
        return 0
    }

    public fun canadianWorkExp(): Int? {
        mCRSUserInfo?.canWorkExp?.let {
            return mCRS?.getCanadianWorkExp(it, mCRSUserInfo?.educationOutsideCanada ?: "")
        }
        return 0
    }

    public fun foreignWorkSpouse(): Int? {
        mCRSUserInfo?.forWorkExpSpouse?.let {
            return mCRS?.getForeignWorkExp(it, mCRSUserInfo?.primarySpouseCLB, mCRSUserInfo?.canWorkExpSpouse
                    ?: 0)
        }
        return 0
    }

    public fun canadianWorkExpSpouse(): Int? {
        mCRSUserInfo?.canWorkExpSpouse?.let {
            return mCRS?.getCanadianWorkExpSpouse(it, mCRSUserInfo?.primarySpouseCLB
                    ?: 0, mCRSUserInfo?.spsEducationOutsideCanada)
        }
        return 0
    }

    public fun foreignWorkExp(): Int? {
        mCRSUserInfo?.forWorkExp?.let {
            return mCRS?.getForeignWorkExp(it, mCRSUserInfo?.primaryCLB, mCRSUserInfo?.canWorkExp
                    ?: 0)
        }
        return 0
    }

    public fun workExpOnOtherParametersSpouse(): Int? {
        mCRSUserInfo?.primarySpouseCLB?.let {

            if (it in (5..7) && mCRSUserInfo?.certiOfQualiSpouse == true) {
                return 25
            }
            if (it > 7 && mCRSUserInfo?.certiOfQualiSpouse == true) {
                return 50
            }

        }
        return 0
    }


    public fun workExpOnOtherParameters(): Int? {

        return 0;

    }

    public fun workExpOnOtherParameters2(): Int? {
        var total = 0

        mCRSUserInfo?.primaryCLB?.let {
            if (it in (5..6) && mCRSUserInfo?.certiOfQuali == true) {
                total = 25
            }
            if (it >=7 && mCRSUserInfo?.certiOfQuali == true) {
                total = 50
            }

        }

        return total;

    }


    public fun workExp(): Int {
        var total = 0
        canadianWorkExp()?.let {
            total = it;
        }
        foreignWorkExp()?.let {
            total += it;
        }

/*
        workExpOnOtherParameters()?.let {
            total += it;
        }
*/
        return total
    }


    public fun workExpSpouse(): Int {
        var total = 0
        canadianWorkExpSpouse()?.let {
            total = it;
        }
        foreignWorkSpouse()?.let {
            total += it;
        }

/*
        workExpOnOtherParametersSpouse()?.let {
            total += it;
        }
*/
        return total
    }


    public fun spsEducationOutsideCalculator(): Int {
        mCRSUserInfo?.spsEducationOutsideCanada?.let {
            updateCRSUser()
            return mCRS?.spsEducationOutsideCanadaCalculator(it) ?: 0
        }
        return 0
    }

    public fun additionalPointJobOfferCalculator(): Int? {
        mCRSUserInfo?.additionalJobOffer?.let {
            updateCRSUser()
            return mCRS?.getAdditionPointCalculator(it)
        }
        return 0
    }

    public fun additionalPointCitizenCalculator(): Int? {
        mCRSUserInfo?.additionalPrCitizen?.let {
            updateCRSUser()
            return mCRS?.getAdditionPointCalculator(it)
        }
        return 0
    }

    public fun additionalPointNominationCalculator(): Int? {
        mCRSUserInfo?.additionalNomination?.let {
            updateCRSUser()
            return mCRS?.getAdditionPointCalculator(it)
        }
        return 0
    }

    fun getAdditionalPointScore(): Int {
        val jobOffer = mCRSUserInfo?.additionalJobOffer ?: ""
        val citizen = mCRSUserInfo?.additionalPrCitizen ?: ""
        val nomination = mCRSUserInfo?.additionalNomination ?: ""

        val jobPoint = mCRS?.getAdditionPointCalculator(jobOffer) ?: 0
        val citizenPoint = mCRS?.getAdditionPointCalculator(citizen) ?: 0
        val nominationPoint = mCRS?.getAdditionPointCalculator(nomination) ?: 0
        var total = jobPoint.plus(citizenPoint).plus(nominationPoint)
        return if (total >= 600) 600 else total
    }

    public fun calculateScore(marital: Boolean): Int? {
        var total = 0
        var totalPointA = 0
        var totalPointC = 0

        var totalsps = 0

        totalPointA = age()?.plus(educationOutsideCalculator())?.plus(calLang())?.plus(canadianWorkExp()
                ?: 0)
                ?: 0

        if (marital) {
            totalsps = total.plus(spsEducationOutsideCalculator().plus(calLangSpouse()).plus(workExpSpouse()))

            if (totalsps >= 40) totalsps = 40

            if (totalPointA >= 460) totalPointA = 460
        } else {
            if (totalPointA >= 500) totalPointA = 500

        }

        totalPointC = getAdditionalPointScore() + educationInsideCanadaCalculator() + frenchLanAddtionalPoint()
        if (totalPointC >= 600) totalPointC = 600

        total = total.plus(totalPointC).plus(skillsTransferability())
        return total.plus(totalsps).plus(totalPointA)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            super.onBackPressed()
        }
    }

    fun lang(): Int {
        var count = 0
        mCRSUserInfo?.primaryScore?.let {
            count = it.toInt()
        }

        mCRSUserInfo?.secondaryScore?.let { it ->
            count += it.toInt()
        }
        return count;

    }

    fun calLang(): Int {
        var count = 0

        updateLanguageScore(Primary)
        updateLanguageScore(Secondary)


        mCRSUserInfo?.primaryScore?.let {
            count = it.toInt()
        }

        mCRSUserInfo?.secondaryScore?.let { it ->
            count += it.toInt()
        }

        return count;


    }

    fun calLangSpouse(): Int {
        updateLanguageScore(CRSCalculatorFragment.SPOUSE_LANG)
        var count = 0
        mCRSUserInfo?.primarySpouseScore?.let {
            count = it.toInt()
        }

        return count;

    }

    fun updateLanguageScore(key: String) {
        var total = 0
        var minClb = 0
        getLanguage(key).let {
            val numbers: MutableList<CLB> = ArrayList()
            it?.listening?.let {
                numbers.add(CLB(it.toDouble(), "listening"))
            }
            it?.reading?.let {
                numbers.add(CLB(it.toDouble(), "reading"))
            }
            it?.writing?.let {
                numbers.add(CLB(it.toDouble(), "writing"))
            }
            it?.speaking?.let {
                numbers.add(CLB(it.toDouble(), "speaking"))
            }

            if (numbers.isEmpty())
                numbers.add(CLB(0.0, ""))

            var minScore = numbers.minByOrNull { it.count }

            numbers.forEach { selection ->
                var clb = 0
                it?.let {
                    when (it?.LanguageName) {
                        "IELTS" -> {
                            clb = mCRS?.IELTS(selection) ?: 0
                            minClb = mCRS?.IELTS(minScore) ?: 0
                        }
                        "CELPIP" -> {
                            clb = mCRS?.CELPIP(selection) ?: 0
                            minClb = mCRS?.CELPIP(minScore)
                                    ?: 0


                        }

                        "TEF Canada" -> {

                            clb = mCRS?.TEF_Canada(selection)
                                    ?: 0
                            minClb = mCRS?.TEF_Canada(minScore)
                                    ?: 0


                        }

                        "TCF Canada" -> {

                            clb = mCRS?.TCF_Canada(selection)
                                    ?: 0
                            minClb = mCRS?.TCF_Canada(minScore) ?: 0
                        }

                    }

                    when (selection.name) {

                        "listening" -> {
                            it?.listeningCLB = clb
                            Log.d("CLB:", selection.name + ">" + clb.toString())

                        }
                        "reading" -> {
                            it?.readingCLB = clb
                            Log.d("CLB:", selection.name + ">" + clb.toString())

                        }
                        "writing" -> {
                            it?.writingCLB = clb
                            Log.d("CLB:", selection.name + ">" + clb.toString())

                        }

                        "speaking" -> {
                            it?.speakingCLB = clb
                            Log.d("CLB:", selection.name + ">" + clb.toString())

                        }

                    }

                    when (key) {
                        Primary -> {
                            total += calculatePrimaryLag(clb) ?: 0
                            mCRSUserInfo?.primaryCLB = minClb
                            mCRSUserInfo?.primaryScore = total.toString()
                            mCRSUserInfo?.primaryLang = it.LanguageName


                        }
                        Secondary -> {
                            total += calculateSecondaryLag(clb) ?: 0
                            mCRSUserInfo?.secondaryCLB = minClb
                            mCRSUserInfo?.secondaryScore = total.toString()
                            mCRSUserInfo?.secondaryLang = it.LanguageName


                        }
                        CRSCalculatorFragment.SPOUSE_LANG -> {
                            total += calculatePrimaryLagForSpouse(clb) ?: 0
                            mCRSUserInfo?.primarySpouseCLB = minClb
                            mCRSUserInfo?.primarySpouseScore = total.toString()
                        }

                    }


                }

            }

            Log.d("Total", key + ":" + total)
        }
    }

    fun calculatePrimaryLagForSpouse(clb: Int?): Int {

        mCRS?.let {
            return it.calculateSecondaryLagScoreSpouse(clb)
        }
        return 0
    }

    fun calculatePrimaryLag(clb: Int?): Int {
        mCRS?.let {
            return it.calculatePrimaryLagScore(clb)
        }
        return 0
    }


    fun calculateSecondaryLag(clb: Int?): Int {
        mCRS?.let {
            return it.calculateSecondaryLagScore(clb)
        }
        return 0
    }

    fun getCRS(): CRS? {
        return this.mCRS
    }

}