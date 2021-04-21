package com.prod.treknation.crs

import com.prod.treknation.model.CLB

class CRS {
    companion object {
        var married: Boolean = true

    }


    public fun ageCalculator(age: Int): Int {

        when (age) {
            17 -> return 0;

            18 -> if (married) return 90 else return 99

            19 -> if (married) return 95 else return 105

            in 20..29 -> if (married) return 100 else return 110

            30 -> if (married) return 95 else return 105

            31 -> if (married) return 90 else return 99
            32 -> if (married) return 85 else return 94
            33 -> if (married) return 80 else return 88
            34 -> if (married) return 75 else return 83
            35 -> if (married) return 70 else return 77
            36 -> if (married) return 65 else return 72
            37 -> if (married) return 60 else return 66
            38 -> if (married) return 55 else return 61
            39 -> if (married) return 50 else return 55

            40 -> if (married) return 45 else return 50

            41 -> if (married) return 35 else return 39

            42 -> if (married) return 25 else return 28
            43 -> if (married) return 15 else return 17

            44 -> if (married) return 5 else return 6

            in 44..110 -> if (married) return 0 else return 0

            else -> {
                return 0
            }
        }

    }

    public fun educationOutsideCanadaCalculator(education: String): Int {
        when (education) {
            "Less than secondary school (high school)" -> return 0
            "Secondary diploma (high school graduation)" -> if (married) return 28 else return 30
            "One-year degree, diploma or certificate from a university, college, trade or technical school, or other institute" -> if (married) return 48 else return 90
            "Two-year program at a university, college, trade or technical school, or other institute" -> if (married) return 91 else return 98
            "Bachelor's degree OR a three or more year program at a university, college, trade or technical school, or other institute" -> if (married) return 112 else return 120
            "Two or more certificates, diplomas, or degrees. One must be for a program of three or more years" -> if (married) return 119 else return 128
            "Master\'s degree, OR professional degree needed to practice in a licensed profession (For “professional degree,” the degree program must have been in: medicine, veterinary medicine, dentistry, optometry, law, chiropractic medicine, or pharmacy.)" -> if (married) return 126 else return 135
            "Doctoral level university degree (Ph.D.)" -> if (married) return 140 else return 150
            else -> {
                return 0
            }

        }

    }

    public fun spsEducationOutsideCanadaCalculator(education: String): Int {
        when (education) {
            "Less than secondary school (high school)" -> return 0
            "Secondary diploma (high school graduation)" -> return 2
            "One-year degree, diploma or certificate from a university, college, trade or technical school, or other institute" -> return 6
            "Two-year program at a university, college, trade or technical school, or other institute" -> return 7
            "Bachelor's degree OR a three or more year program at a university, college, trade or technical school, or other institute" -> return 8
            "Two or more certificates, diplomas, or degrees. One must be for a program of three or more years" -> return 9
            "Master\'s degree, OR professional degree needed to practice in a licensed profession (For “professional degree,” the degree program must have been in: medicine, veterinary medicine, dentistry, optometry, law, chiropractic medicine, or pharmacy.)" -> return 10
            "Doctoral level university degree (Ph.D.)" -> return 10
            else -> {
                return 0
            }

        }

    }


    public fun educationInsideCanadaCalculator(education: String): Int {
        when (education) {
            "Secondary High School" -> return 0
            "One or two year diploma or certificate" -> return 15
            "Degree, diploma or certificate of 3 years or longer or a Master’s profession or Doctoral degree of at least 1 academic year" -> return 30
            else -> {
                return 0
            }
        }
    }

    fun getAdditionPointCalculator(points: String): Int {
        return when (points) {
            "NOC - OO" -> 200
            "NOC - O/A/B" -> 50
            "citizen_Yes" -> 15
            "nomination_Yes" -> 600
            else -> 0
        }
    }

    fun getCanadianWorkExp(workExp: Int, education: String): Int {
        var total = when (workExp) {
            1 -> if (married) 35 else 40
            2 -> if (married) 46 else 53
            3 -> if (married) 56 else 64
            4 -> if (married) 63 else 72
            5 -> if (married) 70 else 80
            else -> 0
        }

        return total
    }

    // point 16
    fun canadianExperiencePostsecondaryDegree(workExp: Int, education: String): Int {
        var total = 0
        if (workExp > 0) {
            var isOne = true
            if (workExp == 1)
                isOne = true
            else if (workExp > 1)
                isOne = false

            total += when (education) {
                "One-year degree, diploma or certificate from a university, college, trade or technical school, or other institute" -> if (isOne) 13 else 25
                "Two-year program at a university, college, trade or technical school, or other institute" -> if (isOne) 13 else 25
                "Bachelor's degree OR a three or more year program at a university, college, trade or technical school, or other institute" -> if (isOne) 13 else 25
                "Two or more certificates, diplomas, or degrees. One must be for a program of three or more years" -> if (isOne) 25 else 50
                "Master\'s degree, OR professional degree needed to practice in a licensed profession (For “professional degree,” the degree program must have been in: medicine, veterinary medicine, dentistry, optometry, law, chiropractic medicine, or pharmacy.)" -> if (isOne) 25 else 50
                "Doctoral level university degree (Ph.D.)" -> if (isOne) 25 else 50
                else -> {
                    0
                }

            }

        }
        return total;

    }

    //point 15
    fun goodLanguageScorePostSecondaryDegree(primaryCLB: Int?, educationOutsideCanada: String): Int {
        var total = 0
        primaryCLB?.let {
            if (it >= 7) {
                var langCLB = true
                if (it in 7..8)
                    langCLB = true
                else if (it >= 9)
                    langCLB = false

                total += when (educationOutsideCanada) {
                    "Less than secondary school (high school)" -> if (langCLB) 0 else 0
                    "Secondary diploma (high school graduation)" -> if (langCLB) 0 else 0
                    "One-year degree, diploma or certificate from a university, college, trade or technical school, or other institute" -> if (langCLB) 13 else 25
                    "Two-year program at a university, college, trade or technical school, or other institute" -> if (langCLB) 13 else 25
                    "Bachelor's degree OR a three or more year program at a university, college, trade or technical school, or other institute" -> if (langCLB) 13 else 25
                    "Two or more certificates, diplomas, or degrees. One must be for a program of three or more years" -> if (langCLB) 25 else 50
                    "Master\'s degree, OR professional degree needed to practice in a licensed profession (For “professional degree,” the degree program must have been in: medicine, veterinary medicine, dentistry, optometry, law, chiropractic medicine, or pharmacy.)" -> if (langCLB) 25 else 50
                    "Doctoral level university degree (Ph.D.)" -> if (langCLB) 25 else 50
                    else -> {
                        0
                    }

                }

            }
        }
        return total

    }


    fun getCanadianWorkExpSpouse(workExp: Int, primarySpouseCLB: Int, spsEducationInsideCanada: String?): Int {
        var total = 0
        total = when (workExp) {
            1 -> 5
            2 -> 7
            3 -> 8
            4 -> 9
            5 -> 10
            else -> 0
        }





        return total
    }

   fun foreignWorkExperienceWithLanguage(workExp: Int, primaryCLB: Int?, canWorkExp: Int): Int {
        //Foreign Work Experience with Language
        var total = 0
        if (primaryCLB != 0) {
            var clb = true
            if (primaryCLB in 7..8) {
                clb = true;
            } else if (primaryCLB in 9..100) {
                clb = false;
            }

            when (workExp) {

                in 1..2 -> if (clb) total = 13 else total = 25

                3 -> if (clb) total = 25 else total = 50
            }

        }
       return total

    }


    fun getForeignWorkExp(workExp: Int, primaryCLB: Int?, canWorkExp: Int): Int {

        var total = 0

        // Foreign Work Experience with Canadian Work Experience
        if (canWorkExp == 1 && workExp != 0) {
            when (workExp) {

                in 1..2 -> total += 13

                3 -> total += 25
            }

            return (total) ?: total

        } else if (canWorkExp >= 2 && workExp != 0) {
            when (workExp) {

                in 1..2 -> total += 25

                3 -> total += 50
            }

            return (total) ?: total
        }

        return if(total>=50)50 else total
    }


    fun calculatePrimaryLagScore(clb: Int?): Int {

        when (clb) {
            in 0..3 -> return 0;
            in 4..5 -> return 6;

            6 -> if (married) return 8 else return 9

            7 -> if (married) return 16 else return 17

            8 -> if (married) return 22 else return 23

            9 -> if (married) return 29 else return 31

            10 -> if (married) return 32 else return 34
            else -> {
                return 0
            }
        }
    }

    fun calculateSecondaryLagScoreSpouse(clb: Int?): Int {
        when (clb) {
            in 0..4 -> return 0;

            in 5..6 -> if (married) return 1 else return 1

            in 7..8 -> if (married) return 3 else return 3

            in 9..10 -> if (married) return 5 else return 5

            else -> {
                return 0
            }
        }

    }


    fun calculateSecondaryLagScore(clb: Int?): Int {
        when (clb) {
            in 0..4 -> return 0;

            in 5..6 -> if (married) return 1 else return 1

            in 7..8 -> if (married) return 3 else return 3

            in 9..10 -> if (married) return 6 else return 6

            else -> {
                return 0
            }
        }

    }


    fun CELPIP(min: CLB?): Int {
        when (min?.count?.toInt()) {
            in 0..3 -> return 0;

            5 -> return 5

            4 -> return 4

            6 -> return 6

            7 -> return 7

            8 -> return 8

            9 -> return 9

            in 10..12 -> return 10
            else -> {
                return 0
            }
        }


    }

    fun IELTS(score: CLB?): Int {
        val min = CLB(score?.count, score?.name)
        min.count = min.count?.div(2)

        when (min.name) {
            "listening" -> {
                when (min.count) {

                   in 0.0.. 4.5 -> return 4

                   in 4.6.. 5.0 -> return 5

                   in 5.1..5.5 -> return 6

                    in 5.6..6.0 -> return 7

                    in 6.1..7.5 -> return 8

                    in 7.6..8.0 -> return 9

                    in 8.1..9.0 -> return 10
                    else -> {
                        return 0
                    }
                }


            }

            "reading" -> {
                when (min.count) {

                    3.5 -> {
                        return 4
                    }

                   in 3.5..4.0 -> return 5

                   in 4.1..5.0 -> return 6

                   in 5.1..6.0 -> return 7

                  in 6.1..6.5 -> return 8

                    in 6.6..7.9 -> return 9

                    in 8.0..9.0 -> return 10
                    else -> {
                        return 0
                    }
                }

            }


            "writing" -> {
                when (min.count) {

                    4.0 -> {
                        return 4
                    }

                    5.0 -> return 5

                    5.5 -> return 6

                    6.0 -> return 7

                    6.5 -> return 8

                    7.0 -> return 9

                    in 7.1..9.0 -> return 10
                    else -> {
                        return 0
                    }
                }

            }


            "speaking" -> {
                when (min.count) {

                    4.0 -> {
                        return 4
                    }

                    5.0 -> return 5

                    5.5 -> return 6

                    6.0 -> return 7

                    6.5 -> return 8

                    7.0 -> return 9

                    in 7.1..9.0 -> return 10
                    else -> {
                        return 0
                    }
                }


            }
        }
        return 0
    }

    fun TEF_Canada(findMin: CLB?): Int {
        when (findMin?.name) {

            "reading" -> {
                when (findMin.count.toInt()) {
                    in 263..300 -> return 10
                    in 248..262 -> return 9
                    in 233..247 -> return 8
                    in 207..232 -> return 7
                    in 181..206 -> return 6
                    in 151..180 -> return 5
                    in 121..150 -> return 4
                    else -> {
                        return 0
                    }
                }


            }

            "writing" -> {
                when (findMin.count.toInt()) {
                    in 393..450 -> return 10
                    in 371..392 -> return 9
                    in 349..370 -> return 8
                    in 310..348 -> return 7
                    in 271..309 -> return 6
                    in 226..270 -> return 5
                    in 181..225 -> return 4
                    else -> {
                        return 0
                    }
                }

            }


            "speaking" -> {
                when (findMin.count.toInt()) {
                    in 393..450 -> return 10
                    in 371..392 -> return 9
                    in 349..370 -> return 8
                    in 310..348 -> return 7
                    in 271..309 -> return 6
                    in 226..270 -> return 5
                    in 181..225 -> return 4
                    else -> {
                        return 0
                    }
                }

            }


            "listening" -> {
                when (findMin.count.toInt()) {
                    in 316..360 -> return 10
                    in 298..315 -> return 9
                    in 280..297 -> return 8
                    in 249..279 -> return 7
                    in 217..248 -> return 6
                    in 181..216 -> return 5
                    in 145..180 -> return 4
                    else -> {
                        return 0
                    }
                }


            }
        }


        return 0
    }

    fun TCF_Canada(findMin: CLB?): Int? {
        when (findMin?.name) {

            "reading" -> when (findMin.count.toInt()) {
                in 549..699 -> return 10
                in 524..548 -> return 9
                in 499..523 -> return 8
                in 453..498 -> return 7
                in 406..452 -> return 6
                in 372..405 -> return 5
                in 342..374 -> return 4
                else -> return 0

            }

            "writing" -> {
                when (findMin.count.toInt()) {
                    in 16..20 -> return 10
                    in 14..15 -> return 9
                    in 12..13 -> return 8
                    in 10..11 -> return 7
                    in 7..9 -> return 6
                    6 -> return 5
                    in 4..5 -> return 4
                    else -> return 0

                }

            }


            "speaking" -> {
                when (findMin.count.toInt()) {
                    in 16..20 -> return 10
                    in 14..15 -> return 9
                    in 12..13 -> return 8
                    in 10..11 -> return 7
                    in 7..9 -> return 6
                    6 -> return 5
                    in 4..5 -> return 4
                    else -> return 0

                }

            }


            "listening" -> {
                when (findMin.count.toInt()) {
                    in 549..699 -> return 10
                    in 523..548 -> return 9
                    in 503..522 -> return 8
                    in 458..502 -> return 7
                    in 398..457 -> return 6
                    in 369..397 -> return 5
                    in 331..368 -> return 4
                    else -> return 0

                }


            }
        }
        return 0

    }

    fun haveCertificate(it: String) {

    }


}