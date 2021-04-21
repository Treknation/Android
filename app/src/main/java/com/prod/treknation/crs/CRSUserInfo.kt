package com.prod.treknation.crs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CRSUserInfo(
        @PrimaryKey var id: String,
        @ColumnInfo(name = "age") var age: String? = null,
        @ColumnInfo(name = "educationOutsideCanada)") var educationOutsideCanada: String? = null,
        @ColumnInfo(name = "educationInsideCanada") var educationInsideCanada: String? = null,
        @ColumnInfo(name = "primaryScore") var primaryScore: String? = null,
        @ColumnInfo(name = "primaryScoreMaried") var primaryScoreMaried: String? = null,
        @ColumnInfo(name = "secondaryScoreMaried") var secondaryScoreMaried: String? = null,
        @ColumnInfo(name = "secondaryScore") var secondaryScore: String? = null,
        @ColumnInfo(name = "secondarySpouseScore") var secondarySpouseScore: String? = null,
        @ColumnInfo(name = "primarySpouseScore") var primarySpouseScore: String? = null,

        @ColumnInfo(name = "primaryCLB") var primaryCLB: Int = 0,
        @ColumnInfo(name = "secondaryCLB") var secondaryCLB: Int = 0,
        @ColumnInfo(name = "primaryLang") var primaryLang: String? = null,
        @ColumnInfo(name = "secondaryLang") var secondaryLang: String? = null,
        @ColumnInfo(name = "secondarySpouseCLB") var secondarySpouseCLB: Int = 0,
        @ColumnInfo(name = "primarySpouseCLB") var primarySpouseCLB: Int? = 0,


        @ColumnInfo(name = "additionalJobOffer") var additionalJobOffer: String? = null,
        @ColumnInfo(name = "additionalPrCitizen") var additionalPrCitizen: String? = null,
        @ColumnInfo(name = "additionalNomination") var additionalNomination: String? = null,
        @ColumnInfo(name = "spsEducationOutsideCanada)") var spsEducationOutsideCanada: String? = null,
        @ColumnInfo(name = "spsEducationInsideCanada") var spsEducationInsideCanada: String? = null,
        @ColumnInfo(name = "canWorkExp") var canWorkExp: Int = 0,
        @ColumnInfo(name = "forWorkExp") var forWorkExp: Int = 0,
        @ColumnInfo(name = "certiOfQuali") var certiOfQuali: Boolean = false,
        @ColumnInfo(name = "marital_status") var marital_status: Boolean? = false,

        @ColumnInfo(name = "canWorkExpSpouse") var canWorkExpSpouse: Int = 0,
        @ColumnInfo(name = "forWorkExpSpouse") var forWorkExpSpouse: Int = 0,
        @ColumnInfo(name = "certiOfQualiSpouse") var certiOfQualiSpouse: Boolean = false


)